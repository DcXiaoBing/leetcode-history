/*
 * @lc app=leetcode id=84 lang=java
 *
 * [84] Largest Rectangle in Histogram
 */

// @lc code=start
class Solution {
    public int largestRectangleArea(int[] heights) {
        // maintain a decreasing queue. Such that all nodes between index is larger
        Deque<Integer> queue = new ArrayDeque<>();
        
        int res = 0;
        for(int i = 0; i < heights.length; i++) {
            while(!queue.isEmpty() && heights[queue.getLast()] >= heights[i]) {
                int last =  queue.removeLast();
                
                // i is not tall enough, so right border is i - 1
                if(queue.isEmpty()) res = Math.max(res, i * heights[last]);
                else res = Math.max(res, (i - 1 - queue.getLast()) * heights[last]);
            }
            
            res = Math.max(res, heights[i]); // sigle column
            if(queue.isEmpty()) res = Math.max(res, (i + 1) * heights[i]);
            else res = Math.max(res, (i - queue.getLast() + 1) * heights[queue.getLast()]);
            
            queue.add(i);
            // System.out.println(queue + " " + res);
        }
        
        // System.out.println(queue + " " + res);
        // handle the rectangle use heights.length as right border
        while(!queue.isEmpty()) {
            int last = queue.removeLast();
            if(queue.isEmpty()) res = Math.max(res, heights.length * heights[last]);
            else res = Math.max(res, heights[last] * (heights.length - queue.getLast() - 1));
            // System.out.println(queue + " " + res);
        }
        return res;
    }
}
// @lc code=end

