package org.somox.gast2seff.visitors;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.EList;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CatchClause;
import org.eclipse.jdt.core.dom.EnhancedForStatement;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.ExpressionStatement;
import org.eclipse.jdt.core.dom.ForStatement;
import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.ReturnStatement;
import org.eclipse.jdt.core.dom.Statement;
import org.eclipse.jdt.core.dom.SwitchStatement;
import org.eclipse.jdt.core.dom.SynchronizedStatement;
import org.eclipse.jdt.core.dom.TryStatement;
import org.eclipse.jdt.core.dom.Type;
import org.eclipse.jdt.core.dom.VariableDeclarationStatement;
import org.eclipse.jdt.core.dom.WhileStatement;
import org.palladiosimulator.generator.fluent.exceptions.FluentApiException;
import org.palladiosimulator.generator.fluent.repository.api.seff.ActionSeff;
import org.palladiosimulator.generator.fluent.repository.factory.FluentRepositoryFactory;
import org.palladiosimulator.generator.fluent.repository.structure.components.BasicComponentCreator;
import org.palladiosimulator.generator.fluent.repository.structure.components.seff.BranchActionCreator;
import org.palladiosimulator.generator.fluent.repository.structure.components.seff.ExternalCallActionCreator;
import org.palladiosimulator.generator.fluent.repository.structure.components.seff.LoopActionCreator;
import org.palladiosimulator.generator.fluent.repository.structure.components.seff.SeffCreator;
import org.palladiosimulator.pcm.core.CoreFactory;
import org.palladiosimulator.pcm.core.PCMRandomVariable;
import org.palladiosimulator.pcm.parameter.ParameterFactory;
import org.palladiosimulator.pcm.parameter.VariableCharacterisation;
import org.palladiosimulator.pcm.parameter.VariableCharacterisationType;
import org.palladiosimulator.pcm.parameter.VariableUsage;
import org.palladiosimulator.pcm.repository.BasicComponent;
import org.palladiosimulator.pcm.repository.CompositeDataType;
import org.palladiosimulator.pcm.repository.DataType;
import org.palladiosimulator.pcm.repository.OperationInterface;
import org.palladiosimulator.pcm.repository.OperationProvidedRole;
import org.palladiosimulator.pcm.repository.OperationRequiredRole;
import org.palladiosimulator.pcm.repository.OperationSignature;
import org.palladiosimulator.pcm.repository.Parameter;
import org.palladiosimulator.pcm.repository.PassiveResource;
import org.palladiosimulator.pcm.repository.PrimitiveDataType;
import org.palladiosimulator.pcm.repository.RepositoryFactory;
import org.palladiosimulator.pcm.seff.SeffFactory;
import org.palladiosimulator.pcm.seff.SetVariableAction;
import org.somox.kdmhelper.MethodBundlePair;
import org.somox.kdmhelper.MethodPalladioInformation;
import org.somox.kdmhelper.StaticNameMethods;

import de.uka.ipd.sdq.stoex.NamespaceReference;
import de.uka.ipd.sdq.stoex.StoexFactory;
import de.uka.ipd.sdq.stoex.VariableReference;

public class Ast2SeffVisitor extends ASTVisitor {

	private static final Logger logger = Logger.getLogger(Ast2SeffVisitor.class);
	private static final FluentRepositoryFactory create = new FluentRepositoryFactory();	
	
	private Map<String, MethodPalladioInformation> methodNameMap;
	private MethodBundlePair methodAssociation;
	private ActionSeff actionSeff;
	private BasicComponentCreator basicComponentCreator;
	
	public Ast2SeffVisitor(MethodBundlePair methodAssociation, ActionSeff actionSeff, Map<String, MethodPalladioInformation> methodNameMap) {
		this.actionSeff = actionSeff;
		this.methodNameMap = methodNameMap;
		this.methodAssociation = methodAssociation;
	}
	
