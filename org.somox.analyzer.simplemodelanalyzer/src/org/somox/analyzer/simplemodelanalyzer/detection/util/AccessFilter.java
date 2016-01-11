package org.somox.analyzer.simplemodelanalyzer.detection.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.emftext.language.java.types.Type;
import org.emftext.language.java.types.TypeReference;
//import org.eclipse.gmt.modisco.java.Commentable;
//import org.eclipse.gmt.modisco.java.ClassInstanceCreation;
//import org.eclipse.gmt.modisco.java.EnumConstantDeclaration;
//import org.eclipse.gmt.modisco.java.SingleVariableAccess;
//import org.eclipse.gmt.modisco.java.Type;
//import org.eclipse.gmt.modisco.java.TypeAccess;
import org.somox.filter.EClassBasedFilter;
import org.somox.kdmhelper.GetAccessedType;
import org.somox.kdmhelper.KDMHelper;

//import de.fzi.gast.accesses.Access;
//import de.fzi.gast.accesses.CompositeAccess;
//import de.fzi.gast.types.GASTClass;

/**
 * Blacklist-based filtering of access types.
 *
 * @author kelsaka
 *
 */
public class AccessFilter {

    private static Logger logger = Logger.getLogger(AccessFilter.class);

    /**
     * Filters a black list of access types
     *
     * @param allAccesses
     * @param blacklistedAccessTypes
     * @return List of accessed GASTClasses after filtering blacklisted access types
     */
    public static List<ConcreteClassifier> filterAccessList(final List<TypeReference> allAccesses,
            final EClassBasedFilter<TypeReference> filter) {

        final ArrayList<ConcreteClassifier> returnAccessedClasses = new ArrayList<ConcreteClassifier>();

        for (final TypeReference access : filter.filter(allAccesses)) {

            // if (KDMHelper.isInheritanceTypeAccess(access)) {// REALLYADDED//SOMOXTODOCHANGE
            // // logger.warn("found Inheritance type access, will not be considered");
            // continue;// REALLYADDED//SOMOXTODOCHANGE
            // }// REALLYADDED//SOMOXTODOCHANGE

            final Type accessedType = GetAccessedType.getAccessedType(access);
            if (accessedType != null && accessedType instanceof ConcreteClassifier) {
                // composite accesses are not considered
                returnAccessedClasses.add((ConcreteClassifier) accessedType);
            } else {
                logger.warn("found empty access: accessed class null, " + KDMHelper.getSISSyID(access));
            }
        }

        return returnAccessedClasses;
    }
}
