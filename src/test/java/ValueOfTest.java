/*
 * Copyright (c) 2017, CipherGateway and/or its affiliates. All rights  reserved.
 *
 */

public class ValueOfTest {

  public static boolean isSmall(Integer a, Integer b) {
    return a < b;
  }

  public static void main(String[] args) {
    Integer a = Integer.valueOf(-128);
    Integer b = Integer.valueOf("-128");
    Integer c = new Integer(-128);
    int d = -128;
    Integer e = -128;
    System.out.println(a == b);
    System.out.println(a == c);
    System.out.println(a == d);


    Integer a1 = Integer.valueOf(300);
    Integer b1 = Integer.valueOf("300");
    System.out.println(a1 == b1);

    Long l1 = Long.valueOf(128L);

    System.out.println(a.equals(l1));

    Long sum = 0L;
    for (long i = 0; i < 1000; i++) {
      sum += i;
    }
    System.out.println(sum);
    boolean boo = isSmall(a, d);
    System.out.println(boo);

    Integer p1 = 1;
    Integer p2 = 2;
    Integer p3 = 3;
    System.out.println(p3 == (p1+p2));


    Integer q1 = 100;
    Integer q2 = 200;
    Integer q3 = 300;
    System.out.println(q3 == (q1+q2));

    try {
      return;
    }finally {
      System.out.println("finally"+q1);
    }
  }
}
