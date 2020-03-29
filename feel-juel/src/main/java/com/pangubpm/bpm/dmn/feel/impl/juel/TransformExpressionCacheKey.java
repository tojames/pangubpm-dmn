/**
 * Copyright (c) 2019-2030 panguBpm All rights reserved.
 * <p>
 * http://www.pangubpm.com/
 * <p>
 * （盘古BPM工作流平台）
 */
package com.pangubpm.bpm.dmn.feel.impl.juel;

public class TransformExpressionCacheKey {

  protected final String expression;
  protected final String inputName;

  public TransformExpressionCacheKey(String expression, String inputName) {
    this.expression = expression;
    this.inputName = inputName;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((expression == null) ? 0 : expression.hashCode());
    result = prime * result + ((inputName == null) ? 0 : inputName.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    TransformExpressionCacheKey other = (TransformExpressionCacheKey) obj;
    if (expression == null) {
      if (other.expression != null)
        return false;
    } else if (!expression.equals(other.expression))
      return false;
    if (inputName == null) {
      if (other.inputName != null)
        return false;
    } else if (!inputName.equals(other.inputName))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "TransformExpressionCacheKey [expression=" + expression + ", inputName=" + inputName + "]";
  }

}
