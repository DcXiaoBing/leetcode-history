/*
 * @lc app=leetcode id=140 lang=java
 *
 * [140] Word Break II
 */
import java.util.*;
// @lc code=start
class Solution {
    // a good solution
    // for (String word : wordDict) {
    // if (s.startsWith(word)) {
    
    // this avoid the for loop of testing

    HashSet<String> words;
    char[] arrayS;
    List<String> dp[];
    public List<String> wordBreak(String s, List<String> wordDict) {
        
        words = new HashSet<>(wordDict);
        arrayS = s.toCharArray();
        dp = new List[arrayS.length];
        
        List<String> temp = helper(0);
        Collections.sort(temp);
        return temp;
    }

    // return a list of words start at this index
    List<String> helper(int curIndex){
        
        List<String> res = new LinkedList<>();

        // base case
        // list with empty string means success match
        if(curIndex == arrayS.length){
            res.add("");
            return res;
        }

        // introduce dp
        // it will not be modified, so shadow copy is enough
        if(dp[curIndex] != null){
            return dp[curIndex];
        }

        // compute
        String curWord = "";
        for(int i = curIndex; i < arrayS.length; i++){
            curWord = curWord + arrayS[i];
            // System.out.println(temp);
            if(words.contains(curWord)){
                // i has been processed, so i + 1
                List<String> tempL = helper(i + 1);
                
                for(String surfix : tempL){
                    if(surfix.length() == 0)
                        res.add(curWord);
                    else
                        res.add(curWord + " " + surfix);
                }
            }
        }
        // System.out.println(res.toString());
        dp[curIndex] = res;
        return res;
    }
}
// @lc code=end

