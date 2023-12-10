/*
 * @lc app=leetcode id=283 lang=java
 *
 * [283] Move Zeroes
 */

// @lc code=start
class Solution {
    // move non-zero to front
    // add zeros manually
    public void moveZeroes(int[] nums) {
        // corner case, no move needed
        if(nums == null || nums.length < 2)
            return;
        
        int cur = 0;
        for(int i = 0; i < nums.length; i++){
            if(nums[i] != 0)
                nums[cur++] = nums[i];
        }
        for(int i = cur; i < nums.length; i++)
            nums[i] = 0;

        return;
    }
}
// @lc code=end

