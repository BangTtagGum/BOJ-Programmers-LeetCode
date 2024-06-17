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
        
        while(head != null){
            ListNode p = parent;    
            while(p.next != null && p.next.val < head.val){
                p = p.next;
            }

            ListNode pNext = p.next;
            ListNode hNext = head.next;
            p.next = head;
            head.next = pNext;
            head = hNext;

        }

        return parent.next;
    }
}