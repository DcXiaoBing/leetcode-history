/*
 * @lc app=leetcode id=23 lang=java
 *
 * [23] Merge k Sorted Lists
 */
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}
// @lc code=start
class Solution {

    // devide and conquer (use a iterative one)
    // heap

    public ListNode mergeKLists(ListNode[] lists) {
        // outer loop just use lists.length, one more loop do not harm
        for(int step = 1; step < lists.length ; step *= 2){
            for(int i = 0; i < lists.length; i += step * 2){
                // System.out.println(step);
                if(i + step < lists.length)
                    lists[i] = merge(lists[i], lists[i + step]);
            }
        }
        return lists[0];
    }
    ListNode merge(ListNode l1, ListNode l2){
        // merge it to l1

        // corner case
        if(l1 == null || l2 == null){
            if(l1 == null)
                l1 = l2;

            return l1;
        }
        
        ListNode p1 = l1, p2 = l2;
        ListNode res = null, pre = null;
        if(l1.val < l2.val) {res = l1; p1 = p1.next;}
        else {res = l2; p2 = p2.next;}

        pre = res;
        while(p1 != null && p2 != null){
            // System.out.println(p1.val + ", " + p2.val);
            if(p1.val < p2.val) {pre.next = p1; p1 = p1.next;}
            else {pre.next = p2; p2 = p2.next;}
            
            pre = pre.next;
        }

        if(p1 == null)
            pre.next = p2;
        if(p2 == null)
            pre.next = p1;

        return res; 
    }
}
// @lc code=end

