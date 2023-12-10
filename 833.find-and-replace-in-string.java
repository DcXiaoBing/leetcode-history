import java.util.*;

/*
 * @lc app=leetcode id=833 lang=java
 *
 * [833] Find And Replace in String
 */

// @lc code=start
class Solution { 
    // start from end so that replace will not interfare with each other

    // !!! when using while(index[p] < i), always remember to check index
    // ! repeat key? use index[i] as key!
    public String findReplaceString(String S, int[] indexes, String[] sources, String[] targets) {
        HashMap<Integer, String> idxToSrc = new HashMap<>(), idxToTg = new HashMap<>();
        for(int i = 0; i < sources.length; i++) {
            idxToSrc.put(indexes[i], sources[i]);
            idxToTg.put(indexes[i], targets[i]);
        }
        Arrays.sort(indexes);

        StringBuilder sb = new StringBuilder();
        char[] s = S.toCharArray();

        for(int i = 0, len = s.length, p = 0; i < len; i++){
            while(p < indexes.length && indexes[p] < i) p++;

            if(p == indexes.length || i < indexes[p]) sb.append(s[i]);
            else{
                String src = idxToSrc.get(indexes[p]);
                String tgt = idxToTg.get(indexes[p]);
                if(check(s, i, src)) {
                    sb.append(tgt);
                    i += src.length() - 1; // -1 to offset ++
                    // p++;
                }else{
                    sb.append(s[i]);
                }
            }
        }
        return sb.toString();
    }
    private boolean check(char[] s, int idx, String src) {
        if(idx < 0 || idx >= s.length) return false;
        for(int i = idx, p = 0, len = src.length(); i < s.length && p < len; i++, p++) {
            if(s[i] != src.charAt(p)) return false;
        }
        return true;
    }
}
// @lc code=end

