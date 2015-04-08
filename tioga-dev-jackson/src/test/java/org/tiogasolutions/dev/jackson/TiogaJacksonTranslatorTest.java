package org.tiogasolutions.dev.jackson;

import java.util.Arrays;
import org.tiogasolutions.dev.common.DateUtils;
import org.tiogasolutions.dev.common.fine.*;
import org.tiogasolutions.dev.common.json.JsonTranslator;
import org.tiogasolutions.dev.domain.locality.LatLng;
import org.tiogasolutions.dev.testing.domain.FreeBird;
import org.testng.annotations.*;
import org.tiogasolutions.dev.domain.money.Money;

import static org.testng.Assert.assertEquals;

@Test
public class TiogaJacksonTranslatorTest {

  // The following tests are not really intended to test Jackson, but
  // rather objects that exist in tioga-dev-common (which knows not of
  // jackson) are translated as we would expect.

  private JsonTranslator translator;

  @BeforeClass
  public void beforeClass() throws Exception {
    translator = new TiogaJacksonTranslator(Arrays.asList(new TiogaJacksonInjectable("injected-string", "I was injected.")));
  }

  public void translateFreeBird() {
    FreeBird oldValue = new FreeBird(
        "string-value", Long.MAX_VALUE, Integer.MIN_VALUE,
        new LatLng("37.3382030", "-119.7085060"),
        new Money("19.95"),

        DateUtils.toLocalTime("12:32:13.333"),
        DateUtils.toLocalDate("1974-09-03"),
        DateUtils.toLocalDateTime("1975-05-06T12:32:13.333"),
        DateUtils.toZonedDateTime("1975-05-06T12:32:13.333-07:00[America/Los_Angeles]"),

        new TraitMap("first:yes", "empty-value:", "null-value", "last:ok"),
        FineMessage.withText("This message is fine!"),
        (FineMessageSetImpl) new FineMessageSetBuilder()
            .withText("Hi, my name is Joe!")
            .withTraits("I'm Suzie", "girl:true", "boy:false").build());

    assertEquals(oldValue.getInjected(), null);
    assertEquals(oldValue.getMissing(), null);

    String json = translator.toJson(oldValue);
    assertEquals(json, String.format(FREE_BIRD_RESULT_JSON, null, null));

    FreeBird newValue = translator.fromJson(FreeBird.class, FREE_BIRD_SOURCE_JSON);

    assertEquals(newValue.getInjected(), "I was injected.");
    assertEquals(newValue.getMissing(), null);
    assertEquals(newValue.getStringValue(), oldValue.getStringValue());
    assertEquals(newValue.getLongValue(), oldValue.getLongValue());
    assertEquals(newValue.getIntValue(), oldValue.getIntValue());
    assertEquals(newValue.getLatLng(), oldValue.getLatLng());
    assertEquals(newValue.getTiogaMoney(), oldValue.getTiogaMoney());

    assertEquals(newValue.getJavaLocalTime(), oldValue.getJavaLocalTime());
    assertEquals(newValue.getJavaLocalDate(), oldValue.getJavaLocalDate());
    assertEquals(newValue.getJavaLocalDateTime(), oldValue.getJavaLocalDateTime());

    assertEquals(newValue.getTraitMap(), oldValue.getTraitMap());
    assertEquals(newValue.getFineMessage(), oldValue.getFineMessage());
    assertEquals(newValue.getMessageSet(), oldValue.getMessageSet());
  }

  public void translateLatLong() {
    LatLng oldValue = new LatLng("37.3382030", "-119.7085060");
    String json = translator.toJson(oldValue);
    assertEquals(json, LAT_LNG_JSON);
  }

  public void translateTiogaMoney() {
    Money oldValue;

    oldValue = new Money("19.75");
    String json = translator.toJson(oldValue);
    assertEquals(json, "\"19.75\"");

    oldValue = new Money("0");
    json = translator.toJson(oldValue);
    assertEquals(json, "\"0.00\"");

    oldValue = new Money("1,000,000,000,000,000.00");
    json = translator.toJson(oldValue);
    assertEquals(json, "\"1000000000000000.00\"");
  }

