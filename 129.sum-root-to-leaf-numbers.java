/*
 * @lc app=leetcode id=129 lang=java
 *
 * [129] Sum Root to Leaf Numbers
 */

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

    // Morris traverse
    // find predecessor takes O(n), find two times
    // locate each node

    public int sumNumbers(TreeNode root) {
        int sum = 0, step = 0, res = 0;
        TreeNode cur = root, prev= null;

        while(cur != null) {
            // System.out.println(cur.val);
            if(cur.left == null) {
                sum = sum * 10 + cur.val; // visit this node
                if(cur.right == null) res += sum; // last node
                // if(cur.right == null) System.out.println(sum);
                cur = cur.right;
            }else {
                step = 1;
                prev = cur.left;
                while(prev.right != null && prev.right != cur) {
                    prev = prev.right;
                    step++;
                }
                    
                if(prev.right == null) { // visit this node
                    sum = sum * 10 + cur.val;
                    prev.right = cur;
                    cur = cur.left; // change to visit left subtree
                }else { // visit right subtree of cur

                    // we have returned from a visted node
                    // we need to check whether predecessor is a leaf
                    // because we cannot check leaf when visit

                    if(prev.left == null) res += sum; // this is leaf
                    // if(prev.left == null) System.out.println(sum);
                    for(int i = 0; i < step; i++) sum /= 10;

                    prev.right = null;
                    cur = cur.right;
                }
            }
        }
        return res;
    }
}
// @lc code=end

