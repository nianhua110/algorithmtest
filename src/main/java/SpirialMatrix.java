/*
 * Copyright (c) 2017, CipherGateway and/or its affiliates. All rights  reserved.
 *
 */
/*
按照顺时针打印矩阵
 */
public class SpirialMatrix {

  private void printCircle(int[][] a, int startX, int startY, int endX, int endY) {
    for (int i = startX; i < endX; i++) {
      System.out.println(a[startY][i]);
    }

    if (startY == endY) {
      System.out.println(a[startY][endX]);
      return;
    }
    for (int j = startY; j < endY; j++) {
      System.out.println(a[j][endX]);
    }

    if (startX == endX) {
      System.out.println(a[endY][startX]);
      return;
    }

    for (int i = endX; i > startX; i--) {
      System.out.println(a[endY][i]);
    }

    for (int j = endY; j > startY; j--) {
      System.out.println(a[j][startX]);
    }
  }


  public void printSpirialMatrix(int[][] a) {
    if (a == null || a.length == 0) {
      return;
    }
    int startX = 0;
    int startY = 0;
    int endX = a[0].length - 1;
    int endY = a.length - 1;
    while (startX <= endX && startY <= endY) {
      printCircle(a, startX, startY, endX, endY);
      startX++;
      startY++;
      endX--;
      endY--;
    }
  }


  public static void main(String[] args) {
    SpirialMatrix spirialMatrix = new SpirialMatrix();
    int[][] a = new int[][]{
        {3, 4, 5, 2},
        {8, 34, 125, 99},
        {6, 7, 4, 9}
    };
   int[][] b=new int[][]{
      {1},
      {2}
    };
    int[][] c=new int[][]{
        {2}
    };
    int[][] d = new int[][]{
        {6, 7, 4, 9}
    };
    System.out.println("a result: ");
    spirialMatrix.printSpirialMatrix(a);
    System.out.println("b result: ");
    spirialMatrix.printSpirialMatrix(b);
    System.out.println("c result: ");
    spirialMatrix.printSpirialMatrix(c);
    System.out.println("d result: ");
    spirialMatrix.printSpirialMatrix(d);
  }
}
