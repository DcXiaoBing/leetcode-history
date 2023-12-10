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
  // if row0 works with rowi and rowj, then rowi works with rowj
  // 2. If n is odd, there should be odd count of ones or odd count of zeros. Should try to put odd number to 1st col

// A new thing learned from official solution:
// - Since there would only be two types of rows, so the original row can only have two type!
// - And there could only be one type to be at first row, so we can just use the cnt of row.
public int movesToChessboard(int[][] b) {
  int m = b.length, n = b[0].length;
  HashMap<String, Integer> rows = new HashMap<>();
  HashMap<String, Integer> cols = new HashMap<>();
  for (int i = 0; i < m; i++) {
    StringBuilder sb = new StringBuilder();
    for (int j = 0; j < n; j++) sb.append(b[i][j]);
    String key = sb.toString();
    rows.put(key, rows.getOrDefault(key, 0) + 1);
  }
  for (int j = 0; j < n; j++) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < m; i++) sb.append(b[i][j]);
    String key = sb.toString();
    cols.put(key, cols.getOrDefault(key, 0) + 1);
  }

  int k1 = analyse(rows), k2 = analyse(cols);
  if (k1 == -1 || k2 == -1) return -1;
  return k1 + k2;
}

private int analyse(HashMap<String, Integer> map) {
  if (map.size() != 2) return -1; // two type of rows

  List<String> keys = new ArrayList<>(map.keySet());
  String key1 = keys.get(0), key2 = keys.get(1);
  if (Math.abs(map.get(key1) - map.get(key2)) > 1) return -1;

  // strings should be opposite.
  // and compute cnt of 1
  int cnt1 = 0, cnt2 = 0, n = key1.length(), swap = 0;
  for (int i = 0; i < n; i++) {
    char c1 = key1.charAt(i), c2 = key2.charAt(i);
    if (c1 == c2) return -1;
    if (c1 == '1') cnt1++;
    if (c2 == '1') cnt2++;
    if ((c1 - '0') == i % 2) swap++; // i % 2 == 0, we want 1. So we want 10101
  }
  if (cnt1 + cnt2 != n) return -1;

  if (n % 2 == 0) swap = Math.min(swap, n - swap);
  else if (swap % 2 == 1) swap = n - swap; // normally, swap has to be even due to symmetric

  return swap / 2;
}

//     public int movesToChessboard(int[][] board) {

//         // check all sub-rectangle
//         for(int i = 0; i < board.length; i++) for(int j = 0; j < board.length; j++)
//             if((board[0][0] ^ board[i][0] ^ board[0][j] ^ board[i][j]) == 1) return -1;

//         // count of 1 in one row and column
//         int n = board.length, rowCnt = 0, colCnt = 0, rowSwap = 0, colSwap = 0;
//         for(int i = 0; i < n; i++) {
//             rowCnt += board[0][i];
//             colCnt += board[i][0];

//             if(board[i][0] == i % 2) rowSwap++; // This means swap to 1010101
//             if(board[0][i] == i % 2) colSwap++;
//         }
//         // System.out.println("row " + rowSwap + " col" + colSwap);

//         // check one count. Must half zero, half one
//         if(rowCnt != n / 2 && rowCnt != (n+1) / 2) return -1; // 2 -> 1, 3 -> 2
//         if(colCnt != n / 2 && colCnt != (n+1) / 2) return -1;

//         // adjust swap count
//         if(n % 2 == 0) {
//             // n is even, 0,0 can be 1 or 0. rowSwap means the swap need to put 1 at 0.0
//             rowSwap = Math.min(rowSwap, n - rowSwap);
//             colSwap = Math.min(colSwap, n - colSwap);
//         } else {
//             // n is odd, 0,0 should be the majority number in first row.
//             // now is the swap to put 1 at 0,0. rowCnt % 2 == 0 means cnt of 1 is less than cnt of 0
//             if(rowSwap % 2 == 1) rowSwap = n - rowSwap;
//             if(colSwap % 2 == 1) colSwap = n - colSwap;
//         }

//         return (rowSwap + colSwap) / 2;
//     }
}
// @lc code=end

