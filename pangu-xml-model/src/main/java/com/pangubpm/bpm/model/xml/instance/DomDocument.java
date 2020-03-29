/**
 * Copyright (c) 2019-2030 panguBpm All rights reserved.
 * <p>
 * http://www.pangubpm.com/
 * <p>
 * （盘古BPM工作流平台）
 */
package com.pangubpm.bpm.model.xml.instance;

import java.util.List;

import javax.xml.transform.dom.DOMSource;

import org.w3c.dom.Document;

/**
 * Encapsulates a {@link Document}. Implementations of this interface must be thread-safe.
 *
 * @author pangubpm( pangubpm@139.com )
 */
public interface DomDocument {

  /**
   * Returns the root element of the document.
   *
   * @return the root element or null if non exists
   */
  DomElement getRootElement();

  /**
   * Sets the root element of the DOM document. Replace an existing if necessary.
   *
   * @param rootElement  the new root element
   */
  void setRootElement(DomElement rootElement);

  /**
   * Creates a new element in the dom document.
   *
   * @param namespaceUri  the namespaceUri of the new element
   * @param localName  the localName of the new element
   * @return the new DOM element
   */
  DomElement createElement(String namespaceUri, String localName);

  /**
   * Gets an element by its id.
   *
   * @param id  the id to search for
   * @return the element or null if no such element exists
   */
  DomElement getElementById(String id);

  /**
   * Gets all elements with the namespace and name.
   *
   * @param namespaceUri  the element namespaceURI to search for
   * @param localName  the element name to search for
   * @return the list of matching elements
   */
  List<DomElement> getElementsByNameNs(String namespaceUri, String localName);

  /**
   * Returns a new {@link DOMSource} of the document.
   *
   * Note that a {@link DOMSource} wraps the underlying {@link Document} which is
   * not thread-safe. Multiple DOMSources of the same document should be synchronized
   * by the calling application.
   *
   * @return the new {@link DOMSource}
   */
  DOMSource getDomSource();

  /**
   * Registers a new namespace with a generic prefix.
   *
   * @param namespaceUri  the namespaceUri of the new namespace
   * @return the used prefix
   */
  String registerNamespace(String namespaceUri);

  /**
   * Registers a new namespace for the prefix.
   *
   * @param prefix  the prefix of the new namespace
   * @param namespaceUri  the namespaceUri of the new namespace
   */
  void registerNamespace(String prefix, String namespaceUri);

  /**
   * Clones the DOM document.
   *
   * @return the cloned DOM document
   */
  DomDocument clone();

}
