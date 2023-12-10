import java.util.*;

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
        result = n;
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

        // 越下层，用的数量必定越多，所以这里数量超了直接break
        if (curCount + alreadyUsed >= result)
            return;

        // deal with what remains
        while (curCount >= 0) {

            subCompute(curRemain, curSquareBase - 1, alreadyUsed + curCount);
            curCount--;
            curRemain += square;
        }
        return;
    }
}
// class Solution {
//     // bfs.
//     // use only 1 number, that is a layer
//     // use only 2 number, that is a layer
    
//     public int numSquares(int n) {
//         HashSet<Integer> seen = new HashSet<>();
//         List<Integer> nums = new ArrayList<>();
//         for(int i = 1; i * i <= n; i++) nums.add(i * i);

//         if(nums.get(nums.size() - 1) == n) return 1;

//         Deque<Integer> queue = new ArrayDeque<>(), next;
        
//         queue.add(n);
//         int cnt = 0;

//         while(!queue.isEmpty()) {
//             next = new ArrayDeque<>();
//             for(Integer cur : queue) {
//                 if(cur == 0) return cnt;

//                 for(Integer num : nums) {
//                     if(cur < num) break;
//                     if(seen.contains(cur - num)) continue;

//                     next.add(cur - num);
//                     seen.add(cur - num);
//                 }
//             }

//             queue = next;
//             cnt++;
//         }
//         return -1;// cannot find. Math prove every number can be find
//     }
// }


// bfs from 0. Causes too much status

// class Solution {
//     // coin change dp. n*n^0.5
    
//     // bfs. sepearate numbers by the min cnt
//     // cnt 3 can only form by cnt 2 numbers
    
//     // But do it in anohter direction!
//     // start from n can reduce status cnt
    
//     public int numSquares(int n) {
//         HashSet<Integer> seen = new HashSet<>();
//         for(int i = 1; i * i <= n; i++) seen.add(i * i);
        
//         int res = 1;
//         if(seen.contains(n)) return res;
        
//         Deque<Integer> queue = new ArrayDeque<>(seen), next;
//         while(!queue.isEmpty()) {
//             next = new ArrayDeque<>();
            
//             while(!queue.isEmpty()) {
//                 int cur = queue.poll();
//                 if(cur == n) return res;
                
//                 int nxt = cur + 1;
//                 for(int i = 1; nxt <= n; i++, nxt = cur + i * i) {
//                     // if(seen.contains(i * i + cur)) continue ;
//                     // seen.add(i * i + cur);
//                     // next.add(i * i + cur);
                    
//                     if(seen.contains(nxt)) continue ;
//                     seen.add(nxt);
//                     next.add(nxt);
//                 }
//             }
            
//             queue = next;
//             res++;
//         }
        
//         return -1; // not find
//     }
// }
// @lc code=end

