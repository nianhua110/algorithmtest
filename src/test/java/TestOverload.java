/*
 * Copyright (c) 2017, CipherGateway and/or its affiliates. All rights  reserved.
 *
 */

public class TestOverload {
  static abstract class Animal {
    public static final String s = "hello";
    public static int a = 1;

    static {
      System.out.println("Animal init");
    }
  }

  static class Tigger extends Animal {
  }

  static class Cat extends Animal {
  }

  public void sayHello(Animal animal) {
    System.out.println("animal say hello");
  }


  public void sayHello(Tigger animal) {
    System.out.println("tigger say hello");
  }


  public void sayHello(Cat animal) {
    System.out.println("cat say hello");
  }

  public static void main(String[] args) {
    System.out.println(Animal.s);
    System.out.println(Animal.a);
    Animal tigger = new Tigger();
    Animal cat = new Cat();
    TestOverload testOverload = new TestOverload();
    testOverload.sayHello(tigger);
    testOverload.sayHello(cat);
  }
}
