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
    public List<Integer> lexicalOrder(int n) {
        List<Integer> res = new ArrayList<>();
        int len = (int)Math.log10(n) + 1;
        int[] digits = new int[len];

        dfs(res, n, digits, 0);

        return res;
    }
    private void dfs(List<Integer> list, int n, int[] digits, int curIdx){
        if(curIdx == digits.length || curIdx > 0 && digits[curIdx - 1] == -1){
            int num = getNum(digits);
            if(num <= n) list.add(getNum(digits));
            return ;
        }

        // -1 means empty
        for(int i = curIdx == 0 ? 1 : -1; i < 10; i++){
            digits[curIdx] = i;
            dfs(list, n, digits, curIdx + 1);
            digits[curIdx] = -1;
        }
    }

    private int getNum(int[] digits){
        int res = 0;
        for(int i = 0; i < digits.length; i++){
            if(digits[i] == -1) return res;
            res = res * 10 + digits[i];
        }

        return res;
    }
}
// @lc code=end

