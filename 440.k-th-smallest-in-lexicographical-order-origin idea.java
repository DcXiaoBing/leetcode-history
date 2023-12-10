import sun.launcher.resources.launcher;

/*
 * @lc app=leetcode id=440 lang=java
 *
 * [440] K-th Smallest in Lexicographical Order
 */

// @lc code=start
class Solution {
    // string compare

    // n is used for limit the length
    // append 0 is the most simple way to generate next smaller num

    // generate all number is not possible
    // so need a way to quickly compute possible counts
    // a number in i-th digit +1, then skipped 10^(restLen)?
    // maxLen = 2 n = 99
    // 1 10 11 ... 19 2 20 ... 29 3 30 ... 39
    // maxLen = 3
    // 1 10 100 101 102 103 ... 109 11 110 110

    // each digit can be empty, 0...9

    // seems like a generate problem
    // chars > len
    // or, generate all n numbers, then quick select
    public int findKthNumber(int n, int k) {
        int maxLen = (int)Math.log10(n) + 1; // count of digits
        int[] digits = new int[maxLen];
        
        // perform as upper limit
        for(int i = maxLen - 1; i>= 0; i--) {
            digits[i] = n % 10;
            n /= 10;
        }
        
        System.out.println(maxLen);
        return 0;
    }

    private int dfs(int[] upper, int[] curDigits, int curIdx, int curCount, int k, boolean limit){
        // not limited part
        int incre = (int)Math.pow(11, upper.length - curIdx);
        for(int i = curIdx == 0 ? 1 : -1; !limit && i < 10; i++){
            if(curCount + incre > k){
                return dfs(upper, curDigits, curIdx + 1, curCount, k, false);
            }else if(curCount + incre == k){
                curDigits[curIdx]++;
                return getNum(curDigits);
            }else{
                curCount += incre;
            }
        }

        // limited part
        // for(int i = -1; )

        return -1; // actually unreachable code
    }

    private int getNum(int[] digits){
        int res = 0;
        for(int i = 0; i < digits.length && digits[i] >= 0; i++)
            res = res * 10 + digits[i];
        return res;
    }
}
// @lc code=end

