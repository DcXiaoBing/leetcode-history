import java.util.Arrays;

/*
 * @lc app=leetcode id=279 lang=java
 *
 * [279] Perfect Squares
 */

// @lc code=start
// class Solution {
//     public int numSquares(int n) {
//         int dp[] = new int[n + 1], max = (int)Math.pow(n, 0.5);
//         Arrays.fill(dp, Integer.MAX_VALUE);
//         dp[0] = 0;
//         dp[1] = 1;

//         // try from max to min
//         // for each val, try each coin
//         for(int i = 2; i <= n; i++) for(int j = 1; j <= max; j++) {
//             if(i - j * j < 0) break;
//             dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
//         }
//         return dp[n];
//     }
// }
class Solution {
    int result;// 默认为0

    public int numSquares(int n) {
        // 这个和硬币凑某个数字有点像
        // 不过因为这里必定有1保底，所以不会存在找不到的情况

        // 找钱那个是dp，每一步都探索所有可能
        // 还有一个贪心+剪枝

        // 找到了一个inclusive的上限
        // 1～这个上限都是可选的
        result = Integer.MAX_VALUE;
        int maxSquareBase = (int) Math.pow(n, 0.5);
        subCompute(n, maxSquareBase, 0);
        return result;
    }

    void subCompute(int target, int curSquareBase, int alreadyUsed) {
        // 写贪心+剪枝的

        // base case
        if (target == 0)
            return;

        int square = curSquareBase * curSquareBase;
        int curCount = target / (square);
        int curRemain = target % square;

        // can be done at this level
        if (curRemain == 0) {
            result = result < alreadyUsed + curCount ? result : alreadyUsed + curCount;
            return;
        }

        // deal with what remains
        while (curCount >= 0) {
            // 越下层，用的数量必定越多，所以这里数量超了直接break
            if (curCount + alreadyUsed >= result)
                return;
            subCompute(curRemain, curSquareBase - 1, alreadyUsed + curCount);
            curCount--;
            curRemain += square;
        }
        return;
    }
}
// @lc code=end

