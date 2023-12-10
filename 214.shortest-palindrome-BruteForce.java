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

    // !!! we can only add at beginning
    // try to add one, two, and so on
    // then we have a center, expand and check
    // worst case is we need to copy whole string

    // first, check function O(n)
    // seems we can only check each center and try to get min

    // but how to get min for a certain center
    // odd check and even check

    // can we dp?
    // for a from edge
    // it needs a pair or center at it self

    char[] arrayS;
    
    public String shortestPalindrome(String s) {
        arrayS = s.toCharArray();
        for(int i = 0; i < arrayS.length; i++){
            int left = 0,right = 0;

            // !!! need to consider the extra len
            // left = (arrayS.length + i) / 2;
            left = (arrayS.length + i) / 2 - i;
            if((arrayS.length + i) % 2 == 0){
                left--; // 8 / 2 = 4, 5th element. we need l = 3, r = 4
                right = left + 1;
            }else{
                right = left;
            }
            if(check(left, right)){
                return getRes(i);
            }
        }
        return "";
    }
    
    // when reach left boundary, we consider is as rest letters in right are matched
    boolean check(int left, int right){
        // System.out.println("left + right: " + left + ", " + right);
        while(left >= 0 && right < arrayS.length){
            if(arrayS[left] != arrayS[right])
                return false;

            left--;
            right++;
        }

        return true;
    }

    String getRes(int extra_len){
        // System.out.println(extra_len);
        StringBuilder res = new StringBuilder();
        for(int i = 0; i < extra_len; i++){
            res.append(arrayS[arrayS.length - 1 - i]);
        }

        res.append(arrayS);
        return res.toString();
    }
}
// @lc code=end

