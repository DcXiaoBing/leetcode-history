/*
 * @lc app=leetcode id=53 lang=java
 *
 * [53] Maximum Subarray
 */

// @lc code=start
class Solution {
    // prefix can provide positive help only when it is non-negative
    public int maxSubArray(int[] nums) {

        int cur = 0, max = Integer.MIN_VALUE;
        for (int n : nums) {
            if (cur < 0) {
                cur = 0;
            }
            cur += n;
            max = Math.max(max, cur);
        }

        return max;
    }
}
// @lc code=end
