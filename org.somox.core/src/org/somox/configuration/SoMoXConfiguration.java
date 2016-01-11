package org.somox.configuration;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;
import org.somox.filter.BlacklistFilter;

import de.uka.ipd.sdq.workflow.configuration.AbstractComposedJobConfiguration;
import de.uka.ipd.sdq.workflow.configuration.IJobConfiguration;

/**
 * SoMoX’ central configuration.
 * <h4>attribute map</h4> This configuration has a bijective representation as an
 * <em>attribute map</em>. This is a {@code Map<String, Object>} containing the class’ attribute
 * constants as keys and the configuration’s attributes as values. The value’s type is the same as
 * the corresponding setter’s return type. A value of {@code null} is interpreted as “not defined”
 * just like if the map did not contain the key in question.
 * <p>
 * For a mapping of attribute constants to getters and setters, see their documentation. An
 * <em>attribute map</em> can be obtained by {@link #toMap()} and be converted into a
 * {@code SoMoXConfiguration} by {@link #SoMoXConfiguration(Map)}.
 * 
 * @author Unknown
 * @author Joshua Gleitze
 *
 */
public class SoMoXConfiguration extends AbstractComposedJobConfiguration implements IJobConfiguration {
    private static Logger logger = Logger.getLogger(SoMoXConfiguration.class);

    public static final String BLACKLIST_CONFIGURATION_WILDCARDS_ADDITIONAL = "org.somox.metrics.wildcards.additional";
    public static final String SOMOX_ANALYZER_INPUT_FILE = "org.somox.analyzer.inputfile";
    public static final String SOMOX_ANALYZER_REVERSE_ENGINEER_INTERFACES_NOT_ASSIGNED_TO_INTERFACES =
            "org.somox.analyzer.ReverseEngineerInterfacesNotAssignedToComponent";
    public static final String SOMOX_ANALYZER_WILDCARD_KEY = "org.somox.metrics.wildcards";
    public static final String SOMOX_EXCLUDED_PREFIXES = "org.somox.metrics.nameResemblance.excludedPrefixes";
    public static final String SOMOX_EXCLUDED_SUFFIXES = "org.somox.metrics.nameResemblance.excludedSuffixes";
    public static final String SOMOX_PROJECT_NAME = "org.somox.project";
    public static final String SOMOX_WEIGHT_CLUSTERING_THRESHOLD_DECREMENT_COMPOSE =
            "org.somox.clusteringThresholdDecrement.Compose";
    public static final String SOMOX_WEIGHT_CLUSTERING_THRESHOLD_DECREMENT_MERGE =
            "org.somox.clusteringThresholdDecrement.Merge";
    public static final String SOMOX_WEIGHT_CLUSTERING_THRESHOLD_MAX_COMPOSE =
            "org.somox.clusteringThresholdMax.Compose";
    public static final String SOMOX_WEIGHT_CLUSTERING_THRESHOLD_MAX_MERGE = "org.somox.clusteringThresholdMax.Merge";
    public static final String SOMOX_WEIGHT_CLUSTERING_THRESHOLD_MIN_COMPOSE =
            "org.somox.clusteringThresholdMin.Compose";
    public static final String SOMOX_WEIGHT_CLUSTERING_THRESHOLD_MIN_MERGE = "org.somox.clusteringThresholdMin.Merge";
    public static final String SOMOX_WEIGHT_DIRECTORY_MAPPING = "org.somox.directoryMapping.weightDirectoryMapping";
    public static final String SOMOX_WEIGHT_DMS = "org.somox.dms.weightDMS";
    public static final String SOMOX_WEIGHT_HIGH_COUPLING = "org.somox.nameResemblance.weightHighCoupling";
    public static final String SOMOX_WEIGHT_HIGH_NAME_RESEMBLANCE =
            "org.somox.nameResemblance.weightHighNameResemblance";
    public static final String SOMOX_WEIGHT_HIGH_SLAQ = "org.somox.subsystemComponent.weightHighSLAQ";
    public static final String SOMOX_WEIGHT_HIGHEST_NAME_RESEMBLANCE =
            "org.somox.nameResemblance.weightHighestNameResemblance";
    public static final String SOMOX_WEIGHT_INTERFACE_VIOLATION_IRRELEVANT =
            "org.somox.interfaceViolation.weightInterfaceViolationIrrelevant";
    public static final String SOMOX_WEIGHT_INTERFACE_VIOLATION_RELEVANT =
            "org.somox.interfaceViolation.weightInterfaceViolationRelevant";
    public static final String SOMOX_WEIGHT_LOW_COUPLING = "org.somox.nameResemblance.weightLowCoupling";
    public static final String SOMOX_WEIGHT_LOW_NAME_RESEMBLANCE = "org.somox.nameResemblance.weightLowNameResemblance";
    public static final String SOMOX_WEIGHT_LOW_SLAQ = "org.somox.subsystemComponent.weightLowSLAQ";
    public static final String SOMOX_WEIGHT_MID_NAME_RESEMBLANCE = "org.somox.nameResemblance.weightMidNameResemblance";
    public static final String SOMOX_WEIGHT_PACKAGE_MAPPING = "org.somox.packageMapping.weightPackageMapping";
    public static final String SOMOX_WILDCARD_DELIMITER = "§";

