import java.util.ArrayDeque;
import java.util.Deque;

/*
 * @lc app=leetcode id=42 lang=java
 *
 * [42] Trapping Rain Water
 */

// @lc code=start
class Solution {
    // another solution is using stack
    // use stack to store decending order sequence
    public int trap(int[] height) {
        if (height == null || height.length <= 2)
            return 0;

        Deque<Integer> stack = new ArrayDeque<>(); // store index
        int res = 0;

        for (int i = 0; i < height.length; i++) {
            while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
                int cur = stack.pop();

                // need a left boundary to store water
                if (stack.isEmpty())
                    break;

                int tempHeight = Math.min(height[stack.peek()], height[i]);
                res += (i - stack.peek() - 1) * (tempHeight - height[cur]); // peek and i is not included
            }

            stack.push(i);
        }

        return res;
    }
}
// @lc code=end
