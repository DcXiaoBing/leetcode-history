public void morrisInOrderTraversal(TreeNode root) {
	TreeNode node = root, prev = null; // 仅存放两个临时变量，O(1)空间复杂度
	while (node != null) { // 当前节点为空时，说明访问完成
		if (node.left == null) { // 左子树不存在时，访问+进入右节点
			visit(node);
			node = node.right;
		} else { // 左子树存在，寻找前驱节点。注意寻找前驱节点时，会不断深入右子树。不加判断时，若前驱节点的右子树已指向自己，会引起死循环
			prev = node.left;
			while (prev.right != null && prev.right != node) prev = prev.right;
			if (prev.right == null) { // 前驱节点未访问过，存放后继节点
				prev.right = node;
				node = node.left;
			} else { // 前驱节点已访问过，恢复树结构
				visit(node); // 确定访问过左子树后，访问当前节点
				prev.right = null;
				node = node.right;
			}
		}
	}
}


public void morrisPreOrderTraversal(TreeNode root) {
	TreeNode node = root, prev = null; // 仅存放两个临时变量，O(1)空间复杂度
	while (node != null) { // 当前节点为空时，说明访问完成
		if (node.left == null) { // 左子树不存在时，访问+进入右节点
			visit(node);
			node = node.right;
		} else { // 左子树存在，寻找前驱节点。注意寻找前驱节点时，会不断深入右子树。不加判断时，若前驱节点的右子树已指向自己，会引起死循环
			prev = node.left;
			while (prev.right != null && prev.right != node) prev = prev.right;
			if (prev.right == null) { // 前驱节点未访问过，存放后继节点
				visit(node); // 在确定前驱节点未访问过时，访问当前节点（注意与中序遍历的区别）
				prev.right = node;
				node = node.left;
			} else { // 前驱节点已访问过，恢复树结构
				prev.right = null;
				node = node.right;
			}
		}
	}
}

public void morrisPostOrderTraversal(TreeNode root) {
	TreeNode temp = new TreeNode(new Value(Value.INVALID_VALUE)), node = temp, prev = null; // 仅存放一个“哨兵”节点和两个临时变量，O(1)空间复杂度
	temp.left = root;
	while (node != null) { // 当前节点为空时，说明访问完成
		if (node.left == null) { // 左子树不存在时，进入右节点
			node = node.right;
		} else { // 左子树存在，寻找前驱节点。注意寻找前驱节点时，会不断深入右子树。不加判断时，若前驱节点的右子树已指向自己，会引起死循环
			prev = node.left;
			while (prev.right != null && prev.right != node) prev = prev.right;
			if (prev.right == null) { // 前驱节点未访问过，存放后继节点
				prev.right = node;
				node = node.left;
			} else { // 前驱节点已访问过，恢复树结构
				visitReverse(node.left, prev); // 确定访问过左子树后，逆序访问沿路节点（注意与中序遍历的区别）
				prev.right = null;
				node = node.right;
			}
		}
	}
}

/**
后续遍历稍显复杂，需要建立一个临时节点dump，令其左孩子是root。并且还需要一个子过程，就是倒序输出某两个节点之间路径上的各个节点。

步骤：

当前节点设置为临时节点dump。

1. 如果当前节点的左孩子为空，则将其右孩子作为当前节点。

2. 如果当前节点的左孩子不为空，在当前节点的左子树中找到当前节点在中序遍历下的前驱节点。

   a) 如果前驱节点的右孩子为空，将它的右孩子设置为当前节点。当前节点更新为当前节点的左孩子。

   b) 如果前驱节点的右孩子为当前节点，将它的右孩子重新设为空。倒序输出从当前节点的左孩子到该前驱节点这条路径上的所有节点。当前节点更新为当前节点的右孩子。

3. 重复以上1、2直到当前节点为空。
 */
// https://www.cnblogs.com/anniekim/archive/2013/06/15/morristraversal.html
// void reverse(TreeNode *from, TreeNode *to) // reverse the tree nodes 'from' -> 'to'.
// {
//     if (from == to)
//         return;
//     TreeNode *x = from, *y = from->right, *z;
//     while (true)
//     {
//         z = y->right;
//         y->right = x;
//         x = y;
//         y = z;
//         if (x == to)
//             break;
//     }
// }

// void printReverse(TreeNode* from, TreeNode *to) // print the reversed tree nodes 'from' -> 'to'.
// {
//     reverse(from, to);

//     TreeNode *p = to;
//     while (true)
//     {
//         printf("%d ", p->val);
//         if (p == from)
//             break;
//         p = p->right;
//     }

//     reverse(to, from);
// }

// void postorderMorrisTraversal(TreeNode *root) {
//     TreeNode dump(0);
//     dump.left = root;
//     TreeNode *cur = &dump, *prev = NULL;
//     while (cur)
//     {
//         if (cur->left == NULL)
//         {
//             cur = cur->right;
//         }
//         else
//         {
//             prev = cur->left;
//             while (prev->right != NULL && prev->right != cur)
//                 prev = prev->right;

//             if (prev->right == NULL)
//             {
//                 prev->right = cur;
//                 cur = cur->left;
//             }
//             else
//             {
//                 printReverse(cur->left, prev);  // call print
//                 prev->right = NULL;
//                 cur = cur->right;
//             }
//         }
//     }
// }

/*
 * @lc app=leetcode id=1022 lang=java
 *
 * [1022] Sum of Root To Leaf Binary Numbers
 */

// @lc code=start
/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode
 * left; TreeNode right; TreeNode() {} TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) { this.val = val; this.left
 * = left; this.right = right; } }
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

class Solution {
    // try a morris pre-order traversal
    public int sumRootToLeaf(TreeNode root) {
        int res = 0, cur = 0, steps;
        TreeNode pre;

        while (root != null) {
            if (root.left != null) {
                pre = root.left;
                steps = 1;

                // find predecessor of root in in-order traverse
                while (pre.right != null && pre.right != root){
                    pre = pre.right;
                    steps++;
                }

                if(pre.right == null) { // if not set link
                    pre.right = root;
                    cur = cur * 2 + root.val; // update path value (can be called as visit)
                    root = root.left; // start to visit left sub-tree
                } else { // left subtree has been visited
                    
                    // check whether pre is leaf(visit node)
                    if(pre.left == null) res += cur;

                    for(int i = 0; i < steps; i++) cur /= 2;
                    pre.right = null;
                    root = root.right;
                }
            } else {
                // left is null, menas it is a leaf node with right pointer modified
                // or it is a original node
                // So cannot decide whether to add value here

                cur = cur * 2 + root.val; // update path value

                // check whether root is leaf(visit node)
                // only last node execute this node (only leaf node could have empty right node)
                if(root.right == null) res += cur; 
                root = root.right;
            }
        }
        return res;
    }
}
// @lc code=end
