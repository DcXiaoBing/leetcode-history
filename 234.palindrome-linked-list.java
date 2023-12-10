/*
 * @lc app=leetcode id=234 lang=java
 *
 * [234] Palindrome Linked List
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
// reverse right part is best solution.
// Because we know the exact start point of right part
class Solution {
  public boolean isPalindrome(ListNode head) {
    boolean isOdd = false; // must remenber this. Because the node fast point to might be changed. e.g. [1,2]
    ListNode slow = head, fast = head;
    while(fast.next != null && fast.next.next != null) {
      slow = slow.next;
      fast = fast.next.next;
    }
    if(fast.next == null) isOdd = true;
    ListNode l, r = slow.next; // r is certain

    ListNode pre = null, cur = head;
    while(cur != slow) {
      ListNode temp = cur.next;
      cur.next = pre;

      pre = cur;
      cur = temp;
    }
    cur.next = pre; // handle last node. slow still pointing to right

    // odd len, fast point to last node. slow points to center
    if(isOdd) l = slow.next;
    else l = slow;
    // System.out.println(l.val + " " + r.val);

    while(l != null) {
      if(l.val != r.val) return false;
      l = l.next;
      r = r.next;
    }
    return true;
  }
}
// @lc code=end

