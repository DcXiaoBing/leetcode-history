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
        Deque<Integer> stack = new ArrayDeque<>();

        int res = 0, count = 0;
        for(int i = 0; i < nums.length; i++) {
            if((nums[i]&1) == 0) {count++; continue;}

            if(stack.size() < k) {
                stack.addLast(count);
                count = 0;
            }else {
                res += (stack.removeFirst() + 1) * (count + 1);
            }
        }

        // handle last interval, to end of array. Need to check array size
        if(!stack.isEmpty() && stack.size() == k - 1){ // k = 1 is special, need to check empty
            res += 1 * (count + 1);
        }else if(stack.size() == k){
            res += (stack.removeFirst() + 1) * (count + 1);
        }

        return res;
    }
}
// @lc code=end