  public void translateTraitMap() {
    // The resulting sort on this will seem weird because one is before two and
    // before three BUT, this is an alpha sort and F (four) comes before T (three)
    TraitMap oldValue = new TraitMap("keyTwo:valTwo", "keyFour:", "keyThree", "keyOne:valOne");

    String json = translator.toJson(oldValue);
    assertEquals(json, TRAIT_MAP_JSON);

    TraitMap newValue = translator.fromJson(TraitMap.class, TRAIT_MAP_JSON);
    assertEquals(newValue, oldValue);
  }

  public void translateFineMessageSet() {

    FineMessageSet oldValue = new FineMessageSetBuilder()
        .withText("One")
        .withTraits("Two", "keyOne:valOne", "keyTwo:valTwo")
        .withId("Three", "3")
        .withAll("Four", "4", new TraitMap("keyOne:valeOne", "keyFour:valFour"))
        .build();

    String json = translator.toJson(oldValue);
    assertEquals(json, FINE_MESSAGE_SET_JSON);

    FineMessageSet newValue = translator.fromJson(FineMessageSet.class, json);
    assertEquals(newValue, oldValue);
  }

  public void translateFineMessage() {
    // The resulting sort on this will seem weird because one is before two and
    // before three BUT, this is an alpha sort and F (four) comes before T (three)
    TraitMap traitMap = new TraitMap("keyTwo:valTwo", "keyFour:", "keyThree", "keyOne:valOne");
    FineMessage oldValue = FineMessage.withAll("some message", "99", traitMap);

    String json = translator.toJson(oldValue);
    assertEquals(json, FINE_MESSAGE_JSON);

    FineMessage newValue = translator.fromJson(FineMessage.class, FINE_MESSAGE_JSON);
    assertEquals(newValue, oldValue);
  }

  public static final String LAT_LNG_JSON = "{\n" +
      "  \"latitude\" : \"37.3382030\",\n" +
      "  \"longitude\" : \"-119.7085060\",\n" +
      "  \"city\" : null,\n" +
      "  \"state\" : null,\n" +
      "  \"country\" : null\n" +
      "}";
  
  public static final String FINE_MESSAGE_JSON = "{\n" +
      "  \"text\" : \"some message\",\n" +
      "  \"id\" : \"99\",\n" +
      "  \"traitMap\" : {\n" +
      "    \"keyFour\" : \"\",\n" +
      "    \"keyOne\" : \"valOne\",\n" +
      "    \"keyThree\" : null,\n" +
      "    \"keyTwo\" : \"valTwo\"\n" +
      "  }\n" +
      "}";

  public static final String TRAIT_MAP_JSON = "{\n" +
      "  \"keyFour\" : \"\",\n" +
      "  \"keyOne\" : \"valOne\",\n" +
      "  \"keyThree\" : null,\n" +
      "  \"keyTwo\" : \"valTwo\"\n" +
      "}";

  static final String FINE_MESSAGE_SET_JSON = "{\n" +
      "  \"messages\" : [ {\n" +
      "    \"text\" : \"One\",\n" +
      "    \"id\" : null,\n" +
      "    \"traitMap\" : { }\n" +
      "  }, {\n" +
      "    \"text\" : \"Two\",\n" +
      "    \"id\" : null,\n" +
      "    \"traitMap\" : {\n" +
      "      \"keyOne\" : \"valOne\",\n" +
      "      \"keyTwo\" : \"valTwo\"\n" +
      "    }\n" +
      "  }, {\n" +
      "    \"text\" : \"Three\",\n" +
      "    \"id\" : \"3\",\n" +
      "    \"traitMap\" : { }\n" +
      "  }, {\n" +
      "    \"text\" : \"Four\",\n" +
      "    \"id\" : \"4\",\n" +
      "    \"traitMap\" : {\n" +
      "      \"keyFour\" : \"valFour\",\n" +
      "      \"keyOne\" : \"valeOne\"\n" +
      "    }\n" +
      "  } ]\n" +
      "}";