    private String additionalWildcards = "";
    private BlacklistFilter blacklistFilter = null;
    private final ClusteringConfiguration clusteringConfig = new ClusteringConfiguration();
    private String excludedPrefixesForNameResemblance = "";
    private String excludedSuffixesForNameResemblance = "";
    private final FileLocationConfiguration locations = new FileLocationConfiguration();
    private boolean reverseEngineerInterfacesNotAssignedToComponent;
    private double weightDirectoryMapping;
    private double weightDMS;
    private double weightHighCoupling;
    private double weightHighestNameResemblance;
    private double weightHighNameResemblance;
    private double weightHighSLAQ;
    private double weightInterfaceViolationIrrelevant;
    private double weightInterfaceViolationRelevant;
    private double weightLowCoupling;
    private double weightLowNameResemblance;
    private double weightLowSLAQ;
    private double weightMidNameResemblance;
    private double weightPackageMapping;

    private String wildcardKey = "";

    /**
     * Creates a new SoMoX configuration initialized with default values.
     */
    public SoMoXConfiguration() {
        getFileLocations().setOutputFolder("/model");
    }

    /**
     * Creates a new SoMoX configuration initialized with the {@code attributeMap}’s values.
     * Attributes not defined in the map will be set to their default values.
     * 
     * @param attributeMap
     *            An <em>attribute map</em>, as defined in the class description. It does not need
     *            to contain all attributes.
     */
    public SoMoXConfiguration(Map<String, Object> attributeMap) {
        this();
        applyAttributeMap(attributeMap);
    }

