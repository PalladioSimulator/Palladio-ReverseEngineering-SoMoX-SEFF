package org.annotationsmox.graphlearner;

public interface PathIntegrationListener {

    /**
     * @param originalPath
     *            the unmodified path before integration of {@code addPath}; {@code null}, if a path
     *            is integrated into an empty {@link SPGraph}.
     * @param addPath
     *            the path to be integrated into {@code originalPath}
     * @param combinedPath
     *            the path resulting from integrating {@code addPath} into {@code originalPath}
     * 
     */
    void notifyIntegration(Path originalPath, Sequence<?> addPath, Path combinedPath);

    default void notifyClosestPath(Path path) {};
    
}
