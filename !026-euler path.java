class Solution {
  // some kind like topological sort? No, because we don't have to satisfy all edges.
  // consider a pair as a node. two nodes have directed edge(n1 -> n2) if n1.end == n2.start
  // dfs?

  // Hierholzer's Algorithm
  // 1. if a node is visited multiple times(several edges in and several edges out), and we want to visit all edges, then the order doesn't matter(if it is guarteened the answer exisxt.).
  // 2. Hardest part would be how to find the starting point.
  // Well, all points in the middle, should have equal amount of incoming and outcoming edges.
  // So, if a node's in and out are not same (out > in), then this node must be the starting node.
  // (under the assumption that answer exist)

  // This question, a pair is an edge.
  public int[][] validArrangement(int[][] pairs) {
    HashMap<Integer, List<Integer>> graph = new HashMap<>();
    HashMap<Integer, Integer> degrees = new HashMap<>();
    for (int[] pair : pairs) {
      graph.putIfAbsent(pair[0], new ArrayList<>());
      graph.get(pair[0]).add(pair[1]);
      // out + 1, in - 1
      degrees.put(pair[0], degrees.getOrDefault(pair[0], 0) + 1);
      degrees.put(pair[1], degrees.getOrDefault(pair[1], 0) - 1);
    }

    // find start point. If the graph is just a big circle, then any node can be start point.
    int start = pairs[0][0];
    for (Map.Entry<Integer, Integer> entry : degrees.entrySet())
      if (entry.getValue() > 0) start = entry.getKey();
    // System.out.println(degrees);
    // System.out.println(start);

    // starting build the route.
    // we remove the last node in the list to improve the performance.
    List<int[]> res = new ArrayList<>();
    /**
     * Loop doesn't work because when we met a empty list, we need to go to pre node.
     * But loop doesn't keep this information.
     * We can add a stack to store this information, but recurrsion is simpler to implement.
     *
     * A loop version(with stack):
     * # Hierholzer's Algorithm:
        route = []
        st = [S]
        while st:
            while g[st[-1]]:
                st.append(g[st[-1]].pop())
            route.append(st.pop())
        route.reverse()
        return [[route[i], route[i+1]] for i in range(len(route)-1)]
     */
//     int cur = start;
//     while (res.size() < pairs.length) {
//       List<Integer> rest = graph.getOrDefault(cur, new ArrayList<>());
//       if (rest.isEmpty()) continue;
//       int nxt = rest.remove(rest.size() - 1);
//       // System.out.println(cur + " " + nxt);
//       // don't get into end point too early
//       if (graph.containsKey(nxt) && graph.get(nxt).isEmpty() && rest.size() > 0) {
//         int temp = rest.remove(rest.size() - 1);
//         rest.add(nxt);
//         nxt = temp;
//       }
//       res.add(new int[]{cur, nxt});

//       cur = nxt;
//     }
    euler(res, graph, start);
    Collections.reverse(res);
    return res.toArray(new int[0][0]);
  }
  private void euler(List<int[]> res, HashMap<Integer, List<Integer>> graph, int cur) {
    List<Integer> list = graph.getOrDefault(cur, new ArrayList<>());
    while(!list.isEmpty()) {
      int nbh = list.remove(list.size() - 1);
      euler(res, graph, nbh);
      res.add(new int[]{cur, nbh});
    }
  }
}