    public void applyAttributeMap(Map<String, Object> attributeMap) {
        if (attributeMap == null) {
            return;
        }

        // Debug output
        logger.debug("SoMoX configuration extended by these attributes:");
        for (final Object key : attributeMap.keySet()) {
            final String keyname = key.toString();

            if (keyname.contains("org.somox")) {
                logger.debug(key + "=" + attributeMap.get(key));
            }

        }

        if (attributeMap.get(SOMOX_PROJECT_NAME) != null) {
            final String projectIdentifier = (String) attributeMap.get(SOMOX_PROJECT_NAME);
            this.getFileLocations().setProjectName(projectIdentifier);
        }

        if (attributeMap.get(SOMOX_ANALYZER_INPUT_FILE) != null) {
            final String inputfile = (String) attributeMap.get(SOMOX_ANALYZER_INPUT_FILE);
            this.getFileLocations().setAnalyserInputFile(inputfile);
        }

        if (attributeMap.get(SOMOX_ANALYZER_REVERSE_ENGINEER_INTERFACES_NOT_ASSIGNED_TO_INTERFACES) != null) {
            final boolean allInterfacesStrategy =
                    (Boolean) attributeMap.get(SOMOX_ANALYZER_REVERSE_ENGINEER_INTERFACES_NOT_ASSIGNED_TO_INTERFACES);
            this.setReverseEngineerInterfacesNotAssignedToComponent(allInterfacesStrategy);
        }

        if (attributeMap.get(SOMOX_ANALYZER_WILDCARD_KEY) != null) {
            final String wildcardkey = (String) attributeMap.get(SOMOX_ANALYZER_WILDCARD_KEY);
            final String additionalWildcardkey =
                    (String) attributeMap.get(BLACKLIST_CONFIGURATION_WILDCARDS_ADDITIONAL);
            this.setWildcardKey(wildcardkey, additionalWildcardkey);
        }

        // NameResemblanceTab
        if (attributeMap.get(SOMOX_EXCLUDED_PREFIXES) != null) {
            final String nameResemblancePrefixString = (String) attributeMap.get(SOMOX_EXCLUDED_PREFIXES);
            this.setExcludedPrefixesForNameResemblance(nameResemblancePrefixString);
        }

        if (attributeMap.get(SOMOX_EXCLUDED_SUFFIXES) != null) {
            final String nameResemblanceSuffixString = (String) attributeMap.get(SOMOX_EXCLUDED_SUFFIXES);
            this.setExcludedSuffixesForNameResemblance(nameResemblanceSuffixString);
        }

        if (attributeMap.get(SOMOX_WEIGHT_DIRECTORY_MAPPING) != null) {
            setWeightDirectoryMapping(Double.parseDouble((String) attributeMap.get(SOMOX_WEIGHT_DIRECTORY_MAPPING)));
        }

        if (attributeMap.get(SOMOX_WEIGHT_DMS) != null) {
            setWeightDMS(Double.parseDouble((String) attributeMap.get(SOMOX_WEIGHT_DMS)));
        }

        if (attributeMap.get(SOMOX_WEIGHT_HIGH_COUPLING) != null) {
            setWeightHighCoupling(Double.parseDouble((String) attributeMap.get(SOMOX_WEIGHT_HIGH_COUPLING)));
        }

        if (attributeMap.get(SOMOX_WEIGHT_HIGHEST_NAME_RESEMBLANCE) != null) {
            setWeightHighestNameResemblance(
                    Double.parseDouble((String) attributeMap.get(SOMOX_WEIGHT_HIGHEST_NAME_RESEMBLANCE)));
        }

        if (SOMOX_WEIGHT_HIGH_NAME_RESEMBLANCE != null) {
            setWeightHighNameResemblance(
                    Double.parseDouble((String) attributeMap.get(SOMOX_WEIGHT_HIGH_NAME_RESEMBLANCE)));
        }

        if (attributeMap.get(SOMOX_WEIGHT_HIGH_SLAQ) != null) {
            setWeightHighSLAQ(Double.parseDouble((String) attributeMap.get(SOMOX_WEIGHT_HIGH_SLAQ)));
        }

        if (attributeMap.get(SOMOX_WEIGHT_INTERFACE_VIOLATION_IRRELEVANT) != null) {
            setWeightInterfaceViolationIrrelevant(
                    Double.parseDouble((String) attributeMap.get(SOMOX_WEIGHT_INTERFACE_VIOLATION_IRRELEVANT)));
        }

        if (attributeMap.get(SOMOX_WEIGHT_INTERFACE_VIOLATION_RELEVANT) != null) {
            setWeightInterfaceViolationRelevant(
                    Double.parseDouble((String) attributeMap.get(SOMOX_WEIGHT_INTERFACE_VIOLATION_RELEVANT)));
        }

        if (attributeMap.get(SOMOX_WEIGHT_LOW_COUPLING) != null) {
            setWeightLowCoupling(Double.parseDouble((String) attributeMap.get(SOMOX_WEIGHT_LOW_COUPLING)));
        }

        if (attributeMap.get(SOMOX_WEIGHT_LOW_NAME_RESEMBLANCE) != null) {
            setWeightLowNameResemblance(
                    Double.parseDouble((String) attributeMap.get(SOMOX_WEIGHT_LOW_NAME_RESEMBLANCE)));
        }

        if (attributeMap.get(SOMOX_WEIGHT_LOW_SLAQ) != null) {
            setWeightLowSLAQ(Double.parseDouble((String) attributeMap.get(SOMOX_WEIGHT_LOW_SLAQ)));
        }

        if (attributeMap.get(SOMOX_WEIGHT_MID_NAME_RESEMBLANCE) != null) {
            setWeightMidNameResemblance(
                    Double.parseDouble((String) attributeMap.get(SOMOX_WEIGHT_MID_NAME_RESEMBLANCE)));
        }

        if (attributeMap.get(SOMOX_WEIGHT_PACKAGE_MAPPING) != null) {
            setWeightPackageMapping(Double.parseDouble((String) attributeMap.get(SOMOX_WEIGHT_PACKAGE_MAPPING)));
        }

        createClusteringConfiguration(attributeMap);
    }

