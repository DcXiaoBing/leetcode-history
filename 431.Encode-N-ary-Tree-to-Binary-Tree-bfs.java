/*
 * @lc app=leetcode id=431 lang=java
 *
 * [431] Encode N-ary Tree to Binary Tree
 */

// @lc code=start
/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

class Codec {
    // left pointer point to left most child
    // right pointer point to siblings with same parent
    
    // Encodes an n-ary tree to a binary tree.
    public TreeNode encode(Node root) {
        if(root == null) return null;
        
        TreeNode nroot = new TreeNode(root.val);
        Deque<Node> queue = new ArrayDeque<>(), next;
        Deque<TreeNode> nqueue = new ArrayDeque<>(), nnext;
        queue.add(root);
        nqueue.add(nroot);
        
        while(!queue.isEmpty()) {
            next = new ArrayDeque<>();
            nnext = new ArrayDeque<>();
            
            while(!queue.isEmpty()) {
                Node cur = queue.poll();
                TreeNode ncur = nqueue.poll();
                TreeNode p = null;
                
                for(Node child : cur.children) {
                    TreeNode nchild = new TreeNode(child.val);
                    next.add(child);
                    nnext.add(nchild);
                    
                    if(ncur.left == null) ncur.left = p = nchild;
                    else {
                        p.right = nchild;
                        p = p.right;
                    }
                }
            }
            
            queue = next;
            nqueue = nnext;
        }
        return nroot;
    }
	
    // Decodes your binary tree to an n-ary tree.
    public Node decode(TreeNode root) {
        if(root == null) return null;
        Node nroot = new Node(root.val);
        
        Deque<TreeNode> queue = new ArrayDeque<>(), next;
        Deque<Node> nqueue = new ArrayDeque<>(), nnext;
        
        queue.add(root);
        nqueue.add(nroot);
        
        while(!queue.isEmpty()) {
            next = new ArrayDeque<>();
            nnext = new ArrayDeque<>();
            
            while(!queue.isEmpty()) {
                TreeNode cur = queue.poll();
                Node ncur = nqueue.poll();
                ncur.children = new ArrayList<>();
                
                TreeNode p = cur.left;
                while(p != null) {
                    Node np = new Node(p.val);
                    ncur.children.add(np);
                    
                    next.add(p);
                    nnext.add(np);
                    
                    p = p.right;
                }
            }
            
            queue = next;
            nqueue = nnext;
        }
        
        return nroot;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.decode(codec.encode(root));
// @lc code=end

