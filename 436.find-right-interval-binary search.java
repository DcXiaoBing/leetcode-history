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
    // ! hashmap can use array as a key
    
    // sort then binary search
    // Or use treemap, save start, index , search end points

    public int[] findRightInterval(int[][] intervals) {
        int[] res = new int[intervals.length];
        
        HashMap<int[], Integer> map = new HashMap<>();
        for(int i = 0; i < intervals.length; i++) {
            res[i] = intervals[i][1]; // temperally store end
            map.put(intervals[i], i);
        }

        Arrays.sort(intervals, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                return a[0] - b[0];
            }
        });

        for(int i = 0; i < intervals.length; i++) {
            // int[] temp = search(intervals, res[i], 0, intervals.length - 1);
            int[] temp = search(intervals, res[i]);
            if(temp == null) res[i] = -1;
            else res[i] = map.get(temp);
        }
        return res;
    }

    private int[] search(int[][] intervals, int end, int l, int r){
        if(l > r) return null;
        if(l == r) {
            if(intervals[l][0] >= end) return intervals[l];
            else return null;
        }

        int mid = l + (r - l) / 2;
        if(intervals[mid][0] >= end) return search(intervals, end, l, mid);
        else return search(intervals, end, mid + 1, r);
    }
    private int[] search(int[][] intervals, int end){
        int l = 0, r = intervals.length - 1, mid;

        while(l < r) {
            mid = l + (r - l) / 2;

            if(intervals[mid][0] >= end) {
                r = mid;
            }else{
                l = mid + 1;
            }
        }

        if(intervals[l][0] >= end) return intervals[l];
        else return null;
    }
}
// @lc code=end

