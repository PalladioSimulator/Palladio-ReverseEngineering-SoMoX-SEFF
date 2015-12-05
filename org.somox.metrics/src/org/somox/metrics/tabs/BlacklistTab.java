package org.somox.metrics.tabs;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.StringTokenizer;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.emftext.language.java.commons.Commentable;
import org.emftext.language.java.types.Type;
import org.somox.analyzer.BlackboardListener;
import org.somox.common.SoMoXProjectPreferences;
import org.somox.kdmhelper.KDMHelper;
import org.somox.kdmhelper.metamodeladdition.Root;

//import de.fzi.gast.core.Root;
//import de.fzi.gast.types.GASTClass;

public class BlacklistTab extends MetricTab {

    private final String DELIMITER = "§";

    protected Composite control;
    protected CheckboxTreeViewer checkboxTreeViewer;
    protected ICheckStateListener checkStateListener;
    protected Composite treeViewerControl;
    protected Composite textFieldControl;
    private Text textField;
    private Text addiditonalBlacklistTextfield;
    private Root root;
    StackLayout stackLayout;

    @Override
    public void activated(final ILaunchConfigurationWorkingCopy workingCopy) {

    }

    @Override
    public boolean canSave() {
        return true;
    }

    public void setRoot(final Root root) {
        if (this.root != root) {
            this.checkboxTreeViewer.getTree().dispose();
            this.checkboxTreeViewer = new CheckboxTreeViewer(this.treeViewerControl, SWT.BORDER);
            this.checkboxTreeViewer.setContentProvider(new CheckboxContentProvider());
            this.checkboxTreeViewer.setLabelProvider(new CheckboxLabelProvider());
            final Tree tree = this.checkboxTreeViewer.getTree();
            tree.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
            this.checkboxTreeViewer.addCheckStateListener(this.checkStateListener);
        }
        this.root = root;
        this.checkboxTreeViewer.setInput(this.root);

        if (this.root != null) {
            this.checkboxTreeViewer.setGrayed(this.root, true);
        }
    }

    /**
     * @wbp.parser.entryPoint
     */
    @Override
    public void createControl(final Composite parent) {
        this.checkStateListener = new ICheckStateListener() {
            @Override
            public void checkStateChanged(final CheckStateChangedEvent event) {
                BlacklistTab.this.checkboxTreeViewer.setSubtreeChecked(event.getElement(), event.getChecked());

                BlacklistTab.this.setDirty(true);
                BlacklistTab.this.updateLaunchConfigurationDialog();
            }
        };

        this.control = new Composite(parent, SWT.NONE);
        this.control.setLayout(new GridLayout(1, false));

        final Composite mainComposite = new Composite(this.control, SWT.NONE);
        mainComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

        this.stackLayout = new StackLayout();
        mainComposite.setLayout(this.stackLayout);

        this.treeViewerControl = this.createControlTreeViewer(mainComposite);
        this.textFieldControl = this.createTextField(mainComposite);

        this.getModelAnalyzerTabGroupBlackboard().addBlackboardListener(new BlackboardListener() {

            @Override
            public void blackboardChanged() {
                BlacklistTab.this.setRoot(BlacklistTab.this.getModelAnalyzerTabGroupBlackboard().getRoot());
                if (BlacklistTab.this.root == null) {
                    BlacklistTab.this.stackLayout.topControl = BlacklistTab.this.textFieldControl;
                } else {
                    BlacklistTab.this.stackLayout.topControl = BlacklistTab.this.treeViewerControl;
                }

            }

        });
        this.setRoot(this.getModelAnalyzerTabGroupBlackboard().getRoot());
        if (this.root == null) {
            this.switchFromTreeToText();
            // treeViewerControl.setVisible(false);
            // textFieldControl.setVisible(true);
            this.stackLayout.topControl = this.textFieldControl;
        } else {
            this.switchFromTextToTree();
            // textFieldControl.setVisible(false);
            // treeViewerControl.setVisible(true);
            this.stackLayout.topControl = this.treeViewerControl;
        }
    }

