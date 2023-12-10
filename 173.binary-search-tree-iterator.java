import java.util.*;

/*
 * @lc app=leetcode id=173 lang=java
 *
 * [173] Binary Search Tree Iterator
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
// @lc code=start
class BSTIterator {
    // in-order traverse. use a iterative one

    Deque<TreeNode> stack;
    TreeNode root;
    TreeNode cur;
    public BSTIterator(TreeNode root) {
        this.root = root;
        stack = new LinkedList<>();
        cur = root;
        while(cur != null){
            stack.push(cur);
            cur = cur.left;
        }
    }
    
    /** @return the next smallest number */
    public int next() {
        cur = stack.pop();
        int res = cur.val;

        cur = cur.right;
        while(cur != null){
            stack.push(cur);
            cur = cur.left;
        }

        return res;
    }
    
    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        if(stack.isEmpty())
            return false;
        return true;
    }
}

/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator obj = new BSTIterator(root);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */
// @lc code=end

