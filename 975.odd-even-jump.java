/*
 * @lc app=leetcode id=975 lang=java
 *
 * [975] Odd Even Jump
 */
import java.util.*;
// @lc code=start
class Solution {
    // can only jump to right (i<j)
    
    // use dp? maintain increasing stack or decreasing stack
    // then we can get the next position quickly
    // ! stack can only give the the closest one bigger than you, not the optimal one
    
    // TreeSet, ceiling, flooring -> log(n) per entry
    // this seems the most likely one

    // ! use treemap, and update the index to minimum one
    // ! can directly compute result in for loop
    
    // dp: if you jump to a good index, then the start index is good
    
    public int oddEvenJumps(int[] A) {
        if(A.length == 1) return 1;

        // rightMax[i] the idx of minimum larger entry in i+1~A.length - 1
        int res = 1;
        boolean odd[] = new boolean[A.length], even[] = new boolean[A.length];
        TreeMap<Integer, Integer> map = new TreeMap<>();
        
        odd[A.length - 1] = true;
        even[A.length - 1] = true;
        map.put(A[A.length - 1], A.length - 1);
        
        for(int i = A.length - 2; i >= 0; i--){
            // if(map.containsKey(A[i])){
            //     // perform jump directly, val equals is the best solution
            //     odd[i] = even[map.get(A[i])];
            //     even[i] = odd[map.get(A[i])];
            // }else{
            //     Integer floorKey = map.floorKey(A[i]);
            //     Integer ceilingKey = map.ceilingKey(A[i]);

            //     if(floorKey != null)
            //         even[i] = odd[map.get(floorKey)];
            //     if(ceilingKey != null)
            //         odd[i] = even[map.get(ceilingKey)];
            // }

            Map.Entry<Integer, Integer> floor = map.floorEntry(A[i]);
            Map.Entry<Integer, Integer> ceiling = map.ceilingEntry(A[i]);
            if(floor != null){
                even[i] = odd[floor.getValue()];
            }
            if(ceiling != null){
                odd[i] = even[ceiling.getValue()];
            }
            map.put(A[i], i); // update
            // 1st jump
            if(odd[i]){
                // System.out.println(i + " " + map.ceilingKey(A[i]));
                res++;
            }
        }
        return res;
    }
}
// @lc code=end

