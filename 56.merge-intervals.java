/*
 * @lc app=leetcode id=56 lang=java
 *
 * [56] Merge Intervals
 */

// @lc code=start
import java.util.*;

class Solution {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        List<int[]> merged = new ArrayList<>();
        int cur[] = null;
        for (int[] interval : intervals) {
            if (cur == null) {
                cur = interval;
            } else {
                if (cur[1] < interval[0]) {
                    // not overlap, write in
                    merged.add(cur);
                    cur = interval;
                } else {
                    cur[1] = Math.max(cur[1], interval[1]);
                }
            }
        }

        if (cur != null)
            merged.add(cur);
        int res[][] = new int[merged.size()][2];
        int counter = 0;
        for (int[] interval : merged) {
            res[counter++] = interval;
        }

        return res;
    }
}
// @lc code=end
