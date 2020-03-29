/**
 * Copyright (c) 2019-2030 panguBpm All rights reserved.
 * <p>
 * http://www.pangubpm.com/
 * <p>
 * （盘古BPM工作流平台）
 */
package com.pangubpm.bpm.model.xml.impl;

import com.pangubpm.bpm.model.xml.Model;

/**
 * A model build operation allows to participate in a model
 * build phase and perform some resolution steps once all
 * types are defined.
 *
 * @author pangubpm(pangubpm@139.com)
 *
 */
public interface ModelBuildOperation {

  void performModelBuild(Model model);

}
