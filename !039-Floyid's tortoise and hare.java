public class Solution {
  public ListNode detectCycle(ListNode head) {
    if (head == null)
      return null;
    ListNode slow = head, fast = head;

    do {
      slow = slow.next;
      fast = fast.next;
      if (fast != null)
        fast = fast.next;
    } while (slow != null && fast != null && slow != fast);
    if (fast == null)
      return null;
    // System.out.println(fast.val);

    // Say circle length is k, the non-circle part length is l
    // slow = n = l + x x is the position in the cirle
    // fast = 2 * n = l + x + mk
    // ==> n = mk

    // after reset fast pointer, they will meet at start point of circle because
    // slow: l + x + l = mk + l
    // fast: l

    fast = head;
    while (slow != fast) {
      fast = fast.next;
      slow = slow.next;
    }
    return slow;
  }
}
