/*
 * Copyright (c) 2017, CipherGateway and/or its affiliates. All rights  reserved.
 *
 */
package concurrency.file.vertx;

import io.vertx.core.*;
import io.vertx.core.buffer.Buffer;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author kyle
 */
public class VertxBlockingWriteFile2 {

  public static final int COUNT = 1_000;
  public static  String dump = null;

  static {
    System.out.println(Runtime.getRuntime().maxMemory());
    StringBuilder sb = new StringBuilder();
    for (long i = 0; i < 100_000_000; i++) {
      sb.append(i%9);
    }
    dump = sb.toString();
  }

  public static class Inner extends AbstractVerticle {
    Path path = Paths.get(System.getProperty("user.dir") + "/temp");

    public void write(String s) throws IOException {
      Files.write(path, s.getBytes());
    }

    public String read() throws IOException {
      return Files.readAllLines(path).stream().reduce((a, b) -> a + "\n" + b).orElse(null);
    }

    @Override
    public void start(Future<Void> future) throws Exception {
      Router route = Router.router(vertx);
      route.route().handler(BodyHandler.create());
      route.route("/write").handler(context1 -> {
        System.out.println(Thread.currentThread());
        try {
          write(dump);
          context1.response().end();
        } catch (IOException e) {
          e.printStackTrace();
          context1.response().setStatusCode(500).end();
        }


      });
      vertx.createHttpServer()
          .requestHandler(route::accept)
          .listen(8133, ar -> {
            if (ar.failed()) future.fail(ar.cause());
            else future.complete();
          });
    }

  }


  public static void main(String[] args) {
    VertxOptions vertxOptions = new VertxOptions();
    vertxOptions.setMaxEventLoopExecuteTime(20_000_000);
    Vertx vertx = Vertx.vertx(vertxOptions);
    vertx.deployVerticle(new Inner());
    System.out.println("start");
  }

}
