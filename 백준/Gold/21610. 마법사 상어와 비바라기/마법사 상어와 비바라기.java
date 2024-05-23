import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static StringTokenizer st;
    static int n, m;
    static int[] dr = {0, -1, -1, -1, 0, 1, 1, 1};
    static int[] dc = {-1, -1, 0, 1, 1, 1, 0, -1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        int[][] map = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        Queue<int[]> cloudQueue = new LinkedList<>();

        // 비바라기 마법 처음 사용
        rainDrops(cloudQueue);

        // m번 이동
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int dir = Integer.parseInt(st.nextToken()) - 1;
            int speed = Integer.parseInt(st.nextToken());

            moveCloud(map, cloudQueue, dir, speed);

        }

        System.out.println(getTotalWater(map));
    }

    static void moveCloud(int[][] map, Queue<int[]> cloudQueue, int dir, int speed) {

        boolean[][] cloudCreationCheck = new boolean[n][n];
        Queue<int[]> increasedAreaQueue = new LinkedList<>();

        while (!cloudQueue.isEmpty()) {

            int[] cloud = cloudQueue.poll();
            int r = cloud[0];
            int c = cloud[1];

            int nr = (r + n + dr[dir] * (speed % n)) % n;
            int nc = (c + n + dc[dir] * (speed % n)) % n;

            // 구름이 이동한 위치 물 증가
            map[nr][nc]++;
            // 물이 증가했던 위치 큐에 저장
            increasedAreaQueue.add(new int[]{nr, nc});

            // 사라진 위치에 구름이 생기지 못하도록 체크
            cloudCreationCheck[nr][nc] = true;
        }

        while (!increasedAreaQueue.isEmpty()) {
            int[] area = increasedAreaQueue.poll();
            int r = area[0];
            int c = area[1];

            // 물 복사 버그
            waterCopyBug(map, r, c);
        }

        // 구름 생성
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] >= 2 && !cloudCreationCheck[i][j]) {
                    cloudQueue.add(new int[]{i, j});
                    map[i][j] -= 2;
                }
            }
        }
    }

    private static void waterCopyBug(int[][] map, int r, int c) {
        int waterNearByCount = 0;
        // 대각선 방향 체크
        for (int i = 1; i < 8; i += 2) {
            int nr = r + dr[i];
            int nc = c + dc[i];

            if (isValid(nr, nc) && map[nr][nc] > 0) {
                waterNearByCount++;
            }
        }

        map[r][c] += waterNearByCount;
    }

    // 비바라기
    static void rainDrops(Queue<int[]> cloudQueue) {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                cloudQueue.add(new int[]{n - 1 - i, j});
            }
        }
    }

    private static int getTotalWater(int[][] map) {
        int totalWater = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                totalWater += map[i][j];
            }
        }
        return totalWater;
    }

    private static boolean isValid(int r, int c) {
        return 0 <= r && r < n && 0 <= c && c < n;
    }
}
