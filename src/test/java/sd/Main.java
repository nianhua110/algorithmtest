/*
 * Copyright (c) 2017, CipherGateway and/or its affiliates. All rights  reserved.
 *
 */
package sd;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
  public static void main(String[] args) {
    Set<String> set = new HashSet<String>();
    Scanner scanner = new Scanner(System.in);
    int n = Integer.parseInt(scanner.nextLine());
    for (int i = 0; i < n; i++) {
      String in = scanner.nextLine();
      set.add(in);
    }
    int m = Integer.parseInt(scanner.nextLine());
    for (int i = 0; i < m; i++) {
      String in = scanner.nextLine();
      set.remove(in);
    }

      set.stream().sorted().forEach(System.out::println);

  }
}
