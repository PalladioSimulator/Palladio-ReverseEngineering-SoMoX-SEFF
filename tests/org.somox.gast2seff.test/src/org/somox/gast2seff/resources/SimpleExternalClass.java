package org.somox.gast2seff.resources;

public class SimpleExternalClass {

	public void externalCallMethod(SimpleClass externalClass) {
		externalClass.ifAndElseMethod(true);
		externalClass.ifAndElseIfMethod(false, true);
		externalClass.whileMethod();
		this.internalCallMethod();
	}
	
	private void internalCallMethod() {
		System.out.println("just some logging");
	}
	
	// TODO: Parameterübergabe
	// TODO: External Calls
}
