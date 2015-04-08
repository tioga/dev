package org.tiogasolutions.dev.common.exceptions;

import org.tiogasolutions.dev.common.BeanUtils;
import org.tiogasolutions.dev.common.fine.FineMessage;
import org.tiogasolutions.dev.common.fine.FineMessageSet;
import org.tiogasolutions.dev.common.fine.FineMessageSetBuilder;
import org.tiogasolutions.dev.common.fine.TraitMap;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/** @noinspection ThrowableInstanceNeverThrown*/
@Test
public class FineRuntimeExceptionTest {

  private static final String userMsgA = "This is my message";
  private static final String[] traitsArrayA = new String[]{"one:1", "color:blue", "UP:Down"};
  private static final String fineMsgIdA = null;
  private static final FineMessage fineMsgA = FineMessage.withAll(userMsgA, fineMsgIdA, traitsArrayA);

  private static final String userMsgB = "This is my second message";
  private static final String[] traitsArrayB = new String[]{"two:2", "blue:color", "dOWn:soft"};
  private static final String fineMsgIdB = "ASDF1234";
  private static final FineMessage fineMsgB = FineMessage.withAll(userMsgB, fineMsgIdB, traitsArrayB);

  private static final String exMsg = "I don't know what you are talking about.";
  private static final IOException ioException = new IOException(exMsg);
  private static final FineMessage fineMsgEx = FineMessage.withTraits(exMsg, traitsArrayA);
  private static final FineMessage fineMsgExNoTraits = FineMessage.withTraits(exMsg);

  private static final String userMsgAB = userMsgA + "; " + userMsgB;
  private static final String userMsgAX = userMsgA + "; " + exMsg;
  private static final String userMsgABX = userMsgA + "; " + userMsgB + "; " + exMsg;

  private static final FineMessageSet fineMsgSet = new FineMessageSetBuilder()
      .withMessage(fineMsgA)
      .withMessage(fineMsgB)
      .build();



  public void create_Default() {
    FineRuntimeException ex = new FineRuntimeException();

    assertEquals(ex.getMessage(), null);
    assertEquals(ex.getSummary(), "FineMessageSetImpl is empty.");

    // There should be no messages in the set.
    assertTrue(ex.getMessageSet().isEmpty(), "Contains: " + ex.getMessageSet().getMessages());

    assertEquals(ex.getCause(), null);
  }



  public void create_Message_TraitsArray() {
    FineRuntimeException ex = new FineRuntimeException(userMsgA, traitsArrayA);
    validate_Message_TraitsArray(ex);

    ex = new FineRuntimeException(userMsgA, Arrays.asList(traitsArrayA));
    validate_Message_TraitsArray(ex);

    ex = new FineRuntimeException(userMsgA, BeanUtils.toMap(traitsArrayA));
    validate_Message_TraitsArray(ex);

    ex = new FineRuntimeException(userMsgA, new TraitMap(traitsArrayA));
    validate_Message_TraitsArray(ex);
  }
  private void validate_Message_TraitsArray(FineRuntimeException ex) {
    assertEquals(ex.getMessage(), userMsgA);
    assertEquals(ex.getSummary(), userMsgA);

    // Expecting only one message from the caller
    List<FineMessage> messages = new ArrayList<>(ex.getMessageSet().getMessages());
    assertEquals(messages.remove(0), fineMsgA);
    assertTrue(messages.isEmpty(), "Contains: " + ex.getMessageSet().getMessages());

    assertEquals(ex.getCause(), null);
  }



  public void create_Throwable_TraitsArray() {
    FineRuntimeException ex = new FineRuntimeException(ioException, traitsArrayA);
    validate_Throwable_TraitsArray(ex, ioException);

    ex = new FineRuntimeException(ioException, Arrays.asList(traitsArrayA));
    validate_Throwable_TraitsArray(ex, ioException);

    ex = new FineRuntimeException(ioException, BeanUtils.toMap(traitsArrayA));
    validate_Throwable_TraitsArray(ex, ioException);

    ex = new FineRuntimeException(ioException, new TraitMap(traitsArrayA));
    validate_Throwable_TraitsArray(ex, ioException);

    FineRuntimeException second = new FineRuntimeException(ex);
    validate_Throwable_TraitsArray(second, ex);
  }
  private void validate_Throwable_TraitsArray(FineRuntimeException ex, Throwable source) {
    assertEquals(ex.getMessage(), exMsg);
    assertEquals(ex.getSummary(), exMsg);

    // Expecting only one message from the exception
    List<FineMessage> messages = new ArrayList<>(ex.getMessageSet().getMessages());
    assertEquals(messages.remove(0), fineMsgEx);
    assertTrue(messages.isEmpty(), "Contains: " + ex.getMessageSet().getMessages());

    assertEquals(ex.getCause(), source);
  }



