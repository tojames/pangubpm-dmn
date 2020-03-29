/**
 * Copyright (c) 2019-2030 panguBpm All rights reserved.
 * <p>
 * http://www.pangubpm.com/
 * <p>
 * （盘古BPM工作流平台）
 */
package com.pangubpm.bpm.model.xml.impl.parser;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import com.pangubpm.bpm.model.xml.ModelInstance;
import com.pangubpm.bpm.model.xml.ModelValidationException;
import com.pangubpm.bpm.model.xml.impl.util.DomUtil;
import com.pangubpm.bpm.model.xml.impl.util.ReflectUtil;
import com.pangubpm.bpm.model.xml.instance.DomDocument;
import com.pangubpm.bpm.model.xml.instance.DomElement;
import org.xml.sax.SAXException;

/**
 * @author pangubpm(pangubpm@139.com)
 *
 */
public abstract class AbstractModelParser {

  private final DocumentBuilderFactory documentBuilderFactory;
  protected SchemaFactory schemaFactory;
  protected Map<String, Schema> schemas = new HashMap<String, Schema>();

  protected AbstractModelParser() {
    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    configureFactory(dbf);
    this.documentBuilderFactory = dbf;
  }

  /**
   * allows subclasses to configure the {@link DocumentBuilderFactory}.
   * @param dbf the factory to configure
   */
  protected void configureFactory(DocumentBuilderFactory dbf) {
    dbf.setValidating(true);
    dbf.setIgnoringComments(false);
    dbf.setIgnoringElementContentWhitespace(false);
    dbf.setNamespaceAware(true);
    protectAgainstXxeAttacks(dbf);
  }

  /**
   * Configures the DocumentBuilderFactory in a way, that it is protected against XML External Entity Attacks.
   * If the implementing parser does not support one or multiple features, the failed feature is ignored.
   * The parser might not protected, if the feature assignment fails.
   *
   * @see <a href="https://www.owasp.org/index.php/XML_External_Entity_(XXE)_Prevention_Cheat_Sheet">OWASP Information of XXE attacks</a>
   *
   * @param dbf The factory to configure.
   */
  private void protectAgainstXxeAttacks(final DocumentBuilderFactory dbf) {
    try {
      dbf.setFeature("http://xml.org/sax/features/external-general-entities", false);
    } catch (ParserConfigurationException ignored) {
    }

    try {
      dbf.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
    } catch (ParserConfigurationException ignored) {
    }

    try {
      dbf.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
    } catch (ParserConfigurationException ignored) {
    }

    dbf.setXIncludeAware(false);
    dbf.setExpandEntityReferences(false);
  }

  public ModelInstance parseModelFromStream(InputStream inputStream) {
    DomDocument document = null;

      document = DomUtil.parseInputStream(documentBuilderFactory, inputStream);

    validateModel(document);
    return createModelInstance(document);

  }

  public ModelInstance getEmptyModel() {
    DomDocument document = null;

      document = DomUtil.getEmptyDocument(documentBuilderFactory);

    return createModelInstance(document);
  }

  /**
   * Validate DOM document
   *
   * @param document the DOM document to validate
   */
  public void validateModel(DomDocument document) {

    Schema schema = getSchema(document);

    if (schema == null) {
      return;
    }

    Validator validator = schema.newValidator();
    try {
        validator.validate(document.getDomSource());
    } catch (IOException e) {
      throw new ModelValidationException("Error during DOM document validation", e);
    } catch (SAXException e) {
      throw new ModelValidationException("DOM document is not valid", e);
    }
  }

  protected Schema getSchema(DomDocument document) {
    DomElement rootElement = document.getRootElement();
    String namespaceURI = rootElement.getNamespaceURI();
    return schemas.get(namespaceURI);
  }

  protected void addSchema(String namespaceURI, Schema schema) {
    schemas.put(namespaceURI, schema);
  }

  protected Schema createSchema(String location, ClassLoader classLoader) {
    URL cmmnSchema = ReflectUtil.getResource(location, classLoader);
    try {
      return schemaFactory.newSchema(cmmnSchema);
    } catch (SAXException e) {
      throw new ModelValidationException("Unable to parse schema:" + cmmnSchema);
    }
  }

  protected abstract ModelInstance createModelInstance(DomDocument document);

}
