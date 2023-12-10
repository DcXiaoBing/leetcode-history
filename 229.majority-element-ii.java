import java.util.*;

/*
 * @lc app=leetcode id=229 lang=java
 *
 * [229] Majority Element II
 */

// @lc code=start
class Solution {
    // Boyer-Moore Voting Algorithm for more than one majority
    public List<Integer> majorityElement(int[] nums) {
        if(nums.length == 0) return new ArrayList<>();
        
        Integer c1 = null, c2 = null;
        int fre1 = 0, fre2 = 0;
        for(int n : nums) {
            if(c1 != null && c1 == n) fre1++;
            else if(c2 != null && c2 == n) fre2++;
            else if(fre1 == 0) {
                c1 = n;
                fre1 = 1;
            } else if(fre2 == 0) {
                c2 = n;
                fre2 = 1;
            } else {
                fre1--;
                fre2--;
            }
        }

        fre1 = fre2 = 0;
        for(int n : nums) {
            if(c1 == n) fre1++;
            else if(c2 == n) fre2++;
        }

        List<Integer> res = new ArrayList<>();
        if(fre1 > nums.length / 3) res.add(c1);
        if(fre2 > nums.length / 3) res.add(c2);

        return res;
    }
}
// @lc code=end

