import java.util.*;

/*
 * @lc app=leetcode id=719 lang=java
 *
 * [719] Find K-th Smallest Pair Distance
 */

// @lc code=start
class Solution {
    // quick select. Random pivot, median of medians. MLE

    // heap
    
    Random ran = new Random();
    public int smallestDistancePair(int[] nums, int k) {
        int dis[] = new int[nums.length * (nums.length - 1) / 2], p = 0;

        for(int i = 0; i < nums.length; i++) for(int j = i + 1; j < nums.length; j++) dis[p++] = Math.abs(nums[i] - nums[j]);

        return quickSelect(dis, k - 1, 0, dis.length - 1);   
    }
    private int quickSelect(int[] nums, int k, int l, int r) {
        if(l == r) return nums[l];

        int idx = partation(nums, l, r, l + ran.nextInt(r - l + 1));
        if(idx == k) return nums[idx];
        else if(idx > k) return quickSelect(nums, k, l, idx - 1);
        else return quickSelect(nums, k, idx + 1, r);
    }
    private int partation(int[] nums, int l, int r, int pIdx) {
        int p = nums[pIdx], smallCount = l;
        swap(nums, pIdx, r);

        for(int i = l; i < r; i++) if(nums[i] < p){
            swap(nums, smallCount++, i);
        }

        swap(nums, smallCount, r);
        return smallCount; // proper idx for pivot
    }
    private void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }
}
// @lc code=end

