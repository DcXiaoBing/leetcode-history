/*
 * @lc app=leetcode id=148 lang=java
 *
 * [148] Sort List
 */
/**
 * 148.sort-list
 */
class ListNode {
    int val;
    ListNode next;
}
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
    // two solution: merge sort or quick sort
    // merge sort is more suitable for linkedlist

    // merge sort
    // do not use while(l != null || r != null)
    // when l != null, will judge l.val > r.val. At this point, r could be null
    public ListNode sortList(ListNode head) {
        if(head == null || head.next == null) return head;
        
        ListNode mid = getMid(head), temp = mid.next;
        // System.out.println(head.val + " " + mid.val);

        mid.next = null;
        ListNode l = sortList(head), r = sortList(temp);

        ListNode nhead = null, cur = null;
        while(l != null && r != null) {
            if(l.val > r.val) {
                if(nhead == null) {
                    cur = nhead = r;
                } else {
                    cur.next = r;
                    cur = cur.next;
                }
                r = r.next;
            } else {
                if(nhead == null) {
                    cur = nhead = l;
                } else {
                    cur.next = l;
                    cur = cur.next;
                }
                l = l.next;
            }
        }
        if(l != null) cur.next = l;
        if(r != null) cur.next = r;
        return nhead;
    }

    private ListNode getMid(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode fast = head, slow = head;
        while(fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
// @lc code=end

