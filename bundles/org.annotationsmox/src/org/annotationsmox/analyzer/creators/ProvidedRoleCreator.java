package org.annotationsmox.analyzer.creators;

import java.util.Collection;

import org.palladiosimulator.pcm.repository.BasicComponent;
import org.palladiosimulator.pcm.repository.EventGroup;
import org.palladiosimulator.pcm.repository.Interface;
import org.palladiosimulator.pcm.repository.OperationInterface;
import org.palladiosimulator.pcm.repository.OperationProvidedRole;
import org.palladiosimulator.pcm.repository.RepositoryFactory;
import org.palladiosimulator.pcm.repository.SinkRole;

public class ProvidedRoleCreator {

	public void createProvidedRoles(final BasicComponent basicComponent, final Collection<Interface> providedInterfaces) {
		for (org.palladiosimulator.pcm.repository.Interface pcmInterface : providedInterfaces) {
			if (pcmInterface instanceof OperationInterface) {
				createOperationProvidedRole(basicComponent, (OperationInterface) pcmInterface);
			} else if (pcmInterface instanceof EventGroup) {
				createSinkRole(basicComponent, (EventGroup) pcmInterface);
			} else {
				throw new RuntimeException("Interface " + pcmInterface + "not supported yet");
			}
		}
	}
	
	private void createOperationProvidedRole(final BasicComponent basicComponent,
			final OperationInterface providedOpInterface) {
		final OperationProvidedRole opr = RepositoryFactory.eINSTANCE.createOperationProvidedRole();
		opr.setProvidedInterface__OperationProvidedRole(providedOpInterface);
		opr.setProvidingEntity_ProvidedRole(basicComponent);
		opr.setEntityName(basicComponent.getEntityName() + "_provides_" + providedOpInterface.getEntityName());
	}

	private void createSinkRole(BasicComponent basicComponent, EventGroup eventGroup) {
		final SinkRole sinkRole = RepositoryFactory.eINSTANCE.createSinkRole();
		sinkRole.setEventGroup__SinkRole(eventGroup);
		sinkRole.setProvidingEntity_ProvidedRole(basicComponent);
		sinkRole.setEntityName(basicComponent.getEntityName() + "_provides_" + sinkRole.getEntityName());
	}

}
