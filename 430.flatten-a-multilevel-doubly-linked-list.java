/*
 * @lc app=leetcode id=430 lang=java
 *
 * [430] Flatten a Multilevel Doubly Linked List
 */
class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;
}
// @lc code=start
class Solution {

    // simple dfs
    public Node flatten(Node head) {
        helper(head);

        // System.out.println("here");
        // Node cur = head;
        // while(cur != null){
        //     if(cur.prev != null)
        //         System.out.println(cur.prev.val);    
        //     else
        //         System.out.println("prev null");
        //     System.out.println(cur.val);

        //     if(cur.next != null)
        //         System.out.println(cur.next.val);
        //     else
        //         System.out.println("next null");

        //     cur = cur.next;
        // }

        return head;
    }

    Node helper(Node head){
        // returns the end of flattened list
        
        // corner case
        if(head ==null)
            return head;

        // cannot return when head.next == null
        // because there might exist child

        Node pre = null ,cur = head;
        while(cur != null){
            if(cur.child != null){
                
                // flat directly
                if(cur.next == null){
                    // link on left
                    cur.next = cur.child;
                    cur.next.prev = cur;
                    
                    cur.child = null;
                    continue;
                }

                // need repair link
                Node temp = cur.next;
                
                // double link on right
                temp.prev = helper(cur.child);
                temp.prev.next = temp;

                // double link on left
                cur.next = cur.child;
                cur.next.prev = cur;
                cur.child = null;

                // go to next
                cur = temp;
                pre = cur.prev;
                continue;
            }
            
            pre = cur;
            cur = cur.next;            
        }

        return pre;
    }
}
// @lc code=end

