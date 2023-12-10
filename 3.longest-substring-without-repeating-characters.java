/*
 * @lc app=leetcode id=3 lang=java
 *
 * [3] Longest Substring Without Repeating Characters
 */

// @lc code=start
class Solution {
    public int lengthOfLongestSubstring(String s) {
        int fre[] = new int[128];
        int l = 0, r = 0, res = 0;

        while (r < s.length()) {
            // expand window
            boolean flag = false;

            while (r < s.length()) {
                int index = s.charAt(r);
                r++;
                fre[index]++;

                if (fre[index] > 1) {
                    flag = true;
                    break;
                }
            }

            // record cur length
            if (flag) {
                res = Math.max(res, r - l - 1);
            } else {
                res = Math.max(res, r - l);
            }
            // System.out.println(l + "; " + r);
            // System.out.println(res);

            // shrink window
            while (l < r) {
                int index = s.charAt(l);
                l++;
                fre[index]--;

                if (fre[index] == 1)
                    break;
            }
        }
        return res;
    }
}
// @lc code=end
