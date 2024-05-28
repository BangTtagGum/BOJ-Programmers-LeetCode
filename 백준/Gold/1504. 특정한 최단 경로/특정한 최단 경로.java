import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.AbstractMap.SimpleEntry;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        Map<Integer, Map<Integer, Integer>> distanceMap = new HashMap<>();
        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            distanceMap.putIfAbsent(a, new HashMap<>());
            distanceMap.get(a).put(b, weight);

            distanceMap.putIfAbsent(b, new HashMap<>());
            distanceMap.get(b).put(a, weight);
        }

        st = new StringTokenizer(br.readLine());

        int totalDist = 0;
        int[] v = new int[4];
        v[0] = 1;
        v[1] = Integer.parseInt(st.nextToken());
        v[2] = Integer.parseInt(st.nextToken());
        v[3] = n;
        
        for (int i = 1; i <= 3; i++) {
            int dist = getShortestDistance(distanceMap, v[i - 1], v[i]);
            if (dist == -1) {
                System.out.println(-1);
                return;
            }
            totalDist += dist;
        }
        int result = totalDist;

        int tmp = v[1];
        v[1] = v[2];
        v[2] = tmp;
        totalDist = 0;
        for (int i = 1; i <= 3; i++) {
            int dist = getShortestDistance(distanceMap, v[i - 1], v[i]);
            if (dist == -1) {
                System.out.println(-1);
                return;
            }
            totalDist += dist;
        }

        result = Math.min(result, totalDist);

        System.out.println(result);

        br.close();
    }

    static int getShortestDistance(Map<Integer, Map<Integer, Integer>> distanceMap, int start,
            int end) {
        PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>(
                (e1, e2) -> e1.getValue() - e2.getValue());

        pq.add(new SimpleEntry<>(start, 0));

        Map<Integer, Integer> dist = new HashMap<>();
        while (!pq.isEmpty()) {
            Entry<Integer, Integer> cur = pq.poll();
            int node = cur.getKey();
            int value = cur.getValue();

            if (node == end) {
                return value;
            }

            if (!dist.containsKey(node)) {
                dist.put(node, value);

                if (distanceMap.containsKey(node)) {
                    for (Entry<Integer, Integer> entry : distanceMap.get(node).entrySet()) {
                        pq.add(new SimpleEntry<>(entry.getKey(), entry.getValue() + value));
                    }
                }
            }
        }
        return -1;
    }

}