    private void createClusteringConfiguration(final Map<String, Object> attributeMap) {
        final ClusteringConfiguration clusteringConfiguration = getClusteringConfig();
        // COMPOSE
        clusteringConfiguration.setMaxComposeClusteringThreshold(
                Double.parseDouble((String) attributeMap.get(SOMOX_WEIGHT_CLUSTERING_THRESHOLD_MAX_COMPOSE)) / 100.0d);
        clusteringConfiguration.setMinComposeClusteringThreshold(
                Double.parseDouble((String) attributeMap.get(SOMOX_WEIGHT_CLUSTERING_THRESHOLD_MIN_COMPOSE)) / 100.0d);
        clusteringConfiguration.setClusteringComposeThresholdDecrement(
                Double.parseDouble((String) attributeMap.get(SOMOX_WEIGHT_CLUSTERING_THRESHOLD_DECREMENT_COMPOSE))
                        / 100.0d);

        // MERGE
        clusteringConfiguration.setMaxMergeClusteringThreshold(
                Double.parseDouble((String) attributeMap.get(SOMOX_WEIGHT_CLUSTERING_THRESHOLD_MAX_MERGE)) / 100.0d);
        clusteringConfiguration.setMinMergeClusteringThreshold(
                Double.parseDouble((String) attributeMap.get(SOMOX_WEIGHT_CLUSTERING_THRESHOLD_MIN_MERGE)) / 100.0d);
        clusteringConfiguration.setClusteringMergeThresholdDecrement(
                Double.parseDouble((String) attributeMap.get(SOMOX_WEIGHT_CLUSTERING_THRESHOLD_DECREMENT_MERGE))
                        / 100.0d);

    }

    public String getAdditionalWildcards() {
        return additionalWildcards;
    }

    /**
     * Create a new list of blacklist specifications
     *
     * @return A set of blacklist entries
     */
    public Set<String> getBlacklist() {
        final String wildcardString = this.wildcardKey;

        final StringTokenizer tokenizer = new StringTokenizer(wildcardString, SOMOX_WILDCARD_DELIMITER);

        final Set<String> blacklist = new HashSet<String>();
        while (tokenizer.hasMoreElements()) {
            blacklist.add(tokenizer.nextToken());
        }
        return blacklist;
    }

    /**
     * @return the {@link BlacklistFilter} or {@code null} if
     *         {@link #setWildcardKey(String, String)} has not been called yet.
     */
    public BlacklistFilter getBlacklistFilter() {
        return this.blacklistFilter;
    }

    /**
     * @return the clusteringConfig
     */
    public ClusteringConfiguration getClusteringConfig() {
        return this.clusteringConfig;
    }

    @Override
    public String getErrorMessage() {
        return "An error in SoMoX occured";
    }

    public String getExcludedPrefixesForNameResemblance() {
        return this.excludedPrefixesForNameResemblance;
    }

    public String getExcludedSuffixesForNameResemblance() {
        return this.excludedSuffixesForNameResemblance;
    }

    /**
     * @return the locations
     */
    public FileLocationConfiguration getFileLocations() {
        return this.locations;
    }

    public double getWeightDirectoryMapping() {
        return this.weightDirectoryMapping;
    }

    public double getWeightDMS() {
        return this.weightDMS;
    }

    public double getWeightHighCoupling() {
        return this.weightHighCoupling;
    }

    public double getWeightHighestNameResemblance() {
        return this.weightHighestNameResemblance;
    }

    public double getWeightHighNameResemblance() {
        return this.weightHighNameResemblance;
    }

    public double getWeightHighSLAQ() {
        return this.weightHighSLAQ;
    }

    public double getWeightInterfaceViolationIrrelevant() {
        return this.weightInterfaceViolationIrrelevant;
    }

    public double getWeightInterfaceViolationRelevant() {
        return this.weightInterfaceViolationRelevant;
    }

