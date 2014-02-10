package org.somox.analyzer.simplemodelanalyzer.detection;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.emftext.language.java.types.Type;
import org.jgrapht.DirectedGraph;
import org.jgrapht.Graph;
import org.jgrapht.alg.ConnectivityInspector;
import org.jgrapht.graph.DirectedSubgraph;
import org.jgrapht.graph.SimpleDirectedGraph;
import org.jgrapht.graph.Subgraph;
import org.somox.analyzer.ModelAnalyzerException;
import org.somox.analyzer.simplemodelanalyzer.builder.ComponentBuilder;
import org.somox.analyzer.simplemodelanalyzer.detection.util.ComponentPrinter;
import org.somox.analyzer.simplemodelanalyzer.detection.util.EdgeThresholdFilter;
import org.somox.analyzer.simplemodelanalyzer.detection.util.VertexTypeAndEdgeThresholdFilter;
import org.somox.analyzer.simplemodelanalyzer.metrics.DefaultCompositionIndicatingMetric;
import org.somox.analyzer.simplemodelanalyzer.metrics.DefaultMergeIndicatingMetric;
import org.somox.analyzer.simplemodelanalyzer.metricvalues.MetricValuesWriter;
import org.somox.configuration.SoMoXConfiguration;
import org.somox.filter.FilteredCollectionsFactory;
import org.somox.kdmhelper.metamodeladdition.Root;
import org.somox.metrics.ClusteringRelation;
import org.somox.metrics.IMetric;
import org.somox.metrics.MetricID;
import org.somox.metrics.MetricsRegistry;
import org.somox.metrics.helper.Class2ClassAccessGraphHelper;
import org.somox.metrics.helper.ClassAccessGraphEdge;
import org.somox.metrics.helper.ComponentToImplementingClassesHelper;
import org.somox.metrics.util.GraphPrinter;

//import de.fzi.gast.core.Root;
//import de.fzi.gast.types.GASTClass;
import org.somox.sourcecodedecorator.ComponentImplementingClassesLink;

/**
 * Detection strategy for composite component which relies on clustering of metrics computed for pair-wise component
 * relationships
 * 
 * @author Steffen Becker, Klaus Krogmann, Michael Hauck
 */
public class ComponentDetectionByClustering implements IDetectionStrategy {

	/**
	 * The logger of this detection strategy 
	 */
	private static Logger logger = Logger.getLogger(ComponentDetectionByClustering.class);

	/**
	 * GAST model used to detect components 
	 */
	final private Root gastModel;

	/**
	 * Somox configuration to use to configure detection 
	 */
	final private SoMoXConfiguration somoxConfiguration;
	
	/**
	 * Helper to convert {@link ComponentImplementingClassesLink}s to Set of {@link org.eclipse.gmt.modisco.java.Type}
	 */
	private ComponentToImplementingClassesHelper componentToImplementingClassHelper = new ComponentToImplementingClassesHelper();

	/**
	 * Metric used when computing the relationship graph that indicates a composition of two or more components
	 */
	final private IMetric compositionIndicatingMetric;

	/**
	 * Metric used when merging the relationship graph that indicates a composition of two or more components
	 */
	final private IMetric mergeIndicatingMetric;
	
	/**
	 * Map of all metrics in the Eclipse system and initialized for this clustering algorithm
	 */
	private final Map<MetricID, IMetric> allMetrics;

	private ExecutorCompletionService<ClusteringRelation[]> completionService;
	
