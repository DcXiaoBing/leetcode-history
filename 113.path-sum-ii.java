import java.util.*;

/*
 * @lc app=leetcode id=113 lang=java
 *
 * [113] Path Sum II
 */

// @lc code=start

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

class Solution {
    // backtracking
    List<List<Integer>> res;

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        res = new LinkedList<>();
        helper(root, sum, 0, new LinkedList<>());
        return res;
    }

    void helper(TreeNode root, int target, int curSum, LinkedList<Integer> curL) {

        // base case
        // we do not actions here
        if (root == null)
            return;

        // this leads to a duplicate list
        // if (root == null) {
        // if (curSum == target)
        // res.add(new LinkedList<>(curL));

        // return;
        // }

        // try to add
        curL.add(root.val);
        if (root.left == null && root.right == null) {
            if (curSum + root.val == target)
                res.add(new LinkedList<>(curL));

            curL.removeLast();
            return;
        }
        helper(root.left, target, curSum + root.val, curL);
        helper(root.right, target, curSum + root.val, curL);
        curL.removeLast();
    }
}
// @lc code=end
