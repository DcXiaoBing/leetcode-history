/*
 * @lc app=leetcode id=279 lang=java
 *
 * [279] Perfect Squares
 */

// @lc code=start
class Solution {
    public int numSquares(int n) {
        int max = (int)Math.pow(n, 0.5);
        // System.out.println(max);
        helper(max, 0, n);
        return res;
    }
    int res = Integer.MAX_VALUE;
    private void helper(int cur, int cnt, int rest) {
        // System.out.println(cur + " " + cnt + " " + rest);
        if(rest == 0) {
            res = Math.min(res, cnt);
            return ;
        }
        if(cur == 1) {
            res = Math.min(res, cnt + rest); // min is 1
            return ;
        }
        if(cnt > res) {
            return ; // branch cut
        }

        for(int i = rest / (cur * cur), temp = rest - (cur*cur) * i; i >= 0; i--, temp += cur * cur) {
            helper(cur - 1, cnt + i, temp);
        }
    }
}
// @lc code=end

