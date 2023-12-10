// import java.math.BigInteger;
import java.util.*;

/*
 * @lc app=leetcode id=1044 lang=java
 *
 * [1044] Longest Duplicate Substring
 */

// @lc code=start
import java.math.BigInteger;
class Solution {
    // binary search + rolling hash
    // the reason we can use binary search is that, if k exist, then all number < k also exist
    public String longestDupSubstring(String SS) {
        char[] S = SS.toCharArray();
        int l = 1, r = S.length - 1, mid; // substring donot contain whole string itself
        
        int idx = -1;
        while(l < r) {
            mid = l + (r - l + 1) / 2; // make it belong to right part

            int temp = check(S, mid);
            if(temp < 0) {
                r = mid - 1;
            }else {
                idx = temp;
                l = mid;
            }
        }
        // return new String(S, idx - l, l);
        if(idx < 0) return "";
        return SS.substring(idx - l + 1, idx + 1);
    }
    private int check(char[] S, int mid) {
        // System.out.println("mid" + mid);

        HashMap<Long, Integer> seen = new HashMap<>();
        int p = 113, modulo = 1_000_000_007; // p has to be a number larger than alphabet's size
        int pInv = BigInteger.valueOf(p).modInverse(BigInteger.valueOf(modulo)).intValue(); // 模反元素， * pInv % MOD, 等价除以p

        // need to put highest power at tail to simplify the compute.
        long cur = 0, power = 1;
        for(int i = 0; i < mid; i++) { // init
            cur += S[i] * power;
            cur %= modulo;

            power = power * p % modulo;
        }
        seen.put(cur, mid - 1);
        power = power * pInv % modulo; // reduce a p

        // System.out.println(cur);
        for(int i = mid; i < S.length; i++) {

            cur -= S[i - mid]; // lowest power is 1
            cur = cur * pInv % modulo; // reduce a p

            cur += S[i] * power; // add in new char
            cur = cur % modulo;

            // System.out.println(cur + " " + i);
            if(seen.containsKey(cur)) {
                if(check(S, seen.get(cur) - mid + 1, i - mid + 1, mid)) return i;
            }
            seen.put(cur, i);
        }
        return -1;
    }

    private boolean check(char[] S, int p1, int p2, int mid) {
        for(int i = 0; i < mid; i++) if(S[p1 + i] != S[p2 + i]) return false;
        return true;
    }
}
// @lc code=end

