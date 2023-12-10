class Solution {
    // dp is failTo function
    public boolean repeatedSubstringPattern(String s) {
        int n = s.length();
        int[] dp = new int[n];
        // Construct partial match table (lookup table).
        // It stores the length of the proper prefix that is also a proper suffix.
        // ex. ababa --> [0, 0, 1, 2, 1]
        // ab --> the length of common prefix / suffix = 0
        // aba --> the length of common prefix / suffix = 1
        // abab --> the length of common prefix / suffix = 2
        // ababa --> the length of common prefix / suffix = 1
        for (int i = 1; i < n; ++i) {
            int j = dp[i - 1];
            while (j > 0 && s.charAt(i) != s.charAt(j)) {
                j = dp[j - 1];
            }
            if (s.charAt(i) == s.charAt(j)) {
                ++j;
            }
            dp[i] = j;
        }
    }

    // kmp算法
    public int strStr(String haystack, String needle) {
        if (needle == null || needle.length() == 0)
            return 0;
        if (haystack == null || haystack.length() == 0)
            return -1;
        if (haystack.length() < needle.length())
            return -1;

        char s[] = haystack.toCharArray();
        char p[] = needle.toCharArray();
        int fallBack[] = new int[p.length];
        int pLength = p.length, sLength = s.length;

        int base, cur;

        // 填充回退串
        // fallback 本质是needle串头部和尾部的长度匹配
        // fallBack存头部和尾部相等串的长度。刚好也是下次匹配的index
        fallBack[0] = 0;
        cur = 1;
        int temp;
        while (cur < pLength) {
            temp = fallBack[cur - 1];
            // 循环找 cur指向当前要匹配的字符（因为它是长度）fallback存长度，也正好是匹配点 延续点

            while (temp > 0 && p[cur] != p[temp])
                temp = fallBack[temp - 1];// 一定注意是temp-1，就和cur-1是一个道理
            // 如果延续
            if (p[cur] == p[temp])
                fallBack[cur] = temp + 1;
            else
                fallBack[cur] = 0;

            cur++;
        }

        // 开始匹配
        // cur=pLength,则匹配完成
        cur = 0;
        base = 0;
        while (base + cur < sLength) {
            // 利用循环做长度判断
            if (cur == pLength)
                return base;
            else if (p[cur] == s[base + cur]) {
                cur++;

            } else if (cur == 0) {
                // 头不相等，直接继续即可
                base++;
            } else {
                // cur指向的位置导致了不相等，把base滑动一些位置并修改cur的值，但同时保证cur指向字符之前的字符依旧相等
                // fallBack比实际保证相等的index大1，所以base前进的值比实际小1
                base += cur - fallBack[cur - 1];
                cur = fallBack[cur - 1];
                while (cur > 0 && s[base + cur] != p[cur]) {
                    base += cur - fallBack[cur - 1];
                    cur = fallBack[cur - 1];
                }
                // 后面判断字符相等的事情的交给下个循环
            }

        }
        // 出来是两个原因，匹配到或者没匹配到
        // 这个每个人代码都不同，我自己是把长度判读放到了循环里
        return -1;
    }
}

====

class Solution {

  // let's try kmp algorithm

  public int strStr(String ss, String pp) {
      if(pp.isEmpty()) return 0;
      if(ss.isEmpty()) return -1;
      char[] s = ss.toCharArray(), p = pp.toCharArray();

      // failTo[i] means if match at i fails, try to match the index at failTo[i]

      int[] failTo = new int[p.length];

      int i = 1, j = 0, len = 0; // length of the previous longest prefix suffix
      while(i < p.length){
          if(p[i] == p[len]){
              len++;
              failTo[i] = len;
              i++;
          }else if(len != 0){
              // the index need to match is the lenght of prefix suffix
              len = failTo[len - 1];
          }else{
              failTo[i] = 0;
              i++;
          }
      }

      i = 0; // i for s, j for p
      int res = -1;
      while(i < s.length && res == -1){
          if(s[i] == p[j]){
              i++;
              j++;
              if(j == p.length) {res = i - j;}
          }else if(j != 0){
              j = failTo[j-1];
          }else {
              i++;
          }
      }
      // deal with the case that happens to match at end of string
      // if(j == p.length && res == -1) res = i - j;
      return res;
  }
}
====

class Solution {
  // 1. built-in function indexOf
  // 2. KMP
  public int strStr(String _s1, String _s2) {
    char[] s1 = _s1.toCharArray(), s2 = _s2.toCharArray();
    int n1 = s1.length, n2 = s2.length;

    // length of matching string.
    int[] fallBack = new int[n2];
    int idx = 1;
    while (idx < n2) {
      int tmp = fallBack[idx - 1];

      while (tmp > 0 && s2[idx] != s2[tmp])
        tmp = fallBack[tmp - 1];
      if (s2[idx] == s2[tmp]) tmp++;

      fallBack[idx] = tmp;
      idx++;
    }
    System.out.println(Arrays.toString(fallBack));

    int p1 = 0, p2 = 0;
    while (p1 < n1) {
      // System.out.println(s1[p1] + " " + s2[p2]);
      if (s1[p1] == s2[p2]) {
        p1++;
        p2++;
      } else {
        if (p2 != 0) p2 = fallBack[p2 - 1];
        else p1++;
      }
      // System.out.println(p1 + " " + p2);

      if (p2 == n2) return p1 - n2;
    }

    return -1;
  }
}
