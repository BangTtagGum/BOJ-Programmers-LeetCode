import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int n, m;
    static StringTokenizer st;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        ArrayList<int[]>[] map = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            map[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            map[a].add(new int[]{b, c});
            map[b].add(new int[]{a, c});
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);

        Map<Integer, Integer> costMap = new HashMap<>();
        pq.add(new int[]{1, 0});
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int hut = cur[0];
            int cost = cur[1];

            if (hut == n) {
                System.out.println(cost);
                break;
            }

            if (!costMap.containsKey(hut)) {
                costMap.put(hut, cost);

                for (int[] info : map[hut]) {
                    pq.add(new int[]{info[0], cost + info[1]});
                }
            }
        }
    }


}

