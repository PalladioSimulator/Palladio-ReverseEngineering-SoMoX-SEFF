package org.somox.analyzer.simplemodelanalyzer.metrics;

import java.util.Map;

import org.apache.log4j.Logger;
import org.somox.configuration.SoMoXConfiguration;
import org.somox.metrics.ICompositionFunction;
import org.somox.metrics.MetricID;
import org.somox.metrics.abstractmetrics.AbstractMetric;
import org.somox.metrics.hierarchy.DirectoryMapping;
import org.somox.metrics.hierarchy.PackageMapping;
import org.somox.metrics.naming.NameResemblance;
import org.somox.metrics.ratio.AdherenceToInterfaceCommunication;
import org.somox.metrics.ratio.Coupling;
import org.somox.metrics.structure.SliceLayerArchitectureQuality;
import org.somox.metrics.structure.SubsystemComponent;

/**
 * Function calculating weights indicating the merge of two component candidates. <br>
 * Related to {@link DefaultMergeIndicatingMetric}. There required metrics are listed.
 *
 * @author Klaus Krogmann
 */
public class MergeIndicationFunction implements ICompositionFunction {

    private final SoMoXConfiguration somoxConfig;
    private static Logger logger = Logger.getLogger(MergeIndicationFunction.class);

    private double packageMappingWeight, directoryMappingWeight;

    /**
     *
     * @param somoxConfiguration
     */
    public MergeIndicationFunction(final SoMoXConfiguration somoxConfiguration) {
        super();

        this.somoxConfig = somoxConfiguration;
        this.getWeightsFromConfiguration();
    }

    @Override
    public double computeOverallDirectedMetricValue(final Map<MetricID, Double> metricValues) {

        if (metricValues == null || metricValues.size() == 0) {
            throw new IllegalArgumentException("Metric not set");
        }

        final double nameResemblance = metricValues.get(NameResemblance.METRIC_ID);
        final double subsystemComponent = metricValues.get(SubsystemComponent.METRIC_ID);
        final double packageMapping = metricValues.get(PackageMapping.METRIC_ID);
        final double directoryMapping = metricValues.get(DirectoryMapping.METRIC_ID);
        final double slaq = metricValues.get(SliceLayerArchitectureQuality.METRIC_ID);
        final double coupling = metricValues.get(Coupling.METRIC_ID);
        final double interfaceAdherence = metricValues.get(AdherenceToInterfaceCommunication.METRIC_ID);

        final double nameResemblanceAfterCoupling = this.getNameResemblance(nameResemblance, coupling);
        final double interfaceAdherenceWeight = this.getInterfaceAdherenceWeight(coupling, interfaceAdherence);
        final double subsystemComponentWeight = this.getSubsystemComponentWeight(slaq);

        // compute overall metric score
        // TODO: check use of constants (packageMappingWeight, ...)
        // TODO: Klaus, check maximum function usle here...
        final double sum = this.getMaxNameResemblanceWeigth() + interfaceAdherenceWeight + subsystemComponentWeight
                + this.packageMappingWeight + this.directoryMappingWeight;
        if (sum == 0) {
            throw new RuntimeException("The weight of all metrics must not be 0!");
        }
        if (logger.isTraceEnabled()) {
            logger.trace("Name resemblance = " + nameResemblance + " * " + nameResemblanceAfterCoupling + " = "
                    + nameResemblance * nameResemblanceAfterCoupling);
            logger.trace("Interface violation = " + interfaceAdherence + " * " + interfaceAdherenceWeight + " = "
                    + interfaceAdherence * interfaceAdherenceWeight);
            logger.trace("Subsystem component = " + subsystemComponent + " * " + subsystemComponentWeight + " = "
                    + subsystemComponent * subsystemComponentWeight);
            logger.trace("Package mapping = " + packageMapping + " * " + this.packageMappingWeight + " = "
                    + packageMapping * this.packageMappingWeight);
            logger.trace("Directory mapping = " + directoryMapping + " * " + this.directoryMappingWeight + " = "
                    + directoryMapping * this.directoryMappingWeight);
        }
        double score = nameResemblanceAfterCoupling - interfaceAdherence * interfaceAdherenceWeight + // interface
                                                                                                      // adherence
                                                                                                      // negative
                                                                                                      // for
                                                                                                      // merge
        subsystemComponent * subsystemComponentWeight + packageMapping * this.packageMappingWeight
                + directoryMapping * this.directoryMappingWeight;

        score = score / sum;

        logger.trace("Overall computed metric: " + score);

        return score;
    }

    private double getMaxNameResemblanceWeigth() {
        double result = Math.max(this.somoxConfig.getWeightLowCoupling(), this.somoxConfig.getWeightHighCoupling());
        result = Math.max(result, this.somoxConfig.getWeightLowNameResemblance());
        result = Math.max(result, this.somoxConfig.getWeightMidNameResemblance());
        result = Math.max(result, this.somoxConfig.getWeightHighNameResemblance());
        result = Math.max(result, this.somoxConfig.getWeightHighestNameResemblance());

        return result;
    }

    private double getNameResemblance(final double nameResemblance, final double coupling) {

        // determine nameResemblance
        if (coupling >= 0 && coupling < 0.2) {
            return this.somoxConfig.getWeightLowNameResemblance() * nameResemblance; // TODO make 0
            // config (Ci)
        } else if (coupling >= 0.2 && coupling < 0.7) {
            return this.somoxConfig.getWeightMidNameResemblance() * nameResemblance;
        } else if (coupling >= 0.7 && coupling < 0.9) {
            return this.somoxConfig.getWeightHighNameResemblance() * nameResemblance;
        } else {
            return this.somoxConfig.getWeightHighestNameResemblance() * nameResemblance;
        }
    }

    private double getInterfaceAdherenceWeight(final double coupling, final double interfaceAdherence) {
        // TODO: What to do here after changing from interface violation to interface adherence??
        if (coupling >= 0.3) {
            if (interfaceAdherence > 0.0) {
                return this.somoxConfig.getWeightInterfaceViolationRelevant();
            } else {
                return -1 * this.somoxConfig.getWeightInterfaceViolationIrrelevant(); // negative to
                // prefer merge of
                // component which
                // bypass
                // interfaces
            }
        } else {
            // no coupling
            return 0.0;
        }
    }

    private double getSubsystemComponentWeight(final double slaq) {
        if (slaq >= 0.5) {
            return this.somoxConfig.getWeightHighSLAQ();
        } else {
            return this.somoxConfig.getWeightLowSLAQ();
        }
    }

    private void getWeightsFromConfiguration() {
        this.packageMappingWeight = this.somoxConfig.getWeightPackageMapping();
        this.directoryMappingWeight = this.somoxConfig.getWeightDirectoryMapping();
    }

    protected AbstractMetric getMetric(final Map<String, AbstractMetric> allMetrics, final String metricId) {
        final AbstractMetric result = allMetrics.get(metricId);

        if (result == null) {
            throw new RuntimeException("Configuration error, Metric " + metricId + " needed but not available");
        }

        return result;
    }
}
