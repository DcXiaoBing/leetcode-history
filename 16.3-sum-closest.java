/*
 * @lc app=leetcode id=16 lang=java
 *
 * [16] 3Sum Closest
 */

// @lc code=start

import java.util.*;

class Solution {
    public int threeSumClosest(int[] nums, int target) {
        int differ = Integer.MAX_VALUE, res = 0, left, right;
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {
            if (i == 0 || nums[i] != nums[i - 1]) {
                left = i + 1;
                right = nums.length - 1;

                while (left < right) {
                    int sum = nums[i] + nums[left] + nums[right];
                    if (sum == target)
                        return target;
                    else if (sum < target) {
                        left++;
                    } else {
                        right--;
                    }

                    int temp = Math.abs(sum - target);

                    if (temp < differ) {
                        differ = temp;
                        res = sum;
                    }
                }
            }
        }

        return res;
    }
}
// @lc code=end