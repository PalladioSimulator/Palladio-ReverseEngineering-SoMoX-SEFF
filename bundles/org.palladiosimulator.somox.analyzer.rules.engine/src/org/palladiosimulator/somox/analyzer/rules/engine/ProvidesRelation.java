package org.palladiosimulator.somox.analyzer.rules.engine;

import java.util.stream.Collectors;

import org.emftext.language.java.classifiers.Classifier;
import org.emftext.language.java.members.Method;
import org.emftext.language.java.parameters.Parameter;

// Encapsulates the operation interface and method a component is providing.
// This class is required because a method from the java model itself does not contain a reference to the interface it comes from like variables do.
public class ProvidesRelation {
    private final Classifier operationInterface;
    private final Method method;

    public ProvidesRelation(Classifier operationInterface, Method method) {
        super();
        this.operationInterface = operationInterface;
        this.method = method;
    }

    @Override
    public String toString() {
        String parameterString = "";
        parameterString += method.getParameters().stream().map(Parameter::getName).collect(Collectors.joining(","));
        return (operationInterface.getName() + ": " + method.getName() + "(" + parameterString+")");
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = (prime * result) + (toString().hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ProvidesRelation other = (ProvidesRelation) obj;
        if (!toString().equals(other.toString())) {
            return false;
        }
        return true;
    }

    public Classifier getOperationInterface() {
        return operationInterface;
    }

    public Method getMethod() {
        return method;
    }

}
