package org.somox.analyzer.simplemodelanalyzer.metrics.tabs;
/**
 * @author Oliver Burkhardt, Klaus Krogmann
 */
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Slider;
import org.somox.analyzer.simplemodelanalyzer.Activator;
import org.somox.common.MetricsDetails;
import org.somox.common.SoMoXProjectPreferences;
import org.somox.metrics.tabs.MetricTab;

public class WeightsTab extends MetricTab {
	
	/**
	 * Default weights
	 */
	private static final String CONFIG_SIMPLE_ANALYZER_PROPERTIES_FILE = "/config/SimpleAnalyzer.properties";

	protected Composite control;
	
	private static Logger logger = Logger.getLogger(WeightsTab.class);
	private ArrayList<Label> keyLabels;
	private ArrayList<Slider> valueSliders;
	private ArrayList<Label> valueLabels;

	private static SoMoXProjectPreferences projectPreferences = new SoMoXProjectPreferences();

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
		control.setLayout(new GridLayout(3,false));

//		final ScrolledComposite scrollContainer = new ScrolledComposite(control,
//		SWT.BORDER | SWT.V_SCROLL);
//		
//		Composite container = new Composite(scrollContainer, SWT.NONE);
//		scrollContainer.setContent(container);
//		
//		container.setLayout(new GridLayout(3,false));
				
		keyLabels = new ArrayList<Label>();
		valueSliders = new ArrayList<Slider>();
		valueLabels = new ArrayList<Label>();
				
		int weightCounter=0;
		for(MetricsDetails metricDetail : projectPreferences.orderedMetricDetails){
			Label keyLabel = new Label(control,SWT.NONE);
			keyLabel.setText(metricDetail.metricLabel); // metric label			
			keyLabel.setToolTipText(metricDetail.metricExplanantion); // metric explanation text		
			keyLabels.add(keyLabel);
			
			final Label valueLabel = new Label(control,SWT.BORDER);
			GridData gd = new GridData();
			gd.minimumWidth = 100;
			gd.widthHint = 30;
			valueLabel.setAlignment(SWT.CENTER);
			valueLabel.setLayoutData(gd);
			valueLabels.add(valueLabel);
			
			final Slider slider = new Slider(control, SWT.HORIZONTAL);
			slider.setMinimum(0);
			slider.setMaximum(110);
			slider.setIncrement(1);
			slider.addSelectionListener(new SelectionListener()
			{
				@Override
				public void widgetSelected(SelectionEvent e) {
					valueLabel.setText(String.valueOf((slider.getSelection())));
					setDirty(true);
					updateLaunchConfigurationDialog();
				}
				
				@Override
				public void widgetDefaultSelected(SelectionEvent e) {
				}
			});
			valueSliders.add(slider);
			
			weightCounter++;
		}
			
		
		// important for the scrollbars
//		container.setSize(container.computeSize(SWT.DEFAULT, SWT.DEFAULT));
//		scrollContainer.setAlwaysShowScrollBars(true);
//		scrollContainer.setMinSize(container.computeSize(SWT.DEFAULT, 100));
//		scrollContainer.setExpandHorizontal(true);
		//setControl(scrollContainer);		
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
		return "Weights";
	}

	public void initializeFrom(ILaunchConfiguration configuration) {
		try {
			for (int i = 0 ; i < valueSliders.size() ; i++)
			{
				String value = configuration.getAttribute(projectPreferences.orderedMetricDetails.get(i).metricWeightPeferenceName, "");
//				System.out.println("value was:" + value);	
				valueSliders.get(i).setSelection(Integer.valueOf(value));
				valueLabels.get(i).setText(value + "");
				
			}

		} catch (CoreException e) {			
			
			e.printStackTrace();
		}

	}

	public boolean isValid(ILaunchConfiguration launchConfig) {
		return true;
	}

	public void launched(ILaunch launch) {

	}

	public void performApply(ILaunchConfigurationWorkingCopy configuration) {
		for (int i = 0 ; i < valueSliders.size() ; i++)
		{
			int value = valueSliders.get(i).getSelection();
			configuration.setAttribute(projectPreferences.orderedMetricDetails.get(i).metricWeightPeferenceName, String.valueOf(value));
		}
	}

	public void setDefaults(ILaunchConfigurationWorkingCopy configuration) {

		setDefault(configuration);

		
	}
	/**
	 * Helper method to set the default values
	 * @param configuration
	 */
	public static void setDefault(ILaunchConfigurationWorkingCopy configuration){
		// Read properties file
	    Properties properties = new Properties();
	    try {
	    	InputStream inStream = Activator.getDefault().getBundle().getEntry(CONFIG_SIMPLE_ANALYZER_PROPERTIES_FILE).openStream();
	    	properties.load(inStream);

	    	for(MetricsDetails metricDetail : projectPreferences.orderedMetricDetails) {
	    		configuration.setAttribute(metricDetail.metricWeightPeferenceName, properties.getProperty(metricDetail.metricWeightPeferenceName));
	    	}
	
			for(Object o : configuration.getAttributes().entrySet()) {
				if(o == null) {
					logger.error("At least one metric weight was not defined in the properties file");
				}
			}
			
	    } catch (IOException e) {
	    	logger.error("Could not read property file " + CONFIG_SIMPLE_ANALYZER_PROPERTIES_FILE + " with metric defaults", e);
	    	e.printStackTrace();
	    } catch (CoreException e) {
	    	logger.error("Could not read property file with metric defaults", e);
	    	e.printStackTrace();
	    }
	}
}
