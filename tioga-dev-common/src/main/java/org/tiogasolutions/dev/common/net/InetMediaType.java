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

package org.tiogasolutions.dev.common.net;

/**
 * User: harlan
 * Date: 3/10/12
 * Time: 5:56 PM
 */


public class InetMediaType {

  private final String type;
  private final String subType;
  private final String fileExtension;

  public static final InetMediaType UNDEFINED = applicationType("undefined");
  public static final String UNDEFINED_VALUE = "application/undefined";

  public static final InetMediaType ANY = type("*", "*");
  public static final String ANY_VALUE = "*/*";

  public static final InetMediaType APPLICATION_ANY = applicationType("*");
  public static final String APPLICATION_ANY_VALUE = "application/*";

  public static final InetMediaType APPLICATION_XML = applicationType("xml");
  public static final String APPLICATION_XML_VALUE = "application/xml";

  public static final InetMediaType APPLICATION_XHTML = applicationType("xhtml+xml", "xhtml");
  public static final String APPLICATION_XHTML_VALUE = "application/xhtml+xml";

  public static final InetMediaType APPLICATION_JSON = applicationType("json");
  public static final String APPLICATION_JSON_VALUE = "application/json";

  public static final InetMediaType APPLICATION_ZIP = applicationType("zip");
  public static final String APPLICATION_ZIP_VALUE = "application/zip";

  public static final InetMediaType APPLICATION_PDF = applicationType("pdf");
  public static final String APPLICATION_PDF_VALUE = "application/pdf";

  public static final InetMediaType TEXT_ANY = textType("*");
  public static final String TEXT_ANY_VALUE = "text/*";

  public static final InetMediaType TEXT_PLAIN = textType("plain", "text");
  public static final String TEXT_PLAIN_VALUE = "text/plain";

  public static final InetMediaType TEXT_HTML = textType("html");
  public static final String TEXT_HTML_VALUE = "text/html";

  public static final InetMediaType TEXT_XML = textType("xml");
  public static final String TEXT_XML_VALUE = "text/xml";

  public static final InetMediaType TEXT_CSS = textType("css");
  public static final String TEXT_CSS_VALUE = "text/css";

  public static final InetMediaType APPLICATION_JAVASCRIPT = applicationType("javascript");
  public static final String APPLICATION_JAVASCRIPT_VALUE = "application/javascript";

  public static final InetMediaType IMAGE_ANY = imageType("*");
  public static final String IMAGE_ANY_VALUE = "image/*";

  public static final InetMediaType IMAGE_GIF = imageType("gif");
  public static final String IMAGE_GIF_VALUE = "image/gif";

  public static final InetMediaType IMAGE_JPEG = imageType("jpeg");
  public static final String IMAGE_JPEG_VALUE = "image/jpeg";

  public static final InetMediaType IMAGE_TIFF = imageType("tiff");
  public static final String IMAGE_TIFF_VALUE = "image/tiff";

  public static final InetMediaType IMAGE_PNG = imageType("png");
  public static final String IMAGE_PNG_VALUE = "image/png";

  public static final InetMediaType IMAGE_ICON = imageType("icon");
  public static final String IMAGE_ICON_VALUE = "image/icon";

  public static final InetMediaType AUDIO_ANY = audioType("*");
  public static final String AUDIO_ANY_VALUE = "audio/*";

  public static final InetMediaType AUDIO_MPEG = audioType("mpeg");
  public static final String AUDIO_MPEG_VALUE = "audio/mpeg";

  public static final InetMediaType VIDEO_ANY = videoType("*");
  public static final String VIDEO_ANY_VALUE = "video/*";

  public static final InetMediaType VIDEO_MP4 = videoType("mp4");
  public static final String VIDEO_MP4_VALUE = "video/mp4";

  public static final InetMediaType MULTIPART_ANY = multipartType("*");
  public static final String MULTIPART_ANY_VALUE = "multipart/*";

  public static final InetMediaType MULTIPART_MIXED = multipartType("mixed");
  public static final String MULTIPART_MIXED_VALUE = "multipart/mixed";

