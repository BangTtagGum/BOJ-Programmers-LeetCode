class Solution {

    public int rangeSumBST(TreeNode root, int low, int high) {
        Queue<TreeNode> queue = new LinkedList<>();

        queue.add(root);

        int result = 0;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();

            if (node.val > low) {
                if (node.left != null) {
                    queue.add(node.left);
                }
            }
            if (high > node.val) {
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            if (low <= node.val && node.val <= high) {
                result += node.val;
            }

        }
        return result;
    }
}