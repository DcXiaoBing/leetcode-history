/*
 * @lc app=leetcode id=880 lang=java
 *
 * [880] Decoded String at Index
 */

// @lc code=start
class Solution {
    
    // for the decoded string, we can use idx to infer which part is belongs to
    // So, first thing first, compute length
    
    public String decodeAtIndex(String S, int K) {
        
        long total = 0, cnt = 0;
        Deque<Pair> stack = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();
        
        for(int i = 0, len = S.length(); i < len; i++) {
            char c = S.charAt(i);
            
            if(c >= 'a' && c <= 'z') {
                
                sb.append(c);
            } else {
                cnt = c - '0';
                
                stack.push(new Pair(total, cnt, sb.toString()));
                total = (total + sb.length()) * cnt; // total length for decoded string

                cnt = 0;
                sb = new StringBuilder();
            }
        }
        stack.push(new Pair(total, cnt, sb.toString())); // handle tail
        K--; // make it become 0-indexed
        
        while(!stack.isEmpty()) {
            Pair cur = stack.pop();
            K %= (cur.prevLen + cur.rest.length());
            
            // System.out.println(K + " " + cur.prevLen + " " + cur.rest);
            // word at rest part.
            if(K >= cur.prevLen) return cur.rest.charAt(K - (int)cur.prevLen) + ""; 
        }
        
        return ""; // not found
    }
}

class Pair {
    long prevLen, cnt; // length for single string of current part
    String rest; // string that do not belong to previous decode
    
    Pair(long prevLen, long cnt, String rest) {
        this.prevLen = prevLen;
        this.cnt = cnt;
        this.rest = rest;
    }
}
// @lc code=end

