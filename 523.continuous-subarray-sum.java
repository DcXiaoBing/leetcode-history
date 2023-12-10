import java.util.HashMap;
import java.util.HashSet;

/*
 * @lc app=leetcode id=523 lang=java
 *
 * [523] Continuous Subarray Sum
 */

// @lc code=start
class Solution {
    // ! at least 2
    // seems like a sliding window problem?
    // JUST consider what will happen to two side of prefix sum
    
    // transfer all number to num % k?
    // YES. if sum[i] == sum[j].
    public boolean checkSubarraySum(int[] nums, int k) {        
        int sum = 0;
        HashMap<Integer, Integer> seen = new HashMap<>();
        seen.put(0, -1);
        k = Math.abs(k);
        for(int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if(k > 0) sum %= k;

            if(seen.containsKey(sum)){
                if(i - seen.get(sum) > 1)
                    return true;
            }else
                seen.put(sum, i);
        }
        return false;
    }
}
// @lc code=end

