package org.annotationsmox.graphlearner;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.annotationsmox.graphlearner.Path;

public class TestUtils {

    public static String pathToString(Path path) {
        return path.excludeNonLeaves().excludeEpsilon().toString();
    }

    public static Set<String> pathToSetOfStrings(List<Path> allPaths) {
        Set<String> allPathString = new HashSet<>();
        allPaths.forEach(p -> allPathString.add(pathToString(p)));
        return allPathString;
    }

}
