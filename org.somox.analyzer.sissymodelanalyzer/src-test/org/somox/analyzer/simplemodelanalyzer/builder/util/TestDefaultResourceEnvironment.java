package org.somox.analyzer.simplemodelanalyzer.builder.util;
import static org.junit.matchers.JUnitMatchers.hasItems;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import de.uka.ipd.sdq.pcm.repository.PrimitiveDataType;
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
	
	@Test
	public void testPrimitiveDataTypes() throws Exception {
		List<PrimitiveDataType> primitiveDataTypes = DefaultResourceEnvironment.getPrimitiveDataTypes();
		assertEquals(6, primitiveDataTypes.size());
		List<String> names = new ArrayList<String>();
		for (PrimitiveDataType dataType : primitiveDataTypes) {
			names.add(dataType.getType().getName());
		}
		hasItems("STRING","DOUBLE","BOOL","CHAR","BYTE","INT");
		
	}

}
