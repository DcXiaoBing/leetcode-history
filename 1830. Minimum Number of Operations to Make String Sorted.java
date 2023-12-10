class Solution {
  // Key: this operation makes string lexilogical order one smaller.
  // To make permutation larger, we do this:
  // 1. find last increase, call it x. Number after x are in a decreasing order!!!
  // 2. find the smallest char y that larger than x in the chars in the right of x.
  // 3. swap x y.
  // 4. Because chars are decreasing now, so just reverse the chars to the right of x.

  // So to the reverse of finding next permuataion(Which is exactly the operation mentioned in the context)
  // 1. find last decrease, call it x
  // 2. find the last/largest y one that smaller than x. (Because they are increasing now)
  // 3. swap x y.
  // 4. reverse

  // So the target now is to find the lexilogical rank.
  // This is simple. The key for this is compute from back.
  // Because when compute, we can consider substring to tail is sorted
  // So c + substring -> #char_less_than_c * factorial[substring_len]
  static int factories[] = new int[3001], inverse[] = new int[3001], modulo = (int)1e9 + 7;
  static {
    factories[0] = inverse[0] = 1; // 0! = 1

    for (int i = 1; i <= 3000; i++) {
      factories[i] = (int)((long)i * factories[i - 1] % modulo);
      inverse[i] = pow(factories[i], modulo - 2); // 费马小定理 推 模反
    }
  }

  public int makeStringSorted(String s) {
    int fre[] = new int[26], len = s.length();
    long res = 0;
    for (int i = len - 1; i >= 0; i--) {
      fre[s.charAt(i) - 'a']++;
      long less = 0;
      for (int j = 0; j < s.charAt(i) - 'a'; j++) less += fre[j];
      // System.out.println(less);

      long perm = less * factories[len - i - 1] % modulo; // substring len don't include s[i]
      for (int j = 0; j < 26; j++) perm = perm * inverse[fre[j]] % modulo;
      res = (res + perm) % modulo;
    }
    return (int)res;
  }

  private static int pow(long base, int exponent) {
    long res = 1;
    while(exponent > 0) {
      if((exponent & 1) != 0) res = res * base % modulo;
      base = base * base % modulo;
      exponent >>= 1;
    }
    return (int)res;
  }
}
