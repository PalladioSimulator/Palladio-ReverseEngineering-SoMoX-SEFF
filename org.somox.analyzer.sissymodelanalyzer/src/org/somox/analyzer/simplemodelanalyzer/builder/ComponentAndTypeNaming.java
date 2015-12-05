package org.somox.analyzer.simplemodelanalyzer.builder;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Category;
import org.apache.log4j.Logger;
import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.emftext.language.java.types.Type;
import org.palladiosimulator.pcm.repository.Interface;
import org.palladiosimulator.pcm.repository.RepositoryComponent;
import org.somox.kdmhelper.KDMHelper;
import org.somox.kdmhelper.metamodeladdition.Root;
import org.somox.sourcecodedecorator.ComponentImplementingClassesLink;

/**
 * Component, package, interface, and port naming facility.
 *
 * @author Klaus Krogmann
 *
 */
public class ComponentAndTypeNaming {

    /**
     * General logger
     */
    private final Logger logger = Logger.getLogger(ComponentAndTypeNaming.class);
    /**
     * Counter for composite component names
     */
    private int compositeComponentNumber, primitiveComponentNumber, requiredPortNumber, providedPortNumber;

    private final int MAXIMUM_NAME_LENGTH = 55;
    private final int MAX_NUMBER_OF_PATH_SEGMENTS_IN_INTERFACE_NAME = 3;

    public ComponentAndTypeNaming() {
        this.compositeComponentNumber = 0;
        this.primitiveComponentNumber = 0;
        this.requiredPortNumber = 0;
        this.primitiveComponentNumber = 0;
    }

    @Deprecated
    public String createSimpleComponentName(final int i, final Type astClass) {
        // astClass.getName? => (Category)
        return "Comp. " + i + ": " + ((Category) astClass).getName();
    }

    /**
     * Creates a primitive component name
     *
     * @param gastClasses
     *            : inner classes of the component
     * @param shorten
     *            true: a short name
     * @return
     */
    public String createSimpleComponentName(final List<ConcreteClassifier> astClasses, final boolean shorten) {
        final StringBuilder nameBuilder = new StringBuilder();
        nameBuilder.append(" <PC No. " + this.primitiveComponentNumber++);

        final StringBuilder subComponentNames = new StringBuilder();
        for (final Type astClass : astClasses) {
            // use (CompilationUnit) casting for Type
            subComponentNames.append(" " + KDMHelper.computeFullQualifiedName(astClass));
        }

        if (shorten) {
            nameBuilder.append(this.shorten(subComponentNames.toString()));
        } else {
            nameBuilder.append(subComponentNames);
        }

        nameBuilder.append(">");

        return nameBuilder.toString();
    }

    public String createSimpleComponentName(final Type astClass) {
        return KDMHelper.computeFullQualifiedName(astClass) + " <PC No. " + this.primitiveComponentNumber++ + ">";
    }

    public String createSimpleComponentName(final int i, final List<ComponentImplementingClassesLink> currentList) {
        final StringBuilder sb = new StringBuilder();
        for (final ComponentImplementingClassesLink currentClassesLinkList : currentList) {
            for (final Type currentClass : currentClassesLinkList.getImplementingClasses()) {
                sb.append(KDMHelper.getName(currentClass) + " ");
            }
        }
        final String name = "Comp No. " + i + " " + sb.toString();
        return this.shorten(name);
    }

    /**
     * Short name
     *
     * @param innerComponents
     * @return
     */
    public String createCompositeComponentName(final Collection<ComponentImplementingClassesLink> innerComponents) {
        return this.createCompositeComponentName(innerComponents, true);
    }

    public String createCompositeComponentName(final Collection<ComponentImplementingClassesLink> innerComponents,
            final boolean shorten) {
        this.compositeComponentNumber++;
        final StringBuilder nameBuilder = new StringBuilder();
        nameBuilder.append("CC No. ");
        nameBuilder.append(this.compositeComponentNumber + " ");
        nameBuilder.append(this.createComponentNameBasedOnPackageName(this.compositeComponentNumber, innerComponents));
        nameBuilder.append(" <");

        // collect subcomponent names:
        final StringBuilder subComponentName = new StringBuilder();
        for (final ComponentImplementingClassesLink subcomponent : innerComponents) {
            if (subcomponent.getComponent() != null) { // empty for very initial components
                subComponentName.append(subcomponent.getComponent().getEntityName() + " ");
            } else {
                subComponentName.append(this.createSimpleComponentName(0, subcomponent.getSubComponents()) + " "); // 0:
                // default
                // level
            }
        }
        subComponentName.deleteCharAt(subComponentName.length() - 1);

        if (shorten) {
            nameBuilder.append(this.shorten(subComponentName.toString(), true)); // keep tail
            // intact; remove
            // start
        } else {
            nameBuilder.append(subComponentName.toString());
        }
        nameBuilder.append(">");

        return nameBuilder.toString();
    }

