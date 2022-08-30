package org.somox.gast2seff.visitors;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.EList;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.Assignment;
import org.eclipse.jdt.core.dom.CatchClause;
import org.eclipse.jdt.core.dom.EnhancedForStatement;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.ExpressionStatement;
import org.eclipse.jdt.core.dom.ForStatement;
import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.InfixExpression;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.ReturnStatement;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.Statement;
import org.eclipse.jdt.core.dom.SwitchStatement;
import org.eclipse.jdt.core.dom.SynchronizedStatement;
import org.eclipse.jdt.core.dom.TryStatement;
import org.eclipse.jdt.core.dom.Type;
import org.eclipse.jdt.core.dom.VariableDeclarationStatement;
import org.eclipse.jdt.core.dom.WhileStatement;
import org.palladiosimulator.generator.fluent.repository.api.seff.ActionSeff;
import org.palladiosimulator.generator.fluent.repository.factory.FluentRepositoryFactory;
import org.palladiosimulator.generator.fluent.repository.structure.components.BasicComponentCreator;
import org.palladiosimulator.generator.fluent.repository.structure.components.seff.BranchActionCreator;
import org.palladiosimulator.generator.fluent.repository.structure.components.seff.ExternalCallActionCreator;
import org.palladiosimulator.generator.fluent.repository.structure.components.seff.LoopActionCreator;
import org.palladiosimulator.generator.fluent.repository.structure.components.seff.SeffCreator;
import org.palladiosimulator.generator.fluent.repository.structure.components.seff.SetVariableActionCreator;
import org.palladiosimulator.generator.fluent.shared.components.VariableUsageCreator;
import org.palladiosimulator.pcm.parameter.VariableCharacterisationType;
import org.palladiosimulator.pcm.reliability.ResourceTimeoutFailureType;
import org.palladiosimulator.pcm.repository.CompositeDataType;
import org.palladiosimulator.pcm.repository.DataType;
import org.palladiosimulator.pcm.repository.OperationSignature;
import org.palladiosimulator.pcm.repository.Parameter;
import org.palladiosimulator.pcm.repository.PrimitiveDataType;
import org.somox.kdmhelper.ComponentInformation;
import org.somox.kdmhelper.MethodBundlePair;
import org.somox.kdmhelper.MethodPalladioInformation;
import org.somox.kdmhelper.StaticNameMethods;

public class Ast2SeffVisitor extends ASTVisitor {

	private static final Logger logger = Logger.getLogger(Ast2SeffVisitor.class);
	
	private FluentRepositoryFactory create;	
	private Map<String, MethodPalladioInformation> methodPalladioInfoMap;
	private MethodPalladioInformation methodPalladioInfo;
	private MethodBundlePair methodBundlePair;
	private ActionSeff actionSeff;
	private BasicComponentCreator basicComponentCreator;
	private ComponentInformation componentInformation;
	private boolean isPassiveResourceSet = false;
	
	public Ast2SeffVisitor(MethodPalladioInformation methodPalladioInformation, ActionSeff actionSeff, Map<String, MethodPalladioInformation> methodPalladionInfoMap, ComponentInformation componentInformation, FluentRepositoryFactory create) {
		this.actionSeff = actionSeff;
		this.methodPalladioInfoMap = methodPalladionInfoMap;
		this.methodBundlePair = methodPalladioInformation.getMethodBundlePair();
		this.methodPalladioInfo = methodPalladioInformation;
		this.componentInformation = componentInformation;
		this.basicComponentCreator = componentInformation.getBasicComponentCreator();
		this.create = create;
	}
	
	public static ActionSeff perform(MethodPalladioInformation methodPalladioInformation, ActionSeff actionSeff, Map<String, MethodPalladioInformation> methodPalladionInfoMap, ComponentInformation componentInformation, FluentRepositoryFactory create) {
		Ast2SeffVisitor newFunctionCallClassificationVisitor = new Ast2SeffVisitor(methodPalladioInformation, actionSeff, methodPalladionInfoMap, componentInformation, create);
		methodPalladioInformation.getMethodBundlePair().getAstNode().accept(newFunctionCallClassificationVisitor);
		return actionSeff;
	}
	
