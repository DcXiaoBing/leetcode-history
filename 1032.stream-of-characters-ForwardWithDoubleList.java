/*
 * @lc app=leetcode id=1032 lang=java
 *
 * [1032] Stream of Characters
 */

// @lc code=start
class StreamChecker {
    // original
    
    // use a list to store Trie Node
    // when a new letter comes in, try to append it to all node in list
    
    Trie root;
    DoubleLinkedList nodeList;
    public StreamChecker(String[] words) {
        root = new Trie();
        nodeList = new DoubleLinkedList();
        buildTrie(words);
    }
    
    public boolean query(char letter) {
        
        // check current list
        // remove node only when went into a deadend.
        // Do not remove when met a word, because of prefix
        boolean res = false;
        int idx = letter - 'a';
        ListNode cur = nodeList.head;
        while(cur != null){
            if(cur.val.children[idx] != null){
                cur.val = cur.val.children[idx];
                if(cur.val.isWord) res = true;
            }else{
                nodeList.remove(cur);
                
                // System.out.println("here");
            }
            cur = cur.next;
        }
        // System.out.println(nodeList.size);
        if(root.children[idx] != null){
            nodeList.addLast(new ListNode(root.children[idx]));
            if(root.children[idx].isWord) res = true;
        }
        return res;
    }
    
    
    void buildTrie(String[] words){
        for(String word : words){
            insertTrie(word);
        }
    }
    void insertTrie(String word){
        Trie cur = root;
        for(int i = 0, len = word.length(); i < len; i++){
            int idx = word.charAt(i) - 'a';
            if(cur.children[idx] == null) cur.children[idx] = new Trie();
            cur = cur.children[idx];
        }
        cur.isWord = true;
    }
}
class Trie{
    boolean isWord;
    Trie children[];
    Trie(){
        isWord = false;
        children = new Trie[26];
    }
}

class DoubleLinkedList{
    ListNode head, tail;
    int size;
    
    DoubleLinkedList(){
        head = tail = null;
        size = 0;
    }
    
    void remove(ListNode n){
        // I garentee n is in list
        if(size == 1){
            head = tail = null;
        }else if(n == head){
            head = head.next;
            head.prev = null;
        }else if(n == tail){
            tail = tail.prev;
            tail.next = null;
        }else{
            n.prev.next = n.next;
            n.next.prev = n.prev;
        }
        // n.prev = null;
        // n.next = null;
        size--;
    }
    void addLast(ListNode n){
        if(size == 0){
            head = tail = n;
        }else{
            tail.next = n;
            n.prev = tail;
            
            tail = n;
        }
        size++;
    }
}
class ListNode{
    Trie val;
    ListNode prev, next;
    
    ListNode(){
        val = null;
        prev = null;
        next = null;
    }
    ListNode(Trie _val){
        val = _val;
        prev = null;
        next = null;
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

