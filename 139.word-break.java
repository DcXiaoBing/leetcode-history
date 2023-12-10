import java.util.HashSet;

/*
 * @lc app=leetcode id=139 lang=java
 *
 * [139] Word Break
 */

// @lc code=start
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        HashSet<String> words = new HashSet<>();
        for (String w : wordDict) {
            words.add(w);
        }
        return helper(words, new Boolean[s.length()], 0, s);
    }

    Boolean helper(HashSet<String> words, Boolean dp[], int curIndex, String s) {
        // base case
        if (s.length() == curIndex)
            return true;

        // introduce dp
        if (dp[curIndex] != null)
            return dp[curIndex];
        // try all possibility
        for (int i = curIndex, len = s.length(); i < len; i++) {
            if (words.contains(s.substring(curIndex, i + 1))) {
                if (helper(words, dp, i + 1, s)) {
                    dp[curIndex] = true;
                    return true;
                }
            }
        }
        dp[curIndex] = false;
        return false;
    }
}
// @lc code=end
