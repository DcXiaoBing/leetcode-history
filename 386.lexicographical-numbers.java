import java.lang.reflect.Array;
import java.util.*;

/*
 * @lc app=leetcode id=386 lang=java
 *
 * [386] Lexicographical Numbers
 */

// @lc code=start
class Solution {
    // this is much simpler
    // just a dfs

    // this solution is so elegant
    public List<Integer> lexicalOrder(int n) {
        List<Integer> res = new ArrayList<>();

        dfs(res, n, 1, 9);

        return res;
    }
    private void dfs(List<Integer> list, int n, int start, int end){
        for(int i = start; i <= end && i <= n; i++){
            list.add(i); // no trailing digit

            dfs(list, n, i * 10, i * 10 + 9); // add a digit
        }
    }
}
// @lc code=end

