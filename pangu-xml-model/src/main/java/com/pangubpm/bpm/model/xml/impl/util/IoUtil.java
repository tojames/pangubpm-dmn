/**
 * Copyright (c) 2019-2030 panguBpm All rights reserved.
 * <p>
 * http://www.pangubpm.com/
 * <p>
 * （盘古BPM工作流平台）
 */
package com.pangubpm.bpm.model.xml.impl.util;

import com.pangubpm.bpm.model.xml.instance.DomDocument;

import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import java.io.*;

/**
 * @author pangubpm(pangubpm@139.com)
 * @author pangubpm( pangubpm@139.com )
 *
 */
public final class IoUtil {

  public static void closeSilently(Closeable closeable) {
    try {
      if (closeable != null) {
        closeable.close();
      }
    } catch (Exception e) {
      // ignored
    }
  }

  /**
   * Convert an {@link InputStream} to a {@link String}
   *
   * @param inputStream the {@link InputStream} to convert
   * @return the resulting {@link String}
   * @throws IOException
   */
  public static String getStringFromInputStream(InputStream inputStream) throws IOException {
    return getStringFromInputStream(inputStream, true);
  }

  /**
   * Convert an {@link InputStream} to a {@link String}
   *
   * @param inputStream the {@link InputStream} to convert
   * @param trim trigger if whitespaces are trimmed in the output
   * @return the resulting {@link String}
   * @throws IOException
   */
  private static String getStringFromInputStream(InputStream inputStream, boolean trim) throws IOException {
    BufferedReader bufferedReader = null;
    StringBuilder stringBuilder = new StringBuilder();
    try {
      bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
      String line;
      while ((line = bufferedReader.readLine()) != null) {
        if (trim) {
          stringBuilder.append(line.trim());
        }
        else {
          stringBuilder.append(line).append("\n");
        }
      }
    }
    finally {
      closeSilently(bufferedReader);
    }

    return stringBuilder.toString();
  }

  /**
   * Converts a {@link OutputStream} to an {@link InputStream} by coping the data directly.
   * WARNING: Do not use for large data (>100MB). Only for testing purpose.
   *
   * @param outputStream the {@link OutputStream} to convert
   * @return the resulting {@link InputStream}
   */
  public static InputStream convertOutputStreamToInputStream(OutputStream outputStream) {
    byte[] data = ((ByteArrayOutputStream) outputStream).toByteArray();
    return new ByteArrayInputStream(data);
  }

  /**
   * Converts a {@link DomDocument} to its String representation
   *
   * @param document  the XML document to convert
   */
  public static String convertXmlDocumentToString(DomDocument document) {
    StringWriter stringWriter = new StringWriter();
    StreamResult result = new StreamResult(stringWriter);
    transformDocumentToXml(document, result);
    return stringWriter.toString();
  }

  /**
   * Writes a {@link DomDocument} to an {@link OutputStream} by transforming the DOM to XML.
   *
   * @param document  the DOM document to write
   * @param outputStream  the {@link OutputStream} to write to
   */
  public static void writeDocumentToOutputStream(DomDocument document, OutputStream outputStream) {
    StreamResult result = new StreamResult(outputStream);
    transformDocumentToXml(document, result);
  }

  /**
   * Transforms a {@link DomDocument} to XML output.
   *
   * @param document  the DOM document to transform
   * @param result  the {@link StreamResult} to write to
   */
  public static void transformDocumentToXml(DomDocument document, StreamResult result) {
    TransformerFactory transformerFactory = TransformerFactory.newInstance();
    try {
      Transformer transformer = transformerFactory.newTransformer();
      transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
      transformer.setOutputProperty(OutputKeys.INDENT, "yes");
      transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

        transformer.transform(document.getDomSource(), result);
    } catch (TransformerConfigurationException e) {
      throw new ModelIoException("Unable to create a transformer for the model", e);
    } catch (TransformerException e) {
      throw new ModelIoException("Unable to transform model to xml", e);
    }
  }

}
