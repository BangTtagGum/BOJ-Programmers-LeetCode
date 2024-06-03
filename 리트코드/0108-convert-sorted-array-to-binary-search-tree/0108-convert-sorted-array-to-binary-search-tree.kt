/**
 * Example:
 * var ti = TreeNode(5)
 * var v = ti.`val`
 * Definition for a binary tree node.
 * class TreeNode(var `val`: Int) {
 *     var left: TreeNode? = null
 *     var right: TreeNode? = null
 * }
 */
class Solution {
    fun sortedArrayToBST(nums: IntArray): TreeNode? {
        fun construct(lo:Int, hi:Int): TreeNode?{
            if(hi < lo) return null
            val mid = lo + (hi-lo) / 2

            val node:TreeNode = TreeNode(nums[mid])
            node.left = construct(lo,mid - 1)
            node.right = construct(mid + 1, hi)
            
            return node
        }

        return construct(0,nums.size-1)
    }
    
    
}