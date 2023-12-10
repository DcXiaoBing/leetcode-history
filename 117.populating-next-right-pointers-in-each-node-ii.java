import java.util.*;
/*
 * @lc app=leetcode id=117 lang=java
 *
 * [117] Populating Next Right Pointers in Each Node II
 */

// @lc code=start
/*
// Definition for a Node.
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
};
*/
// class Node {
//     public int val;
//     public Node left;
//     public Node right;
//     public Node next;

//     public Node() {
//     }

//     public Node(int _val) {
//         val = _val;
//     }

//     public Node(int _val, Node _left, Node _right, Node _next) {
//         val = _val;
//         left = _left;
//         right = _right;
//         next = _next;
//     }
// }

class Solution {
    // bfs
    // bfs needs extra space

    // if we use previously built next pointer, we can avoid extra space

    Node leftMost, pre;

    public Node connect(Node root) {
        if (root == null)
            return root;

        leftMost = root;
        Node cur = root;

        while (leftMost != null) {
            cur = leftMost;
            pre = null;
            leftMost = null;

            while (cur != null) {
                processChild(cur.left);
                processChild(cur.right);

                cur = cur.next;
            }
        }

        return root;
    }

    void processChild(Node child) {

        // base case
        if (child == null)
            return;

        if (pre == null) {
            // first one of next row
            leftMost = child;
        } else {
            // not the first one
            pre.next = child;
        }

        // update pre
        pre = child;
    }

}
// @lc code=end
