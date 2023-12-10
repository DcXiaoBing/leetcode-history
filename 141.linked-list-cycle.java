/*
 * @lc app=leetcode id=141 lang=java
 *
 * [141] Linked List Cycle
 */

// @lc code=start
/**
 * Definition for singly-linked list. class ListNode { int val; ListNode next;
 * ListNode(int x) { val = x; next = null; } }
 */

// class ListNode {

// int val;
// ListNode next;

// ListNode(int x) {
// val = x;
// next = null;
// }
// }

public class Solution {

    // Floyid's tortoise and hare
    public boolean hasCycle(ListNode head) {

        // deal with corner input
        if (head == null || head.next == null)
            return false;
        ListNode fast = head, slow = head;
        do {
            slow = slow.next;
            fast = fast.next;
            if (fast == null) {
                return false;
            } else {
                fast = fast.next;
            }
        } while (fast != null && fast != slow);

        if (fast == slow)
            return true;
        else
            return false;

    }
}
// @lc code=end
