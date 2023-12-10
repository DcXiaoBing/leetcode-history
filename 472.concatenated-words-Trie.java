import java.util.*;

/*
 * @lc app=leetcode id=472 lang=java
 *
 * [472] Concatenated Words
 */

// @lc code=start
class Solution {

    // Perfect for Trie
    
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        
        // get trie
        Trie root = bulidTrie(words);

        // search
        List<String> res = new LinkedList<>();
        for(String word : words){
            if(search(root, word, 0, 0)) res.add(word);
        }
        
        return res;
    }

    boolean search(Trie root, String word, int curIndex, int count){
        // base case
        if(curIndex == word.length()){
            if(count < 2) return false;
            else return true;
        } 

        Trie cur = root;
        for(int i = curIndex,len = word.length(); i < len; i++){
            int idx = word.charAt(i) - 'a';
            if(cur.children[idx] == null) return false; // no path -> no word
            cur = cur.children[idx];
            if(cur.word != null && search(root, word, i + 1, count + 1)) return true;
        }

        // failed to search
        return false;
    }

    Trie bulidTrie(String[] words){
        Trie root = new Trie();
        for(String w : words){
            if(w.isEmpty()) continue; // skip empty word which cause infinate recurse
            Trie.insert(root, w);
        }

        return root;
    }
}
class Trie{
    String word;
    Trie[] children;

    Trie(){
        word = null;
        children = new Trie[26];
    }

    public static void insert(Trie root, String word){
        Trie cur = root;
        for(int i = 0, len = word.length(); i < len; i++){
            int idx = word.charAt(i) - 'a';
            if(cur.children[idx] == null) cur.children[idx] = new Trie();
            cur = cur.children[idx];
        }
        cur.word = word;
    }
}
// @lc code=end

