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

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * The data container of the reference cache.
 */
public class ReferenceCacheData implements Serializable {

    /** The data version identifier. */
    private static final long serialVersionUID = 1L;

    /**
     * The list of reference URIs for reach resource. The resource itself is also identified by its
     * URI.
     */
    private final Map<String, LinkedHashMap<String, String>> resourceToTargetURIListMap = new LinkedHashMap<String, LinkedHashMap<String, String>>();

    /** The absolute paths to jar files to register in the JavaClasspath. */
    private final Set<String> jarFilePaths = new LinkedHashSet<String>();

    /**
     * Access to the resource reference map.
     *
     * @return The map at least empty but never null.
     */
    public Map<String, LinkedHashMap<String, String>> getResourceToTargetURIListMap() {
        return this.resourceToTargetURIListMap;
    }

    /**
     * Access the list of jar files to be registered.
     *
     * @return The path list, never null.
     */
    public Set<String> getJarFilePaths() {
        return this.jarFilePaths;
    }

    /**
     * Merge the data of the provided cache to this one. If the provided cache data contains data
     * with a key stored in this cache already, the new one will override the existing one.
     *
     * @param mergeInCacheData
     *            The data to merge into this cache.
     */
    public void merge(final ReferenceCacheData mergeInCacheData) {
        this.resourceToTargetURIListMap.putAll(mergeInCacheData.getResourceToTargetURIListMap());
        this.jarFilePaths.addAll(mergeInCacheData.getJarFilePaths());
    }
}
