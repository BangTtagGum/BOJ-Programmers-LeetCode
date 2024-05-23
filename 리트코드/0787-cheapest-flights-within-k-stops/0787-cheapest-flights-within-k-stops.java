import java.util.Map.Entry;
import java.util.Map;
import java.util.HashMap;
import java.util.PriorityQueue;
class Solution {
    static public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        Map<Integer, Map<Integer,Integer>> graph = new HashMap<>();

        for (int[] flight : flights){
            graph.putIfAbsent(flight[0],new HashMap<>());
            graph.get(flight[0]).put(flight[1],flight[2]);
        }

        Queue<int[]> pq = new PriorityQueue<>((e1,e2) ->
            e1[1] - e2[1]
        );

        Map<Integer,Integer> visited = new HashMap<>();

        pq.add(new int[]{src,0,0});
        
        while(!pq.isEmpty()){
            int[] cur = pq.poll();
            int node = cur[0];
            int dist = cur[1];
            int visitCount = cur[2];

            if(node == dst){
                return dist;
            }
            visited.put(node,visitCount);

            // 현재 노드에서 다음 경로가 있는 경우 pq 에 추가
            if(visitCount <= k && graph.containsKey(node)){
                for(Map.Entry<Integer,Integer> e : graph.get(node).entrySet()){
                    // 처음 방문하는 노드거나 || 방문 했었지만, 아직 다른곳을 경유해 더 짧게 갈 가능성이 있는 경우
                    if(!visited.containsKey(e.getKey()) || visitCount < visited.get(e.getKey())){
                        pq.add(new int[]{e.getKey(),e.getValue() + dist, visitCount + 1});
                    }
                }
            }
            
        }

        return -1;

    }
}