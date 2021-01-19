package org.somox.filter;

import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.emftext.language.java.members.Member;
import org.somox.kdmhelper.GetAccessedType;

//import de.fzi.gast.accesses.Access;
//import de.fzi.gast.types.GASTClass;

/**
 * This class filters a list of Accesses based on the accessed class. If the accessed class is in
 * the classifier filter, then the current access is also removed.
 *
 * @author Steffen Becker
 */
public class AccessedTargetBlacklistFilter extends BaseFilter<Member> {
    private BaseFilter<ConcreteClassifier> classifierFilter;;

    public AccessedTargetBlacklistFilter(final BaseFilter<ConcreteClassifier> classifierFilter) {
        super();
        if (classifierFilter == null) {
            throw new IllegalArgumentException("Blacklistfilter must not be null");
        }
        this.classifierFilter = classifierFilter;
    }

    @Override
    public boolean passes(final Member access) {
        final org.emftext.language.java.types.Type accessedClass = GetAccessedType.getAccessedType(access);
        if (accessedClass == null && !(accessedClass instanceof ConcreteClassifier)) {
            return false;
        }
        return this.classifierFilter.passes((ConcreteClassifier) accessedClass);
    }

    public BaseFilter<ConcreteClassifier> getClassifierFilter() {
        return this.classifierFilter;
    }
}
