import java.util.ArrayDeque;
import java.util.Deque;

/*
 * @lc app=leetcode id=42 lang=java
 *
 * [42] Trapping Rain Water
 */

// @lc code=start
class Solution {

    // dp solution can be improved
    // because max_height is a monotone increase value

    public int trap(int[] height) {
        if (height == null || height.length <= 2)
            return 0;

        int left = 0, right = height.length - 1;
        int lMax = height[0], rMax = height[height.length - 1];
        int res = 0;

        // 只有左边比右边小的时候才会left++
        // 所以,左边动的时候，lMax一定是比rMax小的or update lMax的时候
        // 紧接着就是right那边的问题了

        while (left < right) {

            if (height[left] < height[right]) {
                if (height[left] > lMax)
                    lMax = height[left];
                else
                    res += lMax - height[left];

                left++;
            } else {
                if (height[right] > rMax)
                    rMax = height[right];
                else
                    res += rMax - height[right];

                right--;
            }
        }

        return res;
    }
}
// @lc code=end
