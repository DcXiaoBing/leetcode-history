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

    // compute lMax and rMin

    public int partitionDisjoint(int[] A) {
        int len = A.length, lMax[] = new int[len], rMin[] = new int[len];
        lMax[0] = A[0];
        rMin[len - 1] = A[len - 1];
        for(int i = 1; i < A.length; i++){
            lMax[i] = Math.max(A[i], lMax[i-1]);
            rMin[len - i - 1] = Math.min(A[len - i - 1], rMin[len - i]);
        }

        // System.out.println(Arrays.toString(lMax));
        // System.out.println(Arrays.toString(rMin));

        // i belong to left
        for(int i = 0; i < A.length - 1; i++) if(lMax[i] <= rMin[i + 1]) return i + 1; // transfer to len

        return -1; // guarantee partation exist, so unreachable
    }
}
// @lc code=end

