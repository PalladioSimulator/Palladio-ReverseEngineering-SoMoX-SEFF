package org.somox.metrics.parameter;

public class ParameterDescriptor {
	private final String shortName;
	private final String description;
	private final double defaultValue;
	public ParameterDescriptor(String shortName, String description,
			double defaultValue) {
		super();
		this.shortName = shortName;
		this.description = description;
		this.defaultValue = defaultValue;
	}
	/**
	 * @return the shortName
	 */
	public String getShortName() {
		return shortName;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @return the defaultValue
	 */
	public double getDefaultValue() {
		return defaultValue;
	}
}
