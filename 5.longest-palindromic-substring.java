import java.util.Arrays;

/*
 * @lc app=leetcode id=5 lang=java
 *
 * [5] Longest Palindromic Substring
 */

// @lc code=start
class Solution {

    // let's try Manacher's Algorithm
    // basic idea is that palindrom's symmetry contains information allow us to
    // avoid repeat comparation
    public String longestPalindrome(String s) {

        // deal with corner case input
        if (s == null || s.length() == 0)
            return "";

        // pre-process the string
        char nS[] = new char[s.length() * 2 + 3];
        int nLen = nS.length;

        nS[0] = '$';
        nS[nLen - 2] = '#';
        nS[nLen - 1] = '^';

        for (int i = 0, len = s.length(); i < len; i++) {
            nS[i * 2 + 1] = '#';
            nS[i * 2 + 2] = s.charAt(i);
        }
        // System.out.println(Arrays.toString(nS));

        // start to get the longest palindromic substring's start and end index
        int rightLen[] = new int[nLen];
        int curCenter = 0, curRight = 0, resCenter = 0;

        // skip first and last special letter
        for (int i = 1; i < nLen - 1; i++) {

            int symmetry = curCenter * 2 - i;
            if (i < curRight) {
                // can provide info
                rightLen[i] = Math.min(curRight - i, rightLen[symmetry]);
            }

            // continue try to expand
            while (nS[i - rightLen[i]] == nS[i + rightLen[i]]) {
                rightLen[i]++;
            }

            // update res
            if (rightLen[i] + i > curRight) {
                curRight = rightLen[i] + i;
                curCenter = i;
            }
            // record result
            if (rightLen[resCenter] < rightLen[i])
                resCenter = i;
        }

        // get the string
        StringBuilder temp = new StringBuilder();

        // rightLen arr is one step farther
        for (int i = resCenter - rightLen[resCenter] + 1; i < resCenter + rightLen[resCenter]; i++) {
            if (nS[i] != '#')
                temp.append(nS[i]);
        }

        return temp.toString();
    }
}
// @lc code=end
