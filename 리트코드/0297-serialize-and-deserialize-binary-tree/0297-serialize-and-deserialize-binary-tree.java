/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {

    public String serialize(TreeNode root) {
        if (root == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("#,").append(root.val);

        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            TreeNode node = q.poll();
            if (node.left != null) {
                sb.append(",").append(node.left.val);
                q.add(node.left);
            } else {
                sb.append(",#");
            }
            if (node.right != null) {
                sb.append(",").append(node.right.val);
                q.add(node.right);
            } else {
                sb.append(",#");
            }

        }

        return sb.toString();
    }

    public TreeNode deserialize(String data) {
        if (data.equals("")) {
            return null;
        }

        String[] split = data.split(",");
        TreeNode root = new TreeNode(Integer.parseInt(split[1]));
        Queue<TreeNode> q = new LinkedList<>();


        q.add(root);
        int index = 2;
        while (!q.isEmpty()) {
            TreeNode node = q.poll();

            if (!split[index].equals("#")) {
                node.left = new TreeNode(Integer.parseInt(split[index]));
                q.add(node.left);
            }
            index += 1;
            
            if (!split[index].equals("#")) {
                node.right = new TreeNode(Integer.parseInt(split[index]));
                q.add(node.right);
            }
            index += 1;

        }
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));