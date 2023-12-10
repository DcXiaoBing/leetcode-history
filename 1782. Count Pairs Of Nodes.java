import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/*
 * @lc app=leetcode id=1782 lang=java
 *
 * [1782] Count Pairs Of Nodes
 */

// @lc code=start
class Solution {
  // 1. real cnt = in[i] + in[j] - share[i][j]
  // 2. If in[i] + in[j] > queries[k], potential failure could only cause by shared edges!

  // !!! base could not be to big, int could overflow
  static int base = 100000;
  public int[] countPairs(int n, int[][] edges, int[] queries) {
    int nodeCnts[] = new int[n + 1];
    HashMap<Integer, Integer> pairs = new HashMap<>();
    for(int i = 0; i < edges.length; i++) {

      Arrays.sort(edges[i]);
      nodeCnts[edges[i][0]]++;
      nodeCnts[edges[i][1]]++;

      int key = edges[i][0] * base + edges[i][1];
      pairs.put(key, 1 + pairs.getOrDefault(key, 0));
    }

    int res[] = new int[queries.length], sorted[] = nodeCnts.clone();
    Arrays.sort(sorted);
    for(int i = 0; i < queries.length; i++) {
      int cnt = 0, l = 1, r = sorted.length - 1;
      // find out all in[i] + in[j] > queries[i]
      while(l < r) {
        if(sorted[l] + sorted[r] > queries[i]) {
          cnt += r - l; // all pair with end r is quailfy!!!
          r--;
        } else l++;
      }

      // try to remove all potential failure.
      // Notice, one pair can only be removed once.
      for(Integer key : pairs.keySet()) {
        int e1 = key / base, e2 = key % base;
        // System.out.printf("e1:%d, e2:%d %n", e1, e2);
        if(nodeCnts[e1] + nodeCnts[e2] > queries[i] && nodeCnts[e1] + nodeCnts[e2] - pairs.get(key) <= queries[i])
          cnt--;
      }
      res[i] = cnt;
    }
    return res;
  }
}
// @lc code=end
