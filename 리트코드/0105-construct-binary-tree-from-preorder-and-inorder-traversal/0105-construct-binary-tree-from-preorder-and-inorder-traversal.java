/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return dfs(0, 0, inorder.length - 1, preorder, inorder);

    }

    public TreeNode dfs(int preIndex, int inStart, int inEnd, int[] preorder, int[] inorder) {

        if (inStart > inEnd) {
            return null;
        }

        int inIndex = 0;
        for (int i = inStart; i <= inEnd; i++) {
            if (inorder[i] == preorder[preIndex]) {
                inIndex = i;
                break;
            }
        }

        TreeNode treeNode = new TreeNode(inorder[inIndex]);

        treeNode.left = dfs(preIndex + 1, inStart, inIndex - 1, preorder, inorder);
        treeNode.right = dfs(preIndex + 1 + (inIndex - inStart), inIndex + 1, inEnd, preorder, inorder);

        return treeNode;
    }
}