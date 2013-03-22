/**
 * 
 */
package org.somox.gast2seff.visitors;

import org.apache.log4j.Logger;
import org.eclipse.gmt.modisco.java.AbstractMethodInvocation;
import org.eclipse.gmt.modisco.java.Type;

//import de.fzi.gast.accesses.FunctionAccess;//GAST2SEFFCHANGE
//import de.fzi.gast.types.GASTClass;//GAST2SEFFCHANGE
import eu.qimpress.samm.staticstructure.PrimitiveComponent;

import org.somox.kdmhelper.GetAccessedType;
import org.somox.kdmhelper.KDMHelper;
import org.somox.sourcecodedecorator.ComponentImplementingClassesLink;
import org.somox.sourcecodedecorator.InterfaceSourceCodeLink;
import org.somox.sourcecodedecorator.SourceCodeDecoratorRepository;

/**
 * Implementation of {@link IFunctionClassificationStrategy}. Uses basic heuristics based on the source code decorator and the GAST model
 * to decide on the type of function calls.
 * @author Steffen Becker, Klaus Krogmann
 *
 */
public class BasicFunctionClassificationStrategy 
extends AbstractFunctionClassificationStrategy
implements
		IFunctionClassificationStrategy {

	private static Logger logger = Logger.getLogger(BasicFunctionClassificationStrategy.class);

	private SourceCodeDecoratorRepository sourceCodeDecoratorRepository;
	private PrimitiveComponent primitiveComponent;
	
	/**
	 * @param sourceCodeDecoratorRepository The source code decorator which links the component
	 * for which to classify statements and the GAST. 
	 * @param primitiveComponent  The primitive component for which to decide whether the function access
	 * represents an external call.
	 */
	public BasicFunctionClassificationStrategy(SourceCodeDecoratorRepository sourceCodeDecoratorRepository, PrimitiveComponent primitiveComponent) {
		this.sourceCodeDecoratorRepository = sourceCodeDecoratorRepository;
		this.primitiveComponent = primitiveComponent;
	}
	
	@Override
	protected boolean isExternalCall(AbstractMethodInvocation functionAccess) {//GAST2SEFFCHANGE
				
		ComponentImplementingClassesLink compLink = queryComponentLink(this.primitiveComponent);
		for(InterfaceSourceCodeLink ifLink : compLink.getRequiredInterfaces()) {
			if(KDMHelper.getMethods(ifLink.getGastClass()).contains(functionAccess.getMethod())) {//GAST2SEFFCHANGE//GAST2SEFFCHANGE
				logger.debug("Classified call as external call: "+functionAccess.getMethod().getName() +//GAST2SEFFCHANGE//GAST2SEFFCHANGE 
						" for component " + primitiveComponent.getName());				
				return true;
			}
		}	
		
		logger.trace("no external call: " + functionAccess.getMethod().getName());//GAST2SEFFCHANGE//GAST2SEFFCHANGE
		return false;
	}

	private ComponentImplementingClassesLink queryComponentLink(PrimitiveComponent primitiveComponent) {
		for(ComponentImplementingClassesLink compLink : sourceCodeDecoratorRepository.getComponentImplementingClassesLink()) {
			if(compLink.getComponent().equals(primitiveComponent)) {
				return compLink;
			}
		}
		String msg = "Could not find a component implementing classes link in the source code " +
				"decorator for component " + primitiveComponent.getName();
		logger.error(msg);
		throw new RuntimeException(msg);
	}
	
	@Override
	protected boolean isLibraryCall(AbstractMethodInvocation functionAccess) {//GAST2SEFFCHANGE
		Type targetClass = GetAccessedType.getAccessedType(functionAccess);//GAST2SEFFCHANGE//GAST2SEFFCHANGE
		if (targetClass == null) {
			logger.warn("Failed to classifiy library call because called GASTClass was unavailable.");
			return true;
		}
		logger.debug("Classified call as library call: "+functionAccess.getMethod().getName() +//GAST2SEFFCHANGE//GAST2SEFFCHANGE 
				" for component " + primitiveComponent.getName());	
		return (KDMHelper.getJavaNodeSourceRegion(targetClass) == null || KDMHelper.getSourceFile(KDMHelper.getJavaNodeSourceRegion(targetClass)) == null);//GAST2SEFFCHANGE//GAST2SEFFCHANGE
	}

}
