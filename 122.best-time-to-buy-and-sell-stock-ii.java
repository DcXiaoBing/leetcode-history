/*
 * @lc app=leetcode id=122 lang=java
 *
 * [122] Best Time to Buy and Sell Stock II
 */

// @lc code=start
class Solution {
    // simple brute force
    // public int maxProfit(int[] prices) {
    // int res = 0;
    // for (int i = 1; i < prices.length; i++) {
    // if (prices[i] > prices[i - 1])
    // res += prices[i] - prices[i - 1];
    // }
    // return res;
    // }

    // summit valley
    public int maxProfit(int[] prices) {
        int res = 0, valley = 0, summit = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            // find valley
            while (i < prices.length - 1 && prices[i] >= prices[i + 1])
                i++;
            valley = prices[i];

            // find summit
            while (i < prices.length - 1 && prices[i] <= prices[i + 1])
                i++;
            summit = prices[i];

            // if (summit > valley)
            res += summit - valley;
        }
        return res;
    }
}
// @lc code=end
