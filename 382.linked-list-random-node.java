import java.util.Random;

/*
 * @lc app=leetcode id=382 lang=java
 *
 * [382] Linked List Random Node
 */

// @lc code=start
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    // CS565 data mining 讲过这个算法
    // Random choose k elements from a data stream
    
    // Main idea: use a buffer with length k
    // init buffer with first k elements
    // when i-th element comes in, get a random number from 0...i
    // if rand < k, replace the number in the window

    // can prove the correctness by induction by computing the chance of an element kept in window

    /** @param head The linked list's head.
        Note that the head is guaranteed to be not null, so it contains at least one node. */

    ListNode head;
    Random ran = new Random();
    public Solution(ListNode head) {
        this.head = head;
    }
    
    /** Returns a random node's value. */
    public int getRandom() {
        ListNode cur = head;
        int target = 0;

        int p = 1;
        while(cur != null) {
            if(ran.nextInt(p++) == 0) target = cur.val;
            cur = cur.next;
        }

        return target;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(head);
 * int param_1 = obj.getRandom();
 */
// @lc code=end

