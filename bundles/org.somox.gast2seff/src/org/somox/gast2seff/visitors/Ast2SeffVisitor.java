package org.somox.gast2seff.visitors;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.EList;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.BooleanLiteral;
import org.eclipse.jdt.core.dom.CatchClause;
import org.eclipse.jdt.core.dom.EnhancedForStatement;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.ExpressionStatement;
import org.eclipse.jdt.core.dom.ForStatement;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.InfixExpression;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.PostfixExpression;
import org.eclipse.jdt.core.dom.PrimitiveType;
import org.eclipse.jdt.core.dom.ReturnStatement;
import org.eclipse.jdt.core.dom.Statement;
import org.eclipse.jdt.core.dom.SwitchCase;
import org.eclipse.jdt.core.dom.SwitchStatement;
import org.eclipse.jdt.core.dom.SynchronizedStatement;
import org.eclipse.jdt.core.dom.ThisExpression;
import org.eclipse.jdt.core.dom.TryStatement;
import org.eclipse.jdt.core.dom.Type;
import org.eclipse.jdt.core.dom.VariableDeclarationExpression;
import org.eclipse.jdt.core.dom.VariableDeclarationStatement;
import org.eclipse.jdt.core.dom.WhileStatement;
import org.palladiosimulator.pcm.core.CoreFactory;
import org.palladiosimulator.pcm.core.PCMRandomVariable;
import org.palladiosimulator.pcm.parameter.CharacterisedVariable;
import org.palladiosimulator.pcm.parameter.ParameterFactory;
import org.palladiosimulator.pcm.parameter.VariableCharacterisation;
import org.palladiosimulator.pcm.parameter.VariableCharacterisationType;
import org.palladiosimulator.pcm.parameter.VariableUsage;
import org.palladiosimulator.pcm.repository.BasicComponent;
import org.palladiosimulator.pcm.repository.OperationInterface;
import org.palladiosimulator.pcm.repository.OperationProvidedRole;
import org.palladiosimulator.pcm.repository.OperationRequiredRole;
import org.palladiosimulator.pcm.repository.PassiveResource;
import org.palladiosimulator.pcm.repository.PrimitiveTypeEnum;
import org.palladiosimulator.pcm.repository.RepositoryFactory;
import org.palladiosimulator.pcm.seff.AbstractAction;
import org.palladiosimulator.pcm.seff.AbstractBranchTransition;
import org.palladiosimulator.pcm.seff.AcquireAction;
import org.palladiosimulator.pcm.seff.BranchAction;
import org.palladiosimulator.pcm.seff.ExternalCallAction;
import org.palladiosimulator.pcm.seff.InternalAction;
import org.palladiosimulator.pcm.seff.LoopAction;
import org.palladiosimulator.pcm.seff.ReleaseAction;
import org.palladiosimulator.pcm.seff.ResourceDemandingBehaviour;
import org.palladiosimulator.pcm.seff.SeffFactory;
import org.palladiosimulator.pcm.seff.SetVariableAction;
import org.palladiosimulator.pcm.seff.StartAction;
import org.palladiosimulator.pcm.seff.StopAction;
import org.somox.kdmhelper.MethodAssociation;
import org.somox.kdmhelper.StaticNameMethods;

import de.uka.ipd.sdq.stoex.AbstractNamedReference;
import de.uka.ipd.sdq.stoex.NamespaceReference;
import de.uka.ipd.sdq.stoex.StoexFactory;
import de.uka.ipd.sdq.stoex.StoexPackage;
import de.uka.ipd.sdq.stoex.VariableReference;

public class Ast2SeffVisitor extends ASTVisitor {

	private static final Logger logger = Logger.getLogger(Ast2SeffVisitor.class);
		
	private EList<AbstractAction> actionList;
	private Map<String, MethodAssociation> methodNameMap;
	private MethodAssociation methodAssociation;
	private BasicComponent basicComponent;
	
	public Ast2SeffVisitor(MethodAssociation methodAssociation, EList<AbstractAction> actionList, Map<String, MethodAssociation> methodNameMap) {
		this.actionList = actionList;
		this.methodNameMap = methodNameMap;
		this.methodAssociation = methodAssociation;
		this.basicComponent = methodAssociation.getBasicComponent();
	}
	
	public static void perform(MethodAssociation methodAssociation, EList<AbstractAction> actionList, Map<String, MethodAssociation> methodNameMap) {
		Ast2SeffVisitor newFunctionCallClassificationVisitor = new Ast2SeffVisitor(methodAssociation, actionList, methodNameMap);
		methodAssociation.getAstNode().accept(newFunctionCallClassificationVisitor);
	}
	
