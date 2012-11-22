package org.somox.metrics.hierarchy;

import org.somox.metrics.MetricID;

import de.fzi.gast.core.Package;
import de.fzi.gast.types.GASTClass;

/**
 * PackageMapping metric: Checks how well component candidates are arranged in the same or at least comparable packages.
 * @author Klaus Krogmann
 *
 */
public class PackageMapping extends AbstractHierarchyMapping<Package> {
	public static final MetricID METRIC_ID = new MetricID("org.somox.metrics.PackageMapping");

	/**
	 * {@inheritDoc}
	 */
	public MetricID getMID () {
		return METRIC_ID;
	}

	@Override
	protected Package getPath(GASTClass clazz) {
		return clazz.getSurroundingPackage();
	}

	@Override
	protected Package getPath(Package element) {
		return element.getSurroundingPackage();
	}
	
}
