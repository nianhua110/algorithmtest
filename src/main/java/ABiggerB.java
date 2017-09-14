/*
 * Copyright (c) 2017, CipherGateway and/or its affiliates. All rights  reserved.
 *
 */

import java.util.Scanner;

/**
 * @author kyle
 */
public class ABiggerB {
  public static void main(String[] args){
    Scanner scanner = new Scanner(System.in);
    int x = scanner.nextInt();
    for(int i=0; i< x; i++){
      long a = scanner.nextLong();
      long b = scanner.nextLong();
      long c = scanner.nextLong();
      System.out.println("Case #"+i+": "+(a+b>c));
    }
  }
}