  public void create_Message_Throwable_TraitsArray() {
    FineRuntimeException ex = new FineRuntimeException(userMsgA, ioException, traitsArrayA);
    validate_Message_Throwable_TraitsArray(ex, ioException);

    ex = new FineRuntimeException(userMsgA, ioException, Arrays.asList(traitsArrayA));
    validate_Message_Throwable_TraitsArray(ex, ioException);

    ex = new FineRuntimeException(userMsgA, ioException, BeanUtils.toMap(traitsArrayA));
    validate_Message_Throwable_TraitsArray(ex, ioException);

    ex = new FineRuntimeException(userMsgA, ioException, new TraitMap(traitsArrayA));
    validate_Message_Throwable_TraitsArray(ex, ioException);

    FineRuntimeException second = new FineRuntimeException(ex);
    validate_Message_Throwable_TraitsArray(second, ex);
  }
  private void validate_Message_Throwable_TraitsArray(FineRuntimeException ex, Throwable source) {
    assertEquals(ex.getMessage(), userMsgAX);
    assertEquals(ex.getSummary(), userMsgAX);

    // Expecting two messages, one form the caller, one from the exception
    List<FineMessage> messages = new ArrayList<>(ex.getMessageSet().getMessages());
    assertEquals(messages.remove(0), fineMsgA);
    assertEquals(messages.remove(0), fineMsgExNoTraits);

    assertTrue(messages.isEmpty(), "Contains: " + ex.getMessageSet().getMessages());
    assertEquals(ex.getCause(), source);
  }



  public void create_FineMessage() {
    FineRuntimeException ex = new FineRuntimeException(fineMsgA);
    validate_FineMessage(ex);
  }
  private void validate_FineMessage(FineRuntimeException ex) {
    assertEquals(ex.getMessage(), userMsgA);
    assertEquals(ex.getSummary(), userMsgA);

    // Expecting only one message from the message
    List<FineMessage> messages = new ArrayList<>(ex.getMessageSet().getMessages());
    assertEquals(messages.remove(0), fineMsgA);
    assertTrue(messages.isEmpty(), "Contains: " + ex.getMessageSet().getMessages());

    assertEquals(ex.getCause(), null);
  }



  public void create_FineMessage_Throwable_TraitsArray() {
    FineRuntimeException ex = new FineRuntimeException(fineMsgA, ioException, traitsArrayA);
    validate_FineMessage_Throwable_TraitsArray(ex, ioException);

    ex = new FineRuntimeException(fineMsgA, ioException, Arrays.asList(traitsArrayA));
    validate_FineMessage_Throwable_TraitsArray(ex, ioException);

    ex = new FineRuntimeException(fineMsgA, ioException, BeanUtils.toMap(traitsArrayA));
    validate_FineMessage_Throwable_TraitsArray(ex, ioException);

    ex = new FineRuntimeException(fineMsgA, ioException, new TraitMap(traitsArrayA));
    validate_FineMessage_Throwable_TraitsArray(ex, ioException);

    FineRuntimeException second = new FineRuntimeException(ex);
    validate_FineMessage_Throwable_TraitsArray(second, ex);
  }
  private void validate_FineMessage_Throwable_TraitsArray(FineRuntimeException ex, Throwable source) {
    assertEquals(ex.getMessage(), userMsgAX);
    assertEquals(ex.getSummary(), userMsgAX);

    // Expecting two messages, one form the message, one from the exception
    List<FineMessage> messages = new ArrayList<>(ex.getMessageSet().getMessages());
    assertEquals(messages.remove(0), fineMsgA);
    assertEquals(messages.remove(0), fineMsgExNoTraits);
    assertTrue(messages.isEmpty(), "Contains: " + ex.getMessageSet().getMessages());

    assertEquals(ex.getCause(), source);
  }



  public void create_FineMsgSet() {
    FineRuntimeException ex = new FineRuntimeException(fineMsgSet);
    validate_FineMsgSet(ex);
  }
  private void validate_FineMsgSet(FineRuntimeException ex) {
    assertEquals(ex.getMessage(), userMsgAB);
    assertEquals(ex.getSummary(), userMsgAB);

    // Expecting two messages, one for each message in the original set.
    List<FineMessage> messages = new ArrayList<>(ex.getMessageSet().getMessages());
    assertEquals(messages.remove(0), fineMsgA);
    assertEquals(messages.remove(0), fineMsgB);
    assertTrue(messages.isEmpty(), "Contains: " + ex.getMessageSet().getMessages());

    assertEquals(ex.getCause(), null);
  }



  public void create_FineMsgSet_Throwable() {
    FineRuntimeException first = new FineRuntimeException(fineMsgSet, ioException, traitsArrayA);
    validate_FineMsgSet_Throwable(first, ioException);

    first = new FineRuntimeException(fineMsgSet, ioException, Arrays.asList(traitsArrayA));
    validate_FineMsgSet_Throwable(first, ioException);

    first = new FineRuntimeException(fineMsgSet, ioException, BeanUtils.toMap(traitsArrayA));
    validate_FineMsgSet_Throwable(first, ioException);

    first = new FineRuntimeException(fineMsgSet, ioException, new TraitMap(traitsArrayA));
    validate_FineMsgSet_Throwable(first, ioException);

    FineRuntimeException second = new FineRuntimeException(first);
    validate_FineMsgSet_Throwable(second, first);
  }
  private void validate_FineMsgSet_Throwable(FineRuntimeException ex, Throwable source) {
    assertEquals(ex.getMessage(), userMsgABX);
    assertEquals(ex.getSummary(), userMsgABX);

    // Expecting three messages, two from the set, one from the exception
    List<FineMessage> messages = new ArrayList<>(ex.getMessageSet().getMessages());
    assertEquals(messages.remove(0), fineMsgA);
    assertEquals(messages.remove(0), fineMsgB);
    assertEquals(messages.remove(0), fineMsgExNoTraits);
    assertTrue(messages.isEmpty(), "Contains: " + ex.getMessageSet().getMessages());

    assertEquals(ex.getCause(), source);
  }
}