package org.tiogasolutions.dev.common.exceptions;

import org.tiogasolutions.dev.common.fine.FineMessage;
import org.tiogasolutions.dev.common.fine.FineMessageSet;

import static org.tiogasolutions.dev.common.net.HttpStatusCode.INTERNAL_SERVER_ERROR;

public class ApiServiceUnavailableException extends Api5xxException {

  private static final long serialVersionUID = 1L;

  /*default*/ ApiServiceUnavailableException() {
    super(INTERNAL_SERVER_ERROR);
  }

  /*default*/ ApiServiceUnavailableException(String message, String... traits) {
    super(INTERNAL_SERVER_ERROR, message, traits);
  }

  /*default*/ ApiServiceUnavailableException(Throwable ex, String... traits) {
    super(INTERNAL_SERVER_ERROR, ex, traits);
  }

  /*default*/ ApiServiceUnavailableException(String message, Throwable ex, String... traits) {
    super(INTERNAL_SERVER_ERROR, message, ex, traits);
  }

  /*default*/ ApiServiceUnavailableException(FineMessage richMessage) {
    super(INTERNAL_SERVER_ERROR, richMessage);
  }

  /*default*/ ApiServiceUnavailableException(FineMessage richMessage, Throwable ex) {
    super(INTERNAL_SERVER_ERROR, richMessage, ex);
  }

  /*default*/ ApiServiceUnavailableException(FineMessageSet messageSet) {
    super(INTERNAL_SERVER_ERROR, messageSet);
  }

  /*default*/ ApiServiceUnavailableException(FineMessageSet messageSet, Throwable ex) {
    super(INTERNAL_SERVER_ERROR, messageSet, ex);
  }
}
