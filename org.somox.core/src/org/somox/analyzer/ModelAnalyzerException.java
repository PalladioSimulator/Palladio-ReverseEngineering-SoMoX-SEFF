package org.somox.analyzer;

public class ModelAnalyzerException extends Exception {

	public ModelAnalyzerException(String msg, Exception e) {
		super(msg,e);
	}

	public ModelAnalyzerException(String msg) {
		super(msg);
	}

}
