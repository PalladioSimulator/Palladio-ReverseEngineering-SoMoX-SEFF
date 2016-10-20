package org.somox.filter.tests;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.emftext.language.java.containers.CompilationUnit;
import org.somox.filter.BaseFilter;
import org.somox.filter.BlacklistFilter;

public class TestFileFilter extends BaseFilter<ConcreteClassifier> {

    private static final Logger logger = Logger.getLogger(BlacklistFilter.class);

    /**
     * Caches the results of {@link #isTestFile(ConcreteClassifier)}.
     */
    private final Map<ConcreteClassifier, Boolean> cache = new HashMap<>();

    /**
     * The detectors that are used to determine whether a file is a test file. Because ordering
     * affects performance, this is not a set.
     */
    private final TestDetector[] detectors = new TestDetector[] { new MavenProjectStructureTestFileDetector(),
            new JUnitAnnotationTestDetector() };

    @Override
    public boolean passes(ConcreteClassifier object) {
        return !cache.computeIfAbsent(object, this::isTestFile);
    }

    /**
     * Determines whether the provided {@code classifier} is a test file, using the
     * {@link #detectors}. The method’s behaviour is equivalent to {@link #isTestFile(Path)}.
     *
     * @param classifier
     *            A JaMoPP concrete classifier.
     * @return Whether the provided {@code classifier} is a test file.
     */
    private boolean isTestFile(final ConcreteClassifier classifier) {
        final CompilationUnit compilationUnit = classifier.getContainingCompilationUnit();
        if (compilationUnit == null) {
            return false;
        }
        final Path compilationUnitPath = EmfResource.getPath(compilationUnit.eResource());
        if (compilationUnitPath == null) {
            return false;
        }
        final boolean isTestFile = this.isTestFile(compilationUnitPath);
        if (isTestFile) {
            logger.debug("Excluded \"" + compilationUnitPath + "\" because it’s a test file.");
        }
        return isTestFile;
    }

    /**
     * Determines whether the provided {@code file} is a test file, using the {@link #detectors}. A
     * file is a test file if any detector’s {@link TestDetector#isTest(Path)} returns {@code true}
     * and no detector returns {@code false}.
     *
     * @param file
     *            The path to a java source file.
     * @return Whether the provided {@code file} is a test file.
     */
    private boolean isTestFile(final Path file) {
        // indicates whether any detector thinks the file may be a test file. As long as
        // no other detector returns {@code false}, the file will be regarded as a test
        // file.
        boolean mayBeTestFile = false;

        for (final TestDetector detector : this.detectors) {
            // don’t run the detector if we don’t need it
            if (!mayBeTestFile || detector.mayReturnFalse()) {
                final Optional<Boolean> result;
                try {
                    result = detector.isTest(file);
                } catch (final IOException ioError) {
                    // If the detector fails, we skip it.
                    continue;
                }
                if (!result.orElse(true)) {
                    // if any detector returns {@code false}, the file is definitely not a
                    // test file.
                    return false;
                } else if (result.orElse(false)) {
                    mayBeTestFile = true;
                }
            }
        }
        return mayBeTestFile;
    }

}
