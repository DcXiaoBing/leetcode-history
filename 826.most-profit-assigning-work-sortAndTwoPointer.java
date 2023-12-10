/*
 * @lc app=leetcode id=826 lang=java
 *
 * [826] Most Profit Assigning Work
 */

// @lc code=start
class Solution {
    
    // two pointer
    // sort according to difficulty
    // then the maxium profit we met is the highest for current worker
    
    // can also dp
    // dp[i] means the highest profit for diffculty at most i
    public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
        int jobs[][] = new int[profit.length][2];
        for(int i = 0; i < profit.length; i++){
            jobs[i][0] = difficulty[i];
            jobs[i][1] = profit[i];
        }
        Arrays.sort(jobs, new Comparator<int[]>(){
           public int compare(int[] a, int[] b){return a[0] - b[0];} 
        });
        Arrays.sort(worker);
        int curJob = 0, max = 0, res = 0;
        for(int i = 0; i < worker.length; i++){
            while(curJob < jobs.length && jobs[curJob][0] <= worker[i]){
                max = Math.max(max, jobs[curJob][1]);
                curJob++;
            }
            
            res += max;
        }
        
        return res;
    }
}
// @lc code=end

