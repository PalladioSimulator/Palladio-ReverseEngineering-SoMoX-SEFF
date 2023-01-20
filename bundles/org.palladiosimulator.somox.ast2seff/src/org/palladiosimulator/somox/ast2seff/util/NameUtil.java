package org.palladiosimulator.somox.ast2seff.util;

import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.jdt.core.dom.BooleanLiteral;
import org.eclipse.jdt.core.dom.CatchClause;
import org.eclipse.jdt.core.dom.CharacterLiteral;
import org.eclipse.jdt.core.dom.EnhancedForStatement;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.ExpressionStatement;
import org.eclipse.jdt.core.dom.ForStatement;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.InfixExpression;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.PostfixExpression;
import org.eclipse.jdt.core.dom.PrimitiveType;
import org.eclipse.jdt.core.dom.ReturnStatement;
import org.eclipse.jdt.core.dom.SimpleType;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;
import org.eclipse.jdt.core.dom.StringLiteral;
import org.eclipse.jdt.core.dom.SwitchCase;
import org.eclipse.jdt.core.dom.SwitchStatement;
import org.eclipse.jdt.core.dom.TryStatement;
import org.eclipse.jdt.core.dom.Type;
import org.eclipse.jdt.core.dom.VariableDeclarationExpression;
import org.eclipse.jdt.core.dom.WhileStatement;
import org.palladiosimulator.somox.ast2seff.visitors.Ast2SeffVisitor;

public class NameUtil {
    private static final Logger LOGGER = Logger.getLogger(Ast2SeffVisitor.class);

    public static final String START_ACTION_NAME = "Start Action";
    public static final String STOP_ACTION_NAME = "Stop Action";
    public static final String RELEASE_ACTION_NAME = "Release Action";
    public static final String ACQUIRE_ACTION_NAME = "Acquire Action";
    public static final String LOOP_ACTION_NAME = "Loop Action";
    public static final String BRANCH_ACTION_NAME = "Branch Action";

    public static String getClassName(MethodInvocation methodInvocation) {
        return NameUtil.getClassName(methodInvocation.getExpression());
    }

    public static String getClassName(Expression calledClass) {
        String result = "unknown";
        if (calledClass.resolveTypeBinding() != null) {
            ITypeBinding bindingExpression = calledClass.resolveTypeBinding();
            if (bindingExpression != null && bindingExpression.getPackage() != null) {
                result = bindingExpression.getBinaryName();
            } else {
                LOGGER.warn("No Class Name found for: " + calledClass.toString());
            }
        }
        return result;
    }

    public static String getExpressionClassName(Expression variable) {
        if (variable instanceof BooleanLiteral) {
            return "BOOLEAN";
        } else if (variable instanceof CharacterLiteral) {
            return "CHAR";
        } else if (variable instanceof StringLiteral) {
            return "STRING";
        }

        return variable.toString();
    }

    /*
     * Dynamically sets the Name of the supplied action
     */
    public static String getEntityName(Expression expression) {
        return expression.toString();
    }

    public static String getEntityName(ExpressionStatement expressionStatement) {
        return expressionStatement.getExpression().toString();
    }

    public static String getEntityName(MethodInvocation methodInvocation) {
        if (methodInvocation.arguments().isEmpty()) {
            return methodInvocation.getName().toString();
        } else {
            return methodInvocation.getName().toString() + "(" + methodInvocation.arguments().toString() + ")";
        }
    }

    public static String getEntityName(ReturnStatement returnStatement) {
        return returnStatement.getExpression().toString();
    }

    public static String getEntityName(final ForStatement forStatement) {
        Expression initializers = (Expression) forStatement.initializers().get(0);
        Expression updaters = (Expression) forStatement.updaters().get(0);
        Expression expression = forStatement.getExpression();

        final StringBuilder positionString = new StringBuilder("@position: ");
        if (initializers instanceof VariableDeclarationExpression && expression instanceof InfixExpression
                && updaters instanceof PostfixExpression) {
            positionString.append(" from ").append(initializers).append(" to ").append(expression).append(" with ")
                    .append(updaters);
        } else {
            positionString.append("No position information available");
        }
        return positionString.toString();
    }

    public static String getEntityName(final EnhancedForStatement forStatement) {
        SingleVariableDeclaration variableDeclaration = forStatement.getParameter();
        Expression expression = forStatement.getExpression();

        final StringBuilder positionString = new StringBuilder("@position: ");
        positionString.append("for (").append(variableDeclaration).append(" : ").append(expression).append(")");
        return positionString.toString();
    }

    public static String getEntityName(final WhileStatement whileStatement) {
        Expression expression = whileStatement.getExpression();

        final StringBuilder positionString = new StringBuilder("@position: ");
        positionString.append("while (").append(expression).append(")");
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

    public static String getIfStatementConditionString(IfStatement ifStatement) {
        Expression expression = ifStatement.getExpression();

        final StringBuilder conditionString = new StringBuilder("@position: ");
        conditionString.append("Condition: if(").append(expression).append(")");
        return conditionString.toString();
    }

    public static String getElseStatementConditionString() {
        final StringBuilder positionString = new StringBuilder("@position: ");
        positionString.append("Condition: ").append("else").append("");
        return positionString.toString();
    }

    public static String getTryStatementConditionString() {
        final StringBuilder positionString = new StringBuilder("@position: ");
        positionString.append("Try Block");
        return positionString.toString();
    }

    public static String getCatchClauseConditionString(CatchClause catchClause) {

        String expression = "Exception";
        Type type = catchClause.getException().getType();

        if (type.isSimpleType()) {
            expression = ((SimpleType) type).getName().toString();
        } else if (type.isPrimitiveType()) {
            expression = ((PrimitiveType) type).toString();
        }

        final StringBuilder conditionString = new StringBuilder("@position: ");
        conditionString.append("Condition: catch ").append(expression);
        return conditionString.toString();
    }

    public static String getSwitchCaseConditionString(SwitchCase switchCase) {
        List<Expression> expressionList = switchCase.expressions();
        String expressionString = "";

        for (Expression expression : expressionList) {
            expressionString += expression.toString();
        }

        // TODO: Finish function
        final StringBuilder conditionString = new StringBuilder("@position: ");
        conditionString.append("Condition: if(").append(expressionString).append(")");
        return conditionString.toString();
    }
}
