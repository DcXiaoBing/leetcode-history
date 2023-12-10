import java.util.Arrays;
import java.util.LinkedList;

/*
 * @lc app=leetcode id=368 lang=java
 *
 * [368] Largest Divisible Subset
 */

// @lc code=start
class Solution {
    
    // a number can be devided by set's largest number, then it can be added
    // a number can devide set's smallest number, then it can be added

    // dp[i] means the largest set's size with choosing number[i]

    // dfs 2^n choose or not choose
    public List<Integer> largestDivisibleSubset(int[] nums) {
        if(nums.length == 0) return new LinkedList<>();

        int dp[] = new int[nums.length];
        int prev[] = new int[nums.length];
        Arrays.fill(dp, 1);
        Arrays.fill(prev, -1);
        Arrays.sort(nums);
        
        int maxIdx = 0;
        for(int i = 1; i < nums.length; i++){
            for(int j = 0; j < i; j++){
                // need to check it is bigger
                if(nums[i] % nums[j] == 0 && 1 + dp[j] > dp[i]){
                    dp[i] = 1 + dp[j];
                    prev[i] = j;
                }
            }
            if(dp[maxIdx]< dp[i]){
                maxIdx = i;
            }
        }
        // System.out.println(Arrays.toString(dp));
        // System.out.println(Arrays.toString(prev));
        
        // use dp to regenerate the list
        int curIdx = maxIdx;
        List<Integer> res = new LinkedList<>();
        
        while(curIdx != -1){
            res.add(nums[curIdx]);
            curIdx = prev[curIdx];
        }
        return res;
    }
}
// @lc code=end

