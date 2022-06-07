package org.somox.gast2seff.resources;

public class SimpleClass {

	public void ifMethod() {
		System.out.println("Hello World!");
		
		if (true) {
			System.out.println("Inside the ifStatement.");
		}
	}
	
	public void forMethod() {
		System.out.println("Hello World!");
		
		for (int i = 0; i < 10; i++) {
			System.out.println("Current position: " + i);
		}

	}
	
	public void whileMethod() {
		System.out.println("Hello World!");
		
		int i = 0;
		boolean work = true;
		while (work) {
			System.out.println("Current position: " + i);
			work = false;
		}

	}
	
	public void ifAndForMethod() {
		System.out.println("Hello World!");
		
		if (true) {
			System.out.println("Inside the ifStatement.");
			
			for (int i = 0; i < 10; i++) {
				System.out.println("Current position: " + i);
			}
			
		}
	}
	
	public void ifAndElseMethod(boolean decision) {
		System.out.println("Hello World!");
				
		if (decision) {
			System.out.println("Inside the ifStatement.");
		} else {
			System.out.println("Inside the elseStatement.");
		}
	}
	
	public void ifAndElseIfMethod(boolean decision, boolean decision2) {
		System.out.println("Hello World!");
				
		if (decision) {
			System.out.println("Inside the ifStatement.");
		} else if (decision2) {
			System.out.println("Inside the elseIfStatement.");
		} else {
			System.out.println("Inside the elseStatement.");
		}
	}
	
	public void switchMethod(String caseString) {
		System.out.println("Hello World!");
				
		switch (caseString) {
		case "first":
			System.out.println("Inside the first case.");
			break;
		case "second":
			System.out.println("Inside the second case.");
			break;
		case "third":
			System.out.println("Inside the third case.");
		case "fourth":
			System.out.println("Inside the third/fourth case.");
			break;
		default:
			System.out.println("Inside the default case.");
			break;
		}
	}
	
	public void callExternalMethod(String caseString) {
		this.ifMethod();
		this.ifAndElseMethod(true);
		this.ifAndElseMethod(false);
	}
	
}
