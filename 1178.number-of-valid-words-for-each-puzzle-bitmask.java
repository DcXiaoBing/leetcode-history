import java.util.*;

/*
 * @lc app=leetcode id=1178 lang=java
 *
 * [1178] Number of Valid Words for Each Puzzle
 */

// @lc code=start
class Solution {
  // Two thoughts:
  // Pre-process the words to bitmask
  // Pre-process the words to Trie

  // The bitmask solution needs to generate all subset mask
  public List<Integer> findNumOfValidWords(String[] words, String[] puzzles) {
    HashMap<Integer, Integer> fre = new HashMap<>();
    for (String word : words) {
      int mask = getMask(word);
      fre.put(mask, fre.getOrDefault(mask, 0) + 1);
    }

    List<Integer> res = new ArrayList<>();
    for (String p : puzzles) {
      // 1st char must exist
      int firstBit = 1 << (p.charAt(0) - 'a');
      // All rest bits are empty
      int total = fre.getOrDefault(firstBit, 0);
      int mask = getMask(p.substring(1));
      for (int cur = mask; cur > 0; cur = mask & (cur - 1)) {
        total += fre.getOrDefault(cur | firstBit, 0);
      }
      res.add(total);
    }
    return res;
  }

  private int getMask(String s) {
    int res = 0;
    for (int i = 0; i < s.length(); i++) {
      int idx = s.charAt(i) - 'a';
      res = res | (1 << idx);
    }
    return res;
  }
}
// @lc code=end

