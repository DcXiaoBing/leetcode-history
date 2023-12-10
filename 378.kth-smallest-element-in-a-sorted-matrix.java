/*
 * @lc app=leetcode id=378 lang=java
 *
 * [378] Kth Smallest Element in a Sorted Matrix
 */

// @lc code=start
class Solution {
    public int kthSmallest(int[][] mat, int k) {
        int n = mat.length;

        if(k == 1) return mat[0][0];
        if(k == n * n) return mat[n-1][n-1];

        int l = mat[0][0], r = mat[n-1][n-1], mid;

        // all cnt >= 8 could be correct answer
        // e.g. expample in description, 13 count is 9, which is larger than 8
        // So we need to find smallest number whose cnt >= k
        // So we can only rule out a answer when cnt < k
        while(l < r) {
          /// Find the maximum number that satisfy the condition
          // Decrease only when not satisfy
          // mid = l + (r - l) / 2 + 1;
          // int cnt = search(mat, mid);
          // System.out.printf("mid:%d, l:%d, r:%d, cnt:%d %n", mid, l, r, cnt);
          // if(cnt > k) {
          //   r = mid - 1;
          // } else {
          //   l = mid;
          // }

          // find the minimum num that satisfy the condition
          // increase only when not satisfy
          mid = l + (r - l) / 2;
          int cnt = count(mat, mid);
          if (cnt >= k) r = mid;
          else l = mid + 1;
        }
        return l;
    }

    private int count(int[][] matrix, int target) {
        int n = matrix.length, res = 0;
        int row = n - 1, col = 0;

        while(row >= 0) {
            while(col < n && matrix[row][col] <= target) col++;
            // System.out.println(target + " " + row + " " + col);
            res += col;
            row--;
        }
        return res;
    }
}
// @lc code=end

