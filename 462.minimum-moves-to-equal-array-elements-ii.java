import java.util.*;

/*
 * @lc app=leetcode id=462 lang=java
 *
 * [462] Minimum Moves to Equal Array Elements II
 */

// @lc code=start
class Solution {
    // suppose the euqal number is x
    // so total is n*x
    // minimum moves means that |nx - sum| is min?
    // |x - n1| + |x - n2| + ... + |x - nn| is min
    // answer is between min and max
    // answer is median or a number we computed

    // less equals grater -> median (sort or quick select)
    // if less is more, minus will redeuce movements
    // if grater is more, increase will recude movements
    // if len is even, any number between two middle number is ok

    // https://leetcode.com/problems/minimum-moves-to-equal-array-elements-ii/solution/
    // better quick select by using median of medians

    Random ran = new Random();
    public int minMoves2(int[] nums) {
        int median = quickSelect(nums, nums.length / 2, 0, nums.length - 1), res = 0;
        for(int i = 0; i < nums.length; i++) res += Math.abs(nums[i] - median);
            
        // System.out.println(Arrays.toString(nums));
        // System.out.println(median);
        return res;
    }

    private int quickSelect(int[] nums, int k, int l, int r) {
        if(l == r) return nums[l]; // end of recurrsion

        // start partation(swap)
        int smallCount = partation(nums, l, r, l + ran.nextInt(r - l + 1));
        
        if(smallCount == k) return nums[k];
        else if(smallCount > k) { // k is in left part
            return quickSelect(nums, k, l, smallCount - 1);
        }else {
            return quickSelect(nums, k, smallCount + 1, r);
        }
    }

    private int partation(int[] nums, int l, int r, int pivotIdx) {
        int smallCount = l, pivot = nums[pivotIdx];
        swap(nums, pivotIdx, r);

        for(int i = l; i < r; i++){
            if(nums[i] < pivot) {
                swap(nums, smallCount, i);
                smallCount++; // the position of store next small (also the count)
            }
        }
        swap(nums, smallCount, r);
        // System.out.println(Arrays.toString(nums) + " " + l + " " + r + " " + pivotIdx);
        return smallCount;
    }

    private void swap(int[] nums, int a, int b){
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }
}
// @lc code=end

