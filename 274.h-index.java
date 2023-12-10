import java.util.*;
/*
 * @lc app=leetcode id=274 lang=java
 *
 * [274] H-Index
 */
// []
// [0]
// [100]
// [11, 15]
// [0,1]
// [4,4,0,0]
// [3,0,6,1,5]
// @lc code=start
class Solution {
    // a good method is bucket sort
    // A great trick to enable this method is that: replace all number > len by len
    // this won't influence the result
    
    public int hIndex(int[] citations) {
        int buckets[] = new int[citations.length + 1];
        for(int n : citations) buckets[Math.min(n, citations.length)]++;

        // int res = citations.length, count = buckets[res];
        // while(res > count){
        //     res--;
        //     count += buckets[res];
        // }

        int count = 0;
        for(int i = citations.length; i >= 0; i--){
            count += buckets[i];
            if(count >= i) return i;
        }
        return 0; // unreachable code
    }
}
// @lc code=end

