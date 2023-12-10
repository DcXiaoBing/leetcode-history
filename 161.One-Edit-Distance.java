/*
 * @lc app=leetcode id=161 lang=java
 *
 * [161] One Edit Distance
 */

// @lc code=start
class Solution {
  // smart one!
  // 1. assume s is shorter than t
  // 2. if a mismatch occurs, rest in s and t must match!
  public boolean isOneEditDistance(String s, String t) {
      int lenS = s.length(), lenT = t.length();
      if(lenS > lenT) return isOneEditDistance(t, s); // swap s and t to make s shorter
      if(lenT - lenS > 1) return false;

      for(int i = 0; i < lenS; i++) {
          char cs = s.charAt(i), ct = t.charAt(i);
          if(cs != ct) {
              if(lenS == lenT) return match(s, i+1, t, i+1);
              else return match(s, i, t, i+1);
          }
      }
      // reach here means only last char not match.
      // Because we want edit distance exactly 1, so we need to do one more judge.
      return lenS + 1 == lenT;
  }

  private boolean match(String s, int ps, String t, int pt) {
      while(ps < s.length()) {
          char cs = s.charAt(ps++), ct = t.charAt(pt++);
          if(cs != ct) return false;
      }
      return true;
  }
}
// @lc code=end
