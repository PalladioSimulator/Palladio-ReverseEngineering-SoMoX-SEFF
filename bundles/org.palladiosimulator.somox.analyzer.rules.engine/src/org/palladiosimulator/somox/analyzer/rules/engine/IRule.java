package org.palladiosimulator.somox.analyzer.rules.engine;

import org.emftext.language.java.containers.impl.CompilationUnitImpl;

public interface IRule {
    void processRules(CompilationUnitImpl unitImpl);
}