/*
 * @lc app=leetcode id=1022 lang=java
 *
 * [1022] Sum of Root To Leaf Binary Numbers
 */

// @lc code=start
/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode
 * left; TreeNode right; TreeNode() {} TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) { this.val = val; this.left
 * = left; this.right = right; } }
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Solution {
    // try a morris pre-order traversal
    public int sumRootToLeaf(TreeNode root) {
        int res = 0, cur = 0, steps;
        TreeNode pre;

        while (root != null) {
            if (root.left != null) {
                pre = root.left;
                steps = 1;

                // find predecessor of root in in-order traverse
                while (pre.right != null && pre.right != root){
                    pre = pre.right;
                    steps++;
                }

                if(pre.right == null) { // if not set link
                    pre.right = root;
                    cur = cur * 2 + root.val; // update path value
                    root = root.left; // start to visit left sub-tree
                } else { // left subtree has been visited
                    
                    // check whether pre is leaf(visit node)
                    if(pre.left == null) res += cur;

                    for(int i = 0; i < steps; i++) cur /= 2;
                    pre.right = null;
                    root = root.right;
                }
            } else {
                // left is null, menas it is a leaf node with right pointer modified
                // or it is a original node
                // So cannot decide whether to add value here

                cur = cur * 2 + root.val; // update path value

                // check whether root is leaf(visit node)
                // only last node execute this node (only leaf node could have empty right node)
                if(root.right == null) res += cur; 
                root = root.right;
            }
        }
        return res;
    }
}
// @lc code=end
