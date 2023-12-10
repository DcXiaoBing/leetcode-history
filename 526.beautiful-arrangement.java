import java.util.*;

/*
 * @lc app=leetcode id=526 lang=java
 *
 * [526] Beautiful Arrangement
 */

// @lc code=start
class Solution {
    // permutation
    // same frame as generating all permutation by swaping
    // but only swap when the number can fit in current index
    // So the time complexity will only be the time

    private int count = 0;
    public int countArrangement(int n) {
        int[] nums = new int[n];
        for(int i = 0; i < n; i++) nums[i] = i + 1;

        permute(nums, 0);
        return count;
    }

    private void permute(int[] nums, int cur) {
        if(cur == nums.length) {
            count++;
            return ;
        }

        for(int i = cur; i < nums.length; i++) { // cur is the index
            if(nums[i] % (cur + 1) == 0 || (cur + 1) % nums[i] == 0) {
                swap(nums, cur, i);
                permute(nums, cur + 1);
                swap(nums, cur, i);
            }
        }
    }

    private void swap(int[] nums, int l, int r) {
        int temp = nums[l];
        nums[l] = nums[r];
        nums[r] = temp;
    }
}
// @lc code=end

