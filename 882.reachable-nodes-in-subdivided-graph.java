/*
 * @lc app=leetcode id=882 lang=java
 *
 * [882] Reachable Nodes In Subdivided Graph
 */

// @lc code=start
class Solution {
  // maxMoves is like a cost
  // subdivide is like add a weight on an edge

  // Dijisktra
  // 1. compute min distance to reach each node
  // 2. after that, iterate edge by edge.

//   public int reachableNodes(int[][] edges, int maxMoves, int n) {
//     List<HashMap<Integer, Integer>> graph = new ArrayList<>();
//     for (int i = 0; i < n; i++) graph.add(new HashMap<>());
//     for (int[] edge : edges) {
//       graph.get(edge[0]).put(edge[1], edge[2]);
//       graph.get(edge[1]).put(edge[0], edge[2]);
//     }

//     int dis[] = new int[n], res = 0;
//     boolean seen[] = new boolean[n];
//     Arrays.fill(dis, Integer.MAX_VALUE);
//     dis[0] = 0;

//     PriorityQueue<Integer> heap = new PriorityQueue<>((a, b) -> dis[a] - dis[b]);
//     heap.add(0);

//     while(!heap.isEmpty()) {
//       int cur = heap.poll();
//       if (seen[cur] || dis[cur] > maxMoves) continue;
//       seen[cur] = true;
//       res++; // a new node we visit

//       HashMap<Integer, Integer> map = graph.get(cur);
//       for (Integer nbh : map.keySet()) {
//         // cnt + 1 edges is inserted.
//         if (dis[nbh] <= dis[cur] + map.get(nbh) + 1) continue;
//         dis[nbh] = dis[cur] + map.get(nbh) + 1;
//         heap.add(nbh);
//       }
//     }
//     // System.out.println(Arrays.toString(dis));

//     // now we have dis to 0 of all nodes
//     for (int[] edge : edges) {
//       if (dis[edge[0]] <= maxMoves) {
//         int available = Math.min(edge[2], maxMoves - dis[edge[0]]);
//         edge[2] -= available;
//         res += available;
//       }
//       if (dis[edge[1]] <= maxMoves) {
//         int available = Math.min(edge[2], maxMoves - dis[edge[1]]);
//         edge[2] -= available;
//         res += available;
//       }
//       // int available = 2 * maxMoves - dis[edge[0]] - dis[edge[1]];
//       // res += Math.min(available, edge[2]);
//     }
//     return res;
//   }

  public int reachableNodes(int[][] edges, int maxMoves, int n) {
    List<HashMap<Integer, Integer>> graph = new ArrayList<>();
    for (int i = 0; i < n; i++) graph.add(new HashMap<>());
    for (int[] edge : edges) {
      graph.get(edge[0]).put(edge[1], edge[2]);
      graph.get(edge[1]).put(edge[0], edge[2]);
    }

    int dis[] = new int[n], res = 0;
    Arrays.fill(dis, Integer.MAX_VALUE);
    // dis[0] = 0;

    // node in heap should keep dis information
    // Otherwise, when dis array changes, the order inside a node is not updated.
    // I mean the order could be wrong!
    PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[1] - b[1]);
    heap.add(new int[]{0, 0});

    while(!heap.isEmpty()) {
      int cur[] = heap.poll();
      if (cur[1] > dis[cur[0]] || cur[1] > maxMoves) continue;
      dis[cur[0]] = cur[1];
      res++; // a new node we visit

      HashMap<Integer, Integer> map = graph.get(cur[0]);
      for (Integer nbh : map.keySet()) {
        // cnt + 1 edges is inserted.
        if (dis[nbh] <= dis[cur[0]] + map.get(nbh) + 1) continue;
        dis[nbh] = dis[cur[0]] + map.get(nbh) + 1;
        heap.add(new int[]{nbh, dis[nbh]});
      }
    }
    // System.out.println(Arrays.toString(dis));

    // now we have dis to 0 of all nodes
    for (int[] edge : edges) {
      if (dis[edge[0]] <= maxMoves) {
        int available = Math.min(edge[2], maxMoves - dis[edge[0]]);
        edge[2] -= available;
        res += available;
      }
      if (dis[edge[1]] <= maxMoves) {
        int available = Math.min(edge[2], maxMoves - dis[edge[1]]);
        edge[2] -= available;
        res += available;
      }
    }
    return res;
  }
}
// @lc code=end