	private ActionSeff perform(ASTNode node, ActionSeff actionSeff) {
		Ast2SeffVisitor newFunctionCallClassificationVisitor = new Ast2SeffVisitor(methodPalladioInfo, actionSeff, methodPalladioInfoMap, componentInformation, create);
		node.accept(newFunctionCallClassificationVisitor);
		return actionSeff;
	}
	
	public boolean visit(final ExpressionStatement expressionStatement) {
		
		Expression expression = expressionStatement.getExpression();

		if(expression instanceof Assignment) {
			//TODO: further tests if this makes sense
			////Variable Assignment
			//Assignment transformedExpression = (Assignment) expression;
			//SetVariableActionCreator setVariableActionCreator = actionSeff.setVariableAction();
			//VariableUsageCreator inputVariable = this.generateInputVariableUsage(transformedExpression.getRightHandSide());
			//setVariableActionCreator.withLocalVariableUsage(inputVariable);
			//this.actionSeff = setVariableActionCreator.followedBy();
		} else if (expression instanceof MethodInvocation && this.isExternal((MethodInvocation) expression)) {
			//internal / external Action
			MethodInvocation methodInvocation = (MethodInvocation) expression;
			MethodPalladioInformation methodPalladioInformation = this.getMethodPalladioInformation(methodInvocation);
			
			if (!methodBundlePair.getBundleName().equals(methodPalladioInformation.getOperationInterfaceName())) {
				createExternalCallAction(methodInvocation, methodPalladioInformation);
			} else {
				generateClassMethodSeff(methodPalladioInformation);
			}
		} else {
			// TODO: check if we need to detect if expression statement is a class method 
			// 		 -> enter method body and generate seff for it
			createInternalAction(expressionStatement);
		}
		return false;
	}
	
	private void generateClassMethodSeff(MethodPalladioInformation methodPalladioInformation) {
		// TODO: Internal Call Action?
//		perform(methodPalladioInformation.getAstNode(), actionSeff);
	}
	
	private void createExternalCallAction(MethodInvocation methodInvocation, MethodPalladioInformation externalMethodInformation) {

		ExternalCallActionCreator externalCallActionCreator = actionSeff.externalCallAction();

		addRequiredInterfaceToComponent(externalMethodInformation.getOperationInterfaceName());
		externalCallActionCreator.withCalledService(create.fetchOfOperationSignature(externalMethodInformation.getOperationSignatureName()));
		externalCallActionCreator.withRequiredRole(create.fetchOfOperationRequiredRole(externalMethodInformation.getOperationInterfaceName()));
		
		OperationSignature calledFunctionSignature = create.fetchOfOperationSignature(externalMethodInformation.getOperationSignatureName());
		VariableUsageCreator variableUsage;
		if(!methodInvocation.arguments().isEmpty()) {
			if(calledFunctionSignature != null && (calledFunctionSignature.getParameters__OperationSignature().size() == methodInvocation.arguments().size())) {
				//try to get variables from interface
				EList<Parameter> calledFunctParameterList = calledFunctionSignature.getParameters__OperationSignature();
				for(int i=0; i < calledFunctParameterList.size(); i++) {
					Parameter para = calledFunctParameterList.get(i);
					Expression castedArgument = (Expression) methodInvocation.arguments().get(i);
					variableUsage = generateInputVariableUsage(castedArgument, para);
					externalCallActionCreator.withInputVariableUsage(variableUsage);
				}
			} else {
				//fallback if interface is not found or argumentsArrays have different sizes
				for(Object argument : methodInvocation.arguments()) {
					Expression castedArgument = (Expression) argument;
					variableUsage = generateInputVariableUsage(castedArgument);
					externalCallActionCreator.withInputVariableUsage(variableUsage);
				}
			}
		}
		if(calledFunctionSignature != null && calledFunctionSignature.getReturnType__OperationSignature() != null) {
			DataType returnType = calledFunctionSignature.getReturnType__OperationSignature();
			variableUsage = generateOutputVariableUsage(returnType);
			externalCallActionCreator.withReturnVariableUsage(variableUsage);
		}
		
		actionSeff = externalCallActionCreator.withName(StaticNameMethods.getEntityName(methodInvocation)).followedBy();
	}
	
