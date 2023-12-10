/*
 * @lc app=leetcode id=581 lang=java
 *
 * [581] Shortest Unsorted Continuous Subarray
 */

// @lc code=start
class Solution {
  // Core idea is:
  // If number in head and tail are sorted, then they are global min or max

  // To check whether they are global min or max, we can use a min max array
  // min to the right
  // max to the left
  public int findUnsortedSubarray(int[] nums) {
    int n = nums.length, min[] = new int[n], max[] = new int[n];
    min[n - 1] = nums[n - 1];
    max[0] = nums[0];

    for (int i = 1; i < n; i++) {
      min[n - i - 1] = Math.min(nums[n - i - 1], min[n - i]);
      max[i] = Math.max(nums[i], max[i - 1]);
    }

    int l = 0, r = n - 1;
    while(l < n && min[l] == nums[l]) l++;
    while(l <= r && max[r] == nums[r]) r--;

    return r - l + 1;
  }
}
// @lc code=end
