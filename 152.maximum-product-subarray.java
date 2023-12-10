/*
 * @lc app=leetcode id=152 lang=java
 *
 * [152] Maximum Product Subarray
 */

// @lc code=start
class Solution {
    // dp
    // negative convert min to max, max to min 
    public int maxProduct(int[] nums) {
        if(nums.length == 0) return 0;
        
        int res, min, max;
        res = min = max = nums[0];

        for(int i = 1; i < nums.length; i++) {
            res = Math.max(res, nums[i]); // handle single number
            int temp = min; // store orign value.

            min = Math.min(nums[i], Math.min(max * nums[i], temp * nums[i]));
            max = Math.max(nums[i], Math.max(max * nums[i], temp * nums[i]));

            // System.out.println(min + " " + max + " " + nums[i]);
            res = Math.max(res, max);
        }
        return res;
    }
}
// @lc code=end

