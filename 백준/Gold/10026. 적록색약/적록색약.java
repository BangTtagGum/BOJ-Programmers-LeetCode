import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    static char[][] map;

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        map = new char[n][n];
        for (int i = 0; i < n; i++) {
            String input = br.readLine();
            map[i] = input.toCharArray();
        }

        int total = countArea();

        StringBuilder sb = new StringBuilder();
        sb.append(total).append(" ");

        total = countArea();
        sb.append(total);
        System.out.println(sb);
    }

    private static int countArea() {
        int total = 0;
        boolean[][] visited = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j]) {
                    total++;
                    areaCheck(i, j,map[i][j],visited);
                }
            }
        }
        return total;
    }

    private static void areaCheck(int i, int j, char target, boolean[][] visited) {

        Queue<int[]> q = new LinkedList<>();

        visited[i][j] = true;
        q.add(new int[]{i, j});
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0];
            int c = cur[1];

            if (map[r][c] == 'R') {
                map[r][c] = 'G';
            }

            for (int k = 0; k < 4; k++) {
                int nr = r + dr[k];
                int nc = c + dc[k];

                if (isValid(nr, nc) && !visited[nr][nc] && target == map[nr][nc]) {
                    visited[nr][nc] = true;
                    q.add(new int[]{nr, nc});
                }
            }
        }
    }

    private static boolean isValid(int i, int j) {
        return 0 <= i && i < n && 0 <= j && j < n;
    }

}
