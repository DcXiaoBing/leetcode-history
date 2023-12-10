/*
 * @lc app=leetcode id=872 lang=java
 *
 * [872] Leaf-Similar Trees
 */
import java.util.*;
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

// @lc code=start
/**
 * Definition for a binary tree node.
 * 
 */
class Solution {
    // generate and compare
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> l1 = new ArrayList<>(), l2 = new ArrayList<>();
        dfs(root1, l1);
        dfs(root2, l2);

        return l1.equals(l2);
    }

    private void dfs(TreeNode root, List<Integer> list) {
        if(root == null) return ;
        if(isLeaf(root)) {
            list.add(root.val);
            return ;
        }

        dfs(root.left, list);
        dfs(root.right, list);
    }

    private boolean isLeaf(TreeNode root){
        return root != null && root.left == null && root.right == null;
    }
}
// @lc code=end
