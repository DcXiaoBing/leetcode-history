import java.util.*;
/*
 * @lc app=leetcode id=102 lang=java
 *
 * [102] Binary Tree Level Order Traversal
 */

// @lc code=start
/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode
 * left; TreeNode right; TreeNode(int x) { val = x; } }
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

class Solution {
    // basic bfs

    // recursive is also okay
    // use arraylist to store lists
    // then store height information in the parameter
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();

        // deal with corner input
        if (root == null)
            return res;
        Deque<TreeNode> cur = new ArrayDeque<>(), next;

        cur.add(root);
        while (!cur.isEmpty()) {
            next = new ArrayDeque<>();
            List<Integer> curLevel = new LinkedList<>();
            while (!cur.isEmpty()) {
                TreeNode c = cur.poll();
                if (c.left != null)
                    next.add(c.left);
                if (c.right != null)
                    next.add(c.right);

                curLevel.add(c.val);
            }

            res.add(curLevel);
            cur = next;
        }

        return res;
    }
}
// @lc code=end
