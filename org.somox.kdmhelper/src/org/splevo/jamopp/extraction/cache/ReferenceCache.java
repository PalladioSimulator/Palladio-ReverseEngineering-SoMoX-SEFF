/*******************************************************************************
 * Copyright (c) 2014
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Benjamin Klatt - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.splevo.jamopp.extraction.cache;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.emftext.language.java.JavaClasspath;

/**
 * A file based cache to reuse the proxy resolutions already performed.
 *
 * The cache was designed to work with one or more cache files to use it with resource sets
 * containing the resources of one or more software models. For example, during extraction a
 * separate resource set is used per software, but for differencing several software models must be
 * accessed in one resource set.
 *
 * Cache files are always named according to {@link #CACHE_FILE_NAME}.
 *
 * During initialization, cache files existing in the provided directories are loaded.
 * Subdirectories are not considered.
 *
 * When proxies in new resources are resolved and {@link #save()} is triggered, they are stored in a
 * cache file of the first directory provided in the list. If a cache file already exists in the
 * first cache directory, the existing cache is loaded and enhanced with the new cached references.
 */
public class ReferenceCache {

    /** The name of the cache files to be used. */
    public static final String CACHE_FILE_NAME = "jamopp.cache";

    private static Logger logger = Logger.getLogger(ReferenceCache.class);

    /**
     * Internal counter how many references have not been resolved from cache, while their resources
     * have. This is an indicator for failed proxy resolutions or that the cache was not involved in
     * the resolution.
     *
     */
    private int notResolvedFromCacheCounterReference = 0;

    /**
     * The file the cache will be serialized into.
     */
    private final List<String> cacheFileDirectories;

    /** The cache data object to work with. */
    private final ReferenceCacheData cacheData = new ReferenceCacheData();

    /** The java class path to enhance with the cached data. */
    private JavaClasspath javaClasspath = null;

    /**
     * Constructor to set a list of directories containing cache files. Within these directories,
     * files with the name {@link #CACHE_FILE_NAME} are searched.
     *
     * If a new file must be created, this will be done in the first directory of the list.
     *
     * @param cacheFileDirectories
     *            A list of absolute paths to the directories containing cache files.
     * @param javaClasspath
     *            The java class path to enhance with the cached data
     */
    public ReferenceCache(final List<String> cacheFileDirectories, final JavaClasspath javaClasspath) {
        this(cacheFileDirectories, javaClasspath, new ArrayList<String>());
    }

    /**
     * Constructor to set a list of directories containing cache files. Within these directories,
     * files with the name {@link #CACHE_FILE_NAME} are searched.
     *
     * If a new file must be created, this will be done in the first directory of the list.
     *
     * @param cacheFileDirectories
     *            A list of absolute paths to the directories containing cache files.
     * @param javaClasspath
     *            The java class path to enhance with the cached data
     * @param jarPaths
     *            A list of paths to jar files to be registered in the {@link JavaClasspath} and
     *            stored in the cache.
     */
    public ReferenceCache(final List<String> cacheFileDirectories, final JavaClasspath javaClasspath,
            final List<String> jarPaths) {
        this.cacheFileDirectories = cacheFileDirectories;
        this.javaClasspath = javaClasspath;
        this.cacheData.getJarFilePaths().addAll(jarPaths);
        this.init();
    }

    /**
     * Initialize the cache by loading all cache files available in the configured directory.
     *
     * In addition, register the jar files in the {@link JavaClasspath}.
     */
    private void init() {
        for (final String cacheDirectory : this.cacheFileDirectories) {
            final File cacheFile = new File(cacheDirectory + File.separator + CACHE_FILE_NAME);
            if (cacheFile.exists() && cacheFile.canRead()) {
                final ReferenceCacheData loadedCacheData = this.load(cacheFile);
                if (loadedCacheData != null) {
                    this.cacheData.merge(loadedCacheData);
                }
            }
        }

        for (final String jarPath : this.cacheData.getJarFilePaths()) {
            this.javaClasspath.registerClassifierJar(URI.createFileURI(jarPath));
        }
        logger.info("Registered " + this.cacheData.getJarFilePaths().size() + " jar files from cache.");
    }

    /**
     * Forces the complete resolving of the resource.
     *
     * <p>
     * <strong>Note:</strong> This should be used for loading the cache only. It is also recommended
     * to call this method not before all resources are present in the ResourceSet.
     * </p>
     *
     * @param resource
     *            Resource to be resolved.
     */
    public void resolve(final Resource resource) {
        EcoreUtil.resolveAll(resource);
    }

    /**
     * Trigger to save all non yet persisted cache entries.<br>
     * These are the entries created for resources that could not be loaded from any existing cache
     * file.
     *
     * If more than one cache file directory was created, the first entry in the list will be used.
     *
     * If the cache file already exists, it will not be overridden, but loaded and the new entries
     * will be added to it.
     *
     */
    public void save() {

        if (this.cacheFileDirectories == null || this.cacheFileDirectories.size() < 1
                || this.cacheFileDirectories.get(0) == null) {
            logger.warn("No cache file directory(ies) configured");
            return;
        }

        final File cacheFile = new File(this.cacheFileDirectories.get(0) + File.separator + CACHE_FILE_NAME);
        ReferenceCacheData cacheDataExisting = this.load(cacheFile);
        if (cacheDataExisting == null) {
            cacheDataExisting = new ReferenceCacheData();
            this.cacheData.merge(cacheDataExisting);
        }

        this.save(cacheFile, this.cacheData);
    }

