/*
 * @lc app=leetcode id=522 lang=java
 *
 * [522] Longest Uncommon Subsequence II
 */

// @lc code=start
class Solution {
  // !!! The answer of a sub must be a whole string in input list
  // Proof: Suppose answer sub is not a string in input but is a sub sequence of a word.
  // Then, after we add a char into it, it will still be the answer
  // So, we can just check if a string is subsequence of another string.
  public int findLUSlength(String[] strs) {
    Arrays.sort(strs, (a, b) -> b.length() - a.length());

    outer:
    for (int i = 0; i < strs.length; i++) {
      for (int j = 0; j < strs.length; j++) if (i != j) {
        if(isSubsequence(strs[i], strs[j])) continue outer;
      }
      return strs[i].length();
    }
    return -1;
  }

  private boolean isSubsequence(String x, String y) {
    int j = 0;
    for (int i = 0; i < y.length() && j < x.length(); i++)
        if (x.charAt(j) == y.charAt(i))
            j++;
    return j == x.length();
  }
}
// @lc code=end

