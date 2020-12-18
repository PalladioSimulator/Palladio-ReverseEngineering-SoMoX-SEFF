package org.palladiosimulator.somox.analyzer.rules.main;

import java.awt.BorderLayout;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.emftext.language.java.containers.ContainersPackage;
import org.emftext.language.java.containers.impl.CompilationUnitImpl;
import org.palladiosimulator.pcm.repository.Repository;
import org.palladiosimulator.somox.analyzer.rules.all.DefaultRule;
import org.palladiosimulator.somox.analyzer.rules.engine.DecoratorModelFiller;
import org.palladiosimulator.somox.analyzer.rules.engine.DockerParser;
import org.palladiosimulator.somox.analyzer.rules.engine.IRule;
import org.palladiosimulator.somox.analyzer.rules.engine.PCMDetectorSimple;
import org.palladiosimulator.somox.analyzer.rules.engine.PCMInstanceCreator;
import org.palladiosimulator.somox.analyzer.rules.engine.ParserAdapter;
import org.apache.log4j.Logger;
import org.somox.sourcecodedecorator.SourceCodeDecoratorRepository;

import jamopp.resource.JavaResource2Factory;


/**
* The rule engine identifies PCM elements like components and interfaces inside source code via rules specified by a user before.
* The output of this procedure is a SourceCodeDecoratorRepositoryModel and a PCMRepository model.
* For this, the engine needs a project directory, a JaMoPP model and a IRule file.
* There are two ways for using the engine.
*
* The first option is using the method execute() in which the model gets generated from the project and the rules can be selected inside a UI.
* The second option is using executeWith(dir, model, ruleDoc) for which a user has to provide the project directory, a model and rules beforehand.
* To simplify the use of the second option, the engine provides the public methods loadRules() and loadModel().
*/
public class RuleEngine {
    private static Repository pcm;

    private static SourceCodeDecoratorRepository deco;

    private static final Logger LOG = Logger.getLogger(RuleEngine.class);


    public static void main(String[] args) {
        execute();
    }

    /**
     * Starts the PCM repository extraction process
     */
    public static void execute() {

    	LOG.info("start");

        pcm = null;
        deco = null;

        Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("java", new JavaResource2Factory());

        // Provide input path to project
        final String in = showDirDialog();
        if (in.equals("")) {
        	LOG.info("No directory selected. Closing apllication...");
            return;
        }

        // Select a rule file to work with
        final String selectedRule = showRuleSelectionDialog();
        if (selectedRule.equals("")) {
        	LOG.info("No rules selected. Closing apllication...");
            return;
        }

        IRule ruleDoc = DefaultRule.valueOf(selectedRule).getRule();

        final List<CompilationUnitImpl> roots = ParserAdapter.generateModelForProject(in);

        executeWith(in, roots, ruleDoc);

        LOG.info("finish");
    }

    /**
    * Extracts PCM elements out of an existing JaMoPP model using an IRule file.
    *
    * @param  projectPath 	the file system path to the project directory
    * @param  model 		the JaMoPP model
    * @param  ruleDoc 		the object containing the rules
    */
    public static void executeWith(String projectPath, List<CompilationUnitImpl> model, IRule ruleDoc) {

        // for each unit, execute rules data
        for (final CompilationUnitImpl u : model) {
            ruleDoc.processRules(u);
        }
        LOG.info("Applied rules to the compilation units");

        // Parses the docker-compose file to get a mapping between microservice names and components
        // for creating composite components for each microservice
        final DockerParser dockerParser = new DockerParser(projectPath);
        final Map<String, List<CompilationUnitImpl>> mapping = dockerParser.getMapping();

        System.out.println("mapping:");
        mapping.entrySet().forEach(entry->{
        	System.out.println(entry.getKey());
        	entry.getValue().forEach(val->System.out.println(val));
        });

        // Creates a PCM repository with components, interfaces and roles
        pcm = PCMInstanceCreator.createPCM(mapping);

        // Persist the repository at ./pcm.repository
        PCMInstanceCreator.saveRepository(pcm, "./", "pcm.repository", true);

        /*
        LOG.info("Created PCM");

        // Creates a decorator model (krogmann) for upcoming processing purposes
        deco = DecoratorModelFiller.fillModel(pcm);
        System.out.println();
        LOG.info("Created Decorator Model");

        PCMDetectorSimple.showInsides();
        */
    }



