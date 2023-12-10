/*
 * @lc app=leetcode id=110 lang=java
 *
 * [110] Balanced Binary Tree
 */

// @lc code=start
/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode
 * left; TreeNode right; TreeNode(int x) { val = x; } }
 */
class Solution {
    public boolean isBalanced(TreeNode root) {
        if (root == null)
            return true;

        if (Math.abs(getHeight(root.left) - getHeight(root.right)) > 1)
            return false;

        return isBalanced(root.left) && isBalanced(root.right);
    }

    int getHeight(TreeNode root) {
        if (root == null)
            return 0;
        int lHeight = 0, rHeight = 0;
        if (root.left != null)
            lHeight = getHeight(root.left);
        if (root.right != null)
            rHeight = getHeight(root.right);

        return Math.max(lHeight, rHeight) + 1;
    }
}
// @lc code=end
