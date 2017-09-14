/*
 * Copyright (c) 2017, CipherGateway and/or its affiliates. All rights  reserved.
 *
 */
package netstate.string.average;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author kyle
 */
public class Solution {

  public static double solve(String str) {
    List<Integer> list = new ArrayList<>();
    if (str.length() < 1) {
      return 0.0;
    }
    int j = 0;
    list.add(1);
    for (int i = 1; i < str.length(); i++) {
      if (str.charAt(i) != str.charAt(i - 1)) {
        list.add(1);
        j++;
      } else {
        list.set(j, list.get(j) + 1);
      }
    }
    double a = 0;
    for(int i: list){
      a+=i;
    }
    return a/list.size();
  }

  public static void main(String[] args) {
    Scanner console = new Scanner(System.in);
    String str = console.nextLine();
    double x = solve(str);
    String r = new BigDecimal(x).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
    System.out.print(r);
  }
}
