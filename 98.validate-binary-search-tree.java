/*
 * @lc app=leetcode id=98 lang=java
 *
 * [98] Validate Binary Search Tree
 */

// @lc code=start
// class TreeNode {
//     int val;
//     TreeNode left;
//     TreeNode right;

//     TreeNode(int x) {
//         val = x;
//     }
// }

class Solution {
    // recursive
    // use range to check
    public boolean isValidBST(TreeNode root) {
        return helper(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    boolean helper(TreeNode root, long lowerBound, long upperBound) {
        if (root == null)
            return true;
        if (root.val > upperBound || root.val < lowerBound)
            return false;

        return helper(root.left, lowerBound, (long) root.val - 1)
                && helper(root.right, (long) root.val + 1, upperBound);
    }
}
// @lc code=end
