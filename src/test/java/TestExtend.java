/*
 * Copyright (c) 2017, CipherGateway and/or its affiliates. All rights  reserved.
 *
 */

import java.math.BigInteger;

/**
 * @author kyle
 */
public class TestExtend {
  public static class Father {
    public void before() {
      System.out.println("father before");
    }

    public void prin() {
      before();
      System.out.println("Father");
    }
  }

  public static class Children extends Father {
    @Override
    public void before() {
      //prin();
      System.out.println("children before");
    }

    @Override
    public void prin() {
      super.prin();
     // System.out.println("Children");
    }
  }


  public static void main(String[] args) {
    Father f = new Children();
    f.prin();
  }
}
