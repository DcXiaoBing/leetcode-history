/*
 * @lc app=leetcode id=88 lang=java
 *
 * [88] Merge Sorted Array
 */

// @lc code=start
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        // first, we need to move all numbers in nums1 to its end
        for (int i = m - 1; i >= 0; i--) {
            nums1[i + n] = nums1[i];
        }

        int p1 = 0, p2 = 0, cur = 0;
        while (p1 < m && p2 < n) {
            if (nums1[p1 + n] < nums2[p2]) {
                nums1[cur++] = nums1[p1 + n];
                p1++;
            } else {
                nums1[cur++] = nums2[p2];
                p2++;
            }
        }

        while (p2 < n) {
            nums1[cur++] = nums2[p2++];
        }
    }
}
// @lc code=end
