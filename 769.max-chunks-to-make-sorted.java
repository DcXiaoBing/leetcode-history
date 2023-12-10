import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/*
 * @lc app=leetcode id=769 lang=java
 *
 * [769] Max Chunks To Make Sorted
 */

// @lc code=start
class Solution {
    // split into chunks, sort each chunk respectively, then array is sorted
    // want the max count of chunks
    
    // a number should in a chunk that contains its position
    // binary search? 1~n

    // each number has a offset which means the distance to its target postion. They should be in a same chunk.
    // So, we have n intervals sorted in start time
    // merge interval that overlaps

    // another greedy algorithm is that
    // when max == i, this means those numbers can fit into one interval
    // so, we only need to count how many times max == i
    // eg. 0..1 2..4 5..end means at 1, max == 1, at 4, max == 4
    public int maxChunksToSorted(int[] arr) {
        int res = 0, max = -1;
        for(int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
            if(max == i) res++;
        }
        return res;
    }
}
// @lc code=end

