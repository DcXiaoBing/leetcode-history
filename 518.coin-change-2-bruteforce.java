import java.util.*;

import sun.net.www.content.text.plain;

/*
 * @lc app=leetcode id=518 lang=java
 *
 * [518] Coin Change 2
 */

// @lc code=start
class Solution {
    // optimized brute force cannot cut branch. So dp
    // dp cannot distinguish same combination
    // 5 [1,2,5]
    // 1
    // 1

    // 2
    // 1 1
    // 2

    // 3
    // 1 1 1
    // 2 1
    // 1 2

    // 4
    // 1 1 1 1
    // 2 1 1
    // 1 2 1
    // 1 1 2
    // 2 2
    public int change(int amount, int[] coins) {
        if(amount == 0) return 1; // special input

        // primitive is not suitable for comparator
        Arrays.sort(coins);
        
        // int dp[] = new int[amount + 1];
        // Arrays.fill(dp, -1);
        // dp[0] = 1;
        
        

        // for(int i = 1; i <= amount; i++) for(int c : coins) {
        //     if(i >= c) dp[i] += dp[i - c];
        // }
        
        // helper(coins, dp, amount);
        // System.out.println(Arrays.toString(dp));
        // return dp[amount];

        helper(coins, coins.length - 1, amount);
        return res;
    }
    // private int helper(int[] coins, int[] dp, int amount) {
    //     if(amount < 0) return 0;
    //     if(amount == 0) return 1;
        
    //     if(dp[amount] != -1) return dp[amount];
    //     dp[amount] = 0;
        
    //     for(int c : coins) dp[amount] += helper(coins, dp, amount - c);
    //     return dp[amount];
    // }


    int res = 0;
    private void helper(int[] coins, int curIdx, int amount) {
        if(amount == 0) {
            res++;
            return ;
        }
        if(curIdx < 0) return ;

        for(int i = amount / coins[curIdx]; i >= 0; i--) {
            helper(coins, curIdx - 1, amount - i * coins[curIdx]);
        }
    }
}
// @lc code=end

