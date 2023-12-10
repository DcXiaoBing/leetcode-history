import java.util.Arrays;

/*
 * @lc app=leetcode id=76 lang=java
 *
 * [76] Minimum Window Substring
 */

// @lc code=start
class Solution {
    // sliding window
    public String minWindow(String s, String t) {
        if(s.length() < t.length()) return ""; // corner case
        int lenS = s.length(), lenT = t.length();
        int freT[] = new int[128], cntT = 0; // cntT means the # of type of char
        int fre[] = new int[128], cnt = 0;
        for(int i = 0; i < lenT; i++) if(freT[t.charAt(i)]++ == 0) cntT++;

        int l = 0, resL = 0, resR = lenS;
        for(int i = 0; i < lenS; i++) {
            int idx = s.charAt(i);
            if(++fre[idx] == freT[idx]) cnt++;

            while(cnt == cntT) {
                if(resR - resL > i - l) {
                    resR = i;
                    resL = l;
                }

                idx = s.charAt(l++);
                if(fre[idx]-- == freT[idx]) cnt--;
            }
        }
        if(resR == lenS) return ""; // mismatch
        return s.substring(resL, resR + 1);
    }
}
// @lc code=end

