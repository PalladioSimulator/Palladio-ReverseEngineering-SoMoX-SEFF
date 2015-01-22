package org.somox.filter;

import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.emftext.language.java.members.Member;
import org.somox.kdmhelper.GetAccessedType;

//import de.fzi.gast.accesses.Access;
//import de.fzi.gast.types.GASTClass;

/**
 * This class filters a list of Accesses based on the accessed class. If the accessed class is in
 * the blacklist then the current access is also removed.
 *
 * @author Steffen Becker
 */
public class AccessedTargetBlacklistFilter extends BaseFilter<Member> {
    private BlacklistFilter blacklistFilter = null;

    public AccessedTargetBlacklistFilter(final BlacklistFilter blacklistFilter) {
        super();
        if (blacklistFilter == null) {
            throw new IllegalArgumentException("Blacklistfilter must not be null");
        }
        this.blacklistFilter = blacklistFilter;
    }

    @Override
    public boolean passes(final Member access) {
        final org.emftext.language.java.types.Type accessedClass = GetAccessedType.getAccessedType(access);
        if (accessedClass == null && !(accessedClass instanceof ConcreteClassifier)) {
            return false;
        }
        return this.blacklistFilter.passes((ConcreteClassifier) accessedClass);
    }

    public BlacklistFilter getBlacklist() {
        return this.blacklistFilter;
    }
}
