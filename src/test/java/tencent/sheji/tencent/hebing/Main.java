/*
 * Copyright (c) 2017, CipherGateway and/or its affiliates. All rights  reserved.
 *
 */
package tencent.sheji.tencent.hebing;

import java.util.ArrayList;
import java.util.Scanner;
/**
 * @author kyle
 */
public class Main {

  public static  void solve(int[] a, ArrayList<Integer[]> list){
    for(int i=0; i< a.length; i++){
      for (int j =i; j< a.length;j++){

        list.add(new Integer[]{a[i]+a[j], a[i]*a[j]});
      }
    }
  }
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int n = scanner.nextInt();
    int[] a = new int[n];
    for (int i = 0; i < n; i++) {
      a[i] = scanner.nextInt();
    }
  }
}
