package org.tiogasolutions.dev.common.exceptions;

import org.tiogasolutions.dev.common.fine.FineMessage;
import org.tiogasolutions.dev.common.fine.FineMessageSet;

import static org.tiogasolutions.dev.common.net.HttpStatusCode.NOT_FOUND;

public class ApiNotFoundException extends Api4xxException {

  private static final long serialVersionUID = 1L;

  /*default*/ ApiNotFoundException() {
    super(NOT_FOUND);
  }

  /*default*/ ApiNotFoundException(String message, String... traits) {
    super(NOT_FOUND, message, traits);
  }

  /*default*/ ApiNotFoundException(Throwable ex, String... traits) {
    super(NOT_FOUND, ex, traits);
  }

  /*default*/ ApiNotFoundException(String message, Throwable ex, String... traits) {
    super(NOT_FOUND, message, ex, traits);
  }

  /*default*/ ApiNotFoundException(FineMessage richMessage) {
    super(NOT_FOUND, richMessage);
  }

  /*default*/ ApiNotFoundException(FineMessage richMessage, Throwable ex) {
    super(NOT_FOUND, richMessage, ex);
  }

  /*default*/ ApiNotFoundException(FineMessageSet messageSet) {
    super(NOT_FOUND, messageSet);
  }

  /*default*/ ApiNotFoundException(FineMessageSet messageSet, Throwable ex) {
    super(NOT_FOUND, messageSet, ex);
  }
}
