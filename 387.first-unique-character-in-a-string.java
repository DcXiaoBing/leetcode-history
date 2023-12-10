/*
 * @lc app=leetcode id=387 lang=java
 *
 * [387] First Unique Character in a String
 */

// @lc code=start
class Solution {

    // if two pass, it will be simple
    // use an array to record index
    
    public int firstUniqChar(String s) {
        int[] fre = new int[26];
        int[] idx = new int[26];
        // Arrays.fill(idx, -1);

        for(int i = 0, len = s.length(); i < len; i++){
            int cur = s.charAt(i) - 'a';
            fre[cur]++;
            if(idx[cur] == 0)
                idx[cur] = i + 1; // to make difference to 0
        }

        int res = 0;
        for(int i = 0; i < 26; i++){
            if(fre[i] == 1){
                if(res != 0)
                    res = Math.min(res, idx[i]);
                else
                    res = idx[i];
            }
        }

        return res - 1;
    }
}
// @lc code=end

