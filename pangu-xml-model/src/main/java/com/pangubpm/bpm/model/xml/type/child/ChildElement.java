/**
 * Copyright (c) 2019-2030 panguBpm All rights reserved.
 * <p>
 * http://www.pangubpm.com/
 * <p>
 * （盘古BPM工作流平台）
 */
package com.pangubpm.bpm.model.xml.type.child;

import com.pangubpm.bpm.model.xml.instance.ModelElementInstance;

import java.util.Collection;

/**
 * A single child element (child Element collection where {@link ChildElementCollection#getMaxOccurs()} returns 1.
 *
 * The {@link Collection#add(Object)} operation provided by this collection has special behavior: it will
 * replace an existing element if it exists.
 *
 * @author pangubpm(pangubpm@139.com)
 *
 * @param <T> the type of the child element
 */
public interface ChildElement<T extends ModelElementInstance> extends ChildElementCollection<T> {

  /**
   * Sets the child element, potentially replacing an existing child element.
   *
   * @param element the parent element of the child element
   * @param newChildElement the new child element to set
   */
  void setChild(ModelElementInstance element, T newChildElement);

  /**
   * Returns the child element.
   *
   * @param element the parent element of the child element
   * @return the child element of the parent, or null if not exist
   */
  T getChild(ModelElementInstance element);

  /**
   * Removes the child element.
   *
   * @param element  the parent element of the child element
   * @return true if the child was remove otherwise false
   */
  boolean removeChild(ModelElementInstance element);
}
