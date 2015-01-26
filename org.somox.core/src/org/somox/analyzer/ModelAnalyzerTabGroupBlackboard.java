package org.somox.analyzer;

import java.util.Enumeration;
import java.util.Vector;

import org.apache.log4j.Level;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;
//import org.eclipse.gmt.modisco.java.emf.util.JavaAdapterFactory;
//import org.eclipse.gmt.modisco.omg.kdm.kdm.util.KdmAdapterFactory;
//import org.eclipse.modisco.java.composition.javaapplication.util.JavaapplicationAdapterFactory;
import org.somox.core.Activator;
import org.somox.kdmhelper.KDMReader;
import org.somox.kdmhelper.metamodeladdition.Root;

//import de.fzi.gast.accesses.provider.accessesItemProviderAdapterFactory;
//import de.fzi.gast.annotations.provider.annotationsItemProviderAdapterFactory;
//import de.fzi.gast.core.Root;
//import de.fzi.gast.core.provider.coreItemProviderAdapterFactory;
//import de.fzi.gast.functions.provider.functionsItemProviderAdapterFactory;
//import de.fzi.gast.helpers.GASTReader;
//import de.fzi.gast.statements.provider.statementsItemProviderAdapterFactory;
//import de.fzi.gast.types.provider.typesItemProviderAdapterFactory;
//import de.fzi.gast.variables.provider.variablesItemProviderAdapterFactory;

/**
 * @author Snowball
 */
public class ModelAnalyzerTabGroupBlackboard {

    /**
     * @uml.property name="root"
     * @uml.associationEnd
     */
    private Root root;

    private final ComposedAdapterFactory adapterFactory;
    // private AdapterFactoryEditingDomain editingDomain;

    static int idCounter = 0;
    private int myId = 0;

    private final ILog logger = Activator.getPlugin().getLog();

    public ModelAnalyzerTabGroupBlackboard() {
        idCounter++;
        this.myId = idCounter;

        this.adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
        this.adapterFactory.addAdapterFactory(new ResourceItemProviderAdapterFactory());
        // adapterFactory.addAdapterFactory(new statementsItemProviderAdapterFactory());
        // adapterFactory.addAdapterFactory(new coreItemProviderAdapterFactory());
        // adapterFactory.addAdapterFactory(new annotationsItemProviderAdapterFactory());
        // adapterFactory.addAdapterFactory(new typesItemProviderAdapterFactory());
        // adapterFactory.addAdapterFactory(new functionsItemProviderAdapterFactory());
        // adapterFactory.addAdapterFactory(new accessesItemProviderAdapterFactory());
        // adapterFactory.addAdapterFactory(new variablesItemProviderAdapterFactory());
        this.adapterFactory.addAdapterFactory(new ReflectiveItemProviderAdapterFactory());
        // adapterFactory.addAdapterFactory(new JavaAdapterFactory());//REALLYADDED//SOMOXTODOCHANGE
        // adapterFactory.addAdapterFactory(new KdmAdapterFactory());;//REALLYADDED//SOMOXTODOCHANGE
        // adapterFactory.addAdapterFactory(new JavaapplicationAdapterFactory());//SOMOXTODOCHANGE
        // //TODO verify.....

        // BasicCommandStack commandStack = new BasicCommandStack();

        // editingDomain = new AdapterFactoryEditingDomain(adapterFactory, commandStack, new
        // HashMap<Resource, Boolean>());
    }

    public int getId() {
        return this.myId;
    }

    /**
     * @uml.property name="somoxAnalyzerInputFile"
     */
    private String somoxAnalyzerInputFile = null;

    /**
     * @uml.property name="debugLvl"
     */
    private Level debugLvl = Level.INFO;

    /**
     * @return
     * @uml.property name="debugLvl"
     */
    public Level getDebugLvl() {
        return this.debugLvl;
    }

    public void setDebugLvl(final int debugLvl) {
        switch (debugLvl) {
        case 0:
            this.debugLvl = Level.INFO;
            break;
        case 1:
            this.debugLvl = Level.DEBUG;
            break;
        default:
            this.debugLvl = Level.ALL;
        }
    }

    /**
     * @return
     * @uml.property name="somoxAnalyzerInputFile"
     */
    public String getSomoxAnalyzerInputFile() {
        return this.somoxAnalyzerInputFile;
    }

    /**
     * @param somoxAnalyzerInputFile
     * @uml.property name="somoxAnalyzerInputFile"
     */
    public void setSomoxAnalyzerInputFile(final String somoxAnalyzerInputFile) {
        if (this.somoxAnalyzerInputFile == null) {
            this.somoxAnalyzerInputFile = somoxAnalyzerInputFile;
            if (this.somoxAnalyzerInputFile != null) {
                if (somoxAnalyzerInputFile.endsWith(".xmi")) {
                    this.loadModel();
                } else {
                    this.root = null;
                }
            } else {
                this.root = null;
            }
            this.fireBlackboardChanged();
        } else if (this.somoxAnalyzerInputFile != null && !this.somoxAnalyzerInputFile.equals(somoxAnalyzerInputFile)) {
            this.somoxAnalyzerInputFile = somoxAnalyzerInputFile;
            if (this.somoxAnalyzerInputFile != null) {
                if (somoxAnalyzerInputFile.endsWith(".xmi")) {
                    this.loadModel();
                } else {
                    this.root = null;
                }
            } else {
                this.root = null;
            }
            this.fireBlackboardChanged();
        }
    }

    private void loadModel() {

        final URI fileURI = URI.createPlatformResourceURI(this.somoxAnalyzerInputFile, true);

        final IWorkspaceRoot resRoot = ResourcesPlugin.getWorkspace().getRoot();
        if (resRoot.findMember(fileURI.toPlatformString(true)) instanceof IFile) {
            final KDMReader gastReader = new KDMReader();
            // gastReader.loadFile(fileURI);
            this.root = gastReader.getRoot();
        } else {
            this.logger.log(new Status(Status.ERROR, Activator.PLUGIN_ID, "Failed to load Model file " + fileURI));
        }
    }

    /**
     * @return
     * @uml.property name="root"
     */
    public Root getRoot() {
        return this.root;
    }

    //
    // Event handling
    //
    private transient Vector<BlackboardListener> blackboardListeners;

    /** Register a listener for Blackboard events */
    synchronized public void addBlackboardListener(final BlackboardListener listener) {
        if (this.blackboardListeners == null) {
            this.blackboardListeners = new Vector<BlackboardListener>();
        }
        this.blackboardListeners.addElement(listener);
    }

    synchronized public void removeBlackboardListener(final BlackboardListener listener) {
        if (this.blackboardListeners == null) {
            this.blackboardListeners = new Vector<BlackboardListener>();
        }
        this.blackboardListeners.removeElement(listener);
    }

    /** Fire to all registered listeners */
    @SuppressWarnings("unchecked")
    public void fireBlackboardChanged() {
        // If we have no listeners, do nothing.
        if (this.blackboardListeners != null && !this.blackboardListeners.isEmpty()) {
            // Make a copy of the listener list in case anyone adds or removes
            // listeners.
            Vector<BlackboardListener> targets;
            synchronized (this.blackboardListeners) {
                targets = (Vector<BlackboardListener>) this.blackboardListeners.clone();
            }
            // Walk through the listener list and call the listener method in
            // each.
            final Enumeration<BlackboardListener> e = targets.elements();
            while (e.hasMoreElements()) {
                final BlackboardListener l = e.nextElement();
                l.blackboardChanged();
            }
        }
    }

}
