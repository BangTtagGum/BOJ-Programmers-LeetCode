import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static int[] inOrder;
    static int[] postOrder;

    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        init();

        dfs(0, inOrder.length - 1, 0);
    }

    static void dfs(int s, int e, int p) {

        if (s > e) {
            return ;
        }

        int inIndex = -1;
        for (int i = s; i <= e; i++) {
            if (postOrder[p] == inOrder[i]) {
                inIndex = i;
            }
        }

        System.out.print(postOrder[p] + " ");

        // 왼쪽
        dfs(s, inIndex - 1, p + 1 + (e - inIndex));
        // 오른쪽
        dfs(inIndex + 1, e, p + 1);

    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        inOrder = new int[n];
        postOrder = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            inOrder[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = n - 1; i >= 0; i--) {
            postOrder[i] = Integer.parseInt(st.nextToken());
        }
    }

}