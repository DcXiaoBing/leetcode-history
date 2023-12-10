import java.util.*;

/*
 * @lc app=leetcode id=49 lang=java
 *
 * [49] Group Anagrams
 */

// @lc code=start
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        // design a hash
        // anagram has same hashcode
        // fre to string

        int fre[];
        HashMap<String, List<String>> freToStr = new HashMap<>();

        for (String s : strs) {
            fre = new int[26];

            // for (int i = 0, len = s.length(); i < len; i++) {
            // char cur = s.charAt(i);
            // fre[cur - 'a']++;
            // }
            for (char c : s.toCharArray()) {
                fre[c - 'a']++;
            }
            String temp = Arrays.toString(fre);
            List<String> anagrams = freToStr.get(temp);
            if (anagrams == null) {
                anagrams = new ArrayList<>();
                freToStr.put(temp, anagrams);
            }
            anagrams.add(s);
        }

        List<List<String>> res = new LinkedList<>();
        for (Map.Entry<String, List<String>> e : freToStr.entrySet()) {
            res.add(e.getValue());
        }

        return res;
    }
}
// @lc code=end
