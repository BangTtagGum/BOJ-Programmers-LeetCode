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
    fun buildTree(preorder: IntArray, inorder: IntArray): TreeNode? {
        fun dfs(preIndex: Int, start: Int, end: Int): TreeNode? {

            if (start > end) return null

            var inIndex: Int = -1;
            for (i in start..end) {
                if (inorder[i] == preorder[preIndex]) {
                    inIndex = i
                    break
                }
            }

            val node: TreeNode = TreeNode(preorder[preIndex])

            node.left = dfs(preIndex + 1, start, inIndex - 1)
            node.right = dfs(preIndex + 1 + (inIndex - start), inIndex + 1, end)
            
            return node
        }

        return dfs(0, 0, inorder.size - 1)
        
    }
}