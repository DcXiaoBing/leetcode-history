import java.util.*;

/*
 * @lc app=leetcode id=694 lang=java
 *
 * [694] Number of Distinct Islands
 */

// @lc code=start
class Solution {
  // CORE THING:
  // 1. We all start at top left corner.
  // 2. We use same direction.
  // So if two rectangles are same, we can ensure a same visit sequence.
  // And we can change absolute position to relative position to help use compare
  // things.

  // So, how to compute unique sequnce?
  // 1. Generate a string according to some rule, then use hashset
  //    a. record cell coordinate
  //    b. use signature. u,d,l,r. If go to some direction, append it to string
  //       check solution for more detail.
  //       Good part of this method is we don't need to compure relative coordinations.
  // 2. Use some data structure, like javafx.util.Pair

  public int numDistinctIslands(int[][] grid) {
    HashSet<String> uniques = new HashSet<>();
    for (int i = 0; i < grid.length; i++)
      for (int j = 0; j < grid[0].length; j++) {
        if (grid[i][j] == 1) {
          StringBuilder sb = new StringBuilder();
          List<int[]> list = new ArrayList<>();
          dfs(grid, list, i, j);
          int minCol = grid[0].length;
          for(int[] cell : list) minCol = Math.min(minCol, cell[1]);
          for(int[] cell : list)
            sb.append((cell[0] - i) + "," + (cell[1] - minCol) + ".");
          uniques.add(sb.toString());
        }
      }
    return uniques.size();
  }

  // mark gird to 2 as visited
  private static int[] dx = new int[] { 1, -1, 0, 0 };
  private static int[] dy = new int[] { 0, 0, 1, -1 };

  private void dfs(int[][] grid, List<int[]> list, int x, int y) {
    grid[x][y] = 2;
    list.add(new int[] { x, y });

    for (int i = 0; i < dx.length; i++) {
      int nx = x + dx[i], ny = y + dy[i];
      if (nx < 0 || nx >= grid.length || ny < 0 || ny >= grid[0].length || grid[nx][ny] != 1)
        continue;
      dfs(grid, list, nx, ny);
    }
  }
}
// @lc code=end
