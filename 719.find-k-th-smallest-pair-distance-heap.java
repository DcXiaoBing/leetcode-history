import java.util.*;

/*
 * @lc app=leetcode id=719 lang=java
 *
 * [719] Find K-th Smallest Pair Distance
 */

// @lc code=start
class Solution {
    // quick select. Random pivot, median of medians. MLE
    // heap

    // we can sort then keep first k? No, big numbers can produce small dis
    // preprocess, only keep unique one

    // sort
    // same number produce 0s. count 0s
    // get differ, then combine them

    public int smallestDistancePair(int[] nums, int k) {
        HashMap<Integer, Integer> fre = new HashMap<>();
        for(int n : nums) fre.put(n, fre.getOrDefault(n, 0) + 1);

        HashMap<Integer, Integer> differs = new HashMap<>();

        for(Map.Entry<Integer, Integer> l : fre.entrySet()) for(Map.Entry<Integer, Integer> r : fre.entrySet()) {
            // System.out.println(l.getKey() + " " + r.getKey());
            if(r.getKey() == l.getKey()) {
                int cnt = r.getValue() * (r.getValue() - 1) / 2;
                differs.put(0, differs.getOrDefault(0, 0) + cnt);
            }else{
                int dis = Math.abs(r.getKey() - l.getKey());
                int cnt = r.getValue() * l.getValue();
                differs.put(dis, differs.getOrDefault(dis, 0) + cnt);
            }
        }
        
        PriorityQueue<Integer> heap = new PriorityQueue<>(Comparator.reverseOrder());
        int total = 0;
        for(Map.Entry<Integer, Integer> e : differs.entrySet()) {
            int cnt = e.getValue(); // processed twice
            if(e.getKey() != 0) cnt /= 2;
            
            heap.add(e.getKey());
            total += cnt;

            while(true) {
                cnt = differs.get(heap.peek());
                if(heap.peek() != 0) cnt /= 2;

                if(total - cnt >= k) {
                    total -= cnt;
                    heap.poll();
                }else{
                    break;
                }
            }
        }
        return heap.peek(); // unreachable code
    }
}
// @lc code=end

