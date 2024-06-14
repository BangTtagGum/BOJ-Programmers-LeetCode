import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int m, n;
    static char[][] map;
    static StringTokenizer st;
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};
    static int[][] breakingWallsCount;
    public static void main(String[] args) throws IOException {
        init();

        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[2] - b[2]);
        // map[0][0] 까지 벽을 0개 부숴야 한다
        pq.add(new int[]{0, 0, 0});

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int r = cur[0];
            int c = cur[1];
            int walls = cur[2];

            if (r == n - 1 && c == m - 1) {
                System.out.println(walls);
                break;
            }

            // 처음 방문할 때
            if (breakingWallsCount[r][c] == -1) {
                breakingWallsCount[r][c] = walls;
                for (int i = 0; i < 4; i++) {
                    int nr = r + dr[i];
                    int nc = c + dc[i];
                    if (isValid(nr, nc) && breakingWallsCount[nr][nc] == -1) {
                        if (map[nr][nc] == '1') {
                            pq.add(new int[]{nr, nc, walls + 1});
                        } else {
                            pq.add(new int[]{nr, nc, walls});
                        }
                    }
                }
            }
        }
    }

    static boolean isValid(int r, int c) {
        return 0 <= r && r < n && 0 <= c && c < m;
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        breakingWallsCount = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                breakingWallsCount[i][j] = -1;
            }
        }

        map = new char[n][m];
        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = s.charAt(j);
            }
        }
    }
}
