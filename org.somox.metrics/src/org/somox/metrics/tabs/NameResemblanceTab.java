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
        this.control.setLayout(new FillLayout(SWT.HORIZONTAL));
        {
            final Group grpPrefixes = new Group(this.control, SWT.BORDER);
            grpPrefixes.setLayout(new FillLayout(SWT.VERTICAL));
            grpPrefixes.setText("Prefixes");
            {
                final Composite composite = new Composite(grpPrefixes, SWT.NONE);
                {
                    this.prefixList = new List(composite, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
                    this.prefixList.setBounds(0, 0, 314, 205);
                }
            }
            {
                final Composite composite = new Composite(grpPrefixes, SWT.NONE);
                final GridLayout gridLayout = new GridLayout(2, false);
                gridLayout.verticalSpacing = 3;
                composite.setLayout(gridLayout);
                new Label(composite, SWT.NONE);
                {
                    final Button prefixRemoveButton = new Button(composite, SWT.NONE);
                    prefixRemoveButton.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseDown(final MouseEvent e) {
                            NameResemblanceTab.this.prefixList
                                    .remove(NameResemblanceTab.this.prefixList.getFocusIndex());
                            NameResemblanceTab.this.setDirty(true);
                            NameResemblanceTab.this.updateLaunchConfigurationDialog();
                        }
                    });
                    prefixRemoveButton.setText("Remove selected");
                }
                {
                    this.prefixText = new Text(composite, SWT.BORDER);
                    this.prefixText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
                }
                {
                    final Button prefixAddButton = new Button(composite, SWT.NONE);
                    prefixAddButton.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseDown(final MouseEvent e) {
                            final String text = NameResemblanceTab.this.prefixText.getText();
                            if (text != null && !text.equals("")) {
                                NameResemblanceTab.this.prefixList.add(text);
                                NameResemblanceTab.this.setDirty(true);
                                NameResemblanceTab.this.updateLaunchConfigurationDialog();
                            }
                            NameResemblanceTab.this.prefixText.setText("");
                        }
                    });
                    prefixAddButton.setText("Add prefix");
                }
            }
        }
        {
            final Group grpSuffixes = new Group(this.control, SWT.BORDER);
            grpSuffixes.setLayout(new FillLayout(SWT.VERTICAL));
            grpSuffixes.setText("Suffixes");
            {
                final Composite composite = new Composite(grpSuffixes, SWT.NONE);
                {
                    this.suffixList = new List(composite, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
                    this.suffixList.setBounds(0, 0, 314, 205);
                }
            }
            {
                final Composite composite = new Composite(grpSuffixes, SWT.NONE);
                final GridLayout gridLayout = new GridLayout(2, false);
                gridLayout.verticalSpacing = 3;
                composite.setLayout(gridLayout);
                new Label(composite, SWT.NONE);
                {
                    final Button suffixRemoveButton = new Button(composite, SWT.NONE);
                    suffixRemoveButton.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseDown(final MouseEvent e) {
                            NameResemblanceTab.this.suffixList
                                    .remove(NameResemblanceTab.this.suffixList.getFocusIndex());
                            NameResemblanceTab.this.setDirty(true);
                            NameResemblanceTab.this.updateLaunchConfigurationDialog();
                        }
                    });
                    suffixRemoveButton.setText("Remove selected");
                }
                {
                    this.suffixText = new Text(composite, SWT.BORDER);
                    this.suffixText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
                }
                {
                    final Button suffixAddButton = new Button(composite, SWT.NONE);
                    suffixAddButton.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseDown(final MouseEvent e) {
                            final String text = NameResemblanceTab.this.suffixText.getText();
                            if (text != null && !text.equals("")) {
                                NameResemblanceTab.this.suffixList.add(text);
                                NameResemblanceTab.this.setDirty(true);
                                NameResemblanceTab.this.updateLaunchConfigurationDialog();
                            }
                            NameResemblanceTab.this.suffixText.setText("");
                        }
                    });
                    suffixAddButton.setText("Add suffix");
                }
            }
        }
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
        return "NameResemblance";
    }

    @Override
    public void initializeFrom(final ILaunchConfiguration configuration) {
        try {
            final String suffixString = configuration.getAttribute(NAME_RESEMBLANCE_CONFIGURATION_EXCLUDED_SUFFIXES,
                    "");
            final String prefixString = configuration.getAttribute(NAME_RESEMBLANCE_CONFIGURATION_EXCLUDED_PREFIXES,
                    "");

            StringTokenizer tokenizer = new StringTokenizer(suffixString, this.DELIMITER);
            int tokenCount = tokenizer.countTokens();
            final String[] suffixes = new String[tokenCount];
            for (int i = 0; i < tokenCount; i++) {
                suffixes[i] = (tokenizer.nextToken());
            }

            tokenizer = new StringTokenizer(prefixString, this.DELIMITER);
            tokenCount = tokenizer.countTokens();
            final String[] prefixes = new String[tokenCount];
            for (int i = 0; i < tokenCount; i++) {
                prefixes[i] = (tokenizer.nextToken());
            }

            this.prefixList.setItems(prefixes);
            this.suffixList.setItems(suffixes);
        } catch (final CoreException e) {

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
        final String[] prefixes = this.prefixList.getItems();
        final String[] suffixes = this.suffixList.getItems();

        StringBuffer buffer = new StringBuffer();
        for (final String prefixe : prefixes) {
            buffer.append(prefixe);
            buffer.append(this.DELIMITER);
        }
        configuration.setAttribute(NAME_RESEMBLANCE_CONFIGURATION_EXCLUDED_PREFIXES, buffer.toString());

        buffer = new StringBuffer();
        for (final String suffixe : suffixes) {
            buffer.append(suffixe);
            buffer.append(this.DELIMITER);
        }
        configuration.setAttribute(NAME_RESEMBLANCE_CONFIGURATION_EXCLUDED_SUFFIXES, buffer.toString());
    }

    @Override
    public void setDefaults(final ILaunchConfigurationWorkingCopy configuration) {
    }
}
