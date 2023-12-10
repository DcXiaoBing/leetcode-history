/*
 * @lc app=leetcode id=78 lang=java
 *
 * [78] Subsets
 */

// @lc code=start
import java.util.*;

class Solution {

    // recursive
    // add one number to set each time
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        res.add(new LinkedList<>());

        for (int n : nums) {
            List<List<Integer>> newSubset = new LinkedList<>();
            for (List<Integer> old : res) {
                List<Integer> temp = new LinkedList<>(old);
                temp.add(n);

                newSubset.add(temp);
            }

            for (List<Integer> l : newSubset) {
                res.add(l);
            }
        }

        return res;
    }
}
// @lc code=end
