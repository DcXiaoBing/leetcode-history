import java.util.*;

/*
 * @lc app=leetcode id=1300 lang=java
 *
 * [1300] Sum of Mutated Array Closest to Target
 */

// @lc code=start
class Solution {
    // ! not necessarilly in arr
    // binary search? check function? differ positve or negative.
    public int findBestValue(int[] arr, int target) {
        HashMap<Integer, Integer> fre = new HashMap<>();
        TreeSet<Integer> keys = new TreeSet<>(Comparator.reverseOrder());
        TreeMap<Integer, Integer> totalFre = new TreeMap<>();

        for(int n : arr) fre.put(n, fre.getOrDefault(n, 0) + 1);
        keys.addAll(fre.keySet());

        int cnt = 0;
        for(Integer n : keys) totalFre.put(n, cnt + fre.get(n));
        
        int l = 0, r = keys.first(), mid, res = Integer.MAX_VALUE, resDiff = Integer.MAX_VALUE;
        while(l < r) {
            mid = l + (r - l) / 2;

            int sum = 0;
            cnt = 0;
            for(Integer n : keys) {
                if(n <= mid) sum += n * fre.get(n);
                else cnt += fre.get(n);
            }

            int differ = mid * cnt + sum - target;
            if(Math.abs(differ) < resDiff || Math.abs(differ) == resDiff && mid < res) {
                res = mid;
                resDiff = Math.abs(differ);
            }
            // System.out.println(l + " " + mid + " " + r + " " + differ + " " + res + " " + resDiff);
            if(differ < 0) l = mid + 1;
            else r = mid;
        }

        int sum = 0;
        cnt = 0;
        for(Integer n : keys) {
            if(n <= l) sum += n * fre.get(n);
            else cnt += fre.get(n);
        }
        int differ = l * cnt + sum - target;
        if(Math.abs(differ) < resDiff || Math.abs(differ) == resDiff && l < res) {
                res = l;
                resDiff = Math.abs(differ);
        
        }
        // System.out.println(l + " " + differ);
        return res;
    }
}
// @lc code=end

