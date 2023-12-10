/*
 * @lc app=leetcode id=315 lang=java
 *
 * [315] Count of Smaller Numbers After Self
 */

// @lc code=start
class Solution {
  // Change nums range to 0~2e4, so that we can use BIT or segment tree.
  // That is also a good solution.

  // use the idea of merge sort
  // one trick is sort the idxs
  public List<Integer> countSmaller(int[] nums) {
    int n = nums.length, idxs[] = new int[n], res[] = new int[n];
    for (int i = 0; i < n; i++) idxs[i] = i;

    mergeSort(nums, idxs, res, 0, nums.length - 1);
    // System.out.println(Arrays.toString(idxs));

    List<Integer> list = new ArrayList<>();
    for (int num : res) list.add(num);
    return list;
  }

  private void mergeSort(int[] nums, int[] idxs, int[] res, int l, int r) {
    if (l >= r) return;

    int mid = l + (r - l) / 2;
    mergeSort(nums, idxs, res, l, mid);
    mergeSort(nums, idxs, res, mid + 1, r);

    // merge two sub-question
    // If a number is in left part, means its idx must smaller than nums in right part
    // So if a number from right part jumps get merged first, rest nums in left part should +1 to res
    // But this is too slow, we have another trick.
    // If a left num get merges, cnt of merged right nums should be added to res
    // Use a large array might leads to TLE
    int temp[] = new int[r - l + 1], p = l, pl = l, pr = mid + 1;

    while(pl <= mid && pr <= r) {
      if(nums[idxs[pl]] <= nums[idxs[pr]]) { // left num get merged
        res[idxs[pl]] += pr - mid - 1;
        temp[p++ - l] = idxs[pl++];
      } else {
        temp[p++ - l] = idxs[pr++];
      }
    }

    // merge rest
    while(pl <= mid) {
      res[idxs[pl]] += r - mid; // r is included, mid is not
      temp[p++ - l] = idxs[pl++];
    }
    while(pr <= r) {
      temp[p++ - l] = idxs[pr++];
    }

    // write back
    for (int i = l; i <= r; i++) {
      idxs[i] = temp[i - l];
    }
  }
}
// @lc code=end

