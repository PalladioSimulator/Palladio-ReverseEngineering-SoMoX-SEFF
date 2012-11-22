package org.somox.metrics.hierarchy;

import org.somox.metrics.MetricID;

import de.fzi.gast.core.Directory;
import de.fzi.gast.types.GASTClass;

/**
 * DirectoryMapping metric: Checks how well are component candidates are arranged in the same or at least comparable directory.
 * @author Klaus Krogmann
 */
public class DirectoryMapping extends AbstractHierarchyMapping<Directory> {
	public static final MetricID METRIC_ID = new MetricID("org.somox.metrics.DirectoryMapping");

	/**
	 * {@inheritDoc}
	 */
	public MetricID getMID () {
		return METRIC_ID;
	}

	@Override
	protected Directory getPath(GASTClass clazz) {
		if (clazz.getPosition() == null || clazz.getPosition().getSourceFile() == null)
			return null;
		return clazz.getPosition().getSourceFile().getDirectory();
	}

	@Override
	protected Directory getPath(Directory element) {
		return element.getParentDirectory();
	}
}

