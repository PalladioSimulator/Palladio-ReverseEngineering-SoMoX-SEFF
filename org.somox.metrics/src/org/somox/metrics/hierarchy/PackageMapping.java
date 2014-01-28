package org.somox.metrics.hierarchy;

//import Type;
import org.emftext.language.java.types.Type;
import org.somox.kdmhelper.KDMHelper;
import org.somox.metrics.MetricID;
import org.emftext.language.java.containers.Package;
//import de.fzi.gast.core.Package;
//import de.fzi.gast.types.GASTClass;

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
	protected Package getPath(Type clazz) {

		return KDMHelper.getSurroundingPackage(clazz);
	}

	@Override
	protected Package getPath(Package element) {
		return element;
	}
	
}
