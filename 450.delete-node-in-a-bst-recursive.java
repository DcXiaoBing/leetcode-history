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
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    
    public TreeNode deleteNode(TreeNode root, int key) {
        // base case for delete. Only reachable when key is not in tree        
        if(root == null) return null;
        
        if(root.val > key) { // might in left sub-tree
            root.left = deleteNode(root.left, key);
        } else if(root.val < key) { // might in right subtree
            root.right = deleteNode(root.right, key);
        } else { // delete this node
            
            // if this is a leaf node
            if(root.left == null && root.right == null) root = null;
            else if(root.left == null) { // only one sub-tree. only successor is in its subtree
                root.val = successor(root);
                root.right = deleteNode(root.right, root.val); // delete successor
            } else { // left subtree. predecessor is in subtree
                root.val = predecessor(root);
                root.left = deleteNode(root.left, root.val);
            }
        }
        return root;
    }
    
    private int predecessor(TreeNode root) {
        TreeNode cur = root.left;
        while(cur.right != null) cur = cur.right;
        return cur.val;
    }
    
    private int successor(TreeNode root) {
        root = root.right;
        while(root.left != null) root = root.left;
        return root.val;
    }
}
// class Solution {
    
//     // predecessor and successor
//     // swap value and recur

//     // find the key, then delete
//     // returns the root of new tree
//     public TreeNode deleteNode(TreeNode root, int key) {
//         if(root == null) return null;

//         if(root.val < key) root.right = deleteNode(root.right, key);
//         else if(root.val > key) root.left = deleteNode(root.left, key);
//         else{
//             if(root.left == null && root.right == null)
//                 root = null;
//             else if(root.right != null){
//                 root.val = getSuccessor(root);
//                 // delete the successor
//                 root.right = deleteNode(root.right, root.val);
//             }else{
//                 root.val = getPredecessor(root);
//                 root.left = deleteNode(root.left, root.val);
//             }
//         }

//         return root;
//     }
    
//     int getPredecessor(TreeNode root){
//         // left-subtree's right most node
//         root = root.left;
//         while(root.right != null) root = root.right;
//         return root.val;
//     }
//     int getSuccessor(TreeNode root){
//         // right-subtree's left most node
//         root = root.right;
//         while(root.left != null) root = root.left;
//         return root.val;
//     }
// }
// @lc code=end

