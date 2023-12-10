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

    Node leftMost = null, cur = null;
    public Node connect(Node root) {
        // bfs
        Deque<Node> queue = new LinkedList<>(), next;
        queue.add(root);
        while(!queue.isEmpty()){
            next = new LinkedList<>();
            while(!queue.isEmpty()){
                Node cur = queue.poll();
                if(cur == null)
                    continue;
                process(cur);

                next.add(cur.left);
                next.add(cur.right);
            }

            leftMost = null;
            cur = null;
            queue = next;
        }
        
        return root;
    }
    void process(Node child){
        if(leftMost == null){
            leftMost = child;
            cur = leftMost;
            return;
        }

        cur.next = child;
        cur = child;
    }
}
// @lc code=end

