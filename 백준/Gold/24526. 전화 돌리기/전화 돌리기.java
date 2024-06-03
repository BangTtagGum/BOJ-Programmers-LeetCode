import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static StringTokenizer st;
    static int[] isValid; // 0 불가 1 가능
    static boolean[] isCalled;
    static ArrayList<Integer>[] adjList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        adjList = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            adjList[u].add(v);
        }

        isValid = new int[n + 1];
        isCalled = new boolean[n + 1];

        // 한번 방문했던 노드는 성공, 실패 여부가 나왔기에 재방문 하지 않도록
        for (int i = 1; i <= n; i++) {
            isValid[i] = -1;
        }

        for (int i = 1; i <= n; i++) {
            if (isValid[i] == -1) {
                isCalled[i] = true;
                dfs(i);
                isCalled[i] = false;
            }
        }

        int count = 0;
        for (int i = 1; i <= n; i++) {
            if (isValid[i] == 1) {
                count++;
            }
        }

        System.out.println(count);
    }

    static int dfs(int memberNum) {
        if (isValid[memberNum] != -1) {
            return isValid[memberNum];
        }

        isValid[memberNum] = 1;
        for (int i : adjList[memberNum]) {
            if (isCalled[i]) {
                isValid[memberNum] = 0;
                continue;
            }

            isCalled[i] = true;
            if (dfs(i) == 0) {
                isValid[memberNum] = 0;
            }
            isCalled[i] = false;
        }

        return isValid[memberNum];
    }
}
