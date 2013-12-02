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
	
	public void activated(ILaunchConfigurationWorkingCopy workingCopy) {

	}

	public boolean canSave() {
		return true;
	}

	public void setRoot(Root root) {
		if (this.root != root) {
			checkboxTreeViewer.getTree().dispose();
			checkboxTreeViewer = new CheckboxTreeViewer(treeViewerControl, SWT.BORDER);
			checkboxTreeViewer
			.setContentProvider(new CheckboxContentProvider());
			checkboxTreeViewer.setLabelProvider(new CheckboxLabelProvider());
			Tree tree = checkboxTreeViewer.getTree();
			tree.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1,
					1));
			checkboxTreeViewer.addCheckStateListener(checkStateListener);
		}
		this.root = root;
		checkboxTreeViewer.setInput(this.root);

		if (this.root != null) {
			checkboxTreeViewer.setGrayed(this.root, true);
		}
	}

	/**
	 * @wbp.parser.entryPoint
	 */
	public void createControl(Composite parent) {
		checkStateListener = new ICheckStateListener() {
			public void checkStateChanged(CheckStateChangedEvent event) {
				checkboxTreeViewer.setSubtreeChecked(event.getElement(),event.getChecked());
				
				setDirty(true);
				updateLaunchConfigurationDialog();
			}
		};

		control = new Composite(parent, SWT.NONE);
		control.setLayout(new GridLayout(1, false));
		
		final Composite mainComposite = new Composite(control, SWT.NONE);
		mainComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		stackLayout = new StackLayout();
		mainComposite.setLayout(stackLayout);

		treeViewerControl = createControlTreeViewer(mainComposite);
		textFieldControl = createTextField(mainComposite);

		this.getModelAnalyzerTabGroupBlackboard().addBlackboardListener(new BlackboardListener() {

			public void blackboardChanged() {
				BlacklistTab.this.setRoot(BlacklistTab.this.getModelAnalyzerTabGroupBlackboard().getRoot());
				if (BlacklistTab.this.root == null) {
					stackLayout.topControl = textFieldControl;
				} else {
					stackLayout.topControl = treeViewerControl;
				}
				
			}

		});
		this.setRoot(this.getModelAnalyzerTabGroupBlackboard().getRoot());
		if (root == null) {
			switchFromTreeToText();
			//treeViewerControl.setVisible(false);
			//textFieldControl.setVisible(true);
			stackLayout.topControl = textFieldControl;
		} else {
			switchFromTextToTree();
			//textFieldControl.setVisible(false);
			//treeViewerControl.setVisible(true);
			stackLayout.topControl = treeViewerControl;
		}
	}
	
	private Composite createControlTreeViewer(Composite parentControl) {

		Composite treeViewerControl = new Composite(parentControl, SWT.NONE);
		//treeViewerControl.setLayout(new FillLayout(SWT.HORIZONTAL));
		treeViewerControl.setLayout(new GridLayout(2, false));

		checkStateListener = new ICheckStateListener() {
			public void checkStateChanged(CheckStateChangedEvent event) {
				checkboxTreeViewer.setSubtreeChecked(event.getElement(), event.getChecked());

				setDirty(true);
				updateLaunchConfigurationDialog();
			}
		};

		Label lblSpecifiyBlacklist = new Label(treeViewerControl, SWT.CENTER);
		lblSpecifiyBlacklist.setText("Specify blacklist");
		lblSpecifiyBlacklist.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));

		checkboxTreeViewer = new CheckboxTreeViewer(treeViewerControl,SWT.BORDER);
		checkboxTreeViewer.setContentProvider(new CheckboxContentProvider());
		checkboxTreeViewer.setLabelProvider(new CheckboxLabelProvider());
		checkboxTreeViewer.addCheckStateListener(checkStateListener);

		Tree tree = checkboxTreeViewer.getTree();
		tree.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1));
		
		Label additionalFilterLabel = new Label(treeViewerControl, SWT.NONE);
		additionalFilterLabel.setText("Additional filter regex:");
		additionalFilterLabel.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false,1,1));

		addiditonalBlacklistTextfield = new Text(treeViewerControl, SWT.BORDER);
		addiditonalBlacklistTextfield.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false,1,1));
		addiditonalBlacklistTextfield.addModifyListener(new ModifyListener() {

			public void modifyText(ModifyEvent e) {
				setDirty(true);
				updateLaunchConfigurationDialog();	
			}
			
		});
		
		Composite composite = new Composite(treeViewerControl, SWT.NONE);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false,
				false, 1, 1));
		composite.setLayout(new GridLayout(1, false));

		Button invertB = new Button(composite, SWT.NONE);
		invertB.setText("Invert");
		invertB.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,1,1));

		invertB.addMouseListener(new MouseAdapter() {
			public void mouseDown(MouseEvent e) {
				checkboxTreeViewer.expandAll();
				Object[] elements = checkboxTreeViewer.getCheckedElements();
				checkboxTreeViewer.setAllChecked(true);
				for (int i = 0; i < elements.length; i++) {
					checkboxTreeViewer.setChecked(elements[i], false);
				}
				checkboxTreeViewer.collapseAll();
				setDirty(true);
				updateLaunchConfigurationDialog();
			}
		});

		Button selectB = new Button(composite, SWT.NONE);
		selectB.setText("Select all");
		selectB.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false,1,1));
		selectB.addMouseListener(new MouseAdapter() {
			public void mouseDown(MouseEvent e) {
				checkboxTreeViewer.expandAll();
				checkboxTreeViewer.setAllChecked(true);
				checkboxTreeViewer.collapseAll();
				setDirty(true);
				updateLaunchConfigurationDialog();
			}
		});
		Button deselectB = new Button(composite, SWT.NONE);
		deselectB.setText("Deselect all");
		deselectB.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, true,1,1));
		deselectB.addMouseListener(new MouseAdapter() {
			public void mouseDown(MouseEvent e) {
				checkboxTreeViewer.expandAll();
				checkboxTreeViewer.setAllChecked(false);
				checkboxTreeViewer.collapseAll();
				setDirty(true);
				updateLaunchConfigurationDialog();
			}
		});
		return treeViewerControl;

	}
	
	private void switchFromTreeToText() {
		Set<String> wildcards = getTreeSelection();
		wildcards.addAll(getTextField(addiditonalBlacklistTextfield));
		initializeTextField(getTreeSelection());
	}
	
	private void switchFromTextToTree() {
		initializeTreeViewer(getTextField(textField));
	}

	private Composite createTextField(Composite parentControl) {
		Composite textFieldControl = new Group(parentControl, SWT.NONE);
		textFieldControl.setLayout(new FillLayout(SWT.HORIZONTAL));
		textFieldControl.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		textField = new Text(textFieldControl,
				SWT.MULTI | SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
		//textField.setLayout(new FillLayout(SWT.HORIZONTAL));
		//textField.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		ModifyListener textChangedListener = new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				setDirty(true);
				updateLaunchConfigurationDialog();
			}
			
		};
		textField.addModifyListener(textChangedListener);
		//textField.setLayoutData(new FillData(FillData.FILL_BOTH));
		textFieldControl.setSize(textFieldControl.getParent().getSize());
		return textFieldControl;
	}
	
	public void deactivated(ILaunchConfigurationWorkingCopy workingCopy) {

	}

	public void dispose() {
	}

	public Control getControl() {
		return control;
	}

	public String getErrorMessage() {
		return null;
	}

	public Image getImage() {
		return null;
	}

	public String getMessage() {
		return null;
	}

	public String getName() {
		return "Blacklist";
	}

	public void initializeFrom(ILaunchConfiguration configuration) {
		try {
			String wildcardString = configuration.getAttribute(SoMoXProjectPreferences.SOMOX_ANALYSER_WILDCARD_KEY, "");
			StringTokenizer tokenizer = new StringTokenizer(wildcardString,
					DELIMITER);
			int tokenCount = tokenizer.countTokens();
			Set<String> wildcards = new HashSet<String>();
			for (int i = 0; i < tokenCount; i++) {
				wildcards.add(tokenizer.nextToken());
			}

			initializeTreeViewer(wildcards);
			initializeTextField(wildcards);
			
			addiditonalBlacklistTextfield.setText(configuration.getAttribute(SoMoXProjectPreferences.BLACKLIST_CONFIGURATION_WILDCARDS_ADDITIONAL, ""));
		} catch (CoreException e) {
		}
	}
	
	private void initializeTreeViewer(Set<String> wildcardSet) {
		// Restore check-state
		checkboxTreeViewer.expandAll();
		checkboxTreeViewer.setAllChecked(true);
		Object[] elements = checkboxTreeViewer.getCheckedElements();
		checkboxTreeViewer.setAllChecked(false);
		checkboxTreeViewer.collapseAll();

		for (Object currentElement : elements) {
			if (currentElement instanceof org.eclipse.gmt.modisco.java.Package) {
				if (wildcardSet.contains(KDMHelper.computeFullQualifiedName(((org.eclipse.gmt.modisco.java.Package) currentElement)))) {
					checkboxTreeViewer.setChecked(currentElement, true);
				}
			} else if (currentElement instanceof Type) {
				if (wildcardSet.contains(KDMHelper.computeFullQualifiedName(((Type) currentElement)))) {
					checkboxTreeViewer.setChecked(currentElement, true);
				}
			}
		}
	}

	private void initializeTextField(Set<String> wildcardSet) {
		StringBuffer buffer = new StringBuffer();
		Iterator<String> iterator = wildcardSet.iterator();
		while (iterator.hasNext()) {
			buffer.append(iterator.next());
			buffer.append(System.getProperty("line.separator"));
		}
		textField.setText(buffer.toString());
	}

	public boolean isValid(ILaunchConfiguration launchConfig) {
		return true;
	}

	public void launched(ILaunch launch) {

	}
	

	private Set<String> getTreeSelection() {
		Object[] checked = checkboxTreeViewer.getCheckedElements();
		//String[] wildcards = new String[checked.length];
		Set<String> wildcards = new HashSet<String>();
		//int i = 0;
		for (Object current : checked) {
			if (current instanceof Type) {
				//wildcards[i] = ((GASTClass) current).getQualifiedName();
				wildcards.add(KDMHelper.computeFullQualifiedName(((Type) current)));
			} else if (current instanceof org.eclipse.gmt.modisco.java.Package) {
				//wildcards[i] = ((de.fzi.gast.core.Package) current).getQualifiedName()+ ".*";

				// TODO: wildcard for packages conflict with usability
				wildcards.add(KDMHelper.computeFullQualifiedName(((org.eclipse.gmt.modisco.java.Package) current)));
			}
			//i++;
		}

		return wildcards;
	}
	
	private Set<String> getTextField(Text myTextField) {
		Set<String> completeResult = new HashSet<String>();
		String[] result = myTextField.getText().split(System.getProperty("line.separator"));
	    for (int i=0; i<result.length; i++) {
	    	String[] commaResult = result[i].split(",");
	    	for (int j=0; j<commaResult.length; j++) {
	    		String[] semicolonResult = commaResult[j].split(";");
	    		for (int k=0; k<semicolonResult.length; k++) {
	    			completeResult.add(semicolonResult[k]);
	    		}
	    	}
	    }
	    return completeResult;

	}

	public void performApply(ILaunchConfigurationWorkingCopy configuration) {
		Set<String> wildcards;
		if (stackLayout.topControl == textFieldControl) {
			wildcards = getTextField(textField);
		} else {
			wildcards = getTreeSelection();
			wildcards.addAll(getTextField(addiditonalBlacklistTextfield));
		}
		StringBuffer buffer = new StringBuffer();
		Iterator<String> iterator = wildcards.iterator();
		while (iterator.hasNext()) {
			buffer.append(iterator.next());
			buffer.append(DELIMITER);
		}
		configuration.setAttribute(SoMoXProjectPreferences.SOMOX_ANALYSER_WILDCARD_KEY, buffer.toString());
		configuration.setAttribute(SoMoXProjectPreferences.BLACKLIST_CONFIGURATION_WILDCARDS_ADDITIONAL, addiditonalBlacklistTextfield.getText());
	}

	public void setDefaults(ILaunchConfigurationWorkingCopy configuration) {
		if (checkboxTreeViewer != null) {
			checkboxTreeViewer.setInput(null);
		}
		configuration.setAttribute(SoMoXProjectPreferences.BLACKLIST_CONFIGURATION_WILDCARDS_ADDITIONAL, "");
	}
}
