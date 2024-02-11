https://leetcode.com/problems/sum-of-scores-of-built-strings/

class Solution {
  // Z array
  // z algorithm
  // https://oi-wiki.org/string/z-func/
  // Same idea as KMP, use known range to comoute next

  // 对于内层 while 循环，每次执行都会使得r向后移至少1位，而 r < n - 1，所以总共只会执行n次。
  public long sumScores(String _s) {
    char[] s = _s.toCharArray();
    int n = s.length, z[] = new int[n], l = 0, r = 0; // track right most interval
    long res = n;

    // z[i] is the len of longest common prefix(LCP) for s and s[i...n - 1]
    // z[i] covers s[0...z[i] - 1] and s[i...i + z[i] - 1]
    // for right most interval. s[l,r] is equal to s[0,r-l]
    for (int i = 1; i < s.length; i++) {
      if (i <= r) {
        // Covered by range, so s[i,r] == s[i - l, r - l] (by definition)
        // untilze z[i-l] to convert this match to the head.
        // We know z[i-l] covers s[i - l,i - l + z[i - l]]. And this is the longest we could have for subarray starting at i-l.
        // So for s[i,r], we know the best would be z[i - l]. (if r is big enough)
        // But we also need to care about whether i~r length is less than z[i-l]
        z[i] = Math.min(z[i - l], r - i + 1);
      }

      // extend by naive method
      // If i > r, we can only compute this by naive method. (we don't have extra information)
      // If i + z[i] reached r, we should try to expand.
      while (i + z[i] < s.length && s[i + z[i]] == s[z[i]]) z[i]++;
      if (i + z[i] - 1 > r) {
        l = i;
        r = i + z[i] - 1;
      }

      res += z[i];
    }
    return res;
  }
}
