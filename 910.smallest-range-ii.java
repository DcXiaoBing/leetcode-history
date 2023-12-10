import java.util.*;

/*
 * @lc app=leetcode id=910 lang=java
 *
 * [910] Smallest Range II
 */

// @lc code=start
class Solution {
    // Key point:
    // for two number a, b and a < b. The only option to minimize diff is a + k while b - k
    // It is simple, there are only 4 option: a+ b+, a- b-, a- b+, a+ b-
    // First two option will keep the diff, third option will increase the diff
    // So, basic solution is, sort all, then check a+ b- for neighbour pairs
    // The reason to just check neighbour pair is, a[i], a[i+1], a[i+2] (sorted)
    // a[i] + k - a[i+2] + k < a[i] + k - a[i+1] + k
    
    // Improvement:
    // A idea: add K for all element in B, diff is same. So we can change this to add 0 or 2K
    // Two case worth notice:

    // a. max - min >= 4 * K. 
    // This means max - K is still larger than any element + K.
    // min + K is still smaller than any element - K.
    // So we can return max - min - 2 * K.

    // b. max - min <= K
    // This means any element + K will increase max, any element - K will decrease min
    // So best choice is to +K or -K for all element

    public int smallestRangeII(int[] A, int K) {
        int res = 0, min = A[0], max = A[0];
        for(int i = 1; i < A.length; i++) {
            min = Math.min(min, A[i]);
            max = Math.max(max, A[i]);
        }

        if(max - min <= K) return max - min;
        if(max - min >= 4 * K) return max - min - 2 * K;

        // now care about number between [max - 2K, min + 2K]
        // numbers larger than (max - 2K) will larger than max if max - k, num + k
        // same for min + 2K
        // So we need to check all neighbour pair
        List<Integer> temp = new ArrayList<>();
        int lower = min, upper = max; // the number just lower than max-2K and larger than min+2K

        for(int a : A) {
            if (a < max - 2 * K) lower = Math.max(lower, a);
            else if (a > min + 2 * K) upper = Math.min(upper, a);
            else temp.add(a); // is in the range
        }
        temp.add(lower);
        temp.add(upper);

        // now check each pair
        res = max - min; // initial value
        Collections.sort(temp);
        // System.out.println(temp + " " + min + " " + max);

        for (int i = 0; i < temp.size() - 1; i++) {
            int l = Math.min(min + K, temp.get(i + 1) - K); // the true lower boundary
            int r = Math.max(max - K, temp.get(i) + K); // the true higher boundary
            // System.out.println(l + " " + r);
            res = Math.min(res, r - l);
        }
        return res;
    }
}
// @lc code=end

