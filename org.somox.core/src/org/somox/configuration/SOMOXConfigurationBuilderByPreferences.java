package org.somox.configuration;

import static org.somox.common.SoMoXProjectPreferences.SOMOX_WEIGHT_CLUSTERING_THRESHOLD_DECREMENT_COMPOSE;
import static org.somox.common.SoMoXProjectPreferences.SOMOX_WEIGHT_CLUSTERING_THRESHOLD_DECREMENT_MERGE;
import static org.somox.common.SoMoXProjectPreferences.SOMOX_WEIGHT_CLUSTERING_THRESHOLD_MAX_COMPOSE;
import static org.somox.common.SoMoXProjectPreferences.SOMOX_WEIGHT_CLUSTERING_THRESHOLD_MAX_MERGE;
import static org.somox.common.SoMoXProjectPreferences.SOMOX_WEIGHT_CLUSTERING_THRESHOLD_MIN_COMPOSE;
import static org.somox.common.SoMoXProjectPreferences.SOMOX_WEIGHT_CLUSTERING_THRESHOLD_MIN_MERGE;
import static org.somox.common.SoMoXProjectPreferences.SOMOX_WEIGHT_DIRECTORY_MAPPING;
import static org.somox.common.SoMoXProjectPreferences.SOMOX_WEIGHT_DMS;
import static org.somox.common.SoMoXProjectPreferences.SOMOX_WEIGHT_HIGHEST_NAME_RESEMBLANCE;
import static org.somox.common.SoMoXProjectPreferences.SOMOX_WEIGHT_HIGH_COUPLING;
import static org.somox.common.SoMoXProjectPreferences.SOMOX_WEIGHT_HIGH_NAME_RESEMBLANCE;
import static org.somox.common.SoMoXProjectPreferences.SOMOX_WEIGHT_HIGH_SLAQ;
import static org.somox.common.SoMoXProjectPreferences.SOMOX_WEIGHT_INTERFACE_VIOLATION_IRRELEVANT;
import static org.somox.common.SoMoXProjectPreferences.SOMOX_WEIGHT_INTERFACE_VIOLATION_RELEVANT;
import static org.somox.common.SoMoXProjectPreferences.SOMOX_WEIGHT_LOW_COUPLING;
import static org.somox.common.SoMoXProjectPreferences.SOMOX_WEIGHT_LOW_NAME_RESEMBLANCE;
import static org.somox.common.SoMoXProjectPreferences.SOMOX_WEIGHT_LOW_SLAQ;
import static org.somox.common.SoMoXProjectPreferences.SOMOX_WEIGHT_MID_NAME_RESEMBLANCE;
import static org.somox.common.SoMoXProjectPreferences.SOMOX_WEIGHT_PACKAGE_MAPPING;

import java.util.Map;

import org.apache.log4j.Logger;
import org.somox.common.SoMoXProjectPreferences;

public class SOMOXConfigurationBuilderByPreferences {

    private static Logger logger = Logger.getLogger(SOMOXConfigurationBuilderByPreferences.class);

    /*
     * Factory method which creates a new SoMoXConfiguration based on values in attribute map.
     * Values which are not present in attribute map will be set to default.
     */
    public SoMoXConfiguration createSOMOXConfiguration(final Map attributes) {
        final SoMoXConfiguration somoxConfiguration = new SoMoXConfiguration();

        if (attributes != null) {
            this.initializeConfiguration(somoxConfiguration, attributes);
        }

        return somoxConfiguration;
    }

