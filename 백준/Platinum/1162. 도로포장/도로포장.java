import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static StringTokenizer st;

    static class Case{
        int city;
        long cost;
        int pavementCount;

        public Case(int city, long cost, int pavementCount) {
            this.city = city;
            this.cost = cost;
            this.pavementCount = pavementCount;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        Map<Integer, ArrayList<int[]>> roadMap = new HashMap<>();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());

            roadMap.putIfAbsent(a, new ArrayList<>());
            roadMap.putIfAbsent(b, new ArrayList<>());

            roadMap.get(a).add(new int[]{b, time});
            roadMap.get(b).add(new int[]{a, time});
        }

        long[][] shortestWay = new long[n + 1][k+1];
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= k; j++) {
                shortestWay[i][j] = -1;
            }
        }

        PriorityQueue<Case> pq = new PriorityQueue<>((a, b) -> {
            if (a.cost > b.cost) {
                return 1;
            } else {
                return -1;
            }
        });
        pq.add(new Case(1, 0, 0));
        while (!pq.isEmpty()) {
            Case cur = pq.poll();

            // 도시까지 오는데 포장했던 도로 수 마다 최단 거리 저장
            if (shortestWay[cur.city][cur.pavementCount] == -1) {
                shortestWay[cur.city][cur.pavementCount] = cur.cost;

                if (roadMap.containsKey(cur.city)) {
                    for (int[] next : roadMap.get(cur.city)) {
                        int nextCity = next[0];
                        int nextCityCost = next[1];
                        // 지금 도로를 포장하는 경우
                        if (cur.pavementCount < k) {
                            pq.add(new Case(nextCity, cur.cost, cur.pavementCount + 1));
                        }
                        // 지금 도로를 포장하지 않는 경우
                        pq.add(new Case(nextCity, cur.cost + nextCityCost, cur.pavementCount));
                    }
                }
            }
        }
        Arrays.sort(shortestWay[n]);
        System.out.println(shortestWay[n][0]);
    }
}