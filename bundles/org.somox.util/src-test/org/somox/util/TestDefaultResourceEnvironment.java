package org.somox.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.palladiosimulator.pcm.resourceenvironment.ResourceEnvironment;
import org.palladiosimulator.pcm.resourcetype.ProcessingResourceType;
import org.palladiosimulator.pcm.resourcetype.ResourceRepository;
import org.palladiosimulator.pcm.resourcetype.SchedulingPolicy;

/**
 * Must be run as a Junit-Plugin-Test!
 *
 * @author kuester
 *
 */
public class TestDefaultResourceEnvironment {

    @Test
    public void testCreateDefaultResourceEnvironment() {
        // only check that something is created.
        final ResourceEnvironment env = DefaultResourceEnvironment.getDefaultResourceEnvironment();
        assertNotNull(env);
    }

    @Test
    public void testGetResourceRepository() {
        // only check that something is created.
        final ResourceRepository rep = DefaultResourceEnvironment.getResourceRepository();
        assertNotNull(rep);
    }

    @Test
    public void testGetCPUProcessingResourceType() {
        // only check that something is created.
        final ProcessingResourceType type = DefaultResourceEnvironment.getCPUProcessingResourceType();
        assertNotNull(type);
    }

    @Test
    public void testGetProcessorSharingSchedulingPolicy() {
        // only check that something is created.
        final SchedulingPolicy sp = DefaultResourceEnvironment.getProcessorSharingSchedulingPolicy();
        assertNotNull(sp);
    }

    @Test
    public void testPrimitiveDataTypes() throws Exception {
        assertEquals("INT", DefaultResourceEnvironment.getPrimitiveDataTypeInteger().getType().getName());
        assertEquals("DOUBLE", DefaultResourceEnvironment.getPrimitiveDataTypeDouble().getType().getName());
        assertEquals("BOOL", DefaultResourceEnvironment.getPrimitiveDataTypeBool().getType().getName());
        assertEquals("CHAR", DefaultResourceEnvironment.getPrimitiveDataTypeChar().getType().getName());
        assertEquals("BYTE", DefaultResourceEnvironment.getPrimitiveDataTypeByte().getType().getName());
        assertEquals("STRING", DefaultResourceEnvironment.getPrimitiveDataTypeString().getType().getName());
    }

}
