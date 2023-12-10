/*
 * @lc app=leetcode id=410 lang=java
 *
 * [410] Split Array Largest Sum
 */

// @lc code=start
class Solution {
  // split.
  // minimize largest sum
  // dp[i][j] for 0~i, split into j subarray, the minimized largest sum.
  // dp[i][j] = dp[i'][j] + psum
  // This would take n^2m -> 5e7


  public int splitArray(int[] nums, int m) {
    int n = nums.length, dp[][] = new int[n][m], psum[] = new int[n];

    for (int[] row : dp) Arrays.fill(row, -1);
    // psum[0] = nums[0];
//     for (int i = 1; i < n; i++) psum[i] = psum[i - 1] + nums[i];

    dfs(nums, dp, 0, m - 1, 0);
    for (int[] row : dp) System.out.println(Arrays.toString(row));
    return dfs(nums, dp, 0, m - 1, 0);
  }

  // This dfs is correct, but we cannot use dp on it.
  // At each index, we don't know the 'sum' value
  // So the dp[idx][rest] isn't enough to desrcible a status
  // !!! So we need to use a for loop to choose next start and make sure it is a clean start.
  private int dfs(int[] nums, int[][] dp, int idx, int rest, int sum) {
    if (idx == nums.length) {
      // if (rest == 0) return sum;
      // else return Integer.MAX_VALUE;
      return sum;
    }

    // if (dp[idx][rest] != -1) return dp[idx][rest];
    int res = Integer.MAX_VALUE;

    // don't start new subarray
    if (nums.length - idx > rest) {
      res = dfs(nums, dp, idx + 1, rest, sum + nums[idx]);
    }
    // start new subarray
    if (rest > 0) {
      res = Math.min(res, Math.max(sum, dfs(nums, dp, idx + 1, rest - 1, nums[idx])));
    }
    dp[idx][rest] = res;
    return dp[idx][rest];
  }

//   public int splitArray(int[] nums, int m) {
//     int n = nums.length, psum[] = new int[n + 1], dp[][] = new int[n][m + 1];

//     for (int[] row : dp) Arrays.fill(row, -1);
//     for (int i = 0; i < n; i++) psum[i + 1] = psum[i] + nums[i];

//     return dfs(nums, psum, dp, 0, m);
//   }

//   private int dfs(int[] nums, int[] psum, int[][] dp, int idx, int rest) {
//     if (rest == 1) return psum[nums.length] - psum[idx];

//     if (dp[idx][rest] != -1) return dp[idx][rest];
//     int res = Integer.MAX_VALUE;

//     // choose a place and make a cut
//     for (int i = idx; i <= nums.length - rest; i++) {
//       int cur = psum[i + 1] - psum[idx];
//       int temp = dfs(nums, psum, dp, i + 1, rest - 1);
//       res = Math.min(res, Math.max(cur, temp));
//     }
//     dp[idx][rest] = res;
//     return dp[idx][rest];
//   }

  // binary search would be easier to implement and faster
//   public int splitArray(int[] nums, int m) {
//     int l = 0, r = Integer.MAX_VALUE - 1, mid;
//     for (int num : nums) l = Math.max(l, num);
//     while (l < r) {
//       mid = l + (r - l) / 2;
//       if (check(nums, mid, m)) r = mid;
//       else l = mid + 1;
//     }
//     return l;
//   }

//   private boolean check(int[] nums, int max, int m) {
//     int cur = 0, cnt = 1;
//     for (int i = 0; i < nums.length; i++) {
//       if (cur + nums[i] > max) {
//         cnt++;
//         cur = 0;
//       }
//       cur += nums[i];
//     }
//     return cnt <= m;
//   }
}
// @lc code=end

