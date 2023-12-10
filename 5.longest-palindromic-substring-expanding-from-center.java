/*
 * @lc app=leetcode id=5 lang=java
 *
 * [5] Longest Palindromic Substring
 */

// @lc code=start

// exist o(n) algorithm but cannot wirte it in interview
class Solution {
    // expand from center
    // seperate consider odd and even palindrom
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0)
            return "";

        char arrayS[] = s.toCharArray();

        int left = 0, right = 0, res = 0;
        int ansL = 0, ansR = 0;

        // check odd
        for (int i = 0; i < arrayS.length; i++) {
            left = right = i;

            while (left >= 0 && right < arrayS.length && arrayS[left] == arrayS[right]) {
                left--;
                right++;
            }

            if (right - left + 1 > res) {
                res = right - left + 1;
                ansL = left;
                ansR = right;
            }
        }

        // check even
        for (int i = 0; i < arrayS.length - 1; i++) {
            left = i;
            right = i + 1;

            // in case of invalid length of 2 substring
            if (arrayS[left] != arrayS[right])
                continue;

            while (left >= 0 && right < arrayS.length && arrayS[left] == arrayS[right]) {
                left--;
                right++;
            }

            if (right - left + 1 > res) {
                res = right - left + 1;
                ansL = left;
                ansR = right;
            }
        }

        // right and left are both 1 step further
        return s.substring(ansL + 1, ansR);
    }
}
// @lc code=end
