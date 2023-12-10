/*
 * @lc app=leetcode id=126 lang=java
 *
 * [126] Word Ladder II
 */

// @lc code=start
class Solution {
  // try build graph.
  // 1. A*C needs to iterate over possible keys everytime, which is slow
  // 2. No need to build undirected graph. Directed will works better for this.
  // Because we build graph from bfs, so no need to go back.

  // !!! Remeber to filter out word itself when finding a neighbour
  // !!! DELETE the word from set when added to queue (But do it layer by layer)
  HashMap<String, List<String>> graph = new HashMap<>();
  List<List<String>> res = new ArrayList<>();
  public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
    buildGraph(beginWord, new HashSet<>(wordList));
    // System.out.println(graph);
    List<String> temp = new ArrayList<>();
    temp.add(beginWord);
    dfs(temp, beginWord, endWord);
    return res;
  }

  // Because it is a ADG, so no need to use a hashset
  private void dfs(List<String> list, String cur, String target) {
    // System.out.println(list + " " + cur);
    if(cur.equals(target)) {
      res.add(new ArrayList<>(list));
      return ;
    }

    if(!graph.containsKey(cur)) return;
    for (String nbh : graph.get(cur)) {
      list.add(nbh);
      dfs(list, nbh, target);
      list.remove(list.size() - 1);
    }
  }

  // Build direct graph using bfs.
  // !!! Need to do this layer by layer, because a node could have multiple parent.
  // So at paretn layer, if we remove a node before other parent build edge, could cause missing of edges.
  private void buildGraph(String begin, HashSet<String> words) {
    // begin is the first layer. Remove it to make bfs cleaner.
    words.remove(begin);

    List<String> queue = new ArrayList<>(), next;
    HashSet<String> seen = new HashSet<>();

    queue.add(begin);
    seen.add(begin);

    while(!queue.isEmpty()) {
      next = new ArrayList<>();
      // System.out.println(queue);
      // System.out.println(words);

      for (String cur : queue) {
        if(!graph.containsKey(cur)) graph.put(cur, new ArrayList<>());

        for (String nbh : generateNeighbours(cur.toCharArray(), words)) {
          graph.get(cur).add(nbh);

          if(seen.add(nbh)) next.add(nbh);
        }
      }
      words.removeAll(next);
      queue = next;
    }
  }

  private List<String> generateNeighbours(char[] word, HashSet<String> words) {
    List<String> res = new ArrayList<>();
    for (int i = 0; i < word.length; i++) {
      char temp = word[i];
      for (int j = 0; j < 26; j++) if(temp - 'a' != j) {
        word[i] = (char)('a' + j);
        String tt = String.valueOf(word);
        if(words.contains(tt)) res.add(tt);
      }
      word[i] = temp;
    }
    return res;
  }
}
// @lc code=end

