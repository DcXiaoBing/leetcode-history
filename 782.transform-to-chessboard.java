/*
 * @lc app=leetcode id=782 lang=java
 *
 * [782] Transform to Chessboard
 */

// @lc code=start
class Solution {
    
    // two key thing:
    // 1. If a row is set, other rows should be same or inverse. If valid, any rectangle in board, its four corner should be 4 zeros, 2 ones and 2 zeros, 4 ones. So their xor result should be zero.
    // corner is b[0][0], b[i][0], b[0][j], b[i][j]
    // 2. If n is odd, there should be odd count of ones or odd count of zeros. Should try to put odd number to 1st col

    public int movesToChessboard(int[][] board) {

        // check all sub-rectangle
        for(int i = 0; i < board.length; i++) for(int j = 0; j < board.length; j++)
            if((board[0][0] ^ board[i][0] ^ board[0][j] ^ board[i][j]) == 1) return -1;

        // count of 1 in one row and column
        int n = board.length, rowCnt = 0, colCnt = 0, rowSwap = 0, colSwap = 0;
        for(int i = 0; i < n; i++) {
            rowCnt += board[0][i];
            colCnt += board[i][0];

            if(board[i][0] == i % 2) rowSwap++; // This means swap to 1010101
            if(board[0][i] == i % 2) colSwap++;
        }
        // System.out.println("row " + rowSwap + " col" + colSwap);

        // check one count. Must half zero, half one
        if(rowCnt != n / 2 && rowCnt != (n+1) / 2) return -1; // 2 -> 1, 3 -> 2
        if(colCnt != n / 2 && colCnt != (n+1) / 2) return -1;

        // adjust swap count        
        if(n % 2 == 0) { 
            // n is even, 0,0 can be 1 or 0. rowSwap means the swap need to put 1 at 0.0
            rowSwap = Math.min(rowSwap, n - rowSwap);
            colSwap = Math.min(colSwap, n - colSwap);
        } else {
            // swap count should be even, because if one col/row is in wrong place, there will be another col/row in wrong place.
            // n is odd. We trying to achieve 10101 sequnce
            // but swap count is odd, means 1 is not enough, a zero cannot find a pair 1. So we should put 0 at first.
            // Then swap is the count to put 1 to even index, n - swap will be the count to put 1 to odd index. 00011
            if(rowSwap % 2 == 1) rowSwap = n - rowSwap;
            if(colSwap % 2 == 1) colSwap = n - colSwap;
        }

        return (rowSwap + colSwap) / 2;
    }
}
// @lc code=end

