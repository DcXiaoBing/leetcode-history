/*
 * @lc app=leetcode id=29 lang=java
 *
 * [29] Divide Two Integers
 */

// @lc code=start
class Solution {
  // A common key point: transform both dividend and divisor to negative numbers
  // approach1: binary division
  // approach2: Adding Powers of Two with Bit-Shifting
  public int divide(int dividend, int divisor) {
    if(dividend == Integer.MIN_VALUE && divisor == -1) return Integer.MAX_VALUE; // overflow
    if(divisor == 1) return dividend;
    int negative = 2;
    if(dividend > 0) {
      dividend = -dividend;
      negative--;
    }
    if(divisor > 0) {
      divisor = -divisor;
      negative--;
    }

    // get the maximum base
    // Notice, negative numbers has opposite logic

    // power should be -1
    // Integer.MIN_VALUE 1
    // Integer.MIN_VALUE 2
    // This will lead to a overflow power
    int base = divisor, power = -1;
    while(base > Integer.MIN_VALUE / 2 && base * 2 > dividend) {
      base += + base; // should avoid use <<, because this is a negative number!
      power += power;
    }
    // System.out.printf("base:%d, power:%d %n", base, power);

    int res = 0;
    while(dividend <= divisor) {
      if(dividend <= base) {
        res += power; // base means power cnt of divisor
        dividend -= base;
      }
      base >>= 1;
      power >>= 1; // even number, right shift will always correct.
    }

    if(negative != 1) return -res;
    return res;
  }
}
// @lc code=end
