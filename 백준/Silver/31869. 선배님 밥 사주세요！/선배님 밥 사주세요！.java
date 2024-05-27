import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Map<String, int[]> promise = new HashMap<>();
        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            String s = st.nextToken();
            int w = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            promise.put(s, new int[]{w, d, p});
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> a - b);
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            String name = st.nextToken();
            int money = Integer.parseInt(st.nextToken());
            int[] state = promise.get(name);
            if (state[2] <= money) {
                pq.add(state[0] * 7 + state[1]);
            }
        }

        int longest = 0;
        int previousDate = -2;
        int days = 1;
        while (!pq.isEmpty()) {
            Integer date = pq.poll();
            if (date == previousDate) {
                continue;
            }
            if (date == previousDate + 1) {
                days++;
            } else {
                days = 1;
            }
            longest = Math.max(longest, days);
            previousDate = date;
        }
        System.out.println(longest);

    }
}