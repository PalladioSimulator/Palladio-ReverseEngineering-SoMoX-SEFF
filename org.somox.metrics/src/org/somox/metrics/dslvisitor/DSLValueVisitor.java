package org.somox.metrics.dslvisitor;

import org.somox.metrics.dSL.Constant;
import org.somox.metrics.dSL.Parameter;
import org.somox.metrics.dSL.util.DSLSwitch;

public class DSLValueVisitor extends DSLSwitch<Double> {

	/* (non-Javadoc)
	 * @see org.somox.metrics.dSL.util.DSLSwitch#caseConstant(org.somox.metrics.dSL.Constant)
	 */
	@Override
	public Double caseConstant(Constant object) {
		return object.getValue();
	}

	/* (non-Javadoc)
	 * @see org.somox.metrics.dSL.util.DSLSwitch#caseParameter(org.somox.metrics.dSL.Parameter)
	 */
	@Override
	public Double caseParameter(Parameter object) {
		// TODO Auto-generated method stub
		return super.caseParameter(object);
	}

	
}
