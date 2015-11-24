package org.legomanager.api.exceptions;

/**
 * @author Vaclav Muzikar <vaclav@muzikari.cz>
 */
public class ModelKitConverterException extends ServiceException {
    public ModelKitConverterException() {
        super();
    }

    public ModelKitConverterException(String message) {
        super(message);
    }

    public ModelKitConverterException(String message, Throwable cause) {
        super(message, cause);
    }

    public ModelKitConverterException(Throwable cause) {
        super(cause);
    }

    protected ModelKitConverterException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
