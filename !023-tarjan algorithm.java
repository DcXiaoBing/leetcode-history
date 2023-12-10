// Low means the lowest point that this node can reach (directly or indirectly).

class Solution {
  // Tarjan's algorithm
  // discover time + low link

  List<List<Integer>> graph, res = new ArrayList<>();
  int found[], lowlink[], parent[], time = 0;
  public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
    graph = new ArrayList<>();
    for(int i = 0; i < n; i++) graph.add(new ArrayList<>());
    for(List<Integer> edge : connections) {
      graph.get(edge.get(0)).add(edge.get(1));
      graph.get(edge.get(1)).add(edge.get(0));
    }

    found = new int[n];
    lowlink = new int[n];
    parent = new int[n];
    Arrays.fill(found, -1);
    Arrays.fill(parent, -1);

    dfs(0);
    return res;
  }

  private void dfs(int cur) {
    found[cur] = lowlink[cur] = ++time; // mark as visited

    for (Integer nbh : graph.get(cur)) {
      if(found[nbh] == -1) {
        // new node, mark parent and start dfs
        parent[nbh] = cur;
        dfs(nbh);

        // update lowlink and record res
        lowlink[cur] = Math.min(lowlink[cur], lowlink[nbh]);
        if(found[cur] < lowlink[nbh]) {
          // This condition means cur is a critical node.
          List<Integer> edge = new ArrayList<>();
          edge.add(cur);
          edge.add(nbh);
          res.add(edge);
        }
      } else if(nbh != parent[cur]) {
        // this node's found is handled elsewhere, so we just need to check lowlink
        lowlink[cur] = Math.min(lowlink[cur], lowlink[nbh]);
      }
    }
  }
}
