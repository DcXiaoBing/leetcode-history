import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/*
 * @lc app=leetcode id=1504 lang=java
 *
 * [1504] Count Submatrices With All Ones
 */

// @lc code=start
class Solution {

    // monoqueue
    // height maintain the continously 1's length

    public int numSubmat(int[][] mat) {
        int height[] = new int[mat[0].length], res = 0;

        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++)
                if (mat[i][j] == 0) height[j] = 0;
                else height[j]++;

            res += compute(height);
        }

        return res;
    }

    private int compute(int[] height) {
        Deque<Integer> stack = new ArrayDeque<>(); // mono-increasing queue
        int res = 0, dp[] = new int[height.length];

        for (int i = 0; i < height.length; i++) {
            while (!stack.isEmpty() && height[i] <= height[stack.peekLast()])
                stack.removeLast();

            /**
             * stack is empty, all nums previous is larger than this one consider all
             * possible matrix using this row as bottom, i as right boundary
             * 
             * 1. all nums before i is larget than i
             * for each height h from 1 to height[i], we can form (i + 1) submatrix using i as right border. Eg. height 1, len 1, 2, ..., i + 1
             * So total is h * (i + 1)
             * 
             * 2. some index pre, height[pre] < height[i]
             * a. we can get dp[pre] count submatrix by extending all legal submatrix in dp[pre] right border to i
             * b. for columns from (pre + 1) to i, they are not considered in dp[pre]. So like in 1., we can get (i - pre) submatrix for each height from 1 to height[i]
             */
            if (stack.isEmpty()) dp[i] = height[i] * (i + 1);
            else dp[i] = dp[stack.getLast()] + height[i] * (i - stack.getLast());

            stack.addLast(i);
        }
        for(int n : dp) res += n;
        return res;
    }
}
// @lc code=end
