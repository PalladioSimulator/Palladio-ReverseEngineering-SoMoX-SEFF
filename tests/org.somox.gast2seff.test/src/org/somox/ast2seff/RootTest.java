package org.somox.ast2seff;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.junit.jupiter.api.Test;

public class RootTest {
	
    @Test
    public void testCompilationUnit() {
    	AST ast = AST.newAST(AST.getJLSLatest(), false);
    	CompilationUnit compUnit = ast.newCompilationUnit();
    	
    }
}
