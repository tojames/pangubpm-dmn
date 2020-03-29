/**
 * Copyright (c) 2019-2030 panguBpm All rights reserved.
 * <p>
 * http://www.pangubpm.com/
 * <p>
 * （盘古BPM工作流平台）
 */
package com.pangubpm.bpm.model.xml.impl.util;

import com.pangubpm.bpm.model.xml.instance.DomDocument;
import com.pangubpm.bpm.model.xml.instance.DomElement;

import java.util.HashMap;
import java.util.Map;

import static javax.xml.XMLConstants.XMLNS_ATTRIBUTE_NS_URI;

/**
 * @author pangubpm( pangubpm@139.com )
 */
public class XmlQName {

  public static final Map<String, String> KNOWN_PREFIXES;
  static
  {
    KNOWN_PREFIXES = new HashMap<String, String>();
    KNOWN_PREFIXES.put("http://activiti.org/bpmn", "pangu");
    KNOWN_PREFIXES.put("http://pangubpm.com/schema/1.0/dmn", "pangu");
    KNOWN_PREFIXES.put("http://www.omg.org/spec/BPMN/20100524/MODEL", "bpmn2");
    KNOWN_PREFIXES.put("http://www.omg.org/spec/BPMN/20100524/DI", "bpmndi");
    KNOWN_PREFIXES.put("http://www.omg.org/spec/DD/20100524/DI", "di");
    KNOWN_PREFIXES.put("http://www.omg.org/spec/DD/20100524/DC", "dc");
    KNOWN_PREFIXES.put(XMLNS_ATTRIBUTE_NS_URI, "");
  }

  protected DomElement rootElement;
  protected DomElement element;

  protected String localName;
  protected String namespaceUri;
  protected String prefix;

  public XmlQName(DomDocument document, String namespaceUri, String localName) {
    this(document, null, namespaceUri, localName);
  }

  public XmlQName(DomElement element, String namespaceUri, String localName) {
    this(element.getDocument(), element, namespaceUri, localName);
  }

  public XmlQName(DomDocument document, DomElement element, String namespaceUri, String localName) {
    this.rootElement = document.getRootElement();
    this.element = element;
    this.localName = localName;
    this.namespaceUri = namespaceUri;
    this.prefix = null;
  }

  public String getNamespaceUri() {
    return namespaceUri;
  }

  public String getLocalName() {
    return localName;
  }

  public String getPrefixedName() {
    if (prefix == null) {
        if (prefix == null) {
          this.prefix = determinePrefixAndNamespaceUri();
        }
    }
    return QName.combine(prefix, localName);
  }

  public boolean hasLocalNamespace() {
    if (element != null) {
      return element.getNamespaceURI().equals(namespaceUri);
    }
    else {
      return false;
    }
  }

  private String determinePrefixAndNamespaceUri() {
    if (namespaceUri != null) {
      if (rootElement != null && namespaceUri.equals(rootElement.getNamespaceURI())) {
        // global namespaces do not have a prefix or namespace URI
        return null;
      }
      else {
        // lookup for prefix
        String lookupPrefix = lookupPrefix();
        if (lookupPrefix == null && rootElement != null) {
          // if no prefix is found we generate a new one
          // search for known prefixes
         String knownPrefix = KNOWN_PREFIXES.get(namespaceUri);
          if (knownPrefix == null) {
            // generate namespace
            return rootElement.registerNamespace(namespaceUri);
          }
          else if (knownPrefix.isEmpty()) {
            // ignored namespace
            return null;
          }
          else {
            // register known prefix
            rootElement.registerNamespace(knownPrefix, namespaceUri);
            return knownPrefix;
          }
        }
        else {
          return lookupPrefix;
        }
      }
    }
    else {
      // no namespace so no prefix
      return null;
    }
  }

  private String lookupPrefix() {
    if (namespaceUri != null) {
      String lookupPrefix = null;
      if (element != null) {
        lookupPrefix = element.lookupPrefix(namespaceUri);
      }
      else if (rootElement != null) {
        lookupPrefix = rootElement.lookupPrefix(namespaceUri);
      }
      return lookupPrefix;
    }
    else {
      return null;
    }
  }

}
