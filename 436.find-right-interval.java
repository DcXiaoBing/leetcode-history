import java.util.Map;
import java.util.TreeMap;

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
    // ! hashmap can use array as a key
    
    // sort then binary search
    // Or use treemap, save start, index , search end points

    public int[] findRightInterval(int[][] intervals) {
        int[] res = new int[intervals.length];
        TreeMap<Integer, Integer> map = new TreeMap<>();

        for(int i = 0; i < intervals.length; i++) {
            map.put(intervals[i][0], i);
        }

        for(int i = 0; i < intervals.length; i++) {
            Map.Entry<Integer, Integer> e = map.ceilingEntry(intervals[i][1]);
            if(e == null) res[i] = -1;
            else res[i] = e.getValue();
        }
        return res;
    }
}
// @lc code=end

