package org.tiogasolutions.dev.common.exceptions;

import org.tiogasolutions.dev.common.fine.FineMessage;
import org.tiogasolutions.dev.common.fine.FineMessageSet;

import static org.tiogasolutions.dev.common.net.HttpStatusCode.INTERNAL_SERVER_ERROR;

public class ApiInternalServerErrorException extends Api5xxException {

  /*default*/ ApiInternalServerErrorException() {
    super(INTERNAL_SERVER_ERROR);
  }

  /*default*/ ApiInternalServerErrorException(String message, String... traits) {
    super(INTERNAL_SERVER_ERROR, message, traits);
  }

  /*default*/ ApiInternalServerErrorException(Throwable ex, String... traits) {
    super(INTERNAL_SERVER_ERROR, ex, traits);
  }

  /*default*/ ApiInternalServerErrorException(String message, Throwable ex, String... traits) {
    super(INTERNAL_SERVER_ERROR, message, ex, traits);
  }

  /*default*/ ApiInternalServerErrorException(FineMessage richMessage) {
    super(INTERNAL_SERVER_ERROR, richMessage);
  }

  /*default*/ ApiInternalServerErrorException(FineMessage richMessage, Throwable ex) {
    super(INTERNAL_SERVER_ERROR, richMessage, ex);
  }

  /*default*/ ApiInternalServerErrorException(FineMessageSet messageSet) {
    super(INTERNAL_SERVER_ERROR, messageSet);
  }

  /*default*/ ApiInternalServerErrorException(FineMessageSet messageSet, Throwable ex) {
    super(INTERNAL_SERVER_ERROR, messageSet, ex);
  }
}
