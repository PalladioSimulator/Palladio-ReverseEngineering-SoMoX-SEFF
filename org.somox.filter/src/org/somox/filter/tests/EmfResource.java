package org.somox.filter.tests;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.emf.ecore.resource.Resource;

import java.nio.file.Path;

/**
 * Utility class containing convenience methods to interact with EMF resources.
 *
 * @author Joshua Gleitze
 */
public final class EmfResource {

	/**
	 * This utility class is not meant to be instantiated.
	 */
	private EmfResource() {
	}

	/**
	 * Gets the {@linkplain Path path} to the file on the file system the provided
	 * {@code eResource} represents.
	 *
	 * @param eResource An EMF resource.
	 * @return The path to the file system file represented by {@code eResource}.
	 */
	public static Path getPath(final Resource eResource) {
		return ResourcesPlugin.getWorkspace()
			.getRoot()
			.getFile(new org.eclipse.core.runtime.Path(eResource.getURI().toPlatformString(true)))
			.getRawLocation()
			.toFile()
			.toPath();
	}
}
