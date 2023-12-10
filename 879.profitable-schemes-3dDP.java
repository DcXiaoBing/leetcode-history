/*
 * @lc app=leetcode id=879 lang=java
 *
 * [879] Profitable Schemes
 */

// @lc code=start
class Solution {
    // find subset, then generate set? handle overlap?
    // subset count = 2^n each element can be choose or not.

    // dp[i][j] must choose crime i, total gang j, cnt that satisfy the requirement
    // dp[i][j] = sum of all dp[0...i-1][j-group[i]]
    // WRONG, missing some information. scheme not staisfy the condition could be
    // legal in future.

    // start from 3-d dp
    // dp[i][j][k] means, use first i (not have to must choose i-th), use at most j
    // people, profit at least k.

    // a skill: we can cut profit larger than P equal to P. This will not affect the
    // result

    // optimize from 3d to 2d
    // we only use i-1

    public int profitableSchemes(int G, int P, int[] group, int[] profit) {
        long dp[][][] = new long[group.length][G + 1][P + 1];
        int modulo = (int) 1e9 + 7;

        // use 0 group, at most 0 people, at least 0 profit. Choose nothing(empty
        // subset) is also a scheme.
        dp[0][0][0] = 1;

        // notice the range. group[i] > G, we can never choose it
        // if profit[0] > P, cut it to P. Save dp memory.
        for (int k = 0; group[0] <= G && k <= Math.min(profit[0], P); k++)
            dp[0][group[0]][k] = 1;

        for (int i = 1; i < group.length; i++) for (int j = 0; j <= G; j++) for (int k = 0; k <= P; k++) {        
            dp[i][j][k] = dp[i - 1][j][k]; // not add i-th

            // add i-th, notic the overflow
            if (j >= group[i]) {
                dp[i][j][k] = (dp[i][j][k] + dp[i - 1][j - group[i]][Math.max(0, k - profit[i])]) % modulo;
            }
        }

        long res = 0;
        for(int i = 0; i <= G; i++) res = (res + dp[group.length-1][i][P]) % modulo;
        return (int)res;
    }
}
// @lc code=end
