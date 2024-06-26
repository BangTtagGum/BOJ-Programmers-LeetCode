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
    public ListNode insertionSortList(ListNode head) {
        
        ListNode parent = new ListNode();
        ListNode p = parent; 
        while(head != null){
               
            while(p.next != null && p.next.val < head.val){
                p = p.next;
            }

            ListNode pNext = p.next;
            ListNode hNext = head.next;
            p.next = head;
            head.next = pNext;
            head = hNext;

            if(head != null && p.next.val > head.val){
                p = parent;
            }
        }

        return parent.next;
    }
}