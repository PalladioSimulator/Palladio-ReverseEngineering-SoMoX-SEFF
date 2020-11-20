package org.somox.ui;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

/**
 * Wrapper for the runtime logging mechanism with additional utility methods
 *
 * @author Benjamin Klatt
 *
 */
public class SoMoXUILogger {

    public static void logInfo(final String message) {
        log(IStatus.INFO, IStatus.OK, message, null);
    }

    public static void logError(final Throwable exception) {
        logError("UnexpectedException", exception);
    }

    public static void logError(final String message, final Throwable exception) {
        log(IStatus.ERROR, IStatus.OK, message, exception);
    }

    public static void log(final int severity, final int code, final String message, final Throwable exception) {
        log(createStatus(severity, code, message, exception));
    }

    public static IStatus createStatus(final int severity, final int code, final String message,
            final Throwable exception) {
        return new Status(severity, Activator.PLUGIN_ID, code, message, exception);
    }

    public static void log(final IStatus status) {
        Activator.getDefault().getLog().log(status);
    }
}