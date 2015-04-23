package org.tiogasolutions.dev.jackson;

import org.tiogasolutions.dev.common.json.JsonTranslator;
import org.tiogasolutions.dev.domain.locality.Country;
import org.tiogasolutions.dev.domain.locality.LatLng;
import org.tiogasolutions.dev.domain.locality.State;
import org.tiogasolutions.dev.domain.query.ListQueryResult;
import org.tiogasolutions.dev.domain.query.QueryResult;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Arrays;

import static org.testng.Assert.assertEquals;

@Test
public class QueryResultTranslatorTest {

  private JsonTranslator translator;
  private LatLng latLongOne = new LatLng("37.3382030", "-119.7085060", "Yosemite", State.CA, Country.US);
  private LatLng latLongTwo = new LatLng("39.3382030", "-120.7085060", "Ahwahnee", State.CA, Country.US);

  @BeforeClass
  public void beforeClass() throws Exception {
    translator = new TiogaJacksonTranslator();
  }

  public void empty() {

    QueryResult<LatLng> origQr = ListQueryResult.newEmpty(LatLng.class);
    String json = translator.toJson(origQr);
    assertEquals(json, EMPTY_RESULT_JSON);

    QueryResult translatedQr = translator.fromJson(QueryResult.class, json);

    assertEquals(origQr, translatedQr);
  }

  public void singleValue() {

    QueryResult<LatLng> origQr = ListQueryResult.newSingle(LatLng.class, latLongOne);
    String json = translator.toJson(origQr);
    assertEquals(json, SINGLE_RESULT_JSON);

    QueryResult translatedQr = translator.fromJson(QueryResult.class, json);

    assertEquals(origQr, translatedQr);
  }

  public void multipleComplete() {

    LatLng latLongOne = new LatLng("37.3382030", "-119.7085060", "Yosemite", State.CA, Country.US);
    LatLng latLongTwo = new LatLng("39.3382030", "-120.7085060", "Ahwahnee", State.CA, Country.US);
    QueryResult<LatLng> origQr = ListQueryResult.newComplete(LatLng.class, Arrays.asList(latLongOne, latLongTwo));
    String json = translator.toJson(origQr);
    assertEquals(json, MULTIPLE_COMPLETE_JSON);

    QueryResult translatedQr = translator.fromJson(QueryResult.class, json);

    assertEquals(origQr, translatedQr);

  }

  public void multipleIncompleteExact() {

    QueryResult<LatLng> origQr = ListQueryResult.newResult(LatLng.class, 5, 2, 90, true, Arrays.asList(latLongOne, latLongTwo));
    String json = translator.toJson(origQr);
    assertEquals(json, MULTIPLE_INCOMPLETE_EXACT_JSON);

    QueryResult translatedQr = translator.fromJson(QueryResult.class, json);

    assertEquals(origQr, translatedQr);

  }

  public static final String EMPTY_RESULT_JSON = "{\n" +
      "  \"containsType\" : \"org.tiogasolutions.dev.domain.locality.LatLng\",\n" +
      "  \"empty\" : true,\n" +
      "  \"limit\" : 0,\n" +
      "  \"size\" : 0,\n" +
      "  \"totalFound\" : 0,\n" +
      "  \"totalExact\" : true,\n" +
      "  \"offset\" : 0,\n" +
      "  \"hasPrevious\" : false,\n" +
      "  \"hasNext\" : false,\n" +
      "  \"results\" : [ ]\n" +
      "}";

  public static final String SINGLE_RESULT_JSON = "{\n" +
      "  \"containsType\" : \"org.tiogasolutions.dev.domain.locality.LatLng\",\n" +
      "  \"empty\" : false,\n" +
      "  \"limit\" : 0,\n" +
      "  \"size\" : 1,\n" +
      "  \"totalFound\" : 1,\n" +
      "  \"totalExact\" : true,\n" +
      "  \"offset\" : 0,\n" +
      "  \"hasPrevious\" : false,\n" +
      "  \"hasNext\" : false,\n" +
      "  \"results\" : [ {\n" +
      "    \"latitude\" : \"37.3382030\",\n" +
      "    \"longitude\" : \"-119.7085060\",\n" +
      "    \"city\" : \"Yosemite\",\n" +
      "    \"state\" : \"CA\",\n" +
      "    \"country\" : \"US\"\n" +
      "  } ]\n" +
      "}";

  private static final String MULTIPLE_COMPLETE_JSON = "{\n" +
      "  \"containsType\" : \"org.tiogasolutions.dev.domain.locality.LatLng\",\n" +
      "  \"empty\" : false,\n" +
      "  \"limit\" : 0,\n" +
      "  \"size\" : 2,\n" +
      "  \"totalFound\" : 2,\n" +
      "  \"totalExact\" : true,\n" +
      "  \"offset\" : 0,\n" +
      "  \"hasPrevious\" : false,\n" +
      "  \"hasNext\" : false,\n" +
      "  \"results\" : [ {\n" +
      "    \"latitude\" : \"37.3382030\",\n" +
      "    \"longitude\" : \"-119.7085060\",\n" +
      "    \"city\" : \"Yosemite\",\n" +
      "    \"state\" : \"CA\",\n" +
      "    \"country\" : \"US\"\n" +
      "  }, {\n" +
      "    \"latitude\" : \"39.3382030\",\n" +
      "    \"longitude\" : \"-120.7085060\",\n" +
      "    \"city\" : \"Ahwahnee\",\n" +
      "    \"state\" : \"CA\",\n" +
      "    \"country\" : \"US\"\n" +
      "  } ]\n" +
      "}";

  private static final String MULTIPLE_INCOMPLETE_EXACT_JSON = "{\n" +
      "  \"containsType\" : \"org.tiogasolutions.dev.domain.locality.LatLng\",\n" +
      "  \"empty\" : false,\n" +
      "  \"limit\" : 5,\n" +
      "  \"size\" : 2,\n" +
      "  \"totalFound\" : 90,\n" +
      "  \"totalExact\" : true,\n" +
      "  \"offset\" : 2,\n" +
      "  \"hasPrevious\" : true,\n" +
      "  \"hasNext\" : true,\n" +
      "  \"results\" : [ {\n" +
      "    \"latitude\" : \"37.3382030\",\n" +
      "    \"longitude\" : \"-119.7085060\",\n" +
      "    \"city\" : \"Yosemite\",\n" +
      "    \"state\" : \"CA\",\n" +
      "    \"country\" : \"US\"\n" +
      "  }, {\n" +
      "    \"latitude\" : \"39.3382030\",\n" +
      "    \"longitude\" : \"-120.7085060\",\n" +
      "    \"city\" : \"Ahwahnee\",\n" +
      "    \"state\" : \"CA\",\n" +
      "    \"country\" : \"US\"\n" +
      "  } ]\n" +
      "}";
}