	public static ActionSeff perform(MethodBundlePair methodAssociation, ActionSeff actionSeff, Map<String, MethodPalladioInformation> methodNameMap) {
		Ast2SeffVisitor newFunctionCallClassificationVisitor = new Ast2SeffVisitor(methodAssociation, actionSeff, methodNameMap);
		methodAssociation.getAstNode().accept(newFunctionCallClassificationVisitor);
		return actionSeff;
	}
	
	private ActionSeff perform(ASTNode node, ActionSeff actionSeff) {
		Ast2SeffVisitor newFunctionCallClassificationVisitor = new Ast2SeffVisitor(methodAssociation, actionSeff, methodNameMap);
		node.accept(newFunctionCallClassificationVisitor);
		return actionSeff;
	}
	
	public boolean visit(final ExpressionStatement expressionStatement) {
		
		Expression expression = expressionStatement.getExpression();

		if (expression instanceof MethodInvocation && this.isExternal((MethodInvocation) expression)) {
			MethodInvocation methodInvocation = (MethodInvocation) expression;
			MethodBundlePair externalMethodAssociation = this.getExternalMethodAssociation(methodInvocation);
			BasicComponent externalBasicComponent = externalMethodAssociation.getBasicComponent();
			
			if (!externalBasicComponent.equals(basicComponent)) {
				createExternalCallAction(methodInvocation, externalBasicComponent);
			} else {
				generateClassMethodSeff(externalMethodAssociation);
			}
		} else {
			// TODO: check if we need to detect if expression statement is a class method 
			// 		 -> enter method body and generate seff for it
			createInternalAction(expressionStatement);
		}
		return super.visit(expressionStatement);
	}
	
	private void generateClassMethodSeff(MethodBundlePair methodAssociation) {
		perform(methodAssociation.getAstNode(), actionSeff);
	}
	
	private void createExternalCallAction(MethodInvocation methodInvocation, BasicComponent externalBasicComponent) {

		ExternalCallActionCreator externalCallActionCreator = actionSeff.externalCallAction();
//		StaticNameMethods.setEntityName(externalCall, methodInvocation);

		OperationProvidedRole operationProvidedRole = (OperationProvidedRole) externalBasicComponent.getProvidedRoles_InterfaceProvidingEntity().get(0);
		OperationInterface operationInterface = operationProvidedRole.getProvidedInterface__OperationProvidedRole();
		OperationRequiredRole operationRequiredRole = null;
		
		try {
			operationRequiredRole = create.fetchOfOperationRequiredRole("");
		} catch (FluentApiException e) {
			// TODO: handle exception
			basicComponentCreator.requires(create.fetchOfOperationInterface(""));
		}
		

		if (operationRequiredRole == null) {
			operationRequiredRole = RepositoryFactory.eINSTANCE.createOperationRequiredRole();
			StaticNameMethods.setEntityName(operationRequiredRole, basicComponent.getEntityName());
			basicComponent.getRequiredRoles_InterfaceRequiringEntity().add(operationRequiredRole);
		} else {
			operationRequiredRole = (OperationRequiredRole) basicComponent.getRequiredRoles_InterfaceRequiringEntity().get(0);
		}
		operationRequiredRole.setRequiredInterface__OperationRequiredRole(operationInterface);
		externalCallActionCreator.withRequiredRole(operationRequiredRole);

		actionSeff = externalCallActionCreator.followedBy();
		
		//// TODO: Change to Fluent Api
		//OperationSignature calledFunctionSignature = this.getOperationSignatureFromInterfaceByName(operationInterface, methodInvocation.getName().toString());
		//if(!methodInvocation.arguments().isEmpty())
		//{
		//	EList<VariableUsage> inputVariables = externalCall.getInputVariableUsages__CallAction();
		//	if(calledFunctionSignature != null && (calledFunctionSignature.getParameters__OperationSignature().size() == methodInvocation.arguments().size())) {
		//		//try to get variables from interface
		//		EList<Parameter> calledFunctParameterList = calledFunctionSignature.getParameters__OperationSignature();
		//		for(int i=0; i < calledFunctParameterList.size(); i++) {
		//			Parameter para = calledFunctParameterList.get(i);
		//			Expression castedArgument = (Expression) methodInvocation.arguments().get(i);
		//			generateVariables(para, castedArgument, inputVariables);
		//		}
		//	} else {
		//		//fallback if interface is not found or argumentsArrays have different sizes
		//		for(Object argument : methodInvocation.arguments()) {
		//			Expression castedArgument = (Expression) argument;
		//			generateVariables(null, castedArgument, inputVariables);
		//		}
		//	}
		//}
		//if(calledFunctionSignature != null && calledFunctionSignature.getReturnType__OperationSignature() != null) {
		//	DataType returnType = calledFunctionSignature.getReturnType__OperationSignature();
		//	EList<VariableUsage> outputVariables = externalCall.getReturnVariableUsage__CallReturnAction();
		//	generateVariables(returnType, outputVariables);
		//}
	}
	
