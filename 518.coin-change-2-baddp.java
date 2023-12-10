import java.util.*;

import sun.net.www.content.text.plain;

/*
 * @lc app=leetcode id=518 lang=java
 *
 * [518] Coin Change 2
 */

// @lc code=start
class Solution {
    // 500
    // [3,5,7,8,9,10,11]
    // brute force will TLE

    // but use two dimension dp will pass
    // sort coins, then the curIdx with amount rest can represent a state

    public int change(int amount, int[] coins) {
        if(amount == 0) return 1; // special input

        // primitive is not suitable for comparator
        Arrays.sort(coins);

        int dp[][] = new int[amount + 1][coins.length];
        boolean seen[][] = new boolean[amount + 1][coins.length];
        return helper(dp, seen, coins, coins.length - 1, amount);
    }

    private int helper(int[][] dp, boolean[][] seen, int[] coins, int curIdx, int amount) {
        if(amount == 0) return 1;
        if(curIdx < 0) return 0;

        if(seen[amount][curIdx]) return dp[amount][curIdx];
        seen[amount][curIdx] = true;

        int res = 0;
        for(int i = amount / coins[curIdx], cur = amount - i * coins[curIdx]; i >= 0; i--, cur += coins[curIdx]) {
            res += helper(dp, seen, coins, curIdx - 1,cur);
        }
        dp[amount][curIdx] = res;
        return dp[amount][curIdx];
    }
}
// @lc code=end