	private void perform(ASTNode node, EList<AbstractAction> actionList) {
		Ast2SeffVisitor newFunctionCallClassificationVisitor = new Ast2SeffVisitor(methodAssociation, actionList, methodNameMap);
		node.accept(newFunctionCallClassificationVisitor);
	}
	
	public boolean visit(final ExpressionStatement expressionStatement) {
		
		Expression expression = expressionStatement.getExpression();

		if (expression instanceof MethodInvocation && this.isExternal((MethodInvocation) expression)) {
			MethodInvocation methodInvocation = (MethodInvocation) expression;
			MethodAssociation externalMethodAssociation = this.getExternalMethodAssociation(methodInvocation);
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
	
	private void generateClassMethodSeff(MethodAssociation methodAssociation) {
		perform(methodAssociation.getAstNode(), actionList);
	}
	
	private void createExternalCallAction(MethodInvocation methodInvocation, BasicComponent externalBasicComponent) {
		ExternalCallAction externalCall = SeffFactory.eINSTANCE.createExternalCallAction();
		StaticNameMethods.setEntityName(externalCall, methodInvocation);

		OperationProvidedRole operationProvidedRole = (OperationProvidedRole) externalBasicComponent.getProvidedRoles_InterfaceProvidingEntity().get(0);
		OperationInterface operationInterface = operationProvidedRole.getProvidedInterface__OperationProvidedRole();
		OperationRequiredRole operationRequiredRole = null;
		if (basicComponent.getRequiredRoles_InterfaceRequiringEntity().size() == 0) {
			operationRequiredRole = RepositoryFactory.eINSTANCE.createOperationRequiredRole();
			StaticNameMethods.setEntityName(operationRequiredRole, basicComponent.getEntityName());
			basicComponent.getRequiredRoles_InterfaceRequiringEntity().add(operationRequiredRole);
		} else {
			operationRequiredRole = (OperationRequiredRole) basicComponent.getRequiredRoles_InterfaceRequiringEntity().get(0);
		}
		operationRequiredRole.setRequiredInterface__OperationRequiredRole(operationInterface);
		externalCall.setRole_ExternalService(operationRequiredRole);
		
		this.actionList.add(externalCall);
		if(!methodInvocation.arguments().isEmpty())
		{
			EList<VariableUsage> inputVariables = externalCall.getInputVariableUsages__CallAction();
			for(Object argument : methodInvocation.arguments()) {
				Expression castedArgument = (Expression) argument;
				generateVariables(castedArgument, inputVariables);
			}
		}
	}
	
	private void createInternalAction(final ExpressionStatement expressionStatement) {
		InternalAction internalAction = SeffFactory.eINSTANCE.createInternalAction();
		Expression expression = expressionStatement.getExpression();
		StaticNameMethods.setEntityName(internalAction, expression);
		this.actionList.add(internalAction);
	}
	
	
	public boolean visit(final IfStatement ifStatement) {
		BranchAction branchAction = generateBranchAction(ifStatement.getThenStatement());
		StaticNameMethods.setEntityName(branchAction, ifStatement);
		
		if (ifStatement.getElseStatement() != null) {
			handleElseStatement(ifStatement.getElseStatement(), branchAction);			
		}
		
		this.actionList.add(branchAction);
		return false;
	}
	
	//TODO: Correct the entity name for the else if statement 
	private void handleElseStatement(Statement statement, BranchAction branchAction) {
		ResourceDemandingBehaviour branchBehaviourElse = SeffFactory.eINSTANCE.createResourceDemandingBehaviour();
		AbstractBranchTransition branchTransitionElse = SeffFactory.eINSTANCE.createGuardedBranchTransition();
		StartAction startActionElse = SeffFactory.eINSTANCE.createStartAction();
		branchBehaviourElse.getSteps_Behaviour().add(startActionElse);
		
		if (statement instanceof IfStatement) {
			IfStatement elseIfStatement = (IfStatement) statement;
			StaticNameMethods.setEntityName(branchTransitionElse, elseIfStatement);
			this.perform(elseIfStatement.getThenStatement(), branchBehaviourElse.getSteps_Behaviour());
		} else {
			StaticNameMethods.setEntityName(branchTransitionElse, statement);
			this.perform(statement, branchBehaviourElse.getSteps_Behaviour());
		}

		StopAction stopActionElse = SeffFactory.eINSTANCE.createStopAction();
		branchBehaviourElse.getSteps_Behaviour().add(stopActionElse);
		branchTransitionElse.setBranchBehaviour_BranchTransition(branchBehaviourElse);
		VisitorUtils.connectActions(branchBehaviourElse);
		branchAction.getBranches_Branch().add(branchTransitionElse);
		
		if (statement instanceof IfStatement) {
			IfStatement elseIfStatement = (IfStatement) statement;
			handleElseStatement(elseIfStatement.getElseStatement(), branchAction);
		}
	
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

		final AcquireAction acquireAction = SeffFactory.eINSTANCE.createAcquireAction();
		this.actionList.add(acquireAction);
		acquireAction.setPassiveresource_AcquireAction(passiveResource);
		StaticNameMethods.setEntityName(acquireAction, className);

		this.perform(synchronizedStatement.getBody(), actionList);

		final ReleaseAction releaseAction = SeffFactory.eINSTANCE.createReleaseAction();
		this.actionList.add(releaseAction);
		releaseAction.setPassiveResource_ReleaseAction(passiveResource);
		StaticNameMethods.setEntityName(releaseAction, className);
		return false;
	}
	
	public boolean visit(final TryStatement tryStatement) {
		
		BranchAction branchAction = generateBranchAction(tryStatement.getBody());
		
		List<CatchClause> catchClauseList = tryStatement.catchClauses();
		for (CatchClause catchClause : catchClauseList) {
			ResourceDemandingBehaviour catchBranchBehaviour = SeffFactory.eINSTANCE.createResourceDemandingBehaviour();
			AbstractBranchTransition catchBranchTransition = SeffFactory.eINSTANCE.createGuardedBranchTransition();
			StartAction catchStartAction = SeffFactory.eINSTANCE.createStartAction();
			catchBranchBehaviour.getSteps_Behaviour().add(catchStartAction);

			this.perform(catchClause.getBody(), catchBranchBehaviour.getSteps_Behaviour());
			
			StopAction catchStopAction = SeffFactory.eINSTANCE.createStopAction();
			catchBranchBehaviour.getSteps_Behaviour().add(catchStopAction);
			VisitorUtils.connectActions(catchBranchBehaviour);
			catchBranchTransition.setBranchBehaviour_BranchTransition(catchBranchBehaviour);
			branchAction.getBranches_Branch().add(catchBranchTransition);
		}
		
		this.actionList.add(branchAction);
		return false;
	}
	
	public boolean visit(final ForStatement forStatement) {
		LoopAction loopAction = generateLoopAction(forStatement.getBody());
		StaticNameMethods.setEntityName(loopAction, forStatement);
		this.actionList.add(loopAction);
		return false;
	}
	
	public boolean visit(final EnhancedForStatement forStatement) {
		LoopAction loopAction = generateLoopAction(forStatement.getBody());
		StaticNameMethods.setEntityName(loopAction, forStatement);
		this.actionList.add(loopAction);
		return false;
	}
	
	public boolean visit(final WhileStatement whileStatement) {
		LoopAction loopAction = generateLoopAction(whileStatement.getBody());
		StaticNameMethods.setEntityName(loopAction, whileStatement);
		this.actionList.add(loopAction);
		return false;
	}
	
	public boolean visit(final SwitchStatement switchStatement) {
		BranchAction branchAction = SeffFactory.eINSTANCE.createBranchAction();
		StaticNameMethods.setEntityName(branchAction, switchStatement);
		
		List<List<Statement>> blockList = SwitchStatementHelper.createBlockListFromSwitchStatement(switchStatement);
		// TODO: Do we need to build an ASTNode to enable the visitor pattern?
		for (List<Statement> block : blockList) {
			ResourceDemandingBehaviour branchBehaviour = SeffFactory.eINSTANCE.createResourceDemandingBehaviour();
			AbstractBranchTransition branchTransition = SeffFactory.eINSTANCE.createGuardedBranchTransition();
			StartAction startAction = SeffFactory.eINSTANCE.createStartAction();
			branchBehaviour.getSteps_Behaviour().add(startAction);
			
			for (Statement statement : block) {
				this.perform(statement, branchBehaviour.getSteps_Behaviour());
			}
			
			StopAction stopAction = SeffFactory.eINSTANCE.createStopAction();
			branchBehaviour.getSteps_Behaviour().add(stopAction);
			VisitorUtils.connectActions(branchBehaviour);
			branchTransition.setBranchBehaviour_BranchTransition(branchBehaviour);
			branchAction.getBranches_Branch().add(branchTransition);
		}
		
		this.actionList.add(branchAction);
		return false;
	}
	
	public boolean visit(final ReturnStatement returnStatement) {
		////TODO: Für auftreten von return mit variable diese action setzen 
		SetVariableAction variableAction = SeffFactory.eINSTANCE.createSetVariableAction();
		Expression returnExpression = returnStatement.getExpression();
		this.generateVariables(returnExpression, variableAction.getLocalVariableUsages_SetVariableAction());
		this.actionList.add(variableAction);
		return false;
	}
	
	// TODO: Further work
	public boolean visit(final VariableDeclarationStatement variableDeclarationStatement) {
		Type test = variableDeclarationStatement.getType();
		return super.visit(variableDeclarationStatement);
	}
	
	/*
	 * Neu in Ast2Seff dazu gekommen, war nicht in JaMoPP vorhanden
	 * Verhalten aus "MediaStore3 -> AudioWatermarking" abgeschaut
	 * https://updatesite.palladio-simulator.com/archive/pcm_archive/revisions/2009-03-07_PCM_javadoc/de/uka/ipd/sdq/pcm/parameter/ParameterFactory.html
	 */
	private void generateVariables(Expression variable, EList<VariableUsage> variablesList) {
		VariableCharacterisation booleanVariable = ParameterFactory.eINSTANCE.createVariableCharacterisation();
		VariableUsage variableUsage = ParameterFactory.eINSTANCE.createVariableUsage();
		NamespaceReference namespaceReference = StoexFactory.eINSTANCE.createNamespaceReference();
		VariableReference variableReference = StoexFactory.eINSTANCE.createVariableReference();
		PCMRandomVariable randomPCMVariable = CoreFactory.eINSTANCE.createPCMRandomVariable();

		booleanVariable.setType(VariableCharacterisationType.VALUE);
		variableUsage.getVariableCharacterisation_VariableUsage().add(booleanVariable);
		namespaceReference.setReferenceName("PrimitiveType");
		variableReference.setReferenceName(StaticNameMethods.getExpressionClassName(variable));
		namespaceReference.setInnerReference_NamespaceReference(variableReference);
		variableUsage.setNamedReference__VariableUsage(namespaceReference);

		randomPCMVariable.setSpecification(namespaceReference.getReferenceName().toString() + "." + variableReference.getReferenceName().toString() + "." + booleanVariable.getType().toString());
		booleanVariable.setSpecification_VariableCharacterisation(randomPCMVariable);

		variablesList.add(variableUsage);
		
		//missing types: PrimitiveType.INT, PrimitiveType.DOUBLE, PrimitiveType.FLOAT, PrimitiveType.BYTE, CLASS, STRUCT
	}

	private BranchAction generateBranchAction(ASTNode node) {
		BranchAction branchAction = SeffFactory.eINSTANCE.createBranchAction();
		ResourceDemandingBehaviour branchBehaviour = SeffFactory.eINSTANCE.createResourceDemandingBehaviour();
		AbstractBranchTransition branchTransition = SeffFactory.eINSTANCE.createGuardedBranchTransition();
		StartAction startAction = SeffFactory.eINSTANCE.createStartAction();
		branchBehaviour.getSteps_Behaviour().add(startAction);

//		branchTransition.setEntityName(this.ifStatementToString(tryStatement.getExpression()));
		
		this.perform(node, branchBehaviour.getSteps_Behaviour());
		
		StopAction stopAction = SeffFactory.eINSTANCE.createStopAction();
		branchBehaviour.getSteps_Behaviour().add(stopAction);
		VisitorUtils.connectActions(branchBehaviour);
		branchTransition.setBranchBehaviour_BranchTransition(branchBehaviour);
		branchAction.getBranches_Branch().add(branchTransition);
		return branchAction;
	}
	
	private LoopAction generateLoopAction(ASTNode node) {
		LoopAction loopAction = SeffFactory.eINSTANCE.createLoopAction();
		ResourceDemandingBehaviour bodyBehaviour = SeffFactory.eINSTANCE.createResourceDemandingBehaviour();
		loopAction.setBodyBehaviour_Loop(bodyBehaviour);
		StartAction startAction = SeffFactory.eINSTANCE.createStartAction();
		bodyBehaviour.getSteps_Behaviour().add(startAction);
		
		this.perform(node, bodyBehaviour.getSteps_Behaviour());
		
		StopAction stopAction = SeffFactory.eINSTANCE.createStopAction();
		bodyBehaviour.getSteps_Behaviour().add(stopAction);
		VisitorUtils.connectActions(bodyBehaviour);
		return loopAction;
	}
	
	private boolean isExternal(MethodInvocation methodInvocation) {
		String methodName = methodInvocation.getName().toString();
		String className = StaticNameMethods.getClassName(methodInvocation);
		return this.methodNameMap.containsKey(className + "." + methodName);
	}
	
	private MethodAssociation getExternalMethodAssociation(MethodInvocation methodInvocation) {
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
