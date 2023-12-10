import java.util.*;
/*
 * @lc app=leetcode id=853 lang=java
 *
 * [853] Car Fleet
 */

// @lc code=start
class Solution {
    // target's left and target's right
    
    // compute time needed to arrive
    // seperate by slower cars (longer time)

    // left -> right (distance to target)
    // find decreasing sequence
    // decreasing monoqueue
    // actually we can discard previous time. Just use a int to record
    public int carFleet(int target, int[] position, int[] speed) {

        int[][] cars = new int[position.length][2];
        for(int i = 0; i < position.length; i++) {
            cars[i][0] = position[i]; 
            cars[i][1] = speed[i];
        }
        Arrays.sort(cars, (a, b) -> a[0] - b[0]);
        
        Deque<Double> l = new ArrayDeque<>(), r = new ArrayDeque<>();
        for(int i = 0; i < position.length; i++) 
            if(cars[i][0] < target) {
                double time = (double)(target - cars[i][0]) / cars[i][1];
                while(!l.isEmpty() && l.getLast() <= time) l.removeLast();

                l.addLast(time);
            }

        for(int i = position.length - 1; i >= 0; i--)
            if(cars[i][0] >= target) {
                double time = (double)(cars[i][0] - target) / cars[i][1];
                while(!r.isEmpty() && r.getLast() <= time) r.removeLast();

                r.addLast(time);
            }
        
        // System.out.println(l);
        // System.out.println(r);
        return l.size() + r.size();
    }
}
// @lc code=end

