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

  // The Trie solution needs a branch cut.
  // 1. the puzzles maximum length is 7, so at most 7 unique chars
  // 2. We only care about unique char in words, also, any word with more than 7 unique char should be rejected.
  // 3. Then, all possible words for a puzzle would be 7!
  // So we can check all possible word by using Trie. The implementation is interesting.
  // !!! Notice, cannot convert puzzle to bitmask, because it would change the starting char.

  Trie root = new Trie();
  public List<Integer> findNumOfValidWords(String[] words, String[] puzzles) {
    for (String word : words) {
      int mask = getMask(word);
      if (Integer.bitCount(mask) > 7) continue;
      insert(mask);
    }

    List<Integer> res = new ArrayList<>();
    for (String p : puzzles) {
      res.add(dfs(root, false, p));
    }
    return res;
  }

  private int dfs(Trie node, boolean hasFirst, String puzzle) {
    // All words end at this node is valid if hasFirst
    int res = hasFirst ? node.cnt : 0;

    // Try to go to next level.
    // Because word could omit some char, so we should go through the puzzle to find possible valid child.
    // Notice, chars in puzzle is not sorted.
    for (int i = 0; i < puzzle.length(); i++) {
      int idx = puzzle.charAt(i) - 'a';
      if (node.children[idx] == null) continue;
      res += dfs(node.children[idx], idx == puzzle.charAt(0) - 'a' || hasFirst, puzzle);
    }
    return res;
  }

  private void insert(int mask) {
    Trie cur = root;

    for (int i = 0; i < 26; i++) if (((mask & (1 << i)) != 0)) {
      if (cur.children[i] == null) cur.children[i] = new Trie();
      cur = cur.children[i];
    }
    cur.cnt++;
  }

  private int getMask(String s) {
    int res = 0;
    for (int i = 0; i < s.length(); i++) res = res | (1 << (s.charAt(i) - 'a'));
    return res;
  }
}

class Trie {
  Trie[] children;
  int cnt;
  Trie() {
    children = new Trie[26];
  }
}
// @lc code=end