    /*
     * This method can be used to set values of a SoMoXConfiguration according to values in an
     * attribute map. Values which are not present in attribute map will be left untouched.
     */
    public void initializeConfiguration(final SoMoXConfiguration somoxConfiguration, final Map attributes) {
        if (somoxConfiguration == null) {
            return;
        }
        if (attributes == null) {
            return;
        }

        if (attributes.get(SoMoXProjectPreferences.SOMOX_PROJECT_NAME) != null) {
            final String projectIdentifier = (String) attributes.get(SoMoXProjectPreferences.SOMOX_PROJECT_NAME);
            somoxConfiguration.getFileLocations().setProjectName(projectIdentifier);
        }
        somoxConfiguration.getFileLocations().setOutputFolder("/model");

        if (attributes.get(SoMoXProjectPreferences.SOMOX_ANALYZER_INPUT_FILE) != null) {
            final String inputfile = (String) attributes.get(SoMoXProjectPreferences.SOMOX_ANALYZER_INPUT_FILE);
            somoxConfiguration.getFileLocations().setAnalyserInputFile(inputfile);
        }

        if (attributes.get(
                SoMoXProjectPreferences.SOMOX_ANALYZER_REVERSE_ENGINEER_INTERFACES_NOT_ASSIGNED_TO_INTERFACES) != null) {
            final boolean allInterfacesStrategy = (Boolean) attributes
                    .get(SoMoXProjectPreferences.SOMOX_ANALYZER_REVERSE_ENGINEER_INTERFACES_NOT_ASSIGNED_TO_INTERFACES);
            somoxConfiguration.setReverseEngineerInterfacesNotAssignedToComponent(allInterfacesStrategy);
        }

        if (attributes.get(SoMoXProjectPreferences.SOMOX_ANALYSER_WILDCARD_KEY) != null) {
            final String wildcardkey = (String) attributes.get(SoMoXProjectPreferences.SOMOX_ANALYSER_WILDCARD_KEY);
            final String additionalWildcardkey = (String) attributes
                    .get(SoMoXProjectPreferences.BLACKLIST_CONFIGURATION_WILDCARDS_ADDITIONAL);
            somoxConfiguration.setWildcardKey(wildcardkey, additionalWildcardkey);
        }

        // NameResemblanceTab
        if (attributes.get("org.somox.metrics.nameResemblance.excludedPrefixes") != null) {
            final String nameResemblancePrefixString = (String) attributes
                    .get("org.somox.metrics.nameResemblance.excludedPrefixes");
            somoxConfiguration.setExcludedPrefixesForNameResemblance(nameResemblancePrefixString);
        }

        if (attributes.get("org.somox.metrics.nameResemblance.excludedSuffixes") != null) {
            final String nameResemblanceSuffixString = (String) attributes
                    .get("org.somox.metrics.nameResemblance.excludedSuffixes");
            somoxConfiguration.setExcludedSuffixesForNameResemblance(nameResemblanceSuffixString);
        }

        for (final Object key : attributes.keySet()) {

            final String keyname = key.toString();

            if (keyname.contains("org.somox")) {
                logger.debug(key + "=" + attributes.get(key));
            }

        }
        logger.debug(attributes.values());
        somoxConfiguration
                .setWeightDirectoryMapping(Double.parseDouble((String) attributes.get(SOMOX_WEIGHT_DIRECTORY_MAPPING)));
        somoxConfiguration.setWeightDMS(Double.parseDouble((String) attributes.get(SOMOX_WEIGHT_DMS)));
        somoxConfiguration
                .setWeightHighCoupling(Double.parseDouble((String) attributes.get(SOMOX_WEIGHT_HIGH_COUPLING)));
        somoxConfiguration.setWeightHighestNameResemblance(
                Double.parseDouble((String) attributes.get(SOMOX_WEIGHT_HIGHEST_NAME_RESEMBLANCE)));
        somoxConfiguration.setWeightHighNameResemblance(
                Double.parseDouble((String) attributes.get(SOMOX_WEIGHT_HIGH_NAME_RESEMBLANCE)));
        somoxConfiguration.setWeightHighSLAQ(Double.parseDouble((String) attributes.get(SOMOX_WEIGHT_HIGH_SLAQ)));
        somoxConfiguration.setWeightInterfaceViolationIrrelevant(
                Double.parseDouble((String) attributes.get(SOMOX_WEIGHT_INTERFACE_VIOLATION_IRRELEVANT)));
        somoxConfiguration.setWeightInterfaceViolationRelevant(
                Double.parseDouble((String) attributes.get(SOMOX_WEIGHT_INTERFACE_VIOLATION_RELEVANT)));
        somoxConfiguration.setWeightLowCoupling(Double.parseDouble((String) attributes.get(SOMOX_WEIGHT_LOW_COUPLING)));
        somoxConfiguration.setWeightLowNameResemblance(
                Double.parseDouble((String) attributes.get(SOMOX_WEIGHT_LOW_NAME_RESEMBLANCE)));
        somoxConfiguration.setWeightLowSLAQ(Double.parseDouble((String) attributes.get(SOMOX_WEIGHT_LOW_SLAQ)));
        somoxConfiguration.setWeightMidNameResemblance(
                Double.parseDouble((String) attributes.get(SOMOX_WEIGHT_MID_NAME_RESEMBLANCE)));
        somoxConfiguration
                .setWeightPackageMapping(Double.parseDouble((String) attributes.get(SOMOX_WEIGHT_PACKAGE_MAPPING)));
        this.createClusteringConfiguration(somoxConfiguration, attributes);
    }

    /**
     * @param somoxConfiguration
     * @param attributes
     */
    private void createClusteringConfiguration(final SoMoXConfiguration somoxConfiguration, final Map attributes) {
        final ClusteringConfiguration clusteringConfiguration = somoxConfiguration.getClusteringConfig();
        // COMPOSE
        clusteringConfiguration.setMaxComposeClusteringThreshold(
                Double.parseDouble((String) attributes.get(SOMOX_WEIGHT_CLUSTERING_THRESHOLD_MAX_COMPOSE)) / 100.0d);
        clusteringConfiguration.setMinComposeClusteringThreshold(
                Double.parseDouble((String) attributes.get(SOMOX_WEIGHT_CLUSTERING_THRESHOLD_MIN_COMPOSE)) / 100.0d);
        clusteringConfiguration.setClusteringComposeThresholdDecrement(
                Double.parseDouble((String) attributes.get(SOMOX_WEIGHT_CLUSTERING_THRESHOLD_DECREMENT_COMPOSE))
                        / 100.0d);

        // MERGE
        clusteringConfiguration.setMaxMergeClusteringThreshold(
                Double.parseDouble((String) attributes.get(SOMOX_WEIGHT_CLUSTERING_THRESHOLD_MAX_MERGE)) / 100.0d);
        clusteringConfiguration.setMinMergeClusteringThreshold(
                Double.parseDouble((String) attributes.get(SOMOX_WEIGHT_CLUSTERING_THRESHOLD_MIN_MERGE)) / 100.0d);
        clusteringConfiguration.setClusteringMergeThresholdDecrement(
                Double.parseDouble((String) attributes.get(SOMOX_WEIGHT_CLUSTERING_THRESHOLD_DECREMENT_MERGE))
                        / 100.0d);

    }
}
