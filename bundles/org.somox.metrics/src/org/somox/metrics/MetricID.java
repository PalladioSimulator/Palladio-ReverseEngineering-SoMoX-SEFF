package org.somox.metrics;

/**
 * A string based identifier of a metric used in SoMoX.
 *
 * @author snowball
 */
public class MetricID {

    private final String metricID;

    public MetricID(final String id) {
        super();

        if (id == null) {
            throw new IllegalArgumentException("Metric ID must not be null");
        }

        this.metricID = id;
    }

    public String getMetricID() {
        return this.metricID;
    }

    @Override
    public String toString() {
        return this.metricID;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.metricID == null) ? 0 : this.metricID.hashCode());
        return result;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        final MetricID other = (MetricID) obj;
        if (!this.metricID.equals(other.metricID)) {
            return false;
        }
        return true;
    }

}