    /**
     * Persist the cache in the file system.
     *
     * @param cacheFile
     *            The file to save to.
     * @param cacheData
     *            The cache data to save.
     */
    public synchronized void save(final File cacheFile, final ReferenceCacheData cacheData) {
        ObjectOutputStream oos = null;
        try {
            FileUtils.forceMkdir(cacheFile.getParentFile());
            oos = new ObjectOutputStream(new FileOutputStream(cacheFile));
            oos.writeObject(cacheData);
        } catch (final FileNotFoundException e) {
            logger.info("cache file does not exist yet" + cacheFile);
        } catch (final IOException e) {
            logger.warn("cache file could not be accessed: " + cacheFile);
        } finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (final IOException e) {
                    logger.warn("Failed to close cache file output stream", e);
                }
            }
        }
    }

    /**
     * Load the cache from a file.
     *
     * @param cacheFile
     *            The file to load.
     * @return The cache map loaded from this file.
     */
    private ReferenceCacheData load(final File cacheFile) {

        if (!cacheFile.exists() && !cacheFile.canRead()) {
            return null;
        }

        logger.debug("Load reference cache file: " + cacheFile.getAbsolutePath());

        ReferenceCacheData cacheDataLoad = null;
        ObjectInputStream oos = null;
        try {
            oos = new ObjectInputStream(new FileInputStream(cacheFile));
            cacheDataLoad = (ReferenceCacheData) oos.readObject();
        } catch (final FileNotFoundException e) {
            logger.error("Cache file can not be found", e);
        } catch (final IOException e) {
            logger.error("Cache file could not be accessed correctly", e);
        } catch (final ClassNotFoundException e) {
            logger.error("An object persisted in the cache file could not be loaded", e);
        } finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (final IOException e) {
                    logger.warn("Failed to close cache file output stream", e);
                }
            }
        }

        return cacheDataLoad;
    }

    /**
     * Get the target object for a specified URI.
     *
     * @param resource
     *            The resource to use for EObject resolution.
     * @param targetURI
     *            The URI to resolve the target object with.
     * @return The resolved EObject.
     */
    private EObject getTarget(final Resource resource, final String targetURI) {
        final URI createURI = URI.createURI(targetURI);
        final ResourceSet resourceSet = resource.getResourceSet();
        final EObject target = resourceSet.getEObject(createURI, true);
        return target;
    }

    /**
     * Get the internal counter how many references have not been resolved from cache, while their
     * resources have. This is an indicator for failed proxy resolutions or that the cache was not
     * involved in the resolution.
     *
     * @return The counter value.
     */
    public int getNotResolvedFromCacheCounterReference() {
        return this.notResolvedFromCacheCounterReference;
    }

    /**
     * Resolve a specific proxy referenced in resource.
     *
     * @param resource
     *            The resource containing the proxy.
     * @param id
     *            The id (local fragment uri) of the proxy.
     * @return The object resolved for the id.
     */
    public EObject getEObject(final Resource resource, final String id) {

        final String resourceUri = resource.getURI().toString();
        final LinkedHashMap<String, String> targetUriMap = this.cacheData.getResourceToTargetURIListMap().get(
                resourceUri);
        if (targetUriMap == null) {
            return null;
        }
        final String targetURI = targetUriMap.get(id);
        if (targetURI == null) {
            return null;
        }

        return this.getTarget(resource, targetURI);
    }

    /**
     * Check is already present in the cached.
     *
     * @param resource
     *            The resource to check for.
     * @return True/ False if it is cached or not.
     */
    public boolean isCached(final Resource resource) {
        return this.cacheData.getResourceToTargetURIListMap().containsKey(resource.getURI().toString());
    }

    /**
     * Register a resolved {@link EObject} to the cache which has been resolved by EMF without
     * involving the cache. E.g. by indirectly resolving it without participating the cache.
     *
     * @param resource
     *            The resource containing the proxy / reference
     * @param fragmentURI
     *            The fragmentURI if the proxy
     * @param resolvedElement
     *            The resolved element
     */
    public void registerEObject(final Resource resource, final String fragmentURI, final EObject resolvedElement) {

        final String resourceUri = resource.getURI().toString();

        if (resolvedElement == null) {
            logger.warn(String.format("Tried to register a null element in the cache %s#%s", resourceUri, fragmentURI));
            return;
        }

        if (resolvedElement.eIsProxy()) {
            if (this.isNotLibraryProxy(resolvedElement)) {
                logger.warn(String.format("Tried to register a non-library proxy in the cache: %s#%s", resourceUri,
                        fragmentURI));
                return;
            }
        }

        String targetURI;
        final Resource targetResource = resolvedElement.eResource();
        if (targetResource != null) {
            targetURI = targetResource.getURI().toString() + "#" + targetResource.getURIFragment(resolvedElement);
        } else if (resolvedElement.eIsProxy()) {
            targetURI = ((InternalEObject) resolvedElement).eProxyURI().toString();
        } else {
            logger.error("Unable to identify target URI of resolved element: " + resolvedElement);
            return;
        }

        LinkedHashMap<String, String> targetURIMap = this.cacheData.getResourceToTargetURIListMap().get(resourceUri);
        if (targetURIMap == null) {
            targetURIMap = new LinkedHashMap<String, String>();
            this.cacheData.getResourceToTargetURIListMap().put(resourceUri, targetURIMap);
        }

        targetURIMap.put(fragmentURI, targetURI);
        this.notResolvedFromCacheCounterReference++;
    }

    private boolean isNotLibraryProxy(final EObject resolvedElement) {
        return !"pathmap".equals(((InternalEObject) resolvedElement).eProxyURI().scheme());
    }
}
