import java.util.Arrays;

/*
 * @lc app=leetcode id=300 lang=java
 *
 * [300] Longest Increasing Subsequence
 */

// @lc code=start
class Solution {
    public int lengthOfLIS(int[] nums) {
        if(nums == null || nums.length == 0) return 0;

        // dp[i] is the last number for LIS with length i + 1
        int dp[] = new int[nums.length], max = 1;

        dp[0] = nums[0];
        for(int i = 1; i < nums.length; i++) {
            dp[0] = Math.min(dp[0], nums[i]);

            for(int j = 0; j < max; j++) {
                if(nums[i] > dp[j]) { // can generate a longer
                    if(j == max - 1) dp[max++] = nums[i];
                    else dp[j + 1] = Math.min(dp[j+1], nums[i]);
                } else
                    break;
            }

            // System.out.println(Arrays.toString(dp) + " " + max);
        }
        return max;
    }
}
// @lc code=end

