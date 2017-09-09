/*
 * Copyright (c) 2017, CipherGateway and/or its affiliates. All rights  reserved.
 *
 */
package schedule;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class FCFSAndSJF {


  public static void main(String[] args) {
    testFCFS();
    testSJF();
  }

  public static void testSJF() {

    Process[] processes = new Process[]{
        new Process(0, 5, "A"),
        new Process(1, 7, "B"),
        new Process(3, 3, "C"),
        new Process(4, 8, "D"),
        new Process(6, 2, "E"),
    };
    doSJF(processes);
    out(processes);
  }

  public static void testFCFS() {

    Process[] processes = new Process[]{
        new Process(0, 5, "A"),
        new Process(1, 7, "B"),
        new Process(3, 3, "C"),
        new Process(4, 8, "D"),
        new Process(6, 2, "E"),
    };
    doFCFS(processes);
    out(processes);
  }

  public static void out(Process[] p) {
    Arrays.stream(p)
        .forEach(System.out::println);
    Arrays.stream(p)
        .mapToInt(in -> in.wholeTime)
        .average()
        .ifPresent(in -> {
          System.out.println("平均周转时间：" + in);
        });
    Arrays.stream(p)
        .mapToDouble(in -> in.weightWholeTime)
        .average()
        .ifPresent(in -> {
          System.out.println("平均周转时间：" + in);
        });

  }

  public static void doFCFS(Process[] p) {
    Arrays.sort(p, Comparator.comparing(a -> a.arrivalTime));
    for (int i = 0; i < p.length; i++) {
      if (i == 0) {
        p[i].finishTime = p[i].arrivalTime + p[i].serviceTime;
        p[i].startTime = p[i].arrivalTime;
      } else {
        if (p[i].arrivalTime > p[i - 1].finishTime) {
          p[i].finishTime = p[i].arrivalTime + p[i].serviceTime;
          p[i].startTime = p[i].arrivalTime;
        } else {
          p[i].finishTime = p[i].serviceTime + p[i - 1].finishTime;
          p[i].startTime = p[i - 1].finishTime;
        }
      }

      p[i].wholeTime = p[i].finishTime - p[i].arrivalTime;
      p[i].weightWholeTime = (double) p[i].wholeTime / (double) p[i].serviceTime;
    }


  }

  private static Process findNextPro(List<Process> list, int now) {
    Process pro = list.get(0);
    int index = 0;
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).serviceTime < pro.serviceTime & list.get(i).arrivalTime < now) {
        pro = list.get(i);
        index = i;
      }
    }
    list.remove(index);
    return pro;
  }

  public static Process[] doSJF(Process[] p) {
    int now = 0;
    List<Process> list = new LinkedList<>();
    List<Process> res = new LinkedList<>();
    Arrays.sort(p, Comparator.comparing(a -> a.arrivalTime));
    p[0].finishTime = p[0].arrivalTime + p[0].serviceTime;
    p[0].wholeTime = p[0].finishTime + p[0].arrivalTime;
    p[0].weightWholeTime = p[0].wholeTime / p[0].serviceTime;
    res.add(p[0]);
    now = p[0].finishTime;
    for (int i = 1; i < p.length; i++) {
      list.add(p[i]);
    }

    while (list.size() != 0) {
      Process next = findNextPro(list, now);
      if (next.arrivalTime > now) {
        next.finishTime = next.arrivalTime + next.serviceTime;
        next.startTime = next.arrivalTime;
      } else {
        next.finishTime = now + next.startTime;
        next.startTime = now;
      }

      now = next.finishTime;
      next.wholeTime = next.finishTime - next.arrivalTime;
      next.weightWholeTime = (double) next.wholeTime / (double) next.serviceTime;
      res.add(next);
    }
    return res.toArray(new Process[0]);
  }

  private static class Process {
    public int arrivalTime;
    public int serviceTime;
    public int finishTime;
    public int startTime;
    public int wholeTime;
    public double weightWholeTime;
    public String id;

    public Process(int arrivalTime, int serviceTime, String id) {
      this.arrivalTime = arrivalTime;
      this.serviceTime = serviceTime;
      this.id = id;
    }

    @Override
    public String toString() {
      DecimalFormat df = new DecimalFormat("#.00");
      return "时间：" + startTime + " 进程:" + id +
          " 开始运行，完成时间:" + finishTime + " 周转时间:" + wholeTime
          + " 带权周转时间: " + df.format(weightWholeTime);
    }
  }
}
