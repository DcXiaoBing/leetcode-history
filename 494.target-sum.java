import java.util.Arrays;

/*
 * @lc app=leetcode id=494 lang=java
 *
 * [494] Target Sum
 */

// @lc code=start
class Solution {
    public int findTargetSumWays(int[] nums, int S) {
        int sum = 0;
        for(int n : nums) sum += n;
        if(S > sum || S < -sum) return 0;

        int dp[][] = new int[2 * sum + 1][nums.length];
        dp[nums[0] + sum][0] += 1;
        dp[-nums[0] + sum][0] += 1; // use +=, because might duplicate

        // -sum ~ +sum -> 0 ~ 2*sum
        for(int i = 1; i < nums.length; i++) for(int j = 0; j < sum * 2 + 1; j++){
            if(dp[j][i-1] > 0) {
                dp[j+nums[i]][i] += dp[j][i-1];
                dp[j-nums[i]][i] += dp[j][i-1];
            }
        }
        // for(int[] row : dp) System.out.println(Arrays.toString(row));
        return dp[S + sum][nums.length - 1];
    }
}
// @lc code=end

