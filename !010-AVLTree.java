class Solution {
    class Node{
		long val;
		int height;
        long count;
        long totalCount;
		Node left;
		Node right;
		Node(int val){
			this.val = val;
			height = 1;
			left = null;
			right = null;
			count = 1;
            totalCount = 1;
		}
	}
    
    long lc, rc;
    public int createSortedArray(int[] instructions) {
        long ans = 0;
        long mod = 1000000007;
        Node root = null;
        for(int i: instructions){
            lc = 0; rc = 0;
            search(root, i);
            ans = (ans + Math.min(lc, rc))%mod;
            root = insert(root, i);
        }
        return (int)ans;
    }
    
    private Node insert(Node root,int val){
		if(root == null){
			root = new Node(val);
			return root;
        }
        if(val == root.val){
            root.count += 1;    
        }else if(val < root.val){
			root.left = insert(root.left,val);
		}else if(val > root.val){
			root.right = insert(root.right,val);
		}
		root.height = 1 + Math.max(getHeight(root.left),getHeight(root.right));
		root.totalCount = root.count + getCount(root.left) + getCount(root.right);
		int bFactor = getBFactor(root);
		if(bFactor > 1 && val < root.left.val){
			return rightRotate(root);
		}
		if(bFactor > 1 && val > root.left.val){
			root.left = leftRotate(root.left);
			return rightRotate(root);
		}
		if(bFactor < -1 && val > root.right.val){
			return leftRotate(root);
		}
		if(bFactor < -1 && val < root.right.val){
			root.right = rightRotate(root.right);
			return leftRotate(root);
		}
		return root;
	}
 
	private int getHeight(Node root){
		if(root == null){
			return 0;
		}
		return root.height;
	}
 
	private long getCount(Node root){
		if(root == null){
			return 0;
		}
		return root.totalCount;
	}
 
	private int getBFactor(Node root){
		if(root == null){
			return 0;
		}
		return getHeight(root.left) - getHeight(root.right);
	}
 
	private Node rightRotate(Node root){
		Node x = root.left;
		Node y = x.right;
		x.right = root;
		root.left = y;
		root.height = 1 + Math.max(getHeight(root.left),getHeight(root.right));
		root.totalCount = root.count + getCount(root.left) + getCount(root.right);
		x.height = 1 + Math.max(getHeight(x.left),getHeight(x.right));
		x.totalCount = x.count + getCount(x.left) + getCount(x.right);
		return x;
	}
 
	private Node leftRotate(Node root){
		Node x = root.right;
		Node y = x.left;
		x.left = root;
		root.right = y;
		root.height = 1 + Math.max(getHeight(root.left),getHeight(root.right));
		root.totalCount = root.count + getCount(root.left) + getCount(root.right);
		x.height = 1 + Math.max(getHeight(x.left),getHeight(x.right));
		x.totalCount = x.count + getCount(x.left) + getCount(x.right);
		return x;
	}
 
	private void search(Node root,int val){
		if(root == null){
			return;
		}
		if(val == root.val){
			lc += getCount(root.left);
            rc += getCount(root.right);
		}else if(val < root.val){
            rc += (root.count + getCount(root.right));
			search(root.left,val);
		}else{
			lc += (root.count + getCount(root.left));
            search(root.right, val);
		}
	}
}