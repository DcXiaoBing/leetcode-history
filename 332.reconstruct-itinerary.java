import java.util.*;

/*
 * @lc app=leetcode id=332 lang=java
 *
 * [332] Reconstruct Itinerary
 */

// @lc code=start
class Solution {
    // 1. backtrack + try-error

    // 2. Hierholzer's Algorithm
    // when adding a vetex, means that all its outgoing edge has been used up
    // otherwise there is no way to add it.(if delete, then some tickets cannot be used)
    HashMap<String, List<String>> graph;
    public List<String> findItinerary(List<List<String>> tickets) {
        graph = new HashMap<>();
        for(List<String> f : tickets) {
            List<String> l = graph.get(f.get(0));
            if(l == null) {
                l = new LinkedList<>();
                graph.put(f.get(0), l);
            }
            l.add(f.get(1));
        }

        for(Map.Entry<String, List<String>> e : graph.entrySet()) Collections.sort(e.getValue());
        // System.out.println(graph);
        
        // when stuck, print it, it will be the last node we met
        LinkedList<String> route = new LinkedList<>();
        backtrack(route, "JFK");
        return route;
    }
    
    private void backtrack(LinkedList<String> route, String cur) {
        
        if(graph.containsKey(cur)) {
            LinkedList<String> l = (LinkedList<String>)graph.get(cur);
            while(!l.isEmpty()) {
                String dest = l.removeFirst();
                backtrack(route, dest);
            }
        }

        // add after all child is finished
        // to achieve a reversed order, add to head
        route.addFirst(cur); 
    }
}
// @lc code=end

