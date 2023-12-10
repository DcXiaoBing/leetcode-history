import java.util.*;

/*
 * @lc app=leetcode id=526 lang=java
 *
 * [526] Beautiful Arrangement
 */

// @lc code=start
class Solution {
    // bitmask
    // the # of 1 bits is the number we need to assign now. In first version, I used a for loop to track this.
    
    // status: used idxs
    // tranfer: assign next number to this status
    public int countArrangement(int n) {
        List<List<Integer>> validIdxs = new ArrayList<>();
        for(int i = 0; i < n; i++) validIdxs.add(new ArrayList<>());
        
        for (int i = 1; i <= n; i++) { //number
            List<Integer> cur = validIdxs.get(i - 1);
            for(int j = 1; j <= n; j++) if(i % j == 0 || j % i == 0) cur.add(j - 1); // idx
        }

        int all = 1 << n, dp[] = new int[all];
        Arrays.fill(dp, -1);
        dp[0] = 1; // choose nothing, 1 choice

        // need to leave a space for new num!
        // so max can reach is all - 2
        for(int status = 0; status < all - 1; status++) if(dp[status] != -1) {
            int num = Integer.bitCount(status) + 1; // the number to assign

            for (Integer idx : validIdxs.get(num - 1)) {
                if((status >> idx & 1) != 0) continue ; // this idx has been used

                int nxt = status | (1 << idx);
                if(dp[nxt] == -1) dp[nxt] = 0;
                dp[nxt] += dp[status];
            }
        }

        return dp[all - 1];
    }
}
// @lc code=end

