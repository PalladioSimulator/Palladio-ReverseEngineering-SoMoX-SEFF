package org.somox.analyzer.simplemodelanalyzer.builder;

import org.somox.analyzer.AnalysisResult;
import org.somox.configuration.SoMoXConfiguration;
import org.somox.kdmhelper.metamodeladdition.Root;

//import de.fzi.gast.core.Root;

public class AbstractBuilder {

	protected AnalysisResult analysisResult = null;
	protected Root gastModel = null;
	protected SoMoXConfiguration somoxConfiguration;

	public AbstractBuilder (Root gastModel,
			SoMoXConfiguration somoxConfiguration, 
			AnalysisResult analysisResult) {
		
		super();

		this.analysisResult = analysisResult;
		this.gastModel = gastModel;
		this.somoxConfiguration = somoxConfiguration;
	}

}