package org.somox.kdmhelper;

public class MethodPalladioInformation {

	private String methodName;
	private String operationSignatureName;
	private String operationInterfaceName;

	public MethodPalladioInformation(String methodName, String operationSignatureName, String operationInterfaceName) {
		this.methodName = methodName;
		this.operationSignatureName = operationSignatureName;
		this.operationInterfaceName = operationInterfaceName;
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
}
