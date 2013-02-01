/**
 * 
 */
package eu.qimpress.reverseengineering.gast2seff.visitors;

import org.apache.log4j.Logger;

import de.fzi.gast.accesses.FunctionAccess;
import de.fzi.gast.types.GASTClass;
import eu.qimpress.samm.staticstructure.PrimitiveComponent;

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
	protected boolean isExternalCall(FunctionAccess functionAccess) {
				
		ComponentImplementingClassesLink compLink = queryComponentLink(this.primitiveComponent);
		for(InterfaceSourceCodeLink ifLink : compLink.getRequiredInterfaces()) {
			if(KDMHelper.getMethods(ifLink.getGastClass()).contains(functionAccess.getTargetFunction())) {
				logger.debug("Classified call as external call: "+functionAccess.getTargetFunction().getSimpleName() + 
						" for component " + primitiveComponent.getName());				
				return true;
			}
		}	
		
		logger.trace("no external call: " + functionAccess.getTargetFunction().getSimpleName());
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
	protected boolean isLibraryCall(FunctionAccess functionAccess) {
		GASTClass targetClass = functionAccess.getAccessedClass();
		if (targetClass == null) {
			logger.warn("Failed to classifiy library call because called GASTClass was unavailable.");
			return true;
		}
		logger.debug("Classified call as library call: "+functionAccess.getTargetFunction().getSimpleName() + 
				" for component " + primitiveComponent.getName());	
		return (targetClass.getPosition() == null || targetClass.getPosition().getSourceFile() == null);
	}

}
