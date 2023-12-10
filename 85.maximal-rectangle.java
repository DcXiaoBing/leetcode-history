import java.util.*;

/*
 * @lc app=leetcode id=85 lang=java
 *
 * [85] Maximal Rectangle
 */

// @lc code=start
class Solution {
    // scan row from top to down
    // get continuous 1's height
    // use algorithm in 84
    public int maximalRectangle(char[][] matrix) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
        
        int heights[] = new int[matrix[0].length], res = 0;
        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[0].length; j++) {
                if(matrix[i][j] == '0') heights[j] = 0;
                else heights[j]++;
            }
            // System.out.println(Arrays.toString(heights));
            // increasing queue
            Deque<Integer> queue = new ArrayDeque<>();
            for(int j = 0; j < heights.length; j++) {
                // hanle the index only when poped out.
                // Becasue only that time, range is accurate
                while(!queue.isEmpty() && heights[queue.getLast()] >= heights[j]) {
                    int last = queue.removeLast();
                    // empty means last is lowest one in range 0 ~ j-1
                    // not empty, getLast()+1 ~ j-1, last is lowest one
                    if(queue.isEmpty()) res = Math.max(res, heights[last] * j);
                    else res = Math.max(res, heights[last] * (j-1 - queue.getLast()));
                }

                queue.add(j);
                // if(i == matrix.length - 1) System.out.println(res + " " + queue);
            }
            while(!queue.isEmpty()) {
                int last = queue.removeLast();
                if(queue.isEmpty()) res = Math.max(res, heights.length * heights[last]);
                else res = Math.max(res, (heights.length - queue.getLast() - 1) * heights[last]);
            }
        }
        return res;
    }
}
// @lc code=end

