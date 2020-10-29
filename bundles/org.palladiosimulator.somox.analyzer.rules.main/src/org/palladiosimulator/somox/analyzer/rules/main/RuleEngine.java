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
import org.palladiosimulator.somox.analyzer.rules.engine.DecoratorModelFiller;
import org.palladiosimulator.somox.analyzer.rules.engine.DockerParser;
import org.palladiosimulator.somox.analyzer.rules.engine.IRule;
import org.palladiosimulator.somox.analyzer.rules.engine.PCMInstanceCreator;
import org.palladiosimulator.somox.analyzer.rules.engine.ParserAdapter;
import org.palladiosimulator.somox.analyzer.rules.jax_rs.JaxRSRules;
import org.palladiosimulator.somox.analyzer.rules.spring.SpringRules;
import org.somox.sourcecodedecorator.SourceCodeDecoratorRepository;

import jamopp.resource.JavaResource2Factory;

/*
 * What:
 * The rule engine identifies PCM components and interfaces inside source code via rules specified by a user before
 *
 * Inputs:
 * Path to "src" folder of source code to analyze or path to a .containers model file (when jdt was used)
 * Selection of predefined rules or path to external .class file including the relevant rules
 *
 * Outputs:
 * A PCM instance reflecting the analyzed source code (currently a print of all found elements)
 */
public class RuleEngine {
    private static Repository pcm;

    private static SourceCodeDecoratorRepository deco;

    private static final String[] DEFAULT_RULES = { "Default", "Spring", "Java REST API" };

    public static void main(String[] args) {
        execute(args);
    }

    public static void execute(String[] args) {
        pcm = null;
        deco = null;

        System.out.println("start");

        Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("java", new JavaResource2Factory());

        // Provide input path to project
        final String in = showDirDialog();
        if (in.equals("")) {
            System.out.println("No directory selected. Closing apllication...");
            return;
        }

        // Select a rule file to work with
        final String selectedRule = showRuleSelectionDialog();
        if (selectedRule.equals("")) {
            System.out.println("No rules selected. Closing apllication...");
            return;
        }

        // This line loads an external model file like example.containers
        // List<CompilationUnitImpl> roots = loadModel();

        // Instead of loading an existing model file, a new model will be generated and
        // persisted here
        final List<CompilationUnitImpl> roots = ParserAdapter.generateModelForProject(in, args);

        IRule ruleDoc = null;
        if (selectedRule.equals(DEFAULT_RULES[0])) {
            ruleDoc = new JaxRSRules();
        }

        if (selectedRule.equals(DEFAULT_RULES[1])) {
            ruleDoc = new SpringRules();
        }

        if (selectedRule.equals(DEFAULT_RULES[2])) {
            ruleDoc = new JaxRSRules();
        }

        // Load external rules class file. For that the full qualified name of the xtend
        // class have to be known
        // IRule ruleDoc = loadRules();

        // for each unit, execute rules data
        for (final CompilationUnitImpl u : roots) {
            ruleDoc.processRules(u);
        }

        // Parses the docker-compose file to get a mapping between microservice names
        // and components
        // for creating composite components for each microservice
        //final DockerParser dockerParser = new DockerParser(in);
        //final Map<String, List<CompilationUnitImpl>> mapping = dockerParser.getMapping();

        // Creates a PCM repository with components, interfaces and roles
        //pcm = PCMInstanceCreator.createPCM(mapping);

        // Creates a decorator model (krogmann) for upcoming processing purposes
        //deco = DecoratorModelFiller.fillModel(pcm);

        //printResult(pcm);
        System.out.println("finished");
    }

    private static IRule loadRules() {

        final String packageName = "main.example.";

        // path to rules
        final String rulePath = "C:\\Projekte\\masterthesis\\code\\eclipse-workspace\\xtend-examples\\bin\\example1\\";
        final File file = new File(showFileDialog("rules"));
        System.out.println("file name: " + file.getName());
        try (URLClassLoader loader = new URLClassLoader(new URL[] { file.toURI().toURL() })) {
            final Class<?> c = loader.loadClass(packageName + file.getName().replace(".class", ""));
            final Object instance = c.getDeclaredConstructor().newInstance();
            if (instance instanceof IRule) {
                final IRule rules = (IRule) instance;
                System.out.println("it worked");
                return rules;
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static List<CompilationUnitImpl> loadModel() {
        final ResourceSet rs = new ResourceSetImpl();
        rs.getPackageRegistry().put(ContainersPackage.eNS_URI, ContainersPackage.eINSTANCE);
        rs.getResourceFactoryRegistry().getExtensionToFactoryMap().put("containers", new XMIResourceFactoryImpl());

        final String modelIn = showFileDialog("model");
        System.out.println("model path: " + modelIn);

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

    private static String showDirDialog() {
        System.out.println("Please choose the project directory...");
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

    private static String showFileDialog(String purpose) {
        System.out.println("Please choose the relevant " + purpose + "...");
        final JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setDialogTitle("Select the model file");
        chooser.setAcceptAllFileFilterUsed(false);
        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            return chooser.getSelectedFile().getAbsolutePath();
        }
        return "";
    }

    private static void printResult(Repository repo) {
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

    private static String showRuleSelectionDialog() {

        String selectedItem = "";

        /*
         * Ensure dialog never hides behind anything (use if the keyword 'this' can not
         * be used or there is no object to reference as parent for the dialog).
         */
        final JFrame iframe = new JFrame();
        iframe.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        iframe.setAlwaysOnTop(true);
        // ---------------------------------------------------

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
        cb.setModel(new DefaultComboBoxModel<>(DEFAULT_RULES));
        cb.setSelectedIndex(0);
        centerPanel.add(cb, BorderLayout.CENTER);
        topPanel.add(centerPanel);

        // Ensure a selection or Cancel (or dialog close)
        final int res = JOptionPane.showConfirmDialog(iframe, topPanel, dialogTitle, btns);

        if (res == JOptionPane.OK_OPTION) {
            selectedItem = cb.getSelectedItem().toString();
        }

        iframe.dispose();
        return selectedItem;
    }

    public static Repository getPCMRepository() {
        return pcm;
    }

    public static SourceCodeDecoratorRepository getDecoratorRepository() {
        return deco;
    }

}
