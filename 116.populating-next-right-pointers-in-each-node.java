/*
 * @lc app=leetcode id=116 lang=java
 *
 * [116] Populating Next Right Pointers in Each Node
 */
import java.util.*;
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}
    
    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
}
// @lc code=start
class Solution {

    Node leftMost = null, pre = null;
    public Node connect(Node root) {
        // bfs
        leftMost = root;
        Node cur = leftMost;
        while(leftMost != null){
            pre = null;
            cur = leftMost;
            leftMost = null;
            while(cur != null){
                process(cur.left);
                process(cur.right);
                cur = cur.next;
            }
        }
        return root;
    }
    
    void process(Node child){
        if(child == null)
            return;

        if(pre != null)
            pre.next = child;
        else
            // pre is null means this is a new level
            leftMost = child;
        pre = child;
    }
}
// @lc code=end

