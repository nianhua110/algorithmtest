/*
 * Copyright (c) 2017, CipherGateway and/or its affiliates. All rights  reserved.
 *
 */
package concurrency;

/**
 * @author kyle
 */
public class StopThreadUnsafe {
   public static  class User{
     private int id ;
     private String name;

     public User(int id, String name) {
       this.id = id;
       this.name = name;
     }
   }
}
