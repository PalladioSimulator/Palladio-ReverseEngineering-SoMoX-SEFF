package org.somox.analyzer.simplemodelanalyzer.metricvalues;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.emftext.language.java.types.Type;
import org.jgrapht.DirectedGraph;
import org.palladiosimulator.pcm.repository.RepositoryComponent;
import org.somox.analyzer.simplemodelanalyzer.Activator;
import org.somox.configuration.SoMoXConfiguration;
import org.somox.metrics.ClusteringRelation;
import org.somox.metrics.MetricID;
//import de.fzi.gast.types.GASTClass;
import org.somox.sourcecodedecorator.ComponentImplementingClassesLink;

import metricvalues.Component;
import metricvalues.ComponentCandidate;
import metricvalues.Iteration;
import metricvalues.MetricValue;
import metricvalues.MetricValuesModel;
import metricvalues.MetricvaluesFactory;

public class MetricValuesWriter {

    private static final String CONFIG_METRIC_VALUES_MODEL_PROPERTIES_FILE = "/config/MetricValuesModel.properties";

    private static final String CONFIG_METRIC_VALUES_MODEL_PATH = "metricvaluesmodel.path";

    private final SoMoXConfiguration somoxConfiguration;

    public MetricValuesWriter(final SoMoXConfiguration somoxConfiguration) {
        this.somoxConfiguration = somoxConfiguration;
    }

    public void saveMetricValuesModel(
            final DirectedGraph<ComponentImplementingClassesLink, ClusteringRelation> metricsGraph, final int iteration,
            final double currentThreshold, final List<ComponentImplementingClassesLink> componentCandidates,
            final boolean isMergeIteration) {

        final URI resourceURI = this.getMetricValuesPlatformResourceURI();

        final ResourceSet resourceSet = new ResourceSetImpl();
        final URI normalized = resourceSet.getURIConverter().normalize(resourceURI);
        Resource resource = null;
        MetricValuesModel model = null;

        if (iteration == 1) {
            resource = resourceSet.createResource(normalized);
            model = MetricvaluesFactory.eINSTANCE.createMetricValuesModel();
            this.setModelAttributes(model);
        } else {
            resource = resourceSet.getResource(normalized, true);

            model = (MetricValuesModel) resource.getContents().get(0);
        }

        final Iteration currentIteration = this.createCurrentIteration(metricsGraph, iteration, currentThreshold,
                componentCandidates, isMergeIteration);

        model.getIterations().add(currentIteration);// REALLYCHANGEMF

        if (iteration == 1) {
            resource.getContents().add(model);
        }

        try {
            resource.save(Collections.EMPTY_MAP);

        } catch (final IOException e) {
        }

        Activator.getDefault().getLog()
                .log(new Status(IStatus.INFO, Activator.PLUGIN_ID, "Saved metric values of iteration " + iteration));
    }

    private void setModelAttributes(final MetricValuesModel model) {
        model.setMinCompThreshold(this.somoxConfiguration.getClusteringConfig().getMinComposeClusteringThreshold());
        model.setMaxMergeThreshold(this.somoxConfiguration.getClusteringConfig().getMaxMergeClusteringThreshold());
        model.setWeightDirectoryMapping(this.somoxConfiguration.getWeightDirectoryMapping());
        model.setWeightDMS(this.somoxConfiguration.getWeightDMS());
        model.setWeightHighCoupling(this.somoxConfiguration.getWeightHighCoupling());
        model.setWeightHighNameResemblance(this.somoxConfiguration.getWeightHighNameResemblance());
        model.setWeightHighSLAQ(this.somoxConfiguration.getWeightHighSLAQ());
        model.setWeightHighestNameResemblance(this.somoxConfiguration.getWeightHighestNameResemblance());
        model.setWeightInterfaceViolationIrrelevant(this.somoxConfiguration.getWeightInterfaceViolationIrrelevant());
        model.setWeightInterfaceViolationRelevant(this.somoxConfiguration.getWeightInterfaceViolationRelevant());
        model.setWeightLowCoupling(this.somoxConfiguration.getWeightLowCoupling());
        model.setWeightLowNameResemblance(this.somoxConfiguration.getWeightLowNameResemblance());
        model.setWeightLowSLAQ(this.somoxConfiguration.getWeightLowSLAQ());
        model.setWeightMidNameResemblance(this.somoxConfiguration.getWeightMidNameResemblance());
        model.setWeightPackageMapping(this.somoxConfiguration.getWeightPackageMapping());
        model.setWildcardKey(this.getBlacklistString(this.somoxConfiguration.getBlacklist()));
        model.setMinMergeThreshold(this.somoxConfiguration.getClusteringConfig().getMinMergeClusteringThreshold());
        model.setMaxComposeThreshold(this.somoxConfiguration.getClusteringConfig().getMaxComposeClusteringThreshold());
        model.setComposeThresholdDecrement(
                this.somoxConfiguration.getClusteringConfig().getClusteringComposeThresholdDecrement());
        model.setMergeThresholdDecrement(
                this.somoxConfiguration.getClusteringConfig().getClusteringMergeThresholdDecrement());
        model.setExcludedPrefixesForNameResemblance(this.somoxConfiguration.getExcludedPrefixesForNameResemblance());
        model.setExcludedSuffixesForNameResemblance(this.somoxConfiguration.getExcludedSuffixesForNameResemblance());
    }

