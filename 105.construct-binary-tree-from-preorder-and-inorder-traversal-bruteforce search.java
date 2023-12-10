
/*
 * @lc app=leetcode id=105 lang=java
 *
 * [105] Construct Binary Tree from Preorder and Inorder Traversal
 */

// class TreeNode {
//     int val;
//     TreeNode left;
//     TreeNode right;

//     TreeNode(int x) {
//         val = x;
//     }
// }
// @lc code=start

class Solution {

    // binary tree, not BST
    // only nlogn?

    // now try a nlogn one
    // try to search root at inorder traverse each time
    // use length to get range for pre-order

    int[] preorder, inorder;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // corner input
        if (preorder == null || preorder.length == 0)
            return null;
        this.preorder = preorder;
        this.inorder = inorder;
        return helper(0, preorder.length - 1, 0, inorder.length - 1);
    }

    TreeNode helper(int preLeft, int preRight, int inLeft, int inRight) {
        // base case
        if (preLeft == preRight)
            return new TreeNode(preorder[preLeft]);

        TreeNode root = new TreeNode(preorder[preLeft]);

        // for (int i = inLeft; i <= inRight; i++) {
        // if (inorder[i] == preorder[preLeft]) {
        // rIndex = i;
        // leftLen = i - inLeft;
        // rightLen = inRight - i;
        // break;
        // }
        // }

        // rIndex = searchRoot(preorder[preLeft], inLeft, inRight);
        int rIndex = searchIndex(inorder, preorder[preLeft]);
        int leftLen = rIndex - inLeft;
        int rightLen = inRight - rIndex;
        if (leftLen == 0) {
            root.left = null;
        } else {
            root.left = helper(preLeft + 1, preLeft + leftLen, inLeft, rIndex - 1);
        }
        if (rightLen == 0) {
            root.right = null;
        } else {
            root.right = helper(preRight - rightLen + 1, preRight, rIndex + 1, inRight);
        }

        return root;
    }

    int searchRoot(int target, int left, int right) {
        for (int i = left; i <= right; i++) {
            if (inorder[i] == target)
                return i;
        }

        return -1;
    }

    int searchIndex(int[] nums, int target) {
        // 自己写的方法保证能找到
        for (int i = 0, length = nums.length; i < length; i++) {
            if (nums[i] == target)
                return i;
        }
        return -1;
    }
}
// @lc code=end
