package org.somox.kdmhelper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.emftext.language.java.containers.CompilationUnit;
import org.somox.kdmhelper.metamodeladdition.Root;
import org.splevo.jamopp.extraction.JaMoPPSoftwareModelExtractor;

public class KDMReader {

    private final Root root;
    // Resource

    private final static Logger logger = Logger.getLogger(KDMReader.class.getName());

    public KDMReader() {
        this.root = new Root();
    }

    public Root getRoot() {
        return this.root;
    }

    public void loadProject(final String... projects) throws IOException {
        final List<IProject> iProjects = new ArrayList<IProject>();
        for (final String projectName : projects) {
            final IWorkspace workspace = ResourcesPlugin.getWorkspace();
            final IWorkspaceRoot workspaceRoot = workspace.getRoot();
            final IProject project = workspaceRoot.getProject(projectName);
            iProjects.add(project);
        }
        this.loadProject(iProjects.toArray(new IProject[iProjects.size()]));
    }

    public void loadProject(final IProject... projects) throws IOException {
        final JaMoPPSoftwareModelExtractor softwareModelExtractor = new JaMoPPSoftwareModelExtractor();
        final List<String> projectPaths = new ArrayList<String>();
        for (final IProject project : projects) {
            projectPaths.add(project.getLocation().toString());
        }
        KDMReader.logger.trace("Start loading projects: " + projectPaths);
        final String cacheFileDir = System.getProperty("java.io.tmpdir", "/tmp/") + "JaMoPPGeneratorJobCacheDirSoMoX";
        final File file = new File(cacheFileDir);
        final boolean extractLayoutInformation = true;
        softwareModelExtractor.extractSoftwareModel(projectPaths, new NullProgressMonitor(), file.getAbsolutePath(),
                extractLayoutInformation);
        this.addModelsToRoot(softwareModelExtractor.getSourceResources());
        KDMReader.logger.trace("Finished reading projects.");
    }

    public void addModelsToRoot(final Collection<Resource> resources) {
        for (final Resource resource : resources) {
            this.root.addModels(this.getModelsFromResource(resource));
        }
    }

    private Collection<CompilationUnit> getModelsFromResource(final Resource resource) {
        final List<CompilationUnit> modelList = new ArrayList<CompilationUnit>();
        for (final EObject obj : resource.getContents()) {
            if (obj instanceof CompilationUnit) {
                final CompilationUnit model = (CompilationUnit) obj;
                modelList.add(model);
            }
        }
        return modelList;
    }
}
