import java.util.HashMap;

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
    HashMap<Integer, Integer> nodeToIndex;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // corner input
        if (preorder == null || preorder.length == 0)
            return null;
        this.preorder = preorder;
        this.inorder = inorder;
        nodeToIndex = new HashMap<>();
        for (int i = 0; i < preorder.length; i++)
            nodeToIndex.put(inorder[i], i);
        return helper(0, preorder.length - 1, 0, inorder.length - 1);
    }

    TreeNode helper(int preLeft, int preRight, int inLeft, int inRight) {
        // base case
        if (preLeft == preRight)
            return new TreeNode(preorder[preLeft]);

        TreeNode root = new TreeNode(preorder[preLeft]);

        // rIndex = searchRoot(preorder[preLeft], inLeft, inRight);
        int rIndex = nodeToIndex.get(preorder[preLeft]);
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
}
// @lc code=end