	/**
	 * Upper limit for the number of accepted iterations. 
	 * If set too low, reverse engineering will stop unfinished.
	 */
	private final int MAX_ACCEPTABLE_ITERATIONS = 200; //default: 1000

	
	public ComponentDetectionByClustering(Root gastModelToAnalyze, List<ComponentImplementingClassesLink> componentCandidates, SoMoXConfiguration somoxConfig) {
		super();

		GraphPrinter.cleanOutputFolder(somoxConfig.getFileLocations().getAnalyserInputFile());

		this.gastModel = gastModelToAnalyze;
		this.somoxConfiguration = somoxConfig;
		this.allMetrics = initializeMetrics(componentCandidates);		
		this.compositionIndicatingMetric = getMetric(allMetrics, DefaultCompositionIndicatingMetric.METRIC_ID);
		this.mergeIndicatingMetric = getMetric(allMetrics, DefaultMergeIndicatingMetric.METRIC_ID);
		
		// for decrement / increment per iteration; otherwise no termination:
		if(somoxConfiguration.getClusteringConfig().getClusteringMergeThresholdDecrement() == 0) {
			somoxConfiguration.getClusteringConfig().setClusteringMergeThresholdDecrement(1);
		}
		if(somoxConfiguration.getClusteringConfig().getClusteringComposeThresholdDecrement() == 0) {
			somoxConfiguration.getClusteringConfig().setClusteringComposeThresholdDecrement(1);
		}
		
		assert(somoxConfiguration.getClusteringConfig().getClusteringMergeThresholdDecrement() > 0 &&
				somoxConfiguration.getClusteringConfig().getClusteringComposeThresholdDecrement() > 0);
	}
	
