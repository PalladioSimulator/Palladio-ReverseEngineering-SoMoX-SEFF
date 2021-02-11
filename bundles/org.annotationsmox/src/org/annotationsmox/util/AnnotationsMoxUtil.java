package org.annotationsmox.util;

import java.util.Map;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.palladiosimulator.pcm.PcmPackage;
import org.palladiosimulator.pcm.util.PcmResourceFactoryImpl;
import org.somox.util.DefaultResourceEnvironment;
import org.somox.sourcecodedecorator.SourcecodedecoratorPackage;
import org.somox.sourcecodedecorator.util.SourcecodedecoratorResourceFactoryImpl;

public class AnnotationsMoxUtil {

    public static final String SOURCECODEDECORATOR_FILE_ENDING = "sourcecodedecorator";
    public static final String REPOSITORY_FILE_ENDING = "repository";
    public static final String USAGEMODEL_FILE_ENDING = "usagemodel";
    public static final String SYSTEM_FILE_ENDING = "system";
    public static final String DEFAULT_MODEL_PATH = "defaultModels/";
    public static final String PRIMITIVE_TYPE_REPOSITORY_PATH = DEFAULT_MODEL_PATH + "PrimitiveTypes.repository";
    public static final String RESOURCE_TYPES_PATH = DEFAULT_MODEL_PATH + "ResourceTypes.resourcetype";

    private AnnotationsMoxUtil() {
    }

    /**
     * registers URI pathmaps in case SEFF Generator is used in a standalone project
     */
    public static void setupURIPathmaps() {
        final String absPathPrimitiveType = AnnotationsMoxUtil.PRIMITIVE_TYPE_REPOSITORY_PATH;
        final String absPathResourceType = AnnotationsMoxUtil.RESOURCE_TYPES_PATH;
        URIConverter.URI_MAP.put(URI.createURI("pathmap://PCM_MODELS/PrimitiveTypes.repository"),
                URI.createURI(absPathPrimitiveType));
        URIConverter.URI_MAP.put(URI.createURI("pathmap://PCM_MODELS/Palladio.resourcetype"),
                URI.createURI(absPathResourceType));
        URIConverter.URI_MAP.put(URI.createURI("pathmap://PCM_MODELS/FailureTypes.repository"),
                URI.createURI(absPathPrimitiveType));
        URIConverter.URI_MAP.put(URI.createURI(DefaultResourceEnvironment.PRIMITIVETYPES_URI),
                URI.createURI(absPathPrimitiveType));
        URIConverter.URI_MAP.put(URI.createURI(DefaultResourceEnvironment.RESOURCETYPE_URI),
                URI.createURI(absPathResourceType));
    }

    public static void initializeLogger() {
        Logger.getRootLogger().removeAllAppenders();
        Logger.getRootLogger()
                .addAppender(new ConsoleAppender(new PatternLayout("[%-5p] %d{HH:mm:ss,SSS} %-30C{1} - %m%n")));
    }

    public static void registerMetamodels() {
        final Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
        final Map<String, Object> m = reg.getExtensionToFactoryMap();

        // register PCM
        EPackage.Registry.INSTANCE.put(PcmPackage.eNS_URI, PcmPackage.eINSTANCE);
        m.put(AnnotationsMoxUtil.REPOSITORY_FILE_ENDING, new PcmResourceFactoryImpl());
        m.put(AnnotationsMoxUtil.SYSTEM_FILE_ENDING, new PcmResourceFactoryImpl());
        m.put(AnnotationsMoxUtil.USAGEMODEL_FILE_ENDING, new PcmResourceFactoryImpl());

        // register SourceCodeDecoratorMM package and factory globally
        EPackage.Registry.INSTANCE.put(SourcecodedecoratorPackage.eNS_URI, SourcecodedecoratorPackage.eINSTANCE);
        m.put(AnnotationsMoxUtil.SOURCECODEDECORATOR_FILE_ENDING, new SourcecodedecoratorResourceFactoryImpl());
    }

}
