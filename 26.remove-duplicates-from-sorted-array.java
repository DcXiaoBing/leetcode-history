/*
 * @lc app=leetcode id=26 lang=java
 *
 * [26] Remove Duplicates from Sorted Array
 */

// @lc code=start
class Solution {
    public int removeDuplicates(int[] nums) {

        // deal with corner input
        if (nums == null || nums.length == 0)
            return 0;

        int realLen = 1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != nums[realLen - 1]) {
                nums[realLen] = nums[i];
                realLen++;
            }
        }

        return realLen;
    }
}
// @lc code=end
