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
    fun invertTree(root: TreeNode?): TreeNode? {
        if (root == null)
            return root

        val newNode: TreeNode = TreeNode(root.`val`)
        
        newNode.left = invertTree(root.right)
        newNode.right = invertTree(root.left)
        
        return newNode
    }
}