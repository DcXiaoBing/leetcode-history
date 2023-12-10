/*
 * @lc app=leetcode id=878 lang=java
 *
 * [878] Nth Magical Number
 */

// @lc code=start
class Solution {
  // devisible by a or b
  // So, first, we choose Math.min(a, b)
  // Then multiply it by 1,2,...n

  // suppose a is smaller.
  // x * a^i, b^j

  // CORE IDEA:
  // How to compute ranking for a number x
  // Just check solution 2. The key point is LCM(least common multiplier)
  // If we know the way of computing ranking, then we can binary search.
  // Also, if we know the way of computing ranking, we can infer the number.
  // Another key point for infer is:
  // if X is magical, then X + L is magical (L is LCM(least common multiplier))

  private static int modulo = (int)1e9 + 7;
//   public int nthMagicalNumber(int n, int a, int b) {
//     int L = a / gcd(a, b) * b; // LCM least common multiplier.
//     int M = L / a + L / b - 1; // how many mgical number for every L number.
//     int q = n / M, r = n % M;

//     long res = 1L * q * L % modulo;
//     if (r == 0) return (int)res;

//     // try to find numbers.
//     // res currently is magical
//     // Then we can get next by adding A or B.
//     int headA = a, headB = b; // pointing next available number

//     // increase the nxt number r - 1 times so that r-th number is either headA or headB
//     // remember, no modulo here, will break <=
//     for (int i = 0; i < r - 1; i++) {
//       if (headA <= headB) headA = (headA + a);
//       else headB = (headB + b);
//     }
//     return (int)((res + Math.min(headA, headB)) % modulo);
//   }

//   private int gcd(int a, int b) {
//     if (a == 0) return b;
//     return gcd(b % a, a);
//   }

  public int nthMagicalNumber(int n, int a, int b) {
    long l = 0, r = 1L * n * a, mid, L = a / gcd(a, b) * b;
    while (l < r) {
      mid = l + (r - l) / 2;
      long cnt = mid / a + mid / b - mid / L;
      if (cnt < n) l = mid + 1;
      else r = mid;
    }
    return (int)(l % modulo);
  }

  private int gcd(int a, int b) {
    if (a == 0) return b;
    return gcd(b % a, a);
  }
}
// @lc code=end

