import java.util.*;
/*
 * @lc app=leetcode id=768 lang=java
 *
 * [768] Max Chunks To Make Sorted II
 */

// @lc code=start
class Solution {
    // same number should stay in same chunk
    // n interval + same number interval(only need to record max and min)?
    // no no no, actually same number interval is merged automatically?
    // ! previous method needs all number to be distinct

    // combine numbers, get min max
    // then merge intervals
    // [5,4,3,2,1] outputs 5

    // !!!
    // same diea as 769
    // but change to: when max is left part is no larger than min in right, then we can make a split
    // if A1...Am is a chunk and A1...An is a chunk, then Am+1...An is also a chunk
    // so we only need to notice the count of An that can make a chunk A1...An
    // this dp is a faster way to find An

    // method1. use leftMax array and rigthMin array
    // method2. track satisfied number count. (whether origin equals sorted)
    // method3. see solution Approach 2

    public int maxChunksToSorted(int[] arr) {
        int len = arr.length, lMax[] = new int[arr.length], rMin[] = new int[arr.length];

        lMax[0] = arr[0];
        rMin[len - 1] = arr[len - 1];

        for(int i = 1; i < arr.length; i++){
            lMax[i] = Math.max(lMax[i - 1], arr[i]);
            rMin[len - i - 1] = Math.min(rMin[len - i], arr[len - i - 1]);
        }

        // right part has at least one number (because we don't process it)
        // so initial value is 1 and we are tring to make more
        int res = 1;
        for(int i = 0; i < len - 1; i++) if(lMax[i] <= rMin[i+1]) res++;

        return res;
    }
}
// @lc code=end

