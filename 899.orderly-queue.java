/*
 * @lc app=leetcode id=899 lang=java
 *
 * [899] Orderly Queue
 */

// @lc code=start
class Solution {
  // 1. after some moves, we can make the smallest char to head.
  // 2. after some moves, we can make secondary smallest char to 2nd index
  // ...
  // All other chars remain at same position
  // !! WRONG: "xmvzi" 2 => "imvxz"

  // 1. when k = 1, try all rotation and find the smallest
  // find smallest one is not good, because you don't know following chars.
  // 2. when k >= 2, we can swap a pair. e.g. steps:
  // make the pair to index 0 and 1
  // move remove and append the num at index 0 to tail. The num at idx 1 now is at idx 0
  // Keep remove num at idx 1, until we met that num
  // swap complete

  // When we can swap any pair, we can sort the string with bubble sort
  public String orderlyQueue(String ss, int k) {
    char[] s = ss.toCharArray();
    Set

    if (k > 1) {
      Arrays.sort(s);
      return new String(s);
    }

    String res = ss;
    for (int i = 1; i < s.length; i++) { // i is the new string's head
      String temp = ss.substring(i) + ss.substring(0, i);
      if (res.compareTo(temp) > 0) res = temp;
    }
    return res;
  }
}
// @lc code=end

