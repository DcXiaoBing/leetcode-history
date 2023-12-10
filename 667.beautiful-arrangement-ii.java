/*
 * @lc app=leetcode id=667 lang=java
 *
 * [667] Beautiful Arrangement II
 */

// @lc code=start
class Solution {
  // Observe 1,n,2,n-1,3... we can get diff n-1,n-2,n-3,n-4
  // So we can generate k,k-1...2 using appropriate starting numebr
  // And we can put extra cnt of number in the head in increasing order, to make it have diff 1

  // to generate k-1 diffs(k,k-1,...,2), we need to append n behind n-k
  // So we can only put n - k numer at front
  // As for other number, diffs will be decreasing, so total distinct will not exceed k
  public int[] constructArray(int n, int k) {
    int res[] = new int[n];
    for (int i = 0; i < n - k; i++) res[i] = i + 1;
    int p = n - k, l = n - k + 1, r = n;
    // System.out.println(Arrays.toString(res));
    // k-1 could be odd or even, so use a if to make a judge
    while(p < n) {
      res[p++] = r--;
      if(p < n) res[p++] = l++;
    }
    return res;
  }
}
// @lc code=end

