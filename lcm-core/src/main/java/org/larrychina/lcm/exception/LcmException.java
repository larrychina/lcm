package org.larrychina.lcm.exception;

/**
 * Created by larrychina on 2018/5/4.
 */
public class LcmException extends RuntimeException {

    public LcmException(String message) {
        super(message);
    }

    public LcmException(String message, Throwable cause) {
        super(message, cause);
    }

    public LcmException(Throwable cause) {
        super(cause);
    }

    public LcmException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
