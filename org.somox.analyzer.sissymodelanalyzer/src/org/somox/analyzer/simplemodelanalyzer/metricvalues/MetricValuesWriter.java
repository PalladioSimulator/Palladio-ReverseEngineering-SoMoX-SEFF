package org.somox.analyzer.simplemodelanalyzer.metricvalues;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import metricvalues.Component;
import metricvalues.ComponentCandidate;
import metricvalues.Iteration;
import metricvalues.MetricValue;
import metricvalues.MetricValuesModel;
import metricvalues.MetricvaluesFactory;

import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.emftext.language.java.types.Type;

import org.jgrapht.DirectedGraph;
import org.somox.analyzer.simplemodelanalyzer.Activator;
import org.somox.configuration.SoMoXConfiguration;
import org.somox.metrics.ClusteringRelation;
import org.somox.metrics.MetricID;

//import de.fzi.gast.types.GASTClass;
import org.somox.sourcecodedecorator.ComponentImplementingClassesLink;

import de.uka.ipd.sdq.pcm.repository.RepositoryComponent;


public class MetricValuesWriter
{

   private static final String CONFIG_METRIC_VALUES_MODEL_PROPERTIES_FILE = "/config/MetricValuesModel.properties";

   private static final String CONFIG_METRIC_VALUES_MODEL_PATH = "metricvaluesmodel.path";

   private SoMoXConfiguration somoxConfiguration;


   public MetricValuesWriter(SoMoXConfiguration somoxConfiguration)
   {
      this.somoxConfiguration = somoxConfiguration;
   }


   public void saveMetricValuesModel(DirectedGraph<ComponentImplementingClassesLink, ClusteringRelation> metricsGraph,
         int iteration, double currentComposeThreshold, double currentMergeThreshold,
         List<ComponentImplementingClassesLink> componentCandidates, boolean isMergeIteration)
   {

      URI resourceURI = getMetricValuesPlatformResourceURI();

      ResourceSet resourceSet = new ResourceSetImpl();
      URI normalized = resourceSet.getURIConverter().normalize(resourceURI);
      Resource resource = null;
      MetricValuesModel model = null;

      if (iteration == 1)
      {
         resource = resourceSet.createResource(normalized);
         model = MetricvaluesFactory.eINSTANCE.createMetricValuesModel();
         setModelAttributes(model);
      }
      else
      {
         resource = resourceSet.getResource(normalized, true);

         model = (MetricValuesModel) resource.getContents().get(0);
      }

      Iteration currentIteration = createCurrentIteration(metricsGraph, iteration, currentComposeThreshold,
            currentMergeThreshold, componentCandidates, isMergeIteration);

      model.getIterations().add(currentIteration);//REALLYCHANGEMF

      if (iteration == 1)
      {
         resource.getContents().add(model);
      }

      try
      {
         resource.save(Collections.EMPTY_MAP);

      }
      catch (IOException e)
      {
      }

      Activator.getDefault().getLog()
            .log(new Status(Status.INFO, Activator.PLUGIN_ID, "Saved metric values of iteration " + iteration));
   }


   private void setModelAttributes(MetricValuesModel model)
   {
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
      model.setWildcardKey(getBlacklistString(this.somoxConfiguration.getBlacklist()));
      model.setMinMergeThreshold(this.somoxConfiguration.getClusteringConfig().getMinMergeClusteringThreshold());
      model.setMaxComposeThreshold(this.somoxConfiguration.getClusteringConfig().getMaxComposeClusteringThreshold());
      model.setComposeThresholdDecrement(this.somoxConfiguration.getClusteringConfig()
            .getClusteringComposeThresholdDecrement());
      model.setMergeThresholdDecrement(this.somoxConfiguration.getClusteringConfig()
            .getClusteringMergeThresholdDecrement());
      model.setExcludedPrefixesForNameResemblance(this.somoxConfiguration.getExcludedPrefixesForNameResemblance());
      model.setExcludedSuffixesForNameResemblance(this.somoxConfiguration.getExcludedSuffixesForNameResemblance());
   }


   private String getBlacklistString(Set<String> blacklist)
   {
      StringBuilder blacklistString = new StringBuilder();
      for (String string : blacklist)
      {
         blacklistString.append(string + SoMoXConfiguration.SOMOX_WILDCARD_DELIMITER);
      }
      return blacklistString.toString();
   }


