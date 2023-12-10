import java.util.Arrays;
import java.util.Comparator;

/*
 * @lc app=leetcode id=179 lang=java
 *
 * [179] Largest Number
 */

// @lc code=start
class Solution {
    // sort by concate two string
    // proven by contradiction. if there is a pair is not in correct order
    // if a, b are not in correct order, means a + b > b + a and in correct answer we need to put b before a
    // But a + b > b + a, means that there are some digits are larger and will lead to bigger final result

    // induction. 2 nums, n-1 nums, n nums.
    // transitivity. adding a number to a position.
    // if it is smaller, then all numbers before it is larger than the number added.

    // or use the explaination in leetcode. suppose a,b are a pair next to each other in sorted string
    public String largestNumber(int[] nums) {
        String[] strings = new String[nums.length];
        for(int i = 0; i < nums.length; i++) strings[i] = String.valueOf(nums[i]);

        Arrays.sort(strings, new Comparator<String>(){
            public int compare(String a, String b) {
                String s1 = a + b;
                String s2 = b + a;
                return s2.compareTo(s1);
            }
        });

        if(strings[0].equals("0")) return "0";
        StringBuilder sb = new StringBuilder();
        for(String s : strings) sb.append(s);

        return sb.toString();
    }
}
// @lc code=end
