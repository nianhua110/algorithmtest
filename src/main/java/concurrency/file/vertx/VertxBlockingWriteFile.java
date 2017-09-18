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
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author kyle
 */
public class VertxBlockingWriteFile {

  public static final int COUNT = 1_000;

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
        io.vertx.core.buffer.Buffer buffer = context1.getBody();
        String str = buffer.toString();
        try {
          try {
            Thread.sleep(1000);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
          write(str);
          context1.response().end();
        } catch (IOException e) {
          e.printStackTrace();
          context1.response().setStatusCode(500).end();
        }

//        vertx.fileSystem()
//            .writeFile(path.toString(), Buffer.buffer(str), ar -> {
//              if (ar.failed()) context1.response().setStatusCode(500).end();
//              else context1.response().end();
//            });

      });
      route.route("/read").handler(context1 -> {
        try {
          String str = read();
          context1.response().end(str);
        } catch (IOException e) {
          e.printStackTrace();
          context1.response().setStatusCode(500);
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
    vertxOptions.setMaxEventLoopExecuteTime(200_000_000);
    Vertx vertx = Vertx.vertx(vertxOptions);
    vertx.deployVerticle(new Inner(), ar -> {
      if (ar.failed()) {
        System.out.println(ar.cause());
      } else {
        vertx.executeBlocking(f -> {
          // request();
        }, f -> {

        });
      }
    });

  }

  public static void request() {
    List<Future> list = new ArrayList<>();
    CyclicBarrier cb = new CyclicBarrier(COUNT);
    Vertx vertx1 = Vertx.vertx();
    Random random = new Random();
    for (int i = 0; i < COUNT; i++) {
      final int finalI = i;
      final StringBuilder sb = new StringBuilder();
      int sum = 0;
      for (int j = 0; j < 10_000; j++) {
        int val = random.nextInt();
        sum += val;
        sb.append(val);
        sb.append(",");
      }
      sb.append("\n");
      sb.append(sum);
      list.add(Future.future());
      new Thread(() -> {
        try {
          cb.await();
        } catch (InterruptedException e) {
          e.printStackTrace();
        } catch (BrokenBarrierException e) {
          e.printStackTrace();
        }
        io.vertx.core.http.HttpClient client = vertx1.createHttpClient();
        client.post(8133, "localhost", "/write", in -> {
          if (in.statusCode() != 200) {
            System.out.println("error");
          }
          list.get(finalI).complete();
        }).end(sb.toString());
      }).start();

    }
    CompositeFuture.all(list)
        .setHandler(ar2 -> {
          System.out.println("finish");
          vertx1.createHttpClient()
              .getNow(8133, "localhost", "/read", ar3 -> {
                if (ar2.failed()) {
                  System.out.println(ar2.cause());
                  return;
                }
                ar3.bodyHandler(bdy -> {
                  String result = bdy.toString();
                  String[] resultArr = result.split("\n");
                  int sum = Arrays.stream(resultArr[0].split(","))
                      .mapToInt(Integer::parseInt)
                      .sum();
                  int expect = Integer.parseInt(resultArr[1]);
                  if (sum != expect) {
                    System.out.println("error");
                  } else {
                    System.out.println("success");
                  }
                });

              });

        });
  }
}
