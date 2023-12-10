/*
 * @lc app=leetcode id=1305 lang=java
 *
 * [1305] All Elements in Two Binary Search Trees
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
    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        // use stack to simulate in-order dfs
        Deque<TreeNode> stack1 = new ArrayDeque<>();
        Deque<TreeNode> stack2 = new ArrayDeque<>();
        TreeNode cur1 = getNext(stack1, root1), cur2 = getNext(stack2, root2);
        
        List<Integer> res = new ArrayList<>();
        
        while((!stack1.isEmpty() || cur1 != null) && (!stack2.isEmpty() || cur2 != null)) {
            
            if(cur1.val < cur2.val) {
                res.add(cur1.val);
                // visit this node. then we need to handle right sub-tree
                cur1 = getNext(stack1, cur1.right);
            } else {
                res.add(cur2.val);
                cur2 = getNext(stack2, cur2.right);
            }
        }
        
        while(!stack1.isEmpty() || cur1 != null) {
            res.add(cur1.val);
            cur1 = getNext(stack1, cur1.right);
        }
        
        while(!stack2.isEmpty() || cur2 != null) {
            res.add(cur2.val);
            cur2 = getNext(stack2, cur2.right);
        }
        
        return res;
    }
    
    private TreeNode getNext(Deque<TreeNode> stack, TreeNode cur) {
        if(stack.isEmpty() && cur == null) return null; // no following
        while(cur != null) {
            stack.push(cur);
            cur = cur.left;
        }
        cur = stack.pop();
        return cur;
    }
}
// @lc code=end

