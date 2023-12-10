import java.util.*;

/*
 * @lc app=leetcode id=60 lang=java
 *
 * [60] Permutation Sequence
 */

// @lc code=start
class Solution {
    // add 'one'(means choose next) to i-th digit, will add i!
    public String getPermutation(int n, int k) {
        int[] factorial = new int[10];
        for(int i = 1, base = 1; i <= 9; base *= ++i) factorial[i] = base;

        List<Integer> digits = new ArrayList<>();
        for(int i = 1; i <= n; i++) digits.add(i);

        StringBuilder sb = new StringBuilder();
        k--; // change k to 0 based
        for(int i = n - 1; i >= 1; i--) {
            int idx = k / factorial[i];
            k -= idx * factorial[i];
            sb.append(digits.get(idx));
            digits.remove(idx);
        }
        sb.append(digits.get(0)); // append last one
        return sb.toString();
    }
}
// @lc code=end

