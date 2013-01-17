package org.somox.metrics.hierarchy;

import org.eclipse.gmt.modisco.java.Type;
import org.somox.kdmhelper.KDMHelper;
import org.somox.metrics.MetricID;

//import de.fzi.gast.core.Package;
//import de.fzi.gast.types.GASTClass;

/**
 * PackageMapping metric: Checks how well component candidates are arranged in the same or at least comparable packages.
 * @author Klaus Krogmann
 *
 */
public class PackageMapping extends AbstractHierarchyMapping<org.eclipse.gmt.modisco.java.Package> {
	public static final MetricID METRIC_ID = new MetricID("org.somox.metrics.PackageMapping");

	/**
	 * {@inheritDoc}
	 */
	public MetricID getMID () {
		return METRIC_ID;
	}

	@Override
	protected org.eclipse.gmt.modisco.java.Package getPath(Type clazz) {

		return KDMHelper.getSurroundingPackage(clazz);
	}

	@Override
	protected org.eclipse.gmt.modisco.java.Package getPath(org.eclipse.gmt.modisco.java.Package element) {
		return element.getPackage();
	}
	
}
