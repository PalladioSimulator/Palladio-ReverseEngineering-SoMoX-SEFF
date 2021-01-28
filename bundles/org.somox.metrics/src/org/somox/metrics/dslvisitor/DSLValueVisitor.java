package org.somox.metrics.dslvisitor;

import org.somox.metrics.dsl.metricDSL.Constant;
import org.somox.metrics.dsl.metricDSL.Parameter;
import org.somox.metrics.dsl.metricDSL.util.MetricDSLSwitch;

public class DSLValueVisitor extends MetricDSLSwitch<Double> {

    /* (non-Javadoc)
     * @see org.somox.metrics.dSL.util.DSLSwitch#caseConstant(org.somox.metrics.dSL.Constant)
     */
    @Override
    public Double caseConstant(final Constant object) {
        return object.getValue();
    }

    /* (non-Javadoc)
     * @see org.somox.metrics.dSL.util.DSLSwitch#caseParameter(org.somox.metrics.dSL.Parameter)
     */
    @Override
    public Double caseParameter(final Parameter object) {
        // TODO: Read value from configuration!!!
        return object.getDefaultValue();
    }
}
