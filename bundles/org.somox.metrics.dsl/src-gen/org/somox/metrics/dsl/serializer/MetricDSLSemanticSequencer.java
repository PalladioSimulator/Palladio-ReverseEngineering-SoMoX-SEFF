package org.somox.metrics.dsl.serializer;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.serializer.acceptor.SequenceFeeder;
import org.eclipse.xtext.serializer.sequencer.AbstractDelegatingSemanticSequencer;
import org.eclipse.xtext.serializer.sequencer.ISemanticNodeProvider.INodesForEObjectProvider;
import org.eclipse.xtext.serializer.sequencer.ITransientValueService.ValueTransient;
import org.somox.metrics.dsl.metricDSL.BoundAndWeight;
import org.somox.metrics.dsl.metricDSL.Constant;
import org.somox.metrics.dsl.metricDSL.ExternalMetric;
import org.somox.metrics.dsl.metricDSL.InternalMetric;
import org.somox.metrics.dsl.metricDSL.MetricAndWeight;
import org.somox.metrics.dsl.metricDSL.MetricDSLPackage;
import org.somox.metrics.dsl.metricDSL.MetricModel;
import org.somox.metrics.dsl.metricDSL.Parameter;
import org.somox.metrics.dsl.metricDSL.RatioMetric;
import org.somox.metrics.dsl.metricDSL.StepwiseMetric;
import org.somox.metrics.dsl.metricDSL.WeightedMetric;
import org.somox.metrics.dsl.services.MetricDSLGrammarAccess;

import com.google.inject.Inject;

@SuppressWarnings("all")
public class MetricDSLSemanticSequencer extends AbstractDelegatingSemanticSequencer {

    @Inject
    private MetricDSLGrammarAccess grammarAccess;

    @Override
    public void createSequence(final EObject context, final EObject semanticObject) {
        if (semanticObject.eClass().getEPackage() == MetricDSLPackage.eINSTANCE) {
            switch (semanticObject.eClass().getClassifierID()) {
            case MetricDSLPackage.BOUND_AND_WEIGHT:
                if (context == this.grammarAccess.getBoundAndWeightRule()) {
                    this.sequence_BoundAndWeight(context, (BoundAndWeight) semanticObject);
                    return;
                } else {
                    break;
                }
            case MetricDSLPackage.CONSTANT:
                if (context == this.grammarAccess.getConstantRule() || context == this.grammarAccess.getNumberRule()) {
                    this.sequence_Constant(context, (Constant) semanticObject);
                    return;
                } else {
                    break;
                }
            case MetricDSLPackage.EXTERNAL_METRIC:
                if (context == this.grammarAccess.getExternalMetricRule()
                        || context == this.grammarAccess.getMetricRule()) {
                    this.sequence_ExternalMetric(context, (ExternalMetric) semanticObject);
                    return;
                } else {
                    break;
                }
            case MetricDSLPackage.INTERNAL_METRIC:
                if (context == this.grammarAccess.getInternalMetricRule()
                        || context == this.grammarAccess.getMetricRule()) {
                    this.sequence_InternalMetric(context, (InternalMetric) semanticObject);
                    return;
                } else {
                    break;
                }
            case MetricDSLPackage.METRIC_AND_WEIGHT:
                if (context == this.grammarAccess.getMetricAndWeightRule()) {
                    this.sequence_MetricAndWeight(context, (MetricAndWeight) semanticObject);
                    return;
                } else {
                    break;
                }
            case MetricDSLPackage.METRIC_MODEL:
                if (context == this.grammarAccess.getMetricModelRule()) {
                    this.sequence_MetricModel(context, (MetricModel) semanticObject);
                    return;
                } else {
                    break;
                }
            case MetricDSLPackage.PARAMETER:
                if (context == this.grammarAccess.getNumberRule() || context == this.grammarAccess.getParameterRule()) {
                    this.sequence_Parameter(context, (Parameter) semanticObject);
                    return;
                } else {
                    break;
                }
            case MetricDSLPackage.RATIO_METRIC:
                if (context == this.grammarAccess.getMetricDefinitionRule()
                        || context == this.grammarAccess.getRatioMetricRule()) {
                    this.sequence_RatioMetric(context, (RatioMetric) semanticObject);
                    return;
                } else {
                    break;
                }
            case MetricDSLPackage.STEPWISE_METRIC:
                if (context == this.grammarAccess.getMetricDefinitionRule()
                        || context == this.grammarAccess.getStepwiseMetricRule()) {
                    this.sequence_StepwiseMetric(context, (StepwiseMetric) semanticObject);
                    return;
                } else {
                    break;
                }
            case MetricDSLPackage.WEIGHTED_METRIC:
                if (context == this.grammarAccess.getMetricDefinitionRule()
                        || context == this.grammarAccess.getWeightedMetricRule()) {
                    this.sequence_WeightedMetric(context, (WeightedMetric) semanticObject);
                    return;
                } else {
                    break;
                }
            }
        }
        if (this.errorAcceptor != null) {
            this.errorAcceptor
                    .accept(this.diagnosticProvider.createInvalidContextOrTypeDiagnostic(semanticObject, context));
        }
    }

