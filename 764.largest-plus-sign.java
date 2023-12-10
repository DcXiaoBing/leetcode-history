/*
 * @lc app=leetcode id=764 lang=java
 *
 * [764] Largest Plus Sign
 */

// @lc code=start
class Solution {
  // n 500 => n^2 2.5e5
  // longest is center, which is n / 2 + 1

  // from hint, just 4 dp
  // or actually we can share one dp and update it 4 times.

  // Using one dp and use dp[i][j] = Math.min(dp[i][j], dp[i][j + 1] + 1) will cause override
  // e.g. updating vertial edge will be affected by horizontal data
  // So I use h and v two array

  // But in the ans, it uses an count variable to avoid this!
  public int orderOfLargestPlusSign(int n, int[][] mines) {
    int mat[][] = new int[n][n];
    int[][] h = new int[n][n], v = new int[n][n];

    for (int[] row : h) Arrays.fill(row, Integer.MAX_VALUE);
    for (int[] row : v) Arrays.fill(row, Integer.MAX_VALUE);
    for (int[] row : mat) Arrays.fill(row, 1);
    for (int[] mine : mines) mat[mine[0]][mine[1]] = 0;
    // for (int[] row : mat) System.out.println(Arrays.toString(row));


    for (int i = 0; i < n; i++) {
      // l -> r
      h[i][0] = Math.min(h[i][0], mat[i][0]); // 0 or 1
      for (int j = 1; j < n; j++) {
        if (mat[i][j] == 0) h[i][j] = 0;
        else h[i][j] = Math.min(h[i][j], h[i][j - 1] + 1);
      }

      // r -> l
      h[i][n - 1] = Math.min(h[i][n - 1], mat[i][n - 1]);
      for (int j = n - 2; j >= 0; j--) {
        if (mat[i][j] == 0) h[i][j] = 0;
        else h[i][j] = Math.min(h[i][j], h[i][j + 1] + 1);
      }
    }
    // System.out.println();
    // for (int[] row : dp) System.out.println(Arrays.toString(row));

    for (int j = 0; j < n; j++) {
      // u -> d
      v[0][j] = Math.min(v[0][j], mat[0][j]);
      for (int i = 1; i < n; i++) {
        if (mat[i][j] == 0) v[i][j] = 0;
        else v[i][j] = Math.min(v[i][j], v[i - 1][j] + 1);
      }

      // d -> u
      v[n - 1][j] = Math.min(v[n - 1][j], mat[n - 1][j]);
      for (int i = n - 2; i >= 0; i--) {
        if (mat[i][j] == 0) v[i][j] = 0;
        else v[i][j] = Math.min(v[i][j], v[i + 1][j] + 1);
      }
    }
    // System.out.println();
    // for (int[] row : dp) System.out.println(Arrays.toString(row));
    int res = 0;
    for (int i = 0; i < n; i++) for (int j = 0; j < n; j++)
      res = Math.max(res, Math.min(h[i][j], v[i][j]));
    return res;
  }
}
// @lc code=end

