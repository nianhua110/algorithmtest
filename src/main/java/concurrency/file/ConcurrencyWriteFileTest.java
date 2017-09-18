/*
 * Copyright (c) 2017, CipherGateway and/or its affiliates. All rights  reserved.
 *
 */
package concurrency.file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author kyle
 */
public class ConcurrencyWriteFileTest {
  public static class Inner {
    Path path = Paths.get(System.getProperty("user.dir") + "/temp1");
    ReadWriteLock lock = new ReentrantReadWriteLock();

    public void write(String s) throws IOException {

      try {
        lock.writeLock().lock();
        Files.write(path, s.getBytes());
      } finally {
        lock.writeLock().unlock();
      }
    }

    public String read() throws IOException {
      return Files.readAllLines(path).get(0);
    }
  }

  public static void main(String[] args) {
    NoConcurrencyWriteFileTest.Inner inner = new NoConcurrencyWriteFileTest.Inner();
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
          for (int j = 1; j < sb.length(); j++) {
            if(sb.charAt(j) != sb.charAt(j-1)){
              System.out.println("error");
            }
          }
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
