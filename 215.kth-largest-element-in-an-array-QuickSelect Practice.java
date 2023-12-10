import java.util.*;
/*
 * @lc app=leetcode id=215 lang=java
 *
 * [215] Kth Largest Element in an Array
 */

// @lc code=start
class Solution {

    // quick select

    Random ran;

    public int findKthLargest(int[] nums, int k) {
        // transfer to smallest
        k = nums.length - k;
        ran = new Random();

        return quickSelect(nums, 0, nums.length - 1, k);
    }

    int quickSelect(int[] nums, int left, int right, int k) {

        // base case
        // and we are sure we can find the target
        if (left == right) {
            return nums[left];
        }

        // get pivot
        // ran do not include last int
        int pivotIndex = ran.nextInt(right - left + 1) + left;

        int smallCount = partation(nums, left, right, pivotIndex);
        if (smallCount == k) {
            return nums[k];
        } else if (smallCount > k) {
            return quickSelect(nums, left, smallCount - 1, k);
        } else {
            return quickSelect(nums, smallCount + 1, right, k);
        }
    }

    // THIS MUST START FROM L AND END AT R
    // Other wise it cannot handle [3,3,3,3,3] 3
    int partation(int[] nums, int left, int right, int pivotIndex) {
        // both left and right index are inclusive

        // put all elements smaller than target to left
        // all else to rigth
        int pivot = nums[pivotIndex], smallCount = 0;

        // swap pivot to right
        swap(nums, pivotIndex, right);

        // only need to care about how many is smaller
        // though right is inclusive, but we store pivot there
        for (int i = left; i < right; i++) {
            if (nums[i] < pivot) {
                swap(nums, i, smallCount + left);
                smallCount++;
            }
        }

        // swap pivot to its position
        swap(nums, right, smallCount + left);

        return smallCount + left; // this means how many smaller total
    }

    void swap(int[] nums, int l, int r) {
        int temp = nums[l];
        nums[l] = nums[r];
        nums[r] = temp;
    }

}
// @lc code=end
