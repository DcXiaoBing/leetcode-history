/*
 * @lc app=leetcode id=537 lang=java
 *
 * [537] Complex Number Multiplication
 */

// @lc code=start
class Solution {
    // split real and virtual
    // calculate
    public String complexNumberMultiply(String a, String b) {
        int[] cn1 = seperate(a), cn2 = seperate(b);

        return "" + (cn1[0]*cn2[0] - cn1[1]*cn2[1]) + "+" + (cn1[0]*cn2[1] + cn1[1] * cn2[0])+"i";
    }
    private int[] seperate(String s){
        String[] parts = s.split("[+]");
        parts[1] = parts[1].substring(0, parts[1].length() - 1); // rule out i

        return new int[]{Integer.valueOf(parts[0]), Integer.valueOf(parts[1])};
    }
}
// @lc code=end

