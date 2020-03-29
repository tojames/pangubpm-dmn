/**
 * Copyright (c) 2019-2030 panguBpm All rights reserved.
 * <p>
 * http://www.pangubpm.com/
 * <p>
 * （盘古BPM工作流平台）
 */
package com.pangubpm.bpm.dmn.feel.impl.juel.el;

import java.math.BigDecimal;
import java.math.BigInteger;
import javax.el.ELException;

import com.pangubpm.bpm.dmn.feel.impl.juel.FeelEngineLogger;
import com.pangubpm.bpm.dmn.feel.impl.juel.FeelLogger;

import de.odysseus.el.misc.TypeConverterImpl;

public class FeelTypeConverter extends TypeConverterImpl {

  public static final FeelEngineLogger LOG = FeelLogger.ENGINE_LOGGER;

  @Override
  protected Boolean coerceToBoolean(Object value) {
    if (value instanceof Boolean) {
      return (Boolean) value;
    }
    else {
      throw LOG.unableToConvertValue(value, Boolean.class);
    }
  }

  @Override
  protected BigDecimal coerceToBigDecimal(Object value) {
    if (value instanceof BigDecimal) {
      return (BigDecimal)value;
    }
    else if (value instanceof BigInteger) {
      return new BigDecimal((BigInteger)value);
    }
    else if (value instanceof Number) {
      return new BigDecimal(((Number)value).doubleValue());
    }
    else {
      throw LOG.unableToConvertValue(value, BigDecimal.class);
    }
  }

  @Override
  protected BigInteger coerceToBigInteger(Object value) {
    if (value instanceof BigInteger) {
      return (BigInteger)value;
    }
    else if (value instanceof BigDecimal) {
      return ((BigDecimal)value).toBigInteger();
    }
    else if (value instanceof Number) {
      return BigInteger.valueOf(((Number)value).longValue());
    }
    else {
      throw LOG.unableToConvertValue(value, BigInteger.class);
    }
  }

  @Override
  protected Double coerceToDouble(Object value) {
    if (value instanceof Double) {
      return (Double)value;
    }
    else if (value instanceof Number) {
      return ((Number) value).doubleValue();
    }
    else {
      throw LOG.unableToConvertValue(value, Double.class);
    }
  }

  @Override
  protected Long coerceToLong(Object value) {
    if (value instanceof Long) {
      return (Long)value;
    }
    else if (value instanceof Number && isLong((Number) value)) {
      return ((Number) value).longValue();
    }
    else {
      throw LOG.unableToConvertValue(value, Long.class);
    }
  }

  @Override
  protected String coerceToString(Object value) {
    if (value instanceof String) {
      return (String)value;
    }
    else if (value instanceof Enum<?>) {
      return ((Enum<?>)value).name();
    }
    else {
      throw LOG.unableToConvertValue(value, String.class);
    }
  }

  @Override
  public <T> T convert(Object value, Class<T> type) throws ELException {
    try {
      return super.convert(value, type);
    }
    catch (ELException e) {
      throw LOG.unableToConvertValue(value, type, e);
    }
  }

  protected boolean isLong(Number value) {
    double doubleValue = value.doubleValue();
    return doubleValue == (long) doubleValue;
  }

}
