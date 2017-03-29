package org.tiogasolutions.dev.common.exceptions;

import org.tiogasolutions.dev.common.fine.FineMessage;
import org.tiogasolutions.dev.common.fine.FineMessageSet;

import static org.tiogasolutions.dev.common.net.HttpStatusCode.INTERNAL_SERVER_ERROR;

public class ApiInternalServerErrorException extends Api5xxException {

    private static final long serialVersionUID = 1L;

    public ApiInternalServerErrorException() {
        super(INTERNAL_SERVER_ERROR);
    }

    public ApiInternalServerErrorException(String message) {
        super(INTERNAL_SERVER_ERROR, message);
    }

    public ApiInternalServerErrorException(String message, String... traits) {
        super(INTERNAL_SERVER_ERROR, message, traits);
    }

    public ApiInternalServerErrorException(Throwable ex, String... traits) {
        super(INTERNAL_SERVER_ERROR, ex, traits);
    }

    public ApiInternalServerErrorException(String message, Throwable ex, String... traits) {
        super(INTERNAL_SERVER_ERROR, message, ex, traits);
    }

    public ApiInternalServerErrorException(FineMessage richMessage) {
        super(INTERNAL_SERVER_ERROR, richMessage);
    }

    public ApiInternalServerErrorException(FineMessage richMessage, Throwable ex) {
        super(INTERNAL_SERVER_ERROR, richMessage, ex);
    }

    public ApiInternalServerErrorException(FineMessageSet messageSet) {
        super(INTERNAL_SERVER_ERROR, messageSet);
    }

    public ApiInternalServerErrorException(FineMessageSet messageSet, Throwable ex) {
        super(INTERNAL_SERVER_ERROR, messageSet, ex);
    }
}
