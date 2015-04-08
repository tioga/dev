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

import java.util.List;
import org.testng.annotations.Test;

import static org.tiogasolutions.dev.common.StringUtils.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

@Test
public class StringUtilsTest {

  public void testConcat() throws Exception {
    String actual;
    
    actual = toDelineatedString(", ", "one", "two", "three");
    assertEquals(actual, "one, two, three");

    actual = toDelineatedString("-", "a", "b", "c");
    assertEquals(actual, "a-b-c");
    
    actual = toDelineatedString("", "a", "b", "c");
    assertEquals(actual, "abc");
    
    actual = toDelineatedString("-"); // empty list/array
    assertEquals(actual, "");
  }
  
  public void testConcatWithBadDeilm() throws Exception {
    try {
      toDelineatedString(null, "a", "b", "c");
      fail("Expected exception");
      
    } catch (NullPointerException ex) {
      assertEquals(ex.getMessage(), "The value \"delineator\" cannot be null.");
    }
  }
  
  public void testConcatWithBadList() throws Exception {
    try {
      toDelineatedString("-", (List<String>) null);
      fail("Expected exception");
      
    } catch (NullPointerException ex) {
      assertEquals(ex.getMessage(), "The value \"values\" cannot be null.");
    }
  }
  
  public void testConcatWithBadArray() throws Exception {
    try {
      toDelineatedString("-", (String[]) null);
      fail("Expected exception");
      
    } catch (NullPointerException ex) {
      assertEquals(ex.getMessage(), "The value \"values\" cannot be null.");
    }
  }
  
  public void testGetTag() throws Exception {
    
    String text;
    String html = "<html><body>This is a <b>test</b> for <b>parsing</b> data<p/></body></html>";
    
    text = getTagContents(html, "body", 0);
    assertEquals(text, "This is a <b>test</b> for <b>parsing</b> data<p/>");
    
    text = getTagContents(html, "html", 0);
    assertEquals(text, "<body>This is a <b>test</b> for <b>parsing</b> data<p/></body>");
    
    text = getTagContents(html, "p", 0);
    assertEquals(text, null);
    
    text = getTagContents(html, "b", 0);
    assertEquals(text, "test");
    
    text = getTagContents(html, "b", 30);
    assertEquals(text, "parsing");
    
    text = getTagContents(html, "body", 30);
    assertEquals(text, null);
  }

  public void testEmptyToNull() throws Exception {
    assertEquals(emptyToNull(null), null);
    assertEquals(emptyToNull(""), null);
    assertEquals(emptyToNull("\n"), "\n");
    assertEquals(emptyToNull("\t"), "\t");
    assertEquals(emptyToNull(" "), " ");
    assertEquals(emptyToNull("testing 123"), "testing 123");
  }

  public void testWhiteSpaceToNull() throws Exception {
    assertEquals(whiteSpaceToNull(null), null);
    assertEquals(whiteSpaceToNull(""), null);
    assertEquals(whiteSpaceToNull("\n"), null);
    assertEquals(whiteSpaceToNull("\t"), null);
    assertEquals(whiteSpaceToNull(" "), null);
    assertEquals(whiteSpaceToNull("testing 123"), "testing 123");
  }

  public void testSanitizePhoneNumber() throws Exception {
    assertEquals(sanitizePhoneNumber(null), null);
    assertEquals(sanitizePhoneNumber(" "), null);
    assertEquals(sanitizePhoneNumber("\n"), null);
    assertEquals(sanitizePhoneNumber("\t"), null);
    assertEquals(sanitizePhoneNumber("9876543210"), "9876543210");
    assertEquals(sanitizePhoneNumber("987-654-3210"), "9876543210");
    assertEquals(sanitizePhoneNumber("987.654.3210"), "9876543210");
    assertEquals(sanitizePhoneNumber("987 654 3210"), "9876543210");
    assertEquals(sanitizePhoneNumber("(987) 654 3210"), "9876543210");
    assertEquals(sanitizePhoneNumber("(987).-654-.3210"), "9876543210");
  }

  public void testAddQueryParam() throws Exception {
    assertEquals(appendQueryParam("http://www.google.com", "test", "true"), "http://www.google.com?test=true");
    assertEquals(appendQueryParam("http://www.google.com?", "test", "true"), "http://www.google.com?test=true");
    assertEquals(appendQueryParam("http://www.google.com?live=false", "test", "true"), "http://www.google.com?live=false&test=true");
    assertEquals(appendQueryParam("http://www.google.com?live=false&", "test", "true"), "http://www.google.com?live=false&test=true");
  }

