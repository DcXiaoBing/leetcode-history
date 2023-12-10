/*
 * @lc app=leetcode id=199 lang=java
 *
 * [199] Binary Tree Right Side View
 */
import java.util.*;
class TreeNode {
         int val;
         TreeNode left;
         TreeNode right;
         TreeNode(int x) { val = x; }
}
// @lc code=start
class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        // bfs
        Deque<TreeNode> queue = new LinkedList<>(), next;
        List<Integer> res = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            
            next = new LinkedList<>();
            
            while(!queue.isEmpty()){
                TreeNode cur = queue.poll();
                if(cur == null) continue;
                     
                // to make find last element easier, we need to check null here
                if(cur.left != null) next.add(cur.left);    
                if(cur.right != null) next.add(cur.right);

                // last element of this level
                if(queue.isEmpty()) res.add(cur.val);
            }

            queue = next;
        }
        return res;
    }
}
// @lc code=end

