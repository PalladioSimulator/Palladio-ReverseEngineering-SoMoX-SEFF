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
	public static String getEntityName(Expression expression) {
		return expression.toString();
	}
	
	public static String getEntityName(MethodInvocation methodInvocation) {
		if (methodInvocation.arguments().isEmpty()) {
			return methodInvocation.getName().toString();
		} else {
			return methodInvocation.getName().toString() + "(" + methodInvocation.arguments().toString() + ")";
		}
	}
	
	public static String getConditiontring(IfStatement ifStatement) {
		Expression expression = ifStatement.getExpression();
		
		final StringBuilder positionString = new StringBuilder(" @position: ");
		positionString.append("Cond: if(").append(expression).append(")");
		return positionString.toString();
	}
	
	public static String getEntityName(Statement elseStatement) {		
		final StringBuilder positionString = new StringBuilder(" @position: ");
		positionString.append("Cond: ").append("else").append("");
		return positionString.toString();
	}
	
//	public static String getEntityName(TryStatement tryStatement) {
		//Expression expression = tryStatement.catchClauses();
		
		//final StringBuilder positionString = new StringBuilder(" @position: ");
		//positionString.append("Cond: if(").append(expression).append(")");
		//branchTransitionElse.setEntityName(positionString.toString());
//	}
	
//	public static String getEntityName(List<Statement> block) {
		//cant access block name. mb need to rewrite Blocklist
		
		//Expression expression = tryStatement.catchClauses();
		
		//final StringBuilder positionString = new StringBuilder(" @position: ");
		//positionString.append("Cond: if(").append(expression).append(")");
		//branchTransitionElse.setEntityName(positionString.toString());
//	}
	
	public static String getEntityName(final ForStatement forStatement) {
		Expression initializers = (Expression) forStatement.initializers().get(0);
		Expression updaters = (Expression) forStatement.updaters().get(0);
		Expression expression = forStatement.getExpression(); 
		
		final StringBuilder positionString = new StringBuilder(" @position: ");
		if (initializers instanceof VariableDeclarationExpression && expression instanceof InfixExpression && updaters instanceof PostfixExpression) {
			positionString.append(" from ").append(initializers).append(" to ").append(expression).append(" with ").append(updaters);
		} else {
			positionString.append("no position information available");
		}
		return positionString.toString();
	}
	
	public static String getEntityName(final EnhancedForStatement forStatement) {
		SingleVariableDeclaration variableDeclaration = forStatement.getParameter();
		Expression expression = forStatement.getExpression();
		
		final StringBuilder positionString = new StringBuilder(" @position: ");
		positionString.append("for (").append(variableDeclaration).append(" : ").append(expression).append(")");
		return positionString.toString();
	}
	
	public static String getEntityName(final WhileStatement whileStatement) {
		Expression expression = whileStatement.getExpression();
		
		final StringBuilder positionString = new StringBuilder(" @position: ");
		positionString.append("expression name \"").append(expression).append("\"");
		return positionString.toString();
	}
	
	public static String getEntityName(final SwitchStatement switchStatement) {
		return "Switch Branch";			
	}
	public static String getEntityName(final IfStatement ifStatement) {
		return "If Branch";
	}
	public static String getEntityName(final TryStatement tryStatement) {
		return "Try Catch Branch";
	}
	public static String getEntityName(String className) {
		return className;
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
