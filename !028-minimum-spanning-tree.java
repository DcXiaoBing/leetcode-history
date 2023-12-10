class Solution {
  // minimum spanning tree.
  // 1e3 points -> 1e6 edges
  // 1e6 nlogn -> 6e6
  // Kruskal's Algorithm
//   public int minCostConnectPoints(int[][] points) {
//     int n = points.length;
//     DSU dsu = new DSU(n);

//     // PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[2] - b[2]);
//     List<int[]> heap = new ArrayList<>();
//     for (int i = 0; i < n; i++) for (int j = i + 1; j < n; j++) heap.add(new int[]{i, j, distance(points[i], points[j])});
//     Collections.sort(heap, (a, b) -> a[2] - b[2]);

//     int res = 0;
//     // while (!heap.isEmpty()) {
//     //   int[] cur = heap.poll();
//     for (int[] cur : heap) {
//       if (dsu.union(cur[0], cur[1])) res += cur[2];
//     }
//     return res;
//   }

  private int distance(int[] p1, int[] p2) {
    return Math.abs(p1[0] - p2[0]) + Math.abs(p1[1] - p2[1]);
  }

  // Prim's Algorithm
  // a. with heap -> mlog(m)
  // b. with minDist arry -> n^2
  //    n-1 edges to pick, each iteration needs to go through all nodes
  //    each edge would only be visited once
  // So implementation b. gives good time complexity when m is large enough.
  public int minCostConnectPoints(int[][] points) {
    int n = points.length, dist[] = new int[n], cnt = 0;
    boolean used[] = new boolean[n];

    Arrays.fill(dist, Integer.MAX_VALUE);
    dist[0] = 0;

    // cnt is the cnt of nodes in BST
    int res = 0;
    while (cnt < n) {
      int min = Integer.MAX_VALUE, minIdx = 0;
      for (int i = 0; i < n; i++) if (!used[i]) if (dist[i] < min)  {
        min = dist[i];
        minIdx = i;
      }

      res += min;
      used[minIdx] = true;
      cnt++;

      // update dist
      for (int i = 0; i < n; i++) if (!used[i]) {
        int temp = distance(points[minIdx], points[i]);
        if (temp < dist[i]) dist[i] = temp;
      }

      // System.out.println(Arrays.toString(dist));
    }

    return res;
  }
}

class DSU {
  int[] parent, size;

  DSU(int n) {
    parent = new int[n];
    size = new int[n];

    for (int i = 0; i < n; i++) {
      parent[i] = i;
      size[i] = 1;
    }
  }

  public int find(int x) {
    if (parent[x] != x) parent[x] = find(parent[x]);
    return parent[x];
  }

  public boolean union(int x, int y) {
    int px = find(x), py = find(y);
    if (px == py) return false;

    if (size[px] >= size[py]) {
      size[px] += size[py];
      parent[py] = px;
    } else {
      size[py] += size[px];
      parent[px] = py;
    }
    return true;
  }
}
