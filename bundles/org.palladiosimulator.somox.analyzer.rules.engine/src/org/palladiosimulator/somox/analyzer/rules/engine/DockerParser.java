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
//import org.yaml.snakeyaml.Yaml;

public class DockerParser {
    private final String FILE_NAME = "docker-compose";
    private final String path;
    Map<String, List<CompilationUnitImpl>> mapping;

    public DockerParser(String path) {
        this.path = path;
        final InputStream input = getDockerFile();
        final List<String> services = extractServiceNames(input);
        mapping = createServiceComponentMapping(services);
    }

    private InputStream getDockerFile() {

        // first, try to automatically find the docker-compose file
        List<Path> paths = new ArrayList<>();
        try (Stream<Path> files = Files.walk(Paths.get(path))) {
            paths = files.filter(f -> f.getFileName().toString().contains(FILE_NAME)).collect(Collectors.toList());
        } catch (final IOException e) {
            e.printStackTrace();
        }
        if (paths.size() <= 0) {
            System.out.println("No docker compose file detected.");
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

    @SuppressWarnings("unchecked")
    private List<String> extractServiceNames(InputStream stream) {
        // TODO Use
        // https://github.com/PalladioSimulator/Palladio-ReverseEngineering-Docker
        // final Yaml yaml = new Yaml();
        // final Map<String, Object> object = (Map<String, Object>) yaml.load(stream);
        final Map<String, Object> object = new HashMap<>();

        // get all service names from the map
        if (!object.containsKey("services")) {
            System.out.println("No property with name 'services' in docker compose file. File not usable");
            return null;
        }
        final List<String> serviceNames = new ArrayList<>();
        serviceNames.addAll(((Map<String, Object>) object.get("services")).keySet());
        serviceNames.forEach(name -> System.out.println(name));
        return serviceNames;
    }

    private Map<String, List<CompilationUnitImpl>> createServiceComponentMapping(List<String> serviceNames) {
        // all detected components
        final List<CompilationUnitImpl> components = PCMDetectorSimple.getComponents();

        // map each component path to a service name
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
