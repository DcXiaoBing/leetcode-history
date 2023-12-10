/*
 * @lc app=leetcode id=880 lang=java
 *
 * [880] Decoded String at Index
 */

// @lc code=start
class Solution {
    // my stack solution passed
    // but we don't need stack, all information is in string
    public String decodeAtIndex(String S, int K) {
        long len = 0;
        int i;

        // decode until len is big enough to cover K
        for(i = 0; len < K; i++) {
            char c = S.charAt(i);

            if (c >= '0' && c <= '9') len *= c - '0';
            else len++;
        }
        i--; // offset the i++

        // try to get the char
        while(i >= 0) {
            char c = S.charAt(i);
            K %= len;

            if(K == 0 && c >= 'a' && c <= 'z') return c + "";
            if(c >= '0' && c <= '9') len /= c - '0';
            else len--;

            i--;
        }

        return null; // illegal
    }
}
// @lc code=end

