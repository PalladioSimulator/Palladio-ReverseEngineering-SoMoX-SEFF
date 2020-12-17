package org.palladiosimulator.somox.analyzer.rules.all;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.palladiosimulator.somox.analyzer.rules.engine.IRule;
import org.palladiosimulator.somox.analyzer.rules.jax_rs.JaxRSRules;
import org.palladiosimulator.somox.analyzer.rules.spring.SpringRules;

/**
* This enum contains all default rule technologies the rule engine provides
*
* @param  url  an absolute URL giving the base location of the image
* @param  name the location of the image, relative to the url argument
* @return      the image at the specified URL
* @see         Image
*/
public enum DefaultRule {

	SPRING(new SpringRules()),
	JAX_RS(new JaxRSRules());

	private final IRule rule;

	private DefaultRule(IRule rule){
		this.rule = rule;
	}

	/**
	* Returns the names of all currently available default rule technologies.
	*
	* @return      the names of all available default rule technologies
	* @see         Image
	*/
	public static String[] valuesAsString() {
		String[] names = new String[DefaultRule.values().length];
		for(int i=0; i< DefaultRule.values().length; i++) {
			names[i] = DefaultRule.values()[i].name();
		}

		return names;
	}

	public IRule getRule() {
		return this.rule;
	}

}
