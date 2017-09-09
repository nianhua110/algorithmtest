/*
 * Copyright (c) 2017, CipherGateway and/or its affiliates. All rights  reserved.
 *
 */

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ThreadTest {

   ReadWriteLock lock = new ReentrantReadWriteLock();
   AtomicInteger i = new AtomicInteger(0);

  public  int getValue() throws InterruptedException {
    try {
      if (!lock.writeLock().tryLock()) {
        return -1;
      }
     // Thread.sleep(1);
      return i.getAndIncrement();
    } finally {
      lock.writeLock().unlock();
    }
  }

  public  int getValue2() throws InterruptedException {

      if (!lock.writeLock().tryLock()) {
        return -1;
      }
      try{
        return i.getAndIncrement();

      }finally {
        lock.writeLock().unlock();
      }
    // Thread.sleep(1);

  }


  public static void main(String[] args) {
   ThreadTest threadTest =new ThreadTest();
    for (int j = 0; j < 5; j++) {
      new Thread(new Runnable() {
        @Override
        public void run() {
          try {
            int k = threadTest.getValue2();
            System.out.println(k);
          } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println(-2);
          }
        }
      }).start();
    }
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
