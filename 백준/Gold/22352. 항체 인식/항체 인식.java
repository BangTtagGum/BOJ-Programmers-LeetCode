import java.io.*;
import java.util.*;

public class Main {
    public static int[][] map, compare;
    public static boolean[][] check;
    public static int N, M;
    public static int[] dx = {-1, 1, 0, 0};
    public static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        compare = new int[N][M];
        check = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[] diffInfo = new int[4];
        boolean diffFound = false;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                compare[i][j] = Integer.parseInt(st.nextToken());
                if (!diffFound && map[i][j] != compare[i][j]) {
                    diffFound = true;
                    diffInfo[0] = i;
                    diffInfo[1] = j;
                    diffInfo[2] = map[i][j];
                    diffInfo[3] = compare[i][j];
                }
            }
        }

        if (diffFound) {
            DFS(diffInfo[0], diffInfo[1], diffInfo[2], diffInfo[3]);
        }

        String result = "YES";
        outer:
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] != compare[i][j]) {
                    result = "NO";
                    break outer;
                }
            }
        }

        System.out.println(result);
    }

    public static void DFS(int x, int y, int color, int toColor) {
        if (x < 0 || y < 0 || x >= N || y >= M || check[x][y] || map[x][y] != color) return;
        check[x][y] = true;
        map[x][y] = toColor;
        for (int i = 0; i < 4; i++) {
            DFS(x + dx[i], y + dy[i], color, toColor);
        }
    }
}
