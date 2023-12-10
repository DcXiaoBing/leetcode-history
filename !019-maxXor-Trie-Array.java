class Solution {
    public int[] maximizeXor(int[] nums, int[][] queries) {
        int Q = queries.length;
        int[][] qs = new int[Q][];
        for(int i = 0;i < Q;i++){
            qs[i] = new int[]{queries[i][0], queries[i][1], i};
        }
        Arrays.sort(qs, (x, y) -> x[1] - y[1]);
        Arrays.sort(nums);

        TrieArrayBinary trie = new TrieArrayBinary(31);
        int[] ret = new int[Q];
        int p = 0;
        for(int z = 0;z < Q;z++){
            while(p < nums.length && nums[p] <= qs[z][1]){
                trie.add(nums[p]);
                p++;
            }
            ret[qs[z][2]] = p == 0 ? -1 : (int)(trie.xormax(qs[z][0]));
        }
        return ret;
    }

    public class L {
        public int[] a;
        public int p = 0;

        public L(int n) { a = new int[n]; }
        public L add(int n)
        {
            if(p >= a.length)a = Arrays.copyOf(a, a.length*3/2+1);
            a[p++] = n;
            return this;
        }
        public int size() { return p; }
        public L clear() { p = 0; return this; }
        public int[] toArray() { return Arrays.copyOf(a, p); }
        @Override
        public String toString() {return Arrays.toString(toArray());}
    }


    public class TrieArrayBinary {
        // root = 0
        public L next;
        public int gen;
        public int W;

        public TrieArrayBinary(int W)
        {
            this.W = W;
            this.next = new L(2);
            this.gen = 1;
            this.next.add(-1).add(-1);
        }

        public void add(long s)
        {
            int cur = 0;
            for(int d = W-1;d >= 0;d--){
                int v = (int)(s>>>d&1);
                int nex = next.a[cur*2+v];
                if(nex == -1){
                    nex = next.a[cur*2+v] = gen++;
                    next.add(-1).add(-1);
                }
                cur = nex;
            }
        }

        public long xormax(long x)
        {
            if(gen == 1)return 0;
            int cur = 0;
            long ret = 0;
            for(int d = W-1;d >= 0;d--){
                if(cur == -1){
                    ret |= x<<-d>>>-d;
                    break;
                }
                int xd = (int)(x>>>d&1);
                if(next.a[cur*2|xd^1] != -1){
                    ret |= 1L<<d;
                    cur = next.a[cur*2|xd^1];
                }else{
                    cur = next.a[cur*2|xd];
                }
            }
            return ret;
        }

        public int[] des()
        {
            int[] des = new int[gen];
            for(int i = gen-1;i >= 0;i--){
                if(next.a[2*i] != -1)des[i] += des[next.a[2*i]];
                if(next.a[2*i+1] != -1)des[i] += des[next.a[2*i+1]];
                if(des[i] == 0)des[i] = 1;
            }
            return des;
        }

        public int mex(long x, int[] des)
        {
            int ret = 0;
            for(int cur = 0, d = W-1;d >= 0 && cur != -1;d--){
                int xd = (int)(x>>>d&1);
                if(next.a[2*cur|xd] != -1 && des[next.a[2*cur|xd]] == 1<<d){
                    ret |= 1<<d;
                    cur = next.a[2*cur|xd^1];
                }else{
                    cur = next.a[2*cur|xd];
                }
            }
            return ret;
        }

        public boolean contains(long x, int low)
        {
            int cur = 0;
            for(int d = W-1;d >= low;d--){
                int v = (int)(x>>>d&1);
                int nex = next.a[cur*2+v];
                if(nex == -1)return false;
            }
            return true;
        }
    }

}

// Xor cnt less than target
class Solution {
  Trie root = new Trie();

  public int countPairs(int[] nums, int low, int high) {
    int res = 0;
    for (int num : nums) {
      if(low == 0) res += cnt(high, num);
      else res += cnt(high, num) - cnt(low - 1, num);

      insert(num);
      // System.out.println(cnt(high, num) + " " + cnt(low - 1, num));
    }
    return res;
  }

  private int cnt(int max, int num) {
    int val = 0, res = 0;
    Trie cur = root;

    for (int i = 20; i >= 0; i--) {
      int bit = num & (1 << i);
      // System.out.printf("num:%d, res:%d max:%d %n", num, res, max);
      if(bit == 0) { // bit 0, go to r is increasing
        if ((val | (1 << i)) <= max) {
          if(cur.l != null) res += cur.l.cnt;
          if(cur.r != null) {
            cur = cur.r;
            val = val | (1 << i);
          } else return res;
        } else {
          if(cur.l != null) cur = cur.l;
          else return res;
        }
      } else {
        if ((val | (1 << i)) <= max) { // go to l is increasing
          if(cur.r != null) res += cur.r.cnt;
          if(cur.l != null) {
            cur = cur.l;
            val = val | (1 << i);
          } else return res;
        } else {
          if(cur.r != null) cur = cur.r;
          else return res;
        }
      }
      // System.out.printf("num:%d, res:%d %n", num, res);
    }
    res += cur.cnt;
    return res;
  }

  private void insert(int num) {
    Trie node = root;
    for (int i = 20; i >= 0; i--) {
      int bit = num & (1 << i);
      node.cnt++;

      if(bit == 0) { // bit 0
        if(node.l == null) node.l = new Trie();
        node = node.l;
      } else {
        if(node.r == null) node.r = new Trie();
        node = node.r;
      }
    }
    node.cnt++; // leaf node
  }
}

class Trie {
  Trie l, r;
  int cnt;
}
