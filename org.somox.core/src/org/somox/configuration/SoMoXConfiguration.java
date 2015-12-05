package org.somox.configuration;

import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

import org.somox.filter.BlacklistFilter;

import de.uka.ipd.sdq.workflow.configuration.AbstractComposedJobConfiguration;
import de.uka.ipd.sdq.workflow.configuration.IJobConfiguration;

public class SoMoXConfiguration extends AbstractComposedJobConfiguration implements IJobConfiguration {

    public static final String SOMOX_WILDCARD_DELIMITER = "§";

    // values from properties file
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
     *
     * @param preferences
     *            Preferences for this analyzer
     * @return A list of blacklist entries
     */
    public Set<String> getBlacklist() {
        final String wildcardString = this.rawBlacklist;

        final StringTokenizer tokenizer = new StringTokenizer(wildcardString, SOMOX_WILDCARD_DELIMITER);

        final Set<String> blacklist = new HashSet<String>();
        while (tokenizer.hasMoreElements()) {
            blacklist.add(tokenizer.nextToken());
        }
        return blacklist;
    }

    public void setWildcardKey(final String wildcardKey, final String additionalWildcards) {
        this.rawBlacklist = wildcardKey;
        final Set<String> wildCardList = this.getBlacklist();
        if (additionalWildcards != null && additionalWildcards.length() > 0) {
            wildCardList.add(additionalWildcards);
        }
        this.blacklistFilter = new BlacklistFilter(wildCardList);
    }

    /**
     * @return the blacklistFilter
     */
    public BlacklistFilter getBlacklistFilter() {
        if (this.blacklistFilter == null) {
            throw new IllegalArgumentException("Must set blacklist pattern first.");
        }
        return this.blacklistFilter;
    }

    public String getExcludedPrefixesForNameResemblance() {
        return this.excludedPrefixesForNameResemblance;
    }

    public void setExcludedPrefixesForNameResemblance(final String excludedPrefixesForNameResemblance) {
        this.excludedPrefixesForNameResemblance = excludedPrefixesForNameResemblance;
    }

    public String getExcludedSuffixesForNameResemblance() {
        return this.excludedSuffixesForNameResemblance;
    }

    public void setExcludedSuffixesForNameResemblance(final String excludedSuffixesForNameResemblance) {
        this.excludedSuffixesForNameResemblance = excludedSuffixesForNameResemblance;
    }

    public double getWeightPackageMapping() {
        return this.weightPackageMapping;
    }

    public void setWeightPackageMapping(final double weightPackageMapping) {
        this.weightPackageMapping = weightPackageMapping;
    }

    public double getWeightDirectoryMapping() {
        return this.weightDirectoryMapping;
    }

    public void setWeightDirectoryMapping(final double weightDirectoryMapping) {
        this.weightDirectoryMapping = weightDirectoryMapping;
    }

    public double getWeightDMS() {
        return this.weightDMS;
    }

    public void setWeightDMS(final double weightDMS) {
        this.weightDMS = weightDMS;
    }

    public double getWeightLowCoupling() {
        return this.weightLowCoupling;
    }

    public void setWeightLowCoupling(final double weightLowCoupling) {
        this.weightLowCoupling = weightLowCoupling;
    }

    public double getWeightHighCoupling() {
        return this.weightHighCoupling;
    }

    public void setWeightHighCoupling(final double weightHighCoupling) {
        this.weightHighCoupling = weightHighCoupling;
    }

    public double getWeightLowNameResemblance() {
        return this.weightLowNameResemblance;
    }

    public void setWeightLowNameResemblance(final double weightLowNameResemblance) {
        this.weightLowNameResemblance = weightLowNameResemblance;
    }

    public double getWeightMidNameResemblance() {
        return this.weightMidNameResemblance;
    }

    public void setWeightMidNameResemblance(final double weightMidNameResemblance) {
        this.weightMidNameResemblance = weightMidNameResemblance;
    }

    public double getWeightHighNameResemblance() {
        return this.weightHighNameResemblance;
    }

    public void setWeightHighNameResemblance(final double weightHighNameResemblance) {
        this.weightHighNameResemblance = weightHighNameResemblance;
    }

    public double getWeightHighestNameResemblance() {
        return this.weightHighestNameResemblance;
    }

    public void setWeightHighestNameResemblance(final double weightHighestNameResemblance) {
        this.weightHighestNameResemblance = weightHighestNameResemblance;
    }

    public double getWeightInterfaceViolationRelevant() {
        return this.weightInterfaceViolationRelevant;
    }

    public void setWeightInterfaceViolationRelevant(final double weightInterfaceViolationRelevant) {
        this.weightInterfaceViolationRelevant = weightInterfaceViolationRelevant;
    }

    public double getWeightInterfaceViolationIrrelevant() {
        return this.weightInterfaceViolationIrrelevant;
    }

    public void setWeightInterfaceViolationIrrelevant(final double weightInterfaceViolationIrrelevant) {
        this.weightInterfaceViolationIrrelevant = weightInterfaceViolationIrrelevant;
    }

    public double getWeightHighSLAQ() {
        return this.weightHighSLAQ;
    }

    public void setWeightHighSLAQ(final double weightHighSLAQ) {
        this.weightHighSLAQ = weightHighSLAQ;
    }

    public double getWeightLowSLAQ() {
        return this.weightLowSLAQ;
    }

    public void setWeightLowSLAQ(final double weightLowSLAQ) {
        this.weightLowSLAQ = weightLowSLAQ;
    }

    /**
     * @return the clusteringConfig
     */
    public ClusteringConfiguration getClusteringConfig() {
        return this.clusteringConfig;
    }

    /**
     * @return the locations
     */
    public FileLocationConfiguration getFileLocations() {
        return this.locations;
    }

    /**
     * Switch for interface reverse engineering. Serves for debugging-like use of SoMoX.
     *
     * @return
     */
    public boolean isReverseEngineerInterfacesNotAssignedToComponent() {
        return this.reverseEngineerInterfacesNotAssignedToComponent;
    }

    /**
     * Switch for interface reverse engineering. Serves for debugging-like use of SoMoX.
     *
     * @param reverseEngineerInterfacesNotAssignedToComponent
     */
    public void setReverseEngineerInterfacesNotAssignedToComponent(
            final boolean reverseEngineerInterfacesNotAssignedToComponent) {
        this.reverseEngineerInterfacesNotAssignedToComponent = reverseEngineerInterfacesNotAssignedToComponent;
    }

}
