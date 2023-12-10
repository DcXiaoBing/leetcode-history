/*
 * @lc app=leetcode id=438 lang=java
 *
 * [438] Find All Anagrams in a String
 */
import java.util.*;
// @lc code=start
class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new LinkedList<>();
        if(s.length() < p.length())
            return res;
        int[] cur_fre = new int[26], target_fre = new int[26];
        int target_count = 0, cur_count= 0;

        // pre-prcess p
        for(int i = 0, len = p.length(); i < len; i++){
            int temp_idx = p.charAt(i) - 'a';
            if(target_fre[temp_idx] == 0) target_count++;
            target_fre[temp_idx]++;
        }

        // find all anagram
        // initialize window
        for(int i = 0, len = p.length(); i < len; i++){
            int temp_idx = s.charAt(i) - 'a';
            cur_fre[temp_idx]++;
            if(cur_fre[temp_idx] == target_fre[temp_idx])
                cur_count++;
        }
        if(cur_count == target_count)
            res.add(0);

        // find the rest
        for(int i = p.length(), len = s.length(); i < len; i++){
            // new come in
            int temp_idx = s.charAt(i) - 'a';
            cur_fre[temp_idx]++;
            if(cur_fre[temp_idx] == target_fre[temp_idx])
                cur_count++;

            // eject old
            temp_idx = s.charAt(i - p.length()) - 'a';
            cur_fre[temp_idx]--;
            if(cur_fre[temp_idx] + 1 == target_fre[temp_idx])
                cur_count--;

            if(cur_count == target_count)
                res.add(i - p.length() + 1); // new head
        }

        return res;
    }
}
// @lc code=end

