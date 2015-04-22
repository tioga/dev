/*
 * Copyright 2014 Harlan Noonkester
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.tiogasolutions.dev.common.exceptions;

import org.tiogasolutions.dev.common.fine.FineMessage;
import org.tiogasolutions.dev.common.fine.FineMessageSet;
import org.tiogasolutions.dev.common.net.HttpStatusCode;

import static org.tiogasolutions.dev.common.net.HttpStatusCode.*;

@SuppressWarnings("unused")
public abstract class ApiException extends FineRuntimeException {

  private final HttpStatusCode httpStatusCode;

  protected ApiException(HttpStatusCode httpStatusCode) {
    super();
    this.httpStatusCode = httpStatusCode;
  }
  protected ApiException(HttpStatusCode httpStatusCode,  String message, String...traits) {
    super(message, traits);
    this.httpStatusCode = httpStatusCode;
  }
  protected ApiException(HttpStatusCode httpStatusCode,  Throwable ex, String...traits) {
    super(ex, traits);
    this.httpStatusCode = httpStatusCode;
  }
  protected ApiException(HttpStatusCode httpStatusCode,  String message, Throwable ex, String...traits) {
    super(message, ex, traits);
    this.httpStatusCode = httpStatusCode;
  }



  protected ApiException(HttpStatusCode httpStatusCode,  FineMessage richMessage) {
    super(richMessage);
    this.httpStatusCode = httpStatusCode;
  }
  protected ApiException(HttpStatusCode httpStatusCode,  FineMessage richMessage, Throwable ex) {
    super(richMessage, ex);
    this.httpStatusCode = httpStatusCode;
  }



  protected ApiException(HttpStatusCode httpStatusCode,  FineMessageSet messageSet) {
    super(messageSet);
    this.httpStatusCode = httpStatusCode;
  }
  protected ApiException(HttpStatusCode httpStatusCode,  FineMessageSet messageSet, Throwable ex) {
    super(messageSet, ex);
    this.httpStatusCode = httpStatusCode;
  }



  public int getStatusCode() {
    return (httpStatusCode == null) ? -1 : httpStatusCode.getCode();
  }

  public HttpStatusCode getHttpStatusCode() {
    return httpStatusCode;
  }


  /////////////////////////////////////
  // This is the 400 series of errors.
  /////////////////////////////////////

  // Status 400
  public static ApiException badRequest() {                                              return new ApiBadRequestException(); }
  public static ApiException badRequest(String message, String...traits) {               return new ApiBadRequestException(message, traits); }
  public static ApiException badRequest(Throwable ex, String...traits) {                 return new ApiBadRequestException(ex, traits); }
  public static ApiException badRequest(String message, Throwable ex, String...traits) { return new ApiBadRequestException(message, ex, traits); }
  public static ApiException badRequest(FineMessage richMessage) {                       return new ApiBadRequestException(richMessage); }
  public static ApiException badRequest(FineMessage richMessage, Throwable ex) {         return new ApiBadRequestException(richMessage, ex); }
  public static ApiException badRequest(FineMessageSet messageSet) {                     return new ApiBadRequestException(messageSet); }
  public static ApiException badRequest(FineMessageSet messageSet, Throwable ex) {       return new ApiBadRequestException(messageSet, ex); }


  // Status 401
  public static ApiException unauthorized() {                                              return new ApiUnauthorizedException(); }
  public static ApiException unauthorized(String message, String...traits) {               return new ApiUnauthorizedException(message, traits); }
  public static ApiException unauthorized(Throwable ex, String...traits) {                 return new ApiUnauthorizedException(ex, traits); }
  public static ApiException unauthorized(String message, Throwable ex, String...traits) { return new ApiUnauthorizedException(message, ex, traits); }
  public static ApiException unauthorized(FineMessage richMessage) {                       return new ApiUnauthorizedException(richMessage); }
  public static ApiException unauthorized(FineMessage richMessage, Throwable ex) {         return new ApiUnauthorizedException(richMessage, ex); }
  public static ApiException unauthorized(FineMessageSet messageSet) {                     return new ApiUnauthorizedException(messageSet); }
  public static ApiException unauthorized(FineMessageSet messageSet, Throwable ex) {       return new ApiUnauthorizedException(messageSet, ex); }

  // Status 402 - payment required

  // Status 403
  public static ApiException forbidden() {                                              return new ApiForbiddenException(); }
  public static ApiException forbidden(String message, String...traits) {               return new ApiForbiddenException(message, traits); }
  public static ApiException forbidden(Throwable ex, String...traits) {                 return new ApiForbiddenException(ex, traits); }
  public static ApiException forbidden(String message, Throwable ex, String...traits) { return new ApiForbiddenException(message, ex, traits); }
  public static ApiException forbidden(FineMessage richMessage) {                       return new ApiForbiddenException(richMessage); }
  public static ApiException forbidden(FineMessage richMessage, Throwable ex) {         return new ApiForbiddenException(richMessage, ex); }
  public static ApiException forbidden(FineMessageSet messageSet) {                     return new ApiForbiddenException(messageSet); }
  public static ApiException forbidden(FineMessageSet messageSet, Throwable ex) {       return new ApiForbiddenException(messageSet, ex); }


  // Status 404
  public static ApiException notFound() {                                              return new ApiNotFoundException(); }
  public static ApiException notFound(String message, String...traits) {               return new ApiNotFoundException(message, traits); }
  public static ApiException notFound(Throwable ex, String...traits) {                 return new ApiNotFoundException(ex, traits); }
  public static ApiException notFound(String message, Throwable ex, String...traits) { return new ApiNotFoundException(message, ex, traits); }
  public static ApiException notFound(FineMessage richMessage) {                       return new ApiNotFoundException(richMessage); }
  public static ApiException notFound(FineMessage richMessage, Throwable ex) {         return new ApiNotFoundException(richMessage, ex); }
  public static ApiException notFound(FineMessageSet messageSet) {                     return new ApiNotFoundException(messageSet); }
  public static ApiException notFound(FineMessageSet messageSet, Throwable ex) {       return new ApiNotFoundException(messageSet, ex); }

  // Status 405 - method not allowed
  // Status 406 - not acceptable
  // Status 407 - proxy authentication required
  // Status 408 - request timeout

  // Status 409
  public static ApiException conflict() {                                              return new ApiConflictException(); }
  public static ApiException conflict(String message, String...traits) {               return new ApiConflictException(message, traits); }
  public static ApiException conflict(Throwable ex, String...traits) {                 return new ApiConflictException(ex, traits); }
  public static ApiException conflict(String message, Throwable ex, String...traits) { return new ApiConflictException(message, ex, traits); }
  public static ApiException conflict(FineMessage richMessage) {                       return new ApiConflictException(richMessage); }
  public static ApiException conflict(FineMessage richMessage, Throwable ex) {         return new ApiConflictException(richMessage, ex); }
  public static ApiException conflict(FineMessageSet messageSet) {                     return new ApiConflictException(messageSet); }
  public static ApiException conflict(FineMessageSet messageSet, Throwable ex) {       return new ApiConflictException(messageSet, ex); }

  // Status 410 - gone
  // Status 411 - length required

  // Status 412
  public static ApiException preconditionFailed() {                                              return new ApiPreconditionFailedException(); }
  public static ApiException preconditionFailed(String message, String...traits) {               return new ApiPreconditionFailedException(message, traits); }
  public static ApiException preconditionFailed(Throwable ex, String...traits) {                 return new ApiPreconditionFailedException(ex, traits); }
  public static ApiException preconditionFailed(String message, Throwable ex, String...traits) { return new ApiPreconditionFailedException(message, ex, traits); }
  public static ApiException preconditionFailed(FineMessage richMessage) {                       return new ApiPreconditionFailedException(richMessage); }
  public static ApiException preconditionFailed(FineMessage richMessage, Throwable ex) {         return new ApiPreconditionFailedException(richMessage, ex); }
  public static ApiException preconditionFailed(FineMessageSet messageSet) {                     return new ApiPreconditionFailedException(messageSet); }
  public static ApiException preconditionFailed(FineMessageSet messageSet, Throwable ex) {       return new ApiPreconditionFailedException(messageSet, ex); }

  // Status 413 - Request Entity Too Large
  // Status 414 - Request-URI Too Long
  // Status 415 - Unsupported Media Type
  // Status 416 - Requested Range Not Satisfiable
  // Status 417 - Expectation Failed

  // Status 418
  public static ApiException imATeapot() {                                              return new ApiImATeapotException(); }
  public static ApiException imATeapot(String message, String...traits) {               return new ApiImATeapotException(message, traits); }
  public static ApiException imATeapot(Throwable ex, String...traits) {                 return new ApiImATeapotException(ex, traits); }
  public static ApiException imATeapot(String message, Throwable ex, String...traits) { return new ApiImATeapotException(message, ex, traits); }
  public static ApiException imATeapot(FineMessage richMessage) {                       return new ApiImATeapotException(richMessage); }
  public static ApiException imATeapot(FineMessage richMessage, Throwable ex) {         return new ApiImATeapotException(richMessage, ex); }
  public static ApiException imATeapot(FineMessageSet messageSet) {                     return new ApiImATeapotException(messageSet); }
  public static ApiException imATeapot(FineMessageSet messageSet, Throwable ex) {       return new ApiImATeapotException(messageSet, ex); }

  // Status 419 to 4999

  /////////////////////////////////////
  // This is the 500 series of errors.
  /////////////////////////////////////

  // Status 500
  public static ApiException internalServerError() {                                              return new ApiInternalServerErrorException(); }
  public static ApiException internalServerError(String message, String...traits) {               return new ApiInternalServerErrorException(message, traits); }
  public static ApiException internalServerError(Throwable ex, String...traits) {                 return new ApiInternalServerErrorException(ex, traits); }
  public static ApiException internalServerError(String message, Throwable ex, String...traits) { return new ApiInternalServerErrorException(message, ex, traits); }
  public static ApiException internalServerError(FineMessage richMessage) {                       return new ApiInternalServerErrorException(richMessage); }
  public static ApiException internalServerError(FineMessage richMessage, Throwable ex) {         return new ApiInternalServerErrorException(richMessage, ex); }
  public static ApiException internalServerError(FineMessageSet messageSet) {                     return new ApiInternalServerErrorException(messageSet); }
  public static ApiException internalServerError(FineMessageSet messageSet, Throwable ex) {       return new ApiInternalServerErrorException(messageSet, ex); }


  // Status 501
  public static ApiException notImplemented() {                                              return new ApiNotImplementedException(); }
  public static ApiException notImplemented(String message, String...traits) {               return new ApiNotImplementedException(message, traits); }
  public static ApiException notImplemented(Throwable ex, String...traits) {                 return new ApiNotImplementedException(ex, traits); }
  public static ApiException notImplemented(String message, Throwable ex, String...traits) { return new ApiNotImplementedException(message, ex, traits); }
  public static ApiException notImplemented(FineMessage richMessage) {                       return new ApiNotImplementedException(richMessage); }
  public static ApiException notImplemented(FineMessage richMessage, Throwable ex) {         return new ApiNotImplementedException(richMessage, ex); }
  public static ApiException notImplemented(FineMessageSet messageSet) {                     return new ApiNotImplementedException(messageSet); }
  public static ApiException notImplemented(FineMessageSet messageSet, Throwable ex) {       return new ApiNotImplementedException(messageSet, ex); }

  // Status 502 - bad gateway

  // Status 503
  public static ApiException serviceUnavailable() {                                              return new ApiServiceUnavailableException(); }
  public static ApiException serviceUnavailable(String message, String...traits) {               return new ApiServiceUnavailableException(message, traits); }
  public static ApiException serviceUnavailable(Throwable ex, String...traits) {                 return new ApiServiceUnavailableException(ex, traits); }
  public static ApiException serviceUnavailable(String message, Throwable ex, String...traits) { return new ApiServiceUnavailableException(message, ex, traits); }
  public static ApiException serviceUnavailable(FineMessage richMessage) {                       return new ApiServiceUnavailableException(richMessage); }
  public static ApiException serviceUnavailable(FineMessage richMessage, Throwable ex) {         return new ApiServiceUnavailableException(richMessage, ex); }
  public static ApiException serviceUnavailable(FineMessageSet messageSet) {                     return new ApiServiceUnavailableException(messageSet); }
  public static ApiException serviceUnavailable(FineMessageSet messageSet, Throwable ex) {       return new ApiServiceUnavailableException(messageSet, ex); }

  // Status 504 to 5999

  public static ApiException fromCode(int code) {
    return fromCode(toHttpStatusCode(code));
  }
  public static ApiException fromCode(HttpStatusCode httpStatusCode) {
    switch (httpStatusCode) {
      // The 400 series
      case BAD_REQUEST: return badRequest();
      case UNAUTHORIZED: return unauthorized();
      case FORBIDDEN: return forbidden();
      case NOT_FOUND: return notFound();
      case CONFLICT: return conflict();
      case PRECONDITION_FAILED: return preconditionFailed();
      case IM_A_TEAPOT: return imATeapot();
      // The 500 series
      case INTERNAL_SERVER_ERROR: return internalServerError();
      case NOT_IMPLEMENTED: return notImplemented();
      case SERVICE_UNAVAILABLE: return serviceUnavailable();
      // The 100, 200 & 300 series
      default: return new ApiException(httpStatusCode) {};
    }
  }
  public static ApiException fromCode(int code, String message, String...traits) {
    return fromCode(toHttpStatusCode(code), message, traits);
  }
  public static ApiException fromCode(HttpStatusCode httpStatusCode, String message, String...traits) {
    switch (httpStatusCode) {
      // The 400 series
      case BAD_REQUEST: return badRequest(message, traits);
      case UNAUTHORIZED: return unauthorized(message, traits);
      case FORBIDDEN: return forbidden(message, traits);
      case NOT_FOUND: return notFound(message, traits);
      case CONFLICT: return conflict(message, traits);
      case PRECONDITION_FAILED: return preconditionFailed(message, traits);
      case IM_A_TEAPOT: return imATeapot(message, traits);
      // The 500 series
      case INTERNAL_SERVER_ERROR: return internalServerError(message, traits);
      case NOT_IMPLEMENTED: return notImplemented(message, traits);
      case SERVICE_UNAVAILABLE: return serviceUnavailable(message, traits);
      // The 100, 200 & 300 series
      default: return new ApiException(httpStatusCode, message, traits) {};
    }
  }
  public static ApiException fromCode(int code, Throwable ex, String...traits) {
    return fromCode(toHttpStatusCode(code), ex, traits);
  }
  public static ApiException fromCode(HttpStatusCode httpStatusCode, Throwable ex, String...traits) {
    switch (httpStatusCode) {
      // The 400 series
      case BAD_REQUEST: return badRequest(ex, traits);
      case UNAUTHORIZED: return unauthorized(ex, traits);
      case FORBIDDEN: return forbidden(ex, traits);
      case NOT_FOUND: return notFound(ex, traits);
      case CONFLICT: return conflict(ex, traits);
      case PRECONDITION_FAILED: return preconditionFailed(ex, traits);
      case IM_A_TEAPOT: return imATeapot(ex, traits);
      // The 500 series
      case INTERNAL_SERVER_ERROR: return internalServerError(ex, traits);
      case NOT_IMPLEMENTED: return notImplemented(ex, traits);
      case SERVICE_UNAVAILABLE: return serviceUnavailable(ex, traits);
      // The 100, 200 & 300 series
      default: return new ApiException(httpStatusCode, ex, traits) {};
    }
  }
  public static ApiException fromCode(int code, String message, Throwable ex, String...traits) {
    return fromCode(toHttpStatusCode(code), message, ex, traits);
  }
  public static ApiException fromCode(HttpStatusCode httpStatusCode, String message, Throwable ex, String...traits) {
    switch (httpStatusCode) {
      // The 400 series
      case BAD_REQUEST: return badRequest(message, ex, traits);
      case UNAUTHORIZED: return unauthorized(message, ex, traits);
      case FORBIDDEN: return forbidden(message, ex, traits);
      case NOT_FOUND: return notFound(message, ex, traits);
      case CONFLICT: return conflict(message, ex, traits);
      case PRECONDITION_FAILED: return preconditionFailed(message, ex, traits);
      case IM_A_TEAPOT: return imATeapot(message, ex, traits);
      // The 500 series
      case INTERNAL_SERVER_ERROR: return internalServerError(message, ex, traits);
      case NOT_IMPLEMENTED: return notImplemented(message, ex, traits);
      case SERVICE_UNAVAILABLE: return serviceUnavailable(message, ex, traits);
      // The 100, 200 & 300 series
      default: return new ApiException(httpStatusCode, message, ex, traits) {};
    }
  }
  public static ApiException fromCode(int code, FineMessage richMessage) {
    return fromCode(toHttpStatusCode(code), richMessage);
  }
  public static ApiException fromCode(HttpStatusCode httpStatusCode, FineMessage richMessage) {
    switch (httpStatusCode) {
      // The 400 series
      case BAD_REQUEST: return badRequest(richMessage);
      case UNAUTHORIZED: return unauthorized(richMessage);
      case FORBIDDEN: return forbidden(richMessage);
      case NOT_FOUND: return notFound(richMessage);
      case CONFLICT: return conflict(richMessage);
      case PRECONDITION_FAILED: return preconditionFailed(richMessage);
      case IM_A_TEAPOT: return imATeapot(richMessage);
      // The 500 series
      case INTERNAL_SERVER_ERROR: return internalServerError(richMessage);
      case NOT_IMPLEMENTED: return notImplemented(richMessage);
      case SERVICE_UNAVAILABLE: return serviceUnavailable(richMessage);
      // The 100, 200 & 300 series
      default: return new ApiException(httpStatusCode, richMessage) {};
    }
  }
  public static ApiException fromCode(int code, FineMessage richMessage, Throwable ex) {
    return fromCode(toHttpStatusCode(code), richMessage, ex);
  }
  public static ApiException fromCode(HttpStatusCode httpStatusCode, FineMessage richMessage, Throwable ex) {
    switch (httpStatusCode) {
      // The 400 series
      case BAD_REQUEST: return badRequest(richMessage, ex);
      case UNAUTHORIZED: return unauthorized(richMessage, ex);
      case FORBIDDEN: return forbidden(richMessage, ex);
      case NOT_FOUND: return notFound(richMessage, ex);
      case CONFLICT: return conflict(richMessage, ex);
      case PRECONDITION_FAILED: return preconditionFailed(richMessage, ex);
      case IM_A_TEAPOT: return imATeapot(richMessage, ex);
      // The 500 series
      case INTERNAL_SERVER_ERROR: return internalServerError(richMessage, ex);
      case NOT_IMPLEMENTED: return notImplemented(richMessage, ex);
      case SERVICE_UNAVAILABLE: return serviceUnavailable(richMessage, ex);
      // The 100, 200 & 300 series
      default: return new ApiException(httpStatusCode, richMessage, ex) {};
    }
  }
  public static ApiException fromCode(int code, FineMessageSet messageSet) {
    return fromCode(toHttpStatusCode(code), messageSet);
  }
  public static ApiException fromCode(HttpStatusCode httpStatusCode, FineMessageSet messageSet) {
    switch (httpStatusCode) {
      // The 400 series
      case BAD_REQUEST: return badRequest(messageSet);
      case UNAUTHORIZED: return unauthorized(messageSet);
      case FORBIDDEN: return forbidden(messageSet);
      case NOT_FOUND: return notFound(messageSet);
      case CONFLICT: return conflict(messageSet);
      case PRECONDITION_FAILED: return preconditionFailed(messageSet);
      case IM_A_TEAPOT: return imATeapot(messageSet);
      // The 500 series
      case INTERNAL_SERVER_ERROR: return internalServerError(messageSet);
      case NOT_IMPLEMENTED: return notImplemented();
      case SERVICE_UNAVAILABLE: return serviceUnavailable(messageSet);
      // The 100, 200 & 300 series
      default: return new ApiException(httpStatusCode, messageSet) {};
    }
  }
  public static ApiException fromCode(int code, FineMessageSet messageSet, Throwable ex) {
    return fromCode(toHttpStatusCode(code), messageSet, ex);
  }
  public static ApiException fromCode(HttpStatusCode httpStatusCode, FineMessageSet messageSet, Throwable ex) {
    switch (httpStatusCode) {
      // The 400 series
      case BAD_REQUEST: return badRequest(messageSet, ex);
      case UNAUTHORIZED: return unauthorized(messageSet, ex);
      case FORBIDDEN: return forbidden(messageSet, ex);
      case NOT_FOUND: return notFound(messageSet, ex);
      case CONFLICT: return conflict(messageSet, ex);
      case PRECONDITION_FAILED: return preconditionFailed(messageSet, ex);
      case IM_A_TEAPOT: return imATeapot(messageSet, ex);
      // The 500 series
      case INTERNAL_SERVER_ERROR: return internalServerError(messageSet, ex);
      case NOT_IMPLEMENTED: return notImplemented(messageSet, ex);
      case SERVICE_UNAVAILABLE: return serviceUnavailable(messageSet, ex);
      // The 100, 200 & 300 series
      default: return new ApiException(httpStatusCode, messageSet, ex) {};
    }
  }
  private static HttpStatusCode toHttpStatusCode(int code) {
    HttpStatusCode httpStatusCode = HttpStatusCode.findByCode(code);
    if (UNDEFINED == httpStatusCode) {
      String msg = String.format("The value %s is not a valid HTTP status code.", code);
      throw new IllegalArgumentException(msg);
    }
    return httpStatusCode;
  }
}
