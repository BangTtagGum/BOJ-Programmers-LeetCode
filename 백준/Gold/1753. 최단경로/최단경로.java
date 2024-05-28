import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.AbstractMap.SimpleEntry;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }


    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());

        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        int k = Integer.parseInt(br.readLine());

        Map<Integer, Map<Integer, Integer>> distanceMap = new HashMap<>();
        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            distanceMap.putIfAbsent(start, new HashMap<>());
            int previousWeight = distanceMap.get(start).getOrDefault(end, -1);

            // 간선이 존재하지 않을 경우 처음 삽입
            if (previousWeight == -1) {
                distanceMap.get(start).put(end, weight);
                continue;
            }

            // 이미 동일한 간선이 존재하는 경우 더 짧은 것을 저장
            weight = Math.min(weight, previousWeight);
            distanceMap.get(start).put(end, weight);


        }

        Queue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>((a, b) ->
                a.getValue() - b.getValue()
        );

        pq.add(new SimpleEntry<>(k, 0));

        int[] distToOtherNode = new int[v + 1];
        for (int i = 1; i <= v; i++) {
            distToOtherNode[i] = Integer.MAX_VALUE;
        }

        while (!pq.isEmpty()) {
            Entry<Integer, Integer> edge = pq.poll();
            int edgeNum = edge.getKey();
            int dist = edge.getValue();

            // 거리 최신화
            if (dist < distToOtherNode[edgeNum]) {
                distToOtherNode[edgeNum] = dist;
            } else {
                continue;
            }

            if (distanceMap.containsKey(edgeNum)) {
                for (Entry<Integer, Integer> entry : distanceMap.get(edgeNum).entrySet()) {
                    int nextNode = entry.getKey();
                    int newDist = dist + entry.getValue();
                    if (newDist < distToOtherNode[nextNode]) {
                        pq.add(new SimpleEntry<>(nextNode, newDist));
                    }
                }
            }
        }

        for (int i = 1; i <= v; i++) {
            if (distToOtherNode[i] == Integer.MAX_VALUE) {
                System.out.println("INF");
            } else {
                System.out.println(distToOtherNode[i]);
            }
        }

    }
}
