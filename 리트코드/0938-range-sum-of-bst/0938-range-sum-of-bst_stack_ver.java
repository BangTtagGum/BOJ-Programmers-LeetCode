class Solution {

    public int rangeSumBST(TreeNode root, int low, int high) {
        Deque<TreeNode> stack = new ArrayDeque<>();

        stack.push(root);

        int result = 0;
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();

            if (node.val > low) {
                if (node.left != null) {
                    stack.push(node.left);
                }
            }
            if (high > node.val) {
                if (node.right != null) {
                    stack.push(node.right);
                }
            }
            if (low <= node.val && node.val <= high) {
                result += node.val;
            }

        }
        return result;
    }
}