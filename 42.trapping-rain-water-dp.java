/*
 * @lc app=leetcode id=42 lang=java
 *
 * [42] Trapping Rain Water
 */

// @lc code=start
class Solution {
    // compute left max and right max
    // compute water for each column

    // another solution is using stack
    public int trap(int[] height) {
        if (height == null || height.length <= 2)
            return 0;

        int lMax[] = new int[height.length], rMax[] = new int[height.length];

        // pre-process
        lMax[0] = height[0];
        rMax[height.length - 1] = height[height.length - 1];

        for (int i = 1; i < height.length; i++) {
            lMax[i] = Math.max(height[i], lMax[i - 1]);
            rMax[height.length - 1 - i] = Math.max(height[height.length - 1 - i], rMax[height.length - i]);
        }

        int res = 0;
        for (int i = 0; i < height.length; i++) {
            res += Math.min(lMax[i], rMax[i]) - height[i];
        }

        return res;
    }
}
// @lc code=end
