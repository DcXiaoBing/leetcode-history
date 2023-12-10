/*
 * @lc app=leetcode id=189 lang=java
 *
 * [189] Rotate Array
 */

// @lc code=start
class Solution {
    public void rotate(int[] nums, int k) {
        // directly move the element to its final position

        // nothing need to be done

        k = k % nums.length;
        if (k == 0)
            return;

        int store = nums[0];
        int cur = 0, counter = 0, start = 0;

        // use cur's value to judge is not precise
        // because there could be loop

        while (counter < nums.length) {
            // need a outer loop because sometime it loops
            // eg. nums.length = 6, k = 2
            // start at 0, first loop ends at 0

            do {
                // k is moving right
                // nums.length - k is moving left

                int targetIndex = (k + cur) % nums.length;

                int temp = nums[targetIndex];
                nums[targetIndex] = store;
                store = temp;

                cur = targetIndex;
                counter++;
                // System.out.println(Arrays.toString(nums));

            } while (cur != start);
            start++;
            cur = start;
            store = nums[start];
        }
        return;
    }
}
// @lc code=end
