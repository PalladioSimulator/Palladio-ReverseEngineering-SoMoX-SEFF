package org.somox.filter.tests;

import java.nio.file.Path;
import java.util.Optional;

/**
 * Detects a file as being a test source file if its path contains {@code src/test/java}.
 *
 * <h2>Rationale</h2>
 *
 * <p>In the maven project structure convention, test logic written in Java belongs into
 * the project’s {@code src/test/java} folder. Prefixes of that path are allowed because a
 * file could contain test logic of a subproject. This project structure is widely used as
 * it’s being put forward by both Maven and Gradle.
 *
 * <p>The strategy is unlikely to produce false positives, as in practice, a developer
 * would not put production code in a folder called {@code test}. {@code src.test.java} is
 * also very unlikely to be chosen as a part of a package path.
 *
 * @author Joshua Gleitze
 */
public class MavenProjectStructureTestFileDetector implements TestDetector {

	/**
	 * A part a path must contain to be regarded as a test file.
	 */
	private static final String TEST_PART = "src/test/java";

	@Override
	public Optional<Boolean> isTest(final Path path) {
		if (path.toString().replace("\\", "/").contains(TEST_PART)) {
			return Optional.of(Boolean.TRUE);
		}
		return Optional.empty();
	}

	@Override
	public boolean mayReturnFalse() {
		return false;
	}

}
