import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static StringTokenizer st;
    static Map<Integer, Map<Integer, Integer>> graph;
    static Map<Integer, Integer> goBackTimeMap;
    static int n,m, x;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());

        graph = new HashMap<>();
        goBackTimeMap = new HashMap<>();
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());

            graph.putIfAbsent(start, new HashMap<>());
            graph.get(start).put(end, time);
        }

        getGoBackTime(x);

        int max = 0;
        for (int i = 1; i <= n; i++) {
            max = Math.max(max, getShortestTime(i) + goBackTimeMap.get(i));
        }

        System.out.println(max);
    }

    static int getShortestTime(int n) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);

        Map<Integer, Integer> timeMap = new HashMap<>();

        pq.add(new int[]{n, 0});
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int town = cur[0];
            int time = cur[1];

            if (town == x) {
                return time;
            }

            if (!timeMap.containsKey(town)) {
                timeMap.put(town, time);
                if (graph.containsKey(town)) {
                    for (Map.Entry<Integer,Integer> e  : graph.get(town).entrySet()) {
                        pq.add(new int[]{e.getKey(), time + e.getValue()});
                    }
                }
            }
        }
        return 0;
    }

    static void getGoBackTime(int n) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);

        pq.add(new int[]{n, 0});

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int town = cur[0];
            int time = cur[1];

            if (!goBackTimeMap.containsKey(town)) {
                goBackTimeMap.put(town, time);
                if (graph.containsKey(town)) {
                    for (Map.Entry<Integer,Integer> e  : graph.get(town).entrySet()) {
                        pq.add(new int[]{e.getKey(), time + e.getValue()});
                    }
                }
            }
        }
    }
}