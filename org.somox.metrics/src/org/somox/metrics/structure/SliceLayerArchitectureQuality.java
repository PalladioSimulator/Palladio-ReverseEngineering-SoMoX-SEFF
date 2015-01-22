package org.somox.metrics.structure;

import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.emftext.language.java.types.Type;
import org.somox.kdmhelper.KDMHelper;
import org.somox.metrics.ClusteringRelation;
import org.somox.metrics.MetricID;
import org.somox.metrics.abstractmetrics.AbstractMetric;

//import de.fzi.gast.types.GASTClass;

/**
 * SliceLayerArchitectureQuality (SLAQ) metric
 *
 * @author Grischa Liebel
 *
 */
public class SliceLayerArchitectureQuality extends AbstractMetric {
    public static final MetricID METRIC_ID = new MetricID("org.somox.metrics.SliceLayerArchitectureQuality");

    // implemented abstract methods from Metric

    /**
     * {@inheritDoc}
     */
    @Override
    protected void internalComputeDirected(final ClusteringRelation relationToCompute) {

        // removelater
        // java.util.List<Type> type1 = relationToCompute.getComponentA().getImplementingClasses();
        // java.util.List<Type> type2 = relationToCompute.getComponentB().getImplementingClasses();
        // if(type1!= null & type2!=null & type1.size()>0 & type2.size()>0){
        // if(type1.get(0).getName().equals("Store") &
        // type2.get(0).getName().equals("ApplicationEventHandlerImpl")){
        // System.out.println("a");
        // }
        // }

        final Set<ConcreteClassifier> classes1 = this.getComponentToClassHelper().deriveImplementingClasses(
                relationToCompute.getSourceComponent());
        final Set<ConcreteClassifier> classes2 = this.getComponentToClassHelper().deriveImplementingClasses(
                relationToCompute.getTargetComponent());

        // compute overall prefix
        final org.emftext.language.java.containers.Package prefixPackage = this.computePrefix(classes1, classes2);

        if (prefixPackage == null) {
            relationToCompute.setResultMetric(this.getMID(), 0.0);
            return;
        }
        final EList<org.emftext.language.java.containers.Package> slices = KDMHelper.getOwnedPackages(prefixPackage);
        EList<org.emftext.language.java.containers.Package> layers = null;

        // compute the maximum number of layers in a slice
        int max = 0;
        for (final org.emftext.language.java.containers.Package current : slices) {
            if (KDMHelper.getOwnedPackages(current).size() >= max) {
                layers = KDMHelper.getOwnedPackages(current);
                max = layers.size();
            }
        }

        // check how many of the computed layers exist in every slice
        if (max == 0) {
            relationToCompute.setResultMetric(this.getMID(), 1.0);
            return;
        } else {
            final int expectedSubsystems = slices.size() * layers.size();
            int existingSubsystems = 0;

            for (final org.emftext.language.java.containers.Package currentSlice : slices) {
                final EList<org.emftext.language.java.containers.Package> currentLayers = KDMHelper
                        .getOwnedPackages(currentSlice);
                for (final org.emftext.language.java.containers.Package currentReferencePackage : layers) {
                    for (final org.emftext.language.java.containers.Package currentLayer : currentLayers) {
                        if (currentLayer.getName().equals(currentReferencePackage.getName())) {
                            existingSubsystems++;
                            break;
                        }
                    }
                }
            }

            if (expectedSubsystems == 0) {
                relationToCompute.setResultMetric(this.getMID(), 1.0);
            } else {
                relationToCompute.setResultMetric(this.getMID(), (double) existingSubsystems
                        / (double) expectedSubsystems);
            }
        }
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
    public MetricID getMID() {
        return METRIC_ID;
    }

    /**
     * Computes the longest prefix for the given elements
     *
     * @param elements1
     *            first list of elements
     * @param elements2
     *            second list of elements
     * @return the last package in the package-hierarchy in which all elements are included
     */
    private org.emftext.language.java.containers.Package computePrefix(final Set<ConcreteClassifier> elements1,
            final Set<ConcreteClassifier> elements2) {
        String prefix = "";
        boolean prefixFound = false;
        org.emftext.language.java.containers.Package currentPackage = null;

        for (final Type current : elements1) {
            if (KDMHelper.getSurroundingPackage(current) != null) {
                currentPackage = KDMHelper.getSurroundingPackage(current);
                break;
            }
        }

        if (currentPackage == null) {
            for (final Type current : elements2) {
                if (KDMHelper.getSurroundingPackage(current) != null) {
                    currentPackage = KDMHelper.getSurroundingPackage(current);
                    break;
                }
            }
        }

        if (currentPackage == null) {
            return null;
        }

        prefix = KDMHelper.computeFullQualifiedName(currentPackage);

        while (!prefixFound) {
            prefixFound = true;

            for (final ConcreteClassifier current : elements1) {
                if (!KDMHelper.isInnerClass(current)) {
                    if (KDMHelper.getSurroundingPackage(current) != null) {
                        if (!KDMHelper.computeFullQualifiedName(KDMHelper.getSurroundingPackage(current)).contains(
                                prefix)) {
                            prefixFound = false;
                            break;
                        }
                    }
                }
            }

            if (!prefixFound) {
                // currentPackage = currentPackage.getPackage();
                if (currentPackage == null) {
                    return null;
                } else {
                    prefix = KDMHelper.computeFullQualifiedName(currentPackage);
                }
            }
        }
        prefixFound = false;

        while (!prefixFound) {
            prefixFound = true;

            for (final ConcreteClassifier current : elements2) {
                if (!KDMHelper.isInnerClass(current)) {
                    if (KDMHelper.getSurroundingPackage(current) != null) {
                        if (!KDMHelper.computeFullQualifiedName(KDMHelper.getSurroundingPackage(current)).contains(
                                prefix)) {
                            prefixFound = false;
                            break;
                        }
                    }
                }
            }

            if (!prefixFound) {
                // currentPackage = currentPackage.getPackage();
                if (currentPackage == null) {
                    return null;
                } else {
                    prefix = KDMHelper.computeFullQualifiedName(currentPackage);
                }
            }
        }
        return currentPackage;
    }

    @Override
    public boolean isNormalised() {
        return true;
    }
}
