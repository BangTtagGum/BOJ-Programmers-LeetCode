import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static StringTokenizer st;
    static TreeNode[] tree;

    static class TreeNode {

        char val;
        TreeNode left;
        TreeNode right;


        TreeNode(char val) {
            this.val = val;
        }

        TreeNode(char val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        tree = new TreeNode[n];
        for (int i = 0; i < n; i++) {
            char val = (char) (i + 'A');
            tree[i] = new TreeNode(val);
        }

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            char p = st.nextToken().charAt(0);
            char c1 = st.nextToken().charAt(0);
            char c2 = st.nextToken().charAt(0);


            if (c1 != '.') {
                tree[p-'A'].left = tree[c1 - 'A'];
            }
            if (c2 != '.') {
                tree[p-'A'].right = tree[c2 - 'A'];
            }

        }

        pre(tree[0]);
        System.out.println();
        in(tree[0]);
        System.out.println();
        pos(tree[0]);
        System.out.println();
    }


    static void pre(TreeNode node) {

        if (node != null) {
            System.out.print(node.val);
            pre(node.left);
            pre(node.right);
        }
    }


    static void in(TreeNode node) {

        if (node != null) {
            in(node.left);
            System.out.print(node.val);
            in(node.right);
        }
    }
    static void pos(TreeNode node) {

        if (node != null) {
            pos(node.left);
            pos(node.right);
            System.out.print(node.val);
        }
    }
}