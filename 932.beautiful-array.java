/*
 * @lc app=leetcode id=932 lang=java
 *
 * [932] Beautiful Array
 */

// @lc code=start
class Solution {
  // Two key points:
  // 1. a + c = 2 * b => a and c has to be both odd or both even.
  // Otherwise a + c will be an odd number.
  // So we can put odd to left and even to right.
  // 2.If we devide 2 for both sied, the equation still stands.
  // So if a sequence is valid, then we can map 1,2...n to 2,4...2n and get another valid sequence.

  HashMap<Integer, int[]> dp = new HashMap<>();
  public int[] beautifulArray(int n) {
    if(dp.containsKey(n)) return dp.get(n);
    if(n == 1) return new int[]{1};

    int[] l = beautifulArray((n + 1) / 2); // make mid belong to left
    int[] r = beautifulArray(n / 2);

    // combine by mapping.
    int res[] = new int[n], p = 0;
    for(int num : l) res[p++] = num * 2 - 1; // 1, 2, 3 -> 1, 3, 5
    for(int num : r) res[p++] = num * 2; // 1, 2, 3 -> 2, 4, 6
    return res;
  }
}
// @lc code=end

