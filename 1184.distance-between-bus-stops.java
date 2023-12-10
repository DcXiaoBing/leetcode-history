/*
 * @lc app=leetcode id=1184 lang=java
 *
 * [1184] Distance Between Bus Stops
 */

// @lc code=start
class Solution {
    // a circle, so only two path
    // start +1 and -1 are two path

    // found a elegant way
    public int distanceBetweenBusStops(int[] distance, int start, int destination) {
        // int dis1 = 0, dis2 = 0, pos = start;

        // while(pos != destination){
        //     dis1 += distance[pos];
        //     pos++;
        //     if(pos == distance.length) pos = 0;
        // }

        // pos = start;
        // while(pos != destination){
        //     pos--; // need minus to get accurate value
        //     if(pos == -1) pos = distance.length - 1;
            
        //     dis2 += distance[pos];
        // }

        // return Math.min(dis1, dis2);

        if(start > destination) {
            int temp = start;
            start = destination;
            destination = temp;
        }

        int sum1 = 0, sum2 = 0;
        for(int i = 0; i < distance.length; i++){
            if(start <= i && i < destination) sum1 += distance[i];
            else sum2 += distance[i];
        }

        return Math.min(sum1, sum2);
    }
}
// @lc code=end

