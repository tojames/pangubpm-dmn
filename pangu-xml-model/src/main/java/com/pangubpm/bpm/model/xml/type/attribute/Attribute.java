/**
 * Copyright (c) 2019-2030 panguBpm All rights reserved.
 * <p>
 * http://www.pangubpm.com/
 * <p>
 * （盘古BPM工作流平台）
 */
package com.pangubpm.bpm.model.xml.type.attribute;

import com.pangubpm.bpm.model.xml.instance.ModelElementInstance;
import com.pangubpm.bpm.model.xml.type.ModelElementType;
import com.pangubpm.bpm.model.xml.type.reference.Reference;

import java.util.List;

/**
 * @author meyerd
 *
 * @param <T>
 */
public interface Attribute<T> {

  /**
   * returns the value of the attribute.
   *
   * @return the value of the attribute.
   */
  T getValue(ModelElementInstance modelElement);

  /**
   * sets the value of the attribute.
   *
   * @param value the value of the attribute.
   */
  void setValue(ModelElementInstance modelElement, T value);

  /**
   * sets the value of the attribute.
   *
   * @param value the value of the attribute.
   * @param withReferenceUpdate true to update id references in other elements, false otherwise
   */
  void setValue(ModelElementInstance modelElement, T value, boolean withReferenceUpdate);

  T getDefaultValue();

  boolean isRequired();

  /**
   * @return the namespaceUri
   */
  String getNamespaceUri();

  /**
   * @return the attributeName
   */
  String getAttributeName();

  boolean isIdAttribute();

  ModelElementType getOwningElementType();

  List<Reference<?>> getIncomingReferences();

  List<Reference<?>> getOutgoingReferences();

}
