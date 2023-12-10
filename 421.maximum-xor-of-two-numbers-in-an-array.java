/*
 * @lc app=leetcode id=421 lang=java
 *
 * [421] Maximum XOR of Two Numbers in an Array
 */

// @lc code=start
class Solution {
    
    // main idea is that: try to keep every bit to 1
    // for each number, check its pair with all other numbers
    // Use trie to minimize the comparation

    public int findMaximumXOR(int[] nums) {
        
        Trie root = new Trie();
        int res = 0;

        // 00001
        // 10001
        // if a path is total new, insert and search goes same path (So must insert first)
        // if a path prefix is common to, then node must exist
        // if a path starts a new one, that means that path also exist! (Started by a previous number)
        // 2选1的路径
        for(int num : nums) {
            // indicate what prefix we can get
            int curMask = 0; 

            Trie search = root, insert = root;

            // left shift 30 time will reach 31st bit. 32nd is flag bit
            for(int i = 30; i >= 0; i--) {
                int bit = (num >> i) & 1;

                // insert
                if(insert.children[bit] == null) insert.children[bit] = new Trie();
                insert = insert.children[bit];

                // search number
                // this number will now compare with all numbers shown before
                // futhure number will also compare with this number

                if(search.children[1 - bit] == null) { // toggled bit not exist
                    curMask <<= 1;
                    search = search.children[bit];
                } else {
                    curMask = (curMask << 1) | 1; // this added bit of prefix can be 1
                    search = search.children[1 - bit];
                }

                res = Math.max(res, curMask);
            }
        }

        return res;
    }
}

class Trie{
    // Integer number; // not needed
    Trie[] children;

    Trie() {
        children = new Trie[2];
    }
}
// @lc code=end

