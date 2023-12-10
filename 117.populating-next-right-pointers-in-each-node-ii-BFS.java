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
    public Node connect(Node root) {
        if (root == null)
            return root;

        Deque<Node> queue = new LinkedList<>(), next;
        queue.add(root);

        while (!queue.isEmpty()) {
            next = new LinkedList<>();
            while (!queue.isEmpty()) {

                Node cur = queue.poll();

                if (!queue.isEmpty()) {
                    cur.next = queue.getFirst();
                }
                if (cur.left != null)
                    next.add(cur.left);

                if (cur.right != null)
                    next.add(cur.right);
            }

            queue = next;
        }

        return root;
    }
}
// @lc code=end
