/*
 * @lc app=leetcode id=546 lang=java
 *
 * [546] Remove Boxes
 */

// @lc code=start
class Solution {
  // The key point for this problem is define the "status"
  // dp[i][j] is not enough, because of the combination
  // So use dp[i][j][k] -> in subarray i...j, there are k elements right behind r which equals nums[r]
  // trailing elements can be obtained by removing elements in between
  public int removeBoxes(int[] boxes) {
    int dp[][][] = new int[100][100][100];
    return dfs(boxes, dp, 0, boxes.length - 1, 0);
  }

  private int dfs(int[] nums, int[][][] dp, int l, int r, int k) {
    if(l > r) return 0;

    // move r to correct position to reduce computation
    while(l < r && nums[r] == nums[r - 1]) {
      r--;
      k++;
    }

    if(dp[l][r][k] != 0) return dp[l][r][k];

    int res = dfs(nums, dp, l, r - 1, 0) + (k + 1) * (k + 1);
    for (int i = l; i < r; i++) if(nums[i] == nums[r]) {
      // left part with one more trailing num + points got from numbers in between
      res = Math.max(res, dfs(nums, dp, l, i, k + 1) + dfs(nums, dp, i + 1, r - 1, 0));
    }

    dp[l][r][k] = res;
    return dp[l][r][k];
  }
}
// @lc code=end

