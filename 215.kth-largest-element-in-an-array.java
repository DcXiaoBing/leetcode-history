import java.util.*;
/*
 * @lc app=leetcode id=215 lang=java
 *
 * [215] Kth Largest Element in an Array
 */

// @lc code=start
class Solution {

    // quick select

    // add a Random for choosing pivot
    // always choose right will cose 30ms
    // but random one only takes 1
    Random ran;

    public int findKthLargest(int[] nums, int k) {
        // transfer to smallest
        k = nums.length - k;
        ran = new Random();

        return quickSelect(nums, 0, nums.length - 1, k);
    }

    int quickSelect(int[] nums, int left, int right, int k) {
        // always choose right as pivot
        // or choose a random pivot and swap it to right

        // base case
        if (left == right)
            return nums[left];

        int pivotIndex = left + ran.nextInt(right - left + 1);
        int rank = partation(nums, left, right, pivotIndex);
        if (rank == k) {
            return nums[rank];
        } else if (rank > k) {
            return quickSelect(nums, left, rank - 1, k);
        } else {
            return quickSelect(nums, rank + 1, right, k);
        }
    }

    int partation(int[] nums, int left, int right, int piovtIndex) {
        // always choose right as pivot

        int piovt = nums[piovtIndex], rank = left;
        swap(nums, right, piovtIndex);

        // !!! start at left
        for (int i = left; i < right; i++) {
            if (nums[i] < piovt) {
                swap(nums, i, rank);
                // number at nums[rank] has been checked
                // so move forward
                // rank <= i
                rank++;
            }
        }

        // swap pivot to its position
        swap(nums, rank, right);
        return rank; // total rank, not only in this part
    }

    void swap(int[] nums, int l, int r) {
        int temp = nums[l];
        nums[l] = nums[r];
        nums[r] = temp;
    }
}
// @lc code=end
