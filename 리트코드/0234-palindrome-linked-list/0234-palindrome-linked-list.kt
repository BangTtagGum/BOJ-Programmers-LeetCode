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
    fun isPalindrome(head: ListNode?): Boolean {
        val dq: Deque<Int> = LinkedList()
    var node = head
    while (node != null) {
        dq.add(node.`val`)
        node = node.next
    }
    while (!dq.isEmpty() && dq.size > 1) {
        if (dq.pollFirst() != dq.pollLast()) {
            return false
        }
    }
    return true
    }
}