import java.util.*;

/*
 * @lc app=leetcode id=187 lang=java
 *
 * [187] Repeated DNA Sequences
 */

// @lc code=start
class Solution {
    // two methods:
    // 1. Rabin-Karp, compute hash. Because length is 10, So no chance to get a hash collision caused by overflow
    // 2. Bitmap. Because char set only has 4 characters, so we can use two digit to encode a char, then use 20bit to represent the string with length 10
    public List<String> findRepeatedDnaSequences(String s) {
        
        int map = 0, mask = 0xFFFFF, L = 10; // 20 bit mask
        HashSet<Integer> seen = new HashSet<>();
        HashSet<String> res = new HashSet<>();
        
        for(int i = 0, len = s.length(); i < len; i++) {
            map <<= 2; // make room for new bit
            map &= mask; // get rid of two leading
            map |= getId(s.charAt(i)); // add new char to bitmap

            if(i >= L - 1) {
                if(!seen.add(map)) res.add(s.substring(i - L + 1, i + 1));
                
                seen.add(map);
            }
        }

        return new ArrayList<>(res);
    }

    private int getId(char c) {
        switch(c) {
            case 'A': return 0;
            case 'C': return 1;
            case 'G': return 2;
            case 'T': return 3;
        }
        return -1;
    }
}
// @lc code=end

