import java.io.*;
import java.util.*;

public class Main {

    static StringTokenizer st;
    static int[][] map;
    static int paintingMaxSize = 0;
    static int paintingCount = 0;


    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {-1, 0, 1, 0};
    static int n, m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 1 && !visited[i][j]) {

                    paintingCount++;
                    visited[i][j] = true;
                    int size = 1;

                    q.offer(new int[]{i, j});
                    while (!q.isEmpty()) {
                        int[] cur = q.poll();
                        int curRow = cur[0];
                        int curCol = cur[1];

                        for (int k = 0; k < 4; k++) {
                            int nr = curRow + dr[k];
                            int nc = curCol + dc[k];
                            if (isValid(nr, nc) && !visited[nr][nc]) {
                                visited[nr][nc] = true;
                                q.offer(new int[]{nr, nc});
                                size++;
                            }
                        }
                    }
                    if (size > paintingMaxSize) {
                        paintingMaxSize = size;
                    }

                }
            }
        }

        System.out.println(paintingCount);
        System.out.println(paintingMaxSize);
        br.close();
    }

    private static boolean isValid(int nr, int nc) {
        return nr >= 0 && nr < n && nc >= 0 && nc < m && map[nr][nc] == 1;
    }
}