/**
 * Copyright (c) 2019-2030 panguBpm All rights reserved.
 * <p>
 * http://www.pangubpm.com/
 * <p>
 * （盘古BPM工作流平台）
 */
package com.pangubpm.bpm.model.xml.impl.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author pangubpm( pangubpm@139.com )
 */
public final class StringUtil {

  private static final Pattern pattern = Pattern.compile("(\\w[^,]*)|([#$]\\{[^}]*})");

  /**
   * Splits a comma separated list in to single Strings. The list can
   * contain expressions with commas in it.
   *
   * @param text  the comma separated list
   * @return the Strings of the list or an empty List if text is empty or null
   */
  public static List<String> splitCommaSeparatedList(String text) {
    if (text == null || text.isEmpty()) {
      return Collections.emptyList();
    }
    Matcher matcher = pattern.matcher(text);
    List<String> parts = new ArrayList<String>();
    while(matcher.find()) {
      parts.add(matcher.group().trim());
    }
    return parts;
  }

  /**
   * Joins a list of Strings to a comma separated single String.
   *
   * @param list  the list to join
   * @return the resulting comma separated string or null if the list is null
   */
  public static String joinCommaSeparatedList(List<String> list) {
    return joinList(list, ", ");
  }

  public static List<String> splitListBySeparator(String text, String separator) {
    String[] result = new String[]{};
    if (text != null) {
      result = text.split(separator);
    }
    return new ArrayList<String>(Arrays.asList(result));
  }

  public static String joinList(List<String> list, String separator) {
    if (list == null) {
      return null;
    }

    int size = list.size();
    if (size == 0) {
      return "";
    }
    else if (size == 1) {
      return list.get(0);
    }
    else {
      StringBuilder builder = new StringBuilder(size * 8);
      builder.append(list.get(0));
      for (Object element : list.subList(1, size)) {
        builder.append(separator);
        builder.append(element);
      }
      return builder.toString();
    }
  }
}