	private void addRequiredInterfaceToComponent(String requiredInterfaceName) {
		String basicComponentName = methodBundlePair.getBundleName();
		Map<String, List<String>> componentRequiredListMap = componentInformation.getComponentRequiredListMap();
		if (componentRequiredListMap.containsKey(basicComponentName)) {
			List<String> requiredList = componentRequiredListMap.get(basicComponentName);
			if (!requiredList.contains(requiredInterfaceName)) {
				basicComponentCreator.requires(create.fetchOfOperationInterface(requiredInterfaceName), requiredInterfaceName);
				requiredList.add(requiredInterfaceName);
			}
		} else {
			basicComponentCreator.requires(create.fetchOfOperationInterface(requiredInterfaceName), requiredInterfaceName);
			List<String> requiredList = new ArrayList<>();
			requiredList.add(requiredInterfaceName);
			componentRequiredListMap.put(basicComponentName, requiredList);
		}
	}
	
	private void createInternalAction(final ExpressionStatement expressionStatement) {
		actionSeff = actionSeff.internalAction().withName(StaticNameMethods.getEntityName(expressionStatement)).followedBy();
	}
	
	public boolean visit(final IfStatement ifStatement) {
		BranchActionCreator branchActionCreator = actionSeff.branchAction();
		branchActionCreator = generateBranchAction(ifStatement, branchActionCreator);
		
		if (ifStatement.getElseStatement() != null) {
			branchActionCreator = handleElseStatement(ifStatement.getElseStatement(), branchActionCreator);	
		}
		
		actionSeff = branchActionCreator.withName(StaticNameMethods.getEntityName(ifStatement)).followedBy();
		return false;
	}
	
	private BranchActionCreator handleElseStatement(Statement statement, BranchActionCreator branchActionCreator) {
		
		ActionSeff innerActionSeff = create.newSeff().withSeffBehaviour().withStartAction().withName("Start Action").followedBy();
		
		if (statement instanceof IfStatement) {
			IfStatement elseIfStatement = (IfStatement) statement;
			innerActionSeff = this.perform(elseIfStatement.getThenStatement(), innerActionSeff);
		} else {
			innerActionSeff = this.perform(statement, innerActionSeff);
		}
		
		SeffCreator seffCreator = innerActionSeff.stopAction().withName("Stop Action").createBehaviourNow();
		branchActionCreator = branchActionCreator.withGuardedBranchTransition("expression", seffCreator, "Guarded Branch Transition");

		if (statement instanceof IfStatement) {
			IfStatement elseIfStatement = (IfStatement) statement;
			branchActionCreator = handleElseStatement(elseIfStatement.getElseStatement(), branchActionCreator);
		}
		
		return branchActionCreator;
	}
	
	public boolean visit(final SynchronizedStatement synchronizedStatement) {
		Expression exp = synchronizedStatement.getExpression();
		String className = StaticNameMethods.getClassName(exp) + ".class";
		
		if (!componentInformation.getIsPassiveResourceSet()) {
			basicComponentCreator.withPassiveResource("1", (ResourceTimeoutFailureType) create.newResourceTimeoutFailureType("PassiveResourceTimeoutFailure").build(), "Passive Resource");
			componentInformation.setPassiveResourceSetTrue();
		}
		
		
//		PassiveResource passiveResource = RepositoryFactory.eINSTANCE.createPassiveResource();
//		passiveResource.setCapacity_PassiveResource(CoreFactory.eINSTANCE.createPCMRandomVariable());
//		StaticNameMethods.setEntityName(passiveResource, className);
//		passiveResource.getCapacity_PassiveResource().setSpecification("1");
//		if (this.basicComponent.getPassiveResource_BasicComponent().isEmpty())
//			this.basicComponent.getPassiveResource_BasicComponent().add(passiveResource);
//		else {
//			EList<PassiveResource> passiveResoceList = this.basicComponent.getPassiveResource_BasicComponent();
//			boolean found = false;
//			for (PassiveResource entry : passiveResoceList) {
//				if (found)
//					break;
//				if (entry.getEntityName() == className) {
//					found = true;
//					passiveResource = entry;
//				}
//			}
//			if(!found)
//				this.basicComponent.getPassiveResource_BasicComponent().add(passiveResource);
//		}

		actionSeff = actionSeff.acquireAction().withName("Aquire Action").withPassiveResource(create.fetchOfPassiveResource("Passive Resource")).followedBy();

		actionSeff = this.perform(synchronizedStatement.getBody(), actionSeff)
				.releaseAction().withName("Release Action").withPassiveResource(create.fetchOfPassiveResource("Passive Resource")).followedBy();

		return false;
	}
	
