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
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if(head == null)
            return null;

        ListNode root = new ListNode(0);
        root.next = head;
        ListNode start = root;
        for(int i = 0; i <left - 1; i++){
            start = start.next;
        }
        ListNode end = start.next;


        for(int i = 0; i < right - left; i++){
            ListNode tmp = start.next;
            start.next = end.next;
            end.next = start.next.next;
            start.next.next = tmp;
        }

        return root.next;
    }
}