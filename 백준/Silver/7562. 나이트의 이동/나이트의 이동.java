import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int[] dr = {-1, -2, -2, -1, 1, 2, 2, 1};
    static int[] dc = {-2, -1, 1, 2, 2, 1, -1, -2};

    static StringTokenizer st;
    static int t;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < t; i++) {
            int l = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            int startRow = Integer.parseInt(st.nextToken());
            int startCol = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            int targetRow = Integer.parseInt(st.nextToken());
            int targetCol = Integer.parseInt(st.nextToken());
            sb.append(solve(l,startRow,startCol,targetRow,targetCol)).append("\n");
        }

        System.out.println(sb);
    }

    private static int solve(int len, int r, int c, int tr, int tc) {
        Queue<int[]> q = new LinkedList<>();

        boolean[][] visited = new boolean[len][len];
        visited[r][c] = true;
        q.offer(new int[]{r, c});
        int times = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int[] cur = q.poll();
                int curRow = cur[0];
                int curCol = cur[1];

                if(curRow == tr && curCol == tc) {
                    return times;
                }

                for (int j = 0; j < 8; j++) {
                    int nr = curRow + dr[j];
                    int nc = curCol + dc[j];
                    if(isValid(nr,nc,len) && !visited[nr][nc]) {
                        visited[nr][nc] = true;
                        q.offer(new int[]{nr, nc});
                    }
                }
            }

            times++;
        }
        return -1;
    }

    private static boolean isValid(int r,int c,int len) {
        return 0 <= r && r < len && 0 <= c && c < len;
    }
}
