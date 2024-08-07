import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

class Main {

    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};

    static int n, m;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];

        Queue<int[]> q = new LinkedList<>();
        Map<Integer, Integer> roomSize = new HashMap<>();

        int roomNum = 2;
        for (int i = 0; i < n; i++) {
            String input = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = (int) input.charAt(j) - '0';
                if (map[i][j] == 1) {
                    q.add(new int[]{i, j});
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 0) {
                    roomSize.put(roomNum, dfs(i, j, roomNum));
                    roomNum++;
                }
            }
        }

        int[][] answer = new int[n][m];

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            int r = cur[0];
            int c = cur[1];

            int size = 1;
            Set<Integer> s = new HashSet<>();

            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                if (isValid(nr, nc) && map[nr][nc] != 1) {
                    s.add(map[nr][nc]);
                }
            }
            for (Integer i : s) {
                size += roomSize.get(i);
            }

            answer[r][c] = size % 10;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                bw.append(String.valueOf(answer[i][j]));
            }
            bw.append("\n");
        }
        bw.flush();

    }

    private static int dfs(int r, int c, int num) {
        int result = 1;
        map[r][c] = num;
        for (int j = 0; j < 4; j++) {
            int nr = r + dr[j];
            int nc = c + dc[j];
            if (isValid(nr, nc) && map[nr][nc] == 0) {
                result += dfs(nr, nc, num);
            }
        }
        return result;
    }

    private static boolean isValid(int nr, int nc) {
        return 0 <= nr && nr < n && 0 <= nc && nc < m;
    }
}
