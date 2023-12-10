/*
 * @lc app=leetcode id=440 lang=java
 *
 * [440] K-th Smallest in Lexicographical Order
 */

// @lc code=start
class Solution {
    // try the solution in 386
    // 386 lead to TLE

    // the solution is similar to the idea I had in origin answer. Solution uses a tree structure to simplify the step computation

    // consider two neighbour in same level.
    // In this level, step difference is 1
    // but in next level, step difference is difference of left most child eg. 1 - 10...19 2 - 20 ... 29
    // 1, 10, 100 ... if no limit

    int count = 0;
    int res = 0;
    public int findKthNumber(int n, int k) {
        k--; // k-th smallest need k-1 steps
        int cur = 1;

        while(k > 0){
            int step = getSteps(n, cur, cur + 1);
            if(step <= k){
                cur++;
                k -= step;
            }else{ // move to next level. Visit current node
                k--;
                cur *= 10;
            }
        }

        return cur;
    }

    // n1 and n2 has to be neighbour
    // n2 - n1 means count at this level
    // n2 *= 10, n1 *= 10 means try to compute in next level
    private int getSteps(int n, long n1, long n2){
        int res = 0;
        while(n1 <= n){
            res += Math.min(n + 1, n2) - n1; // n is included, n2 is not included, so need to +1 to transfer to non-included
            n1 *= 10;
            n2 *= 10;
        }
        return res;
    }
}
// @lc code=end

