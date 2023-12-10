/*
 * @lc app=leetcode id=930 lang=java
 *
 * [930] Binary Subarrays With Sum
 */

// @lc code=start
class Solution {
    // sum S
    // prefix sum, check all pairs in O(n^2) time

    // sliding window
    // or at most method
    
    public int numSubarraysWithSum(int[] A, int S) {
        return atMost(A, S) - atMost(A, S - 1);
    }
    private int atMost(int[] A, int S) {
        int l = 0, res = 0, sum = 0;
        for(int r = 0; r < A.length; r++) {
            sum += A[r];
            while(l <= r && sum > S) sum -= A[l++];

            res += r - l + 1;
        }
        return res;
    }
}
// @lc code=end

