import java.util.*;
/*
 * @lc app=leetcode id=992 lang=java
 *
 * [992] Subarrays with K Different Integers
 */

// @lc code=start
class Solution {
    // compute somthing like prefixsum, then check each pair. O(n^2)

    // at most way
    public int subarraysWithKDistinct(int[] A, int K) {
        return atMost(A, K) - atMost(A, K - 1);
    }

    private int atMost(int[] A, int K){
        HashMap<Integer, Integer> fre = new HashMap<>();
        int l = 0, r = 0, res = 0;
        for(r = 0; r < A.length; r++) {
            fre.put(A[r], fre.getOrDefault(A[r], 0) + 1);
            if(fre.get(A[r]) == 1) K--; // a new number comes in

            while(K < 0) { // too much different number
                fre.put(A[l], fre.get(A[l]) - 1); 
                if(fre.get(A[l]) == 0) {
                    K++; // a number poped out
                    fre.remove(A[l]);
                }
                l++;
            }

            res += r - l + 1; // count of subarrays ending at r
        }
        return res;
    }
}
// @lc code=end

