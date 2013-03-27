package org.somox.analyzer.simplemodelanalyzer.builder.util;

import static org.junit.Assert.*;

import org.junit.Test;

import de.uka.ipd.sdq.pcm.resourceenvironment.ResourceEnvironment;
import de.uka.ipd.sdq.pcm.resourcetype.ProcessingResourceType;
import de.uka.ipd.sdq.pcm.resourcetype.ResourceRepository;
import de.uka.ipd.sdq.pcm.resourcetype.SchedulingPolicy;

/**
 * Must be run as a Junit-Plugin-Test!
 * @author kuester
 *
 */
public class TestDefaultResourceEnvironment {

	@Test
	public void testCreateDefaultResourceEnvironment() {
		// only check that something is created. 
		ResourceEnvironment env = DefaultResourceEnvironment.createDefaultResourceEnvironment();
		assertNotNull(env);
	}

	@Test
	public void testGetResourceRepository() {
		// only check that something is created. 
		ResourceRepository rep = DefaultResourceEnvironment.getResourceRepository();
		assertNotNull(rep);
	}

	@Test
	public void testGetCPUProcessingResourceType() {
		// only check that something is created. 
		ProcessingResourceType type = DefaultResourceEnvironment.getCPUProcessingResourceType();
		assertNotNull(type);
	}

	@Test
	public void testGetProcessorSharingSchedulingPolicy() {
		// only check that something is created. 
		SchedulingPolicy sp = DefaultResourceEnvironment.getProcessorSharingSchedulingPolicy();
		assertNotNull(sp);
	}

}
