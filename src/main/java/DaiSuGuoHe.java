/*
 * Copyright (c) 2017, CipherGateway and/or its affiliates. All rights  reserved.
 *
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class DaiSuGuoHe {
  public static int find(int[] a) {
    int[] f = new int[a.length + 1];
    f[0] = 0;
    for (int i = 1; i < a.length + 1; i++) {
      int temp = Integer.MAX_VALUE;
      for (int j = 0; j < i; j++) {
        if (a[j] + j >= i) {
          temp = Math.min(temp, f[j] + 1);
        }
      }
      if (temp == Integer.MAX_VALUE) {
        return -1;
      }
      f[i] = temp;
    }
    System.out.println(Arrays.toString(f));
    return f[a.length];
  }


  public static int find2(int[] spring) {
    int n = spring.length;
    int[] step = new int[n + 1];

    for (int i = 0; i < n + 1; i++) {
      step[i] = 10000000;
    }
    step[0] = 0;

    for (int i = 0; i < n; i++) {
      if (spring[i] == 0) continue;
      for (int j = 1; j <= spring[i]; j++) {
        if (i + j > n) break;
        if (i + j == n) {
          if (step[n] > step[i] + 1) step[n] = step[i] + 1;
          break;
        }
        if (i + j < n) {
          if (step[i + j] > step[i] + 1) step[i + j] = step[i] + 1;
        }
      }
    }
    if (step[n - 1] == 10000000) {
      return -1;
    }
    System.out.println(Arrays.toString(step));
    return step[n];
  }

  public static void main(String[] args) {
//    Scanner scanner = new Scanner(System.in);
//    int n = scanner.nextInt();
//    int[] q= new int[n];
//    for(int i=0; i< n; i++){
//      q[i]=scanner.nextInt();
//    }
    String str = "3 6 6 6 3 8 9 8 5 2 9 7 3 6 5 4 2 3 6 9 9 8 6 4 1 0 4 4 8 9 3 6 0 7 8 1 1 8 4";
    String[] strings = str.split(" ");
    int[] q1 = Arrays.stream(strings).mapToInt(Integer::valueOf).toArray();
//    int[]q =new int[]{}
    int[] q2 = new int[]{2, 0, 1, 1, 1};
    int[] q =q2;
    int r1 = find(q);
    System.out.println(r1);
    int r2 = find2(q);
    System.out.println(r2);
  }
}
