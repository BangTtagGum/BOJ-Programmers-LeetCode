

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import javax.imageio.spi.ImageInputStreamSpi;

/**
 * 2023.11.24 내리막 길
 * 첫번째 풀이 - 그냥 백트래킹 (시간초과)
 * 두번째 풀이 - 사방이 자신보다 높은 공간은 체크 후 제외하고 백트래킹 (시간초과)
 * 세번째 풀이 - 방문한 위치 사방으로 도착 가능한지 아닌지 체크 후 반복 방문 방지 (시간초과)
 * 네번째 풀이 - DP
 */
public class Main {


    static int m;
    static int n;
    static int[][] map;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        map = new int[m][n];
        dp = new int[m][n];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = -1;
            }
        }


        System.out.println(start());


    }

    public static int start() {
        return lookAround(0, 0);
    }


    // 주변 탐색 후 이동 여부 지시
    public static int lookAround(int x, int y) {
        if (x == n - 1 && y == m - 1) {
            return 1;
        }
        if (dp[y][x] != -1) {
            return dp[y][x];
        }

        dp[y][x] = 0;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            // 높이가 더 낮은 경우
            if (isValid(nx, ny)) {
                if (map[ny][nx] < map[y][x]) {
                    dp[y][x] += lookAround(nx,ny);
                }
            }
        }
        return dp[y][x];
    }

    // 유효 범위 이내인지 판단
    public static boolean isValid(int x, int y) {
        if (0 <= x && x < n && 0 <= y && y < m) {
            return true;
        }
        return false;
    }

}