  public void testPadRight() throws Exception {
    assertEquals(padRight(null, 10, 'M'), "MMMMMMMMMM");
    assertEquals(padRight("", 10, 'M'), "MMMMMMMMMM");
    assertEquals(padRight("a", 10, 'M'), "aMMMMMMMMM");
    assertEquals(padRight("ab", 10, 'M'), "abMMMMMMMM");
    assertEquals(padRight("abc", 10, 'M'), "abcMMMMMMM");
    assertEquals(padRight("abcd", 10, 'M'), "abcdMMMMMM");
    assertEquals(padRight("abcde", 10, 'M'), "abcdeMMMMM");
    assertEquals(padRight("abcdef", 10, 'M'), "abcdefMMMM");
    assertEquals(padRight("abcdefg", 10, 'M'), "abcdefgMMM");
    assertEquals(padRight("abcdefgh", 10, 'M'), "abcdefghMM");
    assertEquals(padRight("abcdefghi", 10, 'M'), "abcdefghiM");
    assertEquals(padRight("abcdefghij", 10, 'M'), "abcdefghij");
    assertEquals(padRight("abcdefghijk", 10, 'M'), "abcdefghijk");
    assertEquals(padRight("abcdefghijkl", 10, 'M'), "abcdefghijkl");
  }
  public void testPadLeft() throws Exception {
    assertEquals(padLeft(null, 10, 'M'), "MMMMMMMMMM");
    assertEquals(padLeft("", 10, 'M'), "MMMMMMMMMM");
    assertEquals(padLeft("a", 10, 'M'), "MMMMMMMMMa");
    assertEquals(padLeft("ab", 10, 'M'), "MMMMMMMMab");
    assertEquals(padLeft("abc", 10, 'M'), "MMMMMMMabc");
    assertEquals(padLeft("abcd", 10, 'M'), "MMMMMMabcd");
    assertEquals(padLeft("abcde", 10, 'M'), "MMMMMabcde");
    assertEquals(padLeft("abcdef", 10, 'M'), "MMMMabcdef");
    assertEquals(padLeft("abcdefg", 10, 'M'), "MMMabcdefg");
    assertEquals(padLeft("abcdefgh", 10, 'M'), "MMabcdefgh");
    assertEquals(padLeft("abcdefghi", 10, 'M'), "Mabcdefghi");
    assertEquals(padLeft("abcdefghij", 10, 'M'), "abcdefghij");
    assertEquals(padLeft("abcdefghijk", 10, 'M'), "abcdefghijk");
    assertEquals(padLeft("abcdefghijkl", 10, 'M'), "abcdefghijkl");
  }
  public void testPadCenter() throws Exception {
    assertEquals(padCenter(null, 10, 'M'), "MMMMMMMMMM");
    assertEquals(padCenter("", 10, 'M'), "MMMMMMMMMM");
    assertEquals(padCenter("a", 10, 'M'), "MMMMMaMMMM");
    assertEquals(padCenter("ab", 10, 'M'), "MMMMabMMMM");
    assertEquals(padCenter("abc", 10, 'M'), "MMMMabcMMM");
    assertEquals(padCenter("abcd", 10, 'M'), "MMMabcdMMM");
    assertEquals(padCenter("abcde", 10, 'M'), "MMMabcdeMM");
    assertEquals(padCenter("abcdef", 10, 'M'), "MMabcdefMM");
    assertEquals(padCenter("abcdefg", 10, 'M'), "MMabcdefgM");
    assertEquals(padCenter("abcdefgh", 10, 'M'), "MabcdefghM");
    assertEquals(padCenter("abcdefghi", 10, 'M'), "Mabcdefghi");
    assertEquals(padCenter("abcdefghij", 10, 'M'), "abcdefghij");
    assertEquals(padCenter("abcdefghijk", 10, 'M'), "abcdefghijk");
    assertEquals(padCenter("abcdefghijkl", 10, 'M'), "abcdefghijkl");
  }

