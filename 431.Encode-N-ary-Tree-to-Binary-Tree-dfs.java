import java.util.*;

import javax.swing.tree.TreeNode;

import org.w3c.dom.Node;

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
        Iterator<Node> it = root.children.iterator();
        if(it.hasNext()) nroot.left = encode(it.next());
        
        TreeNode p = nroot.left;
        while(it.hasNext()) {
            p.right = encode(it.next());
            p = p.right;
        }
        
        return nroot;
    }
	
    // Decodes your binary tree to an n-ary tree.
    public Node decode(TreeNode root) {
        if(root == null) return null;
        Node nroot = new Node(root.val);
        nroot.children = new ArrayList<>();
        
        TreeNode p = root.left;
        while(p != null) {
            nroot.children.add(decode(p));
            p = p.right;
        }
        
        return nroot;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.decode(codec.encode(root));
// @lc code=end

