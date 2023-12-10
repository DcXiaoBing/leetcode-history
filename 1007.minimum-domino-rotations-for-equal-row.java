/*
 * @lc app=leetcode id=1007 lang=java
 *
 * [1007] Minimum Domino Rotations For Equal Row
 */

// @lc code=start
class Solution {
    // THIS CODE IS IN LEETCODE SUBMISSION. FIRST SESSION
    // find majority numbe Boyer–Moore majority vote algorithm 
    // try to find this element at each index, if cannot find, return false
    // mantian a counter for a and b

    // !!! A Better Idea
    // A[0] or B[0] must be the target number
    // otherwise it cannot form such thing
    // then we can delete the voting process.
    public int minDominoRotations(int[] A, int[] B) {
        int res = test(A, B, A[0]);

        // if we find an answer, it is it
        // when there are only two number, the answer is same no matter which number to choose
        if(res != -1 || A[0] == B[0]) return res;
        
        // when reach here, res cannot be -1
        // it means A[0] cannot be target
        return test(A, B, B[0]);
    }
    private int test(int[] A, int[] B, int target){
        int c1 = 0, c2 = 0;

        for(int i = 0; i < A.length; i++){
            // System.out.println(i + " " + c1 + " " + c2);
            if(A[i] != target && B[i] != target) return -1; // need target numebr at this index
            else if(A[i] == target && B[i] != target) c2++; // rotate one in B
            else if(A[i] != target && B[i] == target) c1++; // rotate one in A
        }

        return Math.min(c1, c2);
    }
}
// @lc code=end

