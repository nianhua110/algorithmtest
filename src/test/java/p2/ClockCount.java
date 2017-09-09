/*
 * Copyright (c) 2017, CipherGateway and/or its affiliates. All rights  reserved.
 *
 */
package p2;

import java.util.Scanner;

public class ClockCount {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int raw = Integer.parseInt(scanner.nextLine());
    int want = Integer.parseInt(scanner.nextLine());
    int result;
    if (want < raw) {
      int temp = Math.min(want + 360 - raw, raw -want );
      if (temp == raw-want) {
        temp = temp * -1;
      }
      result = temp;
    } else {
      int temp = Math.min(want - raw, raw + 360 - want);
      if (temp != want-raw) {
        temp = temp * -1;
      }
      result = temp;
    }
    System.out.println(result);
  }
}
