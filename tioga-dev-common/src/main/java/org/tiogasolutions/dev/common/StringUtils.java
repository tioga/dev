/*
 * Copyright 2012 Jacob D Parr
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

package org.tiogasolutions.dev.common;

import org.tiogasolutions.dev.common.exceptions.ExceptionUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.*;

public class StringUtils {
  
  public static String UTF_8 = "UTF-8";

  public static final Map<Integer,String> htmlMap;
  static {
    Map<Integer,String> map = new HashMap<>();
    map.put(160, "nbsp");
    map.put(161, "iexcl");
    map.put(162, "cent");
    map.put(163, "pound");
    map.put(164, "curren");
    map.put(165, "yen");
    map.put(166, "brvbar");
    map.put(167, "sect");
    map.put(168, "uml");
    map.put(169, "copy");
    map.put(170, "ordf");
    map.put(171, "laquo");
    map.put(172, "not");
    map.put(173, "shy");
    map.put(174, "reg");
    map.put(175, "macr");
    map.put(176, "deg");
    map.put(177, "plusmn");
    map.put(178, "sup2");
    map.put(179, "sup3");
    map.put(180, "acute");
    map.put(181, "micro");
    map.put(182, "para");
    map.put(183, "middot");
    map.put(184, "cedil");
    map.put(185, "sup1");
    map.put(186, "ordm");
    map.put(187, "raquo");
    map.put(188, "frac14");
    map.put(189, "frac12");
    map.put(190, "frac34");
    map.put(191, "iquest");
    map.put(192, "Agrave");
    map.put(193, "Aacute");
    map.put(194, "Acirc");
    map.put(195, "Atilde");
    map.put(196, "Auml");
    map.put(197, "Aring");
    map.put(198, "AElig");
    map.put(199, "Ccedil");
    map.put(200, "Egrave");
    map.put(201, "Eacute");
    map.put(202, "Ecirc");
    map.put(203, "Euml");
    map.put(204, "Igrave");
    map.put(205, "Iacute");
    map.put(206, "Icirc");
    map.put(207, "Iuml");
    map.put(208, "ETH");
    map.put(209, "Ntilde");
    map.put(210, "Ograve");
    map.put(211, "Oacute");
    map.put(212, "Ocirc");
    map.put(213, "Otilde");
    map.put(214, "Ouml");
    map.put(215, "times");
    map.put(216, "Oslash");
    map.put(217, "Ugrave");
    map.put(218, "Uacute");
    map.put(219, "Ucirc");
    map.put(220, "Uuml");
    map.put(221, "Yacute");
    map.put(222, "THORN");
    map.put(223, "szlig");
    map.put(224, "agrave");
    map.put(225, "aacute");
    map.put(226, "acirc");
    map.put(227, "atilde");
    map.put(228, "auml");
    map.put(229, "aring");
    map.put(230, "aelig");
    map.put(231, "ccedil");
    map.put(232, "egrave");
    map.put(233, "eacute");
    map.put(234, "ecirc");
    map.put(235, "euml");
    map.put(236, "igrave");
    map.put(237, "iacute");
    map.put(238, "icirc");
    map.put(239, "iuml");
    map.put(240, "eth");
    map.put(241, "ntilde");
    map.put(242, "ograve");
    map.put(243, "oacute");
    map.put(244, "ocirc");
    map.put(245, "otilde");
    map.put(246, "ouml");
    map.put(247, "divide");
    map.put(248, "oslash");
    map.put(249, "ugrave");
    map.put(250, "uacute");
    map.put(251, "ucirc");
    map.put(252, "uuml");
    map.put(253, "yacute");
    map.put(254, "thorn");
    map.put(255, "yuml");
    htmlMap = Collections.unmodifiableMap(map);
  }

  private StringUtils() {
  }

  public static int countOccurrencesOf(Object value, String sub) {
    if (isBlank(value) || isBlank(sub)) {
      return 0;
    }

    int count = 0;
    int pos = 0;
    int idx;
    String text = value.toString();
    while ((idx = text.indexOf(sub, pos)) != -1) {
      ++count;
      pos = idx + sub.length();
    }
    return count;
  }

  /**
   * @return true if value.toString() is null or zero-length
   * @param value the object to be evaluated
   */
  public static boolean isBlank(Object value) {
    return (value == null || value.toString().isEmpty());
  }
  
  /**
   * @return true if value.toString() is NOT null or NOT zero-length
   * @param value the object to be evaluated
   */
  public static boolean isNotBlank(Object value) {
    return isBlank(value) == false;
  }

  public static boolean isAnyBlank(String...values) {
    return values != null && isAnyBlank(Arrays.asList(values));
  }
  public static boolean isAnyBlank(Collection<?> values) {
    if (values == null) {
      return false;
    }

    for (Object value : values) {
      if (isBlank(value)) {
        return true;
      }
    }

    return false;
  }

  public static boolean isAnyNotBlank(String...values) {
    return values != null && isAnyNotBlank(Arrays.asList(values));
  }
  public static boolean isAnyNotBlank(Collection<?> values) {
    if (values == null) {
      return false;
    }

    for (Object value : values) {
      if (isNotBlank(value)) {
        return true;
      }
    }

    return false;
  }

  /**
   * @return true if value.toString() is null contains only whitespace.
   * @param value the object to be evaluated
   */
  public static boolean isWhitespace(Object value) {
    return (value == null || value.toString().trim().isEmpty());
  }

  public static String escapeHtml(Object value) {
    if (value == null) {
      return null;
    }
    String text = value.toString();

    // A list of characters that do not need to be escaped.
    List<Character> acceptable = new ArrayList<>();
    for (char c = 'a'; c <= 'z'; c++) acceptable.add(c);
    for (char c = 'A'; c <= 'Z'; c++) acceptable.add(c);
    for (char c = '0'; c <= '9'; c++) acceptable.add(c);
    acceptable.addAll(Arrays.asList('-', '.', ',', '_'));

    StringBuilder builder = new StringBuilder();
    for ( int i = 0; i < text.length(); i++ ) {
      char c = text.charAt( i );
      if (acceptable.contains(c)) {
        builder.append(c);
      } else {
        builder.append("&#");
        builder.append(Integer.valueOf(c));
        builder.append(";");
      }
    }
    return builder.toString();
  }

  public static List<String> tokenize(Object value, char delimiter) {
    if (value == null) {
      return new ArrayList<>();
    }
    String text = value.toString();

    List<String> retVal = new ArrayList<>();
    StringTokenizer tokenizer = new StringTokenizer(text, delimiter+"");
    while (tokenizer.hasMoreTokens()) {
      retVal.add(tokenizer.nextToken());
    }
    return retVal;
  }

  public static String decodeUrl(Object value) {
    if (value == null) {
      return null;
    }
    String text = value.toString();

    try {
      return URLDecoder.decode(text, UTF_8);
    } catch (UnsupportedEncodingException e) {
      throw new RuntimeException(e);
    }
  }

  public static String encodeUrl(Object value) {
    if (value == null) {
      return null;
    }
    String text = value.toString();

    try {
      return URLEncoder.encode(text, UTF_8);
    } catch (UnsupportedEncodingException e) {
      throw new RuntimeException(e);
    }
  }

  public static String getNth(int sequence) {
    if (sequence >= 4 && sequence <= 20) {
      return sequence+"th";
    } else {
      String sv = String.valueOf(sequence);
      char last = sv.charAt(sv.length()-1);
      if (last == '1') {
        return sequence+"st";
      } else if (last == '2') {
        return sequence+"nd";
      } else if (last == '3') {
        return sequence+"rd";
      } else {
        return sequence+"th";
      }
    }
  }

  public static String toDelineatedString(String delineator, String...values) {
    ExceptionUtils.assertNotNull(values, "values");
    return toDelineatedString(delineator, delineator, Arrays.asList(values));
  }
  public static String toDelineatedString(String delineator, Collection<?> values) {
    ExceptionUtils.assertNotNull(values, "values");
    return toDelineatedString(delineator, delineator, values);
  }
  public static String toDelineatedString(String delineator, String lastDelineator, Collection<?> values) {
    ExceptionUtils.assertNotNull(values, "values");
    ExceptionUtils.assertNotNull(delineator, "delineator");
    ExceptionUtils.assertNotNull(lastDelineator, "lastDelineator");

    List<?> objects = new ArrayList<Object>(values);
    // noinspection StatementWithEmptyBody
    while (objects.remove(null));

    StringBuilder builder = new StringBuilder();

    for (int i = 0; i < objects.size(); i++) {
      Object object = objects.get(i);
      String id = object.toString();

      if (i > 0 && i == objects.size() - 1) {
        builder.append(lastDelineator);
      } else if (i > 0) {
        builder.append(delineator);
      }

      builder.append(id);
    }
    return builder.toString();
  }

  /**
   * Safely converts an object to it's string value without 
   * a "null value check". If the value is null, null is returned.
   * @param value the object to be converted.
   * @return null or a string representation of the value.
   */
  public static String toString(Object value) {
    return (value == null) ? null : value.toString();
  }
  
  public static String getTagContents(Object value, String tagName, int offset) {
    if (value == null) {
      return null;
    }

    String text = value.toString();
    String begTag = String.format("<%s ", tagName);
    
    int posA = text.indexOf(begTag, offset);
    if (posA < 0) {
      begTag = String.format("<%s>", tagName);
      posA = text.indexOf(begTag, offset);
      if (posA < 0) {
        return null;
      }
    }
    
    int posB = text.indexOf(">", posA+1);
    if (posB < 0) {
      return null;
    }
    
    if ('/' == text.charAt(posB-1)) {
      // we have an empty tag.
      return null;
    }
    
    posA = posB+1;
    String endTag = String.format("</%s>", tagName);
    posB = text.indexOf(endTag, posA);
    if (posB < 0) {
      return null;
    }
    
    text = text.substring(posA, posB);
    return text;
  }

  public static String encode_ISO_8859_1(Object value) {
    if (value == null) {
      return null;
    }

    String text = value.toString();
    StringBuilder builder = new StringBuilder();
    
    for (char chr : text.toCharArray()) {
      String txt = htmlMap.get((int)chr);
      if (txt == null) {
        builder.append(chr);
      } else {
        builder.append("&");
        builder.append(txt);
        builder.append(";");
      }
    }
    
    return builder.toString();
  }


  public static String truncateRight(Object value, int length) {
    return truncateRight(value, length, null);
  }
  public static String truncateRight(Object value, int length, String ellipsis) {
    if (value == null) {
      return null;
    }

    if (ellipsis == null) ellipsis = "";

    if (ellipsis.length() > length) {
      return ellipsis.substring(0, length);
    }

    String text = value.toString();

    if (text.length() > length) {
      text = text.substring(0, length- ellipsis.length());
      text += ellipsis;
    }

    return text;
  }

  public static String truncateLeft(Object value, int length) {
    return truncateLeft(value, length, null);
  }
  public static String truncateLeft(Object value, int length, String ellipsis) {
    if (value == null) {
      return null;
    }

    String text = value.toString();
    if (ellipsis == null) ellipsis = "";

    if (text.length() > length) {
      int pos = text.length() - length + ellipsis.length();
      text = text.substring(Math.min(pos, text.length()));
      text = ellipsis + text;

      if (text.length() > length) {
        text = text.substring(0, length);
      }
    }

    return text;
  }

  public static String padCenter(Object value, int length) {
    return padCenter(value, length, ' ');
  }

  public static String padCenter(Object value, int length, char chr) {
    StringBuilder builder = new StringBuilder();
    if (value != null) builder.append(value.toString());

    while (builder.length() < length) {
      builder.insert(0, chr);
      if (builder.length() < length) {
        builder.append(chr);
      }
    }

    return builder.toString();
  }

  public static String padLeft(Object value, int length) {
    return padLeft(value, length, ' ');
  }

  public static String padLeft(Object value, int length, char chr) {
    String text = (value == null) ? "" : value.toString();

    int size = text.length();
    StringBuilder builder = new StringBuilder(text);

    for (int i = size; i < length; i++) {
      builder.insert(0, chr);
    }

    return builder.toString();
  }

  public static String padRight(Object value, int length) {
    return padRight(value, length, ' ');
  }

  public static String padRight(Object value, int length, char chr) {
    String text = (value == null) ? "" : value.toString();

    int size = text.length();
    StringBuilder builder = new StringBuilder(text);

    for (int i = size; i < length; i++) {
      builder.append(chr);
    }

    return builder.toString();
  }

  public static String toFixedLength(int length, char chr) {
    return toFixedLength("", length, chr, null);
  }
  public static String toFixedLength(Object value, int length, char chr) {
    return toFixedLength(value, length, chr, null);
  }
  public static String toFixedLength(Object value, int length, char chr, String ellipsis) {
    if (length > 0) return fixedRight(value, length, chr, ellipsis);
    if (length < 0) return fixedLeft(value, length*-1, chr, ellipsis);
    return "";
  }
  private static String fixedRight(Object value, int length, char chr, String ellipsis) {
    String text = (value == null) ? "" : value.toString();

    text = StringUtils.truncateRight(text, length, ellipsis);
    text = StringUtils.padRight(text, length, chr);
    return text;
  }
  private static String fixedLeft(Object value, int length, char chr, String ellipsis) {
    String text = (value == null) ? "" : value.toString();

    text = StringUtils.truncateLeft(text, length, ellipsis);
    text = StringUtils.padLeft(text, length, chr);
    return text;
  }

  public static String trim(Object value) {
    return (value == null) ? null : value.toString().trim();
  }

  public static String toUpperCase(Object value) {
    return (value == null) ? null : value.toString().toUpperCase();
  }

  public static String toLowerCase(Object value) {
    return (value == null) ? null : value.toString().toLowerCase();
  }

  public static String substring(Object value, int beginIndex, int endIndex) {
    if (value == null) {
      return null;
    }

    String text = value.toString();

    // Convert negative values to their positive
    if (beginIndex < 0) {
      beginIndex = text.length() + beginIndex;
    }
    if (endIndex < 0) {
      endIndex = text.length() + endIndex;
    }


    // Readjust for out-of-bounds
    if (beginIndex < 0) {
      beginIndex = 0;
    } else if (beginIndex > text.length()) {
      beginIndex = text.length();
    }

    if (endIndex < 0) {
      endIndex = 0;
    } else if (endIndex > text.length()) {
      endIndex = text.length();
    }

    boolean reverse = false;
    if (endIndex < beginIndex) {
      int temp = endIndex;
      endIndex = beginIndex;
      beginIndex = temp;
      reverse = true;
    }

    text = text.substring(beginIndex, endIndex);
    return (reverse) ? reverse(text): text;
  }

  public static String reverse(Object value) {
    if (value == null) {
      return null;
    }

    String text = value.toString();
    char[] characters = text.toCharArray();

    for(int i = 0; i < characters.length / 2; i++) {
      char temp = characters[i];
      characters[i] = characters[characters.length - i - 1];
      characters[characters.length - i - 1] = temp;
    }

    return new String(characters);
  }

  public static int length(Object value) {
    return (value == null) ? 0 : value.toString().length();
  }

  public static String removeZeroDecimal(Object value) {
    if (value == null) {
      return null;
    }

    String text = value.toString();

    while (text.endsWith("0")) {
      text = substring(text, 0, -1);
    }

    while (text.endsWith(".")) {
      text = substring(text, 0, -1);
    }

    return text;
  }

  /**
   * Converts any null value to an empty string
   * @param value the Object to be converted.
   * @return an empty String or a String representation of the value.
   */
  public static String nullToString(Object value) {
    return (value == null) ? "" : value.toString();
  }

  /**
   * Converts an empty string to a null value.
   * @param value the Object to be converted.
   * @return null or a String representation of the value.
   */
  public static String emptyToNull(Object value) {
    return StringUtils.isBlank(value) ? null : value.toString();
  }

  /**
   * Converts an white-space-only string to a null value.
   * @param value the Object to be converted.
   * @return null or a String representation of the value.
   */
  public static String whiteSpaceToNull(Object value) {
    if (StringUtils.isBlank(value)) {
      return null;
    } else {
      return value.toString().trim().isEmpty() ? null : value.toString();
    }
  }

  public static String sanitizePhoneNumber(Object value) {
    String text = whiteSpaceToNull(value);
    if (text == null) {
      return null;
    }

    text = text.replaceAll(" ", "");
    text = text.replaceAll("-", "");
    text = text.replaceAll("\\.", "");
    text = text.replaceAll("\\(", "");
    text = text.replaceAll("\\)", "");

    return text;
  }

  public static String appendQueryParam(Object value, Object queryKey, Object queryValue) {
    if (value == null) {
      return null;
    }

    String text = value.toString();
    int pos = text.indexOf("?");

    if (pos < 0) {
      // It doesn't exist, go ahead and add it.
      return text + "?" + queryKey + "=" + queryValue;

    } else if (pos == text.length()-1 || text.endsWith("&")) {
      // It's at the end, just add the query.
      return text + queryKey + "=" + queryValue;

    } else {
      return text + "&" + queryKey + "=" + queryValue;

    }
  }

  public static String removeLineSeparators(Object value) {
    return replaceLineSeparators(value, "");
  }

  public static String replaceLineSeparators(Object value, String with) {
    if (value == null) {
      return null;
    }
    String text = value.toString();
    return text.replaceAll("(\\r)?\\n", with);
  }

  public static int indexOfExpected(Object value, String...strings) {
    return indexOfExpected(value, Arrays.asList(strings));
  }

  public static int indexOfExpected(Object value, Collection<String> strings) {

    int pos = -1;
    ExceptionUtils.assertNotEmpty(strings, "strings");
    String text = ExceptionUtils.assertNotNull(value, "value").toString();

    for (String string : strings) {
      pos = text.indexOf(string, pos+1);
      assertContentFound(pos);
    }

    return pos;
  }

  public static int indexOfExpected(Object value, String what, int fromIndex) {
    ExceptionUtils.assertNotNull(value, "value");

    int pos = value.toString().indexOf(what, fromIndex);
    return assertContentFound(pos);
  }

  public static int lastIndexOfExpected(Object value, String...strings) {
    return lastIndexOfExpected(value, Arrays.asList(strings));
  }

  public static int lastIndexOfExpected(Object value, Collection<String> strings) {

    ExceptionUtils.assertNotEmpty(strings, "strings");
    String text = ExceptionUtils.assertNotNull(value, "value").toString();
    int pos = text.length();

    for (String string : strings) {
      pos = text.lastIndexOf(string, pos);
      assertContentFound(pos);
    }

    return pos;
  }

  public static int lastIndexOfExpected(Object value, String what, int fromIndex) {
    ExceptionUtils.assertNotNull(value, "value");

    int pos = value.toString().lastIndexOf(what, fromIndex);
    return assertContentFound(pos);
  }

  private static int assertContentFound(int pos) {
    if (pos < 0) {
      String msg = String.format("Content not found.");
      throw new IllegalArgumentException(msg);
    }
    return pos;
  }

  public static String toHex(String asciiValue) {
    if (asciiValue == null) {
      return null;
    }

    char[] chars = asciiValue.toCharArray();
    StringBuilder hex = new StringBuilder();
    for (char aChar : chars) {
      hex.append(Integer.toHexString((int) aChar));
    }
    return hex.toString();
  }

  public static String fromHex(String hexValue) {
    if (hexValue == null) {
      return null;
    }
    StringBuilder output = new StringBuilder("");
    for (int i = 0; i < hexValue.length(); i += 2) {
      String str = hexValue.substring(i, i + 2);
      output.append((char) Integer.parseInt(str, 16));
    }
    return output.toString();
  }
}