	private void createInternalAction(final ExpressionStatement expressionStatement) {
		
		actionSeff = actionSeff.internalAction().withName("").followedBy();
//		StaticNameMethods.setEntityName(internalAction, expression);
	}
	
	
	public boolean visit(final IfStatement ifStatement) {
		BranchActionCreator branchActionCreator = actionSeff.branchAction();
		branchActionCreator = generateBranchAction(ifStatement, branchActionCreator);
//		StaticNameMethods.setEntityName(branchAction, ifStatement);
		
		if (ifStatement.getElseStatement() != null) {
			branchActionCreator = handleElseStatement(ifStatement.getElseStatement(), branchActionCreator);	
		}
		
		actionSeff = branchActionCreator.followedBy();
		return false;
	}
	
	private BranchActionCreator handleElseStatement(Statement statement, BranchActionCreator branchActionCreator) {
		
		ActionSeff innerActionSeff = create.newSeff().withSeffBehaviour().withStartAction().followedBy();
		
		if (statement instanceof IfStatement) {
			IfStatement elseIfStatement = (IfStatement) statement;
//			StaticNameMethods.setEntityName(branchTransitionElse, elseIfStatement);
			innerActionSeff = this.perform(elseIfStatement.getThenStatement(), innerActionSeff);
		} else {
//			StaticNameMethods.setEntityName(branchTransitionElse, statement);
			innerActionSeff = this.perform(statement, innerActionSeff);
		}
		
		SeffCreator seffCreator = innerActionSeff.stopAction().createBehaviourNow();
		branchActionCreator = branchActionCreator.withGuardedBranchTransition("expression", seffCreator);

		if (statement instanceof IfStatement) {
			IfStatement elseIfStatement = (IfStatement) statement;
			branchActionCreator = handleElseStatement(elseIfStatement.getElseStatement(), branchActionCreator);
		}
		
		return branchActionCreator;
	}
	
	public boolean visit(final SynchronizedStatement synchronizedStatement) {
		Expression exp = synchronizedStatement.getExpression();
		String className = StaticNameMethods.getClassName(exp) + ".class";
		
		PassiveResource passiveResource = RepositoryFactory.eINSTANCE.createPassiveResource();
		passiveResource.setCapacity_PassiveResource(CoreFactory.eINSTANCE.createPCMRandomVariable());
		StaticNameMethods.setEntityName(passiveResource, className);
		passiveResource.getCapacity_PassiveResource().setSpecification("1");
		if(this.basicComponent.getPassiveResource_BasicComponent().isEmpty())
			this.basicComponent.getPassiveResource_BasicComponent().add(passiveResource);
		else {
			EList<PassiveResource> passiveResoceList = this.basicComponent.getPassiveResource_BasicComponent();
			boolean found = false;
			for (PassiveResource entry : passiveResoceList) {
				if(found)
					break;
				if(entry.getEntityName() == className) {
					found = true;
					passiveResource = entry;
				}
			}
			if(!found)
				this.basicComponent.getPassiveResource_BasicComponent().add(passiveResource);
		}

		actionSeff = actionSeff.acquireAction().withPassiveResource(passiveResource).followedBy();

//		StaticNameMethods.setEntityName(acquireAction, className);

		actionSeff = this.perform(synchronizedStatement.getBody(), actionSeff)
				.releaseAction().withPassiveResource(passiveResource).followedBy();


//		StaticNameMethods.setEntityName(releaseAction, className);
		return false;
	}
	
