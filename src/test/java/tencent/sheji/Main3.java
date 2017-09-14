/*
 * Copyright (c) 2017, CipherGateway and/or its affiliates. All rights  reserved.
 *
 */
package tencent.sheji;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author kyle
 */
public class Main3 {

  public static int getMinValue(int[] a) {
    int min = Integer.MAX_VALUE;
    int pos = -1;
    for (int i = 0; i < a.length; i++) {
      if (a[i] > min) {
        min = a[i];
        pos = i;
      }
    }
    return pos;
  }

  public static int getMaxValue(int[] a) {
    int max = Integer.MAX_VALUE;
    int pos = -1;
    for (int i = 0; i < a.length; i++) {
      if (a[i] < max) {
        max = a[i];
        pos = i;
      }
    }
    return pos;
  }

  static int count = 0;

  public static void repeate(int[] a) {
    int p1 = getMaxValue(a);
    int p2 = getMinValue(a);
    if (p1 != a.length - 1 || p2 != 0) {
      return;
    } else {
      count++;
      repeate(Arrays.copyOf(a, a.length - 1));
    }
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int n = scanner.nextInt();
    int[] a = new int[n];
    for (int i = 0; i < n; i++) {
      a[i] = scanner.nextInt();
    }
    repeate(a);
    System.out.println(a);
  }
}