    /**
    * Loads an external rules class file. For that the full qualified name of the xtend class has to be known
    *
    * @param  	namespace the string containing the namespace of the class implementing the IRule Interface
    * @return	the rules from the specified (via gui) file system place
    */
    public static IRule loadRules(String namespace) {

        final File file = new File(showFileDialog("rules"));

        try (URLClassLoader loader = new URLClassLoader(new URL[] { file.toURI().toURL() })) {
            final Class<?> c = loader.loadClass(namespace + file.getName().replace(".class", ""));
            final Object instance = c.getDeclaredConstructor().newInstance();
            if (instance instanceof IRule) {
                final IRule rules = (IRule) instance;
                return rules;
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Loads an external JaMoPP model.
     *
     * @return the JaMoPP model instances for each java file
     */
    public static List<CompilationUnitImpl> loadModel() {
        final ResourceSet rs = new ResourceSetImpl();
        rs.getPackageRegistry().put(ContainersPackage.eNS_URI, ContainersPackage.eINSTANCE);
        rs.getResourceFactoryRegistry().getExtensionToFactoryMap().put("containers", new XMIResourceFactoryImpl());

        final String modelIn = showFileDialog("model");

        final URI uri = URI.createFileURI(modelIn);
        final Resource res = rs.createResource(uri);
        try {
            res.load(null);
        } catch (final IOException e) {
            e.printStackTrace();
        }

        final List<EObject> contents = res.getContents();
        return contents.stream().map(content -> (CompilationUnitImpl) content).filter(compi -> compi.getName() != null)
                .collect(Collectors.toList());
    }

    /**
     * Creates a GUI for selecting the project directory
     *
     * @return the String containing the path to the project
     */
    private static String showDirDialog() {
        LOG.info("Directory Chooser opened");
        final JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setDialogTitle("Select the project directory");
        chooser.setAcceptAllFileFilterUsed(false);
        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            return chooser.getSelectedFile().getAbsolutePath();
        }
        return "";
    }

    /**
     * Creates a GUI for selecting a file, which in this context, can be a rules file or a model file
     *
     * @param  	purpose the string containing the type of file to select
     * @return	the string containing the path to the selected file
     */
    private static String showFileDialog(String purpose) {
        LOG.info("File Chooser for file of type: "+purpose+" opened");
        final JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setDialogTitle("Select the "+purpose+" file");
        chooser.setAcceptAllFileFilterUsed(false);
        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            return chooser.getSelectedFile().getAbsolutePath();
        }
        return "";
    }

    /**
     * Outputs the content of a given PCM repository to the console
     *
     * @param repo the PCM repository
     */
    private static void printResult(Repository repo) {
    	System.out.println("\nResult:");
        repo.getComponents__Repository().forEach(comp -> {
            System.out.println("Component: " + comp.getEntityName());
            System.out.println("Provides:");
            comp.getProvidedRoles_InterfaceProvidingEntity().forEach(prov -> {
                System.out.println(prov.getEntityName());
            });
            System.out.println("Requires");
            comp.getRequiredRoles_InterfaceRequiringEntity().forEach(re -> {
                System.out.println(re.getEntityName());
            });
            System.out.println();
        });
        repo.getInterfaces__Repository().forEach(in -> {
            System.out.println("Interface: " + in.getEntityName());
        });
    }

    /**
     * Creates a GUI for selecting a default rule technology
     *
     * @return	the string containing the name of the selected default rule technology
     */
    private static String showRuleSelectionDialog() {

        String selectedItem = "";

        final JFrame iframe = new JFrame();
        iframe.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        iframe.setAlwaysOnTop(true);

        final int btns = JOptionPane.OK_CANCEL_OPTION;

        final String dialogMessage = "<html>Select the desired item from the Drop-Down "
                + "list<br>you want to work with:<br><br></html>";
        final String dialogTitle = "Select relevant rules";

        final BorderLayout layout = new BorderLayout();
        final JPanel topPanel = new JPanel(layout);
        final JLabel label = new JLabel(dialogMessage);
        topPanel.add(label, BorderLayout.NORTH);
        final JPanel centerPanel = new JPanel(new BorderLayout(5, 5));
        final JComboBox<String> cb = new JComboBox<>();
        cb.setModel(new DefaultComboBoxModel<>(DefaultRule.valuesAsString()));
        cb.setSelectedIndex(0);
        centerPanel.add(cb, BorderLayout.CENTER);
        topPanel.add(centerPanel);

        final int res = JOptionPane.showConfirmDialog(iframe, topPanel, dialogTitle, btns);

        if (res == JOptionPane.OK_OPTION) {
            selectedItem = cb.getSelectedItem().toString();
        }

        iframe.dispose();
        return selectedItem;
    }

    /**
     * Returns the current PCM repository model of the engine
     *
     * @return the PCM repository model
     */
    public static Repository getPCMRepository() {
        return pcm;
    }

    /**
     * Returns the current SourceCodeDecoratorRepository model of the engine
     *
     * @return the SourceCodeDecoratorRepository model
     */
    public static SourceCodeDecoratorRepository getDecoratorRepository() {
        return deco;
    }

}
