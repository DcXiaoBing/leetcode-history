/*
 * @lc app=leetcode id=152 lang=java
 *
 * [152] Maximum Product Subarray
 */

// @lc code=start
class Solution {
    // worst case o(n)
    public int maxProduct(int[] nums) {
        int l = 0, r = 0, res = Integer.MIN_VALUE;
        while(r < nums.length) {
            while(r < nums.length && nums[r] == 0) {
                res = Math.max(res, 0);
                r++;
            }
            if(r == nums.length) return res; // all zero in tail
            
            // r points to first non-zero number
            l = r;
            int cur = nums[r++];
            while(r < nums.length && nums[r] != 0) {
                if(nums[r] != 0) cur *= nums[r];
                r++;
            }
            
            if(cur > 0) res = Math.max(res, cur);
            else {
                res = Math.max(res, cur);
                if(r - l > 1) // exclude single number
                    res = Math.max(res, getMax(l, r - 1, nums, cur));
            }
        }
        return res;
    }
    private int getMax(int l, int r, int[] nums, int cur) {
        int temp = cur, res = cur;
        while(temp < 0) temp /= nums[l++];
        res = temp;
        
        temp = cur;
        while(temp < 0) temp /= nums[r--];
        
        res = Math.max(res, temp);
        
        return res;
    }
}
// @lc code=end

