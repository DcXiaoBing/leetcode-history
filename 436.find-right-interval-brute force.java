import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

/*
 * @lc app=leetcode id=436 lang=java
 *
 * [436] Find Right Interval
 */

// @lc code=start
class Solution {
    
    // could over lap
    // sort

    // intervals do not have same start point -> hashmap

    public int[] findRightInterval(int[][] intervals) {
        int[] res = new int[intervals.length];
        Arrays.fill(res, -1);
        
        for(int i = 0; i < intervals.length; i++) for(int j = 0; j < intervals.length; j++) {
            if(i == j) continue;
            if(intervals[i][1] <= intervals[j][0]) {
                if(res[i] == -1 || intervals[res[i]][0] > intervals[j][0]) res[i] = j;
            }
        }

        return res;
    }
}
// @lc code=end

