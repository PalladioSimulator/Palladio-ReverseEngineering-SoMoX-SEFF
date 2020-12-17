package org.palladiosimulator.somox.analyzer.rules.engine;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.emftext.language.java.containers.impl.CompilationUnitImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
// import org.yaml.snakeyaml.Yaml;

/**
* The DockerParser parses a docker-compose file to extract a mapping between service names (microservices) and JaMoPP model instances.
* Later, this parser will be replaced with the project in:
* https://github.com/PalladioSimulator/Palladio-ReverseEngineering-Docker
*/
public class DockerParser {
    private final String FILE_NAME = "docker-compose";
    private final String path;
    Map<String, List<CompilationUnitImpl>> mapping;

    private static final Logger LOG = LoggerFactory.getLogger(DockerParser.class);

    public DockerParser(String path) {

    	LOG.info("starting docker process");

        this.path = path;
        final InputStream input = getDockerFile();
        final List<String> services = extractServiceNames(input);
        mapping = createServiceComponentMapping(services);
    }

    /**
    * Returns a Stream to the docker-compose file found by walking through a given project directory.
    *
    * @return the docker-compose file as stream
    */
    private InputStream getDockerFile() {

        List<Path> paths = new ArrayList<>();
        try (Stream<Path> files = Files.walk(Paths.get(path))) {
            paths = files.filter(f -> f.getFileName().toString().contains(FILE_NAME)).collect(Collectors.toList());
        } catch (final IOException e) {
            e.printStackTrace();
        }
        if (paths.size() <= 0) {
        	LOG.info("No docker compose file detected.");
            return null;
        }
        final Path path = paths.get(0);

        final File initialFile = path.toFile();
        InputStream targetStream = null;
        try {
            targetStream = new FileInputStream(initialFile);
            return targetStream;
        } catch (final FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
    * Extracts the service names within a docker-compose file.
    *
    * @param  stream the docker-compose file
    * @return the list of all service names found in the docker-compose file
    */
    @SuppressWarnings("unchecked")
    private List<String> extractServiceNames(InputStream stream) {
    	System.out.println(stream.toString());

        // final Yaml yaml = new Yaml();
        final Map<String, Object> object = null; // (Map<String, Object>) yaml.load(stream);

        // get all service names from the map
        if (!object.containsKey("services")) {
        	LOG.info("No property with name 'services' in docker compose file. File not usable");
            return null;
        }
        final List<String> serviceNames = new ArrayList<>();
        serviceNames.addAll(((Map<String, Object>) object.get("services")).keySet());
        return serviceNames;
    }

    /**
    * Creates a mapping between service names and JaMoPP model instances to know which component belongs to which microservice
    *
    * @param  serviceNames a list of all service names from a docker-compose file
    * @return the mapping between service names and JaMoPP model instances
    */
    private Map<String, List<CompilationUnitImpl>> createServiceComponentMapping(List<String> serviceNames) {

        final List<CompilationUnitImpl> components = PCMDetectorSimple.getComponents();

        final Map<String, List<CompilationUnitImpl>> serviceToCompMapping = new HashMap<>();

        components.forEach(comp -> {
            try (Stream<Path> files = Files.walk(Paths.get(path))) {
                final Path foundPath = files.filter(f -> f.toString().contains(comp.getName()))
                        .collect(Collectors.toList()).get(0);

                serviceNames.forEach(serviceName -> {
                    if (foundPath.toString().contains(serviceName)) {
                        if (!serviceToCompMapping.containsKey(serviceName)) {
                            serviceToCompMapping.put(serviceName, new ArrayList<>());
                        }
                        serviceToCompMapping.get(serviceName).add(comp);
                    }
                });
            } catch (final IOException e) {
                e.printStackTrace();
            }
        });

        return serviceToCompMapping;
    }

    public Map<String, List<CompilationUnitImpl>> getMapping() {
        return mapping;
    }

}
