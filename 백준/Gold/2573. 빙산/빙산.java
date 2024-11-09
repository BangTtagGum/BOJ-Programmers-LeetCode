import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main {

    static StringTokenizer st;

    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};
    static int remainArea;
    static int n, m;

    static int[][] map;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];

        Queue<int[]> q = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] != 0) {
                    q.add(new int[]{i, j});
                    remainArea++;
                }
            }
        }

        int yearCount = 0;
        // 빙산이 두조각 나거나 다 녹을때까지
        while (true) {
            if (divideCheck()) {
                break;
            }
            if (remainArea == 0) {
                yearCount = 0;
                break;
            }

            // 빙하 녹이기
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int[] point = q.poll();
                int r = point[0];
                int c = point[1];
                int discountPoint = 0;
                for (int j = 0; j < 4; j++) {
                    int nr = r + dr[j];
                    int nc = c + dc[j];
                    if (isValid(nr, nc) && map[nr][nc] <= 0) {
                        discountPoint++;
                    }
                }
                q.add(new int[]{r, c, discountPoint});
            }

            for (int i = 0; i < size; i++) {
                int[] cur = q.poll();
                int r = cur[0];
                int c = cur[1];
                int discount = cur[2];
                map[r][c] -= discount;
                if (map[r][c] <= 0) {
                    map[r][c] = 0;
                    remainArea--;
                } else {
                    q.add(new int[]{r, c});
                }
            }

            yearCount++;
        }

        System.out.println(yearCount);

    }

    private static boolean isValid(int r, int c) {
        return 0 <= r && r < n && 0 <= c && c < m;
    }

    private static boolean divideCheck() {
        boolean[][] visited = new boolean[n][m];
        int idx = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] > 0 && !visited[i][j]) {
                    idx++;
                    visited[i][j] = true;
                    Queue<int[]> q = new LinkedList<>();
                    q.add(new int[]{i, j});
                    while (!q.isEmpty()) {
                        int[] point = q.poll();
                        int r = point[0];
                        int c = point[1];
                        for (int k = 0; k < 4; k++) {
                            int nr = r + dr[k];
                            int nc = c + dc[k];
                            if (isValid(nr, nc) && map[nr][nc] > 0 && !visited[nr][nc]) {
                                visited[nr][nc] = true;
                                q.add(new int[]{nr, nc});
                            }
                        }
                    }
                }
            }
        }
        if (idx >= 2) {
            return true;
        }
        return false;
    }
}