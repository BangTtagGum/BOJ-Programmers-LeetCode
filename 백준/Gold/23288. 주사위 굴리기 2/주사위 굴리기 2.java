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
    static int n, m, k;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        int[][] map = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] scoreMap = getScoreMap(map);

        /** 굴리기 시작 **/
        int r = 0;
        int c = 0;
        int dir = 0; // 동쪽

        int[] diceMap = {1, 2, 5, 4, 3, 6}; // 천장 상 하 좌 우 바닥;
        int totalScore = 0;
        while (k-- > 0) {
            int nr = r + dr[dir];
            int nc = c + dc[dir];
            if (!isValid(nr, nc)) {
                dir = (dir + 2) % 4;
                nr = r + dr[dir];
                nc = c + dc[dir];
            }
            moveDice(diceMap, dir);
            totalScore += scoreMap[nr][nc];

            if (diceMap[5] > map[nr][nc]) {
                dir = (dir + 1) % 4;
            } else if (diceMap[5] < map[nr][nc]) {
                dir = (dir + 3) % 4;
            }
            r = nr;
            c = nc;
        }
        System.out.println(totalScore);

    }

    private static void moveDice(int[] diceMap, int dir) {
        int tmp = diceMap[0];
        // 0: 천장 1: 상 2: 하 3: 좌 4: 우 5: 바닥
        switch (dir) {
            // 동
            case 0:
                diceMap[0] = diceMap[3];
                diceMap[3] = diceMap[5];
                diceMap[5] = diceMap[4];
                diceMap[4] = tmp;
                break;
            // 남
            case 1:
                diceMap[0] = diceMap[1];
                diceMap[1] = diceMap[5];
                diceMap[5] = diceMap[2];
                diceMap[2] = tmp;
                break;
            // 서
            case 2:
                diceMap[0] = diceMap[4];
                diceMap[4] = diceMap[5];
                diceMap[5] = diceMap[3];
                diceMap[3] = tmp;
                break;
            // 북
            case 3:
                diceMap[0] = diceMap[2];
                diceMap[2] = diceMap[5];
                diceMap[5] = diceMap[1];
                diceMap[1] = tmp;
                break;
        }
    }


    private static int[][] getScoreMap(int[][] map) {
        int r = map.length;
        int c = map[0].length;
        int[][] newMap = new int[r][c];
        boolean[][] visited = new boolean[r][c];

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                // 방문하지 않았다면
                if (!visited[i][j]) {
                    visited[i][j] = true;
                    Queue<int[]> q = new LinkedList<>();
                    Queue<int[]> group = new LinkedList<>();
                    q.add(new int[]{i, j});
                    group.add(new int[]{i, j});
                    while (!q.isEmpty()) {
                        int[] cur = q.poll();
                        int currentRow = cur[0];
                        int currentColumn = cur[1];

                        for (int l = 0; l < 4; l++) {
                            int nextRow = currentRow + dr[l];
                            int nextColumn = currentColumn + dc[l];
                            if (isValid(nextRow, nextColumn) && !visited[nextRow][nextColumn]
                                    && map[nextRow][nextColumn] == map[i][j]) {
                                visited[nextRow][nextColumn] = true;
                                q.add(new int[]{nextRow, nextColumn});
                                group.add(new int[]{nextRow, nextColumn});
                            }
                        }
                    }

                    int totalScore = group.size() * map[i][j];
                    while (!group.isEmpty()) {
                        int[] cur = group.poll();
                        int currentRow = cur[0];
                        int currentColumn = cur[1];
                        newMap[currentRow][currentColumn] = totalScore;
                    }
                }
            }
        }

        return newMap;
    }

    private static boolean isValid(int r, int c) {
        return 0 <= r && r < n && 0 <= c && c < m;
    }

    private static void printMap(int[][] map) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }
}