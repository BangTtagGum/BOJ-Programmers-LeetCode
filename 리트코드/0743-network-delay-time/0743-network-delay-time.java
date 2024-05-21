import java.util.AbstractMap.*;
import java.util.*;
import java.util.Map.Entry;
class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        Map<Integer, Map<Integer, Integer>> graph = new HashMap<>();

        for (int[] time : times) {
            graph.putIfAbsent(time[0], new HashMap<>());
            graph.get(time[0]).put(time[1], time[2]);
        }

        Queue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>((e1, e2) ->
                e1.getValue() - e2.getValue()
        );

        pq.add(new SimpleEntry<>(k, 0));
        Map<Integer, Integer> distMap = new HashMap<>();

        while (!pq.isEmpty()) {
            Entry<Integer, Integer> cur = pq.poll();
            int node = cur.getKey();
            int distance =cur.getValue();

            if (!distMap.containsKey(node)) {
                distMap.put(node, distance);
                if (graph.containsKey(node)) {
                    for (Entry<Integer, Integer> e : graph.get(node).entrySet()) {
                        pq.add(new SimpleEntry<>(e.getKey(), distance + e.getValue()));
                    }
                }
            }
        }

        if (distMap.size() == n) {
            return Collections.max(distMap.values());
        }
        return -1;
    }
}