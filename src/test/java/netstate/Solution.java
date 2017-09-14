/*
 * Copyright (c) 2017, CipherGateway and/or its affiliates. All rights  reserved.
 *
 */
package netstate;

import java.util.Scanner;
import java.util.Stack;

/**
 * @author kyle
 */
public class Solution {
  public static boolean isRight(String str){
    Stack<Character> stack = new Stack<>();
    for (int i=0; i< str.length(); i++){
      if(str.charAt(i) == '('){
        stack.push('(');
      }else {
        if(stack.isEmpty()){
          return  false;
        }else {
          stack.pop();
        }
      }
    }
    return  stack.isEmpty();
  }
  public static int iter(String str) {
    int count=0;
    if(str.length()<1){
      return 1;
    }
    for (int i = 0; i < str.length(); i++) {
      if (str.charAt(i) == '(') {
        int len = str.length();
        String str1 = str.substring(i + 1, str.length());
        for (int j = i ; j < str1.length(); j++) {
          if (str1.charAt(j) == ')') {
            int len2 = str1.length();
            String trim = str1.substring(0, j) + str1.substring(j + 1, str1.length());
            boolean b = isRight(trim);
            if(b){
              count += iter(trim);
            }
          }
        }
      }
    }
    return  count;
  }

  public static void main(String[] args) {
    Scanner console =  new Scanner(System.in);
    String str = console.nextLine();
    int x= iter(str);
    System.out.println(x);
  }
}