/*
 * @lc app=leetcode id=413 lang=java
 *
 * [413] Arithmetic Slices
 */

// @lc code=start

// A good problem to think it as dp
// Notice, what they want is a consecutive array.
// We can compute the cnt of a certain end point

class Solution {
  public int numberOfArithmeticSlices(int[] A) {
    int res = 0, cnt = 2;
    for(int i = 2; i < A.length; i++) {
      if(A[i] - A[i-1] == A[i-1] - A[i-2]) res += ++cnt - 2; // at least 3 chars
      else {
        cnt = 2;
        // i++; // A[i-1] is new start
      }
      // System.out.printf("len:%d, i:%d %n", cnt, i);
    }
    return res;
  }
}
// @lc code=end
