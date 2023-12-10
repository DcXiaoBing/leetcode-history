/*
 * @lc app=leetcode id=617 lang=java
 *
 * [617] Merge Two Binary Trees
 */

// @lc code=start
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    // recursive, combine them to t1
    // if a child is missing, directly use the one in t2
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if(t1 == null) return t2;
        else if(t2 == null) return t1;

        t1.val += t2.val;
        if(t1.left == null) t1.left = t2.left;
        else mergeTrees(t1.left, t2.left);

        if(t1.right == null) t1.right = t2.right;
        else mergeTrees(t1.right, t2.right);

        return t1;
    }
}
// @lc code=end

