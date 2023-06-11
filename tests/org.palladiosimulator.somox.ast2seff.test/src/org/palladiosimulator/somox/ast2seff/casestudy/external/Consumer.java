package org.palladiosimulator.somox.ast2seff.casestudy.external;

public class Consumer {
    public void consume() {
        Provider provider = new Provider();
        provider.provide();
    }
}
