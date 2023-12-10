/*
 * @lc app=leetcode id=50 lang=java
 *
 * [50] Pow(x, n)
 */

// @lc code=start
class Solution {
    // devide and conquer can avoid the use of long

    // at the bottom, returns 1
    // so at beginning it is 1/x
    public double myPow(double x, int n) {
        if (n == 0)
            return 1.0d;

        double temp = myPow(x, n / 2);

        if ((n & 1) == 1) {
            if (n >= 0)
                return temp * temp * x;
            else
                return temp * temp * 1 / x;
        } else
            return temp * temp;
    }
}
// @lc code=end
