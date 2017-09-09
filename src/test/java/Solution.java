/*
 * Copyright (c) 2017, CipherGateway and/or its affiliates. All rights  reserved.
 *
 */

import java.math.BigDecimal;

public class Solution {

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
    BigDecimal res ;
    if(Double.isNaN(s1)|| Double.isNaN(s2)){
      res = new BigDecimal(0.00000000);
    }else {
       res = new BigDecimal(s1 + s2);

    }
    double result = res.setScale(6, BigDecimal.ROUND_HALF_UP).doubleValue();
    return result;
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    double s =solution.areaOfIntersectionOfCircles(0, 0, 2, 3, 0, 2);
    System.out.println(s);
    double s1 =solution.areaOfIntersectionOfCircles(0, 13, 9, 0, -9, 9);
    System.out.println(s1);
  }
}

