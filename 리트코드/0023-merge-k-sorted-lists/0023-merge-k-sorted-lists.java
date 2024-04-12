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
    
    public ListNode mergeKLists(ListNode[] lists) {

        PriorityQueue<ListNode> pq = new PriorityQueue<>((p1, p2) -> {
            return p1.val - p2.val;
        });
        ListNode root = new ListNode();
        ListNode node = root;
        
        for (ListNode list : lists) {
            if (list != null) {
                pq.add(list);
            }
        }
        
        
        while (!pq.isEmpty()) {
            node.next = pq.poll();
            node = node.next;
            if (node.next != null) {
                pq.add(node.next);
            }
        }
        return root.next;
    }

}