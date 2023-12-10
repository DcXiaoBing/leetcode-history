/*
 * @lc app=leetcode id=45 lang=java
 *
 * [45] Jump Game II
 */
import java.util.*;
// @lc code=start
class Solution {

    // classic dp problem
    // dp[i] means the minimum step to reach index i
    // dp[i] = Math.min{all nodes reach can reach i}
    // TLE

    // BFS or say Greedy
    // record the furest node that current level can reach
    // then search in next level and so on
    // count the layer
    
    public int jump(int[] nums) {
        // corner case: no need to jump
        // in following case, it will add an extra one leads to error
        if(nums.length <=  1)
            return 0;
        int jumps = 0, curEnd = 0, curFurthest = 0;
        for(int i = 0; i <= curEnd && i < nums.length; i++){
            curFurthest = Math.max(curFurthest, i + nums[i]);
            if(curFurthest >= nums.length - 1)
                return jumps + 1;
            
            // when curEnd == 0, we add 1 here
            if(i == curEnd){
                // has explored all nodes in this level
                curEnd = curFurthest;
                jumps++;
            }
        }
        // not reachable
        return -1;
    }
}
// @lc code=end