    /**
     * Constraint: (upperBound=[Number|MYID] weight=[Number|MYID])
     */
    protected void sequence_BoundAndWeight(final EObject context, final BoundAndWeight semanticObject) {
        if (this.errorAcceptor != null) {
            if (this.transientValues.isValueTransient(semanticObject,
                    MetricDSLPackage.Literals.BOUND_AND_WEIGHT__UPPER_BOUND) == ValueTransient.YES) {
                this.errorAcceptor.accept(this.diagnosticProvider.createFeatureValueMissing(semanticObject,
                        MetricDSLPackage.Literals.BOUND_AND_WEIGHT__UPPER_BOUND));
            }
            if (this.transientValues.isValueTransient(semanticObject,
                    MetricDSLPackage.Literals.BOUND_AND_WEIGHT__WEIGHT) == ValueTransient.YES) {
                this.errorAcceptor.accept(this.diagnosticProvider.createFeatureValueMissing(semanticObject,
                        MetricDSLPackage.Literals.BOUND_AND_WEIGHT__WEIGHT));
            }
        }
        final INodesForEObjectProvider nodes = this.createNodeProvider(semanticObject);
        final SequenceFeeder feeder = this.createSequencerFeeder(semanticObject, nodes);
        feeder.accept(this.grammarAccess.getBoundAndWeightAccess().getUpperBoundNumberMYIDParserRuleCall_1_0_1(),
                semanticObject.getUpperBound());
        feeder.accept(this.grammarAccess.getBoundAndWeightAccess().getWeightNumberMYIDParserRuleCall_3_0_1(),
                semanticObject.getWeight());
        feeder.finish();
    }

    /**
     * Constraint: (name=MYID value=DOUBLE)
     */
    protected void sequence_Constant(final EObject context, final Constant semanticObject) {
        if (this.errorAcceptor != null) {
            if (this.transientValues.isValueTransient(semanticObject,
                    MetricDSLPackage.Literals.NUMBER__NAME) == ValueTransient.YES) {
                this.errorAcceptor.accept(this.diagnosticProvider.createFeatureValueMissing(semanticObject,
                        MetricDSLPackage.Literals.NUMBER__NAME));
            }
            if (this.transientValues.isValueTransient(semanticObject,
                    MetricDSLPackage.Literals.CONSTANT__VALUE) == ValueTransient.YES) {
                this.errorAcceptor.accept(this.diagnosticProvider.createFeatureValueMissing(semanticObject,
                        MetricDSLPackage.Literals.CONSTANT__VALUE));
            }
        }
        final INodesForEObjectProvider nodes = this.createNodeProvider(semanticObject);
        final SequenceFeeder feeder = this.createSequencerFeeder(semanticObject, nodes);
        feeder.accept(this.grammarAccess.getConstantAccess().getNameMYIDParserRuleCall_1_0(), semanticObject.getName());
        feeder.accept(this.grammarAccess.getConstantAccess().getValueDOUBLETerminalRuleCall_3_0(),
                semanticObject.getValue());
        feeder.finish();
    }

