import java.util.*;
/*
 * @lc app=leetcode id=797 lang=java
 *
 * [797] All Paths From Source to Target
 */

// @lc code=start
class Solution {
    List<List<Integer>> res;
    // List<Integer> dp[];
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        res = new LinkedList<>();
        if(graph == null)
            return res;
        
        // actually we can use dp to memorize
        // but it has a lot to modify
        // dp = new LinkedList[graph.length];
        LinkedList<Integer> curL = new LinkedList<>();
        HashSet<Integer> curS = new HashSet<>();

        curL.add(0);
        curS.add(0);
        dfs(graph, 0, curL, curS);
        return res;
    }

    void dfs(int[][] graph, int curNode, LinkedList<Integer> curList, HashSet<Integer> curSet){
        if(curNode == graph.length - 1){
            // reach the target
            res.add(new LinkedList<>(curList)); // deep copy
            return ;
        }

        // no cycle, so we can ignore the hashset check

        // visit all neighbour
        for(int n : graph[curNode]){
            // if(curSet.contains(n))
                // continue;
            
            curList.add(n);
            // curSet.add(n);
            dfs(graph, n, curList, curSet);
            curList.removeLast();
            // curSet.remove(n);
        }
    }
}
// @lc code=end