	public boolean visit(final TryStatement tryStatement) {
		BranchActionCreator branchActionCreator = actionSeff.branchAction();
		branchActionCreator = generateBranchAction(tryStatement, branchActionCreator);
		
		List<CatchClause> catchClauseList = tryStatement.catchClauses();
		for (CatchClause catchClause : catchClauseList) {
			ActionSeff innerActionSeff = create.newSeff().withSeffBehaviour().withStartAction().withName("Start Action").followedBy();
			innerActionSeff = this.perform(catchClause.getBody(), innerActionSeff);
			SeffCreator seffCreator = innerActionSeff.stopAction().withName("Stop Action").createBehaviourNow();
			branchActionCreator = branchActionCreator.withGuardedBranchTransition("expression", seffCreator, StaticNameMethods.getEntityName(tryStatement));
		}
		
		actionSeff = branchActionCreator.withName(StaticNameMethods.getEntityName(tryStatement)).followedBy();
		return false;
	}
	
	public boolean visit(final ForStatement forStatement) {
		LoopActionCreator loopActionCreator = actionSeff.loopAction();
		Expression forStatementExpression = forStatement.getExpression();
		if(forStatementExpression != null && forStatementExpression instanceof InfixExpression) {
			loopActionCreator.withIterationCount(((InfixExpression) forStatementExpression).getRightOperand().toString());
		}
		loopActionCreator = generateLoopAction(forStatement.getBody(), loopActionCreator);
		actionSeff = loopActionCreator.withName(StaticNameMethods.getEntityName(forStatement)).followedBy();
		return false;
	}
	
	public boolean visit(final EnhancedForStatement forStatement) {
		LoopActionCreator loopActionCreator = actionSeff.loopAction();
		Expression forStatementExpression = forStatement.getExpression();
		if(forStatementExpression != null && forStatementExpression instanceof SimpleName) {
			loopActionCreator.withIterationCount("1");
		}
		loopActionCreator = generateLoopAction(forStatement.getBody(), loopActionCreator);
		actionSeff = loopActionCreator.withName(StaticNameMethods.getEntityName(forStatement)).followedBy();
		return false;
	}
	
	public boolean visit(final WhileStatement whileStatement) {
		LoopActionCreator loopActionCreator = actionSeff.loopAction();
		Expression whileStatementExpression = whileStatement.getExpression();
		if(whileStatementExpression != null && whileStatementExpression instanceof SimpleName) {
			loopActionCreator.withIterationCount("1");
		}
		loopActionCreator = generateLoopAction(whileStatement.getBody(), loopActionCreator);
		actionSeff = loopActionCreator.withName(StaticNameMethods.getEntityName(whileStatement)).followedBy();
		return false;
	}
	
