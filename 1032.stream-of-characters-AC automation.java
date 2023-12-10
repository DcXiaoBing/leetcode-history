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

    Node root, cur;
    public StreamChecker(String[] words) {
        root = new Node();
        cur = root;
        bulidTrie(words);
        buildFail();
    }
    
    public boolean query(char letter) {
        int idx = letter - 'a';
        boolean res = false;

        // move to correct node
        while(cur != null && cur.children[idx] == null) cur = cur.fail;
        if(cur != null){
            // try to find word
            cur = cur.children[idx];
            if(cur.isWord) res = true;

            // because we have moved down one time, so cur.fail cannot be null
            // fail means the node we will go when next letter mismatch.
            // And if it is a word, means matched suffix is a valid prefix
            if(cur.fail.isWord) res = true;
        }else{
            // search fail, discard cur letter and start from root
            cur = root;
        }
        return res;
    }

    // 1. build trie
    void bulidTrie(String[] words){
        Node cur;
        for(String w : words){
            cur = root;
            for(int i = 0, len = w.length(); i < len; i++){
                int idx = w.charAt(i) - 'a';
                if(cur.children[idx] == null) cur.children[idx] = new Node();
                cur = cur.children[idx];
            }
            cur.isWord = true;
        }
    }

    // 2. build fail pointer
    void buildFail(){
        Deque<Node> queue = new ArrayDeque<>();
        
        // initialize
        // all root's child points to root
        for(Node n : root.children){
            if(n == null) continue;
            n.fail = root;
            queue.add(n);
        }

        // start bfs
        // queue store parent node, build fail link for its child node
        while(!queue.isEmpty()){
            Node cur = queue.poll();
            
            for(int i = 0; i < 26; i++){
                if(cur.children[i] == null) continue;
                
                queue.add(cur.children[i]);
                Node failTo = cur.fail; // failTo is prefix part(Think of KMP fail array)

                // loop until find a node for fail pointer
                while(cur.children[i].fail == null){

                    // CORE IDEA: the node in cur.fail(represents prefix) matched the suffix of cur
                    // so if cur.fail.children[i] is not null, just point cur.children[i].fail to it

                    if(failTo == null){
                        // parent fail has reach root
                        // go to root to accept new char
                        cur.children[i].fail = root; 
                    }else if(failTo.children[i] != null){
                        cur.children[i].fail = failTo.children[i];
                    }
                    else{
                        failTo = failTo.fail; // move back again to find a match
                    }
                }
            }
        }
    }

}
class Node{
    boolean isWord;
    Node[] children;
    Node fail;

    Node(){
        isWord = false;
        children = new Node[26];
        fail = null;
    }
}

/**
 * Your StreamChecker object will be instantiated and called as such:
 * StreamChecker obj = new StreamChecker(words);
 * boolean param_1 = obj.query(letter);
 */
// @lc code=end

