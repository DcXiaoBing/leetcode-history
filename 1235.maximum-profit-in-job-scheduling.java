/*
 * @lc app=leetcode id=1235 lang=java
 *
 * [1235] Maximum Profit in Job Scheduling
 */

// @lc code=start
class Solution {
  // dp[i] max profit using 0~i element
  int n, jobs[][], dp[];
  public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
    n = startTime.length;
    dp = new int[n];
    jobs = new int[n][];
    Arrays.fill(dp, -1);
    for (int i = 0; i < n; i++) jobs[i] = new int[]{startTime[i], endTime[i], profit[i]};

    // sort by start time to allow us find compatible element by binary search
    Arrays.sort(jobs, (a, b) -> a[0] - b[0]);
    dfs(0);
    // System.out.println(Arrays.toString(dp));
    return dfs(0);
  }

  private int dfs(int idx) {
    if (idx == jobs.length) return 0;

    if(dp[idx] != -1) return dp[idx];

    // skip this one
    int res = dfs(idx + 1);
    // pick this one
    int nxt = bs(jobs[idx][1]);
    res = Math.max(res, jobs[idx][2] + dfs(nxt));

    dp[idx] = res;
    return dp[idx];
  }

  // find first job that start after target
  private int bs(int target) {
    if(target > jobs[n - 1][0]) return n;

    int l = 0, r = n - 1, mid;
    while(l < r) {
      mid = l + (r - l) / 2;
      if (jobs[mid][0] < target) l = mid + 1;
      else r = mid;
    }
    return l;
  }

//   private int bs(int lastEndingTime) {
//         int start = 0, end = n - 1, nextIndex = n;

//         while (start <= end) {
//             int mid = (start + end) / 2;
//             if (jobs[mid][0] >= lastEndingTime) {
//                 nextIndex = mid;
//                 end = mid - 1;
//             } else {
//                 start = mid + 1;
//             }
//         }
//         return nextIndex;
//     }
  // public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
  //   int n = startTime.length;
  //   int[][] jobs = new int[n][3];
  //   for (int i = 0; i < n; i++) {
  //     jobs[i] = new int[] {startTime[i], endTime[i], profit[i]};
  //   }
  //   Arrays.sort(jobs, (a, b)->a[1] - b[1]);
  //   TreeMap<Integer, Integer> dp = new TreeMap<>();
  //   dp.put(0, 0);
  //   for (int[] job : jobs) {
  //     int cur = dp.floorEntry(job[0]).getValue() + job[2];
  //     // Because end time is increasing
  //     // dp[i] means the max profit using 0...i
  //     // If we put this answer to treemap, we will break dp[i - 1] thing
  //     // dp[i] = Math.max(dp[i-1], dp[compatible[i]])
  //     if (cur > dp.lastEntry().getValue())
  //         dp.put(job[1], cur);
  //   }
  //   return dp.lastEntry().getValue();
  // }
}
// @lc code=end

