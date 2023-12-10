/*
 * @lc app=leetcode id=336 lang=java
 *
 * [336] Palindrome Pairs
 */

// @lc code=start
class Solution {
  // if center is palindrome, we can find try to find a match for rest part (prefix/suffix)
  public List<List<Integer>> palindromePairs(String[] words) {
    HashMap<String, Integer> map = new HashMap<>();
    for (int i = 0; i < words.length; i++) map.put(words[i], i);

    List<List<Integer>> res = new ArrayList<>();
    for (int i = 0; i < words.length; i++) {
      String rev = new StringBuilder(words[i]).reverse().toString();
      if(map.containsKey(rev) && map.get(rev) != i) {
        res.add(Arrays.asList(i, map.get(rev))); // another symmetric pair is handled when process rev
      }

      for (String prefix : findPrefix(words[i])) {
        String revPrefix = new StringBuilder(prefix).reverse().toString();
        if(map.containsKey(revPrefix) && map.get(revPrefix) != i)
          res.add(Arrays.asList(i, map.get(revPrefix)));
      }

      for (String suffix : findSuffix(words[i])) {
        String revSuffix = new StringBuilder(suffix).reverse().toString();
        if(map.containsKey(revSuffix) && map.get(revSuffix) != i)
          res.add(Arrays.asList(map.get(revSuffix), i));
      }
    }
    return res;
  }

  private boolean isPal(String word, int l, int r) {
    while(l < r) {
      if(word.charAt(l) != word.charAt(r)) return false;
      l++;
      r--;
    }
    return true;
  }

  private List<String> findSuffix(String word) {
    List<String> res = new ArrayList<>();
    for (int i = 0; i < word.length(); i++) {
      if(isPal(word, 0, i)) res.add(word.substring(i + 1, word.length()));
    }
    return res;
  }

  private List<String> findPrefix(String word) {
    List<String> res = new ArrayList<>();
    for (int i = 0; i < word.length(); i++) {
      if(isPal(word, i, word.length() - 1)) res.add(word.substring(0, i)); // "" will be included.
    }
    return res;
  }
}
// @lc code=end

