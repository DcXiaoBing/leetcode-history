/*
 * @lc app=leetcode id=138 lang=java
 *
 * [138] Copy List with Random Pointer
 */

// @lc code=start

// class Node {
//     public int val;
//     public Node next;
//     public Node random;

//     public Node() {
//     }

//     public Node(int _val, Node _next, Node _random) {
//         val = _val;
//         next = _next;
//         random = _random;
//     }
// }

class Solution {

    // HashMap or make a copy node after
    // HashMap is easy, try no extra space

    public Node copyRandomList(Node head) {
        if (head == null)
            return null;

        Node cur = head;

        // insert copied node into the postion after original node
        while (cur != null) {
            Node temp = new Node(cur.val);

            temp.next = cur.next;
            cur.next = temp;

            cur = temp.next;
        }

        // copy random node
        cur = head;
        while (cur != null) {
            if (cur.random != null)
                cur.next.random = cur.random.next;
            cur = cur.next.next;
        }

        // split two list
        cur = head;
        Node res = head.next;
        while (cur != null) {
            Node temp = cur.next;

            cur.next = temp.next;
            if (temp.next != null)
                temp.next = temp.next.next;

            cur = cur.next;
        }
        return res;
    }
}
// @lc code=end
