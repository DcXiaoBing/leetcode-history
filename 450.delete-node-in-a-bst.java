/*
 * @lc app=leetcode id=450 lang=java
 *
 * [450] Delete Node in a BST
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
// @lc code=start
class Solution {
    
    // predecessor and successor
    // swap value and recur

    // find the key, then delete
    // returns the root of new tree
    public TreeNode deleteNode(TreeNode root, int key) {
        
        // 1. find the target node
        TreeNode curr = root, prev = null;
        while(curr != null && curr.val != key) {
            prev = curr;
            if(curr.val < key) curr = curr.right;
            else curr = curr.left;
        }

        // 2. not find, do nothing
        if(curr == null) return root;
        
        // 3-a. root is leaf node, short circuit it
        if(curr.left == null && curr.right == null) {
            if(prev == null) return null; // target is root
            shortCircuit(prev, curr);
        } else if(curr.left == null) { // one right sub tree
            if(prev == null) return curr.right;
            shortCircuit(prev, curr, curr.right);
        } else if(curr.right == null) {
            if(prev == null) return curr.left;
            shortCircuit(prev, curr, curr.left);
        } else {
            // find successor node and swap value
            TreeNode succ = curr.right;
            prev = curr;
            if(succ.left == null) { // first node
                curr.right = succ.right;
                curr.val = succ.val;
            } else {
                while(succ.left != null) {
                    prev = succ;
                    succ = succ.left;
                }
                // succ is on left subtree of prev
                if(succ.right == null) prev.left = null; 
                else prev.left = succ.right;
                curr.val = succ.val; // swap value, then short circuit
            }
        }

        return root;
    }

    private void shortCircuit(TreeNode prev, TreeNode curr) {
        if(prev.val > curr.val) prev.left = null;
        else prev.right = null;
    }
    private void shortCircuit(TreeNode prev, TreeNode curr, TreeNode child) {
        if(prev.val > curr.val) prev.left = child;
        else prev.right = child;
    }
}
// @lc code=end

