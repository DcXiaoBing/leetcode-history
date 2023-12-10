/*
 * @lc app=leetcode id=973 lang=java
 *
 * [973] K Closest Points to Origin
 */
import java.util.*;
// @lc code=start
class Solution {

    // quick select
    // compute distance first and then quick select
    int[][] points;
    int[] square_distance;
    Random ran;
    public int[][] kClosest(int[][] points, int K) {
        // if(points.length == K)
            // return points;
        this.points = points;
        square_distance = new int[points.length];
        ran = new Random();

        for(int i = 0; i < points.length; i++) 
            square_distance[i] = points[i][0] * points[i][0] + points[i][1] * points[i][1];
        
        // K closest, K - 1 is before it
        quick_select(0, points.length - 1, K - 1);
        return Arrays.copyOf(points, K);
    }
    void quick_select(int left, int right, int K){
        // System.out.println(left + ", " + right);
        int pivot_index = ran.nextInt(right - left + 1) + left;
        int cur = partation(left, right, pivot_index);
        if(cur == K)
            return ;
        if(cur < K){
            quick_select(cur + 1, right, K);
        }else{
            quick_select(left, cur - 1, K);
        }
    }
    int partation(int left, int right, int pivot_index){
        int pivot = square_distance[pivot_index];
        
        // swap to right
        swap(pivot_index, right);

        int cur = left;
        for(int i = left; i < right; i++){
            if(square_distance[i] < pivot){
                swap(i, cur);
                cur++;
            }
        }

        // swap pivot to its position
        swap(cur, right);
        return cur;
    }
    void swap(int l, int r){
        int temp1 = square_distance[l];
        square_distance[l] = square_distance[r];
        square_distance[r] = temp1;

        int[] temp2 = points[l];
        points[l] = points[r];
        points[r] = temp2;
    }


}
// @lc code=end

