package org.somox.gast2seff.jobs;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.somox.analyzer.simplemodelanalyzer.builder.util.DefaultResourceEnvironment;
import org.somox.seff2javaast.SEFF2MethodMapping;
//import org.somox.resources.defaultmodels.DefaultModelLoader;

import de.uka.ipd.sdq.pcm.core.CoreFactory;
import de.uka.ipd.sdq.pcm.core.PCMRandomVariable;
import de.uka.ipd.sdq.pcm.seff.BranchAction;
import de.uka.ipd.sdq.pcm.seff.ProbabilisticBranchTransition;
import de.uka.ipd.sdq.pcm.seff.ServiceEffectSpecification;
import de.uka.ipd.sdq.pcm.seff.seff_performance.ParametricResourceDemand;
import de.uka.ipd.sdq.pcm.seff.seff_performance.SeffPerformanceFactory;

/**
 * Creates default QoS annotations for behaviour. Same probabilities for all branches and 1
 * execution per loop.
 *
 * @author Klaus Krogmann
 *
 */
public class DefaultQosAnnotationsBuilder {

    private final Logger logger = Logger.getLogger(DefaultQosAnnotationsBuilder.class);

    public DefaultQosAnnotationsBuilder() {
    }

    /**
     * Creates a QoS Annotation model with equal branch probabilities and 1 fixed loop execution.
     *
     * @param listofSEFF2MethodMappings
     *
     */
    public void buildDefaultQosAnnotations(final EList<SEFF2MethodMapping> listofSEFF2MethodMappings) {
        for (final SEFF2MethodMapping mapping : listofSEFF2MethodMappings) {
            final ServiceEffectSpecification seff = mapping.getSeff();

            final TreeIterator<Object> elements = EcoreUtil.getAllContents(seff, true);
            while (elements.hasNext()) {
                final EObject eObject = (EObject) elements.next();

                if (eObject instanceof de.uka.ipd.sdq.pcm.seff.LoopAction) {
                    final de.uka.ipd.sdq.pcm.seff.LoopAction loopAction = (de.uka.ipd.sdq.pcm.seff.LoopAction) eObject;
                    this.createDefaultLoopCount(loopAction);
                }
                if (eObject instanceof de.uka.ipd.sdq.pcm.seff.BranchAction) {
                    final de.uka.ipd.sdq.pcm.seff.BranchAction branchAction = (de.uka.ipd.sdq.pcm.seff.BranchAction) eObject;
                    this.createDefaultBranchProbability(branchAction);
                }
                if (eObject instanceof de.uka.ipd.sdq.pcm.seff.InternalAction) {
                    final de.uka.ipd.sdq.pcm.seff.InternalAction internalAction = (de.uka.ipd.sdq.pcm.seff.InternalAction) eObject;
                    this.createDefaultCpuResourceDemand(internalAction);
                }
            }
        }

        // this.qosAnnotationsModel.setEntityName("SoMoX Default QoS Annotations");
    }

    private void createDefaultBranchProbability(final BranchAction branchAction) {

        for (final de.uka.ipd.sdq.pcm.seff.AbstractBranchTransition branchTransition : branchAction
                .getBranches_Branch()) {
            if (branchTransition instanceof ProbabilisticBranchTransition) {
                final ProbabilisticBranchTransition probabilisticBranchTransition = (ProbabilisticBranchTransition) branchTransition;
                final double probability = 1.0d / branchAction.getBranches_Branch().size();
                probabilisticBranchTransition.setBranchProbability(probability);
                probabilisticBranchTransition.setEntityName("SoMoX default branch probability");
            } else {
                this.logger.warn("Unsupported branch transition type. Only ProbabilisticBranchTransition supported");
            }
        }
    }

    private void createDefaultLoopCount(final de.uka.ipd.sdq.pcm.seff.LoopAction loopAction) {

        final PCMRandomVariable loopCount = CoreFactory.eINSTANCE.createPCMRandomVariable();
        loopCount.setSpecification("1");
        loopAction.setIterationCount_LoopAction(loopCount);
        loopAction.setEntityName("SoMoX default loop count value");
    }

    private void createDefaultCpuResourceDemand(final de.uka.ipd.sdq.pcm.seff.InternalAction internalAction) {
        final ParametricResourceDemand prd = SeffPerformanceFactory.eINSTANCE.createParametricResourceDemand();
        final PCMRandomVariable randomVar = CoreFactory.eINSTANCE.createPCMRandomVariable();
        randomVar.setSpecification("0");
        prd.setSpecification_ParametericResourceDemand(randomVar);
        internalAction.getResourceDemand_Action().add(prd);
        prd.setRequiredResource_ParametricResourceDemand(DefaultResourceEnvironment.getCPUProcessingResourceType());
        prd.setAction_ParametricResourceDemand(internalAction);
    }
}
