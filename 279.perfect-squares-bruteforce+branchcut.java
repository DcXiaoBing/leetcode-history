import java.util.*;

/*
 * @lc app=leetcode id=277 lang=java
 *
 * [277] Find the Celebrity
 */

// @lc code=start
public class Solution extends Relation {
  // Use the defination, we know that we can exclude a candidate when call knows once.
  // 1. If a know b, then a cannot be the celebrity.
  // 2. if a don't know b, then b cannot be celebrity.
  public int findCelebrity(int n) {
    if(n == 0) return -1;
    else if(n == 1) return 0;

    int l = 0, r = 1;
    while(r < n) {
      if(knows(l, r)) l = r; // l know r, l cannot be celebrity
      r++;
    }

    // check the only candidate.
    // every one should know him.
    // need to take care of special case [[1,1],[1,1]]
    for(int i = 0; i < n; i++) if(i != l) {
      if(!knows(i, l) || knows(l, i)) return -1;
    }
    return l;
  }
}
// @lc code=end

