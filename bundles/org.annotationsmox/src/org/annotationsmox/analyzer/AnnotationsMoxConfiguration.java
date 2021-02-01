package org.annotationsmox.analyzer;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.somox.configuration.AbstractMoxConfiguration;
import org.somox.configuration.SoMoXConfiguration;

import de.uka.ipd.sdq.workflow.extension.ExtendableJobConfiguration;

public class AnnotationsMoxConfiguration extends AbstractMoxConfiguration implements ExtendableJobConfiguration {

    public static final String EJBMOX_INSPECTIT_FILE_PATHS = "inspectItFilePaths";

    private final Set<String> inspectITFilePaths = new HashSet<String>();

    private Map<String, Object> attributeMap;

    public AnnotationsMoxConfiguration(final Map<String, Object> attributeMap) {
        this.attributeMap = attributeMap;
        this.applyAttributeMap(attributeMap);
    }

    public AnnotationsMoxConfiguration() {
        this.getFileLocations().setOutputFolder("model/");
    }

    public Set<String> getInspectITFilePaths() {
        return this.inspectITFilePaths;
    }

    public void setInspectITFilePaths(final String... filePaths) {
        this.inspectITFilePaths.addAll(Arrays.asList(filePaths));
    }

    /**
     * needed for some methods that need to deal with a {@link SoMoXConfiguration} and can not deal
     * with AbstractMoxConfguration easily.
     */
    public SoMoXConfiguration convertToSoMoXConfiguration() {
        final SoMoXConfiguration somoxConfiguration = new SoMoXConfiguration(this.toMap());
        return somoxConfiguration;

    }

    @Override
    public void applyAttributeMap(final Map<String, Object> attributeMap) {
        if (null == attributeMap) {
            return;
        }
        super.applyAttributeMap(attributeMap);
        if (attributeMap.get(AnnotationsMoxConfiguration.EJBMOX_INSPECTIT_FILE_PATHS) != null) {
            final Object pathsObject = attributeMap.get(AbstractMoxConfiguration.SOMOX_ANALYZER_INPUT_FILE);
            if (pathsObject instanceof Collection<?>) {
                for (final Object pathObject : (Collection<?>) pathsObject) {
                    this.inspectITFilePaths.add((String) pathObject);
                }
            }
        }
    }

    @Override
    public Map<String, Object> toMap() {
        final Map<String, Object> result = super.toMap();

        result.put(AnnotationsMoxConfiguration.EJBMOX_INSPECTIT_FILE_PATHS, this.inspectITFilePaths);
        return result;

    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributeMap;
    }
}
