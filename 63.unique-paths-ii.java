/*
 * @lc app=leetcode id=63 lang=java
 *
 * [63] Unique Paths II
 */

// @lc code=start
class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0 || obstacleGrid[0].length == 0)
            return 0;

        int rLen = obstacleGrid.length, cLen = obstacleGrid[0].length;
        if (obstacleGrid[0][0] == 1 || obstacleGrid[rLen - 1][cLen - 1] == 1)
            return 0;

        // begin dp

        // !if you want to use in-space algorithm, then pre-process is needed for all
        // cells

        obstacleGrid[0][0] = 1;
        for (int i = 1; i < rLen; i++) {
            // not blocked and node before is not blocked
            if (obstacleGrid[i][0] == 0 && obstacleGrid[i - 1][0] == 1) {
                obstacleGrid[i][0] = 1;
            } else
                obstacleGrid[i][0] = 0;
        }
        for (int j = 1; j < cLen; j++) {
            if (obstacleGrid[0][j] == 0 && obstacleGrid[0][j - 1] == 1)
                obstacleGrid[0][j] = 1;
            else
                obstacleGrid[0][j] = 0;
        }

        for (int i = 1; i < rLen; i++) {
            for (int j = 1; j < cLen; j++) {
                if (obstacleGrid[i][j] == 1)
                    obstacleGrid[i][j] = 0;
                else
                    obstacleGrid[i][j] = obstacleGrid[i - 1][j] + obstacleGrid[i][j - 1];
            }
        }

        return obstacleGrid[rLen - 1][cLen - 1];
    }
}
// @lc code=end
