/*
 * Copyright (c) 2017, CipherGateway and/or its affiliates. All rights  reserved.
 *
 */

/**
 * @author kyle
 */
public class Food {

  public static int max = 0;

  public static void iter(int[][] profits, int i, int c) {
    if (i >= profits.length - 1) {
      if (c > max) max = c;
    }
    for (int j = 0; i < profits[0].length; j++) {
      iter(profits, i + 1, c + profits[i][j]);
    }
  }

  public static void getMaxValue(int[] desk, int[][] customers) {
    int[][] profits = new int[desk.length][customers.length];
    for (int i = 0; i < desk.length; i++) {
      for (int j = 0; j < customers.length; j++) {
        if (customers[j][0] <= desk[i]) {
          profits[i][j] = customers[j][1];
        }
      }
    }
    iter(profits, 0, 0);
  }

  public static void main(String[] args) {

  }
}
