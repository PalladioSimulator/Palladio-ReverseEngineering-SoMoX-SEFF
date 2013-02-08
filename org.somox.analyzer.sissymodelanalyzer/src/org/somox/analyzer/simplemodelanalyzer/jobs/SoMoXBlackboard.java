package org.somox.analyzer.simplemodelanalyzer.jobs;

import org.somox.analyzer.AnalysisResult;

import de.uka.ipd.sdq.workflow.Blackboard;
import eu.qimpress.seff.SeffRepository;

/**
 * Blackboard to transfer SoMoX analysis results between jobs of the overall SoMoX run. 
 * @author Oliver Burkhardt
 * @author Benjamin Klatt
 *
 */
public class SoMoXBlackboard extends Blackboard<Object> {

	
	
	/** The result to hand over between jobs. */
	private AnalysisResult analysisResult = null;

	/** The seff repository created in addition to the generic somox analysis result. */
	private SeffRepository seffRepository = null;
	
	/**
	 * Get the analysis result.
	 * @return the analysisResult
	 */
	public AnalysisResult getAnalysisResult() {
		return analysisResult;
	}

	/**
	 * Set the analysis result.
	 * @param analysisResult the analysisResult to set
	 */
	public void setAnalysisResult(AnalysisResult analysisResult) {
		this.analysisResult = analysisResult;
	}

	/**
	 * @return the seffRepository
	 */
	public SeffRepository getSeffRepository() {
		return seffRepository;
	}

	/**
	 * @param seffRepository the seffRepository to set
	 */
	public void setSeffRepository(SeffRepository seffRepository) {
		this.seffRepository = seffRepository;
	}
	
	
}
