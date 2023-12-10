import java.util.HashMap;
import java.util.Map;

/*
 * @lc app=leetcode id=1072 lang=java
 *
 * [1072] Flip Columns For Maximum Number of Equal Rows
 */

// @lc code=start
class Solution {
    // each row can be fliped or not. 2 ^ 300
    
    // store column index of 1 of each row to a list
    // list size 0 or n means all equal

    // if two row become all 0, then this two row is identical at start
    // if finally a row is all 0, and a row is all 1, the are reversed at start
    // so the question becomes finding the count of the row with most identical row or reversed row
    // group by hashmap
    
    // a smart way to group reversed row:
    // if row starts with 1, store origin string
    // if start with 0, store reversed/fliped string
    public int maxEqualRowsAfterFlips(int[][] matrix) {
        HashMap<String, Integer> fre = new HashMap<>();

        for(int[] row : matrix){
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < row.length; i++){
                if(row[0] == 1) sb.append(row[i]);
                else sb.append(1 - row[i]);
            }

            String key = sb.toString();
            fre.put(key, fre.getOrDefault(key, 0) + 1);
        }

        int res = 0;
        for(Map.Entry<String, Integer> e : fre.entrySet()){ res = Math.max(res, e.getValue());}

        return res;
    }
}
// @lc code=end