    /**
     * Constraint: name=MYID
     */
    protected void sequence_ExternalMetric(final EObject context, final ExternalMetric semanticObject) {
        if (this.errorAcceptor != null) {
            if (this.transientValues.isValueTransient(semanticObject,
                    MetricDSLPackage.Literals.METRIC__NAME) == ValueTransient.YES) {
                this.errorAcceptor.accept(this.diagnosticProvider.createFeatureValueMissing(semanticObject,
                        MetricDSLPackage.Literals.METRIC__NAME));
            }
        }
        final INodesForEObjectProvider nodes = this.createNodeProvider(semanticObject);
        final SequenceFeeder feeder = this.createSequencerFeeder(semanticObject, nodes);
        feeder.accept(this.grammarAccess.getExternalMetricAccess().getNameMYIDParserRuleCall_0(),
                semanticObject.getName());
        feeder.finish();
    }

    /**
     * Constraint: (name=MYID shortName=STRING description=STRING parameter+=Number*
     * definition=MetricDefinition)
     */
    protected void sequence_InternalMetric(final EObject context, final InternalMetric semanticObject) {
        this.genericSequencer.createSequence(context, semanticObject);
    }

    /**
     * Constraint: (metric=[Metric|MYID] weight=[Number|MYID])
     */
    protected void sequence_MetricAndWeight(final EObject context, final MetricAndWeight semanticObject) {
        if (this.errorAcceptor != null) {
            if (this.transientValues.isValueTransient(semanticObject,
                    MetricDSLPackage.Literals.METRIC_AND_WEIGHT__METRIC) == ValueTransient.YES) {
                this.errorAcceptor.accept(this.diagnosticProvider.createFeatureValueMissing(semanticObject,
                        MetricDSLPackage.Literals.METRIC_AND_WEIGHT__METRIC));
            }
            if (this.transientValues.isValueTransient(semanticObject,
                    MetricDSLPackage.Literals.METRIC_AND_WEIGHT__WEIGHT) == ValueTransient.YES) {
                this.errorAcceptor.accept(this.diagnosticProvider.createFeatureValueMissing(semanticObject,
                        MetricDSLPackage.Literals.METRIC_AND_WEIGHT__WEIGHT));
            }
        }
        final INodesForEObjectProvider nodes = this.createNodeProvider(semanticObject);
        final SequenceFeeder feeder = this.createSequencerFeeder(semanticObject, nodes);
        feeder.accept(this.grammarAccess.getMetricAndWeightAccess().getMetricMetricMYIDParserRuleCall_1_0_1(),
                semanticObject.getMetric());
        feeder.accept(this.grammarAccess.getMetricAndWeightAccess().getWeightNumberMYIDParserRuleCall_3_0_1(),
                semanticObject.getWeight());
        feeder.finish();
    }

    /**
     * Constraint: (importURI+=STRING* metrics+=ExternalMetric* metrics+=InternalMetric+)
     */
    protected void sequence_MetricModel(final EObject context, final MetricModel semanticObject) {
        this.genericSequencer.createSequence(context, semanticObject);
    }

