import java.util.*;

/*
 * @lc app=leetcode id=1032 lang=java
 *
 * [1032] Stream of Characters
 */

// @lc code=start

// another way is using Trie, but built in reversed order

// best is AC automation: a combination of Trie and KMP
// find prefix in current word's suffix
// https://www.jianshu.com/p/be365cfd6655

class StreamChecker {

    // try a AC-automation sulotion
    // suitable for multi-target string match

    Trie root = new Trie(), cur;
    public StreamChecker(String[] words) {
        buildTrie(words);
        buildACAutomation();
        cur = root;
    }

    public boolean query(char letter) {
        int idx = letter - 'a';
        boolean res = false;

        while(cur != null && cur.children[idx] == null) cur = cur.failTo; // skip all mismatch
        if(cur == null) {
            cur = root;
        }else{
            cur = cur.children[idx];

            if(cur.isWord) res = true; // all is word
            if(cur.failTo.isWord) res = true; // lsp(longest proper prefix) is word

             /*这里很容易被忽视，因为一个目标串的中间某部分字符串可能正好包含另一个目标字符串，
                 * 即使当前结点不表示一个目标字符串的终点，但到当前结点为止可能恰好包含了一个字符串*/
        }
        return res;
    }

    private void buildTrie(String[] words) {
        for(String word : words) {
            cur = root;
            for(int i = 0, len = word.length(); i < len; i++) {
                int idx = word.charAt(i) - 'a';
                if(cur.children[idx] == null) cur.children[idx] = new Trie();
                cur = cur.children[idx];
            }
            cur.isWord = true;
        }
    }

    private void buildACAutomation() {
        Deque<Trie> queue = new ArrayDeque<>(); // like a bfs. Build fail layer by layer

        // 1. all child of root points to root (len 1 suffix)
        for(int i = 0; i < 26; i++) {
            if(root.children[i] == null) continue;
            root.children[i].failTo = root;
            queue.add(root.children[i]);
        }

        // 2. hanld all rest nodes
        while(!queue.isEmpty()){
            cur = queue.poll();

            for(int i = 0; i < 26; i++) {
                if(cur.children[i] == null) continue;
                queue.add(cur.children[i]); // bfs

                Trie failTo = cur.failTo;

                while(cur.children[i].failTo == null) { // loop when not set
                    if(failTo == null) { // failTo has reach root, and root has no child i
                        cur.children[i].failTo = root;
                    }else if(failTo.children[i] != null) {
                        cur.children[i].failTo = failTo.children[i]; // extend matched prefix and suffix
                    }else {
                        failTo = failTo.failTo;
                    }
                }
            }
        }
    }
}

class Trie {
    boolean isWord;
    Trie[] children;
    Trie failTo;

    Trie() {
        isWord = false;
        children = new Trie[26];
        failTo = null;
    }
}

/**
 * Your StreamChecker object will be instantiated and called as such:
 * StreamChecker obj = new StreamChecker(words);
 * boolean param_1 = obj.query(letter);
 */

/**
 * Your StreamChecker object will be instantiated and called as such:
 * StreamChecker obj = new StreamChecker(words);
 * boolean param_1 = obj.query(letter);
 */
// @lc code=end

