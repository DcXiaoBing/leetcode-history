import java.util.*;

/*
 * @lc app=leetcode id=769 lang=java
 *
 * [769] Max Chunks To Make Sorted
 */

// @lc code=start
class Solution {
    // split into chunks, sort each chunk respectively, then array is sorted
    // want the max count of chunks
    
    // a number should in a chunk that contains its position
    // binary search? 1~n

    // each number has a offset which means the distance to its target postion. They should be in a same chunk.
    // So, we have n intervals sorted in start time
    // merge interval that overlaps
    public int maxChunksToSorted(int[] arr) {
        List<int[]> intervals = new ArrayList<>();

        for(int i = 0; i < arr.length; i++) {
            int interval[] = new int[]{i, arr[i]};
            Arrays.sort(interval);
            addInterval(intervals, interval);

            // System.out.println(Arrays.toString(interval));
            // for(int[] it : intervals) System.out.print(Arrays.toString(it) + " ");
            // System.out.println();
            // System.out.println("====");
        }

        return intervals.size();
    }
    
    // interval managed as non-overlapped sorted intervals
    private void addInterval(List<int[]> intervals, int[] interval){
        for(int i = 0; i < intervals.size(); i++){
            int cur[] = intervals.get(i);
            if(cur[1] < interval[0]) continue;
            if(interval[1] < cur[0]){
                intervals.add(i, interval);
                return ;
            }

            // need merge
            cur[0] = Math.min(cur[0], interval[0]);
            cur[1] = Math.max(cur[1], interval[1]);
            return ;
        }

        // add to tail
        intervals.add(interval);
    }
}
// @lc code=end

