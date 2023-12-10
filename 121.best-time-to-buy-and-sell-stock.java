import java.security.KeyFactory;

/*
 * @lc app=leetcode id=121 lang=java
 *
 * [121] Best Time to Buy and Sell Stock
 */

// @lc code=start
class Solution {
    // use an array to store the biggest in its right
    public int maxProfit(int[] prices) {
        if (prices.length == 0)
            return 0;
        int rMax[] = new int[prices.length];

        rMax[prices.length - 1] = prices[prices.length - 1];
        for (int i = prices.length - 2; i >= 0; i--) {
            rMax[i] = Math.max(rMax[i + 1], prices[i]);
        }

        int res = 0; // we can choose not buy and sell
        for (int i = 0; i < prices.length - 1; i++) {
            if (prices[i] < rMax[i + 1])
                res = Math.max(res, rMax[i + 1] - prices[i]);
        }

        return res;
    }
}
// @lc code=end
