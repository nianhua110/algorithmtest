/*
 * Copyright (c) 2017, CipherGateway and/or its affiliates. All rights  reserved.
 *
 */

import java.math.BigDecimal;

public class GoToSchool {
  double dis(int firstX, int firstY, int secondX, int secondY) {
    double len = Math.sqrt((firstX - secondX + 0.0) * (firstX - secondX + 0.0) + (firstY - secondY + 0.0) * (firstY - secondY + 0.0));
    return len;
  }

  double areaOfIntersectionOfCircles(int firstX, int firstY, int firstR, int secondX, int secondeY, int secondR) {
    double a = dis(firstX, firstY, secondX, secondeY), b = firstR + 0.0, c = secondR + 0.0;
    double cta1 = Math.acos((a * a + b * b - c * c) / 2 / (a * b)),
        cta2 = Math.acos((a * a + c * c - b * b) / 2 / (a * c));
    double s1 = (firstR + 0.0) * firstR * cta1 - (firstR + 0.0) * firstR * Math.sin(cta1) * (a * a + b * b - c * c) / 2 / (a * b);
    double s2 = (secondR + 0.0) * secondR * cta2 - (secondR + 0.0) * secondR * Math.sin(cta2) * (a * a + c * c - b * b) / 2 / (a * c);
    BigDecimal res = new BigDecimal(s1 + s2);
    double result = res.setScale(6).doubleValue();
    return result;
  }

  int minSupplier = Integer.MIN_VALUE;

  void doFindMinNumberOfJuiceStalls(int numOfStails, int[] distOfStalls, int[] juiceQuantity, int distance, int initialEnergy, int numOfStop) {
    if (distance <= 0) { //到达学校
      if (numOfStop < minSupplier) {
        numOfStop = minSupplier;
      }
      return;
    }
    if (initialEnergy <= 0) {//没到达学校并且没用能量了
      return;
    }
    for(int i =0; i< distOfStalls.length;i++){
      if(distOfStalls[i]==numOfStop){
        /*拿*/
        int currentJuiceQuantity= juiceQuantity[i];
        doFindMinNumberOfJuiceStalls(numOfStails, distOfStalls, juiceQuantity, distance-1, initialEnergy-1+currentJuiceQuantity, numOfStop+1);
        break;
      }
    }
    /*不拿这个*/
    doFindMinNumberOfJuiceStalls(numOfStails, distOfStalls, juiceQuantity, distance-1, initialEnergy-1, numOfStop+1);
  }

  int findMinNumberOfJuiceStalls(int numOfStails, int[] distOfStalls, int[] juiceQuantity, int distance, int initialEnergy) {
    minSupplier = Integer.MIN_VALUE;
    doFindMinNumberOfJuiceStalls(numOfStails,distOfStalls,juiceQuantity,distance,initialEnergy, 0);
    if(minSupplier == Integer.MIN_VALUE)
    {
      return -1;
    }else {
      return minSupplier;
    }
  }

  public static void main(String[] args) {

  }
}
