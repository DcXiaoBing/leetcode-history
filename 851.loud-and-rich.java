import java.util.*;
/*
 * @lc app=leetcode id=851 lang=java
 *
 * [851] Loud and Rich
 */

// @lc code=start
class Solution {

    // seems like a graph problem
    // topological sort to get a richness sequence
    
    // maintain a graph
    // get richer people list for each people(need to liik at chain)
    // build graph could be difficult
    
    // well, seems build graph then dfs for each node is much more simpler
    // and graph guarantee to have no circle
    
    public int[] loudAndRich(int[][] richer, int[] quiet) {
        List<List<Integer>> graph = new ArrayList<>();
        for(int i = 0; i < quiet.length; i++) graph.add(new ArrayList<>());
        for(int[] edge : richer) graph.get(edge[1]).add(edge[0]);

        int res[] = new int[quiet.length];
        boolean seen[] = new boolean[quiet.length];
        for(int i = 0; i < res.length; i++) res[i] = dfs(graph, res, seen, quiet, i);

        return res;
    }
    private int dfs(List<List<Integer>> graph, int[] ans, boolean[] seen, int[] quiet, int cur) {
        if(seen[cur]) return ans[cur];
        seen[cur] = true;

        int res = cur;
        for(Integer nei : graph.get(cur)) {
            int temp = dfs(graph, ans, seen, quiet, nei);
            if(quiet[res] > quiet[temp]) res = temp;
        }

        ans[cur] = res;
        return res;
    }
}
// @lc code=end
