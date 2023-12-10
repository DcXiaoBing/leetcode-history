/*
 * @lc app=leetcode id=151 lang=java
 *
 * [151] Reverse Words in a String
 */

// @lc code=start
class Solution {
    public String reverseWords(String s) {
        // split by space
        // reverse list

        s = s.trim(); // remove leading and trailing space
        String[] words = s.split(" +"); // X+ one or more time
        StringBuilder res = new StringBuilder();
        for (int i = words.length - 1; i >= 0; i--) {
            res.append(words[i]);
            res.append(' ');
        }

        res.deleteCharAt(res.length() - 1);
        return res.toString();
    }
}
// @lc code=end
