import java.util.*;

/*
 * @lc app=leetcode id=719 lang=java
 *
 * [719] Find K-th Smallest Pair Distance
 */

// @lc code=start
class Solution {
    // quick select. Random pivot, median of medians. MLE
    // heap

    // we can sort then keep first k? No, big numbers can produce small dis
    // preprocess, only keep unique one

    // sort
    // same number produce 0s. count 0s is len * (len-1) / 2
    // get differ, then combine them

    // binary search
    // check function use sliding window
    // anohter check is in solution. Idea is using prefix
    // prefix[i] means how many numbers is less than num[i] in 0...i-1
    // preifx[i + mid] - prefix[i] + count of num[o]
    // And remember to compute

    public int smallestDistancePair(int[] nums, int k) {
        Arrays.sort(nums);
        int l = 0, r = nums[nums.length - 1] - nums[0], mid;

        while(l < r) {
            mid = l + (r - l) / 2;
            
            // iterate over all i, get min legal j. i is end point
            int cnt = 0;
            for(int i = 0, j = 0; i < nums.length; i++) {
                // when i == j, differ will be 0, so won't exceed boundary
                while(nums[i] - nums[j] > mid) j++;
                cnt += i - j; // j ~ i -1
            }
            // System.out.println(mid + " " + cnt);

            // if(cnt == k) return mid; // Can't directly return. Could have better result with same count. Eg. [1,1,3] mid = 1, 0
            if(cnt >= k) r = mid; // mid legal, try to find larger
            else l = mid + 1;
        }
        return l;
    }
}
// @lc code=end

