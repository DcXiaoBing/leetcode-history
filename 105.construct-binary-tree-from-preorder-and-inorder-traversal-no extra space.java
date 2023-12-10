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
  // a solution that uses stop value is so cool
  // 1. maintain a global pointer for pre. As pointer for preoder only increases, so one pointer is enough.
  // 2. For inorder array, we need two pointer. The left side also increases. (e.g. all left node is visited before root is visited.)
  // The right side is the split point, we can use a value to do this.

  private int in = 0, pre = 0;
  public TreeNode buildTree(int[] preorder, int[] inorder) {
    return helper(preorder, inorder, Integer.MAX_VALUE);
  }

  private TreeNode helper(int[] preorder, int[] inorder, int stop) {
    if(in >= inorder.length) return null;
    if(inorder[in] == stop) {in++; return null;}

    TreeNode root = new TreeNode(preorder[pre++]);
    root.left = helper(preorder, inorder, root.val);
    root.right = helper(preorder, inorder, stop); // use pre stop value
    return root;
  }
}
