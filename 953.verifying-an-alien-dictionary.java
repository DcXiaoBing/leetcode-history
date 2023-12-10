/*
 * @lc app=leetcode id=953 lang=java
 *
 * [953] Verifying an Alien Dictionary
 */

// @lc code=start
// Core idea, compare adjacent pair!

class Solution {
  // compare adjacent words is enough
  // if there is a > b at index i & j
  // if a & b are adjacent, it is verified
  // if a & b are not adjacent, then there must be a c in between
  // Then a > c and are adjacent.
  public boolean isAlienSorted(String[] words, String order) {
    int[] cToIdx = new int[26];
    for (int i = 0, len = order.length(); i < len; i++) cToIdx[order.charAt(i) - 'a'] = i;

    // String[] sorted = words.clone();
    // Arrays.sort(sorted, (a, b) -> compare(cToIdx, a, b));

    for (int i = 0; i < words.length - 1; i++) {
      if(compare(cToIdx, words[i], words[i + 1]) > 0) return false;
    }
    return true;
  }
  private int compare(int[] cToIdx, String a, String b) {
    for (int i = 0; i < a.length() && i < b.length(); i++) {
      int idxA = cToIdx[a.charAt(i) - 'a'], idxB = cToIdx[b.charAt(i) - 'a'];
      if(idxA < idxB) return -1;
      else if(idxA > idxB) return 1;
    }
    if(a.length() < b.length()) return -1;
    else if(a.length() > b.length()) return 1;
    return 0;
  }
}
// @lc code=end

