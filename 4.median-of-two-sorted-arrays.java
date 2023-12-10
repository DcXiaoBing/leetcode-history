import java.util.Arrays;

/*
 * @lc app=leetcode id=4 lang=java
 *
 * [4] Median of Two Sorted Arrays
 */

// @lc code=start
class Solution {
    // 0~i-1 belongs to left, i~len-1 belongs to right
    // use binary search to get the proper partation
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if(nums1.length > nums2.length){
            int[] temp = nums1;
            nums1 = nums2;
            nums2 = temp;
        }
        // nums1[i-1] : nums1[i]
        // nums2[j-1] : nums2[j]
        // they are sorted, so nums1[i-1] always less than nums1[i]
        // so only consider relation between nums1[i-1] and nums2[j], nums1[i] and nums2[j-1]
        int iMin = 0, iMax = nums1.length, halfLen = (nums1.length + nums2.length + 1) / 2;
        int i, j;

        while(iMin <= iMax){
            i = iMin + (iMax - iMin) / 2;
            j = halfLen - i;
            if(i < iMax && nums2[j-1] > nums1[i]){ // i is too small. less than imax, can increase
                iMin = i + 1; 
            }else if(i > iMin && nums1[i-1] > nums2[j]){ // i is too big. bigger than imin, can decrease
                iMax = i - 1;
            }else{ // perfect point, can get answer now
                
                int lMax = 0;
                // check between two number.
                // 0 means i-1 or j-1 do not exist
                if(i == 0) lMax = nums2[j-1];
                else if(j == 0) lMax = nums1[i-1];
                else lMax = Math.max(nums1[i-1], nums2[j-1]);

                if((nums1.length + nums2.length) % 2 == 1) return lMax;

                int rMin = 0;
                if(i == nums1.length) rMin = nums2[j];
                else if(j == nums2.length) rMin = nums1[i];
                else rMin = Math.min(nums2[j], nums1[i]);

                return (lMax + rMin) / 2d;
            }
        }
        return 0.0;
    }
}
// @lc code=end

