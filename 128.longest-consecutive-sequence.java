import java.util.*;

/*
 * @lc app=leetcode id=128 lang=java
 *
 * [128] Longest Consecutive Sequence
 */

// @lc code=start
class Solution {
    // use hashset to expand from center
    public int longestConsecutive(int[] nums) {
        
        HashSet<Integer> set = new HashSet<>(nums.length);
        for(int n : nums) set.add(n);

        int res = 0;
        while(!set.isEmpty()) {
            int l, r;
            l = set.iterator().next();
            r = l + 1;

            while(set.contains(l)) set.remove(l--);
            while(set.contains(r)) set.remove(r++);
            
            res = Math.max(res, r - l - 1); // r and l are both exclusive
        }

        return res;
    }
}
// @lc code=end