	public boolean visit(final SwitchStatement switchStatement) {
		BranchActionCreator branchActionCreator = actionSeff.branchAction();
		branchActionCreator.withName(StaticNameMethods.getEntityName(switchStatement));
		
		List<List<Statement>> blockList = SwitchStatementHelper.createBlockListFromSwitchStatement(switchStatement);
		for (List<Statement> block : blockList) {
			
			ActionSeff innerActionSeff = create.newSeff().withSeffBehaviour().withStartAction().withName("Start Action").followedBy();
			
			for (Statement statement : block) {
				innerActionSeff = this.perform(statement, innerActionSeff);
			}
			
			SeffCreator seffCreator = innerActionSeff.stopAction().withName("Stop Action").createBehaviourNow();
			branchActionCreator= branchActionCreator.withGuardedBranchTransition("expression", seffCreator);
		}
		
		actionSeff = branchActionCreator.withName(StaticNameMethods.getEntityName(switchStatement)).followedBy();
		return false;
	}

	/*
	 * Neu in Ast2Seff dazu gekommen, war nicht in JaMoPP vorhanden
	 * Verhalten aus "MediaStore3 -> AudioWatermarking" abgeschaut
	 */
	public boolean visit(final ReturnStatement returnStatement) {
		Expression returnExpression = returnStatement.getExpression();
		SetVariableActionCreator setVariableActionCreator = actionSeff.setVariableAction();
		VariableUsageCreator returnVariable = this.generateInputVariableUsage(returnExpression);
		setVariableActionCreator.withLocalVariableUsage(returnVariable);
		this.actionSeff = setVariableActionCreator.followedBy();
		return false;
	}
	
	// TODO: Further work
	public boolean visit(final VariableDeclarationStatement variableDeclarationStatement) {
		Type test = variableDeclarationStatement.getType();
		return super.visit(variableDeclarationStatement);
	}
	
	/*
	 * generates Input Variable Usages
	 * 
	 * Parameters:  
	 * 	Expression: Expression of variable that is passed in AST
	 *	Parameter: optional parameter, if matching interface to function was found for additional 
	 * 
	 * Neu in Ast2Seff dazu gekommen, war nicht in JaMoPP vorhanden
	 * Verhalten aus "MediaStore3 -> AudioWatermarking" abgeschaut +
	 * zusätzliche infos von: https://www.palladio-simulator.com/tools/tutorials/ (PCM Parameter (PDF) -> 18)
	 * 
	 *** The following types are available
	 * BYTESIZE: Memory footprint of a parameter
	 * VALUE: The actual value of a parameter for primitive types
	 * STRUCTURE: Structure of data, like sorted or unsorted
	 * NUMBER_OF_ELEMENTS: The number of elements in a collection
	 * TYPE: The actual type of a parameter (vs. the declared type)
	 */
	private VariableUsageCreator generateInputVariableUsage(Expression variable, Parameter para) {
		VariableUsageCreator variableUsage = create.newVariableUsage();
		
		//randomPCMVariable.setSpecification(namespaceReference.getReferenceName().toString() + "." + variableReference.getReferenceName().toString() + "." + booleanVariable.getType().toString());
		String randomPCMName;
		DataType paraDataType = para.getDataType__Parameter();
		if(paraDataType instanceof PrimitiveDataType) {
			variableUsage.withNamespaceReference(((PrimitiveDataType) paraDataType).getType().toString(), variable.toString());
			randomPCMName = ((PrimitiveDataType) paraDataType).getType().toString() + "." + variable.toString();
			variableUsage.withVariableCharacterisation(randomPCMName, VariableCharacterisationType.VALUE);
		}
		else if(paraDataType instanceof CompositeDataType) {
			variableUsage.withNamespaceReference(((CompositeDataType) paraDataType).getEntityName(), variable.toString());
			randomPCMName = ((CompositeDataType) paraDataType).getEntityName() + "." + variable.toString();
			variableUsage.withVariableCharacterisation(randomPCMName, VariableCharacterisationType.BYTESIZE);
		}
		else {
			variableUsage.withNamespaceReference(para.getParameterName(), variable.toString());
			randomPCMName = para.getParameterName() + "." + variable.toString();
			variableUsage.withVariableCharacterisation(randomPCMName, VariableCharacterisationType.VALUE);
		}
		return variableUsage;
	}
	private VariableUsageCreator generateInputVariableUsage(Expression variable) {
		VariableUsageCreator variableUsage = create.newVariableUsage();
		variableUsage.withNamespaceReference("PrimitiveType", StaticNameMethods.getExpressionClassName(variable));
		String randomPCMName = "PrimitiveType" + "." + StaticNameMethods.getExpressionClassName(variable);
		variableUsage.withVariableCharacterisation(randomPCMName, VariableCharacterisationType.VALUE);
		return variableUsage;
	}
	/*
	 * Same as generateVariables above, but for Output Variables 
	 */
	private VariableUsageCreator generateOutputVariableUsage(DataType returnType) {
		//From ParameterFactory Docs: Note that it was an explicit design decision to refer to variable names instead of the actual variables (i.e., by refering to Parameter class).
		VariableUsageCreator variableUsage = create.newVariableUsage();
		String randomPCMName;

		if(returnType instanceof PrimitiveDataType) {
			variableUsage.withNamespaceReference(((PrimitiveDataType) returnType).getType().toString(), "tempVariable");
			randomPCMName = ((PrimitiveDataType) returnType).getType().toString() + "." + "tempVariable";
			variableUsage.withVariableCharacterisation(randomPCMName, VariableCharacterisationType.VALUE);
		}
		else if(returnType instanceof CompositeDataType) {
			variableUsage.withNamespaceReference(((CompositeDataType) returnType).getEntityName(), "tempVariable");
			randomPCMName = ((CompositeDataType) returnType).getEntityName() + "." + "tempVariable";
			variableUsage.withVariableCharacterisation(randomPCMName, VariableCharacterisationType.BYTESIZE);
		}
		else {
			variableUsage.withNamespaceReference(null, "tempVariable");
			randomPCMName = "tempVariable";
			variableUsage.withVariableCharacterisation(randomPCMName, VariableCharacterisationType.VALUE);
		}
		return variableUsage;
	}