	/* (non-Javadoc)
	 * @see org.somox.analyzer.simplemodelanalyzer.detection.IDetectionStrategy#startDetection(org.somox.analyzer.simplemodelanalyzer.SimpleAnalysisResult, org.eclipse.core.runtime.IProgressMonitor, java.util.List)
	 */
	public List<ComponentImplementingClassesLink> startDetection(
			ComponentBuilder sammBuilder,
			SoMoXConfiguration somoxConfig,
			IProgressMonitor progressMonitor,
			List<ComponentImplementingClassesLink> componentCandidates)
			throws ModelAnalyzerException {

		double currentComposeThreshold = somoxConfiguration.getClusteringConfig().getMaxComposeClusteringThreshold();
		double currentMergeThreshold = somoxConfiguration.getClusteringConfig().getMinMergeClusteringThreshold(); //merge starts with minimal threshold		
		int componentCountPreviousIteration = componentCandidates.size();
		boolean newComponentsFound = true;
		boolean isMergeIteration = true; //merge or compose in a iteration; by default first try to merge
		int iteration = 0;
		
		//removelater
//		ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() + 1);
		ExecutorService pool = Executors.newFixedThreadPool(1);
		completionService = new ExecutorCompletionService<ClusteringRelation[]>(pool);
		
		// Cluster as long as there is a chance to find new components in the clustering step; //merge starts with minimal threshold
		while ( (!isComposeFinished(currentComposeThreshold) || !isMergeFinished(currentMergeThreshold) ) /*&& componentCandidates.size() > 1*/
				&& iteration < MAX_ACCEPTABLE_ITERATIONS) { //second line: fallback break			
			
		   DirectedGraph<ComponentImplementingClassesLink, ClusteringRelation> componentIndicatingGraph = 
	         setupGraph(componentCandidates); 
	      DirectedGraph<ComponentImplementingClassesLink, ClusteringRelation> unfilteredComponentIndicatingGraph = 
	         setupGraph(componentCandidates);
		   
			iteration++;

			logger.info("Clustering iteration nr.: " + iteration + " with merge: " + isMergeIteration);
			logger.info("NR Components: " + componentCandidates.size());		
			logger.trace("thresholds: curComp: " + currentComposeThreshold + " minComp: " + somoxConfiguration.getClusteringConfig().getMinComposeClusteringThreshold() +
					" / curMerge: " + currentMergeThreshold + " maxMerge: " + somoxConfiguration.getClusteringConfig().getMaxMergeClusteringThreshold());
							
			if(isMergeIteration) { 
				if(!isMergeFinished(currentMergeThreshold)) {
					// MERGE
					// 1. Metric computation for the "merge" operation
					logger.trace("merge metric computation");
					componentIndicatingGraph = computeAllMetrics(
							componentCandidates, 
							mergeIndicatingMetric,
							componentIndicatingGraph,
							progressMonitor,
							somoxConfiguration.getClusteringConfig().getMinMergeClusteringThreshold()); // use minimal threshold; not current: only worst case estimation
               unfilteredComponentIndicatingGraph = computeAllMetrics(
                     componentCandidates, 
                     mergeIndicatingMetric,
                     unfilteredComponentIndicatingGraph, 
                     progressMonitor, 
                     -1);
				}
			} else {
				if(!isComposeFinished(currentComposeThreshold)) { 						
	 				// COMPOSE 	 		
					// 1. Metric Computation for the "compose" operation
					logger.trace("trace metric computation");
					componentIndicatingGraph = computeAllMetrics(
							componentCandidates, 
							compositionIndicatingMetric,
							componentIndicatingGraph,
							progressMonitor,
							somoxConfiguration.getClusteringConfig().getMinComposeClusteringThreshold()); 	// use minimal threshold; not current: only worst case estimation	
               unfilteredComponentIndicatingGraph = computeAllMetrics(
                     componentCandidates, 
                     compositionIndicatingMetric,
                     unfilteredComponentIndicatingGraph, 
                     progressMonitor, 
                     -1);				
				}
			}
 				
 			// 2. create projected graph from one with evaluated metrics:
 			DirectedGraph<ComponentImplementingClassesLink, ClusteringRelation> projectedGraph = createProjectedGraph(
					currentComposeThreshold, currentMergeThreshold,
					isMergeIteration, componentIndicatingGraph);

			// debug output:
 			createDebugOutputForIteration(isMergeIteration, iteration,
					componentIndicatingGraph, projectedGraph); 			
 			
 			saveMetricValuesModel(unfilteredComponentIndicatingGraph, iteration, currentComposeThreshold, currentMergeThreshold,
               componentCandidates, isMergeIteration);

         // 3. Component Clustering
			componentCandidates = componentComposition(sammBuilder, projectedGraph, iteration, isMergeIteration);			
			
			// update existing components for new interfaces
			// TODO: the following line causes a lot of performance overhead and is only useful if 
			// public methods are being recognised as interfaces in a fallback strategy
			sammBuilder.updateRequiredInterfacesOfExistingPrimitiveComponents();			
						
			// 4. Check whether new components have been found in this iteration 
			if (componentCandidates.size() == componentCountPreviousIteration) {
				newComponentsFound = false;
			} else {
				componentCountPreviousIteration = componentCandidates.size();
				newComponentsFound = true;
			}

			// 5. adapt thresholds if necessary
			if (!newComponentsFound) {
				if(isMergeIteration && !isMergeFinished(currentMergeThreshold)) { //merge starts with minimal threshold and increases threshold
					currentMergeThreshold += somoxConfiguration.getClusteringConfig().getClusteringMergeThresholdDecrement();
					//TODO: ensure not more than max value
				} else if(!isComposeFinished(currentComposeThreshold)) {					 
					currentComposeThreshold -= somoxConfiguration.getClusteringConfig().getClusteringComposeThresholdDecrement();
					//TODO: ensure not less than min value
				}
			}
			
			// 6. Decide whether to merge or compose (merge first; if no component merged then compose) in *next* iteration:
			isMergeIteration = decideMergeOrComposeNextIteration(
					newComponentsFound, isMergeIteration, currentComposeThreshold, currentMergeThreshold);
			
			if(iteration == MAX_ACCEPTABLE_ITERATIONS - 1) {
				logger.warn("Clustering will stop in next iteration (#" + iteration + ") due to reaching max limit: " + MAX_ACCEPTABLE_ITERATIONS);
			}
		}

		if (logger.isDebugEnabled()) {
			ComponentPrinter.printComponents(componentCandidates,logger);
		}
		
		pool.shutdown();
		
		return componentCandidates;
	}
	
