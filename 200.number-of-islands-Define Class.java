import java.util.Deque;

/*
 * @lc app=leetcode id=200 lang=java
 *
 * [200] Number of Islands
 */

// @lc code=start
class Solution {

    // change visited cube to #
    // bfs or dfs both okay
    public int numIslands(char[][] grid) {
        int res = 0;
        
        // corner case
        if(grid == null || grid.length == 0 || grid[0].length == 0)
            return res;
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if(grid[i][j] == '1'){
                    res++;
                    bfs(grid, i, j);
                }
            }
        }
        // for(char[] row : grid)
            // System.out.println(Arrays.toString(row));
        return res;
    }

    class Coordinate{
        int x;
        int y;

        Coordinate(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    int[] dx = {1, -1, 0, 0};
    int[] dy = {0, 0, 1, -1};
    void bfs(char[][] grid, int i, int j){
        // need a queue
        Deque<Coordinate> queue = new LinkedList<>();
        queue.add(new Coordinate(i, j));

        while(!queue.isEmpty()){
            Coordinate cur = queue.poll();
            int curX = cur.x, curY = cur.y;

            // mark as visited
            if(curX <0 || curX >= grid.length || curY < 0 || curY >= grid[0].length)
                continue;

            // remember here
            if(grid[curX][curY] == '#' || grid[curX][curY] == '0')
                continue;
            // System.out.println("here");
            grid[curX][curY] = '#';

            // add neighbour to queue
            for(int k = 0; k < 4; k++){
                queue.add( new Coordinate(curX + dx[k], curY + dy[k]));
            }
        }
    }
}
// @lc code=end

