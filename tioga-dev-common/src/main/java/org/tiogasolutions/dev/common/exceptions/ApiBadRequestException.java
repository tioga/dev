package org.tiogasolutions.dev.common.exceptions;

import org.tiogasolutions.dev.common.fine.FineMessage;
import org.tiogasolutions.dev.common.fine.FineMessageSet;

import static org.tiogasolutions.dev.common.net.HttpStatusCode.BAD_REQUEST;

public class ApiBadRequestException extends Api4xxException {

  private static final long serialVersionUID = 1L;

  public ApiBadRequestException() {
    super(BAD_REQUEST);
  }

  public ApiBadRequestException(String message) {
    super(BAD_REQUEST, message);
  }

  public ApiBadRequestException(String message, String... traits) {
    super(BAD_REQUEST, message, traits);
  }

  public ApiBadRequestException(Throwable ex, String... traits) {
    super(BAD_REQUEST, ex, traits);
  }

  public ApiBadRequestException(String message, Throwable ex, String... traits) {
    super(BAD_REQUEST, message, ex, traits);
  }

  public ApiBadRequestException(FineMessage richMessage) {
    super(BAD_REQUEST, richMessage);
  }

  public ApiBadRequestException(FineMessage richMessage, Throwable ex) {
    super(BAD_REQUEST, richMessage, ex);
  }

  public ApiBadRequestException(FineMessageSet messageSet) {
    super(BAD_REQUEST, messageSet);
  }

  public ApiBadRequestException(FineMessageSet messageSet, Throwable ex) {
    super(BAD_REQUEST, messageSet, ex);
  }
}
