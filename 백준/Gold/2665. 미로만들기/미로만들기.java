import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {

    static int n;
    static char[][] map;
    static int [][] breakCount;

    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        init();

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);

        pq.add(new int[]{0, 0, 0});
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();

            if (cur[0] == n - 1 && cur[1] == n - 1) {
                System.out.println(cur[2]);
                break;
            }

            if (breakCount[cur[0]][cur[1]] == -1) {
                breakCount[cur[0]][cur[1]] = cur[2];

                for (int i = 0; i < 4; i++) {
                    int nr = cur[0] + dr[i];
                    int nc = cur[1] + dc[i];
                    if (isValid(nr, nc) && breakCount[nr][nc] == -1) {
                        if (map[nr][nc] == '0') {
                            pq.add(new int[]{nr, nc, cur[2] + 1});
                        } else {
                            pq.add(new int[]{nr, nc, cur[2]});
                        }
                    }
                }
            }
        }
    }

    private static boolean isValid(int nr, int nc) {
        return 0 <= nr && nr < n && 0 <= nc && nc < n;
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        map = new char[n][n];

        for (int i = 0; i < n; i++) {
            String input = br.readLine();
            for (int j = 0; j < n; j++) {
                map[i][j] = input.charAt(j);
            }
        }

        breakCount = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                breakCount[i][j] = -1;
            }
        }
    }
}
