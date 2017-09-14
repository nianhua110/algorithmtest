/*
 * Copyright (c) 2017, CipherGateway and/or its affiliates. All rights  reserved.
 *
 */
package tencent.sheji;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int n = scanner.nextInt();
    int[] a = new int[n];
    for (int i = 0; i < n; i++) {
      a[i] = scanner.nextInt();
    }
    HashMap<Integer, List<Integer[]>> map = new HashMap<>();
    for (int i = 0; i < n; i++) {
      for (int j = i + 1; j < n; j++) {
        for (int k = j + 1; k < n; k++) {
          long s = (long) a[i] *(long) a[j] * (long)a[k];
          if(s>Integer.MAX_VALUE) continue;
          int p = (int)s;
          if (map.containsKey(p)) {
            map.get(p).add(new Integer[]{i, j, k});
          } else {
            List<Integer[]> l = new ArrayList<>();
            l.add(new Integer[]{i, j, k});
            map.put(p, l);
          }
        }
      }
    }

    int count = 0;
    for (int i = 0; i < n; i++) {
      if (map.containsKey(a[i])) {
        count += map.get(a[i]).size();
      }
    }

    System.out.println(count);
    System.out.println(map);
    System.out.println(1000000L*1000000*1000000);
    System.out.print(Integer.MAX_VALUE);
  }
}
/*
6
10 2 2 7 40 160
 */