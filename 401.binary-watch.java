import java.util.*;

/*
 * @lc app=leetcode id=401 lang=java
 *
 * [401] Binary Watch
 */

// @lc code=start
class Solution {
    public List<String> readBinaryWatch(int num) {
        List<Integer> buckets[] = new List[7];
        for(int i = 0; i < 7; i++) buckets[i] = new ArrayList<>();

        for(int i = 0; i < 60; i++) buckets[Integer.bitCount(i)].add(i);
        // for(int i = 0; i < 7; i++) System.out.println(buckets[i]);

        List<String> res = new ArrayList<>();
        for(int i = 0; i <= 4 && i <= num; i++) {
            for(Integer h : buckets[i]) {
                if(h > 11 || num - i > 6) continue;
                for(Integer m : buckets[num - i]) {
                    String cur = h + ":";
                    if(m < 10) cur += "0" + m;
                    else cur += m;
                    res.add(cur);
                }
            }
        }

        return res;
    }
}
// @lc code=end

