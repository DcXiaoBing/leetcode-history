/*
 * @lc app=leetcode id=230 lang=java
 *
 * [230] Kth Smallest Element in a BST
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

// @lc code=start
class Solution {

    // dfs and count
    // numbers are in ascending order if traverse by in-order

    // !!! initialized to 0
    // because we ++ before check
    // so need 0 to make 1
    int counter = 0;

    public int kthSmallest(TreeNode root, int k) {
        // k is always valid

        return dfsAndCount(root, k).val;
    }

    TreeNode dfsAndCount(TreeNode root, int k) {

        // didn't find, return null
        if (root == null)
            return null;

        TreeNode res = dfsAndCount(root.left, k);
        if (res != null)
            return res;

        counter++; // visiting cur node
        if (counter == k)
            return root;

        res = dfsAndCount(root.right, k);

        // !!! Because we might in a subtree right now
        // so it is important to deal with the situation that not presence
        if (res != null)
            return res;
        else
            return null;

    }
}
// @lc code=end
