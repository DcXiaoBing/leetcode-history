/*
 * @lc app=leetcode id=11 lang=java
 *
 * [11] Container With Most Water
 */

// @lc code=start
class Solution {

    // dp get max from right and left
    // two pointer
    public int maxArea(int[] height) {
        int res = 0, left = 0, right = height.length - 1;
        while (left < right) {
            int curHeight = Math.min(height[left], height[right]);
            res = Math.max(res, curHeight * (right - left));

            // move pointers
            if (height[left] < height[right])
                left++;
            else
                right--;
        }
        return res;
    }
}
// @lc code=end
