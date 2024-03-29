// Manacher to find longest palindrome substring.
  public int countSubstrings(String _s) {
    char[] s = new char[_s.length() * 2 + 3];
    int n = s.length;

    // make a #c1#c2#...cn# format!
    s[0] = '$';
    s[s.length - 2] = '#';
    s[s.length - 1] = '^';

    for (int i = 0, p = 1; i < _s.length(); i++, p += 2) {
      s[p] = '#';
      s[p + 1] = _s.charAt(i);
    }

    int c = 0, r = 0, rs[] = new int[n];
    for (int i = 1; i < n - 1; i++) {
      // if current idx is covered by r, we have info to reuse
      int l = 2 * c - i; // symmetric point(palindrome's l and r are similar)

      // since [l,r] is symmetric, so the pal centered at i can be mapped to left part, [l,c]
      // And symmetic center of i is l. So we use rs[l]
      if (i < r) rs[i] = Math.min(rs[l], r - i);
      while (s[i - rs[i]] == s[i + rs[i]]) rs[i]++; // rs[i] is the half-len([l,mid] [mid,r]) for palindrome

      if (i + rs[i] > r) {
        r = i + rs[i];
        c = i;
      }
    }

    int res = 0;
    for (int i = 1; i < n - 1; i++) res += rs[i] / 2;
    return res;
  }
