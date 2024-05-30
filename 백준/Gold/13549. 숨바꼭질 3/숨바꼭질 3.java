import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static StringTokenizer st;
    static StringBuilder sb;

    static class Subin {

        int n;
        int time;

        public Subin(int n, int time) {
            this.n = n;
            this.time = time;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        Queue<Subin> pq = new PriorityQueue<>((s1, s2) -> s1.time - s2.time);

        Map<Integer, Integer> visited = new HashMap<>();
        pq.add(new Subin(n, 0));
        while (!pq.isEmpty()) {
            Subin cur = pq.poll();


            if (cur.n < 0 || visited.containsKey(cur.n)) {
                continue;
            }
            // 방문 표시
            visited.putIfAbsent(cur.n, cur.time);

            if (cur.n == k) {
                System.out.println(cur.time);
                break;
            }

            if (cur.n < k) {
                pq.add(new Subin(cur.n * 2, cur.time));
                pq.add(new Subin(cur.n + 1, cur.time + 1));
                pq.add(new Subin(cur.n - 1, cur.time + 1));
            } else {
                pq.add(new Subin(cur.n - 1, cur.time + 1));
            }
        }
    }
}