import java.util.Scanner;

public class BubbleSort {

  public static void bubbleSort(int[] data) {

    for (int i = 0; i < data.length - 1; i++) {// 控制趟数
      for (int j = 0; j < data.length - i - 1; j++) {

        if (data[j] > data[j + 1]) {
          int tmp = data[j];
          data[j] = data[j + 1];
          data[j + 1] = tmp;
        }
      }
    }

  }

  public static void main(String[] args) {
    Scanner s = new Scanner(System.in);
    int n= s.nextInt();
    int[] p=new int[n];
    for (int i = 0; i < n; i++) {
      p[i]=s.nextInt();
    }
      bubbleSort(p);// 冒泡排序

    for (int i = 0; i < p.length; i++) {
      System.out.print(p[i] + " ");
    }
  }

}



