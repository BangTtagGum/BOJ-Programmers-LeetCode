import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    static int t;
    static int n;
    static int target1, target2;
    static StringTokenizer st;
    static ArrayList<Integer>[] adjList;
    static ArrayList<Integer>[] reverseList;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            n = Integer.parseInt(br.readLine());
            adjList = new ArrayList[n + 1];
            for (int i = 1; i < n+1; i++) {
                adjList[i] = new ArrayList<>();
            }
            reverseList = new ArrayList[n + 1];
            for (int i = 1; i < n+1; i++) {
                reverseList[i] = new ArrayList<>();
            }
            for (int i = 0; i < n - 1; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                adjList[a].add(b);
                reverseList[b].add(a);
            }

            st = new StringTokenizer(br.readLine());

            target1 = Integer.parseInt(st.nextToken());
            target2 = Integer.parseInt(st.nextToken());

            int root = 0;
            int node = 1;
            while (true) {
                if (reverseList[node].size() == 0) {
                    root = node;
                    break;
                }
                node = reverseList[node].get(0);
            }

            dfs(root);
        }

    }



    private static boolean[] dfs(int node) {

        boolean[] target = new boolean[2];
        if (node == target1) {
            target[0] = true;
        } else if (node == target2) {
            target[1] = true;
        }

        for (Integer child : adjList[node]) {
            boolean[] c = dfs(child);
            if (c[0]) {
                target[0] = true;
            }
            if (c[1]) {
                target[1] = true;
            }
        }

        if (target[0] && target[1]) {

            System.out.println(node);
            target[0] = false;
        }

        return target ;
    }
}
