/*
 * @lc app=leetcode id=834 lang=java
 *
 * [834] Sum of Distances in Tree
 */

// @lc code=start
class Solution {
  // 1. Two neighbour nodes
  // ans[x] - ans[y] = cnt@y - cnt@x
  // When we go to y from x, all nodes in sub x dis + 1, all nodes in sub y dis - 1

  // 2. Root's answer is the answer already. value set in dfs1

  // 3. it is a undirectinal tree, so any node can be root

  List<List<Integer>> tree;
  int cnt[], ans[], n;
  public int[] sumOfDistancesInTree(int n, int[][] edges) {
    tree = new ArrayList<>();
    for (int i = 0; i < n; i++) tree.add(new ArrayList<>());
    for (int[] edge : edges) {
      tree.get(edge[0]).add(edge[1]);
      tree.get(edge[1]).add(edge[0]);
    }
    this.n = n;
    cnt = new int[n];
    ans = new int[n];
    dfs1(0, -1);
    dfs2(0, -1);
    return ans;
  }

  // undirected -> need parent to prevent cycle
  private void dfs1(int node, int parent) {
    // 1. distence = distence#child + cnt@child
    // All child increased 1
    for (Integer child : tree.get(node)) if (child != parent) {
      dfs1(child, node);
      cnt[node] += cnt[child];
      ans[node] += ans[child] + cnt[child];
    }
    cnt[node]++; // node itself
  }

  private void dfs2(int node, int parent) {
    // ans root is already set. Infer child val from this know val
    for (Integer child : tree.get(node)) if (child != parent) {
      ans[child] = ans[node] - cnt[child] + n - cnt[child];
      dfs2(child, node);
    }
  }
}


// @lc code=end

