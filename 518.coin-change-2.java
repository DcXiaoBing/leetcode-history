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

    // better! still one dimension dp. But a good idea to prevent duplicate is add coin one by one.
    // because add one by one. so all combination only appear once

    public int change(int amount, int[] coins) {
        int dp[] = new int[amount + 1];
        dp[0] = 1; // when rest amount is 1, we can only choose no coins.

        // try to find a new way for each i
        for(int c : coins) for(int i = c; i <= amount; i++) {
            dp[i] += dp[i - c];
        }

        return dp[amount];
    }
}
// @lc code=end

