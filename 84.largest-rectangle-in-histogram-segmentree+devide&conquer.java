import java.util.Arrays;

/*
 * @lc app=leetcode id=84 lang=java
 *
 * [84] Largest Rectangle in Histogram
 */

// @lc code=start
class Solution {
    // segment tree + devide and conquer
    // answer is divided by minimum height in this range

    // logn time to find min for each range
    public int largestRectangleArea(int[] heights) {
        SegmentTree seg = new SegmentTree(heights);

        return helper(heights, seg, 0, heights.length - 1);
    }
    private int helper(int[] heights, SegmentTree seg, int l, int r) {
        if(l > r) return 0;
        if(l == r) return heights[l];

        // seperate by minIdx !!!
        int minIdx = seg.query(l, r);
        int resL = helper(heights, seg, l, minIdx - 1);
        int resR = helper(heights, seg, minIdx + 1, r);
        return Math.max(heights[minIdx] * (r-l+1), Math.max(resL, resR));
    }
}

class SegmentTree {
    int nodes[], heights[], n;
    public SegmentTree(int[] heights) {
        nodes = new int[heights.length * 4];
        this.n = heights.length - 1;
        this.heights = heights;

        for(int i = 0; i < heights.length; i++) update(i, 0, n, 0);
    }

    private void update(int idx, int l, int r, int root) {
        if(l == r) {nodes[root] = idx; return ;}
        
        int mid = l + (r - l) / 2;
        if(idx <= mid) update(idx, l, mid, root * 2 + 1);
        else update(idx, mid + 1, r, root * 2 + 2);

        if(heights[nodes[root*2+1]] < heights[nodes[root*2+2]]) nodes[root] = nodes[root*2+1];
        else nodes[root] = nodes[root*2+2];
    }

    public int query(int ql, int qr) {
        return query(ql, qr, 0, n, 0);
    }
    private int query(int ql, int qr, int l, int r, int root) {
        if(ql == l && qr == r) return nodes[root];

        int mid = l + (r - l) / 2;
        if(qr <= mid) return query(ql, qr, l, mid, root*2+1);
        else if(ql > mid) return query(ql, qr, mid + 1, r, root*2+2);

        int resL = query(ql, mid, l, mid, root * 2 + 1);
        int resR = query(mid + 1, qr, mid + 1, r, root * 2 + 2);
        if(heights[resL] < heights[resR]) return resL;
        else return resR;
    }
}
// @lc code=end

