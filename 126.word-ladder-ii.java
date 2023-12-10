/*
 * @lc app=leetcode id=126 lang=java
 *
 * [126] Word Ladder II
 */

// @lc code=start
class Solution {

    // 1. use char[] to try and build a graph
    // 2. use * to build graph

    // while both way leads to TLE
    // because search space is just too big
    // use Bidirectional bfs reduce the search space

    // HashMap<String, List<String>> dp;
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        HashSet<String> wordDict = new HashSet<>(wordList);

        // endWord not in path
        if(!wordDict.contains(endWord)) return new LinkedList<>();

        // pre-process data dfs
        HashMap<String, List<String>> graph = buildGraph(beginWord, endWord, wordDict);
        // System.out.println(graph.keySet());
        List<List<String>> res = new LinkedList<>();
        List<String> curL = new LinkedList<>();
        curL.add(beginWord);
        dfs(res, graph, curL, beginWord, new HashSet<>(), endWord);

        return res;
    }

    // can introduce dp
    void dfs(List<List<String>> res, HashMap<String, List<String>> graph , List<String> curL, String curWord, HashSet<String> seen, String endWord){

        // base case
        if(curWord.equals(endWord)){
            res.add(new LinkedList<>(curL));
            return ;
        }
        if(seen.contains(curWord)) return ;
        seen.add(curWord);

        if(!graph.containsKey(curWord)) return ;
        List<String> neighbours = graph.get(curWord);
        // System.out.println(curWord);
        // System.out.println(intermidate);
        for(String neighbour : neighbours){
            curL.add(neighbour);
            dfs(res, graph, curL, neighbour, seen, endWord);
            curL.remove(curL.size() - 1);
        }
        seen.remove(curWord);
    }

    // bidriectional bfs build graph
    // only record nodes can possiblly one the path
    HashMap<String, List<String>> buildGraph(String beginWord, String endWord, HashSet<String> wordDict){
        HashMap<String, List<String>> graph = new HashMap<>();
        HashSet<String> start = new HashSet<>(), end = new HashSet<>();
        start.add(beginWord);
        end.add(endWord);

        HashSet<String> seen = new HashSet<>();
        boolean found = false, backward = false;

        while(!start.isEmpty() && !found){
            HashSet<String> next = new HashSet<>();

            // always search from smaller side
            if(start.size() > end.size()){
                HashSet<String> temp = start;
                start = end;
                end = temp;

                backward = !backward;
            }

            for(String curS : start){

                seen.add(curS);

                for(String neighbour : getNeighbour(curS, wordDict)){
                    // System.out.println(getNeighbour(curS, wordDict));
                    // visited or is already in current set
                    if(seen.contains(neighbour) || start.contains(neighbour)) continue;
                    if(end.contains(neighbour)) {found = true;} // do not break, add all possible node in this layer

                    // add this pair to graph
                    String parent = backward ? neighbour : curS;
                    String child = backward ? curS : neighbour;

                    List<String> curL = graph.get(parent);
                    if(curL == null) {curL = new LinkedList<>(); graph.put(parent, curL);}
                    curL.add(child);

                    next.add(neighbour);
                }
            }
            start = next;
        }
        return graph;
    }

    List<String> getNeighbour(String s, HashSet<String> wordDict){
        List<String> res = new LinkedList<>();
        char[] cur = s.toCharArray();
        for(int i = 0; i < cur.length; i++){
            char temp = cur[i];
            for(char j = 'a'; j <= 'z'; j++){
                if(temp == j) continue;

                cur[i] = j;
                String tempS = new String(cur);
                if(wordDict.contains(tempS)) res.add(tempS);
            }
            cur[i] = temp;
        }

        return res;
    }
}
// @lc code=end

