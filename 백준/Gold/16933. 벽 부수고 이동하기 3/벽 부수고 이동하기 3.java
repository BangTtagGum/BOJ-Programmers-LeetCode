import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {

    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};

    static int n, m, k;

    static class Case{
        int r;
        int c;
        int dist;
        int brokenWallCount;
        boolean night;

        public Case(int r, int c, int dist, int brokenWallCount, boolean night) {
            this.r = r;
            this.c = c;
            this.dist = dist;
            this.brokenWallCount = brokenWallCount;
            this.night = night;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
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

        PriorityQueue<Case> pq = new PriorityQueue<>((a, b) -> a.dist- b.dist);

        pq.add(new Case(0,0,1,0,false));
        while (!pq.isEmpty()) {
            Case cur = pq.poll();

            if (cur.r == n - 1 && cur.c == m - 1) {
                System.out.println(cur.dist);
                return;
            }

            if (!isVisited[cur.r][cur.c][cur.brokenWallCount]) {
                isVisited[cur.r][cur.c][cur.brokenWallCount] = true;

                // 이동
                for (int i = 0; i < 4; i++) {
                    int nr = cur.r + dr[i];
                    int nc = cur.c + dc[i];
                    if (isValid(nr, nc)) {
                        // 벽
                        if (map[nr][nc] == 1) {
                            if (cur.brokenWallCount == k) {
                                continue;
                            }
                            if (!isVisited[nr][nc][cur.brokenWallCount + 1]) {
                                // 밤에는 휴식 후 부수기
                                if(cur.night){
                                    pq.add(new Case(nr, nc, cur.dist + 2, cur.brokenWallCount + 1,
                                            true));
                                } else{
                                    pq.add(new Case(nr,nc,cur.dist + 1,cur.brokenWallCount + 1,
                                            !cur.night));
                                }
                            }
                        } else{
                            if (!isVisited[nr][nc][cur.brokenWallCount]) {
                                pq.add(new Case(nr, nc, cur.dist + 1, cur.brokenWallCount,
                                        !cur.night));
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
