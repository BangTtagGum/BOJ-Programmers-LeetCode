import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    static StringTokenizer st;
    static ArrayList<Integer>[] adjList;
    static Map<Integer, Integer> reverseTree;
    static boolean[] isVisited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        adjList = new ArrayList[n + 1];
        reverseTree = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adjList[a].add(b);
            adjList[b].add(a);
        }

        isVisited = new boolean[n + 1];
        isVisited[1] = true;
        makeTree(1);

        int m = Integer.parseInt(br.readLine());

        for (int i = 0; i < m; i++) {

            isVisited = new boolean[n + 1];
            st = new StringTokenizer(br.readLine());

            int target1 = Integer.parseInt(st.nextToken());
            int target2 = Integer.parseInt(st.nextToken());

            int node = target1;
            while (true) {

                if (node == 1) {
                    isVisited[node] = true;
                    break;
                }
                isVisited[node] = true;
                node = reverseTree.get(node);
            }
            
            node = target2;
            while (true) {

                if (isVisited[node]) {
                    System.out.println(node);
                    break;
                }
                node = reverseTree.get(node);
            }
        }
    }

    static void makeTree(int node) {
        for (Integer child : adjList[node]) {
            if (!isVisited[child]) {
                isVisited[child] = true;
                reverseTree.put(child, node);
                makeTree(child);
            }
        }
    }

}


