import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int n, m, r;
    static int[] items;

    static Map<Integer, Map<Integer, Integer>> graph;
    static Map<Integer, Integer> costMap;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        init();

        int maxItems = 0;
        for (int i = 1; i <= n; i++) {
            PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[1] - a[1]);

            boolean[] canVisited = new boolean[n + 1];

            pq.add(new int[]{i, m});
            while (!pq.isEmpty()) {
                int[] cur = pq.poll();
                int area = cur[0];
                int length = cur[1];

                if (!canVisited[area]) {
                    canVisited[area] = true;

                    if (graph.containsKey(area)) {
                        for (Entry<Integer, Integer> e : graph.get(area).entrySet()) {
                            if (length >= e.getValue()) {
                                pq.add(new int[]{e.getKey(), length - e.getValue()});
                            }
                        }
                    }
                }
            }

            int totalItems = 0;
            for (int j = 1; j <= n; j++) {
                if (canVisited[j]) {
                    totalItems += items[j];
                }
            }
            maxItems = Math.max(maxItems, totalItems);
        }
        System.out.println(maxItems);
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        items = new int[n + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            items[i] = Integer.parseInt(st.nextToken());
        }

        graph = new HashMap<>();
        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());

            graph.putIfAbsent(a, new HashMap<>());
            graph.get(a).put(b, l);
            graph.putIfAbsent(b, new HashMap<>());
            graph.get(b).put(a, l);
        }


    }

}
