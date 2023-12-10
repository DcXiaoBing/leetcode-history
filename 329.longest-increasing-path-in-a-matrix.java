/*
 * @lc app=leetcode id=329 lang=java
 *
 * [329] Longest Increasing Path in a Matrix
 */

// @lc code=start
class Solution {
  // m,n is small enough.
  // Try bfs from each node + cache

  // We will visit each node at most once.
  public int longestIncreasingPath(int[][] mat) {
    int dp[][] = new int[mat.length][mat[0].length], res = 0;
    for (int i = 0; i < mat.length; i++) for (int j = 0; j < mat[0].length; j++) {
      // if(dp[i][j] == 0)
      dfs(mat, dp, i, j);
      res = Math.max(res, dp[i][j]);
    }

    // for (int[] row : dp) System.out.println(Arrays.toString(row));
    return res;
  }

  // If we add a condition to dfs, we can avoid cycle!!!
  // And avoid using duplicate value
  static int[] dx = new int[]{1, -1, 0, 0};
  static int[] dy = new int[]{0, 0, 1, -1};
  private void dfs(int[][] mat, int[][] dp, int x, int y) {
    // dp[x][y] = 1;
    // System.out.printf("%d %d %n", x, y);
    if(dp[x][y] != 0) return;
    for (int i = 0; i < dx.length; i++) {
      int nx = x + dx[i], ny = y + dy[i];
      if(nx < 0 || nx >= mat.length || ny < 0 || ny >= mat[0].length) continue;
      if(dp[nx][ny] == 0 && mat[x][y] < mat[nx][ny]) dfs(mat, dp, nx, ny);

      if(mat[x][y] < mat[nx][ny]) dp[x][y] = Math.max(dp[x][y], dp[nx][ny]);
      // if(mat[x][y] < mat[nx][ny]) dp[x][y] = Math.max(dp[x][y], 1 + dp[nx][ny]);
    }
    // if(dp[x][y] == 0) dp[x][y] = 1; // this method should take care of the case that all neighbour is >= than it. Will make 1 alone.
    dp[x][y]++;
  }
}
}
// @lc code=end

