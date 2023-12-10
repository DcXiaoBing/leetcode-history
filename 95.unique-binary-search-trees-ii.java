import java.util.*;

/*
 * @lc app=leetcode id=95 lang=java
 *
 * [95] Unique Binary Search Trees II
 */

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

// @lc code=start
/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode
 * left; TreeNode right; TreeNode() {} TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) { this.val = val; this.left
 * = left; this.right = right; } }
 */
class Solution {
    // keep mind of shallow copy
    public List<TreeNode> generateTrees(int n) {
        if(n == 0) return new ArrayList<>();
        return helper(1, n);
    }

    private List<TreeNode> helper(int min, int max) {
        List<TreeNode> res = new ArrayList<>();
        if (min > max) {
            res.add(null);
            return res;
        }

        for (int i = min; i <= max; i++) {
            List<TreeNode> lSubs = helper(min, i - 1);
            List<TreeNode> rSubs = helper(i + 1, max);

            for(TreeNode lRoot : lSubs) for(TreeNode rRoot : rSubs) {
                TreeNode root = new TreeNode(i);
                root.left = lRoot;
                root.right = rRoot;

                res.add(root);
            }
        }

        return res;
    }
}
// @lc code=end
