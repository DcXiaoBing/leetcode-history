/*
 * @lc app=leetcode id=62 lang=java
 *
 * [62] Unique Paths
 */

// @lc code=start
class Solution {
    public int uniquePaths(int m, int n) {
        return helper(m - 1, n - 1 + m - 1);
    }

    int helper(int m, int n) {
        double res = 1;
        for (int i = n; i > n - m; i--) {
            res *= i;
        }
        for (int i = m; i > 1; i--) {
            res /= i;
        }

        return (int) Math.round(res);
    }
}
// @lc code=end
