class A {
  public List<Integer> strStr(String ss, String pp) {
    char[] s = ss.toCharArray(), p = pp.toCharArray();

    // failTo[i] means if match at i fails, try to match the index at failTo[i]
    int[] failTo = new int[p.length];

    // char at len is left, i is right
    // match 0 ~ len to 0~i
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
    List<Integer> res = new ArrayList<>();
    while(i < s.length){
      if(s[i] == p[j]){
        i++;
        j++;
      }
      if(j == p.length) {
        res.add(i - j);
        j = failTo[j - 1];
      } else if (i < s.length && s[i] != p[j]) {
        if (j != 0) j = failTo[j - 1];
        else i++;
      }
    }

    return res;
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
