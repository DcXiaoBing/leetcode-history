
// Simpliest Segment tree
// l, r should always be the range that the root covers!!!
class SegmentTree1 {
  long[] nodes;
  int n;
  SegmentTree1(int n) {
    this.n = n;
    nodes = new long[4 * n + 4];
  }

  SegmentTree1(int[] data){
    this.n = data.length;
    nodes = new long[4*data.length];
    buildSegmentTree(data, 0, 0, n - 1);
  }

  // lBoundary and rBoundary is the index boundary for data array
  private void buildSegmentTree(int[] data, int root, int lBoundary, int rBoundary){
    if(lBoundary == rBoundary){
      nodes[root] = data[lBoundary];
      return ;
    }

    int mid = lBoundary + (rBoundary - lBoundary) / 2;
    int lChild = lc(root);
    int rChild = rc(root);

    buildSegmentTree(data, lChild, lBoundary, mid);
    buildSegmentTree(data, rChild, mid + 1, rBoundary);

    // merge
    nodes[root] = compute(nodes[lChild], nodes[rChild]);
  }

  public void update(int idx, long val) {
    update(0, 0, n - 1, idx, val);
  }

  private void update(int root, int l, int r, int idx, long val) {
    if (l == r) {
      nodes[root] = val; // used
      return;
    }

    int mid = l + (r - l) / 2;
    int lRoot = lc(root), rRoot = rc(root);

    if (idx <= mid) {
      update(lRoot, l, mid, idx, val);
    } else {
      update(rRoot, mid + 1, r, idx, val);
    }
    nodes[root] = compute(nodes[lRoot], nodes[rRoot]);
  }

  public long query(int r) {
    return query(0, 0, n - 1, 0, r);
  }

  private long query(int root, int l, int r, int ql, int qr) {
    if (ql == l && qr == r) return nodes[root];
    if (l == r) {
      return nodes[root];
    }

    int mid = l + (r - l) / 2, lRoot = lc(root), rRoot = rc(root);
    if (qr <= mid) return query(lRoot, l, mid, ql, qr);
    else if (ql > mid) return query(rRoot, mid + 1, r, ql, qr);

    long lRes = query(lRoot, l, mid, ql, mid);
    long rRes = query(rRoot, mid + 1, r, mid + 1, qr);
    return compute(lRes, rRes);
  }

  private long compute(long a, long b) {
    return Math.max(a, b);
  }

  private int lc(int node) {
    return node * 2 + 1;
  }
  private int rc(int node) {
    return node * 2 + 2;
  }
}


// SegmentTree for sum
// nodes is always the source of truth.
class SegmentTree {
  long[] nodes, lazy;
  int n;
  SegmentTree(int n) {
    this.n = n;
    nodes = new long[4 * n + 4];
    lazy = new long[4 * n + 4];
  }

  // Val is the diff between new val and old val.
  // to update a single index, make idx == ul == ur
  public void update(int ul, int ur, long val) {
    update(0, 0, n - 1, ul, ur, val);
  }

  private void update(int root, int l, int r, int ul, int ur, long val) {
    // 当前区间为修改区间的子集时直接修改当前节点的值,然后打标记,结束修改
    // This determines that, when recursively call update, we dont update value of ul and ur
    if (ul <= l && r <= ur) {
      nodes[root] += (r - l + 1) * val;
      lazy[root] += val;
      return;
    }

    pushDown(root, l, r);

    int mid = l + (r - l) / 2, lRoot = lc(root), rRoot = rc(root);
    if (ul <= mid) update(lRoot, l, mid, ul, ur, val);
    if (ur > mid) update(rRoot, mid + 1, r, ul, ur, val);


    // nodes is always the source of truth.
    // when update lazy, nodes is also updated.
    nodes[root] = nodes[lRoot] + nodes[rRoot];
  }

  // l,r is the range covered by root
  public long query(int root, int l, int r, int ql, int qr) {
        // System.out.println("root: " + root + " l: " + l + " r: " + r + " ql: " + ql + " qr: " + qr + " nodes: " + nodes[root]);
    // 当前区间为询问区间的子集时直接返回当前区间的和
    if (ql <= l && r <= qr) return nodes[root];

    pushDown(root, l, r);

    int mid = l + (r - l) / 2, lRoot = lc(root), rRoot = rc(root);

    long sum = 0;
    if (ql <= mid) sum += query(lRoot, l, mid, ql, qr);
    if (qr > mid) sum += query(rRoot, mid + 1, r, ql, qr);

    // System.out.println("root: " + root + " l: " + l + " r: " + r + " ql: " + ql + " qr: " + qr + " sum: " + sum);
    return sum;
  }

  // push lazy mark into child node
  // l, r is the range covered by root
  private void pushDown(int root, int l, int r) {
    // when l == r, we know the tree's val is up to date and we can ignore lazy
    // lazy is always ignored for leaf nodes.
    // if (l == r || lazy[root] == 0) return;

    int mid = l + (r - l) / 2;
    int lRoot = lc(root), rRoot = rc(root);

    if (l != r && lazy[root] != 0) {
      lazy[lRoot] += lazy[root];
      lazy[rRoot] += lazy[root];

      nodes[lRoot] += (mid - l + 1) * lazy[root];
      nodes[rRoot] += (r - mid) * lazy[root];
      lazy[root] = 0;
    }
  }

  private int lc(int node) {
    return node * 2 + 1;
  }
  private int rc(int node) {
    return node * 2 + 2;
  }
}
