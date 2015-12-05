package org.somox.metrics.naming;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;
import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.emftext.language.java.types.Type;
import org.jgrapht.DirectedGraph;
import org.somox.configuration.SoMoXConfiguration;
import org.somox.kdmhelper.KDMHelper;
import org.somox.kdmhelper.metamodeladdition.Root;
import org.somox.metrics.ClusteringRelation;
import org.somox.metrics.IMetric;
import org.somox.metrics.MetricID;
import org.somox.metrics.abstractmetrics.AbstractMetric;
import org.somox.metrics.helper.ClassAccessGraphEdge;
import org.somox.metrics.helper.ComponentToImplementingClassesHelper;
import org.somox.metrics.tabs.MetricTab;
import org.somox.metrics.tabs.NameResemblanceTab;

import uk.ac.shef.wit.simmetrics.similaritymetrics.InterfaceStringMetric;
import uk.ac.shef.wit.simmetrics.similaritymetrics.JaroWinkler;
//import de.fzi.gast.core.Root;
//import de.fzi.gast.types.GASTClass;

class NamePair {
    private final Type class1;
    private final Type class2;

    public NamePair(final Type class1, final Type class2) {
        super();

        if (class1 == null || class2 == null) {
            throw new IllegalArgumentException("Names must not be null");
        }
        this.class1 = class1;
        this.class2 = class2;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return this.class1.hashCode() ^ this.class2.hashCode();
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        final NamePair other = (NamePair) obj;
        return this.class1 == other.class1 && this.class2 == other.class2
                || this.class2 == other.class1 && this.class1 == other.class2;
    }
}

/**
 * NameResemblance metric. Counts for all {@link Type}s in any of the two component candidates the
 * similar names and divides it by the total amount of names. The names are trimmed before they are
 * compared, i.e., their prefixes and suffixes are removed as configured on this metric's
 * configuration tab.
 *
 * @author Grischa Liebel, Steffen Becker
 *
 */
public class NameResemblance extends AbstractMetric {
    public static final MetricID METRIC_ID = new MetricID("org.somox.metrics.NameResemblance");

    private static final String DELIMITER = "��";

    private static Logger logger = Logger.getLogger(NameResemblance.class);

    private static final InterfaceStringMetric resemblanceMetric = new JaroWinkler();

    /**
     * Cache the pairwise computed name resemblances for a given pair of strings
     */
    private Map<NamePair, Float> nameResemblanceMap;

    /**
     * Set with prefix Strings that will be excluded in every metric-computation
     */
    private Set<String> excludedPrefixes;

    /**
     * Set with suffix Strings that will be excluded in every metric-computation
     */
    private Set<String> excludedSuffixes;

