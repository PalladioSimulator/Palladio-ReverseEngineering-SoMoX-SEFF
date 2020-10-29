package org.somox.analyzer;

public class ModelAnalyzerException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public ModelAnalyzerException(final String msg, final Exception e) {
        super(msg, e);
    }

    public ModelAnalyzerException(final String msg) {
        super(msg);
    }

}
