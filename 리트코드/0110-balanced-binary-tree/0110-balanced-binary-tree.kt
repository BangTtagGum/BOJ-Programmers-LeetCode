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
    fun isBalanced(root: TreeNode?): Boolean {
        fun dfs(root: TreeNode?) : Int{
            if(root == null) 
                return 0

            val left = dfs(root.left)        
            val right = dfs(root.right)

            return if(left == -1 || right == -1 || Math.abs(left - right) > 1)
                return -1
            else
                left.coerceAtLeast(right) + 1
        }

        return dfs(root) != -1
    }
    
}