   private Iteration createCurrentIteration(
         DirectedGraph<ComponentImplementingClassesLink, ClusteringRelation> metricsGraph, int iteration,
         double currentComposeThreshold, double currentMergeThreshold,
         List<ComponentImplementingClassesLink> componentCandidates, boolean isMergeIteration)
   {
      Iteration currentIteration = MetricvaluesFactory.eINSTANCE.createIteration();
      currentIteration.setNumber(iteration);
      currentIteration.setCurCompThreshold(currentComposeThreshold);
      currentIteration.setCurMergeThreshold(currentMergeThreshold);
      currentIteration.setIsMergeIteration(isMergeIteration);

      createComponents(componentCandidates, currentIteration);
      createComponentCandidates(metricsGraph, currentIteration);

      return currentIteration;
   }


   private void createComponentCandidates(
         DirectedGraph<ComponentImplementingClassesLink, ClusteringRelation> metricsGraph, Iteration currentIteration)
   {
      Set<ClusteringRelation> edges = metricsGraph.edgeSet();
      for (ClusteringRelation clusteringRelation : edges)
      {
         ComponentCandidate compCandidate = MetricvaluesFactory.eINSTANCE.createComponentCandidate();
         RepositoryComponent compA = clusteringRelation.getComponentA().getComponent();
         RepositoryComponent compB = clusteringRelation.getComponentB().getComponent();

         for (Component component : currentIteration.getComponents())//REALLYCHANGEMF
         {
            if (component.getId().equals(compA.getId()))
            {
               compCandidate.setFirstComponent(component);
            }
            else if (component.getId().equals(compB.getId()))
            {
               compCandidate.setSecondComponent(component);
            }
         }

         createMetricValue(clusteringRelation, compCandidate);

         currentIteration.getComponentCandidates().add( compCandidate);//REALLYCHANGEMF
      }
   }


   private void createMetricValue(ClusteringRelation clusteringRelation, ComponentCandidate compCandidate)
   {
      Set<Entry<MetricID, Double>> clusteringMetrics = clusteringRelation.getResult().entrySet();
      for (Entry<MetricID, Double> entry : clusteringMetrics)
      {
         MetricValue metricValue = MetricvaluesFactory.eINSTANCE.createMetricValue();
         metricValue.setMetricID(entry.getKey().getMetricID());
         metricValue.setValue(entry.getValue().doubleValue());
         compCandidate.getMetricValues().add(metricValue);//REALLYCHANGEMF
      }
   }


   private void createComponents(List<ComponentImplementingClassesLink> components, Iteration currentIteration)
   {
      for (ComponentImplementingClassesLink compLink : components)
      {
         Component component = createComponent(currentIteration, compLink);

         currentIteration.getComponents().add(component);//REALLYCHANGEMF
      }
   }


   private Component createComponent(Iteration currentIteration, ComponentImplementingClassesLink compCand)
   {
      Component component = MetricvaluesFactory.eINSTANCE.createComponent();
      RepositoryComponent comp = compCand.getComponent();
      component.setId(comp.getId());
      component.setName(comp.getEntityName());

      List<Type  > classes = compCand.getImplementingClasses();
      for (Type gastClass : classes)
      {
         component.getClasses().add(gastClass);//REALLYCHANGEMF
      }

      List<ComponentImplementingClassesLink> subComponents = compCand.getSubComponents();
      for (ComponentImplementingClassesLink componentImplementingClassesLink : subComponents)
      {
         Component subComponent = createComponent(currentIteration, componentImplementingClassesLink);
         component.getSubComponents().add(subComponent);//REALLYCHANGEMF
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


   private URI getMetricValuesPlatformResourceURI()
   {
      Properties properties = new Properties();
      try
      {
         InputStream inStream = Activator.getDefault().getBundle().getEntry(CONFIG_METRIC_VALUES_MODEL_PROPERTIES_FILE)
               .openStream();
         properties.load(inStream);
      }
      catch (IOException e)
      {
         e.printStackTrace();
      }
      URI fileURI = URI.createPlatformResourceURI(
            new File(this.somoxConfiguration.getFileLocations().getProjectName() + "/"
                  + this.somoxConfiguration.getFileLocations().getOutputFolder() + "/"
                  + properties.getProperty(CONFIG_METRIC_VALUES_MODEL_PATH)).getPath(), true);
      return fileURI;
   }


}
