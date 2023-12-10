/*
 * @lc app=leetcode id=24 lang=java
 *
 * [24] Swap Nodes in Pairs
 */

// @lc code=start
/**
 * Definition for singly-linked list. public class ListNode { int val; ListNode
 * next; ListNode(int x) { val = x; } }
 */
// class ListNode {
// int val;
// ListNode next;

// ListNode(int x) {
// val = x;
// }
// }

class Solution {
    public ListNode swapPairs(ListNode head) {

        // no swap needed
        if (head == null || head.next == null)
            return head;

        // deal with first two
        ListNode pre = head;
        head = head.next;
        pre.next = head.next;
        head.next = pre;

        // loop to deal with rest
        pre = head.next;
        while (pre.next != null) {
            ListNode left = pre.next;
            if (left.next == null)
                return head;

            ListNode right = left.next;
            pre.next = right;
            left.next = right.next;
            right.next = left;

            pre = left;
        }

        return head;
    }
}
// @lc code=end