    /*
     * (non-Javadoc)
     *
     * @see org.somox.metrics.Metric#initialize(de.fzi.gast.core.Root,
     * org.somox.configuration.SoMoXConfiguration, java.util.Map, org.jgrapht.DirectedGraph)
     */
    @Override
    public void initialize(final Root gastModel, final SoMoXConfiguration somoxConfiguration,
            final Map<MetricID, IMetric> allMetrics,
            final DirectedGraph<ConcreteClassifier, ClassAccessGraphEdge> accessGraph,
            final ComponentToImplementingClassesHelper componentToImplementingClassesHelper) {
        super.initialize(gastModel, somoxConfiguration, allMetrics, accessGraph, componentToImplementingClassesHelper);
        this.nameResemblanceMap = new HashMap<NamePair, Float>();

        this.excludedPrefixes = this.tokenizeString(somoxConfiguration.getExcludedPrefixesForNameResemblance());
        this.excludedSuffixes = this.tokenizeString(somoxConfiguration.getExcludedSuffixesForNameResemblance());

        for (final Type class1 : accessGraph.vertexSet()) {
            for (final Type class2 : accessGraph.vertexSet()) {
                Float resemblance = this.nameResemblanceMap.get(new NamePair(class1, class2));
                if (resemblance == null) {
                    resemblance = resemblanceMetric.getSimilarity(this.trimString(KDMHelper.getName(class1)),
                            this.trimString(KDMHelper.getName(class2)));
                    this.nameResemblanceMap.put(new NamePair(class1, class2), resemblance);
                }
            }
        }
        this.nameResemblanceMap = Collections.unmodifiableMap(this.nameResemblanceMap);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void internalComputeDirected(final ClusteringRelation relationToCompute) {

        // TODO: Klaus, check for plausibility. Rationale is that the inner classes' names of a
        // composite component
        // should not be compared for their names, otherwise we end up with a composite component
        // containing everything
        // in the end...
        // if (componentCandidate1.isCompositeComponent() ||
        // componentCandidate2.isCompositeComponent())
        // return 0.0;

        final Set<ConcreteClassifier> classes1 = this.getComponentToClassHelper()
                .deriveImplementingClasses(relationToCompute.getSourceComponent());
        final Set<ConcreteClassifier> classes2 = this.getComponentToClassHelper()
                .deriveImplementingClasses(relationToCompute.getTargetComponent());

        final int totalCompares = classes1.size() * classes2.size();

        double nameResemblance = 0.0;
        for (final Type class1 : classes1) {
            for (final Type class2 : classes2) {
                final Float resemblance = this.nameResemblanceMap.get(new NamePair(class1, class2));
                if (resemblance == null) {
                    throw new RuntimeException("This should not happen as all classes are precomputed");
                }
                nameResemblance += resemblance;
            }
        }
        if (totalCompares == 0) {
            logger.debug("Resemblance Map had a size of 0");
            relationToCompute.setResultMetric(this.getMID(), 0.0);
            return;
        }

        relationToCompute.setResultMetric(this.getMID(), nameResemblance / totalCompares);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isCommutative() {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MetricTab getLaunchConfigurationTab() {
        return new NameResemblanceTab();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MetricID getMID() {
        return METRIC_ID;
    }

    /**
     * Helper interface for a strategy to manipulate a string in a certain way. Used to encapsulate
     * the strategies to remove prefixes and suffixes
     */
    private interface IStringChangerStrategy {
        /**
         * @param s
         *            String to manipulate
         * @param parameter
         *            Strategy specific parameter
         * @return true if the given string s needs to be manipulated under the given parameter
         */
        public boolean isAffected(String s, String parameter);

        /**
         * @param s
         *            String to manipulate
         * @param parameter
         *            Strategy specific parameter
         * @return The modified string according to this strategy
         */
        public String modify(String s, String parameter);
    }

    /**
     * Strategy which removes the prefix given as parameter from the given string
     */
    private static final IStringChangerStrategy prefixRemover = new IStringChangerStrategy() {

        @Override
        public String modify(final String s, final String parameter) {
            return s.substring(parameter.length());
        }

        @Override
        public boolean isAffected(final String s, final String parameter) {
            return s.startsWith(parameter);
        }
    };

    /**
     * Strategy which removes the suffix given as parameter from the given string
     */
    private static final IStringChangerStrategy suffixRemover = new IStringChangerStrategy() {

        @Override
        public String modify(final String s, final String parameter) {
            return s.substring(0, s.length() - parameter.length());
        }

        @Override
        public boolean isAffected(final String s, final String parameter) {
            return s.endsWith(parameter);
        }
    };

    private Set<String> collectAllSimpleNames(final Set<Type> classes) {
        final Set<String> allSimpleTrimmedNames = new HashSet<String>();
        for (final Type clazz : classes) {
            allSimpleTrimmedNames.add(this.trimString(KDMHelper.getName(clazz)));
        }
        return allSimpleTrimmedNames;
    }

    /**
     * Helper method to remove all prefixes and all suffixes from a string
     *
     * @param name
     *            string to trim
     * @return trimmed string
     */
    private String trimString(final String name) {
        String result = this.modifyString(name, this.excludedPrefixes, prefixRemover);
        result = this.modifyString(name, this.excludedSuffixes, suffixRemover);
        return result;
    }

    private String modifyString(String name, final Set<String> parameters, final IStringChangerStrategy strategy) {
        boolean changed = true;
        while (changed) {
            changed = false;
            for (final String currentParameter : parameters) {
                if (strategy.isAffected(name, currentParameter)) {
                    changed = true;
                    name = strategy.modify(name, currentParameter);
                    break;
                }
            }
        }
        return name;
    }

    private Set<String> tokenizeString(final String nameResemblancePrefixString) {
        final HashSet<String> nameResemblancePrefixes = new HashSet<String>();
        final StringTokenizer tokenizer = new StringTokenizer(nameResemblancePrefixString, DELIMITER);

        while (tokenizer.hasMoreTokens()) {
            nameResemblancePrefixes.add(tokenizer.nextToken());
        }

        return Collections.unmodifiableSet(nameResemblancePrefixes);
    }

    @Override
    public boolean isNormalised() {
        return true;
    }
}
