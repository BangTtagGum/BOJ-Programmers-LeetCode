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
    fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {
        var l1var = l1
        var l2var = l2
        
        var node = ListNode(0)
        val root = node

        var sum: Int
        var carry = 0
        var remainder: Int

        while(l1var != null || l2var != null || carry != 0){
            sum = 0
            if(l1var != null){
                sum += l1var.`val`
                l1var = l1var.next
            }
            if(l2var != null){
                sum += l2var.`val`
                l2var = l2var.next
            }

            remainder = (sum + carry) % 10
            carry = (sum + carry) / 10
            node.next = ListNode(remainder)
            node = node.next
        }

        return root.next
    }
}