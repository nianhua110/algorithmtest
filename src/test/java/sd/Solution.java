/*
 * Copyright (c) 2017, CipherGateway and/or its affiliates. All rights  reserved.
 *
 */
package sd;

public class Solution {
  int minSupplier = Integer.MAX_VALUE;

  void doFindMinNumberOfJuiceStalls(int numOfStails, int[] distOfStalls, int[] juiceQuantity, int distance, int initialEnergy, int numOfStop, int numOfTake) {
    if (initialEnergy < 0) {//没到达学校并且没用能量了
      return;
    }
    if (distance <= 0) { //到达学校
      if (numOfStop < minSupplier) {
        minSupplier = numOfTake;
      }
      return;
    }

    for (int i = 0; i < distOfStalls.length; i++) {
      if (distOfStalls[i] == numOfStop) {
        /*拿*/
        int currentJuiceQuantity = juiceQuantity[i];
        doFindMinNumberOfJuiceStalls(numOfStails, distOfStalls, juiceQuantity, distance - 1, initialEnergy - 1 + currentJuiceQuantity, numOfStop + 1, numOfTake + 1);
        return;
      }
    }
    /*不拿这个*/
    doFindMinNumberOfJuiceStalls(numOfStails, distOfStalls, juiceQuantity, distance - 1, initialEnergy - 1, numOfStop + 1, numOfTake);
  }

  int findMinNumberOfJuiceStalls(int numOfStails, int[] distOfStalls, int[] juiceQuantity, int distance, int initialEnergy) {
    minSupplier = Integer.MAX_VALUE;
    doFindMinNumberOfJuiceStalls(numOfStails, distOfStalls, juiceQuantity, distance, initialEnergy, 0, 0);
    if (minSupplier == Integer.MAX_VALUE) {
      return -1;
    } else {
      return minSupplier;
    }
  }


  public static void main(String[] args) {
    Solution solution = new Solution();
     int x= solution.findMinNumberOfJuiceStalls(3, new int[]{5,7,10}, new int[]{2,3,5},15,5);
     System.out.println(x);
    int y = solution.findMinNumberOfJuiceStalls(5, new int[]{10, 20, 22, 23, 26}, new int[]{10, 2, 5, 1, 1}, 30, 10);
    System.out.println(y);
  }

}
