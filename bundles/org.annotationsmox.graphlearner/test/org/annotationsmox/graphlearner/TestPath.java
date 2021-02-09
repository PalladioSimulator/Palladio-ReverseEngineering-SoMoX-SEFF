package org.annotationsmox.graphlearner;

import org.annotationsmox.graphlearner.Path;
import org.annotationsmox.graphlearner.util.PathBuilder;
import org.junit.Assert;
import org.junit.Test;

/**
 * Tests the {@link Path} class.
 */
public class TestPath {

    @Test
    public void testCommonPrefixLength0() {
        Path p1 = PathBuilder.path("A", "B");
        Path p2 = PathBuilder.path("C");

        Path actual = Path.commonPrefix(p1, p2);
        Path expected = Path.emptyPath();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testCommonPrefixLength1() {
        Path p1 = PathBuilder.path("A", "B");
        Path p2 = PathBuilder.path("A");

        Path actual = Path.commonPrefix(p1, p2);
        Path expected = PathBuilder.path("A");
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testCommonPrefixLength2SamePaths() {
        Path p1 = PathBuilder.path("A", "B");
        Path p2 = PathBuilder.path("A", "B");

        Path actual = Path.commonPrefix(p1, p2);
        Path expected = PathBuilder.path("A", "B");
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testCommonPrefixLength2DifferentPaths() {
        Path p1 = PathBuilder.path("A", "B");
        Path p2 = PathBuilder.path("A", "B", "C");

        Path actual = Path.commonPrefix(p1, p2);
        Path expected = PathBuilder.path("A", "B");
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testCommonPrefixLength2MultiplePaths() {
        Path p1 = PathBuilder.path("A", "B");
        Path p2 = PathBuilder.path("A", "B", "C");
        Path p3 = PathBuilder.path("A", "B", "D");
        Path p4 = PathBuilder.path("A", "B", "E", "F");

        Path actual = Path.commonPrefix(p1, p2, p3, p4);
        Path expected = PathBuilder.path("A", "B");
        Assert.assertEquals(expected, actual);
    }

}
