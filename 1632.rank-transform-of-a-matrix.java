/*
 * @lc app=leetcode id=1632 lang=java
 *
 * [1632] Rank Transform of a Matrix
 */

// @lc code=start
class Solution {
  // Should search connected components. Reason is described in the solution:
  // We might need to adjust rank for previous value.
  // E.g. same value nums, one is lower, another is bigger. And they are connected by a thrid node with same value.

  // And another trick is:
  // If we build graph using points, that would cause TLE, due to too much edges.
  // So, compute connected row -> cols and col -> rows
  // Then use val + rows + cols to compute neighbours for each point
  // Consider row & col as node
  // Then transfer row and col to list of nodes

  // ~J
  public int[][] matrixRankTransform(int[][] mat) {
    int m = mat.length, n = mat[0].length;

    // Connect rows and cols
    // value -> rol -> rows & cols.
    // So it looks like a collection of graph.
    HashMap<Integer, HashMap<Integer, List<Integer>>> graphs = new HashMap<>();
    for (int i = 0; i < m; i++) for (int j = 0; j < n; j++) {
      int v = mat[i][j];
      if(graphs.get(v) == null) graphs.put(v, new HashMap<>());
      HashMap<Integer, List<Integer>> graph = graphs.get(v);

      if(graph.get(i) == null) graph.put(i, new ArrayList<>());
      if(graph.get(~j) == null) graph.put(~j, new ArrayList<>());
      graph.get(i).add(~j);
      graph.get(~j).add(i);
    }

    // Do greedy with bfs
    TreeMap<Integer, List<List<int[]>>> valueToIndex = new TreeMap<>();
    boolean[][] seen = new boolean[m][n];
    for (int i = 0; i < m; i++) for (int j = 0; j < n; j++) if(!seen[i][j]) {
      seen[i][j] = true;

      // bfs, compute all rows and cols
      int v = mat[i][j];
      Map<Integer, List<Integer>> graph = graphs.get(v);
      HashSet<Integer> visited = new HashSet<>();
      visited.add(i);
      visited.add(~j);

      Deque<Integer> queue = new ArrayDeque<>();
      queue.add(i);
      queue.add(~j);

      while(!queue.isEmpty()) {
        int cur = queue.poll();
        for (Integer nbh : graph.get(cur)) if(!visited.contains(nbh)) {
          queue.add(nbh);
          visited.add(nbh);
        }
      }

      // Convert rows & cols to points
      List<int[]> points = new ArrayList<>();
      for (Integer rc : visited) {
        if (rc >= 0) { // this is a row
          for (Integer col : graph.get(rc)) {
            seen[rc][~col] = true; // mark these nodes as visited to reduce duplicates
            points.add(new int[]{rc, ~col});
          }
        } else { // this is a col
          for (Integer row : graph.get(rc)) {
            seen[row][~rc] = true;
            points.add(new int[]{row, ~rc});
          }
        }
      }

      if(valueToIndex.get(v) == null) valueToIndex.put(v, new ArrayList<>());
      valueToIndex.get(v).add(points);
    }

    // fill res
    int res[][] = new int[m][n], rowMax[] = new int[m], colMax[] = new int[n];
    for (Integer v : valueToIndex.keySet()) {
      for (List<int[]> points : valueToIndex.get(v)) {
        int rank = 1;
        for (int[] p : points) {
          // +1 because there must have no other same value and pre set rank is for value that smaller.
          rank = Math.max(rank, Math.max(rowMax[p[0]], colMax[p[1]]) + 1);
        }

        for (int[] p : points) {
          res[p[0]][p[1]] = rank;
          rowMax[p[0]] = Math.max(rowMax[p[0]], rank);
          colMax[p[1]] = Math.max(colMax[p[1]], rank);
        }
      }
    }
    return res;
  }
}
// @lc code=end

