import java.util.*;
/*
 * @lc app=leetcode id=445 lang=java
 *
 * [445] Add Two Numbers II
 */
class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
 }
// @lc code=start
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    
    // cannot reverse
    // build our own list

    // use a stack, push all node in
    // then gradually pop
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        // store result in l1
        // swich is l2 is longer

        Deque<ListNode> q1 = new LinkedList<>(), q2 = new LinkedList<>();
        int carry = 0;
        
        ListNode cur = l1;
        while(cur != null) {q1.push(cur); cur = cur.next;}
        cur = l2;
        while(cur != null) {q2.push(cur); cur = cur.next;}

        // !!! THIS SWAP FAIL
        // [1] [9, 9] returns [1, 9, 9]
        // because though we exchange q1, q2, but reference is still l2
        // so when we change ListNode l value, we still do it on l2

        // // swap if l2 is longer
        // if(len1 < len2){
        //     // swap listnode pointer
        //     cur = l1; l1 = l2; l2 = cur;
        //     // !!!swap queue
        //     Deque<ListNode> temp = q1; q1 = q2; q1 = temp;
        // }

        
        // add until one is empty
        while(!q1.isEmpty() && !q2.isEmpty()){
            ListNode l = q1.pop(), r = q2.pop();
            // System.out.println(l.val + ", " + r.val);
            l.val += r.val + carry;
            carry = l.val / 10;
            l.val %= 10;   
            // System.out.println(l.val + ", " + r.val);
            // System.out.println("-----");
        }

        // notice the case when they have same length
        // this case, q2.peek() can be empty
        if(q1.isEmpty() && !q2.isEmpty()){
            // l2 is longer, append rest of l2 to l1
            q2.peek().next = l1;
            l1 = l2;
            q1 = q2;
        }

        // deal with rest carry
        // !!!check empty before remove
        while(carry != 0 && !q1.isEmpty()){
            ListNode l = q1.pop();
            l.val += carry;
            carry = l.val / 10;
            l.val %= 10;
        }

        // if need one more node
        if(carry != 0){
            ListNode temp = new ListNode(1);
            temp.next = l1;
            l1 = temp;
        }

        return l1;
    }
}
// @lc code=end

