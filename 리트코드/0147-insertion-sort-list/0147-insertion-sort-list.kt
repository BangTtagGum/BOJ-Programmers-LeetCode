/**
 * Example:
 * var li = ListNode(5)
 * var v = li.`val`
 * Definition for singly-linked list.
 * class ListNode(var `val`: Int) {
 *     var next: ListNode? = null
 * }
 */
class Solution {
    fun insertionSortList(head: ListNode?): ListNode? {

        var cur:ListNode? = head
        val parent:ListNode = ListNode()
        var p:ListNode = parent
        while(cur != null){
            while(p.next != null && p.next.`val` < cur.`val`){
                p = p.next
            }

            val pNext:ListNode? = p.next
            val cNext:ListNode? = cur.next

            p.next = cur
            cur.next = pNext
            cur = cNext

            if(cur != null && p.next.`val` > cur.`val`){
                p = parent
            }
        }

        return parent.next
    }
}