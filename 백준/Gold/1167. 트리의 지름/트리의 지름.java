import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static StringTokenizer st;
    static Map<Integer, Map<Integer, Integer>> edgeMap;
    static boolean[] isVisited;
    static int longest;


    public static void main(String[] args) throws IOException {
        init();

        for (int i = 1; i <= n; i++) {
            if (edgeMap.containsKey(i)) {
                isVisited[i] = true;
                dfs(i);
                break;
            }
        }
        System.out.println(longest);

    }

    static int dfs(int node) {

        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        if (edgeMap.containsKey(node)) {
            for (Entry<Integer, Integer> entry : edgeMap.get(node).entrySet()) {
                if (!isVisited[entry.getKey()]) {
                    isVisited[entry.getKey()] = true;
                    pq.add(entry.getValue() + dfs(entry.getKey()));
                }
            }
        }
        int maxLength = 0;
        if (!pq.isEmpty()) {
            maxLength = pq.poll();
        }

        int secondLength = 0;
        if (!pq.isEmpty()) {
            secondLength = pq.poll();
        }
        longest = Math.max(longest, maxLength + secondLength);

        return maxLength;
    }

    private static void init() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        isVisited = new boolean[n + 1];
        edgeMap = new HashMap<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken());
            edgeMap.putIfAbsent(u, new HashMap<>());
            while (true) {
                int v = Integer.parseInt(st.nextToken());
                if (v == -1) {
                    break;
                }
                int w = Integer.parseInt(st.nextToken());

                edgeMap.putIfAbsent(v, new HashMap<>());

                edgeMap.get(u).put(v, w);
                edgeMap.get(v).put(u, w);
            }
        }
    }
}