	public boolean visit(final TryStatement tryStatement) {
		BranchActionCreator branchActionCreator = actionSeff.branchAction();
		branchActionCreator = generateBranchAction(tryStatement, branchActionCreator);
//		StaticNameMethods.setEntityName(branchAction, tryStatement);
		
		List<CatchClause> catchClauseList = tryStatement.catchClauses();
		for (CatchClause catchClause : catchClauseList) {
			ActionSeff innerActionSeff = create.newSeff().withSeffBehaviour().withStartAction().followedBy();
			innerActionSeff = this.perform(catchClause.getBody(), innerActionSeff);
			SeffCreator seffCreator = innerActionSeff.stopAction().createBehaviourNow();
			branchActionCreator = branchActionCreator.withGuardedBranchTransition("expression", seffCreator);
		}
		
		actionSeff = branchActionCreator.followedBy();
		return false;
	}
	
	public boolean visit(final ForStatement forStatement) {
		LoopActionCreator loopActionCreator = actionSeff.loopAction();
		loopActionCreator = generateLoopAction(forStatement.getBody(), loopActionCreator);
//		StaticNameMethods.setEntityName(loopAction, forStatement);
		actionSeff = loopActionCreator.followedBy();
		return false;
	}
	
	public boolean visit(final EnhancedForStatement forStatement) {
		LoopActionCreator loopActionCreator = actionSeff.loopAction();
		loopActionCreator = generateLoopAction(forStatement.getBody(), loopActionCreator);
//		StaticNameMethods.setEntityName(loopAction, forStatement);
		actionSeff = loopActionCreator.followedBy();
		return false;
	}
	
	public boolean visit(final WhileStatement whileStatement) {
		LoopActionCreator loopActionCreator = actionSeff.loopAction();
		loopActionCreator = generateLoopAction(whileStatement.getBody(), loopActionCreator);
//		StaticNameMethods.setEntityName(loopAction, whileStatement);
		actionSeff = loopActionCreator.followedBy();
		return false;
	}
	
	public boolean visit(final SwitchStatement switchStatement) {
		BranchActionCreator branchActionCreator = actionSeff.branchAction();
//		StaticNameMethods.setEntityName(branchAction, switchStatement);
		
		List<List<Statement>> blockList = SwitchStatementHelper.createBlockListFromSwitchStatement(switchStatement);
		for (List<Statement> block : blockList) {
			
			ActionSeff innerActionSeff = create.newSeff().withSeffBehaviour().withStartAction().followedBy();
			
			for (Statement statement : block) {
				innerActionSeff = this.perform(statement, innerActionSeff);
			}
			
			SeffCreator seffCreator = innerActionSeff.stopAction().createBehaviourNow();
//			StaticNameMethods.setEntityName(branchTransition, block);
			branchActionCreator= branchActionCreator.withGuardedBranchTransition("expression", seffCreator);
		}
		
		actionSeff = branchActionCreator.followedBy();
		return false;
	}

	/*
	 * Neu in Ast2Seff dazu gekommen, war nicht in JaMoPP vorhanden
	 * Verhalten aus "MediaStore3 -> AudioWatermarking" abgeschaut
	 */
	public boolean visit(final ReturnStatement returnStatement) {
		// TODO: Implement with Fluent Api
		SetVariableAction variableAction = SeffFactory.eINSTANCE.createSetVariableAction();
		Expression returnExpression = returnStatement.getExpression();
		this.generateVariables(null, returnExpression, variableAction.getLocalVariableUsages_SetVariableAction());
//		this.actionList.add(variableAction);
		return false;
	}
	
	// TODO: Further work
	public boolean visit(final VariableDeclarationStatement variableDeclarationStatement) {
		Type test = variableDeclarationStatement.getType();
		return super.visit(variableDeclarationStatement);
	}
	
