import java.util.*;

/*
 * @lc app=leetcode id=218 lang=java
 *
 * [218] The Skyline Problem
 */

// @lc code=start
class Solution {
    public List<List<Integer>> getSkyline(int[][] buildings) {
        
        return helper(buildings, 0, buildings.length - 1);
    }

    private List<List<Integer>> helper(int[][] buildings, int l, int r) {
        List<List<Integer>> res = new ArrayList<>();

        if(l > r) return res;
        if(l == r) {
            List<Integer> temp = new ArrayList<>();
            temp.add(buildings[l][0]);
            temp.add(buildings[l][2]);
            res.add(temp);

            temp = new ArrayList<>();
            temp.add(buildings[l][1]);
            temp.add(0);
            res.add(temp);

            return res;
        }

        // split
        int mid = l + (r - l) / 2;
        List<List<Integer>> lRes = helper(buildings, l, mid);
        List<List<Integer>> rRes = helper(buildings, mid + 1, r);

        // combine
        int preHeight = 0, maxHeight = 0, lH = 0, rH = 0, lP = 0, rP = 0;
        while(lP < lRes.size() && rP < rRes.size()) {
            List<Integer> cur;
            if(lRes.get(lP).get(0) < rRes.get(rP).get(0)) {
                cur = lRes.get(lP++);
                lH = cur.get(1);
            } else if(lRes.get(lP).get(0) > rRes.get(rP).get(0)){
                cur = rRes.get(rP++);
                rH = cur.get(1);
            } else { // poll two point at once
                cur = lRes.get(lP++);
                lH = cur.get(1);
                cur = rRes.get(rP++);
                rH = cur.get(1);
            }

            maxHeight = Math.max(lH, rH);
            if(maxHeight != preHeight) {
                List<Integer> temp = new ArrayList<>();
                temp.add(cur.get(0));
                temp.add(maxHeight);
                res.add(temp);

                preHeight = maxHeight;
            }
        }

        // handle rest
        while(lP < lRes.size()) {
            List<Integer> cur = lRes.get(lP++);
            if(preHeight != cur.get(1)) {
                preHeight = cur.get(1);
                res.add(cur);    
            }
        }
        while(rP < rRes.size()) {
            List<Integer> cur = rRes.get(rP++);
            if(preHeight != cur.get(1)) {
                preHeight = cur.get(1);
                res.add(cur);    
            }
        }

        // System.out.println(lRes);
        // System.out.println(rRes);
        // System.out.println(res);
        // System.out.println("============");
        return res;
    }
}
// @lc code=end

