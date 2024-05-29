import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static StringTokenizer st;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        // 도시간 도로의 거리 정보
        Map<Integer, Map<Integer, ArrayList<Integer>>> roadMap = new HashMap<>();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            roadMap.putIfAbsent(start, new HashMap<>());
            roadMap.get(start).putIfAbsent(end, new ArrayList<>());
            roadMap.get(start).get(end).add(dist);
        }

        Queue<int[]> pq = new PriorityQueue<>(
                (e1, e2) -> e1[1] - e2[1]
        );
        pq.add(new int[]{1, 0});

        // 도시까지의 최단거리 정보
        Map<Integer, ArrayList<Integer>> distMap = new HashMap<>();

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int nodeNum = cur[0];
            int dist = cur[1];

            // 최단거리가 처음 지정되거나 ||  (k번째 최단거리까지 구해지지 않았고 && 저장된 최단거리 중 가장 먼 최단거리보다 멀게 도착하는 경우만)
            if (!distMap.containsKey(nodeNum) || (distMap.get(nodeNum).size() < k
                    && distMap.get(nodeNum).get(distMap.get(nodeNum).size() - 1) <= dist)) {
                distMap.putIfAbsent(nodeNum, new ArrayList<>());
                distMap.get(nodeNum).add(dist);
                if (roadMap.containsKey(nodeNum)) {
                    for (Entry<Integer, ArrayList<Integer>> entry : roadMap.get(nodeNum)
                            .entrySet()) {
                        for (Integer value : entry.getValue()) {
                            pq.add(new int[]{entry.getKey(), dist + value});
                        }
                    }
                }
            }
        }

        sb = new StringBuilder();

        for (int i = 1; i <= n; i++) {
            if (distMap.get(i) != null && distMap.get(i).size() == k) {
                sb.append(distMap.get(i).get(k - 1)).append("\n");
            } else {
                sb.append(-1).append("\n");
            }
        }

        System.out.println(sb);
        
    }
}