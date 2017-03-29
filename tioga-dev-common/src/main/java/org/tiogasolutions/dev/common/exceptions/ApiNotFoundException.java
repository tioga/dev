package org.tiogasolutions.dev.common.exceptions;

import org.tiogasolutions.dev.common.fine.FineMessage;
import org.tiogasolutions.dev.common.fine.FineMessageSet;

import static org.tiogasolutions.dev.common.net.HttpStatusCode.NOT_FOUND;

public class ApiNotFoundException extends Api4xxException {

  private static final long serialVersionUID = 1L;

  public ApiNotFoundException() {
    super(NOT_FOUND);
  }

  public ApiNotFoundException(String message) {
    super(NOT_FOUND, message);
  }

  public ApiNotFoundException(String message, String... traits) {
    super(NOT_FOUND, message, traits);
  }

  public ApiNotFoundException(Throwable ex, String... traits) {
    super(NOT_FOUND, ex, traits);
  }

  public ApiNotFoundException(String message, Throwable ex, String... traits) {
    super(NOT_FOUND, message, ex, traits);
  }

  public ApiNotFoundException(FineMessage richMessage) {
    super(NOT_FOUND, richMessage);
  }

  public ApiNotFoundException(FineMessage richMessage, Throwable ex) {
    super(NOT_FOUND, richMessage, ex);
  }

  public ApiNotFoundException(FineMessageSet messageSet) {
    super(NOT_FOUND, messageSet);
  }

  public ApiNotFoundException(FineMessageSet messageSet, Throwable ex) {
    super(NOT_FOUND, messageSet, ex);
  }
}
