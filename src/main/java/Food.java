/*
 * Copyright (c) 2017, CipherGateway and/or its affiliates. All rights  reserved.
 *
 */

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Food {

  public static int max = 0;

  public static void iter(int[][] profits, int i, int c, Set set) {
    if (i >= profits.length) {
      if (c > max) {
        max = c;

      }
      return;
    }
    for (int j = 0; j < profits[0].length; j++) {
      if (!set.contains(j)) {
        set.add(j);
        iter(profits, i + 1, c + profits[i][j], set);
        set.remove(j);
      }

    }
  }

  public static int dy(int[][] profits) {
    int[][] result = new int[profits.length][profits[0].length];
    for (int i = 0; i < profits.length; i++) {
      result[i][0] = profits[0][0];
    }

    for (int i = 1; i < profits[0].length; i++) {
      result[0][i] = profits[0][i] > result[0][i - 1] ? profits[0][i] : result[0][i - 1];
    }
    for (int i = 1; i < profits.length; i++) {
      for (int j = 1; j < profits[0].length; j++) {
        int t = Math.max(result[i - 1][j], result[i][j - 1]);
        int p = Math.max(result[i - 1][j - 1] + profits[i][j], t);
        result[i][j] = p;

      }
    }

    Arrays.stream(profits)
        .map(Arrays::toString)
        .forEachOrdered(System.out::println);
    Arrays.stream(result)
        .map(Arrays::toString)
        .forEachOrdered(System.out::println);
    return result[profits.length - 1][profits[0].length - 1];
  }

  public static int getMaxValue(int[] desk, int[][] customers) {
    int[][] profits = new int[desk.length][customers.length];
    for (int i = 0; i < desk.length; i++) {
      for (int j = 0; j < customers.length; j++) {
        if (customers[j][0] <= desk[i]) {
          profits[i][j] = customers[j][1];
        }
      }
    }
   return dy(profits);
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int n = scanner.nextInt();
    int m = scanner.nextInt();
    int[] desk = new int[n];
    int[][] customers = new int[m][2];
    for (int i = 0; i < n; i++) {
      desk[i] = scanner.nextInt();
    }

    for (int i = 0; i < m; i++) {
      customers[i][0] = scanner.nextInt();
      customers[i][1] = scanner.nextInt();
    }
    System.out.println(getMaxValue(desk, customers));
  }
}
/*
3 5
2 4 2
1 3
3 5
3 7
5 9
1 10

    Arrays.stream(profits)
        .map(Arrays::toString)
        .forEachOrdered(System.out::println);
    Arrays.stream(result)
        .map(Arrays::toString)
        .forEachOrdered(System.out::println);
3  3  3   3  10
3  8  10  10 13
3  8  10  10 20

4 6
12 1 4 7
11 3
3 10
35 10
5 9
12 10
6 7


 */