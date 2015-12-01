package fr.tbr.iamcore.services.match.impl;

import fr.tbr.iamcore.datamodel.Identity;
import fr.tbr.iamcore.services.match.Matcher;

public class StartsWithIdentityMatcher implements Matcher<Identity> {

	@Override
	public boolean match(Identity criteria, Identity toBeMatched) {
		return toBeMatched.getEmail().startsWith(criteria.getEmail())
				|| toBeMatched.getDisplayName().startsWith(	criteria.getDisplayName());
	}

}
