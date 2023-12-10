/*
 * @lc app=leetcode id=136 lang=java
 *
 * [136] Single Number
 */

// @lc code=start
class Solution {
    // two bit mask
    
    // ^: once, add in, twice eject
    // AND means can only use data in this
    
    /**
    this means accept digits not in twice
    if n appear first time:
    it will only show in once
    
    if n appear second time:
    it will eject from once, thus it will show in twice
    
    if n appear 3rd time:
    it could appear in once, but rejected by twice,
    because once don't contain this number, so it can appear in twice. But this time twice ejected it.

    And because bit operation can change order, so this holds for all numbers
    */
    
    
    // ~twice & (once ^ n)
    public int singleNumber(int[] nums) {
        int once = 0, twice = 0;
        
        for(int n : nums) {
            once = ~twice & (once ^ n);
            twice = ~once & (twice ^ n);
        }
        
        return once;
    }
}
// @lc code=end

