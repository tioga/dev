package org.tiogasolutions.dev.common.net;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.tiogasolutions.dev.common.net.InetMediaType.*;

@Test
public class InetMediaTypeTest {

  public void testStatics() throws Exception {

    assertEquals(UNDEFINED.getMediaString(), UNDEFINED_VALUE);
    assertEquals(ANY.getMediaString(), ANY_VALUE);

    assertEquals(APPLICATION_ANY.getMediaString(), APPLICATION_ANY_VALUE);
    assertEquals(APPLICATION_XML.getMediaString(), APPLICATION_XML_VALUE);
    assertEquals(APPLICATION_XHTML.getMediaString(), APPLICATION_XHTML_VALUE);
    assertEquals(APPLICATION_JSON.getMediaString(), APPLICATION_JSON_VALUE);
    assertEquals(APPLICATION_ZIP.getMediaString(), APPLICATION_ZIP_VALUE);
    assertEquals(APPLICATION_PDF.getMediaString(), APPLICATION_PDF_VALUE);
    assertEquals(APPLICATION_JAVASCRIPT.getMediaString(), APPLICATION_JAVASCRIPT_VALUE);

    assertEquals(TEXT_ANY.getMediaString(), TEXT_ANY_VALUE);
    assertEquals(TEXT_PLAIN.getMediaString(), TEXT_PLAIN_VALUE);
    assertEquals(TEXT_HTML.getMediaString(), TEXT_HTML_VALUE);
    assertEquals(TEXT_XML.getMediaString(), TEXT_XML_VALUE);
    assertEquals(TEXT_CSS.getMediaString(), TEXT_CSS_VALUE);

    assertEquals(IMAGE_ANY.getMediaString(), IMAGE_ANY_VALUE);
    assertEquals(IMAGE_GIF.getMediaString(), IMAGE_GIF_VALUE);
    assertEquals(IMAGE_JPEG.getMediaString(), IMAGE_JPEG_VALUE);
    assertEquals(IMAGE_TIFF.getMediaString(), IMAGE_TIFF_VALUE);
    assertEquals(IMAGE_PNG.getMediaString(), IMAGE_PNG_VALUE);
    assertEquals(IMAGE_ICON.getMediaString(), IMAGE_ICON_VALUE);

    assertEquals(AUDIO_ANY.getMediaString(), AUDIO_ANY_VALUE);
    assertEquals(AUDIO_MPEG.getMediaString(), AUDIO_MPEG_VALUE);

    assertEquals(VIDEO_ANY.getMediaString(), VIDEO_ANY_VALUE);
    assertEquals(VIDEO_MP4.getMediaString(), VIDEO_MP4_VALUE);

    assertEquals(MULTIPART_ANY.getMediaString(), MULTIPART_ANY_VALUE);
    assertEquals(MULTIPART_MIXED.getMediaString(), MULTIPART_MIXED_VALUE);
    assertEquals(MULTIPART_RELATED.getMediaString(), MULTIPART_RELATED_VALUE);
    assertEquals(MULTIPART_ALTERNATIVE.getMediaString(), MULTIPART_ALTERNATIVE_VALUE);
    assertEquals(MULTIPART_FORM_DATA.getMediaString(), MULTIPART_FORM_DATA_VALUE);
    assertEquals(MULTIPART_SIGNED.getMediaString(), MULTIPART_SIGNED_VALUE);
    assertEquals(MULTIPART_ENCRYPTED.getMediaString(), MULTIPART_ENCRYPTED_VALUE);
  }

}