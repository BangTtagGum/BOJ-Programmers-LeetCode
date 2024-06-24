import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static StringTokenizer st;

    static int n;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    static char[][] map;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        ArrayList<int[]> doors = new ArrayList<>();

        map = new char[n][n];
        for (int i = 0; i < n; i++) {
            String input = br.readLine();
            char[] charArray = input.toCharArray();
            map[i] = charArray;
            for (int j = 0; j < n; j++) {
                if (map[i][j] == '#') {
                    doors.add(new int[]{i, j});
                }
            }
        }
        // 거울을 놓지 않는 경우, 왼쪽으로 기울여 놓는 경우, 오른쪽으로 기울여 놓는 경우
        int[][] leastMirrorCount = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < 3; k++) {
                    leastMirrorCount[i][j] = 10000;
                }
            }
        }

        // 거울을 설치 못하는 경우 방향대로 직진해서 pq 삽입
        // 거울 설치 가능한 경우 좌회전,우회전,직진 세가지 경우 pq 삽입
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        for (int i = 0; i < 4; i++) {
            int nr = doors.get(0)[0] + dr[i];
            int nc = doors.get(0)[1] + dc[i];
            if (isValid(nr, nc) && !isWall(nr, nc)) {
                pq.add(new int[]{nr, nc, 0, i});
            }
        }

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int r = cur[0];
            int c = cur[1];
            int mirrorCount = cur[2];
            int dir = cur[3];

            if (map[r][c] == '#') {
                System.out.println(mirrorCount);
                break;
            } else if (map[r][c] == '!') {
                // 0: 거울 설치 X 1: \ 거울 설치 2: / 거울 설치
                if (leastMirrorCount[r][c] < mirrorCount) {
                    continue;
                }
                leastMirrorCount[r][c] = mirrorCount;
                for (int mirrorCase = 0; mirrorCase < 3; mirrorCase++) {

                    int ndir = reflect(dir, mirrorCase);
                    int nr = r + dr[ndir];
                    int nc = c + dc[ndir];
                    if (isValid(nr, nc) && !isWall(nr, nc)) {
                        if (mirrorCase == 0) {
                            pq.add(new int[]{nr, nc, mirrorCount, ndir});
                        } else {
                            pq.add(new int[]{nr, nc, mirrorCount + 1, ndir});
                        }
                    }
                }

            } else if (map[r][c] == '.') {
                int nr = r + dr[dir];
                int nc = c + dc[dir];
                if (isValid(nr, nc) && !isWall(nr, nc)) {
                    pq.add(new int[]{nr, nc, mirrorCount, dir});
                }
            }

        }

    }

    private static boolean isValid(int nr, int nc) {
        return 0 <= nr && nr < n && 0 <= nc && nc < n;
    }

    private static boolean isWall(int nr, int nc) {
        return map[nr][nc] == '*';
    }

    private static int reflect(int dir, int mirrorCase) {
        if (mirrorCase == 0) {
            return dir;
        } else if (mirrorCase == 1) {
            switch (dir) {
                case 0:
                    return 3;
                case 1:
                    return 2;
                case 2:
                    return 1;
                case 3:
                    return 0;
            }
        } else {
            switch (dir) {
                case 0:
                    return 1;
                case 1:
                    return 0;
                case 2:
                    return 3;
                case 3:
                    return 2;
            }
        }
        return -1;
    }
}