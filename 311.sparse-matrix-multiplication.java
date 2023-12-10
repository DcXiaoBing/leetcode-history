import java.util.Arrays;

/*
 * @lc app=leetcode id=311 lang=java
 *
 * [311] Sparse Matrix Multiplication
 */

// @lc code=start
class Solution {
  //   public int[][] multiply(int[][] mat1, int[][] mat2) {
  //     int m = mat1.length, k = mat1[0].length, n = mat2[0].length;
  //     int res[][] = new int[m][n];

  //     for (int i = 0; i < m; i++) for (int j = 0; j < n; j++) {
  //       for (int p = 0; p < k; p++) res[i][j] += mat1[i][p] * mat2[p][j];
  //     }
  //     return res;
  //   }

    // Core idea:
    // 1. extract non-zero values
    // 2. compute number around non-zero values instead of positions. (naive method compute each position one by one).
    //    This is kind of depend on the data structure.

    // So bascially we have two way to store the extracted non-zero values.
    // 1. list of list. <row, <col, val>>
    // 2. Compressed Sparse Row CSR, Compressed Sparse Row CSC
    public int[][] multiply(int[][] mat1, int[][] mat2) {
      SparseMatrix A = new SparseMatrix(mat1), B = new SparseMatrix(mat2, true);
      // System.out.println(A.rows + " " + A.cols);
      // System.out.println(B.cols + " " + B.rows);

      int res[][] = new int[mat1.length][mat2[0].length];
      for (int i = 0; i < res.length; i++) for (int j = 0; j < res[0].length; j++) {

        int rowMin = A.rows.get(i), rowMax = A.rows.get(i + 1);
        int colMin = B.cols.get(j), colMax = B.cols.get(j + 1);

        // We only add when row == col
        // And because rows and cols are sorted, so we can use two pointer
        int row = rowMin, col = colMin;
        while (row < rowMax && col < colMax) {
          // System.out.println(row + " " + col);
          if (A.cols.get(row) < B.rows.get(col)) row++;
          else if (A.cols.get(row) > B.rows.get(col)) col++;
          else {
            res[i][j] += A.vals.get(row) * B.vals.get(col);
            row++;
            col++;
          }
        }

      }
      return res;
    }
  }


  class SparseMatrix {
    public int colCnt = 0, rowCnt = 0;
    public List<Integer> vals = new ArrayList<>();

    // it would have different meaning for CSR and CSC.
    public List<Integer> cols = new ArrayList<>();
    public List<Integer> rows = new ArrayList<>();

    public SparseMatrix(int[][] mat) {
      rowCnt = mat.length;
      colCnt = mat[0].length;

      rows.add(0); // range
      for (int i = 0; i < rowCnt; i++) {
        for (int j = 0; j < colCnt; j++) if (mat[i][j] != 0) {
          vals.add(mat[i][j]);
          cols.add(j);
        }
        rows.add(cols.size());
      }
    }

    public SparseMatrix(int[][] mat, boolean csc) {
      rowCnt = mat.length;
      colCnt = mat[0].length;

      cols.add(0); // range
      for (int j = 0; j < colCnt; j++) {
        for (int i = 0; i < rowCnt; i++) if (mat[i][j] != 0) {
          vals.add(mat[i][j]);
          rows.add(i);
        }
        cols.add(rows.size());
      }
    }
  }
// @lc code=end

