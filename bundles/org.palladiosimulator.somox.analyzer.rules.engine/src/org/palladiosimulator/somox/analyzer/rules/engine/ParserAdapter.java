package org.palladiosimulator.somox.analyzer.rules.engine;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.emftext.language.java.containers.CompilationUnit;
import org.emftext.language.java.containers.impl.CompilationUnitImpl;
import org.apache.log4j.Logger;

import jamopp.parser.api.JaMoPPParserAPI;
import jamopp.parser.jdt.JaMoPPJDTParser;
import jamopp.resource.JavaResource2Factory;

/**
* This class wraps the JaMoPPJDTParser to parse a project directory and additionally filters and saves the resulting model instances.
*/
public class ParserAdapter {

    private static final Logger LOG = Logger.getLogger(ParserAdapter.class);

    public static List<CompilationUnitImpl> generateModelForProject(String in) {

        // create
        final List<CompilationUnitImpl> roots = new ArrayList<>();
        final ResourceSet rs = new ResourceSetImpl();
        rs.getResourceFactoryRegistry().getExtensionToFactoryMap().put("containers", new XMIResourceFactoryImpl());

        // parse
        final JaMoPPParserAPI parser = new JaMoPPJDTParser();
        final ResourceSet units = parser.parseDirectory(Paths.get(in));

        // filter
        units.getAllContents().forEachRemaining(u -> {
            if (u instanceof CompilationUnitImpl) {
                final CompilationUnitImpl root = (CompilationUnitImpl) u;
                if (isUnitRelevant(root)) {

                    final String name = root.getClassifiers().get(0).getName();
                    root.setName(name);

                    roots.add(root);
                }
            }
        });

        LOG.info("Parsed project directory");

        saveModelToDisk(units);

        LOG.info("Saved generated model to ./standalone_output/model.containers");

        return roots;
    }

    private static void saveModelToDisk(ResourceSet rs) {
        Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("java", new JavaResource2Factory());
        Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("containers", new XMIResourceFactoryImpl());

        EcoreUtil.resolveAll(rs);

        final File outputFile = new File("." + File.separator + "./standalone_output" + File.separator + "model");
        outputFile.getParentFile().mkdirs();
        final URI xmiFileURI = URI.createFileURI(outputFile.getAbsolutePath()).appendFileExtension("containers");
        final Resource xmiResource = rs.createResource(xmiFileURI);

        for (final Resource javaResource : new ArrayList<>(rs.getResources())) {

            if (javaResource.getContents().isEmpty()) {

            	LOG.warn("WARNING: Emtpy Resource: " + javaResource.getURI());

                continue;
            }

            if (!javaResource.getURI().scheme().equals("file")) {
                continue;
            }

            if (javaResource instanceof CompilationUnit) {
                final CompilationUnit unit = (CompilationUnit) javaResource;
                if (!isUnitRelevant(unit)) {
                    continue;
                }
            }
            xmiResource.getContents().addAll(javaResource.getContents());
        }

        try {
            xmiResource.save(rs.getLoadOptions());
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    /**
    * This method was important for earlier versions of the JaMoPP model as some model instances did not have a name for example
    */
    private static boolean isUnitRelevant(CompilationUnit root) {
        return ((root.getClassifiers().size() > 0) && (root.getClassifiers().get(0).getName() != null)
                && !root.getNamespacesAsString().isEmpty());
    }
}