/*
 * @lc app=leetcode id=191 lang=java
 *
 * [191] Number of 1 Bits
 */

// @lc code=start
public class Solution {
  // you need to treat n as an unsigned value
  public int hammingWeight(int n) {
      int sum = 0;
      // != 0, not > 0
      // if use > 0, cannot handle negative numbers
      while (n != 0) {
          sum++;
          // n &= (n - 1);
          n -= n & -n;
      }
      return sum;
  }
}
// @lc code=end

