/*
 * Copyright (c) 2017, CipherGateway and/or its affiliates. All rights  reserved.
 *
 */

import java.util.HashMap;
import java.util.Scanner;

public class CountNumber {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    HashMap<Integer, Integer> hashMap = new HashMap<>();
    String str = scanner.nextLine();
    String[] numbers = str.split(" ");

    for (String s : numbers) {
      int i = Integer.parseInt(s);
      if (hashMap.containsKey(i)) {
        hashMap.put(i, hashMap.get(i) + 1);
      } else {
        hashMap.put(i, 1);
      }

    }

    for (Integer i : hashMap.keySet()) {
      System.out.println(i + "(" + hashMap.get(i) + ")");
    }
  }
}
