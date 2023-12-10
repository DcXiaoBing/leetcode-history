import java.util.*;
/*
 * @lc app=leetcode id=336 lang=java
 *
 * [336] Palindrome Pairs
 */

// @lc code=start
// 3 case:
// case 1: word + revWord
// case 2: word1 + palindrome + suffix
//                 ^^^^^^^^^^^^^^^^^^^ is owrd2
// case 3: prefix + palindrome + word2
//         ^^^^^^^^^^^^^^^^^^^ is word1

// A thing worth noticing when using a Trie is
// Either you build a Trie in reverse order or you do the query in the reverse order.
class Solution {
  public List<List<Integer>> palindromePairs(String[] words) {
    Trie root = new Trie();
    // build trie
    for (int i = 0; i < words.length; i++) {
      Trie cur = root;
      for (int j = 0; j < words[i].length(); j++) {
        // hanld a new rest part
        if(isPal(words[i], j, words[i].length() - 1)) cur.restIsPalWordIdx.add(i);

        // go to next level
        int idx = words[i].charAt(j) - 'a';
        if(cur.children[idx] == null) cur.children[idx] = new Trie();
        cur = cur.children[idx];
      }
      cur.wordId = i;
    }

    // search in reverse order
    List<List<Integer>> res = new ArrayList<>();
    for (int i = 0; i < words.length; i++) {
      Trie cur = root;
      for (int j = words[i].length() - 1; j >= 0; j--) {
        // found a corresponding prefix for this suffix. case 2
        if(cur.wordId != -1 && isPal(words[i], 0, j)) {
          res.add(Arrays.asList(cur.wordId, i));
        }

        // go to next node
        int idx = words[i].charAt(j) - 'a';
        cur = cur.children[idx];
        if(cur == null) break;
      }
      if(cur == null) continue;

      // case 1
      // only place where a word can interact with itself
      if(cur.wordId != -1 && i != cur.wordId) res.add(Arrays.asList(cur.wordId, i)); // the symmetric pair will handled when process another word

      // case 3.
      // We have go through word2 now, so word1 should have palindrome in rest part
      for (Integer id : cur.restIsPalWordIdx) res.add(Arrays.asList(id, i));
    }
    return res;
  }

  private boolean isPal(String s, int l, int r) {
    while(l < r) {
      if(s.charAt(l++) != s.charAt(r--)) return false;
    }
    return true;
  }
}

class Trie {
  int wordId;
  Trie[] children;
  // word index of those words that is a palindrome in rest part after this prefix.
  List<Integer> restIsPalWordIdx;

  Trie() {
    wordId = -1;
    children = new Trie[26];
    restIsPalWordIdx = new ArrayList<>();
  }
}
// @lc code=end

