/*
 * Copyright (c) 2017, CipherGateway and/or its affiliates. All rights  reserved.
 *
 */
package concurrency;

import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

import static java.lang.System.in;

/**
 * @author kyle
 */
public class MyRecursiveTask extends RecursiveTask<Integer> {
  @Override
  protected Integer compute() {
    System.out.println("compute time=" + System.currentTimeMillis());
    String s = null;
    s.toString();
    return 100;
  }


  public static void main(String[] args) {
    //Scanner scanner = new Scanner(in);
    //int n = scanner.nextInt();

    try {
      MyRecursiveTask task = new MyRecursiveTask();
      System.out.println(task.hashCode());
      ForkJoinPool pool = new ForkJoinPool();
      ForkJoinTask task2 = pool.submit(task);
      System.out.println(task2.hashCode() + "----" + task2.join());
      //System.out.println(task2.hashCode()+"----"+task2.join());
      Thread.sleep(500000);
    } catch (InterruptedException e) {
      System.out.println("exception a" );
      e.printStackTrace();

   }
   System.out.println("main end");
// catch (ExecutionException e) {
//      System.out.println("exception execution");
//      e.printStackTrace();
//    }

  }
}
