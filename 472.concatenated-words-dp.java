/*
 * @lc app=leetcode id=472 lang=java
 *
 * [472] Concatenated Words
 */

// @lc code=start
class Solution {
    
    // tradition dp
    // dp[i] for a perticular word means that it can form for 0~i i is inclusive
    // !!! DO NOT COMPARE ONE BY ONE, USE HASHSET
    
    // Trie
    Boolean[] dp;
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        // sort words according to length
        Arrays.sort(words, new Comparator<String>(){
            public int compare(String a, String b){
                return a.length() - b.length();
            }
        });
        
        List<String> res = new LinkedList<>();
        HashSet<String> dict = new HashSet<>();
        dict.add(words[0]);
        for(int i = 1; i < words.length; i++){
            // different dp array for each array
            dp = new Boolean[words[i].length() + 1];
            if(dfs(dict, words[i], 0, 0)) res.add(words[i]);
            dict.add(words[i]);
        }
        
        return res;
    }
    
    
    boolean dfs(HashSet<String> dict,String cur, int index, int count){
        // base case: match, not match(count too small)
        if(index == cur.length()){
            if(count < 2) return false;
            else return true;
        }
        
        // introduce dp for this word
        if(dp[index] != null) return dp[index];
        
        // i is the end index, exclusive
        // use hashset to finish compare quickly
        for(int i = index + 1; i <= cur.length(); i++){
            if(dict.contains(cur.substring(index, i)) && dfs(dict, cur, i, count + 1)){
                dp[index] = true;
                return true;
            }
        }
        
        // no solution
        dp[index] = false;
        return false;
    }
}
// @lc code=end

