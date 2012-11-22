package org.somox.core;

public class Helper {
	
	IRequired1 irq1;
	
	public void execHelper() {
		for(int x = 0; x < 2; x++) {
			if(true) {
				irq1.doSth();
			}
		}
	}
	
	public void execHelper2(int i) {
		irq1.doSth();
	}
}
