package org.somox.metrics.tabs;

import java.util.StringTokenizer;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.swt.SWT;
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
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Text;

public class NameResemblanceTab extends MetricTab {
	
	protected Composite control;
	private Text suffixText;
	private Text prefixText;
	private List prefixList;
	private List suffixList;
	private final String DELIMITER = "§";
	
	public static final String NAME_RESEMBLANCE_CONFIGURATION_EXCLUDED_SUFFIXES = "org.somox.metrics.nameResemblance.excludedSuffixes";
	public static final String NAME_RESEMBLANCE_CONFIGURATION_EXCLUDED_PREFIXES = "org.somox.metrics.nameResemblance.excludedPrefixes";

	public void activated(ILaunchConfigurationWorkingCopy workingCopy) {

	}

	public boolean canSave() {
		return true;
	}

	/**
	 * @wbp.parser.entryPoint
	 */
	public void createControl(Composite parent) {		
		control = new Composite(parent, SWT.BORDER);
		control.setLayout(new FillLayout(SWT.HORIZONTAL));
		{
			Group grpPrefixes = new Group(control, SWT.BORDER);
			grpPrefixes.setLayout(new FillLayout(SWT.VERTICAL));
			grpPrefixes.setText("Prefixes");
			{
				Composite composite = new Composite(grpPrefixes, SWT.NONE);
				{
					prefixList = new List(composite, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
					prefixList.setBounds(0, 0, 314, 205);
				}
			}
			{
				Composite composite = new Composite(grpPrefixes, SWT.NONE);
				GridLayout gridLayout = new GridLayout(2, false);
				gridLayout.verticalSpacing = 3;
				composite.setLayout(gridLayout);
				new Label(composite, SWT.NONE);
				{
					Button prefixRemoveButton = new Button(composite, SWT.NONE);
					prefixRemoveButton.addMouseListener(new MouseAdapter() {
						public void mouseDown(MouseEvent e) {
							prefixList.remove(prefixList.getFocusIndex());
							setDirty(true);
							updateLaunchConfigurationDialog();
						}
					});
					prefixRemoveButton.setText("Remove selected");
				}
				{
					prefixText = new Text(composite, SWT.BORDER);
					prefixText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
				}
				{
					Button prefixAddButton = new Button(composite, SWT.NONE);
					prefixAddButton.addMouseListener(new MouseAdapter() {
						public void mouseDown(MouseEvent e) {
							String text = prefixText.getText();
							if (text != null && !text.equals("")) {
								prefixList.add(text);
								setDirty(true);
								updateLaunchConfigurationDialog();
							}
							prefixText.setText("");
						}
					});
					prefixAddButton.setText("Add prefix");
				}
			}
		}
		{
			Group grpSuffixes = new Group(control, SWT.BORDER);
			grpSuffixes.setLayout(new FillLayout(SWT.VERTICAL));
			grpSuffixes.setText("Suffixes");
			{
				Composite composite = new Composite(grpSuffixes, SWT.NONE);
				{
					suffixList = new List(composite, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
					suffixList.setBounds(0, 0, 314, 205);
				}
			}
			{
				Composite composite = new Composite(grpSuffixes, SWT.NONE);
				GridLayout gridLayout = new GridLayout(2, false);
				gridLayout.verticalSpacing = 3;
				composite.setLayout(gridLayout);
				new Label(composite, SWT.NONE);
				{
					Button suffixRemoveButton = new Button(composite, SWT.NONE);
					suffixRemoveButton.addMouseListener(new MouseAdapter() {
						public void mouseDown(MouseEvent e) {
							suffixList.remove(suffixList.getFocusIndex());
							NameResemblanceTab.this.setDirty(true);
							NameResemblanceTab.this.updateLaunchConfigurationDialog();
						}
					});
					suffixRemoveButton.setText("Remove selected");
				}
				{
					suffixText = new Text(composite, SWT.BORDER);
					suffixText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
				}
				{
					Button suffixAddButton = new Button(composite, SWT.NONE);
					suffixAddButton.addMouseListener(new MouseAdapter() {
						public void mouseDown(MouseEvent e) {
							String text = suffixText.getText();
							if (text != null && !text.equals("")) {
								suffixList.add(text);
								NameResemblanceTab.this.setDirty(true);
								NameResemblanceTab.this.updateLaunchConfigurationDialog();
							}
							suffixText.setText("");
						}
					});
					suffixAddButton.setText("Add suffix");
				}
			}
		}
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
		return "NameResemblance";
	}

	public void initializeFrom(ILaunchConfiguration configuration) {
		try {
			String suffixString = configuration.getAttribute(NAME_RESEMBLANCE_CONFIGURATION_EXCLUDED_SUFFIXES,"");
			String prefixString = configuration.getAttribute(NAME_RESEMBLANCE_CONFIGURATION_EXCLUDED_PREFIXES,"");
			
			StringTokenizer tokenizer = new StringTokenizer(suffixString, DELIMITER);
			int tokenCount = tokenizer.countTokens();
			String[] suffixes = new String[tokenCount];
			for (int i = 0; i < tokenCount; i++) {
				suffixes[i] = (tokenizer.nextToken());
			}
			
			tokenizer = new StringTokenizer(prefixString, DELIMITER);
			tokenCount = tokenizer.countTokens();
			String[] prefixes = new String[tokenCount];
			for (int i = 0; i < tokenCount; i++) {
				prefixes[i] = (tokenizer.nextToken());
			}
			
			prefixList.setItems(prefixes);
			suffixList.setItems(suffixes);
		} catch (CoreException e) {
			
		}
	}

	public boolean isValid(ILaunchConfiguration launchConfig) {
		return true;
	}

	public void launched(ILaunch launch) {

	}

	public void performApply(ILaunchConfigurationWorkingCopy configuration) {		
		String[] prefixes = prefixList.getItems();
		String[] suffixes = suffixList.getItems();
		
		StringBuffer buffer = new StringBuffer();
		for (int i=0;i<prefixes.length;i++) {
			buffer.append(prefixes[i]);
			buffer.append(DELIMITER);
		}
		configuration.setAttribute(NAME_RESEMBLANCE_CONFIGURATION_EXCLUDED_PREFIXES, buffer.toString());
		

		buffer = new StringBuffer();
		for (int i=0;i<suffixes.length;i++) {
			buffer.append(suffixes[i]);
			buffer.append(DELIMITER);
		}
		configuration.setAttribute(NAME_RESEMBLANCE_CONFIGURATION_EXCLUDED_SUFFIXES, buffer.toString());
	}

	public void setDefaults(ILaunchConfigurationWorkingCopy configuration) {
	}
}
