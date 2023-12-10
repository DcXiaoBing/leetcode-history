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
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        List<HashMap<Integer, Integer>> graph = new ArrayList<>();
        for(int i = 0; i < n; i++) graph.add(new HashMap<>());
        for(int[] flight : flights) graph.get(flight[0]).put(flight[1], flight[2]);

        int dp[][] = new int[n][K + 2];
        for(int[] row : dp) Arrays.fill(row, Integer.MAX_VALUE);
        dp[src][0] = 0;

        for(int i = 1; i <= K + 1; i++) for(int j = 0; j < n; j++) {
            if(dp[j][i-1] == Integer.MAX_VALUE) continue;
            dp[j][i] = Math.min(dp[j][i], dp[j][i-1]);
            for(Map.Entry<Integer, Integer> e : graph.get(j).entrySet()) {
                dp[e.getKey()][i] = Math.min(dp[e.getKey()][i], dp[j][i-1] + e.getValue());
            }
        }
        // for(int[] row : dp) System.out.println(Arrays.toString(row));
        // int min = Integer.MAX_VALUE;
        // for(int i = 0; i <= K + 1; i++) if(dp[dst][i] != -1) min = Math.min(min, dp[dst][i]);
        return dp[dst][K + 1] == Integer.MAX_VALUE ? -1 : dp[dst][K + 1];
    }
}
// @lc code=end

