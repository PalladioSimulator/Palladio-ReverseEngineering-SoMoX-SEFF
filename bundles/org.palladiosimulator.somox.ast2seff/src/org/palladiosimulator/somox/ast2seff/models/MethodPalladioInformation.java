package org.palladiosimulator.somox.ast2seff.models;

public class MethodPalladioInformation {

	private String methodName;
	private String operationSignatureName;
	private String operationInterfaceName;
	private MethodBundlePair methodBundlePair;

	public MethodPalladioInformation(String methodName, String operationSignatureName, String operationInterfaceName, MethodBundlePair methodBundlePair) {
		this.methodName = methodName;
		this.operationSignatureName = operationSignatureName;
		this.operationInterfaceName = operationInterfaceName;
		this.methodBundlePair = methodBundlePair;
	}

	public String getMethodName() {
		return methodName;
	}

	public String getOperationSignatureName() {
		return operationSignatureName;
	}

	public String getOperationInterfaceName() {
		return operationInterfaceName;
	}

	public MethodBundlePair getMethodBundlePair() {
		return methodBundlePair;
	}


}