	private void saveMetricValuesModel(DirectedGraph<ComponentImplementingClassesLink, ClusteringRelation> metricsGraph,
	         int iteration, double currentComposeThreshold, double currentMergeThreshold,
	         List<ComponentImplementingClassesLink> componentCandidates, boolean isMergeIteration)
	{
	      MetricValuesWriter mvWriter = new MetricValuesWriter(this.somoxConfiguration);
	      mvWriter.saveMetricValuesModel(metricsGraph,
	            iteration, currentComposeThreshold, currentMergeThreshold,componentCandidates, isMergeIteration);
	      
	}

	private boolean isComposeFinished(double currentComposeThreshold) {
		return !(currentComposeThreshold > somoxConfiguration.getClusteringConfig().getMinComposeClusteringThreshold());		
	}

	private boolean isMergeFinished(double currentMergeThreshold) {
		return !(currentMergeThreshold < somoxConfiguration.getClusteringConfig().getMaxMergeClusteringThreshold());		
	}

	/**
	 * Dumps graphs and values in trace mode.
	 * @param isMergeIteration
	 * @param iteration
	 * @param componentIndicatingGraph
	 * @param projectedGraph
	 */
	private void createDebugOutputForIteration(
			boolean isMergeIteration,
			int iteration,
			DirectedGraph<ComponentImplementingClassesLink, ClusteringRelation> componentIndicatingGraph,
			DirectedGraph<ComponentImplementingClassesLink, ClusteringRelation> projectedGraph) {
		if(logger.isTraceEnabled()) {
			logger.trace("graph with merge = " + isMergeIteration + " contains " + projectedGraph.edgeSet().size() + " edges, " + projectedGraph.vertexSet().size() +
					" vertices / orig graph: " + componentIndicatingGraph.edgeSet().size() + " edges, " + projectedGraph.vertexSet().size() + " vertices");
			
			GraphPrinter.dumpGraph(this.componentToImplementingClassHelper,componentIndicatingGraph, somoxConfiguration.getFileLocations().getAnalyserInputFile(), iteration, 1);

			if (projectedGraph.edgeSet().size() > 0) {	
				GraphPrinter.dumpGraph(this.componentToImplementingClassHelper,projectedGraph, somoxConfiguration.getFileLocations().getAnalyserInputFile(), iteration, 0);
			}			
		}
	}

	/**
	 * Decide whether to merge or compose (merge first; if no component merged then compose) in next iteration
	 * @param newComponentsFound of the current iteration
	 * @param isMergeIteration current status
	 * @param currentMergeThreshold 
	 * @param currentComposeThreshold 
	 * @return
	 */
	private boolean decideMergeOrComposeNextIteration(
			boolean newComponentsFound, boolean isMergeIteration, double currentComposeThreshold, double currentMergeThreshold) {
		// merge did not succeed; try compose
		if(isMergeIteration && !newComponentsFound && !isComposeFinished(currentComposeThreshold)) { 
			isMergeIteration = false;			
		}
		// successfully composed; next iteration starts with merge again
		if(!isMergeIteration && !newComponentsFound && !isMergeFinished(currentMergeThreshold)) { 
			isMergeIteration = true;
		}
		return isMergeIteration;
	}
	
	/** 
	 * Computes a graph containing GAST classes as nodes and directed edges which contain the number of accesses from the class in the 
	 * source node to the class in the target node. The nodes are filtered, i.e., only classes not filtered by the blacklist are
	 * contained.
	 * @param componentCandidates The list of initial component candidates. Used to further narrow the graph
	 * @return The graph as described in the methods main description  
	 */
	private DirectedGraph<Type, ClassAccessGraphEdge> getAccessGraph(List<ComponentImplementingClassesLink> componentCandidates) {
		// Graph whose nodes are GASTClasses and whose Edges 
		DirectedGraph<Type,ClassAccessGraphEdge> accessGraph =
			Class2ClassAccessGraphHelper.computeFilteredClass2ClassAccessGraph(
					somoxConfiguration, 
					this.componentToImplementingClassHelper.collectAllClasses(componentCandidates));
		
		return accessGraph;
	}
	
