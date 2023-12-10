/*
 * @lc app=leetcode id=473 lang=java
 *
 * [473] Matchsticks to Square
 */

// @lc code=start
class Solution {

  // Core idea:
  // filling up edge to edge.
  // Write dfs in this way can eaiser the branch cut.
  // 1. Cut same number choice. (If a number is not chosen, then don't chose the same number in following thing)
  // 2. Use an idx to avoid starting over when filling an edge. Reset idx to 0 if met.
  //    This makes the cnt for one edge drops hard
  //    nC1 + nC2 + ... + nC5 + ... nCn  15, 105, 455, 1365, 3003
  //    They sumup to 2^15 in the worst case. (1 + 1)^n
  //    So the time complexity bounded to 3*2^15 at most!!!
  // * If an edge uses a lot of edges, then the combination number will drop for following one.
  public boolean makesquare(int[] nums) {
    Arrays.sort(nums);

    int sum=0;
    for(int num : nums) sum += num;

    int target = sum / 4;
    if(nums.length < 4|| sum % 4 != 0 || nums[nums.length - 1] > target) return false;

    return dfs(nums, new boolean[nums.length], target, 0 , 0, 0);
  }

  public boolean dfs(int[] nums, boolean[] used,int target,int cur,int idx,int cnt){
    if(cnt == 3) return true;

    if(cur == target) return dfs(nums, used, target, 0, 0, cnt + 1);

    for(int i = idx;i < nums.length; i++){
      // filter out same numbers (this requires sort)
      if(used[i] || i > 0 && nums[i] == nums[i-1] && !used[i-1]) continue;
      if(cur + nums[i] > target) continue;

      used[i]=true;
      if(dfs(nums, used, target, cur + nums[i], i + 1, count)) return true;
      used[i]=false;
    }
    return false;
  }
}

// class Solution {
//   // square
//   // sum % 4 = 0
//   // max <= sum / 4
//   // n <= 15

//   // each stick can have 4 choice 4^15 -> 2e30 TLE
//   // with branch cut?

//   // need 4 len
//   // dp[mask] -> all 4 edegs length?
//   public boolean makesquare(int[] nums) {
//     int sum = 0;
//     for (int num : nums) sum += num;
//     if (sum % 4 != 0) return false;
//     for (int num : nums) if (num > sum / 4) return false;

//     return dfs(nums, new int[4], 0, sum / 4, 0);
//   }

//   private boolean dfs(int[] nums, int[] lens, int idx, int max, int cnt) {
//     if (cnt == 3 || idx == nums.length) {
//       // for (int i = 1; i < lens.length; i++) if (lens[i] != lens[0]) return false;
//       return true;
//     }

//     for (int i = 0; i < lens.length; i++) {
//       if (lens[i] + nums[idx] > max) continue;
//       lens[i] += nums[idx];
//       if (lens[i] == max) cnt++;
//       if (dfs(nums, lens, idx + 1, max, cnt)) return true;
//       if (lens[i] == max) cnt--;
//       lens[i] -= nums[idx];
//     }
//     return false;
//   }
// }
// @lc code=end