    /**
     * Constraint: (name=MYID shortname=STRING description=STRING defaultValue=DOUBLE)
     */
    protected void sequence_Parameter(final EObject context, final Parameter semanticObject) {
        if (this.errorAcceptor != null) {
            if (this.transientValues.isValueTransient(semanticObject,
                    MetricDSLPackage.Literals.NUMBER__NAME) == ValueTransient.YES) {
                this.errorAcceptor.accept(this.diagnosticProvider.createFeatureValueMissing(semanticObject,
                        MetricDSLPackage.Literals.NUMBER__NAME));
            }
            if (this.transientValues.isValueTransient(semanticObject,
                    MetricDSLPackage.Literals.PARAMETER__SHORTNAME) == ValueTransient.YES) {
                this.errorAcceptor.accept(this.diagnosticProvider.createFeatureValueMissing(semanticObject,
                        MetricDSLPackage.Literals.PARAMETER__SHORTNAME));
            }
            if (this.transientValues.isValueTransient(semanticObject,
                    MetricDSLPackage.Literals.PARAMETER__DESCRIPTION) == ValueTransient.YES) {
                this.errorAcceptor.accept(this.diagnosticProvider.createFeatureValueMissing(semanticObject,
                        MetricDSLPackage.Literals.PARAMETER__DESCRIPTION));
            }
            if (this.transientValues.isValueTransient(semanticObject,
                    MetricDSLPackage.Literals.PARAMETER__DEFAULT_VALUE) == ValueTransient.YES) {
                this.errorAcceptor.accept(this.diagnosticProvider.createFeatureValueMissing(semanticObject,
                        MetricDSLPackage.Literals.PARAMETER__DEFAULT_VALUE));
            }
        }
        final INodesForEObjectProvider nodes = this.createNodeProvider(semanticObject);
        final SequenceFeeder feeder = this.createSequencerFeeder(semanticObject, nodes);
        feeder.accept(this.grammarAccess.getParameterAccess().getNameMYIDParserRuleCall_1_0(),
                semanticObject.getName());
        feeder.accept(this.grammarAccess.getParameterAccess().getShortnameSTRINGTerminalRuleCall_3_0(),
                semanticObject.getShortname());
        feeder.accept(this.grammarAccess.getParameterAccess().getDescriptionSTRINGTerminalRuleCall_5_0(),
                semanticObject.getDescription());
        feeder.accept(this.grammarAccess.getParameterAccess().getDefaultValueDOUBLETerminalRuleCall_7_0(),
                semanticObject.getDefaultValue());
        feeder.finish();
    }

    /**
     * Constraint: (nominatorMetric=[Metric|MYID] denominatorMetric=[Metric|MYID])
     */
    protected void sequence_RatioMetric(final EObject context, final RatioMetric semanticObject) {
        if (this.errorAcceptor != null) {
            if (this.transientValues.isValueTransient(semanticObject,
                    MetricDSLPackage.Literals.RATIO_METRIC__NOMINATOR_METRIC) == ValueTransient.YES) {
                this.errorAcceptor.accept(this.diagnosticProvider.createFeatureValueMissing(semanticObject,
                        MetricDSLPackage.Literals.RATIO_METRIC__NOMINATOR_METRIC));
            }
            if (this.transientValues.isValueTransient(semanticObject,
                    MetricDSLPackage.Literals.RATIO_METRIC__DENOMINATOR_METRIC) == ValueTransient.YES) {
                this.errorAcceptor.accept(this.diagnosticProvider.createFeatureValueMissing(semanticObject,
                        MetricDSLPackage.Literals.RATIO_METRIC__DENOMINATOR_METRIC));
            }
        }
        final INodesForEObjectProvider nodes = this.createNodeProvider(semanticObject);
        final SequenceFeeder feeder = this.createSequencerFeeder(semanticObject, nodes);
        feeder.accept(this.grammarAccess.getRatioMetricAccess().getNominatorMetricMetricMYIDParserRuleCall_2_0_1(),
                semanticObject.getNominatorMetric());
        feeder.accept(this.grammarAccess.getRatioMetricAccess().getDenominatorMetricMetricMYIDParserRuleCall_4_0_1(),
                semanticObject.getDenominatorMetric());
        feeder.finish();
    }

    /**
     * Constraint: (innerMetric=[Metric|MYID] steps+=BoundAndWeight*)
     */
    protected void sequence_StepwiseMetric(final EObject context, final StepwiseMetric semanticObject) {
        this.genericSequencer.createSequence(context, semanticObject);
    }

    /**
     * Constraint: weights+=MetricAndWeight+
     */
    protected void sequence_WeightedMetric(final EObject context, final WeightedMetric semanticObject) {
        this.genericSequencer.createSequence(context, semanticObject);
    }
}