	/**
	 * This method is used to initialize all metrics used in the clustering algorithm
	 * @param componentCandidates The set of component candidates. They will be used to limit the
	 * size of the caching graph which is sent to the metric instances. 
	 * @return The initialized set of metrics mapped on their IDs
	 * @throws AnalyzerRuleException If the initialization of a metric fails, an {@link AnalyzerRuleException} is thrown 
	 */
	private Map<MetricID, IMetric> initializeMetrics(List<ComponentImplementingClassesLink> componentCandidates) {
		Map<MetricID, IMetric> allMetrics = MetricsRegistry.getRegisteredMetrics();
		
		DirectedGraph<Type, ClassAccessGraphEdge> accessGraph = getAccessGraph(componentCandidates);
		for (IMetric metric : allMetrics.values()) {
			metric.initialize(gastModel, somoxConfiguration, allMetrics, accessGraph, this.componentToImplementingClassHelper );
		}
		
		return allMetrics;
	}

	/**
	 * Create projected graph from one with evaluated metrics. Only edges passing the threshold are
	 * contained in the resulting graph.
	 * @param currentComposeThreshold Current threshold to check against for compose cases.
	 * @param currentMergeThreshold Current threshold to check against for merge cases.
	 * @param isMergeIteration indicates compose or merge case
	 * @param componentIndicatingGraph The original graph.
	 * @return The projected graphs with removed edges.
	 */
	private DirectedGraph<ComponentImplementingClassesLink, ClusteringRelation> createProjectedGraph(
			double currentComposeThreshold,
			double currentMergeThreshold,
			boolean isMergeIteration,
			DirectedGraph<ComponentImplementingClassesLink, ClusteringRelation> componentIndicatingGraph) {

		if(isMergeIteration) {

			VertexTypeAndEdgeThresholdFilter filter = new VertexTypeAndEdgeThresholdFilter(mergeIndicatingMetric.getMID(), currentMergeThreshold);			
			return
				new DirectedSubgraph<ComponentImplementingClassesLink, ClusteringRelation>(
						componentIndicatingGraph, 
						componentIndicatingGraph.vertexSet(), 
						FilteredCollectionsFactory.getFilteredHashSet(filter,componentIndicatingGraph.edgeSet()));
			
		} else {
			
			EdgeThresholdFilter filter = new EdgeThresholdFilter(compositionIndicatingMetric.getMID(), currentComposeThreshold);
			return
				new DirectedSubgraph<ComponentImplementingClassesLink, ClusteringRelation>(
						componentIndicatingGraph, 
						componentIndicatingGraph.vertexSet(), 
						FilteredCollectionsFactory.getFilteredHashSet(filter,componentIndicatingGraph.edgeSet()));
		}
	}	

