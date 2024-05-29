import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static StringTokenizer st;
    static StringBuilder sb;

    static class Road {

        int end;
        int dist;

        public Road(int end, int dist) {
            this.end = end;
            this.dist = dist;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        // 도시간 도로의 거리 정보
        ArrayList<Road>[] adjLists = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++) {
            adjLists[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            adjLists[start].add(new Road(end, dist));
        }

        Queue<int[]> pq = new PriorityQueue<>((e1, e2) -> e1[1] - e2[1]);
        pq.add(new int[]{1, 0});

        // 도시까지의 최단거리 정보
        PriorityQueue<Integer>[] distMap;
        distMap = new PriorityQueue[n + 1];
        for (int i = 1; i <= n; i++) {
            distMap[i] = new PriorityQueue<>((a, b) -> b - a);
        }

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int nodeNum = cur[0];
            int dist = cur[1];

            // 최단거리가 처음 지정되거나 ||  (k번째 최단거리까지 구해지지 않았고 && 저장된 최단거리 중 가장 먼 최단거리보다 멀게 도착하는 경우만)
            if (distMap[nodeNum].isEmpty() || (distMap[nodeNum].size() < k
                    && distMap[nodeNum].peek() <= dist)) {
                distMap[nodeNum].add(dist);
                for (Road road : adjLists[nodeNum]) {
                    pq.add(new int[]{road.end, dist + road.dist});
                }
            }
        }

        sb = new StringBuilder();

        for (int i = 1; i <= n; i++) {
            if (!distMap[i].isEmpty() && distMap[i].size() == k) {
                sb.append(distMap[i].peek()).append("\n");
            } else {
                sb.append(-1).append("\n");
            }
        }

        System.out.println(sb);

    }
}