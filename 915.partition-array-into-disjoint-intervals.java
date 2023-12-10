import java.util.Arrays;

/*
 * @lc app=leetcode id=915 lang=java
 *
 * [915] Partition Array into Disjoint Intervals
 */

// @lc code=start
class Solution {
    // find min value position, add left most min to left part
    // [5,0,3,8,6] No~ include 0 is not enough

    // 1. compute lMax and rMin
    // 2. a brilliant way:
    // record a localMax. if all value in right is larger than localMax, we find the partation
    // If a value is smaller than this localMax, we know the partation need to be updated, so we update partation index and set localMax to globalMax of 0...i

    public int partitionDisjoint(int[] A) {
        int len = A.length, pIdx = 0, localMax = A[0], max = A[0];
        
        for(int i = 1; i < len; i++) {
            if(A[i] < localMax) {
                localMax = max;
                pIdx = i; // pIdx belong to left
            }else {
                max = Math.max(max, A[i]); // update global max
            }
        }

        return pIdx + 1;
    }
}
// @lc code=end

