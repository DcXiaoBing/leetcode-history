/*
 * @lc app=leetcode id=134 lang=java
 *
 * [134] Gas Station
 */

// @lc code=start
class Solution {

    // brute force, test all start point. o(n^2)
    // stop when rest < cost
    
    // if total_cost < total_gas, then it is possible
    // largest consecutive sum, this can give use maximum gas avaliable
    public int canCompleteCircuit(int[] gas, int[] cost) {
        // if(gas.length == 1) return 0; // one station, start at itself. but need to check gas

        int differ[] = new int[gas.length], total = 0;
        for(int i = 0; i < gas.length; i++){differ[i] = gas[i] - cost[i]; total += differ[i];}

        if(total < 0) return -1; // not enough total gas

        int sum = differ[0], idx = 0;
        for(int i = 1; i < differ.length; i++){
            if(sum < 0){ // cannot provide positive impact for following stations
                sum = differ[i];
                idx = i;
            }else{
                sum += differ[i];
            }
        }

        return idx;
    }
}
// @lc code=end

