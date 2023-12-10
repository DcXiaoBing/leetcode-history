import java.util.*;

/*
 * @lc app=leetcode id=1282 lang=java
 *
 * [1282] Group the People Given the Group Size They Belong To
 */

// @lc code=start
class Solution {
    // categorize by group size
    public List<List<Integer>> groupThePeople(int[] groupSizes) {
        List<List<Integer>> res = new ArrayList<>();
        HashMap<Integer, List<Integer>> sizeToPeople = new HashMap<>();

        for(int i = 0; i < groupSizes.length; i++) {
            int temp = i;
            if(groupSizes[i] == 1) {
                res.add(new ArrayList<>(){{add(temp);}});
                continue;
            }
            if(sizeToPeople.get(groupSizes[i]) == null) sizeToPeople.put(groupSizes[i], new ArrayList<>());
            sizeToPeople.get(groupSizes[i]).add(i);
        }

        for(Map.Entry<Integer, List<Integer>> e : sizeToPeople.entrySet()) {
            List<Integer> peopleList = e.getValue();
            List<Integer> cur = new ArrayList<>();
            for(Integer people : peopleList) {
                cur.add(people);
                if(cur.size() == e.getKey()) {
                    res.add(cur);
                    cur = new ArrayList<>();
                }
            }
        }

        return res;
    }
}
// @lc code=end

