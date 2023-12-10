/*
 * @lc app=leetcode id=50 lang=java
 *
 * [50] Pow(x, n)
 */

// @lc code=start
class Solution {
    public double myPow(double x, int n) {
        if (x == 1)
            return 1;
        if (x == 0)
            return 0;

        double res = 1, base = x;
        long temp = Math.abs((long) n);

        while (temp != 0) {
            if ((temp & 1) == 1)
                res *= base;

            base *= base;
            temp = temp >> 1;
        }
        if (n > 0)
            return res;
        else
            return 1 / res;
    }
}
// @lc code=end
