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

    // yes! sliding window is possible
    // iterate all ending position, try to find left most start position
    // all subarrays satisfy at most k property
    public int numberOfSubarrays(int[] nums, int k) {
        return atMost(nums, k) - atMost(nums, k - 1);
    }
    public int atMost(int[] nums, int k) {
        int res = 0, l = 0, r = 0;

        // in following code. r will stay at each number once
        // l will stay at left most node which satisfy the restriction
        // at most k minus at most k-1
        // r means at most k, l means at most k-1
        while(r < nums.length) {
            k -= (nums[r++] & 1);
            while(k < 0) k += (nums[l++] & 1); // 0~k means k+1 intervals

            res += r - l + 1;
        }


        return res;
    }
}
// @lc code=end

