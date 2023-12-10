/*
 * @lc app=leetcode id=78 lang=java
 *
 * [78] Subsets
 */

// @lc code=start
import java.util.*;

class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();

        // each time get all subset with size i
        for (int i = 0; i <= nums.length; i++) {
            helper(nums, 0, i, new LinkedList<>(), res);
        }

        return res;
    }

    void helper(int[] nums, int curIndex, int restCount, List<Integer> curList, List<List<Integer>> res) {
        if (restCount == 0) {
            res.add(new LinkedList<>(curList)); // deep copy
            return;
        }

        for (int i = curIndex; i < nums.length - restCount + 1; i++) {
            curList.add(nums[i]);
            helper(nums, i + 1, restCount - 1, curList, res);
            curList.remove(curList.size() - 1);
        }
    }
}
// @lc code=end
