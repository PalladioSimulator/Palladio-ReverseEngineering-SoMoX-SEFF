package org.palladiosimulator.somox.analyzer.rules.engine;

import org.emftext.language.java.containers.impl.CompilationUnitImpl;

/**
* This interface has to be implemented in order to write rules.
* The method will be used by the RuleEngine class to process all written rule lines which are inside the method.
*/
public interface IRule {
    void processRules(CompilationUnitImpl unitImpl);
}