    /**
     * Search for the package name that occurs most often
     *
     * @param i
     *            running number
     * @param currentList
     *            List of classes belonging to the component
     * @return Component name
     */
    private String createComponentNameBasedOnPackageName(final int i,
            final Collection<ComponentImplementingClassesLink> currentList) {
        String returnComponentName = "";
        final HashMap<String, Integer> numberOfPackageNames = new HashMap<String, Integer>();
        final HashMap<String, String> packageNames = new HashMap<String, String>();
        String maxNumberPackageId = null;
        String directoryName = "";
        int maxNumber = 0;
        for (final ComponentImplementingClassesLink currentClassesLink : currentList) {
            for (final Type currentClass : currentClassesLink.getImplementingClasses()) {
                if (KDMHelper.getSurroundingPackage(currentClass) != null) {
                    Integer tmpNumber = numberOfPackageNames
                            .get(Root.getIdForPackage(KDMHelper.getSurroundingPackage(currentClass)));
                    if (tmpNumber != null) {
                        tmpNumber++;
                        numberOfPackageNames.put(Root.getIdForPackage(KDMHelper.getSurroundingPackage(currentClass)),
                                tmpNumber);
                        if (tmpNumber > maxNumber) {
                            maxNumber = tmpNumber;
                            maxNumberPackageId = Root.getIdForPackage(KDMHelper.getSurroundingPackage(currentClass));
                        }
                    } else {
                        numberOfPackageNames.put(Root.getIdForPackage(KDMHelper.getSurroundingPackage(currentClass)),
                                1);
                        packageNames.put(Root.getIdForPackage(KDMHelper.getSurroundingPackage(currentClass)),
                                KDMHelper.computeFullQualifiedName(KDMHelper.getSurroundingPackage(currentClass)));
                        if (1 > maxNumber) {
                            maxNumber = 1;
                            maxNumberPackageId = Root.getIdForPackage(KDMHelper.getSurroundingPackage(currentClass));
                        }
                    }
                } else if (KDMHelper.getJavaNodeSourceRegion(currentClass) != null) {
                    directoryName = KDMHelper.getJavaNodeSourceRegion(currentClass).getNamespacesAsString();
                } else {
                    this.logger.warn("found neither packages nor directories for GAST class "
                            + KDMHelper.computeFullQualifiedName(currentClass));
                }
            }
        }

        returnComponentName = "";
        if (maxNumber > 0) {
            final String compName = packageNames.get(maxNumberPackageId);
            if (compName != null) {
                returnComponentName += compName;
            }
        }

        if (!directoryName.equals("")) {
            returnComponentName += "(dir: " + directoryName + ")";
        }

        return this.shorten(returnComponentName);
    }

    public String createComponentInstanceName(final RepositoryComponent repositoryComponent) {
        if (repositoryComponent != null) {
            return repositoryComponent.getEntityName() + "-instance";
        } else {
            return "class-level-instance";
        }
    }

    /**
     * Creates the name for a provided interface
     *
     * @param provInterface
     * @param component
     * @return
     */
    public String createProvidedPortName(final Interface provInterface, final RepositoryComponent component) {
        String ifName = "ifName";
        if (null != provInterface) {
            ifName = provInterface.getEntityName();
            if (ifName.contains(".")) {
                final String[] subStrings = ifName.split("\\.", 0);
                ifName = subStrings[subStrings.length - 1]; // last segment
            }
            ifName += " " + this.providedPortNumber++;
        }
        return this.shorten(ifName) + " (prov)";
    }

    public String createProvidedSystemPortName(final Interface provInterface, final RepositoryComponent component) {
        return this.createProvidedPortName(provInterface, component) + "(sys)";
    }

    /**
     * Creates the name for a required interface
     *
     * @param reqInterface
     * @param component
     * @return
     */
    public String createRequiredPortName(final Interface reqInterface, final RepositoryComponent component) {

        String ifName = reqInterface.getEntityName();
        if (ifName.contains(".")) {
            final String[] subStrings = ifName.split("\\.", 0);
            ifName = subStrings[subStrings.length - 1]; // last segment
        }
        ifName += " " + this.requiredPortNumber++;

        return this.shorten(ifName) + " (req)";
    }

    public String createRequiredSystemPortName(final Interface reqInterface, final RepositoryComponent component) {
        return this.createRequiredPortName(reqInterface, component) + "(sys)";
    }

    /**
     * Interface name created for a real GAST interface class.
     *
     * @param interfaceClass
     * @return
     */
    public String createInterfaceName(final Type interfaceClass) {
        final String interfaceName = this.segmentBasedInterfaceName(KDMHelper.computeFullQualifiedName(interfaceClass));
        return this.shorten(interfaceName, true);
    }

    /**
     * Interface name created for a usual class which is not marked as an interface.
     *
     * @param interfaceClass
     * @return
     */
    public String createInterfaceNameForClass(final Type interfaceClass) {
        final String interfaceName = this.segmentBasedInterfaceName(KDMHelper.computeFullQualifiedName(interfaceClass));
        return "I" + this.shorten(interfaceName, true);
    }

    /**
     * Uses last x segments to create an Interface name
     *
     * @param qualifiedName
     * @return
     */
    private String segmentBasedInterfaceName(final String qualifiedName) {
        // last two segments:
        final String[] segments = qualifiedName.split("\\.");
        String interfaceName = "";

        boolean first = true;
        for (int i = this.MAX_NUMBER_OF_PATH_SEGMENTS_IN_INTERFACE_NAME; i > 0; i--) {
            if (segments.length >= i) {
                if (!first) {
                    interfaceName += ".";
                }
                interfaceName += segments[segments.length - i];
                first = false;
            }
        }
        return interfaceName;
    }

    /**
     * Shorten long strings
     *
     * @param theString
     *            string to shorten
     * @param removeStartOfString
     *            switch between removing trail or head of string
     * @return
     */
    private String shorten(final String theString, final boolean removeStartOfString) {
        String name = theString;
        if (theString.length() > this.MAXIMUM_NAME_LENGTH) {
            if (removeStartOfString) {
                name = "..."
                        + theString.substring(theString.length() - this.MAXIMUM_NAME_LENGTH + 3, theString.length());
            } else {
                name = theString.substring(0, this.MAXIMUM_NAME_LENGTH - 3) + "...";
            }
        }

        return name;
    }

    private String shorten(final String theString) {
        return this.shorten(theString, false);
    }
}
