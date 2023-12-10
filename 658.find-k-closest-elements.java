import java.util.Arrays;

/*
 * @lc app=leetcode id=658 lang=java
 *
 * [658] Find K Closest Elements
 */

// @lc code=start
class Solution {
  // core idea for binary search, two different approach:
  // 1. find the nearest element. Then expand from that element
  // 2. Find the left most element. Core idea is, a[i] and a[i + k] cannot exist in final result in same time.
  public List<Integer> findClosestElements(int[] arr, int k, int x) {
    int l = 0, r = arr.length - k, mid;
    while(l < r) {
      mid = l + (r - l) / 2;

      // if(Math.abs(arr[mid] - x) > Math.abs(arr[mid + k] - x)) l = mid + 1;
      // !!! Condition cannot be ^^^^ this one.
      // Imagine a point x in a range. Cannot use abs. Because abs cannot handle
      // when point x is outside of range and arr[mid] == arr[mid + k].
      // You don't know the direction
      if (x - arr[mid] > arr[mid + k] - x) l = mid + 1;
      else r = mid;
    }
    Arrays.binarySearch(a, fromIndex, toIndex, key)

    List<Integer> res = new ArrayList<>();
    for (int i = l; i < l + k; i++) res.add(arr[i]);
    return res;
  }
}
// @lc code=end

