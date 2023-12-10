import java.util.*;
/*
 * @lc app=leetcode id=103 lang=java
 *
 * [103] Binary Tree Zigzag Level Order Traversal
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
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();
        if (root == null)
            return res;

        // need to record the level information
        // still store node from left to right
        // but add
        int level = 0;
        Deque<TreeNode> curQ = new LinkedList<>(), nextQ; // linkedlist can store null
        curQ.add(root);

        while (!curQ.isEmpty()) {

            nextQ = new LinkedList<>();
            List<Integer> temp = new LinkedList<>();

            while (!curQ.isEmpty()) {
                TreeNode cur;

                if ((level & 1) == 0) {
                    cur = curQ.removeFirst();
                    if (cur == null)
                        continue;
                    temp.add(cur.val);
                    // because we are visiting leftmost element
                    nextQ.addLast(cur.left);
                    nextQ.addLast(cur.right);
                } else {
                    cur = curQ.removeLast();
                    if (cur == null)
                        continue;
                    temp.add(cur.val);
                    // because we are visiting rightmost element
                    nextQ.addFirst(cur.right);
                    nextQ.addFirst(cur.left);
                }
            }

            curQ = nextQ;
            level = 1 - level;
            if (temp.size() > 0)
                res.add(temp);
        }

        return res;
    }
}
// @lc code=end
