package org.tiogasolutions.dev.common.exceptions;

import org.tiogasolutions.dev.common.fine.FineMessage;
import org.tiogasolutions.dev.common.fine.FineMessageSet;

import static org.tiogasolutions.dev.common.net.HttpStatusCode.INTERNAL_SERVER_ERROR;
import static org.tiogasolutions.dev.common.net.HttpStatusCode.SERVICE_UNAVAILABLE;

public class ApiServiceUnavailableException extends Api5xxException {

  private static final long serialVersionUID = 1L;

  /*default*/ ApiServiceUnavailableException() {
    super(SERVICE_UNAVAILABLE);
  }

  /*default*/ ApiServiceUnavailableException(String message, String... traits) {
    super(SERVICE_UNAVAILABLE, message, traits);
  }

  /*default*/ ApiServiceUnavailableException(Throwable ex, String... traits) {
    super(INTERNAL_SERVER_ERROR, ex, traits);
  }

  /*default*/ ApiServiceUnavailableException(String message, Throwable ex, String... traits) {
    super(SERVICE_UNAVAILABLE, message, ex, traits);
  }

  /*default*/ ApiServiceUnavailableException(FineMessage richMessage) {
    super(SERVICE_UNAVAILABLE, richMessage);
  }

  /*default*/ ApiServiceUnavailableException(FineMessage richMessage, Throwable ex) {
    super(SERVICE_UNAVAILABLE, richMessage, ex);
  }

  /*default*/ ApiServiceUnavailableException(FineMessageSet messageSet) {
    super(SERVICE_UNAVAILABLE, messageSet);
  }

  /*default*/ ApiServiceUnavailableException(FineMessageSet messageSet, Throwable ex) {
    super(SERVICE_UNAVAILABLE, messageSet, ex);
  }
}
