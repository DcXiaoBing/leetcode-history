/*
 * @lc app=leetcode id=8 lang=java
 *
 * [8] String to Integer (atoi)
 */

// @lc code=start
class Solution {
    public int myAtoi(String str) {

        // deal with corner input
        if (str == null || str.length() == 0)
            return 0;

        int res = 0, sign = 1, index = 0, len = str.length();
        int limit = Integer.MAX_VALUE / 10;

        // !!!check length
        while (index < len && str.charAt(index) == ' ')
            index++;

        if (index < len && str.charAt(index) == '+') {
            sign = 1;
            index++;
        } else if (index < len && str.charAt(index) == '-') {
            sign = -1;
            index++;
        }

        // use flag to mark whether there is number
        boolean flag = false;
        while (index < len) {
            char cur = str.charAt(index);
            if (cur < '0' || cur > '9')
                break;

            flag = true;

            if (res < limit || (sign >= 0 && res == limit && cur - '0' < 7)
                    || (sign < 0 && res == limit && cur - '0' <= 8))
                res = res * 10 + cur - '0';
            else {
                // deal with overflow
                if (sign >= 0)
                    return Integer.MAX_VALUE;
                else
                    return Integer.MIN_VALUE;
            }

            index++;
        }

        if (flag)
            return res * sign;
        else
            return 0;
    }
}
// @lc code=end
