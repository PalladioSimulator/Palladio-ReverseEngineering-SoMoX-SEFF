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
 * Function calculating weights indicating the merge of
 * two component candidates.
 * <br>
 * Related to {@link DefaultMergeIndicatingMetric}. There required metrics are listed.
 * @author Klaus Krogmann
 */
public class MergeIndicationFunction implements ICompositionFunction {
	
	private SoMoXConfiguration somoxConfig;
	private static Logger logger = Logger.getLogger(MergeIndicationFunction.class);
	
	private double packageMappingWeight, directoryMappingWeight;

	/**
	 * 
	 * @param somoxConfiguration
	 */
	public MergeIndicationFunction(SoMoXConfiguration somoxConfiguration) {
		super();
		
		this.somoxConfig=somoxConfiguration;
		getWeightsFromConfiguration();
	}

	public double computeOverallDirectedMetricValue(
			Map<MetricID, Double> metricValues) {
		
		if (metricValues == null || metricValues.size() == 0)
			throw new IllegalArgumentException("Metric not set");

		double nameResemblance = metricValues.get(NameResemblance.METRIC_ID);
		double subsystemComponent = metricValues.get(SubsystemComponent.METRIC_ID);
		double packageMapping = metricValues.get(PackageMapping.METRIC_ID);
		double directoryMapping = metricValues.get(DirectoryMapping.METRIC_ID);
		double slaq = metricValues.get(SliceLayerArchitectureQuality.METRIC_ID);
		double coupling = metricValues.get(Coupling.METRIC_ID);
		double interfaceAdherence = metricValues.get(AdherenceToInterfaceCommunication.METRIC_ID);
		
		double nameResemblanceAfterCoupling = getNameResemblance(nameResemblance, coupling);
		double interfaceAdherenceWeight = getInterfaceAdherenceWeight(coupling, interfaceAdherence);
		double subsystemComponentWeight = getSubsystemComponentWeight(slaq);
		
		//compute overall metric score		
		//TODO: check use of constants (packageMappingWeight, ...)
		//TODO: Klaus, check maximum function usle here...
		double sum = getMaxNameResemblanceWeigth()+interfaceAdherenceWeight+subsystemComponentWeight+packageMappingWeight+directoryMappingWeight;
		if(sum == 0) {
			throw new RuntimeException("The weight of all metrics must not be 0!");
		}
		if (logger.isTraceEnabled()) {
			logger.trace("Name resemblance = "+nameResemblance+" * "+nameResemblanceAfterCoupling +" = "+nameResemblance*nameResemblanceAfterCoupling);
			logger.trace("Interface violation = "+interfaceAdherence+" * "+interfaceAdherenceWeight +" = "+interfaceAdherence*interfaceAdherenceWeight);
			logger.trace("Subsystem component = "+subsystemComponent+" * "+subsystemComponentWeight +" = "+subsystemComponent*subsystemComponentWeight);
			logger.trace("Package mapping = "+packageMapping+" * "+packageMappingWeight+" = "+packageMapping*packageMappingWeight);
			logger.trace("Directory mapping = "+directoryMapping+" * "+directoryMappingWeight+" = "+directoryMapping*directoryMappingWeight);
		}
		double score = nameResemblanceAfterCoupling - 
					   interfaceAdherence*interfaceAdherenceWeight + //interface adherence negative for merge 
					   subsystemComponent*subsystemComponentWeight + 
					   packageMapping*packageMappingWeight + 
					   directoryMapping*directoryMappingWeight;
		
		score = score/sum;
		
		logger.trace("Overall computed metric: "+score);
		
		return score;
	}

	private double getMaxNameResemblanceWeigth() {
		double result = Math.max(somoxConfig.getWeightLowCoupling(),somoxConfig.getWeightHighCoupling());
		result = Math.max(result,somoxConfig.getWeightLowNameResemblance());
		result = Math.max(result,somoxConfig.getWeightMidNameResemblance());
		result = Math.max(result,somoxConfig.getWeightHighNameResemblance());
		result = Math.max(result,somoxConfig.getWeightHighestNameResemblance());
		
		return result;
	}
	
	private double getNameResemblance(double nameResemblance,
			double coupling) {

		//determine nameResemblance
		if (coupling >= 0 && coupling < 0.2) {
			return somoxConfig.getWeightLowNameResemblance() * nameResemblance; // TODO make 0 config (Ci)
		} else if (coupling >= 0.2 && coupling < 0.7) {
			return somoxConfig.getWeightMidNameResemblance() * nameResemblance; 
		} else if (coupling >= 0.7 && coupling < 0.9) {
			return somoxConfig.getWeightHighNameResemblance() * nameResemblance;
		} else {
			return somoxConfig.getWeightHighestNameResemblance() * nameResemblance;
		}
	}

	private double getInterfaceAdherenceWeight(
			double coupling,
			double interfaceAdherence) {
		// TODO: What to do here after changing from interface violation to interface adherence??
		if (coupling >=0.3) {
			if(interfaceAdherence > 0.0) {
				return somoxConfig.getWeightInterfaceViolationRelevant();
			} else {
				return -1 * somoxConfig.getWeightInterfaceViolationIrrelevant(); //negative to prefer merge of component which bypass interfaces
			}			
		} else {
			// no coupling
			return 0.0;
		}
	}

	private double getSubsystemComponentWeight(double slaq) {
		if (slaq >= 0.5) {
			return somoxConfig.getWeightHighSLAQ();
		} else {
			return somoxConfig.getWeightLowSLAQ();
		}
	}

	private void getWeightsFromConfiguration() {
		packageMappingWeight = somoxConfig.getWeightPackageMapping();
		directoryMappingWeight = somoxConfig.getWeightDirectoryMapping();
	}	
	
	protected AbstractMetric getMetric(Map<String, AbstractMetric> allMetrics, String metricId) {
		AbstractMetric result = allMetrics.get(metricId);
		
		if (result == null)
			throw new RuntimeException("Configuration error, Metric "+metricId+" needed but not available");
		
		return result;
	}
}