    public double getWeightLowCoupling() {
        return this.weightLowCoupling;
    }

    public double getWeightLowNameResemblance() {
        return this.weightLowNameResemblance;
    }

    public double getWeightLowSLAQ() {
        return this.weightLowSLAQ;
    }

    public double getWeightMidNameResemblance() {
        return this.weightMidNameResemblance;
    }

    public double getWeightPackageMapping() {
        return this.weightPackageMapping;
    }

    public String getWildcardKey() {
        return wildcardKey;
    }

    /**
     * Switch for interface reverse engineering. Serves for debugging-like use of SoMoX.
     *
     * @return
     */
    public boolean isReverseEngineerInterfacesNotAssignedToComponent() {
        return this.reverseEngineerInterfacesNotAssignedToComponent;
    }

    public void setExcludedPrefixesForNameResemblance(final String excludedPrefixesForNameResemblance) {
        this.excludedPrefixesForNameResemblance = excludedPrefixesForNameResemblance;
    }

    public void setExcludedSuffixesForNameResemblance(final String excludedSuffixesForNameResemblance) {
        this.excludedSuffixesForNameResemblance = excludedSuffixesForNameResemblance;
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

    public void setWeightDirectoryMapping(final double weightDirectoryMapping) {
        this.weightDirectoryMapping = weightDirectoryMapping;
    }

    public void setWeightDMS(final double weightDMS) {
        this.weightDMS = weightDMS;
    }

    public void setWeightHighCoupling(final double weightHighCoupling) {
        this.weightHighCoupling = weightHighCoupling;
    }

    public void setWeightHighestNameResemblance(final double weightHighestNameResemblance) {
        this.weightHighestNameResemblance = weightHighestNameResemblance;
    }

    public void setWeightHighNameResemblance(final double weightHighNameResemblance) {
        this.weightHighNameResemblance = weightHighNameResemblance;
    }

    public void setWeightHighSLAQ(final double weightHighSLAQ) {
        this.weightHighSLAQ = weightHighSLAQ;
    }

    public void setWeightInterfaceViolationIrrelevant(final double weightInterfaceViolationIrrelevant) {
        this.weightInterfaceViolationIrrelevant = weightInterfaceViolationIrrelevant;
    }

    public void setWeightInterfaceViolationRelevant(final double weightInterfaceViolationRelevant) {
        this.weightInterfaceViolationRelevant = weightInterfaceViolationRelevant;
    }

    public void setWeightLowCoupling(final double weightLowCoupling) {
        this.weightLowCoupling = weightLowCoupling;
    }

    public void setWeightLowNameResemblance(final double weightLowNameResemblance) {
        this.weightLowNameResemblance = weightLowNameResemblance;
    }

    public void setWeightLowSLAQ(final double weightLowSLAQ) {
        this.weightLowSLAQ = weightLowSLAQ;
    }

    public void setWeightMidNameResemblance(final double weightMidNameResemblance) {
        this.weightMidNameResemblance = weightMidNameResemblance;
    }

    public void setWeightPackageMapping(final double weightPackageMapping) {
        this.weightPackageMapping = weightPackageMapping;
    }

    public void setWildcardKey(final String wildcardKey, final String additionalWildcards) {
        this.wildcardKey = wildcardKey;
        this.additionalWildcards = additionalWildcards;
        final Set<String> wildCardList = this.getBlacklist();
        if (additionalWildcards != null && additionalWildcards.length() > 0) {
            wildCardList.add(additionalWildcards);
        }
        this.blacklistFilter = new BlacklistFilter(wildCardList);
    }

    /**
     * Converts this builder's configuration into an <em>attribute map</em>.
     * 
     * @return an <em>attribute map</em>, such that for any {@code SoMoXConfiguration c},
     *         {@code (new SOMOXConfigurationBuilderByPreferences).createSOMOXConfiguration(new SoMoXConfigurationMapBuilder(c).toMap())}
     *         will behave exactly like {@code c}.
     */
    public Map<String, Object> toMap() {
        Map<String, Object> result = new HashMap<String, Object>();

        result.put(SOMOX_PROJECT_NAME, getFileLocations().getProjectName());
        result.put(SOMOX_ANALYZER_INPUT_FILE, getFileLocations().getAnalyserInputFile());
        result.put(SOMOX_ANALYZER_REVERSE_ENGINEER_INTERFACES_NOT_ASSIGNED_TO_INTERFACES,
                isReverseEngineerInterfacesNotAssignedToComponent());
        result.put(SOMOX_ANALYZER_WILDCARD_KEY, getWildcardKey());
        result.put(BLACKLIST_CONFIGURATION_WILDCARDS_ADDITIONAL, getAdditionalWildcards());
        result.put(SOMOX_EXCLUDED_PREFIXES, getExcludedPrefixesForNameResemblance());
        result.put(SOMOX_EXCLUDED_SUFFIXES, getExcludedSuffixesForNameResemblance());

        // {@link SOMOXConfigurationBuilderByPreferences} expects Strings
        result.put(SOMOX_WEIGHT_DIRECTORY_MAPPING, String.valueOf(getWeightDirectoryMapping()));
        result.put(SOMOX_WEIGHT_DMS, String.valueOf(getWeightDMS()));
        result.put(SOMOX_WEIGHT_HIGH_COUPLING, String.valueOf(getWeightHighCoupling()));

        result.put(SOMOX_WEIGHT_HIGHEST_NAME_RESEMBLANCE, String.valueOf(getWeightHighestNameResemblance()));
        result.put(SOMOX_WEIGHT_HIGH_NAME_RESEMBLANCE, String.valueOf(getWeightHighNameResemblance()));
        result.put(SOMOX_WEIGHT_HIGH_SLAQ, String.valueOf(getWeightHighSLAQ()));
        result.put(SOMOX_WEIGHT_INTERFACE_VIOLATION_IRRELEVANT,
                String.valueOf(getWeightInterfaceViolationIrrelevant()));
        result.put(SOMOX_WEIGHT_INTERFACE_VIOLATION_RELEVANT, String.valueOf(getWeightInterfaceViolationRelevant()));
        result.put(SOMOX_WEIGHT_LOW_COUPLING, String.valueOf(getWeightLowCoupling()));
        result.put(SOMOX_WEIGHT_LOW_NAME_RESEMBLANCE, String.valueOf(getWeightLowNameResemblance()));
        result.put(SOMOX_WEIGHT_LOW_SLAQ, String.valueOf(getWeightLowSLAQ()));
        result.put(SOMOX_WEIGHT_MID_NAME_RESEMBLANCE, String.valueOf(getWeightMidNameResemblance()));
        result.put(SOMOX_WEIGHT_PACKAGE_MAPPING, String.valueOf(getWeightPackageMapping()));

        // see {@link SOMOXConfigurationBuilderByPreferences#createClusteringConfiguration}
        // multiplication necessary because values are divided there.
        final ClusteringConfiguration clusteringConfiguration = getClusteringConfig();
        result.put(SOMOX_WEIGHT_CLUSTERING_THRESHOLD_MAX_COMPOSE,
                String.valueOf(clusteringConfiguration.getMaxComposeClusteringThreshold() * 100.0d));
        result.put(SOMOX_WEIGHT_CLUSTERING_THRESHOLD_MIN_COMPOSE,
                String.valueOf(clusteringConfiguration.getMinComposeClusteringThreshold() * 100.0d));
        result.put(SOMOX_WEIGHT_CLUSTERING_THRESHOLD_DECREMENT_COMPOSE,
                String.valueOf(clusteringConfiguration.getClusteringComposeThresholdDecrement() * 100.0d));
        result.put(SOMOX_WEIGHT_CLUSTERING_THRESHOLD_MAX_MERGE,
                String.valueOf(clusteringConfiguration.getMaxMergeClusteringThreshold() * 100.0d));
        result.put(SOMOX_WEIGHT_CLUSTERING_THRESHOLD_MIN_MERGE,
                String.valueOf(clusteringConfiguration.getMinComposeClusteringThreshold() * 100.0d));
        result.put(SOMOX_WEIGHT_CLUSTERING_THRESHOLD_DECREMENT_MERGE,
                String.valueOf(clusteringConfiguration.getClusteringMergeThresholdDecrement() * 100.0d));

        return result;
    }

}