  public void testFixedRight() throws Exception {
    assertEquals(toFixedLength(null, 5, 'M', ".."), "MMMMM");
    assertEquals(toFixedLength("", 5, 'M', ".."), "MMMMM");
    assertEquals(toFixedLength("a", 5, 'M', ".."), "aMMMM");
    assertEquals(toFixedLength("ab", 5, 'M', ".."), "abMMM");
    assertEquals(toFixedLength("abc", 5, 'M', ".."), "abcMM");
    assertEquals(toFixedLength("abcd", 5, 'M', ".."), "abcdM");
    assertEquals(toFixedLength("abcde", 5, 'M', ".."), "abcde");
    assertEquals(toFixedLength("abcdef", 5, 'M', ".."), "abc..");
    assertEquals(toFixedLength("abcdefg", 5, 'M', ".."), "abc..");
    assertEquals(toFixedLength("abcdefg", 5, 'M', "."), "abcd.");
    assertEquals(toFixedLength("abcdefg", 5, 'M', ""), "abcde");
    assertEquals(toFixedLength("abcdefg", 5, 'M', null), "abcde");
    assertEquals(toFixedLength("abcdefg", 5, 'M'), "abcde");
    assertEquals(toFixedLength("abcdefg", 4, 'M', ".."), "ab..");
    assertEquals(toFixedLength("abcdefg", 3, 'M', ".."), "a..");
    assertEquals(toFixedLength("abcdefg", 2, 'M', ".."), "..");
    assertEquals(toFixedLength("abcdefg", 1, 'M', ".."), ".");
    assertEquals(toFixedLength("abcdefg", 0, 'M', ".."), "");
  }
  public void testFixedLeft() throws Exception {
    assertEquals(toFixedLength(null, -5, 'M', ".."), "MMMMM");
    assertEquals(toFixedLength("", -5, 'M', ".."), "MMMMM");
    assertEquals(toFixedLength("a", -5, 'M', ".."), "MMMMa");
    assertEquals(toFixedLength("ab", -5, 'M', ".."), "MMMab");
    assertEquals(toFixedLength("abc", -5, 'M', ".."), "MMabc");
    assertEquals(toFixedLength("abcd", -5, 'M', ".."), "Mabcd");
    assertEquals(toFixedLength("abcde", -5, 'M', ".."), "abcde");
    assertEquals(toFixedLength("abcdef", -5, 'M', ".."), "..def");
    assertEquals(toFixedLength("abcdefg", -5, 'M', ".."), "..efg");
    assertEquals(toFixedLength("abcdefg", -5, 'M', "."), ".defg");
    assertEquals(toFixedLength("abcdefg", -5, 'M', ""), "cdefg");
    assertEquals(toFixedLength("abcdefg", -5, 'M', null), "cdefg");
    assertEquals(toFixedLength("abcdefg", -5, 'M'), "cdefg");
    assertEquals(toFixedLength("abcdefg", -4, 'M', ".."), "..fg");
    assertEquals(toFixedLength("abcdefg", -3, 'M', ".."), "..g");
    assertEquals(toFixedLength("abcdefg", -2, 'M', ".."), "..");
    assertEquals(toFixedLength("abcdefg", -1, 'M', ".."), ".");
    assertEquals(toFixedLength("abcdefg", 0, 'M', ".."), "");
  }

  public void testTruncateRight() throws Exception {
    assertEquals(truncateRight(null, 5), null);
    assertEquals(truncateRight("", 5), "");
    assertEquals(truncateRight("a", 5), "a");
    assertEquals(truncateRight("ab", 5), "ab");
    assertEquals(truncateRight("abc", 5), "abc");
    assertEquals(truncateRight("abcd", 5), "abcd");
    assertEquals(truncateRight("abcde", 5), "abcde");
    assertEquals(truncateRight("abcdef", 5), "abcde");
    assertEquals(truncateRight("abcdefg", 5), "abcde");
  }

  public void testTruncateLeft() throws Exception {
    assertEquals(truncateLeft(null, 5), null);
    assertEquals(truncateLeft("", 5), "");
    assertEquals(truncateLeft("a", 5), "a");
    assertEquals(truncateLeft("ab", 5), "ab");
    assertEquals(truncateLeft("abc", 5), "abc");
    assertEquals(truncateLeft("abcd", 5), "abcd");
    assertEquals(truncateLeft("abcde", 5), "abcde");
    assertEquals(truncateLeft("abcdef", 5), "bcdef");
    assertEquals(truncateLeft("abcdefg", 5), "cdefg");
  }

