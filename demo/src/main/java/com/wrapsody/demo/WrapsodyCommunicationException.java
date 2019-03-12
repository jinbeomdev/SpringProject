package com.wrapsody.demo;

public class WrapsodyCommunicationException extends Exception {
    public WrapsodyCommunicationException() {
        super();
    }

    public WrapsodyCommunicationException(String message) {
        super(message);
    }

    public WrapsodyCommunicationException(String message, Throwable cause) {
        super(message, cause);
    }

    public WrapsodyCommunicationException(Throwable cause) {
        super(cause);
    }

    protected WrapsodyCommunicationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

