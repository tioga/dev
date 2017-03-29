package org.tiogasolutions.dev.common.exceptions;

import org.tiogasolutions.dev.common.fine.FineMessage;
import org.tiogasolutions.dev.common.fine.FineMessageSet;
import org.tiogasolutions.dev.common.net.HttpStatusCode;

public class Api5xxException extends ApiException {

  private static final long serialVersionUID = 1L;

  public Api5xxException(HttpStatusCode httpStatusCode) {
    super(assert500(httpStatusCode));
  }

  public Api5xxException(HttpStatusCode httpStatusCode, String message, String... traits) {
    super(assert500(httpStatusCode), message, traits);
  }

  public Api5xxException(HttpStatusCode httpStatusCode, Throwable ex, String... traits) {
    super(assert500(httpStatusCode), ex, traits);
  }

  public Api5xxException(HttpStatusCode httpStatusCode, String message, Throwable ex, String... traits) {
    super(assert500(httpStatusCode), message, ex, traits);
  }

  public Api5xxException(HttpStatusCode httpStatusCode, FineMessage richMessage) {
    super(assert500(httpStatusCode), richMessage);
  }

  public Api5xxException(HttpStatusCode httpStatusCode, FineMessage richMessage, Throwable ex) {
    super(assert500(httpStatusCode), richMessage, ex);
  }

  public Api5xxException(HttpStatusCode httpStatusCode, FineMessageSet messageSet) {
    super(assert500(httpStatusCode), messageSet);
  }

  public Api5xxException(HttpStatusCode httpStatusCode, FineMessageSet messageSet, Throwable ex) {
    super(assert500(httpStatusCode), messageSet, ex);
  }

  private static HttpStatusCode assert500(HttpStatusCode httpStatusCode) {
    int code = httpStatusCode.getCode();
    if (code < 500 || code > 599) {
      String msg = String.format("The HTTP status code (%s) must between 500 & 599 inclusive.", code);
      throw new IllegalArgumentException(msg);
    }
    return httpStatusCode;
  }
}
