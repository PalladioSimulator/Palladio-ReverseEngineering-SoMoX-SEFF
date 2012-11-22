package org.somox.metrics;

public class MetricID {

	private final String metricID;
	
	public MetricID(String id) {
		super();
		
		if(id == null) {
			throw new IllegalArgumentException("Metric ID must not be null");
		}
		this.metricID = id;
	}
	
	public String getMetricID() {
		return metricID;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		return ((MetricID)obj).getMetricID() == metricID ||
			   ((MetricID)obj).getMetricID().equals(metricID);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return metricID.hashCode();
	}
	
	@Override
	public String toString() {
		return metricID;
	}
}