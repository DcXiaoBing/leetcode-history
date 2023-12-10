import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

/*
 * @lc app=leetcode id=101 lang=java
 *
 * [101] Symmetric Tree
 */

// @lc code=start
/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode
 * left; TreeNode right; TreeNode(int x) { val = x; } }
 */
class Solution {
    // public boolean isSymmetric(TreeNode root) {
    // // check null before using field values
    // if (root == null)
    // return true;
    // return helper(root.left, root.right);
    // }

    // boolean helper(TreeNode l, TreeNode r) {
    // if (l == null && r == null)
    // return true;
    // if (l == null || r == null)
    // return false;

    // return l.val == r.val && helper(l.left, r.right) && helper(l.right, r.left);
    // }

    // try a iterative one
    public boolean isSymmetric(TreeNode root) {

        // idea is based on bfs
        // control the order of adding nodes into queue

        if (root == null)
            return true;

        Deque<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode l = queue.poll();
            TreeNode r = queue.poll();

            if (l == null && r == null)
                continue;
            if (l == null || r == null)
                return false;

            if (l.val != r.val)
                return false;

            // cannot add null into ArrayDeque
            // but linkedlist can
            queue.add(l.left);
            queue.add(r.right);
            queue.add(l.right);
            queue.add(r.left);
        }

        return true;
    }
}
// @lc code=end
