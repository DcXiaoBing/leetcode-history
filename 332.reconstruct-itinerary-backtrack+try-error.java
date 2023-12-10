import java.util.*;

/*
 * @lc app=leetcode id=332 lang=java
 *
 * [332] Reconstruct Itinerary
 */

// @lc code=start
class Solution {
    // backtrack + try-error
    HashMap<String, List<String>> graph;
    HashMap<String, boolean[]> visited;
    public List<String> findItinerary(List<List<String>> tickets) {
        graph = new HashMap<>();
        visited = new HashMap<>();
        for(List<String> f : tickets) {
            List<String> l = graph.get(f.get(0));
            if(l == null) {
                l = new LinkedList<>();
                graph.put(f.get(0), l);
            }
            l.add(f.get(1));
        }

        for(Map.Entry<String, List<String>> e : graph.entrySet()) {
            Collections.sort(e.getValue());
            visited.put(e.getKey(), new boolean[e.getValue().size()]);
        }
        System.out.println(graph);
        LinkedList<String> route = new LinkedList<>();
        route.add("JFK");
        return backtrack(route, tickets.size(), "JFK");
    }
    
    private List<String> backtrack(LinkedList<String> route, int tickets, String cur) {
        // System.out.println(cur);
        // System.out.println(route);
        if(route.size() == tickets + 1) { // route city count is one larger than count of tickets
            return (List<String>)route.clone();
        }

        if(!graph.containsKey(cur)) return null; // get stuck at a city
        
        int i = 0;
        boolean v[] = visited.get(cur);
        List<String> dests = graph.get(cur);
        for(String dest : graph.get(cur)) {
            if(v[i]) {i++; continue;}

            v[i] = true;
            route.addLast(dest);
            List<String> l = backtrack(route, tickets, dest);
            route.removeLast();
            v[i] = false;

            if(l != null) return l;
            i++;
        }
        return null; // no successful route
    }
}
// @lc code=end

