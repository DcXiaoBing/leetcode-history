/*
 * @lc app=leetcode id=826 lang=java
 *
 * [826] Most Profit Assigning Work
 */

// @lc code=start
class Solution {
    
    // one job can be done multiple times
    // a worker can only do one job
    // just find a most profit job for this worker
    
    // sort by profit then difficulty
    // TreeMap?
    
    // segment tree
    // 1~max is the whole segment
    public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
        // get max profit for each index
        int[] data = new int[100001];
        for(int i = 0; i < difficulty.length; i++){
            data[difficulty[i]] = Math.max(data[difficulty[i]], profit[i]);
        }
        SegmentTree st = new SegmentTree(data);
        int res = 0;
        for(int w : worker){
            res += st.query(1, w);
        }
        return res;
    }
}

// get max profit by giving a range of difficulty
class SegmentTree{
    int[] tree;
    int len;
    
    SegmentTree(int[] data){
        tree = new int[4*data.length];
        len = data.length;
        buildSegmentTree(data, 0, 0, len - 1);
    }
    
    // lBoundary and rBoundary is the index boundary for data array
    private void buildSegmentTree(int[] data, int curIdx, int lBoundary, int rBoundary){
        if(lBoundary == rBoundary){
            tree[curIdx] = data[lBoundary];
            return ;
        }
        
        int mid = lBoundary + (rBoundary - lBoundary) / 2;
        int lChild = getLeftChild(curIdx);
        int rChild = getRightChild(curIdx);
        
        buildSegmentTree(data, lChild, lBoundary, mid);
        buildSegmentTree(data, rChild, mid + 1, rBoundary);
        
        // merge
        tree[curIdx] = Math.max(tree[lChild], tree[rChild]);
    }
    public int query(int queryL, int queryR){
        return query(0, 0, len - 1, queryL, queryR);
    }
    private int query(int curIdx, int treeL, int treeR, int queryL, int queryR){
        // match
        if(treeL == queryL && treeR == queryR){
            return tree[curIdx];
        }
        
        // get data from left and right
        int mid = treeL + (treeR - treeL) / 2;
        int lChild = getLeftChild(curIdx), rChild = getRightChild(curIdx);
        
        
        // in left subtree
        if(queryR <= mid){
            return query(lChild, treeL, mid, queryL, queryR);
        }
        // in right subtree
        if(queryL > mid){
            return query(rChild, mid + 1, treeR, queryL, queryR);
        }
        
        // need merge
        int tempL = 0, tempR = 0;
        tempL = query(lChild, treeL, mid, queryL, mid);
        tempR = query(rChild, mid + 1, treeR, mid + 1, queryR);
        return Math.max(tempL, tempR);
    }
    
    private int getLeftChild(int curIdx){
        return curIdx * 2 + 1;
    }
    private int getRightChild(int curIdx){
        return curIdx * 2 + 2;
    }
}
// @lc code=end

