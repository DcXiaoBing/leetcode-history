/*
 * @lc app=leetcode id=79 lang=java
 *
 * [79] Word Search
 */

// @lc code=start
class Solution {

    // simple dfs
    public boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0 || board[0].length == 0)
            return false;

        char arrayW[] = word.toCharArray();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == arrayW[0]) {
                    board[i][j] = '#';
                    if (dfs(board, i, j, arrayW, 1))
                        return true;

                    board[i][j] = arrayW[0];
                }
            }
        }
        return false;
    }

    int dI[] = new int[] { 1, -1, 0, 0 };
    int dJ[] = new int[] { 0, 0, 1, -1 };

    boolean dfs(char[][] board, int curI, int curJ, char[] arrayW, int curIndex) {
        if (curIndex == arrayW.length)
            return true;

        for (int i = 0; i < 4; i++) {
            int nI = curI + dI[i];
            int nJ = curJ + dJ[i];

            if (nI < 0 || nI >= board.length || nJ < 0 || nJ >= board[0].length)
                continue;
            if (board[nI][nJ] != '#' && board[nI][nJ] == arrayW[curIndex]) {
                char temp = board[nI][nJ];
                board[nI][nJ] = '#';
                if (dfs(board, nI, nJ, arrayW, curIndex + 1))
                    return true;

                board[nI][nJ] = temp;
            }
        }

        return false;
    }
}
// @lc code=end
