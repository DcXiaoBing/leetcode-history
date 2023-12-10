/*
 * @lc app=leetcode id=826 lang=java
 *
 * [826] Most Profit Assigning Work
 */

// @lc code=start
class Solution {
    // dp[i] means the max profit can get for difficuty at most i
    public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
        int dp[] = new int[100001];
        for(int i = 0; i < difficulty.length; i++){
            dp[difficulty[i]] = Math.max(dp[difficulty[i]], profit[i]);
        }

        for(int i = 1; i < dp.length; i++){
            dp[i] = Math.max(dp[i-1], dp[i]);
        }

        int res = 0;
        for(int w : worker){
            res += dp[w];
        }
        return res;
    }
}
// @lc code=end

