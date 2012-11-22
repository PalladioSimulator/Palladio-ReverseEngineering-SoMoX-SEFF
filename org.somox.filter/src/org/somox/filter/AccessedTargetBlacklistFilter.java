package org.somox.filter;

import de.fzi.gast.accesses.Access;
import de.fzi.gast.types.GASTClass;

/**
 * This class filters a list of Accesses based on the accessed class. If the accessed class is in the blacklist then
 * the current access is also removed.
 * 
 * @author Steffen Becker
 */
public class AccessedTargetBlacklistFilter extends BaseFilter<Access> {
	private BlacklistFilter blacklistFilter = null;
	
	public AccessedTargetBlacklistFilter(BlacklistFilter blacklistFilter) {
		super();
		if (blacklistFilter == null) {
			throw new IllegalArgumentException("Blacklistfilter must not be null");
		}
		this.blacklistFilter = blacklistFilter;
	}

	@Override
	public boolean passes(Access access) {
		GASTClass accessedClass = access.getAccessedClass(); 
		if (accessedClass == null)
			return false;
		return blacklistFilter.passes(accessedClass);
	}

	public BlacklistFilter getBlacklist() {
		return blacklistFilter;
	}
}
