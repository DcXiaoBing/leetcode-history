/*
 * @lc app=leetcode id=581 lang=java
 *
 * [581] Shortest Unsorted Continuous Subarray
 */

// @lc code=start
class Solution {
  // instead from thinking from head and tail, let's focus on the center!
  public int findUnsortedSubarray(int[] nums) {
    // min is the min value to the right
    // max is the max value to the left
    int n = nums.length, min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
    int l = 0, r = n - 1;

    for(int i = 0; i < n; i++) {



      // compare the min to the right
      if(nums[n - i] <= min) {
        min = nums[n - i];
        r = n - i;
      }

      if(nums[i] >= max) {

      }
    }
  }
}
// @lc code=end

