/*
 * @lc app=leetcode id=160 lang=java
 *
 * [160] Intersection of Two Linked Lists
 */
class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }
}

// @lc code=start

public class Solution {
    // a crazy solution is wirtten in previous version

    // now I will use the one from solution
    // each pointer go through both list

    // list is garenteed to has intersections
    // maybe not
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {

        // corner case input
        if (headA == null || headB == null)
            return null;

        // judge whether there is a intersection
        ListNode p1 = headA, p2 = headB;

        while (p1.next != null) {
            p1 = p1.next;
        }
        while (p2.next != null) {
            p2 = p2.next;
        }
        if (p1 != p2)
            return null;

        // get the intersection node
        p1 = headA;
        p2 = headB;
        while (p1 != p2) {

            // !! else 这里如果不用else括起来，那么就会导致一个地方多走一步
            if (p1 == null) {
                p1 = headB;
            } else {
                p1 = p1.next;
            }
            if (p2 == null) {
                p2 = headA;
            } else {
                p2 = p2.next;
            }
        }
        return p1;
    }
}
// @lc code=end
