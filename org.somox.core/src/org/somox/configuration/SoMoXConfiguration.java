package org.somox.configuration;

import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

import org.somox.filter.BlacklistFilter;

import de.uka.ipd.sdq.workflow.configuration.AbstractComposedJobConfiguration;
import de.uka.ipd.sdq.workflow.configuration.IJobConfiguration;

public class SoMoXConfiguration extends AbstractComposedJobConfiguration
		implements IJobConfiguration {

	public static final String SOMOX_WILDCARD_DELIMITER = "§";
	
	//values from properties file
	private double weightPackageMapping;
	private double weightDirectoryMapping;
	private double weightDMS;
	private double weightLowCoupling;
	private double weightHighCoupling;
	private double weightLowNameResemblance;
	private double weightMidNameResemblance;
	private double weightHighNameResemblance;
	private double weightHighestNameResemblance;
	private double weightInterfaceViolationRelevant;
	private double weightInterfaceViolationIrrelevant;
	private double weightHighSLAQ;
	private double weightLowSLAQ;
	
	private String rawBlacklist;
	private String excludedPrefixesForNameResemblance;
	private String excludedSuffixesForNameResemblance;
	private BlacklistFilter blacklistFilter = null;
	
	private boolean reverseEngineerInterfacesNotAssignedToComponent;

	private final ClusteringConfiguration clusteringConfig = new ClusteringConfiguration();
	private final FileLocationConfiguration locations = new FileLocationConfiguration();
	
	public SoMoXConfiguration() {
		super();
	}
	
	@Override
	public String getErrorMessage() {
		return "An error in SoMoX occured";
	}

	/**
	 * Create a new list of blacklist specifications
	 * @param preferences Preferences for this analyzer
	 * @return A list of blacklist entries
	 */
	public Set<String> getBlacklist() {
		String wildcardString = this.rawBlacklist;

		StringTokenizer tokenizer = new StringTokenizer(wildcardString, SOMOX_WILDCARD_DELIMITER);
		
		Set<String> blacklist = new HashSet<String>();
		while(tokenizer.hasMoreElements())
			blacklist.add(tokenizer.nextToken());
		return blacklist;
	}

	public void setWildcardKey(String wildcardKey, String additionalWildcards) {
		this.rawBlacklist = wildcardKey;
		Set<String> wildCardList = getBlacklist();
		if (additionalWildcards != null && additionalWildcards.length() > 0) {
			wildCardList.add(additionalWildcards);
		}
		this.blacklistFilter = new BlacklistFilter(wildCardList);
	}

	/**
	 * @return the blacklistFilter
	 */
	public BlacklistFilter getBlacklistFilter() {
		if (blacklistFilter == null)
			throw new IllegalArgumentException("Must set blacklist pattern first.");
		return blacklistFilter;
	}

	public String getExcludedPrefixesForNameResemblance() {
		return excludedPrefixesForNameResemblance;
	}

	public void setExcludedPrefixesForNameResemblance(
			String excludedPrefixesForNameResemblance) {
		this.excludedPrefixesForNameResemblance = excludedPrefixesForNameResemblance;
	}

	public String getExcludedSuffixesForNameResemblance() {
		return excludedSuffixesForNameResemblance;
	}

	public void setExcludedSuffixesForNameResemblance(
			String excludedSuffixesForNameResemblance) {
		this.excludedSuffixesForNameResemblance = excludedSuffixesForNameResemblance;
	}

	public double getWeightPackageMapping() {
		return weightPackageMapping;
	}

	public void setWeightPackageMapping(double weightPackageMapping) {
		this.weightPackageMapping = weightPackageMapping;
	}

	public double getWeightDirectoryMapping() {
		return weightDirectoryMapping;
	}

	public void setWeightDirectoryMapping(double weightDirectoryMapping) {
		this.weightDirectoryMapping = weightDirectoryMapping;
	}

	public double getWeightDMS() {
		return weightDMS;
	}

	public void setWeightDMS(double weightDMS) {
		this.weightDMS = weightDMS;
	}

	public double getWeightLowCoupling() {
		return weightLowCoupling;
	}

	public void setWeightLowCoupling(double weightLowCoupling) {
		this.weightLowCoupling = weightLowCoupling;
	}

	public double getWeightHighCoupling() {
		return weightHighCoupling;
	}

	public void setWeightHighCoupling(double weightHighCoupling) {
		this.weightHighCoupling = weightHighCoupling;
	}

	public double getWeightLowNameResemblance() {
		return weightLowNameResemblance;
	}

	public void setWeightLowNameResemblance(double weightLowNameResemblance) {
		this.weightLowNameResemblance = weightLowNameResemblance;
	}

	public double getWeightMidNameResemblance() {
		return weightMidNameResemblance;
	}

	public void setWeightMidNameResemblance(double weightMidNameResemblance) {
		this.weightMidNameResemblance = weightMidNameResemblance;
	}

	public double getWeightHighNameResemblance() {
		return weightHighNameResemblance;
	}

	public void setWeightHighNameResemblance(double weightHighNameResemblance) {
		this.weightHighNameResemblance = weightHighNameResemblance;
	}

	public double getWeightHighestNameResemblance() {
		return weightHighestNameResemblance;
	}

	public void setWeightHighestNameResemblance(double weightHighestNameResemblance) {
		this.weightHighestNameResemblance = weightHighestNameResemblance;
	}

	public double getWeightInterfaceViolationRelevant() {
		return weightInterfaceViolationRelevant;
	}

	public void setWeightInterfaceViolationRelevant(
			double weightInterfaceViolationRelevant) {
		this.weightInterfaceViolationRelevant = weightInterfaceViolationRelevant;
	}

	public double getWeightInterfaceViolationIrrelevant() {
		return weightInterfaceViolationIrrelevant;
	}

	public void setWeightInterfaceViolationIrrelevant(
			double weightInterfaceViolationIrrelevant) {
		this.weightInterfaceViolationIrrelevant = weightInterfaceViolationIrrelevant;
	}

	public double getWeightHighSLAQ() {
		return weightHighSLAQ;
	}

	public void setWeightHighSLAQ(double weightHighSLAQ) {
		this.weightHighSLAQ = weightHighSLAQ;
	}

	public double getWeightLowSLAQ() {
		return weightLowSLAQ;
	}

	public void setWeightLowSLAQ(double weightLowSLAQ) {
		this.weightLowSLAQ = weightLowSLAQ;
	}

	/**
	 * @return the clusteringConfig
	 */
	public ClusteringConfiguration getClusteringConfig() {
		return clusteringConfig;
	}

	/**
	 * @return the locations
	 */
	public FileLocationConfiguration getFileLocations() {
		return locations;
	}

	/**
	 * Switch for interface reverse engineering. Serves for debugging-like
	 * use of SoMoX.
	 * @return
	 */
	public boolean isReverseEngineerInterfacesNotAssignedToComponent() {
		return reverseEngineerInterfacesNotAssignedToComponent;
	}

	/**
	 * Switch for interface reverse engineering. Serves for debugging-like
	 * use of SoMoX.
	 * @param reverseEngineerInterfacesNotAssignedToComponent
	 */
	public void setReverseEngineerInterfacesNotAssignedToComponent(
			boolean reverseEngineerInterfacesNotAssignedToComponent) {
		this.reverseEngineerInterfacesNotAssignedToComponent = reverseEngineerInterfacesNotAssignedToComponent;
	}

}
