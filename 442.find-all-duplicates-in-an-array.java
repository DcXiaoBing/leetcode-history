import java.util.List;

/*
 * @lc app=leetcode id=442 lang=java
 *
 * [442] Find All Duplicates in an Array
 */

// @lc code=start
class Solution {
    // info: 1 ~ n
    // there could be more than one duplicate numbers

    // 1. hashset
    // 2. sort
    // 3. set index
    // use negative to indicate the number has been found
    public List<Integer> findDuplicates(int[] nums) {

        List<Integer> res = new ArrayList<>(nums.length);
        for(int i = 0; i < nums.length; i++) {
            int cur = Math.abs(nums[i]);

            if(nums[cur - 1] < 0) res.add(cur);
            else nums[cur - 1] = -nums[cur - 1];
        }
        return res;
    }
}
// @lc code=end

