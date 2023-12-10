/*
 * @lc app=leetcode id=74 lang=java
 *
 * [74] Search a 2D Matrix
 */

// @lc code=start
class Solution {
    // notic the second condition is not same as 240.
    // use this as binary search!
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return false;

        int m = matrix.length, n = matrix[0].length;
        int l = 0, r = m * n - 1, mid;

        while(l <= r) {
            mid = l + (r - l) / 2;
            int row = mid / n, col = mid % n;
            
            if(matrix[row][col] == target) return true;
            else if(matrix[row][col] < target) l = mid + 1;
            else r = mid - 1;
        }
        return false;
    }
}
// @lc code=end