	private OperationSignature getOperationSignatureFromInterfaceByName(OperationInterface operationInterface, String name) {
		EList<OperationSignature> functionList = operationInterface.getSignatures__OperationInterface();
		if(!functionList.isEmpty() && name != "") {
			for(OperationSignature signature : functionList) {
				String signatureName = signature.getEntityName();
				if(name.equals(signatureName))
					return signature;
			}
		}
		return null;
	}
	
	/*
	 * generates Input Variables
	 * 
	 * Neu in Ast2Seff dazu gekommen, war nicht in JaMoPP vorhanden
	 * Verhalten aus "MediaStore3 -> AudioWatermarking" abgeschaut
	 * +
	 * zusätzliche infos von: https://www.palladio-simulator.com/tools/tutorials/ (PCM Parameter (PDF) -> 18)
	 * 
	 *** The following types are available
	 * BYTESIZE: Memory footprint of a parameter
	 * VALUE: The actual value of a parameter for primitive types
	 * STRUCTURE: Structure of data, like „sorted“ or „unsorted“
	 * NUMBER_OF_ELEMENTS: The number of elements in a collection
	 * TYPE: The actual type of a parameter (vs. the declared type)
	 */
	private void generateVariables(Parameter para, Expression variable, EList<VariableUsage> variablesList) {
		//From ParameterFactory Docs: Note that it was an explicit design decision to refer to variable names instead of the actual variables (i.e., by refering to Parameter class).
		VariableCharacterisation booleanVariable = ParameterFactory.eINSTANCE.createVariableCharacterisation();
		VariableUsage variableUsage = ParameterFactory.eINSTANCE.createVariableUsage();
		NamespaceReference namespaceReference = StoexFactory.eINSTANCE.createNamespaceReference();
		VariableReference variableReference = StoexFactory.eINSTANCE.createVariableReference();
		PCMRandomVariable randomPCMVariable = CoreFactory.eINSTANCE.createPCMRandomVariable();

		if(para != null) {
			DataType paraDataType = para.getDataType__Parameter();
			if(paraDataType instanceof PrimitiveDataType) {
				booleanVariable.setType(VariableCharacterisationType.VALUE);
				namespaceReference.setReferenceName(((PrimitiveDataType) paraDataType).getType().toString());
			}
			else if(paraDataType instanceof CompositeDataType) {
				booleanVariable.setType(VariableCharacterisationType.BYTESIZE);
				namespaceReference.setReferenceName(((CompositeDataType) paraDataType).getEntityName());
			}
			else {
				booleanVariable.setType(VariableCharacterisationType.VALUE);
				namespaceReference.setReferenceName(para.getParameterName());
			}
			variableReference.setReferenceName(variable.toString());
		} else {
			booleanVariable.setType(VariableCharacterisationType.VALUE);
			namespaceReference.setReferenceName("PrimitiveType");
			variableReference.setReferenceName(StaticNameMethods.getExpressionClassName(variable));
		}
		
		variableUsage.getVariableCharacterisation_VariableUsage().add(booleanVariable);
		namespaceReference.setInnerReference_NamespaceReference(variableReference);
		variableUsage.setNamedReference__VariableUsage(namespaceReference);

		randomPCMVariable.setSpecification(namespaceReference.getReferenceName().toString() + "." + variableReference.getReferenceName().toString() + "." + booleanVariable.getType().toString());
		//randomPCMVariable.setSpecification(namespaceReference.getReferenceName().toString() + "." + variableReference.getReferenceName().toString());
		booleanVariable.setSpecification_VariableCharacterisation(randomPCMVariable);

		variablesList.add(variableUsage);
	}
	/*
	 * Same as generateVariables above, but for Output Variables 
	 */
	private void generateVariables(DataType returnType, EList<VariableUsage> variablesList) {
		//From ParameterFactory Docs: Note that it was an explicit design decision to refer to variable names instead of the actual variables (i.e., by refering to Parameter class).
		VariableCharacterisation booleanVariable = ParameterFactory.eINSTANCE.createVariableCharacterisation();
		VariableUsage variableUsage = ParameterFactory.eINSTANCE.createVariableUsage();
		NamespaceReference namespaceReference = StoexFactory.eINSTANCE.createNamespaceReference();
		VariableReference variableReference = StoexFactory.eINSTANCE.createVariableReference();
		PCMRandomVariable randomPCMVariable = CoreFactory.eINSTANCE.createPCMRandomVariable();

		if(returnType instanceof PrimitiveDataType) {
			booleanVariable.setType(VariableCharacterisationType.VALUE);
			namespaceReference.setReferenceName(((PrimitiveDataType) returnType).getType().toString());
		}
		else if(returnType instanceof CompositeDataType) {
			booleanVariable.setType(VariableCharacterisationType.BYTESIZE);
			namespaceReference.setReferenceName(((CompositeDataType) returnType).getEntityName());
		}
		else
			booleanVariable.setType(VariableCharacterisationType.VALUE);
		variableReference.setReferenceName("tempVariable");
		
		variableUsage.getVariableCharacterisation_VariableUsage().add(booleanVariable);
		namespaceReference.setInnerReference_NamespaceReference(variableReference);
		variableUsage.setNamedReference__VariableUsage(namespaceReference);

		randomPCMVariable.setSpecification(namespaceReference.getReferenceName().toString() + "." + variableReference.getReferenceName().toString() + "." + booleanVariable.getType().toString());
		//randomPCMVariable.setSpecification(namespaceReference.getReferenceName().toString() + "." + variableReference.getReferenceName().toString());
		booleanVariable.setSpecification_VariableCharacterisation(randomPCMVariable);

		variablesList.add(variableUsage);
	}

