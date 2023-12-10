import java.util.*;
/*
 * @lc app=leetcode id=239 lang=java
 *
 * [239] Sliding Window Maximum
 */

// @lc code=start
class Solution {
    // dp, deque
    public int[] maxSlidingWindow(int[] nums, int k) {
        
        int[] res = new int[nums.length - k + 1];
        Deque<Integer> monoqueue = new ArrayDeque<>(); // decreasing queue. largest at head

        // sliding
        for(int i = 0; i < nums.length; i++) {

            while(!monoqueue.isEmpty() && monoqueue.getFirst() + k <= i)
                monoqueue.removeFirst();
            
            while(!monoqueue.isEmpty() && nums[monoqueue.getLast()] <= nums[i])
                monoqueue.removeLast();
            
            monoqueue.add(i);
            if(i >= k - 1) res[i - k + 1] = nums[monoqueue.getFirst()];
        }

        return res;
    }
}
// @lc code=end

