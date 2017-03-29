package org.tiogasolutions.dev.common.exceptions;

import org.tiogasolutions.dev.common.fine.FineMessage;
import org.tiogasolutions.dev.common.fine.FineMessageSet;

import static org.tiogasolutions.dev.common.net.HttpStatusCode.INTERNAL_SERVER_ERROR;
import static org.tiogasolutions.dev.common.net.HttpStatusCode.SERVICE_UNAVAILABLE;

public class ApiServiceUnavailableException extends Api5xxException {

    private static final long serialVersionUID = 1L;

    public ApiServiceUnavailableException() {
        super(SERVICE_UNAVAILABLE);
    }

    public ApiServiceUnavailableException(String message) {
        super(SERVICE_UNAVAILABLE, message);
    }

    public ApiServiceUnavailableException(String message, String... traits) {
        super(SERVICE_UNAVAILABLE, message, traits);
    }

    public ApiServiceUnavailableException(Throwable ex, String... traits) {
        super(INTERNAL_SERVER_ERROR, ex, traits);
    }

    public ApiServiceUnavailableException(String message, Throwable ex, String... traits) {
        super(SERVICE_UNAVAILABLE, message, ex, traits);
    }

    public ApiServiceUnavailableException(FineMessage richMessage) {
        super(SERVICE_UNAVAILABLE, richMessage);
    }

    public ApiServiceUnavailableException(FineMessage richMessage, Throwable ex) {
        super(SERVICE_UNAVAILABLE, richMessage, ex);
    }

    public ApiServiceUnavailableException(FineMessageSet messageSet) {
        super(SERVICE_UNAVAILABLE, messageSet);
    }

    public ApiServiceUnavailableException(FineMessageSet messageSet, Throwable ex) {
        super(SERVICE_UNAVAILABLE, messageSet, ex);
    }
}
