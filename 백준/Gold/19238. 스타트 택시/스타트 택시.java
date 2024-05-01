import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int n, totalClientCnt, fuel;

    static int[][] map;
    static int[][] endPoint;
    static int[] dr = {-1, 0, 0, 1};
    static int[] dc = {0, -1, 1, 0};
    static SmartTaxi smartTaxi;

    public static void main(String[] args) throws IOException {


        init();

        smartTaxi.work();

    }
    static class SmartTaxi {

        int r, c;

        int fuel;

        int totalClientCnt;
        int currentClientNum;
        int completeClientCnt = 0;
        public SmartTaxi(int r, int c, int fuel, int totalClientCnt) {
            this.r = r;
            this.c = c;
            this.fuel = fuel;
            this.totalClientCnt = totalClientCnt;
        }
        void work() {
            while (totalClientCnt > completeClientCnt) {
                // 가장 가까운 고객 픽업
                if (!pickup()) {
                    // 현재 연료로 픽업할 수 있는 고객이 없는 경우
                    System.out.println(-1);
                    return;
                }

                map[r][c] = 0;

                // 고객 이송
                if (!moveClientToDestination(endPoint[this.currentClientNum][0], endPoint[this.currentClientNum][1])) {
                    // 연료안에 이송이 불가능한 경우
                    System.out.println(-1);
                    return ;
                }
            }
            System.out.println(this.fuel);
        }

        // 가까운 고객 탐색
        boolean pickup() {
            // 고객과 택시의 위치가 동일할 경우
            if (map[r][c] > 1) {
                this.currentClientNum = map[r][c];
                return true;
            }

            // 가까운 거리부터 탐색
            boolean[][] visited = new boolean[n+1][n+1];
            PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
                if (a[0] == b[0]) {
                    return a[1] - b[1];
                } else {
                    return a[0] - b[0];
                }
            });
            Queue<int[]> q = new LinkedList<>();
            q.add(new int[]{r, c, 0});
            visited[r][c] = true;
            while (!q.isEmpty()) {
                int[] point = q.poll();
                if (point[2] == this.fuel) {
                    continue;
                }
                for (int i = 0; i < 4; i++) {
                    int nr = point[0] + dr[i];
                    int nc = point[1] + dc[i];
                    int usedFuel = point[2] + 1;
                    if (isValid(nr, nc) && !visited[nr][nc]) {
                        if (map[nr][nc] > 1) {
                            if (pq.isEmpty()) {
                                pq.add(new int[]{nr, nc, map[nr][nc], usedFuel});
                            } else if(pq.peek()[3] == usedFuel) {
                                pq.add(new int[]{nr, nc, map[nr][nc], usedFuel});
                            }
                        }
                        visited[nr][nc] = true;
                        q.add(new int[]{nr, nc, usedFuel});
                    }
                }
            }
            if (pq.isEmpty()) {
                return false;
            }
            int[] poll = pq.poll();
            int r = poll[0];
            int c = poll[1];
            int clientNum = poll[2];
            int usedFuel = poll[3];

            this.r = r;
            this.c = c;
            this.fuel -= usedFuel;
            this.currentClientNum = clientNum;
            return true;
        }

        boolean moveClientToDestination(int er, int ec) {
            // 가까운 승객 탐색
            boolean[][] visited = new boolean[n+1][n+1];
            Queue<int[]> q = new LinkedList<>();
            q.add(new int[]{r, c, 0});
            visited[r][c] = true;
            while (!q.isEmpty()) {
                int[] point = q.poll();
                if (point[2] == this.fuel) {
                    continue;
                }
                for (int i = 0; i < 4; i++) {
                    int nr = point[0] + dr[i];
                    int nc = point[1] + dc[i];
                    int usedFuel = point[2] + 1;
                    if (isValid(nr, nc) && !visited[nr][nc]) {
                        if (nr == er && nc == ec) {
                            this.r = er;
                            this.c = ec;
                            this.fuel += usedFuel;
                            this.completeClientCnt++;
                            return true;
                        }
                        visited[nr][nc] = true;
                        q.add(new int[]{nr, nc, usedFuel});
                    }
                }
            }
            return false;
        }



    }
    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        totalClientCnt = Integer.parseInt(st.nextToken());
        fuel = Integer.parseInt(st.nextToken());

        map = new int[n+1][n+1];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());

        int startR = Integer.parseInt(st.nextToken());
        int startC = Integer.parseInt(st.nextToken());

        endPoint = new int[totalClientCnt + 2][2];
        for (int i = 2; i < totalClientCnt + 2; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            map[r][c] = i;

            endPoint[i][0] = Integer.parseInt(st.nextToken());
            endPoint[i][1] = Integer.parseInt(st.nextToken());
        }

        smartTaxi = new SmartTaxi(startR, startC, fuel, totalClientCnt);
    }

    static boolean isValid(int r, int c) {
        return 1 <= r && r <= n && 1 <= c && c <= n && map[r][c] != 1;
    }
}

