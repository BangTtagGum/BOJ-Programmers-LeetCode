import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        long k = Long.parseLong(st.nextToken());

        int[] degree = new int[n + 1];
        long[] trafficCount = new long[n + 1];
        Map<Integer, List<Integer>> adj = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            // 워커 노드
            if (r == 0) {
                continue;
            }

            // 로드밸런서
            adj.putIfAbsent(i, new ArrayList<>());
            for (int j = 0; j < r; j++) {

                int node = Integer.parseInt(st.nextToken());
                adj.get(i).add(node);
                // 노드 진입차수 1 증가
                degree[node]++;
            }
        }

        Queue<Integer> q = new LinkedList<>();
        q.offer(1);
        trafficCount[1] = k;
        while (!q.isEmpty()) {
            Integer currentNode = q.poll();

            List<Integer> adjList = adj.get(currentNode);
            int size = adjList.size();
            long traffics = trafficCount[currentNode] / size;
            long rest = trafficCount[currentNode] % size;

            for (Integer node : adjList) {
                if (rest > 0) {
                    trafficCount[node] += traffics + 1;
                    rest--;
                } else {
                    trafficCount[node] += traffics;
                }

                degree[node]--;
                // 진입 차수 체크
                if (degree[node] == 0) {
                    // 로드밸런서의 경우 큐에 삽입
                    if (adj.containsKey(node)) {
                        q.offer(node);
                    }
                }
            }
        }

        // 출력
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < n + 1; i++) {
            sb.append(trafficCount[i]).append(" ");
        }
        System.out.println(sb);
    }

}