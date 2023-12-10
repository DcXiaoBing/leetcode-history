/*
 * @lc app=leetcode id=148 lang=java
 *
 * [148] Sort List
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
    // two solution: merge sort or quick sort
    // merge sort is more suitable for linkedlist

    // list version quick sort
    Random ran = new Random();
    public ListNode sortList(ListNode head) {
        if(head == null || head.next == null) return head;
        
        int pivot = head.val; // use head as pivot node
        
        ListNode l = null, r = null, cur = head.next, curL = null, curR = null;
        while(cur != null) {
            if(cur.val < pivot) { // put to left part
                if(l == null) {
                    l = curL = cur;
                } else {
                    curL.next = cur;
                    curL = curL.next;
                }
            } else {
                if(r == null) {
                    r = curR = cur;
                } else {
                    curR.next = cur;
                    curR = curR.next;
                }
            }
            
            cur = cur.next;
        }
        
        head.next = null;
        
        if(l != null) {
            curL.next = null;
            curL = l = sortList(l);
        }
        if(r != null) {
            curR.next = null;
            curR = r = sortList(r);
        }

        while(curL != null && curL.next != null) curL = curL.next;
        while(curR != null && curR.next != null) curR = curR.next;
        
        if(l != null) curL.next = head;
        head.next = r;
        
        if(l != null) return l;
        return head;
    }
}
// @lc code=end

