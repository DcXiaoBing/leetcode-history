class Solution {
  // leetcode - 1192
  // 1. brute force
  // 2. dfs - check found time and min time (Tarjan's algorithm)
  // https://oi-wiki.org/graph/cut/
  // when visit a node, we are dfs to its neighbours(edge cur->nbh)
  // if nbh's found[cur] < min[nbh], then this edge would be a cirtical connection

  // for critical nodes
  // there should be at least one node that found[cur] <= min[mbh]
  // the = is because there could be other path from nbh to reach cur.

  int found[], min[], parent[], time = 0;
  List<List<Integer>> res = new ArrayList<>();
  public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
    found = new int[n];
    min = new int[n];
    parent = new int[n];
    Arrays.fill(found, -1);
    Arrays.fill(min, -1);
    Arrays.fill(parent, -1);

    List<List<Integer>> graph = new ArrayList<>();
    for (int i = 0; i < n; i++) graph.add(new ArrayList<>());
    for (List<Integer> edge : connections) {
      graph.get(edge.get(0)).add(edge.get(1));
      graph.get(edge.get(1)).add(edge.get(0));
    }

    dfs(graph, 0);
    return res;
  }

  private void dfs(List<List<Integer>> graph, int cur) {
    found[cur] = min[cur] = ++time;

    for (Integer nbh : graph.get(cur)) {
      if (found[nbh] == -1) { // first time visit
        parent[nbh] = cur;

        dfs(graph, nbh);
        min[cur] = Math.min(min[cur], min[nbh]);
        if (found[cur] < min[nbh]) { // edge cur->nbh
          List<Integer> list = new ArrayList<>();
          list.add(cur);
          list.add(nbh);
          res.add(list);
        }
      } else if (parent[cur] != nbh) { // visited neighbour. (Link to upper component)
        min[cur] = Math.min(min[cur], min[nbh]);
      }
    }
  }
}
