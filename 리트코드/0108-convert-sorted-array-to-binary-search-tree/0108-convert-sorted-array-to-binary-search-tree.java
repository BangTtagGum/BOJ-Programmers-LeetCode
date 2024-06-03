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
    public TreeNode sortedArrayToBST(int[] nums) {
        int size = nums.length;
        if (size == 0) {
            return null;
        }
        return construct(nums, 0, size - 1);
        
    }

    public TreeNode construct(int[] nums, int lo, int hi) {
        if (hi < lo) {
            return null;
        }
        int mid = lo + (hi - lo) / 2;

        TreeNode node = new TreeNode(nums[mid]);

        node.left = construct(nums, lo, mid -1);
        node.right = construct(nums, mid + 1, hi);

        return node;
    }
}