	private BranchActionCreator generateBranchAction(ASTNode node, BranchActionCreator branchActionCreator) {

		if(node instanceof IfStatement) {
			IfStatement ifStatement = (IfStatement) node;
			node = ifStatement.getThenStatement();
//			StaticNameMethods.setEntityName(branchTransition, ifStatement);
		}
		else if (node instanceof TryStatement) {
			TryStatement tryStatement = (TryStatement) node;
			node = tryStatement.getBody();
//			StaticNameMethods.setEntityName(branchTransition, tryStatement);
		}

//		branchTransition.setEntityName(this.ifStatementToString(tryStatement.getExpression()));
		
		ActionSeff innerActionSeff = create.newSeff().withSeffBehaviour().withStartAction().followedBy();
		innerActionSeff = this.perform(node, innerActionSeff);
		SeffCreator seffCreator = innerActionSeff.stopAction().createBehaviourNow();
		
		branchActionCreator.withGuardedBranchTransition("expression", seffCreator);

		return branchActionCreator;
	}
	
	private LoopActionCreator generateLoopAction(ASTNode node, LoopActionCreator loopActionCreator) {
		ActionSeff innerActionSeff = create.newSeff().withSeffBehaviour().withStartAction().followedBy();
		innerActionSeff = this.perform(node, innerActionSeff);
		SeffCreator seffCreator = innerActionSeff.stopAction().createBehaviourNow();
		loopActionCreator = loopActionCreator.withLoopBody(seffCreator);
		return loopActionCreator;
	}
	
	private boolean isExternal(MethodInvocation methodInvocation) {
		String methodName = methodInvocation.getName().toString();
		String className = StaticNameMethods.getClassName(methodInvocation);
		return this.methodNameMap.containsKey(className + "." + methodName);
	}
	
	private MethodBundlePair getExternalMethodAssociation(MethodInvocation methodInvocation) {
		String methodName = methodInvocation.getName().toString();
		String className = StaticNameMethods.getClassName(methodInvocation);
		if (this.methodNameMap.containsKey(className + "." + methodName)) {
			return this.methodNameMap.get(className + "." + methodName);
		} else {
			// TODO: handle the return of null
			return null;
		}
	}
}