	/**
	 * For the given list of potential components, i.e., classes, compute a triangular matrix of metrics indicating the 
	 * relationship of the two classes.
	 * @param componentCandidates The list of potential components
	 * @param metricComputationStrategy A class which encapsulates the computation of the metrics.  
	 * 		The top level metric which is to be computed (merge or compose).
	 * @param progressMonitor The progress monitor used to indicate clustering progress
	 * @param minThreshold Minimal threshold to check against.
	 * @return The elements of the triangular matrix showing the relationship of all classes pairwise
	 * @throws ModelAnalyzerException Thrown if the metric computation fails unexpectedly
	 */
	private DirectedGraph<ComponentImplementingClassesLink, ClusteringRelation> computeAllMetrics(
			List<ComponentImplementingClassesLink> componentCandidates, 
			IMetric metricComputationStrategy, 
			DirectedGraph<ComponentImplementingClassesLink, ClusteringRelation> previousGraph,
			IProgressMonitor progressMonitor,
			double minThreshold) throws ModelAnalyzerException {
		
		Collection<NodePair> work = deriveComputationWork(componentCandidates,previousGraph);
		int totalCount = work.size();

		IProgressMonitor clusteringProgressMonitor = new SubProgressMonitor(progressMonitor,totalCount);
		long startTimeClustering = System.nanoTime();
		logger.debug("Creating weighted directed graph for "+componentCandidates.size() + " components.");
				
		for (NodePair nodePair : work) {
			completionService.submit(nodePair.getWorkTask(metricComputationStrategy,allMetrics));
		}
		
		try {
			for (int stepNo = 0; stepNo < totalCount; stepNo++) {
				ClusteringRelation[] computedRelationPair = completionService.take().get();
				for (ClusteringRelation relation : computedRelationPair) {
					addEdgeToGraph(previousGraph, relation, metricComputationStrategy, minThreshold);
				}
				logger.debug(stepNo * 100 / totalCount + "% of clustering done.");
			}
		} catch (InterruptedException e) {
			throw new RuntimeException("Parallel execution failed unexpectedly",e);
		} catch (ExecutionException e) {
			throw new RuntimeException("Parallel execution failed unexpectedly",e);
		}

		long clusteringTime = System.nanoTime() - startTimeClustering;
		logger.debug("TIME for Compute All Metrics: " + TimeUnit.NANOSECONDS.toSeconds(clusteringTime) + " s");			

		clusteringProgressMonitor.done();
		
		return previousGraph;
	}

	private Collection<NodePair> deriveComputationWork(
			List<ComponentImplementingClassesLink> componentCandidates,
			DirectedGraph<ComponentImplementingClassesLink, ClusteringRelation> previousGraph) {
		Set<ComponentImplementingClassesLink> newNodes = new HashSet<ComponentImplementingClassesLink>();
		Set<ComponentImplementingClassesLink> nodesToRemove = new HashSet<ComponentImplementingClassesLink>();
		
		for (ComponentImplementingClassesLink link : previousGraph.vertexSet()) {
			if (!componentCandidates.contains(link)) {
				nodesToRemove.add(link);
			}
		}
		
		previousGraph.removeAllVertices(nodesToRemove);
		Set<ComponentImplementingClassesLink> oldNodesSet = new HashSet<ComponentImplementingClassesLink>(previousGraph.vertexSet());

		for (ComponentImplementingClassesLink link : componentCandidates) {
			if (!previousGraph.vertexSet().contains(link)) {
				newNodes.add(link);
				previousGraph.addVertex(link);
			}
		}
				
		assert Collections.disjoint(newNodes, oldNodesSet);
		
		int totalCount = newNodes.size() * (newNodes.size() - 1) / 2 +
						 newNodes.size() * oldNodesSet.size();
		
		Collection<NodePair> pairsToCompute = derivePairsToCompute(newNodes,oldNodesSet);
		assert pairsToCompute.size() == totalCount;
		
		return pairsToCompute;
	}

	private Collection<NodePair> derivePairsToCompute(
			Set<ComponentImplementingClassesLink> newNodes,
			Set<ComponentImplementingClassesLink> oldNodesSet) {
		Set<NodePair> result = new HashSet<NodePair>();
		for (ComponentImplementingClassesLink oldNode : oldNodesSet) {
			for (ComponentImplementingClassesLink newNode : newNodes) {
				result.add(new NodePair(newNode,oldNode));
			}
		}
		for (ComponentImplementingClassesLink newNode1 : newNodes) {
			for (ComponentImplementingClassesLink newNode2 : newNodes) {
				if (newNode1 != newNode2) {
					NodePair newPair = new NodePair(newNode1, newNode2);
					if (!result.contains(newPair)) {
						result.add(newPair);
					}
				}
			}
		}
		return result;
	}

	private DirectedGraph<ComponentImplementingClassesLink, ClusteringRelation> setupGraph(
			List<ComponentImplementingClassesLink> componentCandidates) {
		DirectedGraph<ComponentImplementingClassesLink, ClusteringRelation> result = 
			new SimpleDirectedGraph<ComponentImplementingClassesLink, ClusteringRelation>(ClusteringRelation.class);
		return result;
	}

