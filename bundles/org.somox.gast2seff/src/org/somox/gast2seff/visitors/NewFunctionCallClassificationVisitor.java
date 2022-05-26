package org.somox.gast2seff.visitors;

import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.ExpressionStatement;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.Statement;
import org.palladiosimulator.pcm.seff.InternalAction;
import org.palladiosimulator.pcm.seff.ResourceDemandingSEFF;
import org.palladiosimulator.pcm.seff.SeffFactory;

public class NewFunctionCallClassificationVisitor extends ASTVisitor {

	private static final Logger logger = Logger.getLogger(NewFunctionCallClassificationVisitor.class);
		
	private IFunctionClassificationStrategy functionClassificationStrategy = null;
	private ResourceDemandingSEFF resourceDemandingSEFF; 
	
	public NewFunctionCallClassificationVisitor(ResourceDemandingSEFF resourceDemandingSEFF) {
		this.resourceDemandingSEFF = resourceDemandingSEFF;
	}
	
	public static void perform(ASTNode node, ResourceDemandingSEFF seff) {
		NewFunctionCallClassificationVisitor newFunctionCallClassificationVisitor = new NewFunctionCallClassificationVisitor(seff);
		node.accept(newFunctionCallClassificationVisitor);
	}
	
//	public boolean visit(final MethodDeclaration methodDeclaration) {
//		
//		final Block body = methodDeclaration.getBody();
//		List<Statement> bodyStatement = body.statements();
//		for (Statement statement : bodyStatement) {
//			if (statement instanceof ExpressionStatement) {
//				InternalAction internalAction = SeffFactory.eINSTANCE.createInternalAction();
//				this.resourceDemandingSEFF.getSteps_Behaviour().add(internalAction);
//			}
//		}
//		
//		return super.visit(methodDeclaration);
//	}
	
	public boolean visit(final ExpressionStatement expressionStatement) {
		InternalAction internalAction = SeffFactory.eINSTANCE.createInternalAction();
		this.resourceDemandingSEFF.getSteps_Behaviour().add(internalAction);
		return super.visit(expressionStatement);
	}
	
	
	
	
	
}
