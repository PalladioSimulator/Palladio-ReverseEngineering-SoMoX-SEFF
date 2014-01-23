package org.somox.metrics.hierarchy;

//package org.somox.metrics.hierarchy;

import org.emftext.language.java.types.Type;
import org.somox.kdmhelper.KDMHelper;
import org.somox.metrics.MetricID;

import de.fzi.gast.core.Directory;
//import de.fzi.gast.types.GASTClass;

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
	protected Directory getPath(Type clazz) {
		if (KDMHelper.getJavaNodeSourceRegion(clazz) == null || KDMHelper.getSourceFile(KDMHelper.getJavaNodeSourceRegion(clazz)) == null)
			return null;
		return (Directory) KDMHelper.getSourceFile(KDMHelper.getJavaNodeSourceRegion(clazz)).eContainer();
	}

	@Override
	protected Directory getPath(Directory element) {
		//return (Directory) element.eContainer();//FIXEDMYBUG getOwner()
		if(element.eContainer() instanceof Directory){ //REALLYADDED
			return (Directory) element.eContainer(); //REALLYADDED
		} //REALLYADDED
		return null; //REALLYADDED
	}
}

