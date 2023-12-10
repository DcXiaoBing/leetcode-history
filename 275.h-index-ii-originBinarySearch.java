/*
 * @lc app=leetcode id=275 lang=java
 *
 * [275] H-Index II
 */

// @lc code=start
class Solution {
    public int hIndex(int[] citations) {
        if(citations.length == 0) return 0;
        int len = citations.length, l = 0, r = len - 1, mid, res = 0;
        while(l < r) {
            mid = l + (r - l) / 2 + 1; // make this belong to right part

            int c = len - mid; // mid is included
            if(c == citations[mid]) {
                return c;
            }else if(c < citations[mid]) { // legal, but want a better
                res = Math.max(res, c);
                r = mid - 1;
            }else if(c > citations[mid]) { // too much left, c is too big
                l = mid;
            }
        }

        // res in process, compare it with last position
        return Math.max(res, Math.min(len - l, citations[l]));
    }
}
// @lc code=end

