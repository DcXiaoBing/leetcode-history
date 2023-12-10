import java.util.Arrays;

/*
 * @lc app=leetcode id=691 lang=java
 *
 * [691] Stickers to Spell Word
 */

// @lc code=start
class Solution {

    // dp
    // use bitmap as idx
    // in bitmap, i-th bit in idx means whether it is satisfied
    // in this method, each char in target is corresponding to a digit

    // consider each state as a node in a graph
    // each state has an edge if one state can reach by using one word
    // state increase monotonously

    public int minStickers(String[] stickers, String target) {
        int len = target.length();
        int dp[] = new int[1 << len];

        Arrays.fill(dp, -1); // -1 means this state is unaccessible
        dp[0] = 0;

        for (int i = 0; i < 1 << len; i++) {
            if (dp[i] == -1)
                continue; // this stat is unaccessible

            for (String s : stickers) {
                int next = i;
                for (char c : s.toCharArray()) {
                    for(int j = 0; j < len; j++){
                        if(((next >> j) & 1) == 1) continue; // letter at j has been statisfied

                        if(target.charAt(j) == c){
                            next |= 1 << j;
                            break;
                        }
                    }
                }

                // can change value
                if(dp[next] == -1 || dp[next] > 1 + dp[i]){
                    dp[next] = 1 + dp[i];
                }
            }
        }

        return dp[(1 << len) - 1];
    }
}
// @lc code=end
