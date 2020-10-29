package org.somox.configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import de.uka.ipd.sdq.workflow.configuration.AbstractComposedJobConfiguration;
import de.uka.ipd.sdq.workflow.configuration.IJobConfiguration;

public abstract class AbstractMoxConfiguration extends AbstractComposedJobConfiguration implements IJobConfiguration {

    private static final Logger logger = Logger.getLogger(AbstractMoxConfiguration.class.getSimpleName());

    private static final String SOMOX_OUTPUT_FOLDER_DEFAULT = "/model";

    /**
     * attribute key for {@link #getFileLocations()}.{@code getOutputFolder()} /
     * {@link #getFileLocations()}.{@code setOutputFolder(String)}
     */
    public static final String SOMOX_OUTPUT_FOLDER = "org.somox.outputfile";
    /**
     * attribute key for {@link #getFileLocations()}.{@code getProjectName()} /
     * {@link #getFileLocations()}.{@code setProjectName(String)}
     */
    public static final String SOMOX_PROJECT_NAME = "org.somox.project";
    /**
     * attribute key for {@link #getFileLocations()}.{@code getAnalyserInputFile()} /
     * {@link #getFileLocations()}.{@code setAnalyserInputFile(String)}
     */
    public static final String SOMOX_ANALYZER_INPUT_FILE = "org.somox.analyzer.inputfile";
    /**
     * attribute key for {@link #isReverseEngineerInterfacesNotAssignedToComponent()} /
     * {@link #setReverseEngineerInterfacesNotAssignedToComponent(boolean)}
     */
    public static final String SOMOX_ANALYZER_REVERSE_ENGINEER_INTERFACES_NOT_ASSIGNED_TO_INTERFACES = "org.somox.analyzer.ReverseEngineerInterfacesNotAssignedToComponent";
    /**
     * attribute key for
     * {@link #isReverseEngineerInternalMethodsAsResourceDemandingInternalBehaviour()} /
     * {@link #setReverseEngineerInternalMethodsAsResourceDemandingInternalBehaviour(boolean)}
     */
    public static final String SOMOX_ANALYZER_REVERSE_ENGINEER_INTERNAL_METHODS_AS_RESOURCE_DEMANDING_INTERNAL_BEHAVIOUR = "org.somox.analyzer.ReverseEngineerInternalMethodsAsResourceDemandingInternalBehaviour";
    private final FileLocationConfiguration locations = new FileLocationConfiguration();
    private boolean reverseEngineerInterfacesNotAssignedToComponent;
    private boolean reverseEngineerInternalMethodsAsResourceDemandingInternalBehaviour;

    /**
     * @return the locations
     */
    public FileLocationConfiguration getFileLocations() {
        return this.locations;
    }

    public boolean isReverseEngineerInternalMethodsAsResourceDemandingInternalBehaviour() {
        return this.reverseEngineerInternalMethodsAsResourceDemandingInternalBehaviour;
    }

    public void setReverseEngineerInternalMethodsAsResourceDemandingInternalBehaviour(
            final boolean reverseEngineerInternalMethodsAsResourceDemandingInternalBehaviour) {
        this.reverseEngineerInternalMethodsAsResourceDemandingInternalBehaviour = reverseEngineerInternalMethodsAsResourceDemandingInternalBehaviour;
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

    @SuppressWarnings("unchecked")
    public void applyAttributeMap(final Map<String, Object> attributeMap) {
        // Debug output
        logger.debug("SoMoX configuration extended by these attributes:");
        for (final Object key : attributeMap.keySet()) {
            final String keyname = key.toString();

            if (keyname.contains("org.somox")) {
                logger.debug(key + "=" + attributeMap.get(key));
            }

        }
        final FileLocationConfiguration fileLocations = this.getFileLocations();
        if (attributeMap.get(AbstractMoxConfiguration.SOMOX_PROJECT_NAME) != null) {
            fileLocations.setProjectNames((Set<String>) attributeMap.get(AbstractMoxConfiguration.SOMOX_PROJECT_NAME));
        }

        if (attributeMap.get(AbstractMoxConfiguration.SOMOX_ANALYZER_INPUT_FILE) != null) {
            fileLocations.setAnalyserInputFile(
                    (String) attributeMap.get(AbstractMoxConfiguration.SOMOX_ANALYZER_INPUT_FILE));
        }

        if (attributeMap.get(AbstractMoxConfiguration.SOMOX_OUTPUT_FOLDER) != null) {
            fileLocations.setOutputFolder((String) attributeMap.get(AbstractMoxConfiguration.SOMOX_OUTPUT_FOLDER));
        } else {
            if (fileLocations.getProjectNames().size() > 0) {
                fileLocations.setOutputFolder(
                        "/" + fileLocations.getProjectNames().iterator().next() + SOMOX_OUTPUT_FOLDER_DEFAULT);
            }
        }

        if (attributeMap.get(
                AbstractMoxConfiguration.SOMOX_ANALYZER_REVERSE_ENGINEER_INTERFACES_NOT_ASSIGNED_TO_INTERFACES) != null) {
            final boolean allInterfacesStrategy = (Boolean) attributeMap.get(
                    AbstractMoxConfiguration.SOMOX_ANALYZER_REVERSE_ENGINEER_INTERFACES_NOT_ASSIGNED_TO_INTERFACES);
            this.setReverseEngineerInterfacesNotAssignedToComponent(allInterfacesStrategy);
        }

        if (attributeMap.get(
                AbstractMoxConfiguration.SOMOX_ANALYZER_REVERSE_ENGINEER_INTERNAL_METHODS_AS_RESOURCE_DEMANDING_INTERNAL_BEHAVIOUR) != null) {
            final boolean reverseEngineerInternalMethodsAsResourceDemandingInternalBehaviour = (Boolean) attributeMap
                    .get(AbstractMoxConfiguration.SOMOX_ANALYZER_REVERSE_ENGINEER_INTERNAL_METHODS_AS_RESOURCE_DEMANDING_INTERNAL_BEHAVIOUR);
            this.setReverseEngineerInternalMethodsAsResourceDemandingInternalBehaviour(
                    reverseEngineerInternalMethodsAsResourceDemandingInternalBehaviour);
        }

    }

    public Map<String, Object> toMap() {
        final Map<String, Object> result = new HashMap<String, Object>();

        result.put(AbstractMoxConfiguration.SOMOX_PROJECT_NAME, this.getFileLocations().getProjectNames());
        result.put(AbstractMoxConfiguration.SOMOX_ANALYZER_INPUT_FILE, this.getFileLocations().getAnalyserInputFile());
        result.put(AbstractMoxConfiguration.SOMOX_ANALYZER_REVERSE_ENGINEER_INTERFACES_NOT_ASSIGNED_TO_INTERFACES,
                this.isReverseEngineerInterfacesNotAssignedToComponent());
        result.put(AbstractMoxConfiguration.SOMOX_OUTPUT_FOLDER, this.getFileLocations().getOutputFolder());
        return result;
    }

}