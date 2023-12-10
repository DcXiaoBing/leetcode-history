import java.util.*;

/*
 * @lc app=leetcode id=1 lang=java
 *
 * [1] Two Sum
 */

// @lc code=start
class Solution {

    // HashMap

    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> wanted = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int want = target - nums[i];
            if (wanted.containsKey(want)) {
                return new int[] { wanted.get(want), i };
            }
            wanted.put(nums[i], i);
        }
        return null;
    }

    // two pointer
    // but sort distorts the index
    // public int[] twoSum(int[] nums, int target) {
    // Arrays.sort(nums);
    // int left = 0, right = nums.length - 1;

    // while (left < right) {
    // if (nums[left] + nums[right] == target)
    // return new int[] { left, right };
    // else if (nums[left] + nums[right] < target)
    // left++;
    // else
    // right--;
    // }
    // return null;
    // }
}
// @lc code=end
