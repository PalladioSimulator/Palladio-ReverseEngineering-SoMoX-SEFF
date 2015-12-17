/*******************************************************************************
 * Copyright (c) 2015
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Stephan Seifermann
 *******************************************************************************/
package org.splevo.jamopp.extraction;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

import com.google.common.base.Strings;

/**
 * ResourceSet for the SPLevo tooling. The resource set automatically converts URIs when
 * creating/loading resources. The exact behavior depends on the default settings in
 * FileResourceHandling.
 */
public class SPLevoResourceSet extends ResourceSetImpl {

    
    
    @Override
    public EObject getEObject(URI uri, boolean loadOnDemand) {
        return super.getEObject(convertURI(uri), loadOnDemand);
    }

    @Override
    public Resource getResource(URI uri, boolean loadOnDemand) {
        return super.getResource(convertURI(uri), loadOnDemand);
    }

    @Override
    public Resource createResource(URI uri, String contentType) {
        return super.createResource(convertURI(uri), contentType);
    }

    private URI convertURI(URI uri) {

        if (FileResourceHandling.usePlatformResource(this)) {

            if (uri.isPlatform() || "pathmap".equals(uri.scheme())) {
                return uri;
            } else {
                IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
                IPath p = new Path(uri.toFileString());
                IFile f = root.getFileForLocation(p);
                return createURI(URI.createPlatformResourceURI(f.getFullPath().toString(), true), uri.fragment());
            }

        } else {

            if (uri.isPlatform()) {
                IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
                IResource f = root.findMember(uri.toPlatformString(true));
                return URI.createFileURI(f.getLocationURI().toString());
            } else {
                return uri;
            }

        }

    }
        
    private static URI createURI(URI uri, String fragment) {
        if (Strings.isNullOrEmpty(fragment)) {
            return uri;
        }
        return URI.createURI(uri.toString() + "#" + fragment, true, URI.FRAGMENT_LAST_SEPARATOR);
    }

}
