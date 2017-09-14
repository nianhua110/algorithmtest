/*
 * Copyright (c) 2017, CipherGateway and/or its affiliates. All rights  reserved.
 *
 */

import java.util.Scanner;

/**
 * @author kyle
 */
public class BuyApple {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int n= scanner.nextInt();
    int k=n;
    while (n%8!=0){
      n=n-6;
    }
    int a = n/8+(k-n)/6;
    System.out.println(a);
  }
}
