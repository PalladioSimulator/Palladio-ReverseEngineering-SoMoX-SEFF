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
package org.splevo.jamopp.extraction.resource;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.emftext.language.java.JavaClasspath;
import org.emftext.language.java.resource.JavaSourceOrClassFileResource;
import org.emftext.language.java.resource.JavaSourceOrClassFileResourceFactoryImpl;
import org.splevo.jamopp.extraction.cache.ReferenceCache;

/**
 * Factory for creating cache enabled JaMoPP java and class file resource.
 */
public class JavaSourceOrClassFileResourceCachingFactoryImpl extends JavaSourceOrClassFileResourceFactoryImpl {

    private static final String FILE_URI_SCHEME = "file";

    /** The reference cache to use. */
    private ReferenceCache referenceCache = null;

    /**
     * Constructor to set the base directory for internal reference cache.
     *
     * @param cacheDirectories
     *            The absolute paths of directories containing cache files.
     * @param javaClasspath
     *            The class path to enhance. Should be the same as associated with the resource set
     *            the resource factory belongs to.
     */
    public JavaSourceOrClassFileResourceCachingFactoryImpl(List<String> cacheDirectories, JavaClasspath javaClasspath) {
        this(cacheDirectories, javaClasspath, new ArrayList<String>());
    }

    /**
     * Constructor to set the base directory for internal reference cache.
     *
     * @param cacheDirectories
     *            The absolute paths of directories containing cache files.
     * @param javaClasspath
     *            The class path to enhance. Should be the same as associated with the resource set
     *            the resource factory belongs to.
     * @param jarPaths
     *            A list of paths to jar files to be registered in the {@link JavaClasspath} and
     *            stored in the cache.
     */
    public JavaSourceOrClassFileResourceCachingFactoryImpl(List<String> cacheDirectories, JavaClasspath javaClasspath,
            List<String> jarPaths) {
        super();
        referenceCache = new ReferenceCache(cacheDirectories, javaClasspath, jarPaths);
    }

    /**
     * Create a cache enabled resource for file scheme URIs.<br>
     *
     * <p>
     * Depending on how the URI is created, one must call
     * <code>new File("myRelativePath").getCanonicalPath()</code> before to ensure the URI starts
     * with file://
     * </p>
     *
     * Otherwise a regular JaMoPP resource is created.
     *
     * {@inheritDoc}
     */
    @Override
    public Resource createResource(URI uri) {
        if (FILE_URI_SCHEME.equals(uri.scheme())) {
            return new JavaSourceOrClassFileCachingResource(uri, referenceCache);
        } else {
            return new JavaSourceOrClassFileResource(uri);
        }
    }

    /**
     * Access the internal cache.
     *
     * @return The internally used cache.
     */
    public ReferenceCache getReferenceCache() {
        return referenceCache;
    }
}
