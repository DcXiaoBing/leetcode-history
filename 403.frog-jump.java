/*
 * @lc app=leetcode id=403 lang=java
 *
 * [403] Frog Jump
 */

// @lc code=start
class Solution {
    // can compute the differ between stones
    // then we get an array
    // can we generate the gap
    // but it can skip some stone!!!

    // dp[i][k] whether it can reach end if start at stone i, with k
    // 1100 stone, so k at most can be 1100 (always + 1)
    
    Boolean dp[][];
    public boolean canCross(int[] stones) {
        // first jump always 1
        if(stones[1] > 1) return false;
        for(int i = 3; i < stones.length; i++) {
            if(stones[i] > stones[i-1]*2) {
                return false;
            }
        }

        dp = new Boolean[stones.length][stones.length];
        return dfs(stones, 0, 0);
    }

    Boolean dfs(int[] stones, int curIdx, int k){
        //base case
        if(curIdx == stones.length - 1) return true;
        
        // introduce dp
        if(dp[curIdx][k] != null) return dp[curIdx][k];

        // compute
        for(int i = curIdx + 1; i < stones.length; i++){
            if(stones[i] > stones[curIdx] + k + 1) break;
            // if(stones[curIdx] == 5) {
            //     System.out.println(k);
            //     System.out.println(curIdx + k + 1);
            // }
            if(stones[curIdx] + k - 1 <= stones[i] && stones[i] <= stones[curIdx] + k + 1){
                if(dfs(stones, i, stones[i] - stones[curIdx])){
                    dp[curIdx][k] = true;
                    return dp[curIdx][k];
                }
            }
        }
        dp[curIdx][k] = false;
        return dp[curIdx][k];
    }
}
// @lc code=end