/*
 * Copyright (c) 2017, CipherGateway and/or its affiliates. All rights  reserved.
 *
 */
package netstate;

import java.util.Scanner;

public class MagicCoin {

  public static String correctWay = "";

  public static void iter(int current, int target, String way) {

    if (current > target) {
      return;
    }
    if (current == target) {
      correctWay = way;
      return;
    }
    iter(2 * current + 1, target, way + "1");
    iter(2 * current + 2, target, way + "2");
  }

  public static String  dongtai(int target) {
    String[] s= new String[target+1];
    s[0]="";
    s[1]="1";
    s[2]="2";
    for (int i = 3; i < target+1; i++) {
      if ((i - 1) % 2 == 0) {
        s[i]=s[(i-1)/2]+"1";
      }
      ;
      if ((i - 2) % 2 == 0) {
        s[i]=s[(i-2)/2]+"2";
      }
    }
    return s[target];
  }

  public static void main(String[] args) {
    Scanner console = new Scanner(System.in);
    String str = console.nextLine();
    Integer n = Integer.parseInt(str);
    correctWay = "";
    iter(0, n, "");
    System.out.println(correctWay);
    System.out.println(dongtai(n));
  }
}
