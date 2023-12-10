/*
 * @lc app=leetcode id=25 lang=java
 *
 * [25] Reverse Nodes in k-Group
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
    // previous solution: find left boundary and right boundary
    // but need to handle head node

    public ListNode reverseKGroup(ListNode head, int k) {
        if(k == 1 || head == null) return head; // do nothing

        ListNode nHead = null, cur = head, preTail = null; // use this to handle head problem
        int cnt = 0;

        while(cur != null) {
            cnt = 0;
            while(cnt < k && cur != null) {
                cur = cur.next;
                cnt++;
            }

            if(cnt < k) break ;

            ListNode temp = reverse(head, k);
            if(nHead == null) nHead = temp;
            if(preTail != null) preTail.next = temp;
            
            // repair the link broken by reverse
            // head is first node in normal sequence. last node in reversed sequence.

            head.next = cur; // list to right
            preTail = head; // update tail node
            head = cur; // start point of next part
        }

        if(nHead == null) return head; // length is less than k
        return nHead;
    }

    // use k to avoid the need of break link
    private ListNode reverse(ListNode head, int k) {
        ListNode cur = head, pre = null;
        while(k > 0) {
            ListNode nxt = cur.next;
            
            cur.next = pre;
            pre = cur;
            cur = nxt;
            k--;
        }
        return pre;
    }
}
// @lc code=end

