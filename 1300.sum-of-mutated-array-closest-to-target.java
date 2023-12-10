import java.util.*;

/*
 * @lc app=leetcode id=1300 lang=java
 *
 * [1300] Sum of Mutated Array Closest to Target
 */

// @lc code=start
class Solution {
    // replace operation will definatly reduce the sum

    // ! not necessarilly in arr
    // binary search? check function? differ positve or negative.
    public int findBestValue(int[] arr, int target) {
        int sum = 0, max = 0;
        
        for(int n : arr) {
            sum += n;
            max = Math.max(max, n);
        }
        if(sum <= target) return max;

        int l = 1, r = max, mid;
        while(l < r) {
            mid = l + (r - l) / 2;
            
            sum = 0;
            for(int n : arr) if(n > mid) sum += mid; else sum += n;

            if(sum < target) l = mid + 1; // l belong to left part. so + 1
            else r = mid;
        }
        
        // still need to check l - 1
        // 对称性。l-1 l l+1,唯一需要担心的是l-1
        // 因为值小。 l+1 l-1 被淘汰，所以他们的sum差值最多和l相同
        int s1 = 0, s2 = 0;
        for(int n : arr) {
            if(n > l - 1) s1 += l - 1;
            else s1 += n;
            if(n > l) s2 += l;
            else s2 += n;
        }

        return (Math.abs(s1 - target) <= Math.abs(s2 - target)) ? l - 1 : l;
    }
}
// @lc code=end