	private BranchActionCreator generateBranchAction(ASTNode node, BranchActionCreator branchActionCreator) {

		if(node instanceof IfStatement) {
			IfStatement ifStatement = (IfStatement) node;
			node = ifStatement.getThenStatement();
		}
		else if (node instanceof TryStatement) {
			TryStatement tryStatement = (TryStatement) node;
			node = tryStatement.getBody();
		}
		
		ActionSeff innerActionSeff = create.newSeff().withSeffBehaviour().withStartAction().withName("Start Action").followedBy();
		innerActionSeff = this.perform(node, innerActionSeff);
		SeffCreator seffCreator = innerActionSeff.stopAction().withName("Stop Action").createBehaviourNow();
		
		branchActionCreator.withGuardedBranchTransition("expression", seffCreator, "Guarded Branch Transition");

		return branchActionCreator;
	}
	
	private LoopActionCreator generateLoopAction(ASTNode node, LoopActionCreator loopActionCreator) {
		ActionSeff innerActionSeff = create.newSeff().withSeffBehaviour().withStartAction().withName("Start Action").followedBy();
		innerActionSeff = this.perform(node, innerActionSeff);
		SeffCreator seffCreator = innerActionSeff.stopAction().withName("Stop Action").createBehaviourNow();
		loopActionCreator = loopActionCreator.withLoopBody(seffCreator).withName("Loop Action");
		return loopActionCreator;
	}
	
	private boolean isExternal(MethodInvocation methodInvocation) {
		String methodName = methodInvocation.getName().toString();
		String className = StaticNameMethods.getClassName(methodInvocation);
		return this.methodPalladioInfoMap.containsKey(className + "." + methodName);
	}
	
	private MethodPalladioInformation getMethodPalladioInformation(MethodInvocation methodInvocation) {
		String methodName = methodInvocation.getName().toString();
		String className = StaticNameMethods.getClassName(methodInvocation);
		if (this.methodPalladioInfoMap.containsKey(className + "." + methodName)) {
			return this.methodPalladioInfoMap.get(className + "." + methodName);
		} else {
			// TODO: handle the return of null
			return null;
		}
	}
}
