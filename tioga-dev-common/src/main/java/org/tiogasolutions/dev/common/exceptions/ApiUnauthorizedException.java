package org.tiogasolutions.dev.common.exceptions;

import org.tiogasolutions.dev.common.fine.FineMessage;
import org.tiogasolutions.dev.common.fine.FineMessageSet;

import static org.tiogasolutions.dev.common.net.HttpStatusCode.UNAUTHORIZED;

public class ApiUnauthorizedException extends Api4xxException {

  private static final long serialVersionUID = 1L;

  /*default*/ ApiUnauthorizedException() {
    super(UNAUTHORIZED);
  }

  /*default*/ ApiUnauthorizedException(String message, String... traits) {
    super(UNAUTHORIZED, message, traits);
  }

  /*default*/ ApiUnauthorizedException(Throwable ex, String... traits) {
    super(UNAUTHORIZED, ex, traits);
  }

  /*default*/ ApiUnauthorizedException(String message, Throwable ex, String... traits) {
    super(UNAUTHORIZED, message, ex, traits);
  }

  /*default*/ ApiUnauthorizedException(FineMessage richMessage) {
    super(UNAUTHORIZED, richMessage);
  }

  /*default*/ ApiUnauthorizedException(FineMessage richMessage, Throwable ex) {
    super(UNAUTHORIZED, richMessage, ex);
  }

  /*default*/ ApiUnauthorizedException(FineMessageSet messageSet) {
    super(UNAUTHORIZED, messageSet);
  }

  /*default*/ ApiUnauthorizedException(FineMessageSet messageSet, Throwable ex) {
    super(UNAUTHORIZED, messageSet, ex);
  }
}
