/*
 * Copyright (c) 2017, CipherGateway and/or its affiliates. All rights  reserved.
 *
 */
package concurrency.file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
 * 无锁控制下的并发读写
 *
 * @author kyle
 */
public class NoConcurrencyWriteFileTest {
  public static class Inner {
    Path path = Paths.get(System.getProperty("user.dir") + "/temp");

    public void write(String s) throws IOException {
      Files.write(path, s.getBytes());
    }

    public String read() throws IOException {
      return Files.readAllLines(path).get(0);
    }
  }

  public static void main(String[] args) {
    Inner inner = new Inner();
    int n = 10_000;
    CyclicBarrier barrier = new CyclicBarrier(n);
    CountDownLatch latch = new CountDownLatch(n);
    for (int i = 0; i < n; i++) {
      final StringBuilder sb = new StringBuilder();
      for (int j = 0; j < 10_000; j++) {
        sb.append(i%9);
      }
      new Thread(() -> {
        try {
          barrier.await();
          inner.write(sb.toString());
          latch.countDown();
        } catch (InterruptedException e) {
          e.printStackTrace();
        } catch (BrokenBarrierException e) {
          e.printStackTrace();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }).start();

    }
    try {
      long start = System.currentTimeMillis();
      latch.await();
      long end = System.currentTimeMillis();
      System.out.println("cost: " + (end - start));

      String result = inner.read();
      for (int i = 1; i < result.length(); i++) {
        if (result.charAt(i) != result.charAt(i - 1)) {
          throw new Exception("error, not equal");
        }

      }
      System.out.println("success");
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (Exception e) {
      System.out.println("error ,not, equal");
    }

  }
}
