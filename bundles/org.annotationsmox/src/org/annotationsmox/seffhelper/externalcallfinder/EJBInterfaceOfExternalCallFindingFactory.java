package org.annotationsmox.seffhelper.externalcallfinder;

import org.palladiosimulator.pcm.repository.BasicComponent;
import org.somox.gast2seff.visitors.InterfaceOfExternalCallFinding;
import org.somox.gast2seff.visitors.InterfaceOfExternalCallFindingFactory;
import org.somox.sourcecodedecorator.SourceCodeDecoratorRepository;

public class EJBInterfaceOfExternalCallFindingFactory implements InterfaceOfExternalCallFindingFactory{
	public InterfaceOfExternalCallFinding createInterfaceOfExternalCallFinding(
            final SourceCodeDecoratorRepository sourceCodeDecoratorRepository, final BasicComponent basicComponent) {
        return new EJBInterfaceOfExternalCallFinder(sourceCodeDecoratorRepository, basicComponent);
    }
}
