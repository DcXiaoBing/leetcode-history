/*
 * @lc app=leetcode id=936 lang=java
 *
 * [936] Stamping The Sequence
 */

// @lc code=start
class Solution {
  // Brute force can pass, but time complexity is bad.
  // iterate on string, try to peel one at one time.
  // So outer loop will be O(n), inner loop will be O(mn)

  // So use the solution in answer
  // Each index can only affect range i-stamp.length ~ i+stamp.length
  // Remove the mismatch char
  // Each index at target should have a match and mismatch set. To record which window is complete.
  // We can get rid of `made` in solution, because we can infer indexes in `made` from start point
  public int[] movesToStamp(String stamp, String target) {
    char[] s = stamp.toCharArray(), t = target.toCharArray();
    List<HashSet<Integer>> mises = new ArrayList<>();
    List<HashSet<Integer>> matches = new ArrayList<>();
    Deque<Integer> queue = new ArrayDeque<>();
    List<Integer> ops = new ArrayList<>(); // this list is in reverse order. Because we handle the stamp at top first.
    boolean seen[] = new boolean[t.length];

    for (int i = 0; i <= t.length - s.length; i++) {
      // try to match at each index and record mismatch
      HashSet<Integer> mis = new HashSet<>(), match = new HashSet<>();
      for (int j = 0; j < s.length; j++)  {
        if(t[i + j] != s[j]) mis.add(i + j);
        else match.add(i + j);
      }
      mises.add(mis);
      matches.add(match);

      if(mis.isEmpty()) {
        ops.add(i);
        // add all idxs try to find the impact on other window.
        for(int j = 0; j < s.length; j++) if(!seen[i + j]) {
          queue.add(i + j);
          seen[i + j] = true; // this idx has been resolved to *
        }
      }
    }
    // try to use current * to resolve more
    while(!queue.isEmpty()) {
      int idx = queue.poll();

      // for each affected windos's start intdex
      // affected window can only start before idx
      for (int i = Math.max(0, idx - s.length + 1); i <= Math.min(t.length - s.length, idx); i++) {
        // handle the mis of current window start at i!!!
        HashSet<Integer> mis = mises.get(i);
        // only do those thigns when idx is contained. To reduce ops.
        if(mis.contains(idx)) {
          mis.remove(idx);
          if(mis.isEmpty()) {
            ops.add(i);
            // add all idxs try to find the impact on other window.
            for(Integer j : matches.get(i)) if(!seen[j]) {
              queue.add(j); // this place has to add a judge
              seen[j] = true; // this idx has been resolved to *
            }
          }
        }
      }
    }

    for (int i = 0; i < seen.length; i++) if(!seen[i]) return new int[0];
    int res[] = new int[ops.size()], p = res.length - 1;
    for (Integer idx : ops) res[p--] = idx;

    return res;
  }
}
// @lc code=end

