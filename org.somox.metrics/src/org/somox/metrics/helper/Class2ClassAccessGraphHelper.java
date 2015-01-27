package org.somox.metrics.helper;

import java.util.Set;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EObject;
import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.emftext.language.java.types.Type;
import org.emftext.language.java.types.TypeReference;
import org.jgrapht.DirectedGraph;
import org.jgrapht.EdgeFactory;
import org.jgrapht.Graph;
import org.jgrapht.graph.SimpleDirectedGraph;
import org.somox.configuration.SoMoXConfiguration;
import org.somox.filter.AccessedTargetBlacklistFilter;
import org.somox.filter.EClassBasedFilter;
import org.somox.kdmhelper.GetAccessedType;
import org.somox.kdmhelper.KDMHelper;
import org.somox.metrics.util.GraphPrinter;

//import de.fzi.gast.accesses.Access;
//import de.fzi.gast.accesses.accessesPackage;
//import de.fzi.gast.types.GASTClass;

/**
 * A class used as filter to filter the available classes being potential component candidates based
 * on a blacklist of component names
 *
 * @author Michael Hauck, Klaus Krogmann, Steffen Becker
 */
public class Class2ClassAccessGraphHelper {

    private static final Logger logger = Logger.getLogger(Class2ClassAccessGraphHelper.class);

    private static final EdgeFactory<ConcreteClassifier, ClassAccessGraphEdge> edgeFactory = new EdgeFactory<ConcreteClassifier, ClassAccessGraphEdge>() {
        @Override
        public ClassAccessGraphEdge createEdge(final ConcreteClassifier source, final ConcreteClassifier target) {
            return new ClassAccessGraphEdge(source, target);
        }
    };

    /**
     * Computes a map which maps full qualified class names of source classes (FQN) on a set
     * containing tuples (GASTClass, count). Each tuple contains a class which is directly accessed
     * by the source class and the number of (different) accesses from the source to each target
     * class. Visually this is a graph containing the GAST classes as nodes and directed links
     * between the nodes if there is an access from source to target and the link has a weight
     * giving the number of "links" from source to target
     *
     * @param filter
     *            A list of regular expression patterns used to filter the nodes of GAST classes
     *            contained in the graph. Matching classes are not added to the graph
     * @param componentsImplementingClasses
     *            The list of classes used in the detected initial components. This list only
     *            contains classes filtered by the blacklist as the blacklist is also used in the
     *            initial component detection
     * @return A "graph" giving the connections of GAST classes and their number of accesses
     */
    public static DirectedGraph<ConcreteClassifier, ClassAccessGraphEdge> computeFilteredClass2ClassAccessGraph(
            final SoMoXConfiguration somoxConfiguration, final Set<ConcreteClassifier> componentsImplementingClasses) {

        final DirectedGraph<ConcreteClassifier, ClassAccessGraphEdge> accessGraph = new SimpleDirectedGraph<ConcreteClassifier, ClassAccessGraphEdge>(
                edgeFactory);
        for (final ConcreteClassifier clazz : componentsImplementingClasses) {
            accessGraph.addVertex(clazz);
        }

        assert noPrimitiveTypesAsVertexes(accessGraph);

        final AccessedTargetBlacklistFilter filter = new AccessedTargetBlacklistFilter(
                somoxConfiguration.getBlacklistFilter());

        for (final ConcreteClassifier clazz : componentsImplementingClasses) {
            addAccessesToGraph(accessGraph, filter, clazz);
        }

        assert noPrimitiveTypesAsVertexes(accessGraph);

        if (logger.isDebugEnabled()) {
            GraphPrinter.dumpGraph(new ComponentToImplementingClassesHelper(), accessGraph, somoxConfiguration
                    .getFileLocations().getAnalyserInputFile(), 0, 0);
        }
        return accessGraph;
    }

    private static boolean noPrimitiveTypesAsVertexes(
            final DirectedGraph<ConcreteClassifier, ClassAccessGraphEdge> accessGraph) {
        boolean result = true;
        for (final Type clazz : accessGraph.vertexSet()) {
            result &= !KDMHelper.isPrimitive(clazz);
        }
        return result;
    }

    private static final EClassBasedFilter<TypeReference> accessFilter = new EClassBasedFilter<TypeReference>() {
        // new
        // EClass[]{/**accessesPackage.eINSTANCE.getInheritanceTypeAccess()**/});//SOMOXTODOCHANGE
        @Override
        // REALLYADDED
        public boolean passes(final EObject object) {// REALLYADDED
            if (object != null && object instanceof TypeReference) {// REALLYADDED
                if (KDMHelper.isInheritanceTypeAccess((TypeReference) object)) {// REALLYADDED
                    return false;// REALLYADDED
                }// REALLYADDED
            }// REALLYADDED
            return true;// REALLYADDED
        }// REALLYADDED
    };

    /**
     * Compute the outgoing links for the node containing class "clazz". Links pointing to classes
     * which match the blacklist pattern are not created
     *
     * @param filter
     *            Blacklist match pattern. Used to remove potential targets
     * @param clazz
     *            The class for which to compute the outgoing links
     * @return A set of target classes and their link weights. Weights model the number of accesses
     *         between clazz and its respective target
     */
    private static void addAccessesToGraph(final Graph<ConcreteClassifier, ClassAccessGraphEdge> graph,
            final AccessedTargetBlacklistFilter filter, final ConcreteClassifier clazz) {

        for (final TypeReference singleAccess : accessFilter.filter(KDMHelper.getAllAccesses(clazz))) {// TODO
            // Check

            // if(singleAccess != null & singleAccess instanceof TypeAccess){ //SOMOXTODOCHANGE was
            // added here because removed in accessFilter creation
            // if(GASTClassHelper.isInheritanceTypeAccess((TypeAccess) singleAccess)){
            // System.out.println("was ita");
            // continue;
            // }
            // }

            final Type accessedType = GetAccessedType.getAccessedType(singleAccess);
            if (!(accessedType instanceof ConcreteClassifier)) {
                continue;
            }
            final ConcreteClassifier accessedClass = (ConcreteClassifier) accessedType;
            // Relations between the class itself are not interesting...
            if (clazz == accessedClass) {
                continue;
            }

            ClassAccessGraphEdge edge = graph.getEdge(clazz, accessedClass);
            if (edge == null) {
                if (graph.containsVertex(clazz) && graph.containsVertex(accessedClass)) {
                    edge = graph.addEdge(clazz, accessedClass);
                } else {
                    logger.trace("Do not add edge between " + clazz + " and " + accessedClass);
                }
            }
            if (edge != null) {
                edge.incrementCount();
            }
        }
    }
}
