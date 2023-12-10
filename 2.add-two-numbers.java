/*
 * @lc app=leetcode id=2 lang=java
 *
 * [2] Add Two Numbers
 */

// @lc code=start

// class ListNode {
//     int val;
//     ListNode next;

//     ListNode(int x) {
//         val = x;
//     }
// }

class Solution {
    // notice: list is in reversed order
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode res = l1, pre = null;
        int carry = 0;
        while (l1 != null && l2 != null) {
            l1.val += l2.val + carry;

            carry = l1.val / 10;
            l1.val -= carry * 10;

            pre = l1;
            l1 = l1.next;
            l2 = l2.next;
        }

        // add rest part of l2 to l1
        if (l2 != null) {
            pre.next = l2;
            l1 = pre.next;
        }

        // deal with rest l1
        while (l1 != null && carry != 0) {
            l1.val += carry;

            carry = l1.val / 10;
            l1.val -= carry * 10;

            pre = l1;
            l1 = l1.next;
        }

        if (carry != 0)
            pre.next = new ListNode(1);

        return res;
    }
}
// @lc code=end
