import java.awt.List;
import java.util.ArrayList;
import java.util.HashSet;

/*
 * @lc app=leetcode id=264 lang=java
 *
 * [264] Ugly Number II
 */

// @lc code=start
class Solution {
    // main idea is generate
    // in my origin solution, I kept 3 list, and check head
    // Actually, can share all generated number in an arraylist, and maintain 3 pointer, l1, l2, l3 
    public int nthUglyNumber(int n) {
        List<Integer> nums = new ArrayList<>();
        HashSet<Integer> seen = new HashSet<>();
        nums.add(1);
        int l1 = 0, l2 = 0, l3 = 0;

        for(int i = 1; i < n; i++) {
            int num = Math.min(nums.get(l1) * 2, Math.min(nums.get(l2) * 3, nums.get(l3) * 5));
            
            // need to increase pointer first, otherwise will get infinite loop
            if(num == nums.get(l1) * 2) l1++;
            if(num == nums.get(l2) * 3) l2++;
            if(num == nums.get(l3) * 5) l3++;

            if(seen.contains(num)) continue;
            nums.add(num);
            // System.out.println(num);
        }
        return nums.get(nums.size() - 1);
    }
}
// @lc code=end