  public static final String FREE_BIRD_RESULT_JSON = "{\n" +
      "  \"stringValue\" : \"string-value\",\n" +
      "  \"longValue\" : 9223372036854775807,\n" +
      "  \"intValue\" : -2147483648,\n" +
      "  \"latLng\" : {\n" +
      "    \"latitude\" : \"37.3382030\",\n" +
      "    \"longitude\" : \"-119.7085060\",\n" +
      "    \"city\" : null,\n" +
      "    \"state\" : null,\n" +
      "    \"country\" : null\n" +
      "  },\n" +
      "  \"tiogaMoney\" : \"19.95\",\n" +

      "  \"javaLocalTime\" : \"12:32:13.333\",\n" +
      "  \"javaLocalDate\" : \"1974-09-03\",\n" +
      "  \"javaLocalDateTime\" : \"1975-05-06T12:32:13.333\",\n" +
      "  \"javaZonedDateTime\" : \"1975-05-06T12:32:13.333-07:00[America/Los_Angeles]\",\n" +

      "  \"traitMap\" : {\n" +
      "    \"empty-value\" : \"\",\n" +
      "    \"first\" : \"yes\",\n" +
      "    \"last\" : \"ok\",\n" +
      "    \"null-value\" : null\n" +
      "  },\n" +
      "  \"fineMessage\" : {\n" +
      "    \"text\" : \"This message is fine!\",\n" +
      "    \"id\" : null,\n" +
      "    \"traitMap\" : { }\n" +
      "  },\n" +
      "  \"messageSet\" : {\n" +
      "    \"messages\" : [ {\n" +
      "      \"text\" : \"Hi, my name is Joe!\",\n" +
      "      \"id\" : null,\n" +
      "      \"traitMap\" : { }\n" +
      "    }, {\n" +
      "      \"text\" : \"I'm Suzie\",\n" +
      "      \"id\" : null,\n" +
      "      \"traitMap\" : {\n" +
      "        \"boy\" : \"false\",\n" +
      "        \"girl\" : \"true\"\n" +
      "      }\n" +
      "    } ]\n" +
      "  },\n" +
      "  \"injected\" : %s,\n" +
      "  \"missing\" : %s\n" +
      "}";

  public static final String FREE_BIRD_SOURCE_JSON = "{\n" +
      "  \"stringValue\" : \"string-value\",\n" +
      "  \"longValue\" : 9223372036854775807,\n" +
      "  \"intValue\" : -2147483648,\n" +
      "  \"latLng\" : {\n" +
      "    \"latitude\" : \"37.3382030\",\n" +
      "    \"longitude\" : \"-119.7085060\",\n" +
      "    \"city\" : null,\n" +
      "    \"state\" : null,\n" +
      "    \"country\" : null\n" +
      "  },\n" +
      "  \"tiogaMoney\" : \"19.95\",\n" +

      "  \"javaLocalTime\" : \"12:32:13.333\",\n" +
      "  \"javaLocalDate\" : \"1974-09-03\",\n" +
      "  \"javaLocalDateTime\" : \"1975-05-06T12:32:13.333\",\n" +
      "  \"javaZonedDateTime\" : \"1975-05-06T12:32:13.333\",\n" +

      "  \"traitMap\" : {\n" +
      "    \"empty-value\" : \"\",\n" +
      "    \"first\" : \"yes\",\n" +
      "    \"last\" : \"ok\",\n" +
      "    \"null-value\" : null\n" +
      "  },\n" +
      "  \"fineMessage\" : {\n" +
      "    \"text\" : \"This message is fine!\",\n" +
      "    \"id\" : null,\n" +
      "    \"traitMap\" : { }\n" +
      "  },\n" +
      "  \"messageSet\" : {\n" +
      "    \"messages\" : [ {\n" +
      "      \"text\" : \"Hi, my name is Joe!\",\n" +
      "      \"id\" : null,\n" +
      "      \"traitMap\" : { }\n" +
      "    }, {\n" +
      "      \"text\" : \"I'm Suzie\",\n" +
      "      \"id\" : null,\n" +
      "      \"traitMap\" : {\n" +
      "        \"BOY\" : \"false\",\n" +
      "        \"GIRL\" : \"true\"\n" +
      "      }\n" +
      "    } ]\n" +
      "  }\n" +
      "}";
}