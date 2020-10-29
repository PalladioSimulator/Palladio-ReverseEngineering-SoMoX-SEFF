package org.somox.filter.tests;

import org.eclipse.jdt.core.ICompilationUnit;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

/**
 * Detector of test source files. Implements the well known strategy pattern (GOF).
 *
 * @author Joshua Gleitze
 */
public interface TestDetector {

    /**
     * Decides for provided {@code compilationUnit} whether it is a test source file. If a
     * {@code Boolean} is returned, it determines surely whether the unit is a test or not.
     * Alternatively, an empty {@code Optional} is returned, indicating that no sure decision about
     * the file can be made by {@code this} detector.
     *
     * <p>
     * Detectors will usually always return the same {@code Boolean} value if they make a decision.
     * This means that a detector will usually only decide whether a file <em>is surely</em> or
     * <em>is surely not</em> a test source file, but not both.
     *
     * @param compilationUnit
     *            The path to a java file to check.
     * @return An optional of {@link Boolean#TRUE} only if {@code compilationUnit} is a test, an
     *         optional of {@link Boolean#FALSE} only if {@code CompilationUnit} is not a test, an
     *         empty optional if no sure decision can be made.
     * @throws IOException
     *             If reading the {@code compilationUnit} fails.
     */
    Optional<Boolean> isTest(Path compilationUnit) throws IOException;

    /**
     * Indicates whether {@code this} detector’s {@link #isTest(ICompilationUnit)} method may return
     * an optional of {@link Boolean#TRUE}.
     *
     * <p>
     * This method is used to optimise performance when evaluating test detectors. Its default
     * implementation always returns {@code true}.
     *
     * @return {@code false} iff {@code #isTestSourceFile(Path)} will never return an optional of
     *         {@link Boolean#TRUE}.
     */
    default boolean mayReturnTrue() {
        return true;
    }

    /**
     * Indicates whether {@code this} detector’s {@link #isTest(ICompilationUnit)} method may return
     * an optional of {@link Boolean#FALSE}.
     *
     * <p>
     * This method is used to optimise performance when evaluating test detectors. Its default
     * implementation always returns {@code true}.
     *
     * @return {@code false} iff {@code #isTestSourceFile(Path)} will never return an optional of
     *         {@link Boolean#FALSE}.
     */
    default boolean mayReturnFalse() {
        return true;
    }
}
