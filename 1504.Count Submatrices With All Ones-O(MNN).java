import java.util.Arrays;

/*
 * @lc app=leetcode id=1504 lang=java
 *
 * [1504] Count Submatrices With All Ones
 */

// @lc code=start
class Solution {
    // compress two dimension to one dimension
    // and iterate over all possible combination of j index
    public int numSubmat(int[][] mat) {
        int height[] = new int[mat[0].length], res = 0;

        for(int up = 0; up < mat.length; up++) {
            Arrays.fill(height, 1); // initialize it to 1
            for(int down = up; down < mat.length; down++) {
                for(int i = 0; i < mat[0].length; i++) height[i] &= mat[down][i];
                res += computeOneRow(height);
            }
        }
        return res;
    }

    private int computeOneRow(int[] row) {
        int len = 0, res = 0;

        // for 1 at index i
        // use it as right border, it can form 
        for(int i = 0; i < row.length; i++) {
            if(row[i] == 1) len++;
            else len = 0;
            res += len;
        }
        return res;
    }
}
// @lc code=end

