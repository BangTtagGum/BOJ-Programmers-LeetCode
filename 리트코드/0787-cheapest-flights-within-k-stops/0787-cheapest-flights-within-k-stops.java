import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Queue;

class Solution {
    static public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        Map<Integer, Map<Integer, Integer>> graph = new HashMap<>();
        for (int[] flight : flights) {
            graph.putIfAbsent(flight[0], new HashMap<>());
            graph.get(flight[0]).put(flight[1], flight[2]);
        }

        Queue<int[]> pq = new PriorityQueue<>((e1, e2) ->
                e1[1] - e2[1]
        );

        pq.add(new int[]{src, 0, 0});

        Map<Integer,Integer> visited = new HashMap<>();

        while (!pq.isEmpty()) {
            int[] poll = pq.poll();
            int node = poll[0];
            int distance = poll[1];
            int stopoverCount = poll[2];

            visited.put(node,stopoverCount);

            if (node == dst) {
                return distance;
            }

            if (stopoverCount <= k && graph.containsKey(node)) {
                stopoverCount++;
            
                for (Entry<Integer, Integer> e : graph.get(node).entrySet()) {
                    if(!visited.containsKey(e.getKey()) || stopoverCount < visited.get(e.getKey())){
                        pq.add(new int[]{e.getKey(), distance + e.getValue(), stopoverCount});
                    }
                }
            }
        }


        return -1;
    }
}