/*
 * @lc app=leetcode id=686 lang=java
 *
 * [686] Repeated String Match
 */

// @lc code=start
import java.util.*;
import java.math.BigInteger;
class Solution {
    // let's try a rolling hash
    // need to graduatly

    // rolling hash
    // https://zh.wikipedia.org/wiki/模反元素
    // ax ≡ 1 (mod n) means a*x % n == 1
    // we can use this to reduce a p
    // https://oi-wiki.org/math/inverse/#__comments compute methods
    // BigInteger provides way to compute this
    
    // KMP is also fine
    public int repeatedStringMatch(String A, String B) {
        int modulo = (int)1e9 + 7, count = (B.length() - 1) / A.length() + 1;
        int p = 113, pInv = BigInteger.valueOf(p).modInverse(BigInteger.valueOf(modulo)).intValue(); // p need to larger than len of alphabet
        long hashA = 0, hashB = 0, power = 1;

        // compute hashB
        for(int i = 0; i < B.length(); i++){
            hashB += B.charAt(i) * power;
            hashB %= modulo;
            power = power * p % modulo ;
        }

        // compute hashA
        power = 1;
        for(int i = 0, cur = 0; i < B.length(); i++, cur++){
            if(cur == A.length()) cur = 0;

            hashA += A.charAt(cur) * power;
            hashA %= modulo;
            power = power * p % modulo;
        }

        // use Math.max to filter the condition that B is shorter than A and happens to has
        if(hashA == hashB && check(0, A, B)) return count;
        power = power * pInv % modulo; // reduce a p in power

        // need exact point to handle right char
        // "aaac" "aac" 
        // "aa" "a"
        for(int i = B.length(); i < (count + 1) * A.length(); i++){
            hashA -= A.charAt((i - B.length()) % A.length()); // need to handle case that B is shorter than A
            hashA = hashA * pInv % modulo; // reduce a p
            hashA += A.charAt(i % A.length()) * power; // add tail char
            hashA %= modulo;
            
            // + 1 is the new start
            if(hashA == hashB && check(i - B.length() + 1, A, B)) 
                return i < count * A.length() ? count : count + 1;
        }
        return -1;
    }

    private boolean check(int start, String A, String B) {
        for(int i = 0, cur = start; i < B.length(); i++, cur++){
            if(cur == A.length()) cur = 0;
            
            if(A.charAt(cur) != B.charAt(i)) return false;
        }

        return true;
    }
}
// @lc code=end

