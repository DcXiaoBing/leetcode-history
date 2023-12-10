import java.util.HashMap;

/*
 * @lc app=leetcode id=13 lang=java
 *
 * [13] Roman to Integer
 */

// @lc code=start
class Solution {

    // smaller one can only appear once before the bigger one

    int getValue(char c) {
        switch (c) {
        case 'I':
            return 1;
        case 'V':
            return 5;
        case 'X':
            return 10;
        case 'L':
            return 50;
        case 'C':
            return 100;
        case 'D':
            return 500;
        case 'M':
            return 1000;
        }

        return 0;
    }

    public int romanToInt(String s) {
        char arrayS[] = s.toCharArray();
        int res = getValue(arrayS[arrayS.length - 1]);

        int pre = res;
        for (int i = arrayS.length - 2; i >= 0; i--) {
            int temp = getValue(arrayS[i]);
            if (temp >= pre)
                res += temp;
            else
                res -= temp;

            pre = temp;
        }

        return res;
    }
}
// @lc code=end
