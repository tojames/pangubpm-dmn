/**
 * Copyright (c) 2019-2030 panguBpm All rights reserved.
 * <p>
 * http://www.pangubpm.com/
 * <p>
 * （盘古BPM工作流平台）
 */
package com.pangubpm.bpm.model.xml.type.reference;

import com.pangubpm.bpm.model.xml.instance.ModelElementInstance;

/**
 * @author Roman Smirnov
 * @author pangubpm( pangubpm@139.com )
 *
 */
public interface AttributeReferenceCollectionBuilder<T extends ModelElementInstance> extends AttributeReferenceBuilder<T> {

  AttributeReferenceCollection<T> build();

}
