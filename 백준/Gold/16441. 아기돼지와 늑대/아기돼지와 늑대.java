import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};

    static char[][] map;
    static StringTokenizer st;
    static int n, m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new char[n][m];

        for (int i = 0; i < n; i++) {
            String input = br.readLine();

            map[i] = input.toCharArray();
        }

        boolean[][][] visited = new boolean[n][m][4];
        Queue<int[]> q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 'W') {
                    for (int k = 0; k < 4; k++) {
                        visited[i][j][k] = true;
                    }
                    q.add(new int[]{i, j, 0});
                }
            }
        }

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0];
            int c = cur[1];
            int dir = cur[2];


            if (map[r][c] == '+') {
                int nr = r + dr[dir];
                int nc = c + dc[dir];
                if (map[nr][nc] == '#') {
                    for (int i = 0; i < 4; i++) {
                        nr = r + dr[i];
                        nc = c + dc[i];

                        if (!visited[nr][nc][i] && map[nr][nc] != '#') {
                            visited[nr][nc][i] = true;
                            q.add(new int[]{nr, nc, i});
                        }
                    }
                } else if (!visited[nr][nc][dir]) {
                    visited[nr][nc][dir] = true;
                    q.add(new int[]{nr, nc, dir});
                }
            } else {
                for (int i = 0; i < 4; i++) {
                    int nr = r + dr[i];
                    int nc = c + dc[i];

                    if (!visited[nr][nc][i] && map[nr][nc] != '#') {
                        visited[nr][nc][i] = true;
                        q.add(new int[]{nr, nc, i});
                    }
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == '.') {
                    boolean flag = false;
                    for (int k = 0; k < 4; k++) {
                        if (visited[i][j][k]) {
                            flag = true;
                            break;
                        }
                    }
                    if (!flag) {
                        map[i][j] = 'P';
                    }
                }
                bw.append(map[i][j]);
            }
            bw.append("\n");
        }

        bw.flush();

        br.close();
        bw.close();
    }

}
