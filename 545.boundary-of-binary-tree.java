/*
 * @lc app=leetcode id=545 lang=java
 *
 * 545. Boundary of Binary Tree
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
    // when root has no left subtree, root is left boundary
    // when root has no right subtree, root is right boundary

    // go left
    // add all leaves
    // go right
    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        // special case!
        if(root == null) return res;
        res.add(root.val);
        if(root.left == null){
            dfs(root.right, res, false, true);
        }
        else if(root.right == null){
            dfs(root.left, res, true, false);
        }else{
            dfs(root.left, res, true, false);
            dfs(root.right, res, false, true);
        }
        return res;
    }

    void dfs(TreeNode root, List<Integer> res, boolean l, boolean r){
        
        if(root == null) return ;

        // leaf node
        if(root.left == null && root.right == null){
            res.add(root.val);
            return ;
        }

        // on left boundary
        if(l) res.add(root.val);

        if(root.left != null) dfs(root.left, res, l, r && root.right == null);
        if(root.right != null) dfs(root.right, res, l && root.left == null, r);

        // on right boundary
        if(!l && r) res.add(root.val);
    }
}
// @lc code=end

