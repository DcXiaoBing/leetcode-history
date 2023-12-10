/*
 * @lc app=leetcode id=33 lang=java
 *
 * [33] Search in Rotated Sorted Array
 */

// @lc code=start
class Solution {
    // still binary search
    // use the sorted part to know where the target is
    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1, mid;

        // need to deal with equal
        while (left <= right) {
            mid = left + (right - left) / 2;

            if (nums[mid] == target)
                return mid;

            // check which part is sorted
            // need to use <= to deal with length of 2
            if (nums[left] <= nums[mid]) {
                // left part is sorted
                if (nums[left] <= target && target <= nums[mid])
                    right = mid - 1;
                else
                    left = mid + 1;
            } else {
                if (nums[mid] <= target && target <= nums[right])
                    left = mid + 1;
                else
                    right = mid - 1;
            }
        }

        // not found
        return -1;
    }
}
// @lc code=end
