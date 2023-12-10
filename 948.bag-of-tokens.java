import java.util.Arrays;

/*
 * @lc app=leetcode id=948 lang=java
 *
 * [948] Bag of Tokens
 */

// @lc code=start
class Solution {
    // sort and two pointer?
    public int bagOfTokensScore(int[] tokens, int P) {
        if(tokens.length == 0) return 0;
        Arrays.sort(tokens);
        int l = 0, r = tokens.length - 1, res = 0;
        if(P < tokens[0]) return 0;
        res = 1;
        l = 1;
        P -= tokens[0];

        while(l < r) {
            while(l <= r && P >= tokens[l]) {
                P -= tokens[l];
                res++;
                l++;
            }
            // System.out.println(res);
            if(l < r - 1){ // only turn down when has one more to turn
                P += tokens[r];
                res--;
                r--;
            }else {
                return res;
            }
        }

        return res;
    }
}
// @lc code=end

