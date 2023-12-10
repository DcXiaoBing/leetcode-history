/*
 * @lc app=leetcode id=275 lang=java
 *
 * [275] H-Index II
 */

// @lc code=start
class Solution {
    public int hIndex(int[] citations) {
        int len = citations.length, l = 0, r = len - 1, mid;
        while(l <= r){
            mid = l + (r - l) / 2;
            
            int c = len - mid; // mid belong to right part
            if(c == citations[mid]) return c;
            else if(c > citations[mid]) { // c is too long, we can find a better one, so at least exist one better solution
                l = mid + 1;
            }else{
                r = mid - 1;
            }
        }

        // when l == r
        // if c < citations[mid], l stay same, else l++ for better one
        return len - l; // c >= citations[l]
    }
}
// @lc code=end

