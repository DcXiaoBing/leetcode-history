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
    
    public int jump(int[] nums) {
        int dp[] = new int[nums.length];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        List<Integer> temp[] = new List[nums.length];
        for(int i = 0; i < nums.length; i++) temp[i] = new LinkedList<>();
        
        // get reachable list
        for(int i = 0; i < nums.length; i++){
            for(int j = i + 1; j < nums.length && j <= i + nums[i]; j++){
                temp[j].add(i);
            }
        }
        
        for(int i = 1; i < nums.length; i++){
            for(Integer before : temp[i]){
                dp[i] = Math.min(dp[i], dp[before] + 1);
            }
        }
        
        return dp[dp.length - 1];
    }
}
// @lc code=end

