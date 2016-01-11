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

    @Override
    public void activated(final ILaunchConfigurationWorkingCopy workingCopy) {

    }

    @Override
    public boolean canSave() {
        return true;
    }

    /**
     * @wbp.parser.entryPoint
     */
    @Override
    public void createControl(final Composite parent) {

        this.control = new Composite(parent, SWT.BORDER);
        this.control.setLayout(new GridLayout(3, false));

        // final ScrolledComposite scrollContainer = new ScrolledComposite(control,
        // SWT.BORDER | SWT.V_SCROLL);
        //
        // Composite container = new Composite(scrollContainer, SWT.NONE);
        // scrollContainer.setContent(container);
        //
        // container.setLayout(new GridLayout(3,false));

        this.keyLabels = new ArrayList<Label>();
        this.valueSliders = new ArrayList<Slider>();
        this.valueLabels = new ArrayList<Label>();

        int weightCounter = 0;
        for (final MetricsDetails metricDetail : projectPreferences.orderedMetricDetails) {
            final Label keyLabel = new Label(this.control, SWT.NONE);
            keyLabel.setText(metricDetail.metricLabel); // metric label
            keyLabel.setToolTipText(metricDetail.metricExplanantion); // metric explanation text
            this.keyLabels.add(keyLabel);

            final Label valueLabel = new Label(this.control, SWT.BORDER);
            final GridData gd = new GridData();
            gd.minimumWidth = 100;
            gd.widthHint = 30;
            valueLabel.setAlignment(SWT.CENTER);
            valueLabel.setLayoutData(gd);
            this.valueLabels.add(valueLabel);

            final Slider slider = new Slider(this.control, SWT.HORIZONTAL);
            slider.setMinimum(0);
            slider.setMaximum(110);
            slider.setIncrement(1);
            slider.addSelectionListener(new SelectionListener() {
                @Override
                public void widgetSelected(final SelectionEvent e) {
                    valueLabel.setText(String.valueOf((slider.getSelection())));
                    WeightsTab.this.setDirty(true);
                    WeightsTab.this.updateLaunchConfigurationDialog();
                }

                @Override
                public void widgetDefaultSelected(final SelectionEvent e) {
                }
            });
            this.valueSliders.add(slider);

            weightCounter++;
        }

        // important for the scrollbars
        // container.setSize(container.computeSize(SWT.DEFAULT, SWT.DEFAULT));
        // scrollContainer.setAlwaysShowScrollBars(true);
        // scrollContainer.setMinSize(container.computeSize(SWT.DEFAULT, 100));
        // scrollContainer.setExpandHorizontal(true);
        // setControl(scrollContainer);
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
        return "Weights";
    }

    @Override
    public void initializeFrom(final ILaunchConfiguration configuration) {
        try {
            for (int i = 0; i < this.valueSliders.size(); i++) {
                final String value = configuration
                        .getAttribute(projectPreferences.orderedMetricDetails.get(i).metricWeightPeferenceName, "");
                // System.out.println("value was:" + value);
                this.valueSliders.get(i).setSelection(Integer.valueOf(value));
                this.valueLabels.get(i).setText(value + "");

            }

        } catch (final CoreException e) {

            e.printStackTrace();
        }

    }

    @Override
    public boolean isValid(final ILaunchConfiguration launchConfig) {
        return true;
    }

    @Override
    public void launched(final ILaunch launch) {

    }

    @Override
    public void performApply(final ILaunchConfigurationWorkingCopy configuration) {
        for (int i = 0; i < this.valueSliders.size(); i++) {
            final int value = this.valueSliders.get(i).getSelection();
            configuration.setAttribute(projectPreferences.orderedMetricDetails.get(i).metricWeightPeferenceName,
                    String.valueOf(value));
        }
    }

    @Override
    public void setDefaults(final ILaunchConfigurationWorkingCopy configuration) {

        setDefault(configuration);

    }

    /**
     * Helper method to set the default values
     *
     * @param configuration
     */
    public static void setDefault(final ILaunchConfigurationWorkingCopy configuration) {
        // Read properties file
        final Properties properties = new Properties();
        try {
            final InputStream inStream = Activator.getDefault().getBundle()
                    .getEntry(CONFIG_SIMPLE_ANALYZER_PROPERTIES_FILE).openStream();
            properties.load(inStream);

            for (final MetricsDetails metricDetail : projectPreferences.orderedMetricDetails) {
                configuration.setAttribute(metricDetail.metricWeightPeferenceName,
                        properties.getProperty(metricDetail.metricWeightPeferenceName));
            }

            for (final Object o : configuration.getAttributes().entrySet()) {
                if (o == null) {
                    logger.error("At least one metric weight was not defined in the properties file");
                }
            }

        } catch (final IOException e) {
            logger.error(
                    "Could not read property file " + CONFIG_SIMPLE_ANALYZER_PROPERTIES_FILE + " with metric defaults",
                    e);
            e.printStackTrace();
        } catch (final CoreException e) {
            logger.error("Could not read property file with metric defaults", e);
            e.printStackTrace();
        }
    }
}
