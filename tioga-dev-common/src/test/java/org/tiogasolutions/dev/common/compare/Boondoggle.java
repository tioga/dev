package org.tiogasolutions.dev.common.compare;

import java.util.*;

public class Boondoggle {
  
  private final byte byteValue;
  private final short	shortValue;
  private final int	intValue;
  private final long	longValue;
  private final float	floatValue;
  private final double	doubleValue;
  private final char	charValue;
  private final boolean	booleanValue;
  private final String stringValue;
  private final Object nullValue;

  private final Note firstNote;

  private final Note[] notesArray;
  private final List<Note> notesList;
  private final Map<String,Note> notesMap;

  public Boondoggle(byte byteValue, short shortValue, int intValue, long longValue, float floatValue, double doubleValue, char charValue, boolean booleanValue, String stringValue, Object nullValue, Note... notes) {
    this.byteValue = byteValue;
    this.shortValue = shortValue;
    this.intValue = intValue;
    this.longValue = longValue;
    this.floatValue = floatValue;
    this.doubleValue = doubleValue;
    this.charValue = charValue;
    this.booleanValue = booleanValue;
    this.stringValue = stringValue;
    this.nullValue = nullValue;

    this.notesArray = notes;
    this.notesList = new ArrayList<>(Arrays.asList(notes));

    notesMap = new LinkedHashMap<>();
    for (int i = 0; i < notes.length; i++) {
      String key = "key-"+i;
      notesMap.put(key, notes[i]);
    }

    this.firstNote = (notes.length == 0) ? null : notes[0];
  }

  public byte getByteValue() {
    return byteValue;
  }

  public short getShortValue() {
    return shortValue;
  }

  public int getIntValue() {
    return intValue;
  }

  public long getLongValue() {
    return longValue;
  }

  public float getFloatValue() {
    return floatValue;
  }

  public double getDoubleValue() {
    return doubleValue;
  }

  public char getCharValue() {
    return charValue;
  }

  public boolean isBooleanValue() {
    return booleanValue;
  }

  public String getStringValue() {
    return stringValue;
  }

  public Object getNullValue() {
    return nullValue;
  }

  public Note getFirstNote() {
    return firstNote;
  }

  public Note[] getNotesArray() {
    return notesArray;
  }

  public List<Note> getNotesList() {
    return notesList;
  }

  public Map<String, Note> getNotesMap() {
    return notesMap;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Boondoggle that = (Boondoggle) o;

    if (booleanValue != that.booleanValue) return false;
    if (byteValue != that.byteValue) return false;
    if (charValue != that.charValue) return false;
    if (Double.compare(that.doubleValue, doubleValue) != 0) return false;
    if (Float.compare(that.floatValue, floatValue) != 0) return false;
    if (intValue != that.intValue) return false;
    if (longValue != that.longValue) return false;
    if (shortValue != that.shortValue) return false;
    if (firstNote != null ? !firstNote.equals(that.firstNote) : that.firstNote != null) return false;
    if (!Arrays.equals(notesArray, that.notesArray)) return false;
    if (notesList != null ? !notesList.equals(that.notesList) : that.notesList != null) return false;
    if (notesMap != null ? !notesMap.equals(that.notesMap) : that.notesMap != null) return false;
    if (nullValue != null ? !nullValue.equals(that.nullValue) : that.nullValue != null) return false;
    if (stringValue != null ? !stringValue.equals(that.stringValue) : that.stringValue != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result;
    long temp;
    result = (int) byteValue;
    result = 31 * result + (int) shortValue;
    result = 31 * result + intValue;
    result = 31 * result + (int) (longValue ^ (longValue >>> 32));
    result = 31 * result + (floatValue != +0.0f ? Float.floatToIntBits(floatValue) : 0);
    temp = Double.doubleToLongBits(doubleValue);
    result = 31 * result + (int) (temp ^ (temp >>> 32));
    result = 31 * result + (int) charValue;
    result = 31 * result + (booleanValue ? 1 : 0);
    result = 31 * result + (stringValue != null ? stringValue.hashCode() : 0);
    result = 31 * result + (nullValue != null ? nullValue.hashCode() : 0);
    result = 31 * result + (firstNote != null ? firstNote.hashCode() : 0);
    result = 31 * result + (notesArray != null ? Arrays.hashCode(notesArray) : 0);
    result = 31 * result + (notesList != null ? notesList.hashCode() : 0);
    result = 31 * result + (notesMap != null ? notesMap.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "Boondoggle{" +
        "byteValue=" + byteValue +
        ", shortValue=" + shortValue +
        ", intValue=" + intValue +
        ", longValue=" + longValue +
        ", floatValue=" + floatValue +
        ", doubleValue=" + doubleValue +
        ", charValue=" + charValue +
        ", booleanValue=" + booleanValue +
        ", stringValue='" + stringValue + '\'' +
        ", nullValue=" + nullValue +
        ", firstNote=" + firstNote +
        ", notesArray=" + Arrays.toString(notesArray) +
        ", notesList=" + notesList +
        ", notesMap=" + notesMap +
        '}';
  }
}
