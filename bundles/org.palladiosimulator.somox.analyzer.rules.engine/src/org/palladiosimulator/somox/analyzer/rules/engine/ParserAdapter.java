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

import jamopp.parser.api.JaMoPPParserAPI;
import jamopp.parser.jdt.JaMoPPJDTParser;
import jamopp.resource.JavaResource2Factory;

public class ParserAdapter {

    public static List<CompilationUnitImpl> generateModelForProject(String in, String[] jarEntries) {

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

        saveModelToDisk(units);

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

                System.out.println("WARNING: Emtpy Resource: " + javaResource.getURI());
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

    private static boolean isUnitRelevant(CompilationUnit root) {
        return ((root.getClassifiers().size() > 0) && (root.getClassifiers().get(0).getName() != null)
                && !root.getNamespacesAsString().isEmpty());
    }
}