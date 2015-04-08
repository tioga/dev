package org.tiogasolutions.dev.domain.validation;

import org.tiogasolutions.dev.common.fine.FineMessageSet;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.validation.Validation;

/**
 * User: harlann
 * Date: 8/26/13
 * Time: 10:50 PM
 */
public class Jsr349BeanValidationTest {

  private BeanValidator validator;

  @BeforeClass
  public void setup() {
    validator = new Jsr349BeanValidator(Validation.buildDefaultValidatorFactory());
  }

  @Test
  public void okTest() {
    Person person = new Person("George", "Washington", 99);
    FineMessageSet messageSet = validator.validate(person);
    Assert.assertTrue(messageSet.isEmpty());
  }

  @Test
  public void invalidFirstNameTest() {
    Person person = new Person(null, "Washington", 99);
    FineMessageSet messageSet = validator.validate(person);
    Assert.assertFalse(messageSet.isEmpty());
    Assert.assertTrue(messageSet.hasId("firstName"));
    Assert.assertEquals(messageSet.findFirstWithId("firstName").getText(), "may not be null");
  }

  @Test
  public void invalidLastNameTest() {
    Person person = new Person("George", null, 99);
    FineMessageSet messageSet = validator.validate(person);
    Assert.assertFalse(messageSet.isEmpty());
    Assert.assertTrue(messageSet.hasId("lastName"));
    Assert.assertEquals(messageSet.findFirstWithId("lastName").getText(), "may not be null");
  }

  @Test
  public void invalidAgeTest() {
    Person person = new Person(null, "Washington", -1);
    FineMessageSet messageSet = validator.validate(person);
    Assert.assertFalse(messageSet.isEmpty());
    Assert.assertTrue(messageSet.hasId("age"));
    Assert.assertEquals(messageSet.findFirstWithId("age").getText(), "must be between 0 and 9223372036854775807");
  }

}
