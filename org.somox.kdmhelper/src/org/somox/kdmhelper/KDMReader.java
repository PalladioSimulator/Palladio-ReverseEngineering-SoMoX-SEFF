package org.somox.kdmhelper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.core.internal.resources.WorkspaceRoot;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
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

    /**
     * Load the specified projects into JaMoPP. If workspace is closed, i.e. we run standalone
     * assume the projects arrays is a path array and load the specific paths into JaMoPP.
     *
     * @param projects
     * @throws IOException
     */
    public void loadProject(final String... projects) throws IOException {
        final JaMoPPSoftwareModelExtractor softwareModelExtractor = new JaMoPPSoftwareModelExtractor();
        final Path cacheFileDir = Paths.get(System.getProperty("java.io.tmpdir", "/tmp/"),
                "JaMoPPGeneratorJobCacheDirSoMoX");
        final boolean extractLayoutInformation = true;

        KDMReader.logger.trace("Start loading projects: " + Arrays.toString(projects));

        if (SoMoXUtil.isStandalone()) {
            List<File> sourceFolderPaths = new ArrayList<>();
            for (String projectPath : projects) {
                sourceFolderPaths.add(new File(projectPath));
            }
            softwareModelExtractor.extractSoftwareModelFromFolders(sourceFolderPaths, new NullProgressMonitor(),
                    cacheFileDir.toString(), extractLayoutInformation);
        } else {
            List<IJavaProject> javaProjects = new ArrayList<>();
            for (final String projectName : projects) {
                IWorkspaceRoot workspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
                final IProject project = workspaceRoot.getProject(projectName);
                final IJavaProject javaProject = JavaCore.create(project);
                if (javaProject.exists()) {
                    javaProjects.add(javaProject);
                } else {
                    KDMReader.logger.warn(String
                            .format("Project %s is not a java project in this workspace. Ignoring it.", projectName));
                }
            }
            softwareModelExtractor.extractSoftwareModelFromProjects(javaProjects, new NullProgressMonitor(),
                    cacheFileDir.toString(), extractLayoutInformation);
        }

        this.addModelsToRoot(softwareModelExtractor.getSourceResources());
        KDMReader.logger.trace("Finished reading projects.");
    }

    public void loadProject(final IProject... projects) throws IOException {
        final List<String> projectPaths = new ArrayList<String>();
        for (final IProject project : projects) {
            projectPaths.add(project.getLocation().toString());
        }
        this.loadPathes(projectPaths);
    }

    private void loadPathes(final List<String> projectPaths) {
          throw new RuntimeException("not implemented yet");
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
