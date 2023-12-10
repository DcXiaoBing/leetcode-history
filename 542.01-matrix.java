import java.util.*;

/*
 * @lc app=leetcode id=542 lang=java
 *
 * [542] 01 Matrix
 */

// @lc code=start
class Solution {
    // start at zeros, bfs

    public int[][] updateMatrix(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[][] distance = new int[m][n];
        for(int[] row : distance) Arrays.fill(row, Integer.MAX_VALUE);

        Deque<Cell> queue = new ArrayDeque<>(), next;
        for(int i = 0; i < m; i++) for(int j = 0; j < n; j++) {
            if(matrix[i][j] == 1) continue;
            
        }
    }
}

class Cell {
    int x, y;
}
// @lc code=end

