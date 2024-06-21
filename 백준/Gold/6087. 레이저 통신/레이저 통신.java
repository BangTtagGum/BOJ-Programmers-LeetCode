import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static StringTokenizer st;

    static char[][] map;
    static int[][] mirrorCount;
    static boolean [][][] visitCount;
    static ArrayList<int[]> CPoint;

    static int w, h;

    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());

        w = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());

        mirrorCount = new int[h][w];
        visitCount = new boolean[h][w][4];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                mirrorCount[i][j] = Integer.MAX_VALUE;
            }
        }

        map = new char[h][w];
        CPoint = new ArrayList<>();
        for (int i = 0; i < h; i++) {
            String input = br.readLine();
            char[] chars = input.toCharArray();
            for (int j = 0; j < w; j++) {
                map[i][j] = chars[j];
                if (map[i][j] == 'C') {
                    CPoint.add(new int[]{i, j});
                }
            }
        }

        int startR = CPoint.get(0)[0];
        int startC = CPoint.get(0)[1];

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);

        mirrorCount[startR][startC] = 0;
        for (int i = 0; i < 4; i++) {
            int nr = startR + dr[i];
            int nc = startC + dc[i];
            if (isValid(nr, nc) && map[nr][nc] != '*') {
                pq.add(new int[]{nr, nc, 0, i});
            }
        }

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();


            int r = cur[0];
            int c = cur[1];
            int mirror = cur[2];
            int dir = cur[3];
            // 같은 방향으로 들어오는 경우 무시
            if (visitCount[r][c][dir] == true) {
                continue;
            }
            visitCount[r][c][dir] = true;
            if (r == CPoint.get(1)[0] && c == CPoint.get(1)[1]) {
                System.out.println(mirror);
                break;
            }

            if (mirror <= mirrorCount[r][c]) {
                mirrorCount[r][c] = mirror;

                for (int i = 0; i < 4; i++) {
                    int nr = r + dr[i];
                    int nc = c + dc[i];
                    if (isValid(nr, nc) && map[nr][nc] != '*') {
                        if (i != dir) {
                            pq.add(new int[]{nr, nc, mirror + 1, i});
                        } else{
                            pq.add(new int[]{nr, nc, mirror, i});
                        }
                    }
                }
            }
        }

    }

    private static boolean isValid(int nr, int nc) {
        return 0 <= nr && nr < h && 0 <= nc && nc < w;
    }
}