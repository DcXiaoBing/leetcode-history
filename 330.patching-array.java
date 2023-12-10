/*
 * @lc app=leetcode id=330 lang=java
 *
 * [330] Patching Array
 */

// @lc code=start
class Solution {
  // !!! Thinking of coverage change

  // suppose x is the minimum missing number, then [1, x) is covered
  // Suppose we add a new number y, then [1, x) and [y + 1, x + y) are also covered
  // Now we have two things to notice:
  // 1. y + 1 has to cover x
  // 2. the bigger x + y is, the less add we need. (greedy)

  // n is too big
  // So we cannot check one by one.
  // Also notice that nums is sorted
  // first, we init the missing 1.
  // Then at some step, suppose we miss x
  // If we met num <= x, then increase miss
  // Else add miss number
  public int minPatches(int[] nums, int n) {
    long r = 1;
    int res = 0, p = 0;
    while(r <= n) {
      if(p == nums.length) {
        r += r;
        res++;
        continue;
      }
      if(nums[p] <= r) {
        r += nums[p];
        p++;
      } else {
        res++;
        r += r; // add the missing number it self. So the next missing is 2*miss
      }
      // System.out.println(p + " " + res + " " + r);
    }
    return res;
  }
}
// @lc code=end

