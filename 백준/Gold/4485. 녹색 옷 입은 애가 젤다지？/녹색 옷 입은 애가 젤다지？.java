import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main {


    static class DoduckRupee{
        int r, c;
        int value;

        public DoduckRupee(int r, int c, int value) {
            this.r = r;
            this.c = c;
            this.value = value;
        }
    }

    static StringTokenizer st;
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();

        int problemNum = 1;
        while (true) {
            int n = Integer.parseInt(br.readLine());
            if (n == 0) {
                break;
            }
            int[][] map = new int[n][n];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            Queue<DoduckRupee> pq = new PriorityQueue<>((d1, d2) -> d1.value - d2.value);
            Map<String, Integer> valueMap = new HashMap<>();
            pq.add(new DoduckRupee(0, 0, map[0][0]));

            while (!pq.isEmpty()) {
                DoduckRupee cur = pq.poll();
                int r = cur.r;
                int c = cur.c;
                int value = cur.value;

                if (r == n - 1 && c == n - 1) {
                    sb.append("Problem ").append(problemNum).append(": ").append(value)
                            .append("\n");
                    break;
                }

                String point = r + " " + c;
                if (!valueMap.containsKey(point)) {
                    valueMap.put(point, value);
                    for (int i = 0; i < 4; i++) {
                        int nr = r + dr[i];
                        int nc = c + dc[i];
                        if (isValid(nr, nc, n)) {
                            pq.add(new DoduckRupee(nr, nc, value + map[nr][nc]));
                        }
                    }
                }
            }

            problemNum++;
        }

        System.out.println(sb);

    }

    private static boolean isValid(int nr, int nc,int n) {
        return 0 <= nr && nr < n && 0 <= nc && nc < n;
    }
}