    private Composite createControlTreeViewer(final Composite parentControl) {

        final Composite treeViewerControl = new Composite(parentControl, SWT.NONE);
        // treeViewerControl.setLayout(new FillLayout(SWT.HORIZONTAL));
        treeViewerControl.setLayout(new GridLayout(2, false));

        this.checkStateListener = new ICheckStateListener() {
            @Override
            public void checkStateChanged(final CheckStateChangedEvent event) {
                BlacklistTab.this.checkboxTreeViewer.setSubtreeChecked(event.getElement(), event.getChecked());

                BlacklistTab.this.setDirty(true);
                BlacklistTab.this.updateLaunchConfigurationDialog();
            }
        };

        final Label lblSpecifiyBlacklist = new Label(treeViewerControl, SWT.CENTER);
        lblSpecifiyBlacklist.setText("Specify blacklist");
        lblSpecifiyBlacklist.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));

        this.checkboxTreeViewer = new CheckboxTreeViewer(treeViewerControl, SWT.BORDER);
        this.checkboxTreeViewer.setContentProvider(new CheckboxContentProvider());
        this.checkboxTreeViewer.setLabelProvider(new CheckboxLabelProvider());
        this.checkboxTreeViewer.addCheckStateListener(this.checkStateListener);

        final Tree tree = this.checkboxTreeViewer.getTree();
        tree.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1));

        final Label additionalFilterLabel = new Label(treeViewerControl, SWT.NONE);
        additionalFilterLabel.setText("Additional filter regex:");
        additionalFilterLabel.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1));

        this.addiditonalBlacklistTextfield = new Text(treeViewerControl, SWT.BORDER);
        this.addiditonalBlacklistTextfield.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
        this.addiditonalBlacklistTextfield.addModifyListener(new ModifyListener() {

            @Override
            public void modifyText(final ModifyEvent e) {
                BlacklistTab.this.setDirty(true);
                BlacklistTab.this.updateLaunchConfigurationDialog();
            }

        });

        final Composite composite = new Composite(treeViewerControl, SWT.NONE);
        composite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
        composite.setLayout(new GridLayout(1, false));

        final Button invertB = new Button(composite, SWT.NONE);
        invertB.setText("Invert");
        invertB.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

        invertB.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseDown(final MouseEvent e) {
                BlacklistTab.this.checkboxTreeViewer.expandAll();
                final Object[] elements = BlacklistTab.this.checkboxTreeViewer.getCheckedElements();
                BlacklistTab.this.checkboxTreeViewer.setAllChecked(true);
                for (final Object element : elements) {
                    BlacklistTab.this.checkboxTreeViewer.setChecked(element, false);
                }
                BlacklistTab.this.checkboxTreeViewer.collapseAll();
                BlacklistTab.this.setDirty(true);
                BlacklistTab.this.updateLaunchConfigurationDialog();
            }
        });

        final Button selectB = new Button(composite, SWT.NONE);
        selectB.setText("Select all");
        selectB.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false, 1, 1));
        selectB.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseDown(final MouseEvent e) {
                BlacklistTab.this.checkboxTreeViewer.expandAll();
                BlacklistTab.this.checkboxTreeViewer.setAllChecked(true);
                BlacklistTab.this.checkboxTreeViewer.collapseAll();
                BlacklistTab.this.setDirty(true);
                BlacklistTab.this.updateLaunchConfigurationDialog();
            }
        });
        final Button deselectB = new Button(composite, SWT.NONE);
        deselectB.setText("Deselect all");
        deselectB.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, true, 1, 1));
        deselectB.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseDown(final MouseEvent e) {
                BlacklistTab.this.checkboxTreeViewer.expandAll();
                BlacklistTab.this.checkboxTreeViewer.setAllChecked(false);
                BlacklistTab.this.checkboxTreeViewer.collapseAll();
                BlacklistTab.this.setDirty(true);
                BlacklistTab.this.updateLaunchConfigurationDialog();
            }
        });
        return treeViewerControl;

    }

    private void switchFromTreeToText() {
        final Set<String> wildcards = this.getTreeSelection();
        wildcards.addAll(this.getTextField(this.addiditonalBlacklistTextfield));
        this.initializeTextField(this.getTreeSelection());
    }

    private void switchFromTextToTree() {
        this.initializeTreeViewer(this.getTextField(this.textField));
    }

    private Composite createTextField(final Composite parentControl) {
        final Composite textFieldControl = new Group(parentControl, SWT.NONE);
        textFieldControl.setLayout(new FillLayout(SWT.HORIZONTAL));
        textFieldControl.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
        this.textField = new Text(textFieldControl, SWT.MULTI | SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
        // textField.setLayout(new FillLayout(SWT.HORIZONTAL));
        // textField.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
        final ModifyListener textChangedListener = new ModifyListener() {
            @Override
            public void modifyText(final ModifyEvent e) {
                BlacklistTab.this.setDirty(true);
                BlacklistTab.this.updateLaunchConfigurationDialog();
            }

        };
        this.textField.addModifyListener(textChangedListener);
        // textField.setLayoutData(new FillData(FillData.FILL_BOTH));
        textFieldControl.setSize(textFieldControl.getParent().getSize());
        return textFieldControl;
    }

    @Override
    public void deactivated(final ILaunchConfigurationWorkingCopy workingCopy) {

    }

    @Override
    public void dispose() {
    }

    @Override
    public Control getControl() {
        return this.control;
    }

    @Override
    public String getErrorMessage() {
        return null;
    }

    @Override
    public Image getImage() {
        return null;
    }

    @Override
    public String getMessage() {
        return null;
    }

    @Override
    public String getName() {
        return "Blacklist";
    }

    @Override
    public void initializeFrom(final ILaunchConfiguration configuration) {
        try {
            final String wildcardString = configuration
                    .getAttribute(SoMoXProjectPreferences.SOMOX_ANALYSER_WILDCARD_KEY, "");
            final StringTokenizer tokenizer = new StringTokenizer(wildcardString, this.DELIMITER);
            final int tokenCount = tokenizer.countTokens();
            final Set<String> wildcards = new HashSet<String>();
            for (int i = 0; i < tokenCount; i++) {
                wildcards.add(tokenizer.nextToken());
            }

            this.initializeTreeViewer(wildcards);
            this.initializeTextField(wildcards);

            this.addiditonalBlacklistTextfield.setText(configuration
                    .getAttribute(SoMoXProjectPreferences.BLACKLIST_CONFIGURATION_WILDCARDS_ADDITIONAL, ""));
        } catch (final CoreException e) {
        }
    }

    private void initializeTreeViewer(final Set<String> wildcardSet) {
        // Restore check-state
        this.checkboxTreeViewer.expandAll();
        this.checkboxTreeViewer.setAllChecked(true);
        final Object[] elements = this.checkboxTreeViewer.getCheckedElements();
        this.checkboxTreeViewer.setAllChecked(false);
        this.checkboxTreeViewer.collapseAll();

        for (final Object currentElement : elements) {
            if (currentElement instanceof org.emftext.language.java.containers.Package) {
                if (wildcardSet.contains(KDMHelper
                        .computeFullQualifiedName(((org.emftext.language.java.containers.Package) currentElement)))) {
                    this.checkboxTreeViewer.setChecked(currentElement, true);
                }
            } else if (currentElement instanceof Type) {
                if (wildcardSet.contains(KDMHelper.computeFullQualifiedName((Commentable) currentElement))) {
                    this.checkboxTreeViewer.setChecked(currentElement, true);
                }
            }
        }
    }

    private void initializeTextField(final Set<String> wildcardSet) {
        final StringBuffer buffer = new StringBuffer();
        final Iterator<String> iterator = wildcardSet.iterator();
        while (iterator.hasNext()) {
            buffer.append(iterator.next());
            buffer.append(System.getProperty("line.separator"));
        }
        this.textField.setText(buffer.toString());
    }

    @Override
    public boolean isValid(final ILaunchConfiguration launchConfig) {
        return true;
    }

    @Override
    public void launched(final ILaunch launch) {

    }

    private Set<String> getTreeSelection() {
        final Object[] checked = this.checkboxTreeViewer.getCheckedElements();
        // String[] wildcards = new String[checked.length];
        final Set<String> wildcards = new HashSet<String>();
        // int i = 0;
        for (final Object current : checked) {
            if (current instanceof Type) {
                // wildcards[i] = ((GASTClass) current).getQualifiedName();
                wildcards.add(KDMHelper.computeFullQualifiedName(((Type) current)));
            } else if (current instanceof org.emftext.language.java.containers.Package) {
                // wildcards[i] = ((de.fzi.gast.core.Package) current).getQualifiedName()+ ".*";

                // TODO: wildcard for packages conflict with usability
                wildcards.add(
                        KDMHelper.computeFullQualifiedName(((org.emftext.language.java.containers.Package) current)));
            }
            // i++;
        }

        return wildcards;
    }

    private Set<String> getTextField(final Text myTextField) {
        final Set<String> completeResult = new HashSet<String>();
        final String[] result = myTextField.getText().split(System.getProperty("line.separator"));
        for (final String element : result) {
            final String[] commaResult = element.split(",");
            for (final String element2 : commaResult) {
                final String[] semicolonResult = element2.split(";");
                for (final String element3 : semicolonResult) {
                    completeResult.add(element3);
                }
            }
        }
        return completeResult;

    }

    @Override
    public void performApply(final ILaunchConfigurationWorkingCopy configuration) {
        Set<String> wildcards;
        if (this.stackLayout.topControl == this.textFieldControl) {
            wildcards = this.getTextField(this.textField);
        } else {
            wildcards = this.getTreeSelection();
            wildcards.addAll(this.getTextField(this.addiditonalBlacklistTextfield));
        }
        final StringBuffer buffer = new StringBuffer();
        final Iterator<String> iterator = wildcards.iterator();
        while (iterator.hasNext()) {
            buffer.append(iterator.next());
            buffer.append(this.DELIMITER);
        }
        configuration.setAttribute(SoMoXProjectPreferences.SOMOX_ANALYSER_WILDCARD_KEY, buffer.toString());
        configuration.setAttribute(SoMoXProjectPreferences.BLACKLIST_CONFIGURATION_WILDCARDS_ADDITIONAL,
                this.addiditonalBlacklistTextfield.getText());
    }

    @Override
    public void setDefaults(final ILaunchConfigurationWorkingCopy configuration) {
        if (this.checkboxTreeViewer != null) {
            this.checkboxTreeViewer.setInput(null);
        }
        configuration.setAttribute(SoMoXProjectPreferences.BLACKLIST_CONFIGURATION_WILDCARDS_ADDITIONAL, "");
    }
}
