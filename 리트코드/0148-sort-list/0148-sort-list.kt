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
    fun sortList(head: ListNode?): ListNode? {
        fun mergeTwoLists(l1: ListNode?, l2: ListNode?): ListNode? {
            if (l1 == null) {
                return l2
            }
            if (l2 == null) {
                return l1
            }

            return if (l1.`val` < l2.`val`) {
                l1.next = mergeTwoLists(l1.next, l2)
                l1
            } else {
                l2.next = mergeTwoLists(l1, l2.next)
                l2
            }
        }

        if(head?.next == null){
            return head
        }
        var half:ListNode? = null
        var slow:ListNode? = head
        var fast:ListNode? = head
        while (fast?.next != null) {
            half = slow
            slow = slow!!.next
            fast = fast.next.next
        }

        half!!.next = null

        var l1: ListNode? = sortList(head)
        var l2: ListNode? = sortList(slow)

        return mergeTwoLists(l1, l2)

    }
}