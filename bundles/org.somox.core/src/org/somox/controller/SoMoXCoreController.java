package org.somox.controller;

import org.somox.configuration.ConfigurableComponent;
import org.somox.core.SoMoXCore;

/**
 * The Controller interface. All plug-ins trying to control the SoMoX core have to implement the
 * interface. a SoMoX core listener can register to.
 *
 * @author Benjamin Klatt
 * @author Michael Hauck
 *
 */
public interface SoMoXCoreController extends ConfigurableComponent {

    /** Set the connection to the SoMoX Core */
    public void setSoMoXCore(SoMoXCore somoxCore);
}
