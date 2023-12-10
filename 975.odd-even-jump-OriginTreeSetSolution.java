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
    
    // dp: if you jump to a good index, then the start index is good
    
    int[] rightMax, rightMin;
    Boolean dp[][];
    public int oddEvenJumps(int[] A) {
        if(A.length == 1) return 1;

        // rightMax[i] the idx of minimum larger entry in i+1~A.length - 1
        rightMax = new int[A.length]; 
        rightMin = new int[A.length];
        dp = new Boolean[A.length][2];
        
        // gives min idx when flooring
        TreeSet<Integer> set = new TreeSet<Integer>(new Comparator<Integer>(){
            public int compare(Integer a, Integer b){
                if(A[a] == A[b]) return b - a;
                else return A[a] - A[b];
            }
        });

        set.add(A.length - 1);
        for(int i = A.length - 2; i >= 0; i--){
            Integer floor = set.floor(i);
            
            if(floor == null) rightMin[i] = -1;
            else rightMin[i] = floor;
            
            set.add(i);
        }

        // give min index when ceiling
        set = new TreeSet<>(new Comparator<Integer>() {
            public int compare(Integer a, Integer b){
                if(A[a] == A[b]) return a - b;
                else return A[a] - A[b];
            }
        });
        set.add(A.length - 1);
        for(int i = A.length - 2; i >= 0; i--){
            Integer ceiling = set.ceiling(i);
            
            if(ceiling == null) rightMax[i] = -1;
            else rightMax[i] = ceiling;
            
            set.add(i);
        }

        // System.out.println(Arrays.toString(rightMin));
        // System.out.println(Arrays.toString(rightMax));
        
        int res = 0;
        for(int i = 0; i < A.length; i++){
            if(dfs(i, 0)) res++; // here is 0-th jump
        }
        return res;
    }
    
    Boolean dfs(int curIdx, int counter){
        // base case
        if(curIdx == rightMax.length - 1){
            return true;
        }
        
        if(dp[curIdx][counter] != null) return dp[curIdx][counter];
        
        if(counter == 1 && rightMin[curIdx] != -1){
            dp[curIdx][counter] = dfs(rightMin[curIdx], 0);
        }else if(counter == 0 && rightMax[curIdx] != -1){
            dp[curIdx][counter] = dfs(rightMax[curIdx], 1);
        }else{
            dp[curIdx][counter] = false;
        }
        
        return dp[curIdx][counter];
    }
}
// @lc code=end

