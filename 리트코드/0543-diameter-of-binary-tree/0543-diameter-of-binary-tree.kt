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
    fun diameterOfBinaryTree(root: TreeNode?): Int {
        var longest:Int = 0;
        fun dfs(node: TreeNode?): Int{
            if(node == null)
                return -1
            
            val left = dfs(node.left)
            val right = dfs(node.right)

            longest = Math.max(longest, left + right + 2)
            return Math.max(left,right) + 1
        }
        
        dfs(root);
        return longest
    }
}