import java.util.*;

/*
 * @lc app=leetcode id=451 lang=java
 *
 * [451] Sort Characters By Frequency
 */

// @lc code=start
class Solution {

    // design a heap for each letter
    // comparator use the fre in hashmap
    HashMap<Character, Integer> fre;

    public String frequencySort(String s) {

        fre = new HashMap<>();

        // remember in descending order
        // PriorityQueue<Character> heap = new PriorityQueue<>((a, b) -> fre.get(b) -
        // fre.get(a));
        PriorityQueue<Character> heap = new PriorityQueue<>(new FreCom());

        // get fre
        for (int i = 0, len = s.length(); i < len; i++) {
            Character cur = s.charAt(i);
            fre.put(cur, fre.getOrDefault(cur, 0) + 1);
        }

        // add letters to heap
        for (Character c : fre.keySet()) {
            heap.add(c);
        }

        StringBuilder res = new StringBuilder();
        while (!heap.isEmpty()) {
            char c = heap.poll();
            char temp[] = new char[fre.get(c)];
            Arrays.fill(temp, c);
            res.append(temp);
        }

        return res.toString();
    }

    class FreCom implements Comparator<Character> {
        public int compare(Character a, Character b) {
            return fre.get(b) - fre.get(a);
        }
    }
}
// @lc code=end
