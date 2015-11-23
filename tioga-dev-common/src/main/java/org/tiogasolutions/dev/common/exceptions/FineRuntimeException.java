package org.tiogasolutions.dev.common.exceptions;

import org.tiogasolutions.dev.common.StringUtils;
import org.tiogasolutions.dev.common.fine.*;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;

public class FineRuntimeException extends RuntimeException implements FineMessageAccessing {

  private static final long serialVersionUID = 1L;

  private final FineMessageSet messageSet;

  public FineRuntimeException() {
    this(true, toMsgSet(null, null, null, (String[])null), null);
  }


  public FineRuntimeException(String message, TraitMap traits) {
    this(true, toMsgSet(message, null, null, traits), null);
  }
  public FineRuntimeException(String message, Map<?,?> traits) {
    this(true, toMsgSet(message, null, null, traits), null);
  }
  public FineRuntimeException(String message, Collection<String> traits) {
    this(true, toMsgSet(message, null, null, traits), null);
  }
  public FineRuntimeException(String message, String...traits) {
    this(true, toMsgSet(message, null, null, traits), null);
  }


  public FineRuntimeException(Throwable ex, TraitMap traits) {
    this(true, toMsgSet(null, null, ex, traits), ex);
  }
  public FineRuntimeException(Throwable ex, Map<?,?> traits) {
    this(true, toMsgSet(null, null, ex, traits), ex);
  }
  public FineRuntimeException(Throwable ex, Collection<String> traits) {
    this(true, toMsgSet(null, null, ex, traits), ex);
  }
  public FineRuntimeException(Throwable ex, String...traits) {
    this(true, toMsgSet(null, null, ex, traits), ex);
  }


  public FineRuntimeException(String message, Throwable ex, TraitMap traits) {
    this(true, toMsgSet(message, null, ex, traits), ex);
  }
  public FineRuntimeException(String message, Throwable ex, Map<?,?> traits) {
    this(true, toMsgSet(message, null, ex, traits), ex);
  }
  public FineRuntimeException(String message, Throwable ex, Collection<String> traits) {
    this(true, toMsgSet(message, null, ex, traits), ex);
  }
  public FineRuntimeException(String message, Throwable ex, String...traits) {
    this(true, toMsgSet(message, null, ex, traits), ex);
  }


  public FineRuntimeException(FineMessage fineMessage) {
    this(true, fineMessage.toSet(), null);
  }


  public FineRuntimeException(FineMessage fineMessage, Throwable ex, TraitMap traits) {
    this(true, toMsgSet(null, fineMessage.toSet(), ex, traits), ex);
  }
  public FineRuntimeException(FineMessage fineMessage, Throwable ex, Map<?,?> traits) {
    this(true, toMsgSet(null, fineMessage.toSet(), ex, traits), ex);
  }
  public FineRuntimeException(FineMessage fineMessage, Throwable ex, Collection<String> traits) {
    this(true, toMsgSet(null, fineMessage.toSet(), ex, traits), ex);
  }
  public FineRuntimeException(FineMessage fineMessage, Throwable ex, String...traits) {
    this(true, toMsgSet(null, fineMessage.toSet(), ex, traits), ex);
  }


  public FineRuntimeException(FineMessageSet messageSet) {
    this(true, messageSet, null);
  }


  public FineRuntimeException(FineMessageSet messageSet, Throwable ex, TraitMap traits) {
    this(true, toMsgSet(null, messageSet, ex, traits), ex);
  }
  public FineRuntimeException(FineMessageSet messageSet, Throwable ex, Map<?,?> traits) {
    this(true, toMsgSet(null, messageSet, ex, traits), ex);
  }
  public FineRuntimeException(FineMessageSet messageSet, Throwable ex, Collection<String> traits) {
    this(true, toMsgSet(null, messageSet, ex, traits), ex);
  }
  public FineRuntimeException(FineMessageSet messageSet, Throwable ex, String...traits) {
    this(true, toMsgSet(null, messageSet, ex, traits), ex);
  }


  // This is our uber-secret constructor that should ensure
  // consistent construction given all the various constructors.
  private FineRuntimeException(boolean discriminator, FineMessageSet messageSet, Throwable ex) {
    super(messageSet.isEmpty() ? null : messageSet.toString("; "), ex);
    this.messageSet = messageSet;
  }

  @Override
  public FineMessageSet getMessageSet() {
    return messageSet;
  }

  public String getSummary() {
    return messageSet.toString("; ");
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof FineRuntimeException)) return false;

    FineRuntimeException that = (FineRuntimeException) o;

    return messageSet.equals(that.messageSet);
  }

  @Override
  public int hashCode() {
    int result = getClass().getName().hashCode();
    result = 31 * result + messageSet.hashCode();
    return result;
  }

  private static FineMessageSet toMsgSet(String message, FineMessageSet fineMessageSet, Throwable ex, String...traits) {
    return toMsgSet(message, fineMessageSet, ex, new TraitMap(traits));
  }
  private static FineMessageSet toMsgSet(String message, FineMessageSet fineMessageSet, Throwable ex, Collection<String> traits) {
    return toMsgSet(message, fineMessageSet, ex, new TraitMap(traits));
  }
  private static FineMessageSet toMsgSet(String message, FineMessageSet fineMessageSet, Throwable ex, Map<?,?> traits) {
    return toMsgSet(message, fineMessageSet, ex, new TraitMap(traits));
  }
  private static FineMessageSet toMsgSet(String message, FineMessageSet fineMessageSet, Throwable ex, TraitMap traitMap) {

    // Note - it's OK for traitMap to be null, but not OK to add them multiple times.

    FineMessageSetBuilder builder = new FineMessageSetBuilder();

    // If we have a message, it should be first in the set.
    if (StringUtils.isNotBlank(message)) {
      builder.withTraits(message, traitMap);

      // Null traitMap to ensure it will not be added again below
      traitMap = null;
    }

    // If we have a set - it goes before the exception.
    if (fineMessageSet != null) {
      builder.withSetAndAdditionalTraits(fineMessageSet, traitMap);

      // Null traitMap to ensure it will not be added again below
      traitMap = null;
    }

    if (ex != null) {
      if (ex instanceof FineMessageAccessing) {
        FineMessageAccessing accessing = (FineMessageAccessing)ex;
        builder.withSetAndAdditionalTraits(accessing.getMessageSet(), traitMap);
      } else {
        builder.withTraits(ex.getMessage(), traitMap);
      }
    }

    return builder.build();
  }
}
