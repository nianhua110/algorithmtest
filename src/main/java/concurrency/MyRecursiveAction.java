/*
 * Copyright (c) 2017, CipherGateway and/or its affiliates. All rights  reserved.
 *
 */
package concurrency;

import java.util.Scanner;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

import static java.lang.System.in;

/**
 * @author kyle
 */
public class MyRecursiveAction extends RecursiveAction {
  private int begin;
  private int end;

  public MyRecursiveAction(int begin, int end) {
    super();
    this.begin = begin;
    this.end = end;
  }

  @Override
  protected void compute() {
    System.out.println(Thread.currentThread().getName() + "--------");
    if (end - begin > 2) {
      int mid = (end + begin) / 2;
      MyRecursiveAction left = new MyRecursiveAction(begin, mid);
      MyRecursiveAction right = new MyRecursiveAction(mid + 1, end);
      this.invokeAll(left, right);
    } else {
      System.out.println("打印组合为：" + begin + "-" + end);
    }
  }

  public static void main(String[] args) throws InterruptedException {
    //Scanner scanner = new Scanner(in);
    //int n = scanner.nextInt();
    ForkJoinPool pool =new ForkJoinPool();
    pool.submit(new MyRecursiveAction(1,10));
    Thread.sleep(50000);
  }
}
