/*
 * @lc app=leetcode id=69 lang=java
 *
 * [69] Sqrt(x)
 */

// @lc code=start
class Solution {
    public int mySqrt(int x) {

        // corner case input
        if (x == 0 || x == 1)
            return x;

        // binary search

        // use long to prevent product overflow
        // use right - 1 to get right answer
        // right means the integer that slightly bigger than x
        int left = 0, right = x, mid;
        while (left <= right) {
            mid = left + (right - left) / 2;
            long temp = (long) mid * mid;

            if (temp == x)
                return mid;
            else if (temp > x) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        // if big, right will decrease
        // if small, left will plus but right keeps same
        // so overall, right stores the correct value
        return right;
    }
}
// @lc code=end
