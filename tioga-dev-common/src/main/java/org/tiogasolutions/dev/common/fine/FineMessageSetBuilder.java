package org.tiogasolutions.dev.common.fine;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
* Created by jacobp on 7/29/2014.
*/
public class FineMessageSetBuilder {

  private static final FineMessageSet emptySet = new FineMessageSetImpl();

  public static FineMessageSet empty() {
    return emptySet;
  }

  private final List<FineMessage> messages = new ArrayList<>();

  public FineMessageSet build() {
    return new FineMessageSetImpl(messages);
  }

  public FineMessageSetBuilder withMessage(FineMessage message) {
    messages.add(message);
    return this;
  }

  public FineMessageSetBuilder withMessage(FineMessage message, TraitMap additionalTraits) {
    TraitMap finalTraits = additionalTraits.add(message.getTraitMap());
    message = FineMessage.withTraits(message.getText(), finalTraits);
    messages.add(message);
    return this;
  }

  public FineMessageSetBuilder withText(String text) {
    messages.add(FineMessage.withAll(text, null, TraitMap.empty()));
    return this;
  }

  public FineMessageSetBuilder withId(String text, String id) {
    messages.add(FineMessage.withAll(text, id, TraitMap.empty()));
    return this;
  }

  public FineMessageSetBuilder withTraits(String text, TraitMap traitMap) {
    messages.add(FineMessage.withAll(text, null, traitMap));
    return this;
  }

  public FineMessageSetBuilder withTraits(String text, Map<String, String> traitMapArg) {
    TraitMap traitMap = (traitMapArg != null && traitMapArg.size() > 0) ? new TraitMap(traitMapArg) : TraitMap.empty();
    messages.add(FineMessage.withAll(text, null, traitMap));
    return this;
  }

  public FineMessageSetBuilder withTraits(String text, String...traits) {
    TraitMap traitMap = (traits != null && traits.length > 0) ? new TraitMap(traits) : TraitMap.empty();
    messages.add(FineMessage.withAll(text, null, traitMap));
    return this;
  }

  public FineMessageSetBuilder withAll(String text, String id, TraitMap traitMap) {
    messages.add(FineMessage.withAll(text, id, traitMap));
    return this;
  }

  public FineMessageSetBuilder withSet(FineMessageSet fineMsgSet) {
    for (FineMessage message : fineMsgSet.getMessages()) {
      this.withMessage(message);
    }
    return this;
  }

  public FineMessageSetBuilder withSetAndAdditionalTraits(FineMessageSet fineMsgSet, TraitMap additionalTraits) {
    for (FineMessage message : fineMsgSet.getMessages()) {
      if (additionalTraits != null) {
        this.withMessage(message, additionalTraits);

        // Now clear additionalTraits so they are not added to any subsequent.
        additionalTraits = null;
      } else {
        this.withMessage(message);
      }
    }
    return this;
  }

}
