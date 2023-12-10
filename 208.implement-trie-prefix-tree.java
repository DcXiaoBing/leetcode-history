/*
 * @lc app=leetcode id=208 lang=java
 *
 * [208] Implement Trie (Prefix Tree)
 */

// @lc code=start
class Trie {

    class Node {

        Node[] children;
        String word;

        Node() {
            children = new Node[26];
            word = null;
        }
    }

    /** Initialize your data structure here. */

    Node root;

    public Trie() {
        root = new Node();
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        Node cur = root;
        for (int i = 0, len = word.length(); i < len; i++) {
            int index = word.charAt(i) - 'a';
            if (cur.children[index] == null) {
                cur.children[index] = new Node();
            }
            cur = cur.children[index];
        }
        cur.word = word;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        Node cur = root;
        for (int i = 0, len = word.length(); i < len; i++) {
            int index = word.charAt(i) - 'a';
            if (cur.children[index] == null)
                return false;
            cur = cur.children[index];
        }
        return cur.word != null;
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        Node cur = root;
        for (int i = 0, len = prefix.length(); i < len; i++) {
            int index = prefix.charAt(i) - 'a';
            if (cur.children[index] == null)
                return false;
            cur = cur.children[index];
        }
        return true;
    }
}

/**
 * Your Trie object will be instantiated and called as such: Trie obj = new
 * Trie(); obj.insert(word); boolean param_2 = obj.search(word); boolean param_3
 * = obj.startsWith(prefix);
 */
// @lc code=end
