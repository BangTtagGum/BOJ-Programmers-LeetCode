import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static StringTokenizer st;
    static int[] possible;
    static boolean[] visited;
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

        possible = new int[n + 1];
        visited = new boolean[n + 1];

        for (int i = 1; i <= n; i++) {
            possible[i] = -1;  // Initialize possible array with -1 (unvisited state)
        }

        for (int i = 1; i <= n; i++) {
            if (possible[i] == -1) {
                visited[i] = true;
                dfs(i);
                visited[i] = false;
            }
        }

        int count = 0;
        for (int i = 1; i <= n; i++) {
            if (possible[i] == 1) {
                count++;
            }
        }

        System.out.println(count);
    }

    static int dfs(int node) {
        if (possible[node] != -1) {
            return possible[node];
        }

        possible[node] = 1; // Assume this node is valid initially
        for (int next : adjList[node]) {
            if (visited[next]) {
                possible[node] = 0; // Found a cycle
                continue;
            }

            visited[next] = true;
            if (dfs(next) == 0) {
                possible[node] = 0;
            }
            visited[next] = false;
        }

        return possible[node];
    }
}
