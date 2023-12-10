/*
 * @lc app=leetcode id=260 lang=java
 *
 * [260] Single Number III
 */

// @lc code=start
class Solution {
    /**
     * 1. use XOR to get number with odd frequency
     * Then in this bit mask, it stores the differ of two number
     * -x = ~x + 1. (complement code)
     * -x and x has same last digit.
     * 
     * 2. use differ to get x.
     * Because we know y's this digit is 0, so we can only XOR number whose this digit is 1.
     * 
     * 3. get y
     * mask XOR x. (eject x)
     */
    public int[] singleNumber(int[] nums) {
        int mask = 0;
        for(int n : nums) mask ^= n;

        int diff = mask & (-mask), x = 0;
        for(int n : nums) if((n & diff) != 0) x ^= n;

        return new int[]{x, mask ^ x};
    }
}
// @lc code=end

