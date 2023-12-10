/*
 * @lc app=leetcode id=686 lang=java
 *
 * [686] Repeated String Match
 */

// @lc code=start
class Solution {
    public int repeatedStringMatch(String A, String B) {
        int len1 = A.length(), len2 = B.length(), curLen = 0, count = 0;
        StringBuilder sb = new StringBuilder();

        while(curLen < len2) {
            curLen += len1;
            count++;
            sb.append(A);
        }

        if(sb.indexOf(B) >= 0) return count;
        else{
            sb.append(A); // some time need extra one to handle head and taile. eg abc cabca
            if(sb.indexOf(B) >= 0) return count+1;
        }

        return -1;
    }
}
// @lc code=end

