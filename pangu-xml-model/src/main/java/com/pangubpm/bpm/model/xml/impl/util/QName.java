/**
 * Copyright (c) 2019-2030 panguBpm All rights reserved.
 * <p>
 * http://www.pangubpm.com/
 * <p>
 * （盘古BPM工作流平台）
 */
package com.pangubpm.bpm.model.xml.impl.util;

/**
 * @author pangubpm(pangubpm@139.com)
 *
 */
public class QName {

  private final String qualifier;
  private final String localName;

  public QName(String localName) {
    this(null, localName);
  }

  public QName(String qualifier, String localName) {
    this.localName = localName;
    this.qualifier = qualifier;
  }

  public String getQualifier() {
    return qualifier;
  }

  public String getLocalName() {
    return localName;
  }

  public static QName parseQName(String identifier) {
    String qualifier;
    String localName;

    String[] split = identifier.split(":", 2);
    if(split.length == 2) {
      qualifier = split[0];
      localName = split[1];
    } else {
      qualifier = null;
      localName = split[0];
    }

    return new QName(qualifier, localName);
  }

  @Override
  public String toString() {
    return combine(qualifier, localName);
  }

  public static String combine(String qualifier, String localName) {
    if (qualifier == null || qualifier.isEmpty()) {
      return localName;
    }
    else {
      return qualifier + ":" + localName;
    }
  }

  @Override
  public int hashCode() {
    int prime = 31;
    int result = 1;
    result = prime * result + ((localName == null) ? 0 : localName.hashCode());
    result = prime * result + ((qualifier == null) ? 0 : qualifier.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    QName other = (QName) obj;
    if (localName == null) {
      if (other.localName != null) {
        return false;
      }
    } else if (!localName.equals(other.localName)) {
      return false;
    }
    if (qualifier == null) {
      if (other.qualifier != null) {
        return false;
      }
    } else if (!qualifier.equals(other.qualifier)) {
      return false;
    }
    return true;
  }


}
