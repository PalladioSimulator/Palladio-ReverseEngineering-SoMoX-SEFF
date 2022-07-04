package org.somox.kdmhelper;

import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.jdt.core.dom.BooleanLiteral;
import org.eclipse.jdt.core.dom.CharacterLiteral;
import org.eclipse.jdt.core.dom.EnhancedForStatement;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.ForStatement;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.InfixExpression;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.PostfixExpression;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;
import org.eclipse.jdt.core.dom.Statement;
import org.eclipse.jdt.core.dom.StringLiteral;
import org.eclipse.jdt.core.dom.SwitchStatement;
import org.eclipse.jdt.core.dom.TryStatement;
import org.eclipse.jdt.core.dom.VariableDeclarationExpression;
import org.eclipse.jdt.core.dom.WhileStatement;
import org.palladiosimulator.pcm.core.entity.Entity;
import org.palladiosimulator.pcm.seff.AbstractBranchTransition;
import org.palladiosimulator.pcm.seff.BranchAction;
import org.palladiosimulator.pcm.seff.ExternalCallAction;
import org.palladiosimulator.pcm.seff.InternalAction;
import org.palladiosimulator.pcm.seff.LoopAction;
import org.somox.gast2seff.visitors.Ast2SeffVisitor;

public class StaticNameMethods {
	private static final Logger logger = Logger.getLogger(Ast2SeffVisitor.class);

	public static String getClassName(MethodInvocation methodInvocation) {
		return StaticNameMethods.getClassName(methodInvocation.getExpression());
	}
	public static String getClassName(Expression calledClass) {
		String result = "unknown";
		ITypeBinding bindingExpression = calledClass.resolveTypeBinding();
		if(bindingExpression != null && bindingExpression.getPackage() != null)
			result = bindingExpression.getBinaryName();
		else
			logger.warn("No Class Name found for: " + calledClass.toString());
		
		return result;
	}
	public static String getExpressionClassName(Expression variable) {
		if(variable instanceof BooleanLiteral) {
			return "BOOLEAN";
		} else if (variable instanceof CharacterLiteral) {
			return "CHAR";
		} else if (variable instanceof StringLiteral) {
			return "STRING";
		}
		
		return "UnknownType";
	}
	
	/*
	 * Dynamically sets the Name of the supplied action
	 */
	public static void setEntityName(InternalAction internalAction, Expression expression) {
		internalAction.setEntityName(expression.toString());
	}
	public static void setEntityName(ExternalCallAction externalCall, MethodInvocation methodInvocation) {
		if (methodInvocation.arguments().isEmpty()) {
			externalCall.setEntityName(methodInvocation.getName().toString());
		} else {
			externalCall.setEntityName(methodInvocation.getName().toString() + "(" + methodInvocation.arguments().toString() + ")");
		}
	}
	public static void setEntityName(AbstractBranchTransition branchTransition, IfStatement ifStatement) {
		Expression expression = ifStatement.getExpression();
		
		final StringBuilder positionString = new StringBuilder(" @position: ");
		positionString.append("Cond: if(").append(expression).append(")");
		branchTransition.setEntityName(positionString.toString());
	}
	public static void setEntityName(AbstractBranchTransition branchTransitionElse, Statement elseStatement) {		
		final StringBuilder positionString = new StringBuilder(" @position: ");
		positionString.append("Cond: ").append("else").append("");
		branchTransitionElse.setEntityName(positionString.toString());
	}
	public static void setEntityName(AbstractBranchTransition branchTransition, TryStatement tryStatement) {
		//Expression expression = tryStatement.catchClauses();
		
		//final StringBuilder positionString = new StringBuilder(" @position: ");
		//positionString.append("Cond: if(").append(expression).append(")");
		//branchTransitionElse.setEntityName(positionString.toString());
	}
	public static void setEntityName(AbstractBranchTransition branchTransition, List<Statement> block) {
		//cant access block name. mb need to rewrite Blocklist
		
		//Expression expression = tryStatement.catchClauses();
		
		//final StringBuilder positionString = new StringBuilder(" @position: ");
		//positionString.append("Cond: if(").append(expression).append(")");
		//branchTransitionElse.setEntityName(positionString.toString());
	}
	public static void setEntityName(LoopAction loopAction, final ForStatement forStatement) {
		Expression initializers = (Expression) forStatement.initializers().get(0);
		Expression updaters = (Expression) forStatement.updaters().get(0);
		Expression expression = forStatement.getExpression(); 
		
		final StringBuilder positionString = new StringBuilder(" @position: ");
		if (initializers instanceof VariableDeclarationExpression && expression instanceof InfixExpression && updaters instanceof PostfixExpression) {
			positionString.append(" from ").append(initializers).append(" to ").append(expression).append(" with ").append(updaters);
		} else {
			positionString.append("no position information available");
		}
		loopAction.setEntityName(positionString.toString());
	}
	public static void setEntityName(LoopAction loopAction, final EnhancedForStatement forStatement) {
		SingleVariableDeclaration variableDeclaration = forStatement.getParameter();
		Expression expression = forStatement.getExpression();
		
		final StringBuilder positionString = new StringBuilder(" @position: ");
		positionString.append("for (").append(variableDeclaration).append(" : ").append(expression).append(")");
		loopAction.setEntityName(positionString.toString());
	}
	public static void setEntityName(LoopAction loopAction, final WhileStatement whileStatement) {
		Expression expression = whileStatement.getExpression();
		
		final StringBuilder positionString = new StringBuilder(" @position: ");
		positionString.append("expression name \"").append(expression).append("\"");
		loopAction.setEntityName(positionString.toString());
	}
	public static void setEntityName(BranchAction branchAction, final SwitchStatement switchStatement) {
		branchAction.setEntityName("Switch Branch");
	}
	public static void setEntityName(BranchAction branchAction, final IfStatement ifStatement) {
		branchAction.setEntityName("If Branch");
	}
	public static void setEntityName(BranchAction branchAction, final TryStatement tryStatement) {
		branchAction.setEntityName("Try Catch Branch");
	}
	public static void setEntityName(Entity defaultResource, String className) {
		defaultResource.setEntityName(className);
	}
	
	
//	public static String whileStatementToString(Expression expression) {
//		final StringBuilder positionString = new StringBuilder(" @position: ");
//		positionString.append("expression name \"").append(expression).append("\"");
//		return positionString.toString();
//	}
	
//	public static String elseStatementToString(Expression expression) {
//		final StringBuilder positionString = new StringBuilder(" @position: ");
//		positionString.append("Cond: !").append(expression).append("");
//		return positionString.toString();
//	}
}
