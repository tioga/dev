package org.tiogasolutions.dev.common.exceptions;

import org.tiogasolutions.dev.common.fine.FineMessage;
import org.tiogasolutions.dev.common.fine.FineMessageSet;

import static org.tiogasolutions.dev.common.net.HttpStatusCode.UNAUTHORIZED;

public class ApiUnauthorizedException extends Api4xxException {

    private static final long serialVersionUID = 1L;

    public ApiUnauthorizedException() {
        super(UNAUTHORIZED);
    }

    public ApiUnauthorizedException(String message) {
        super(UNAUTHORIZED, message);
    }

    public ApiUnauthorizedException(String message, String... traits) {
        super(UNAUTHORIZED, message, traits);
    }

    public ApiUnauthorizedException(Throwable ex, String... traits) {
        super(UNAUTHORIZED, ex, traits);
    }

    public ApiUnauthorizedException(String message, Throwable ex, String... traits) {
        super(UNAUTHORIZED, message, ex, traits);
    }

    public ApiUnauthorizedException(FineMessage richMessage) {
        super(UNAUTHORIZED, richMessage);
    }

    public ApiUnauthorizedException(FineMessage richMessage, Throwable ex) {
        super(UNAUTHORIZED, richMessage, ex);
    }

    public ApiUnauthorizedException(FineMessageSet messageSet) {
        super(UNAUTHORIZED, messageSet);
    }

    public ApiUnauthorizedException(FineMessageSet messageSet, Throwable ex) {
        super(UNAUTHORIZED, messageSet, ex);
    }
}
