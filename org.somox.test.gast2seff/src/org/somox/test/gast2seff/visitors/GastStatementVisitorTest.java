package org.somox.test.gast2seff.visitors;

import org.emftext.language.java.members.ClassMethod;
import org.emftext.language.java.statements.Statement;
import org.junit.Test;
import org.somox.gast2seff.visitors.BasicFunctionClassificationStrategy;
import org.somox.gast2seff.visitors.FunctionCallClassificationVisitor;
import org.somox.gast2seff.visitors.GastStatementVisitor;

import de.uka.ipd.sdq.pcm.repository.BasicComponent;
import de.uka.ipd.sdq.pcm.seff.ResourceDemandingSEFF;
import de.uka.ipd.sdq.pcm.seff.SeffFactory;

public class GastStatementVisitorTest extends Gast2SEFFBaseTest {

    @Test
    public void testGastStatementVisitor() {
        final String methodName = super.getTestMethodName();
        final ClassMethod method = (ClassMethod) super.findMethodInClassifier(methodName, REQUIRED_COMPONENT_NAME
                + "Impl");
        final BasicComponent basicComponent = (BasicComponent) super.findComponentInPCMRepo(REQUIRED_COMPONENT_NAME);
        final ResourceDemandingSEFF seff = SeffFactory.eINSTANCE.createResourceDemandingSEFF();
        final BasicFunctionClassificationStrategy basicFunctionClassifierStrategy = new BasicFunctionClassificationStrategy(
                this.sourceCodeDecorator, basicComponent, this.compilationUnits);
        final FunctionCallClassificationVisitor functionCallClassificationVisitor = new FunctionCallClassificationVisitor(
                basicFunctionClassifierStrategy);
        final GastStatementVisitor gastStatementVisitor = new GastStatementVisitor(
                functionCallClassificationVisitor.getAnnotations(), seff, this.sourceCodeDecorator, basicComponent);

        // to the test
        for (final Statement statement : method.getStatements()) {
            functionCallClassificationVisitor.doSwitch(statement);
            gastStatementVisitor.doSwitch(statement);
        }
    }
}
