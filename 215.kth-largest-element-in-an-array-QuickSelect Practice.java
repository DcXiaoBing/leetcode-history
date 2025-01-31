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

class Solution {
  Random ran = new Random();

  // k is 1-based largest, k-1 element lager, n-k element is smaller
  // so 1-based smallest is n - k + 1
  // idx be n-k
  public int findKthLargest(int[] nums, int k) {
    int n = nums.length;

    // return qs(nums, 0, n - 1, n - k);
    int tmp = qs(nums, 0, n - 1, n - k + 1);
    // System.out.println(tmp + " " + Arrays.toString(nums));
    return nums[n - k];
  }

  private int qs(int[] nums, int l, int r, int k) {
    if (l == r)
      return nums[l];

    int pivot = l + ran.nextInt(r - l + 1);
    int idx = partition(nums, l, r, pivot);
    // System.out.println(l + " " + r + " " + pivot + " " + idx + " " +
    // Arrays.toString(nums));
    if (idx == k - 1)
      return nums[idx];
    else if (idx > k - 1)
      return qs(nums, l, idx - 1, k);
    else
      return qs(nums, idx + 1, r, k);
  }

  private int partition(int[] nums, int l, int r, int pivot) {
    int pval = nums[pivot], idx = l;

    swap(nums, r, pivot);

    for (int i = l; i < r; i++) {
      if (nums[i] < pval) {
        swap(nums, idx, i);
        idx++;
      }
    }

    swap(nums, r, idx);

    // System.out.println(pval + " " + p);
    return idx;
  }

  private void swap(int[] nums, int l, int r) {
    int tmp = nums[l];
    nums[l] = nums[r];
    nums[r] = tmp;
  }
}
