import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.AbstractMap;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main {

    static class Road{
        int end;
        int weight;

        public Road(int end, int weight) {
            this.end = end;
            this.weight = weight;
        }
    }

    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        List<Road>[] adjLists = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++) {
            adjLists[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            adjLists[start].add(new Road(end, weight));
        }

        st = new StringTokenizer(br.readLine());

        int s = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        System.out.println(cheapestWay(s, d, adjLists));
    }

    static long cheapestWay(int start, int end, List<Road> [] adjLists){
        Queue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>(
                (e1, e2) -> e1.getValue() - e2.getValue());

        Map<Integer, Integer> distMap = new HashMap<>();

        pq.add(new SimpleEntry<>(start, 0));
        while (!pq.isEmpty()) {
            Entry<Integer, Integer> cur = pq.poll();
            Integer key = cur.getKey();
            Integer weight = cur.getValue();

            if (key == end) {
                return weight;
            }
            if (!distMap.containsKey(key)) {
                distMap.put(key, weight);
                for (Road road : adjLists[key]) {
                    pq.add(new SimpleEntry<>(road.end, road.weight + weight));
                }
            }
        }
        return -1;
    }
}