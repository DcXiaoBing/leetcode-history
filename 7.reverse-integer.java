/*
 * @lc app=leetcode id=7 lang=java
 *
 * [7] Reverse Integer
 */

// @lc code=start
class Solution {
    public int reverse(int x) {
        // definately will over flow
        // 2147483647 -2147483648
        int pLimit = Integer.MAX_VALUE / 10, nLimit = Integer.MIN_VALUE / 10;
        int temp = x, res = 0;

        while (temp != 0) {
            if (x >= 0) {
                if (res < pLimit || res == pLimit && temp % 10 <= 7) {
                    res = res * 10 + temp % 10;
                    temp /= 10;
                } else
                    return 0; // over flow
            } else {
                if (res > nLimit || res == nLimit && temp % 10 >= -8) {
                    res = res * 10 + temp % 10;
                    temp /= 10;
                } else
                    return 0;
            }
        }

        return res;
    }
}
// @lc code=end
