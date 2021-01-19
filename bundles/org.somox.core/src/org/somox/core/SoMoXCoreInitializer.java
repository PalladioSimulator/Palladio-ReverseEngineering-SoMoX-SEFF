package org.somox.core;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.core.runtime.preferences.DefaultScope;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;

public class SoMoXCoreInitializer extends AbstractPreferenceInitializer {
    @Override
    public void initializeDefaultPreferences() {
        final IEclipsePreferences node = new DefaultScope().getNode(Activator.PLUGIN_ID);
        node.put("Key1", "Value1");
        node.putInt("Key2", 42);
    }
}
