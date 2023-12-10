import java.util.*;

/*
 * @lc app=leetcode id=787 lang=java
 *
 * [787] Cheapest Flights Within K Stops
 */

// @lc code=start
class Solution {
    // dfs brute force + branch cut can pass
    // dp is okay
    // step by step, dp[i][j] means min price to reach i with at most j step

    // bfs with dp[i] means the lowest price to reach city i
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        List<HashMap<Integer, Integer>> graph = new ArrayList<>();
        for(int i = 0; i < n; i++) graph.add(new HashMap<>());
        for(int[] flight : flights) graph.get(flight[0]).put(flight[1], flight[2]);

        int dp[] = new int[n], cnt = 0;
        Arrays.fill(dp, -1);
        dp[src] = 0;
        
        Deque<Integer> queue = new ArrayDeque<>(), next;
        queue.add(src);
        while(!queue.isEmpty() && cnt < K + 1) {
            next = new ArrayDeque<>();
            int[] temp = Arrays.copyOf(dp, n); // need to make sure use the cost from last layer

            while(!queue.isEmpty()) {
                int cur = queue.poll();
                
                for(Map.Entry<Integer, Integer> e : graph.get(cur).entrySet()) {
                    int nextCity = e.getKey(), cost = e.getValue();
                    
                    // ! only add when it min cost change
                    if(dp[nextCity] == -1 || dp[nextCity] > temp[cur] + cost) {
                        dp[nextCity] = temp[cur] + cost;
                        next.add(nextCity);
                    }
                }
            }
            queue = next;
            cnt++;
        }

        return dp[dst];
    }
}
// @lc code=end

