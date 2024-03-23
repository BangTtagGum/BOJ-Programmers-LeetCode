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
    fun reverseList(head: ListNode?): ListNode? {
        var prev: ListNode? = null
        var node = head
        while(node != null){
            var next = node.next
            node.next = prev
            prev = node
            node = next
        }
        return prev
    }
}