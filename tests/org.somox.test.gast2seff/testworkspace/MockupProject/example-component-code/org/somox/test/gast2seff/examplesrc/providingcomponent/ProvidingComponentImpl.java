package org.somox.test.gast2seff.examplesrc.providingcomponent;

import org.somox.test.gast2seff.examplesrc.contracts.InterfaceA;

public class ProvidingComponentImpl implements InterfaceA {

    @Override
    public void testExternalCall() {
        System.out.println("testExternalCall");
    }

    @Override
    public String testExternalCallWithSimpleParametersAndReturnType(final String str1, final String str2) {
        System.out.println("testExternalCallWithSimpleParametersAndReturnType: str1: " + str1 + " str2: " + str2
                + " returning: " + str1 + str2);
        return str1 + str2;
    }

}
