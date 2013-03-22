package org.somox.gast2seff.jobs;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.somox.resources.defaultmodels.DefaultModelLoader;

import eu.qimpress.qualityannotationdecorator.seffdecorator.BranchProbability;
import eu.qimpress.qualityannotationdecorator.seffdecorator.CpuResourceDemand;
import eu.qimpress.qualityannotationdecorator.seffdecorator.LoopCount;
import eu.qimpress.qualityannotationdecorator.seffdecorator.SeffdecoratorFactory;
import eu.qimpress.samm.qosannotation.AnnotationType;
import eu.qimpress.samm.qosannotation.ConstantNumber;
import eu.qimpress.samm.qosannotation.ParametricFormula;
import eu.qimpress.samm.qosannotation.QosAnnotations;
import eu.qimpress.samm.qosannotation.QosannotationFactory;
import eu.qimpress.seff.AbstractBranchTransition;
import eu.qimpress.seff.BranchAction;
import eu.qimpress.seff.InternalAction;
import eu.qimpress.seff.LoopAction;
import eu.qimpress.seff.ProbabilisticBranchTransition;
import eu.qimpress.seff.SeffRepository;

/**
 * Creates default QoS annotations for behaviour. Same probabilities for
 * all branches and 1 execution per loop.
 * @author Klaus Krogmann
 *
 */
public class DefaultQosAnnotationsBuilder {
	
	private Logger logger = Logger.getLogger(DefaultQosAnnotationsBuilder.class);
	
	private QosAnnotations qosAnnotationsModel;
	private SeffRepository seffRepository;
	DefaultModelLoader defaultModelLoader;

	public DefaultQosAnnotationsBuilder(QosAnnotations qosAnnotationsModel, SeffRepository seffRepository) {
		this.qosAnnotationsModel = qosAnnotationsModel;
		this.seffRepository = seffRepository;
		this.defaultModelLoader = new DefaultModelLoader();
	}
	
	/**
	 * Creates a QoS Annotation model with equal
	 * branch probabilities and 1 fixed loop execution.
	 * 
	 */
	public void buildDefaultQosAnnotations() {
		TreeIterator<Object> elements = EcoreUtil.getAllContents(this.seffRepository, true);
		while(elements.hasNext()) {
			EObject eObject = (EObject)elements.next();

			if(eObject instanceof LoopAction) {
				LoopAction loopAction = (LoopAction)eObject;		
				createDefaultLoopCount(loopAction);			
			}
			if(eObject instanceof BranchAction) {
				BranchAction branchAction = (BranchAction)eObject;		
				createDefaultBranchProbability(branchAction);			
			}		
			if(eObject instanceof InternalAction) {
				InternalAction internalAction = (InternalAction)eObject;		
				createDefaultCpuResourceDemand(internalAction);			
			}								
		}		
		
		this.qosAnnotationsModel.setName("SoMoX Default QoS Annotations");
	}

	private void createDefaultBranchProbability(
			BranchAction branchAction) {
		for(AbstractBranchTransition branchTransition : branchAction.getAbstractBranchTransition()) {
			if(branchTransition instanceof ProbabilisticBranchTransition) {
			
				BranchProbability branchProbability = SeffdecoratorFactory.eINSTANCE.createBranchProbability();				
				ConstantNumber constantNumber = QosannotationFactory.eINSTANCE.createConstantNumber();
				// equal probability
				double probability = 1.0d / branchAction.getAbstractBranchTransition().size(); 				
				constantNumber.setValue(probability);
				branchProbability.setConstantNumber(constantNumber);
				branchProbability.setAnnotationType(AnnotationType.REQUIREMENT);
				branchProbability.setName("SoMoX default branch probability");
				branchProbability.setDocumentation("Replace this value with real measured or estimated values");
				branchProbability.setProbabilisticBranchTransition((ProbabilisticBranchTransition)branchTransition);

				qosAnnotationsModel.getAnnotation().add(branchProbability);
			} else {
				logger.warn("Unsupported branch transition type. Only ProbabilisticBranchTransition supported");
			}
		}
	}

	private void createDefaultLoopCount(LoopAction loopAction) {
		LoopCount loopCount = SeffdecoratorFactory.eINSTANCE.createLoopCount();
		ParametricFormula parametricFormula = QosannotationFactory.eINSTANCE.createParametricFormula();
		parametricFormula.setSpecification("1"); //constant default
		loopCount.setParametricFormula(parametricFormula);
		loopCount.setAnnotationType(AnnotationType.REQUIREMENT);
		loopCount.setName("SoMoX default loop count value");
		loopCount.setDocumentation("Replace this value with real measured or estimated values");
		loopCount.setLoopAction(loopAction);

		qosAnnotationsModel.getAnnotation().add(loopCount);
	}	
	
	private void createDefaultCpuResourceDemand(InternalAction internalAction) {
		CpuResourceDemand resourceDemand = SeffdecoratorFactory.eINSTANCE.createCpuResourceDemand();
		ParametricFormula parametricFormula = QosannotationFactory.eINSTANCE.createParametricFormula();
		parametricFormula.setSpecification("1"); //constant default
		resourceDemand.setParametricFormula(parametricFormula);
		resourceDemand.setAnnotationType(AnnotationType.REQUIREMENT);
		//Hauck: QoS annotation resource demand does not have reference to execution resource anymore
		//resourceDemand.setExecutionResource(defaultModelLoader.getDefaultExecutionResource());
		resourceDemand.setName("SoMoX default CPU resource demand");
		resourceDemand.setDocumentation("Replace this value with real measured or estimated values");
		resourceDemand.setInternalAction(internalAction);
		
		qosAnnotationsModel.getAnnotation().add(resourceDemand);
	}
}
