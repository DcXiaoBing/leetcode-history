import java.util.*;

/*
 * @lc app=leetcode id=1248 lang=java
 *
 * [1248] Count Number of Nice Subarrays
 */

// @lc code=start
class Solution {
    // make something like prefix sum. Then check n^2 pairs. Each pair taks O(1) time
    // sliding window? monoqueue? left differ * right differ
    // stack -> store index or counts of even number in this interval
    
    // actuall we don't need a stack
    // partation array to intervals
    public int numberOfSubarrays(int[] nums, int k) {
        List<Integer> intervals = new ArrayList<>();
        int res = 0, count = 0;

        for(int n : nums){
            if((n&1) == 0) count++;
            else {intervals.add(count); count = 0;}
        }
        intervals.add(count); // last interval. end at tail

        // System.out.println(intervals);
        for(int i = 0; i < intervals.size() - k; i++) {
            res += (intervals.get(i) + 1) * (intervals.get(i + k) + 1);
        }

        return res;
    }
}
// @lc code=end

