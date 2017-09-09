/*
 * Copyright (c) 2017, CipherGateway and/or its affiliates. All rights  reserved.
 *
 */

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Candy {
  public static int candy(int[] ratings) {
    if (ratings == null || ratings.length == 0) return 0;
    int total = 1, prev = 1, countDown = 1;
    for (int i = 1; i < ratings.length; i++) {
      if (ratings[i] >= ratings[i - 1]) {
        if (countDown > 0) {
          total += countDown * (countDown + 1) / 2;
          if (countDown >= prev) total += countDown - prev + 1;
          countDown = 0;
          prev = 1;
        }
        prev = ratings[i] == ratings[i - 1] ? 1 : prev + 1;
        total += prev;
      } else {
        countDown++;
      }

    }
    if (countDown > 0) {
      total += countDown * (countDown + 1) / 2;
      if (countDown >= prev) total += countDown - prev + 1;
    }
    return total;
  }


  public static int candy2(int[] ratings) {
    if (ratings == null || ratings.length == 0) return 0;
    int[] nums = IntStream.iterate(1, i -> i)
        .limit(ratings.length)
        .toArray();
    for (int i = 0; i < ratings.length - 1; i++) {
      if (ratings[i + 1] > ratings[i]) nums[i + 1] = nums[i] + 1;
    }

    for (int i = ratings.length - 1; i > 0; i--) {
      if (ratings[i - 1] > ratings[i]) nums[i - 1] = Math.max(nums[i] + 1, nums[i - 1]);
    }
    return Arrays.stream(nums).sum();
  }


  public static int candy3(int[] arr) {
    if (arr == null || arr.length == 0) return 0;
    int index = nextMinIndex1(arr, 0);
    int res = rightCands(arr, 0, index++);
    int lbase = 1;
    int next = 0;
    int rcands = 1;
    int rbase = 0;
    while (index != arr.length) {
      if (arr[index] > arr[index - 1]) {
        //上坡
        res += ++lbase;
        index++;
      } else if (arr[index] < arr[index - 1]) {
        //下坡
        next  = nextMinIndex1(arr, index - 1);
        rcands = rightCands(arr, index - 1, next ++);
        rbase = next - index + 1;
        res += rcands + (rbase > lbase ? -lbase : -rbase);
        lbase = 1;
        index = next;
      } else {
        //平级
        res += 1;
        lbase = 1;
        index++;
      }
      System.out.println("next:"+index);
    }
    return res;
  }

  private static int nextMinIndex1(int[] arr, int start) {
    for (int i = start; i != arr.length - 1; i++) {
      if (arr[i] < arr[i + 1]) {
        return i;
      }

    }
    return arr.length - 1;
  }

  public static int rightCands(int[] arr, int left, int right) {
    int n = right - left + 1;
    return n + n * (n - 1) / 2;
  }


  public static void main(String[] args) {
    int[] a = new int[]{1, 2, 8, 7,5,4,3,2,1,6,4};//, 4, 5, 7, 2, 4, 3, 8};
    int result = candy(a);
    System.out.println(result);
    int result2 = candy2(a);
    System.out.println(result2);
    int result3 = candy3(a);
    System.out.println(result3);
    Set<String> s= new HashSet<>();
    s.contains("Df");
    List<String> sx= new LinkedList<>();
    sx.contains("df");
  }
}
