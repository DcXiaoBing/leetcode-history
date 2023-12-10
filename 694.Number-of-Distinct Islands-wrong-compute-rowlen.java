import java.util.Arrays;

/*
 * @lc app=leetcode id=694 lang=java
 *
 * [694] Number of Distinct Islands
 */

// @lc code=start
class Solution {
  // abstract island to an 1-d array
  // each number in row represents the length of each row.
  // NONONO, even two rectangles have same row length, row's relative position might be different!!!

  // brute force match
  public int numDistinctIslands(int[][] grid) {
      List<int[][]> rectangles = new ArrayList<>();
      for(int i = 0; i < grid.length; i++) for(int j = 0; j < grid[0].length; j++) {
          if(grid[i][j] == 1) {
              int[][] rows = new int[2][grid.length];
              Arrays.fill(rows[0], 2000);
              Arrays.fill(rows[1], -2000);
              dfs(grid, rows, i, j);
              rectangles.add(rows);
          }
      }

      // System.out.println(rectangles.size());
      // rectangles.forEach((row) -> {
      //     System.out.println(Arrays.toString(row[0]));
      //     System.out.println(Arrays.toString(row[1]));
      //     System.out.println("=======");
      // });
      List<int[][]> uniques = new ArrayList<>();
      for(int i = 0; i < rectangles.size(); i++) {
          boolean unique = true;
          for(int j = 0; j < uniques.size() && unique; j++) if(i != j) {
              if(check(rectangles.get(i), uniques.get(j))) unique = false;
          }
          if(unique) uniques.add(rectangles.get(i));
      }
      return uniques.size();
  }

  // mark gird to 2 as visited
  private static int[] dx = new int[]{1, -1, 0, 0};
  private static int[] dy = new int[]{0, 0, 1, -1};
  private void dfs(int[][] grid, int[][] rows, int x, int y) {
      grid[x][y] = 2;
      rows[0][x] = Math.min(rows[0][x], y);
      rows[1][x] = Math.max(rows[1][x], y);

      for(int i = 0; i < dx.length; i++) {
          int nx = x + dx[i], ny = y + dy[i];
          if(nx < 0 || nx >= grid.length || ny < 0 || ny >= grid[0].length || grid[nx][ny] != 1) continue;
          dfs(grid, rows, nx, ny);
      }
  }

  private boolean check(int[][] l, int[][] r) {
      int p1l = 0, p1r = l[0].length - 1, p2l = 0, p2r = r[0].length - 1;
      while(l[0][p1l] == 2000) p1l++;
      while(r[0][p2l] == 2000) p2l++;
      while(l[1][p1r] == -2000) p1r--;
      while(r[1][p2r] == -2000) {p2r--;}
      // System.out.printf("l:%d,%d r:%d,%d %n", p1l, p1r, p2l, p2r);
      if(p1r - p1l != p2r - p2l) return false;
      while(p1l <= p1r) {
          // System.out.printf("l:%d, r:%d %n", l[1][p1l] - l[0][p1l], r[1][p2l] - r[1][p2l]);
          if(l[1][p1l] - l[0][p1l] != r[1][p2l] - r[0][p2l]) return false;
          p1l++;
          p2l++;
      }
      return true;
  }
}
// @lc code=end
