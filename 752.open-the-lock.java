/*
 * @lc app=leetcode id=752 lang=java
 *
 * [752] Open the Lock
 */
import java.util.*;
// @lc code=start
class Solution {

    // need to get the minimun count, so we can bfs
    // one layer means all the status that we can reach with same turns
    public int openLock(String[] deadends, String target) {
        boolean deadSet[] = new boolean[10000];
        // for(String deadend : deadends) deadSet.add(Integer.valueOf(deadend));
        // for(int i = 0; i < deadends.length; i++) deadSet.add(Integer.valueOf(deadends[i]));
        for(int i = 0; i < deadends.length; i++) deadSet[Integer.valueOf(deadends[i])] = true;
        
        int t = Integer.valueOf(target), cur = 0;
        int counter = 0;

        // HashSet<Integer> seen = new HashSet<>();
        boolean[] seen = new boolean[10000];
        Deque<Integer> queue = new LinkedList<>(), next;
        queue.add(cur);

        // add 1000, 100, 10, 1
        while(!queue.isEmpty()){
            next = new LinkedList<>();
            while(!queue.isEmpty()){
                cur = queue.poll();
                if(seen[cur]) continue;
                seen[cur] = true;
                if(deadSet[cur]) continue;
                if(cur == t) return counter;

                // change each bit
                int base = 1, temp = cur;
                for(int i = 0; i < 4; i++){
                    int cur_digit = temp % 10;
                    if(cur_digit < 9) next.add(cur + base);
                    if(cur_digit > 0) next.add(cur - base);

                    if(cur_digit == 9) next.add(cur - 9 * base);
                    if(cur_digit == 0) next.add(cur + 9 * base);

                    base *= 10;
                    temp /= 10;

                    // System.out.println(cur);
                }   
            }
            queue = next;
            counter++;
        }

        // failed to find res
        return -1;
    }
}
// @lc code=end

