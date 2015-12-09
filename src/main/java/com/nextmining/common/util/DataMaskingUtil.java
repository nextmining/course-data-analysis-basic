package com.nextmining.common.util;

public class DataMaskingUtil {

  public static String encode(String data) {
      data = substitute(data, "b", "a");
      data = substitute(data, "Z", "b");

      data = shuffle(data, 0, 5);
      data = shuffle(data, 3, 7);

      return data;
  }

  public static String decode(String data) {
      data = substitute(data, "b", "Z");
      data = substitute(data, "a", "b");

      data = shuffle(data, 5, 0);
      data = shuffle(data, 7, 3);

      return data;
  }

  public static String shuffle(String data, int i, int j) {
      char chi = data.charAt(i);
      char chj = data.charAt(j);

      char[] arr = data.toCharArray();
      arr[i] = chj;
      arr[j] = chi;
      
      StringBuilder sb = new StringBuilder();
      for (char ch : arr) {
      	sb.append(ch);
      }

      return sb.toString();
  }

  public static String substitute(String data, String regex, String replacement) {
      data = data.replaceAll(regex, replacement);
      return data;
  }
}
