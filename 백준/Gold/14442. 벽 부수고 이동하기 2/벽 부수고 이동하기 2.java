import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {

    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};

    static int n, m, k;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        int[][] map = new int[n][m];
        boolean[][][] isVisited = new boolean[n][m][k + 1];
        for (int i = 0; i < n; i++) {
            String input = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = (int) input.charAt(j) - '0';
            }
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);

        pq.add(new int[]{0, 0, 1, 0});
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int r = cur[0];
            int c = cur[1];
            int dist = cur[2];
            int brokenWallCount = cur[3];

            if (r == n - 1 && c == m - 1) {
                System.out.println(dist);
                return;
            }

            if (!isVisited[r][c][brokenWallCount]) {
                isVisited[r][c][brokenWallCount] = true;
                for (int i = 0; i < 4; i++) {
                    int nr = r + dr[i];
                    int nc = c + dc[i];
                    if (isValid(nr, nc)) {
                        if (map[nr][nc] == 1) {
                            if (brokenWallCount == k) {
                                continue;
                            }
                            if (!isVisited[nr][nc][brokenWallCount + 1]) {
                                pq.add(new int[]{nr, nc, dist + 1, brokenWallCount + 1});
                            }
                        } else {
                            if (!isVisited[nr][nc][brokenWallCount]) {
                                pq.add(new int[]{nr, nc, dist + 1, brokenWallCount});
                            }
                        }

                    }
                }
            }
        }
        System.out.println(-1);
    }

    private static boolean isValid(int nr, int nc) {
        return 0 <= nr && nr < n && 0 <= nc && nc < m;
    }
}
