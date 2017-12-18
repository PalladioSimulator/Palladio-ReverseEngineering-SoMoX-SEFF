package org.somox.gast2seff.visitors;

import org.palladiosimulator.pcm.repository.BasicComponent;
import org.somox.sourcecodedecorator.SourceCodeDecoratorRepository;

/**
 * InterfaceOfExternalCallFindingStrategy allows the creation of project or technology specific
 * {@link InterfaceOfExternalCallFinding}, which is used within {@link JaMoPPStatementVisitor}.
 * 
 * @author langhamm
 *
 */
public interface InterfaceOfExternalCallFindingFactory {
    default InterfaceOfExternalCallFinding createInterfaceOfExternalCallFinding(
            final SourceCodeDecoratorRepository sourceCodeDecoratorRepository, final BasicComponent basicComponent) {
        return new DefaultInterfaceOfExternalCallFinder(sourceCodeDecoratorRepository, basicComponent);
    }
}
