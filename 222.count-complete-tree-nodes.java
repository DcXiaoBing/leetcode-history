/*
 * @lc app=leetcode id=222 lang=java
 *
 * [222] Count Complete Tree Nodes
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
    /**
     * 1. of course we can use the information that tree is complete
     * => we only need to count the nodes in last level
     * 
     * 2. to efficiently compute the count, we can use binary search.
     * suppose each node has a index, from 0 to 2^d - 1.
     * the node in root's left subtree, it should be 0 to 2^(d-1) - 1
     * the node in root's right subtree, it should be 2^(d-1) ~ 2^d - 1
     */
    public int countNodes(TreeNode root) {
        if(root == null) return 0;

        int d = 0; // d is real depth
        TreeNode temp = root;
        while(temp.left != null) {temp = temp.left; d++;}

        if(d == 0) return 1; // single root node

        // search for count of node in last level
        int l = 0, r = (int)Math.pow(2, d) - 1, mid, max = (int)Math.pow(2, d) - 1;
        while(l < r) {
            mid = l + (r - l + 1) / 2;
            // System.out.println(mid + " " + check(mid, r, d, root));
            if(check(mid, max, d, root)) {
                l = mid;
            }else {
                r = mid - 1;
            }
        }
        return  max + l + 1; // idx is 0-based
    }

    private boolean check(int idx, int max, int d, TreeNode root){
        int l = 0, r = max, mid;

        // this loop will go one level more
        // because range is wrong. max ~ 2max + 1 is correct for directly search index start from root.
        while(l < r) {
            mid = l + (r - l) / 2;
            // System.out.println(mid + " " + l + " " + r + " " + root.val);

            if(idx <= mid) {
                root = root.left;
                r = mid;
            }else {
                root = root.right;
                l = mid + 1;
            }
        }

        // for(int i = 0; i < d; i++) {
        //     mid = l + (r - l) / 2;
        //     if(idx <= mid) {
        //         root = root.left;
        //         r = mid;
        //     }else {
        //         root = root.right;
        //         l = mid + 1;
        //     }
        // }
        // System.out.println(root == null);
        return root != null;
    }
}
// @lc code=end

