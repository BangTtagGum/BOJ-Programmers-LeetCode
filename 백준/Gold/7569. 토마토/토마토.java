import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int[][] dir = {{0, 0, 1}, {0, 0, -1}, {0, 1, 0}, {0, -1, 0}, {1, 0, 0}, {-1, 0, 0}};

    static int n, m, h;
    static int[][][] map;
    static boolean[][][] isVisited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());

        map = new int[h][n][m];
        isVisited = new boolean[h][n][m];

        Queue<int[]> q = new LinkedList<>();
        boolean flag = true;
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < n; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < m; k++) {
                    map[i][j][k] = Integer.parseInt(st.nextToken());
                    if (map[i][j][k] == 1) {
                        isVisited[i][j][k] = true;
                        q.add(new int[]{i, j, k});
                    }
                }
            }
        }

        int time = 0;
        while (!q.isEmpty()) {
            if (checkAllTomatoRipe()) {
                System.out.println(time);
                break;
            }
            int len = q.size();
            for (int i = 0; i < len; i++) {
                int[] cur = q.poll();
                for (int j = 0; j < 6; j++) {
                    int nh = cur[0] + dir[j][0];
                    int nr = cur[1] + dir[j][1];
                    int nc = cur[2] + dir[j][2];
                    if (isValid(nh, nr, nc) && !isVisited[nh][nr][nc]) {
                        q.add(new int[]{nh, nr, nc});
                        map[nh][nr][nc] = 1;
                        isVisited[nh][nr][nc] = true;
                    }
                }
            }
            time++;
        }

        if (!checkAllTomatoRipe()) {
            System.out.println(-1);
        }

    }

    private static boolean isValid(int nh, int nr, int nc) {
        return 0 <= nh && nh < h && 0 <= nc && nc < m && 0 <= nr && nr < n && map[nh][nr][nc] == 0;
    }

    private static boolean checkAllTomatoRipe() {
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < m; k++) {
                    if (map[i][j][k] == 0) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}