    private String getBlacklistString(final Set<String> blacklist) {
        final StringBuilder blacklistString = new StringBuilder();
        for (final String string : blacklist) {
            blacklistString.append(string + SoMoXConfiguration.SOMOX_WILDCARD_DELIMITER);
        }
        return blacklistString.toString();
    }

    private Iteration createCurrentIteration(
            final DirectedGraph<ComponentImplementingClassesLink, ClusteringRelation> metricsGraph, final int iteration,
            final double currentThreshold, final List<ComponentImplementingClassesLink> componentCandidates,
            final boolean isMergeIteration) {
        final Iteration currentIteration = MetricvaluesFactory.eINSTANCE.createIteration();
        currentIteration.setNumber(iteration);
        // TODO FIXME: Depending on the isMergeIteration in any case only one of the values makes
        // sense...
        // so only current should be stored
        currentIteration.setCurCompThreshold(currentThreshold);
        currentIteration.setCurMergeThreshold(currentThreshold);
        currentIteration.setIsMergeIteration(isMergeIteration);

        this.createComponents(componentCandidates, currentIteration);
        this.createComponentCandidates(metricsGraph, currentIteration);

        return currentIteration;
    }

    private void createComponentCandidates(
            final DirectedGraph<ComponentImplementingClassesLink, ClusteringRelation> metricsGraph,
            final Iteration currentIteration) {
        final Set<ClusteringRelation> edges = metricsGraph.edgeSet();
        for (final ClusteringRelation clusteringRelation : edges) {
            final ComponentCandidate compCandidate = MetricvaluesFactory.eINSTANCE.createComponentCandidate();
            final RepositoryComponent compA = clusteringRelation.getSourceComponent().getComponent();
            final RepositoryComponent compB = clusteringRelation.getTargetComponent().getComponent();

            for (final Component component : currentIteration.getComponents())// REALLYCHANGEMF
            {
                if (component.getId().equals(compA.getId())) {
                    compCandidate.setFirstComponent(component);
                } else if (component.getId().equals(compB.getId())) {
                    compCandidate.setSecondComponent(component);
                }
            }

            this.createMetricValue(clusteringRelation, compCandidate);

            currentIteration.getComponentCandidates().add(compCandidate);// REALLYCHANGEMF
        }
    }

    private void createMetricValue(final ClusteringRelation clusteringRelation,
            final ComponentCandidate compCandidate) {
        final Set<Entry<MetricID, Double>> clusteringMetrics = clusteringRelation.getResult().entrySet();
        for (final Entry<MetricID, Double> entry : clusteringMetrics) {
            final MetricValue metricValue = MetricvaluesFactory.eINSTANCE.createMetricValue();
            metricValue.setMetricID(entry.getKey().getMetricID());
            metricValue.setValue(entry.getValue().doubleValue());
            compCandidate.getMetricValues().add(metricValue);// REALLYCHANGEMF
        }
    }

    private void createComponents(final List<ComponentImplementingClassesLink> components,
            final Iteration currentIteration) {
        for (final ComponentImplementingClassesLink compLink : components) {
            final Component component = this.createComponent(currentIteration, compLink);

            currentIteration.getComponents().add(component);// REALLYCHANGEMF
        }
    }

    private Component createComponent(final Iteration currentIteration,
            final ComponentImplementingClassesLink compCand) {
        final Component component = MetricvaluesFactory.eINSTANCE.createComponent();
        final RepositoryComponent comp = compCand.getComponent();
        component.setId(comp.getId());
        component.setName(comp.getEntityName());

        final List<ConcreteClassifier> classes = compCand.getImplementingClasses();
        for (final Type gastClass : classes) {
            component.getClasses().add(gastClass);// REALLYCHANGEMF
        }

        final List<ComponentImplementingClassesLink> subComponents = compCand.getSubComponents();
        for (final ComponentImplementingClassesLink componentImplementingClassesLink : subComponents) {
            final Component subComponent = this.createComponent(currentIteration, componentImplementingClassesLink);
            component.getSubComponents().add(subComponent);// REALLYCHANGEMF
        }

        return component;
    }

    // private Component createComponent(ComponentType comp)
    // {
    // Component component = MetricvaluesFactory.eINSTANCE.createComponent();
    // if (comp instanceof CompositeComponent)
    // {
    // List<SubcomponentInstance> subcomponents = ((CompositeComponent) comp).getSubcomponents();
    // for (SubcomponentInstance subcomponentInstance : subcomponents)
    // {
    // ComponentType subcomponent = subcomponentInstance.getRealizedBy();
    // Component newComponent = createComponent(subcomponent);
    // component.getSubComponentsList().add(newComponent);
    // }
    // }
    //
    // return component;
    // }

    private URI getMetricValuesPlatformResourceURI() {
        final Properties properties = new Properties();
        try {
            final InputStream inStream = Activator.getDefault().getBundle()
                    .getEntry(CONFIG_METRIC_VALUES_MODEL_PROPERTIES_FILE).openStream();
            properties.load(inStream);
        } catch (final IOException e) {
            e.printStackTrace();
        }
        final URI fileURI = URI
                .createPlatformResourceURI(new File(this.somoxConfiguration.getFileLocations().getOutputFolder() + "/"
                        + properties.getProperty(CONFIG_METRIC_VALUES_MODEL_PATH)).getPath(), true);
        return fileURI;
    }

}
