import java.util.*;
/*
 * @lc app=leetcode id=214 lang=java
 *
 * [214] Shortest Palindrome
 */

// @lc code=start
class Solution {

    // KMP algorithm
    // 那个fallback数组，蕴含的是某个index到末尾等于头部到某个长度的东西
    // 所以它可以用来做回文判断

    // 必须是s + “#” + rev

    char[] arrayS;
    
    public String shortestPalindrome(String s) {
        if(s == null || s.length() < 2)
            return s;

        StringBuilder temp = new StringBuilder();
        temp.append(s);
        temp.reverse();
        String rev = temp.toString();
        temp = new StringBuilder();
        temp.append(s);
        temp.append('#');
        temp.append(rev);

        // s + "#" + rev

        arrayS = temp.toString().toCharArray();
        int[] fallback = new int[arrayS.length];

        // begin kmp
        int i = 1, tempIndex = 0;
        while(i < arrayS.length){
            tempIndex = fallback[i-1];
            while(tempIndex > 0 && arrayS[tempIndex] != arrayS[i]){
                tempIndex = fallback[tempIndex - 1];
            }

            // either be 0 or has a match
            if(arrayS[tempIndex] == arrayS[i])
                tempIndex++;

            fallback[i] = tempIndex;
            i++;
        }

        int extra_len = fallback[fallback.length - 1];
        temp = new StringBuilder();
        temp.append(rev.substring(0, s.length() - extra_len));
        temp.append(s);

        return temp.toString();
    }
}
// @lc code=end

