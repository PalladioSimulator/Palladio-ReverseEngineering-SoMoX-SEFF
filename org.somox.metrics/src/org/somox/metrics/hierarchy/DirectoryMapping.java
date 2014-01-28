package org.somox.metrics.hierarchy;

//package org.somox.metrics.hierarchy;

import org.emftext.language.java.types.Type;
import org.somox.kdmhelper.KDMHelper;
import org.somox.metrics.MetricID;

//import de.fzi.gast.core.Package;
//import de.fzi.gast.types.GASTClass;

/**
 * DirectoryMapping metric: Checks how well are component candidates are arranged in the same or at least comparable directory.
 * @author Klaus Krogmann
 */
public class DirectoryMapping extends AbstractHierarchyMapping<Package> { 
	public static final MetricID METRIC_ID = new MetricID("org.somox.metrics.DirectoryMapping");

	/**
	 * {@inheritDoc}
	 */
	public MetricID getMID () {
		return METRIC_ID;
	}

	@Override
	protected Package getPath(Type clazz) {
		if (KDMHelper.getJavaNodeSourceRegion(clazz) == null || KDMHelper.getSourceFile(KDMHelper.getJavaNodeSourceRegion(clazz)) == null)
			return null;
		return (Package) KDMHelper.getSourceFile(KDMHelper.getJavaNodeSourceRegion(clazz)).eContainer();
	}

	@Override
	protected Package getPath(Package element) {
		//return (Package) element.eContainer();//FIXEDMYBUG getOwner()
		//if(element.eContainer() instanceof Package){ //REALLYADDED
			return element.getContainer(); //REALLYADDED
		//} //REALLYADDED
		//return null; //REALLYADDED
	}
}