	/**
	 * Adds top level metric result to graph edge.  
	 * @param result
	 * @param relation
	 * @param threshold The current threshold to check against; add edge only if threshold exceeded.
	 */
	private void addEdgeToGraph(
			Graph<ComponentImplementingClassesLink, ClusteringRelation> result,
			ClusteringRelation relation,
			IMetric topLevelMetric,
			double threshold) {
		if (relation.getResult().get(topLevelMetric.getMID()) > threshold) {
			logger.debug(relation.getComponentA()+" --"+ relation.getResult().get(topLevelMetric.getMID()) +"--> "+relation.getComponentB());
			result.addEdge(relation.getComponentA(),relation.getComponentB(),relation);
		}
	}
	
	/**
	 * Perform the actual clustering of classes into composite components
	 * @param sammBuilder Builder strategy
	 * @param relationshipGraph The triangular matrix containing the metrics for the relationship of pairwise classes
	 * @param iteration current iteration count
	 * @param isMergeCase indicates a merge or compose creation 
	 * @return A new list of component candidates which resulted from merging old component candidates into new
	 * 		   components plus all non-clustered components
	 */
	private List<ComponentImplementingClassesLink> componentComposition(
			ComponentBuilder sammBuilder,
			DirectedGraph<ComponentImplementingClassesLink, ClusteringRelation> relationshipGraph,
			int iteration,
			boolean isMergeCase) {

		LinkedList<ComponentImplementingClassesLink> result = new LinkedList<ComponentImplementingClassesLink>();
	
		if (logger.isTraceEnabled()) {
			logger.trace(relationshipGraph.toString());
		}
		ConnectivityInspector<ComponentImplementingClassesLink, ClusteringRelation> connectivityInspector = 
			new ConnectivityInspector<ComponentImplementingClassesLink, ClusteringRelation>(relationshipGraph);
		
		List<Set<ComponentImplementingClassesLink>> subGraphs = connectivityInspector.connectedSets();
		logger.debug("Found "+subGraphs.size()+" strong components in relation graph.");
		
		int subgraphNo = 1;
		for (Set<ComponentImplementingClassesLink> componentsToMerge : subGraphs) {
			if(componentsToMerge.size() > 1) {
				
				logger.debug("Found a cluster of " + componentsToMerge.size() + " related components. Merging them into a composite component");
				Graph<ComponentImplementingClassesLink, ClusteringRelation> compositeComponentSubgraph = 
					new Subgraph<ComponentImplementingClassesLink, ClusteringRelation, 
					DirectedGraph<ComponentImplementingClassesLink,ClusteringRelation>>(relationshipGraph, componentsToMerge);
				
				// debug:
				if (compositeComponentSubgraph.edgeSet().size() > 0 && logger.isTraceEnabled()) {
					GraphPrinter.dumpGraph(this.componentToImplementingClassHelper,compositeComponentSubgraph, 
							somoxConfiguration.getFileLocations().getAnalyserInputFile(), iteration, subgraphNo++);
				}
								
				// trigger the builders:
				ComponentImplementingClassesLink newComponent = null;
				if(isMergeCase) {
					newComponent = 
						sammBuilder.createMergedComponent(compositeComponentSubgraph);
				} else {				
					newComponent = 
						sammBuilder.createCompositeComponent(compositeComponentSubgraph);
				}
				result.add(newComponent);
			} else {
				result.addAll(componentsToMerge);
			}	
		} 
		
		return result;
	}

	private IMetric getMetric(Map<MetricID, IMetric> allMetrics, MetricID metricId) {
		IMetric result = allMetrics.get(metricId);
		
		if (result == null)
			throw new RuntimeException("Configuration error, Metric "+metricId+" needed but not available");
		
		return result;
	}
}
