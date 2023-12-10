import java.util.*;

/*
 * @lc app=leetcode id=15 lang=java
 *
 * [15] 3Sum
 */

// @lc code=start
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {

        if (nums == null || nums.length < 3)
            return new LinkedList<List<Integer>>();

        int left = 0, mid = 0, right = 0;
        int target = 0;
        Arrays.sort(nums);

        List<List<Integer>> res = new LinkedList<>();
        if (nums[0] > 0 || nums[nums.length - 1] < 0)
            return res;

        // set different left, then transform the problem to 2 Sum
        while (left < nums.length - 2) {
            target = -nums[left];
            mid = left + 1;
            right = nums.length - 1;

            // cannot check because target can be negative
            // if (nums[left + 1] > target || nums[nums.length - 1] < target) {
            // // skip same left
            // left++;
            // while (left < nums.length - 2 && nums[left] == nums[left - 1])
            // left++;
            // continue;
            // }

            // but we can do the check this way
            if (nums[left] > 0)
                break;

            while (mid < right) {
                if (nums[mid] + nums[right] == target) {
                    res.add(Arrays.asList(nums[left], nums[mid], nums[right]));

                    // skip same number
                    mid++;
                    while (mid < right && nums[mid] == nums[mid - 1])
                        mid++;
                    right--;
                    while (mid < right && nums[right] == nums[right + 1])
                        right--;
                } else if (nums[mid] + nums[right] < target) {
                    // skip same number
                    mid++;
                    // while (mid < right && nums[mid] == nums[mid - 1])
                    // mid++;
                } else {
                    // skip same number
                    right--;
                    // while (mid < right && nums[right] == nums[right + 1])
                    // right--;
                }
            }

            // skip same left
            left++;
            while (left < nums.length - 2 && nums[left] == nums[left - 1])
                left++;
        }

        return res;
    }
}
// @lc code=end