  public static final InetMediaType MULTIPART_RELATED = multipartType("related");
  public static final String MULTIPART_RELATED_VALUE = "multipart/related";

  public static final InetMediaType MULTIPART_ALTERNATIVE = multipartType("alternative");
  public static final String MULTIPART_ALTERNATIVE_VALUE = "multipart/alternative";

  public static final InetMediaType MULTIPART_FORM_DATA = multipartType("form-data");
  public static final String MULTIPART_FORM_DATA_VALUE = "multipart/form-data";

  public static final InetMediaType MULTIPART_SIGNED = multipartType("signed");
  public static final String MULTIPART_SIGNED_VALUE = "multipart/signed";

  public static final InetMediaType MULTIPART_ENCRYPTED = multipartType("encrypted");
  public static final String MULTIPART_ENCRYPTED_VALUE = "multipart/encrypted";

  public static InetMediaType newFromJson(String mediaString) {
    return fromString(mediaString);

  }

  public static InetMediaType type(String type, String subType, String fileExtension) {
    // REVIEW - support caching different types?
    fileExtension = (fileExtension != null) ? fileExtension : subType;
    return new InetMediaType(type, subType, fileExtension);
  }

  public static InetMediaType fromString(String mediaString) {
    String[] split = mediaString.split("/");
    if (split.length != 2) {
      throw new IllegalArgumentException(String.format("Invalid mime type value: %s", mediaString));
    }
    return type(split[0], split[1]);
  }

  public static InetMediaType type(String type, String subType) {
    return type(type, subType, subType);
  }

  public static InetMediaType applicationType(String subType) {
    return type("application", subType);
  }

  public static InetMediaType applicationType(String subType, String fileExtension) {
    return type("application", subType, fileExtension);
  }

  public static InetMediaType multipartType(String subType) {
    return type("multipart", subType);
  }

  public static InetMediaType textType(String subType, String fileExtension) {
    return type("text", subType, fileExtension);
  }

  public static InetMediaType textType(String subType) {
    return type("text", subType);
  }

  public static InetMediaType imageType(String subType, String fileExtension) {
    return type("image", subType, fileExtension);
  }

  public static InetMediaType imageType(String subType) {
    return type("image", subType);
  }

  public static InetMediaType messageType(String subType, String fileExtension) {
    return type("message", subType, fileExtension);
  }

  public static InetMediaType audioType(String subType, String fileExtension) {
    return type("audio", subType, fileExtension);
  }

  public static InetMediaType audioType(String subType) {
    return type("audio", subType);
  }

  public static InetMediaType videoType(String subType, String fileExtension) {
    return type("text", subType, fileExtension);
  }

  public static InetMediaType videoType(String subType) {
    return type("video", subType);
  }

  private InetMediaType(String type, String subType, String fileExtension) {
    if (type == null) {
      throw new NullPointerException("Null type argument give to InetMediaType.");
    }
    if (subType == null) {
      throw new NullPointerException("Null subType argument give to InetMediaType.");
    }
    if (fileExtension == null) {
      throw new NullPointerException("Null fileExtension argument give to InetMediaType.");
    }
    this.type = type;
    this.subType = subType;
    this.fileExtension = fileExtension;
  }

  public String getMediaString() {
    return String.format("%s/%s", type, subType);
  }

  public String getType() {
    return type;
  }

  public String getSubType() {
    return subType;
  }

  public String getFileExtension() {
    return fileExtension;
  }

  public boolean isUndefined() {
    return this.equals(UNDEFINED);
  }

  public boolean isDefined() {
    return !this.equals(UNDEFINED);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    InetMediaType inetMediaType = (InetMediaType) o;

    if (!subType.equals(inetMediaType.subType)) return false;
    if (!type.equals(inetMediaType.type)) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = type.hashCode();
    result = 31 * result + subType.hashCode();
    result = 31 * result + fileExtension.hashCode();
    return result;
  }

  @Override
  public String toString() {
    return getMediaString();
  }
}
