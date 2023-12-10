import java.util.*;

/*
 * @lc app=leetcode id=765 lang=java
 *
 * [765] Couples Holding Hands
 */

// @lc code=start
class Solution {
    // each postion has a pair
    // so, if it not satisfy, need to swap either one of them

    // if a swap makes two satisfy, then that's it
    // otherwise, try to swap another one

    // hint is tree, and draw edges from a couch to another
    // one node could have two edge
    // and will form a circle. count would be the couch count - 1
    public int minSwapsCouples(int[] row) {
        int n = row.length / 2;
        List<List<Integer>> graph = new ArrayList<>();
        for(int i = 0; i < n; i++) graph.add(new ArrayList<>());
        
        int[] numToIdx = new int[row.length];
        for(int i = 0; i < row.length; i++) numToIdx[row[i]] = i;

        // build graph
        for(int i = 0; i < row.length; i++) {
            int pair;
            if((row[i] & 1) == 1) pair = row[i] - 1;
            else pair = row[i] + 1;
            graph.get(i / 2).add(numToIdx[pair] / 2); // add an edge
        }

        // for(List<Integer> l : graph) System.out.println(l);

        // check graph with bfs
        HashSet<Integer> seen = new HashSet<>();
        Deque<Integer> queue = new ArrayDeque<>();
        int res = 0;

        for(int i = 0; i < n; i++) {
            if(seen.contains(i)) continue;

            int size = 0;
            queue.add(i);
            while(!queue.isEmpty()) {
                int cur = queue.poll();
                if(seen.contains(cur)) continue;
                seen.add(cur);
                size++;

                for(Integer nbh : graph.get(cur)) queue.add(nbh);

                // System.out.println(queue + " " + size);
            }
            res += size - 1;
        }
        return res;
    }
}
// @lc code=end