  public void testTruncateRight_ellipses() throws Exception {
    assertEquals(truncateRight(null,      5, "..."), null);
    assertEquals(truncateRight("",        5, "..."), "");
    assertEquals(truncateRight("a",       5, "..."), "a");
    assertEquals(truncateRight("ab",      5, "..."), "ab");
    assertEquals(truncateRight("abc",     5, "..."), "abc");
    assertEquals(truncateRight("abcd",    5, "..."), "abcd");
    assertEquals(truncateRight("abcde",   5, "..."), "abcde");
    assertEquals(truncateRight("abcdef",  5, "..."), "ab...");
    assertEquals(truncateRight("abcdefg", 5, "..."), "ab...");

    assertEquals(truncateRight("abcde",   5, ""), "abcde");
    assertEquals(truncateRight("abcdef",  5, ".."), "abc..");
    assertEquals(truncateRight("abcdef",  5, "."), "abcd.");
    assertEquals(truncateRight("abcdefg", 4, ".."), "ab..");
    assertEquals(truncateRight("abcdefg", 3, ".."), "a..");
    assertEquals(truncateRight("abcdefg", 2, ".."), "..");
    assertEquals(truncateRight("abcdefg", 1, ".."), ".");
    assertEquals(truncateRight("abcdefg", 0, ".."), "");
  }

  public void truncateLeft_ellipses() throws Exception {
    assertEquals(truncateLeft(null,       5, "..."), null);
    assertEquals(truncateLeft("",         5, "..."), "");
    assertEquals(truncateLeft("a",        5, "..."), "a");
    assertEquals(truncateLeft("ab",       5, "..."), "ab");
    assertEquals(truncateLeft("abc",      5, "..."), "abc");
    assertEquals(truncateLeft("abcd",     5, "..."), "abcd");
    assertEquals(truncateLeft("abcde",    5, "..."), "abcde");
    assertEquals(truncateLeft("abcdef",   5, "..."), "...ef");
    assertEquals(truncateLeft("abcdefg",  5, "..."), "...fg");

    assertEquals(truncateLeft("abcde",    5, ""), "abcde");
    assertEquals(truncateLeft("abcdef",   5, ".."), "..def");
    assertEquals(truncateLeft("abcdef",   5, "."), ".cdef");
    assertEquals(truncateLeft("abcdefg",  4, ".."), "..fg");
    assertEquals(truncateLeft("abcdefg",  3, ".."), "..g");
    assertEquals(truncateLeft("abcdefg",  2, ".."), "..");
    assertEquals(truncateLeft("abcdefg",  1, ".."), ".");
    assertEquals(truncateLeft("abcdefg",  0, ".."), "");
  }

  public void testRemoveLineSeparators() throws Exception {
    String sample = "Line 1\r\nLine 2\rLine 3\nLine 4\n\rLast Line";
    assertEquals(removeLineSeparators(sample), "Line 1Line 2\rLine 3Line 4\rLast Line");
  }

  public void testReplaceLineSeparators() throws Exception {
    String sample = "Line 1\r\nLine 2\rLine 3\nLine 4\n\rLast Line";
    assertEquals(replaceLineSeparators(sample, "|"), "Line 1|Line 2\rLine 3|Line 4|\rLast Line");
  }

  public void testReverse() {
    assertEquals(reverse(null), null);
    assertEquals(reverse(""), "");
    assertEquals(reverse("a"), "a");
    assertEquals(reverse("ab"), "ba");
    assertEquals(reverse("abc"), "cba");
    assertEquals(reverse("abcd"), "dcba");
    assertEquals(reverse("abc123xyz"), "zyx321cba");
    assertEquals(reverse("zyx321cba"), "abc123xyz");
  }

  public void testSubstring() {
    final String text = "abc123xyz";

    assertEquals(substring(null, 0, 9), null);
    assertEquals(substring(null, 9, 0), null);

    assertEquals(substring(text, 0, 0), "");
    assertEquals(substring(text, 9, 9), "");

    assertEquals(substring(text, 0, 9), "abc123xyz");
    assertEquals(substring(text, 9, 0), "zyx321cba");

    assertEquals(substring(text, 3, 6), "123");
    assertEquals(substring(text, 6, 3), "321");

    assertEquals(substring(text, 3, -3), "123");
    assertEquals(substring(text, -3, 3), "321");

    assertEquals(substring(text, -6, -3), "123");
    assertEquals(substring(text, -3, -6), "321");

    assertEquals(substring(text, 0, 12), "abc123xyz");
    assertEquals(substring(text, 12, 0), "zyx321cba");

    assertEquals(substring(text, 0, -12), "");
    assertEquals(substring(text, -12, 0), "");
  }

  public void testRemoveZeroDecimal() {
    assertEquals(removeZeroDecimal("1.00"), "1");
  }
}
