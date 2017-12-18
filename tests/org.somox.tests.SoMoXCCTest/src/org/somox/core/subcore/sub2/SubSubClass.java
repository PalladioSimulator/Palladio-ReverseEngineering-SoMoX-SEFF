package org.somox.core.subcore.sub2;

import org.somox.core.subcore.MyClass1;
import org.somox.core.subcore.MyClass2;

public class SubSubClass {
    MyClass1 mc1 = new MyClass1();
    MyClass2 mc2 = new MyClass2();

    public void doSth() {
        this.mc1.doSth();
    }
}
