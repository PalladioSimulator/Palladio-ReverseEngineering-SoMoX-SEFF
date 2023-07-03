package org.palladiosimulator.somox.ast2seff.util;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.FileASTRequestor;

public class CaseStudyAstParser {
    private final Path directory;

    public CaseStudyAstParser(Path directory) {
        this.directory = directory;
    }

    public Map<String, CompilationUnit> parseCasestudyPackage() {
        return CaseStudyAstParser.parseCasestudyPackage(this.directory);
    }

    public static Map<String, CompilationUnit> parseCasestudyPackage(Path directory) {
        ASTParser parser = getJavaParser();
        String[] classpathEntries = getEntries(directory, ".jar");
        final String[] sources = getEntries(directory, ".java");
        final String[] encodings = new String[sources.length];
        Arrays.fill(encodings, StandardCharsets.UTF_8.toString());
        final Map<String, CompilationUnit> compilationUnits = new HashMap<>();
        try {
            parser.setEnvironment(classpathEntries, new String[0], new String[0], true);
            parser.createASTs(sources, encodings, new String[0], new FileASTRequestor() {
                @Override
                public void acceptAST(final String sourceFilePath, final CompilationUnit ast) {
                    compilationUnits.put(sourceFilePath, ast);
                }
            }, new NullProgressMonitor());
        } catch (IllegalArgumentException | IllegalStateException exception) {
            exception.printStackTrace();
        }
        return compilationUnits;
    }

    private static String[] getEntries(Path dir, String suffix) {
        try (Stream<Path> paths = Files.walk(dir)) {
            return paths
                    .filter(path -> Files.isRegularFile(path)
                            && path.getFileName().toString().toLowerCase().endsWith(suffix))
                    .map(Path::toAbsolutePath).map(Path::normalize).map(Path::toString).toArray(i -> new String[i]);
        } catch (final IOException e) {
            e.printStackTrace();
            return new String[0];
        }
    }

    private static ASTParser getJavaParser() {
        String javaCoreVersion = JavaCore.latestSupportedJavaVersion();
        final ASTParser parser = ASTParser.newParser(AST.getJLSLatest());
        parser.setResolveBindings(true);
        parser.setBindingsRecovery(true);
        parser.setStatementsRecovery(true);
        parser.setCompilerOptions(Map.of(JavaCore.COMPILER_SOURCE, javaCoreVersion, JavaCore.COMPILER_COMPLIANCE,
                javaCoreVersion, JavaCore.COMPILER_CODEGEN_TARGET_PLATFORM, javaCoreVersion));
        return parser;
    }
}
