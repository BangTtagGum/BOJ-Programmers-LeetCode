import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;

    static char[][] jail;

    static int h, w;

    static ArrayList<int[]> startPoint;

    static int[][][] openedDoorDP;

    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            st = new StringTokenizer(br.readLine());
            h = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());

            jail = new char[h + 2][w + 2];
            openedDoorDP = new int[h + 2][w + 2][3];
            for (int i = 0; i < h + 2; i++) {
                for (int j = 0; j < w + 2; j++) {
                    for (int k = 0; k < 3; k++) {
                        openedDoorDP[i][j][k] = Integer.MAX_VALUE;
                    }
                }
            }

            startPoint = new ArrayList<>();
            for (int i = 0; i < h + 2; i++) {
                if (i == 0 || i == h + 1) {
                    for (int j = 0; j < w + 2; j++) {
                        jail[i][j] = '.';
                    }
                } else {
                    String input = br.readLine();
                    char[] chars = input.toCharArray();
                    for (int j = 0; j < w + 2; j++) {
                        if (j == 0 || j == w + 1) {
                            jail[i][j] = '.';
                        } else {
                            jail[i][j] = chars[j - 1];
                            if (jail[i][j] == '$') {
                                startPoint.add(new int[]{i, j});
                            }
                        }
                    }
                }
            }
            startPoint.add(new int[]{0, 0});

            findDoorCount();

            int answer = Integer.MAX_VALUE;
            for (int i = 0; i < h + 2; i++) {
                for (int j = 0; j < w + 2; j++) {
                    int totalOpenedDoorCount = 0;
                    if (openedDoorDP[i][j][0] != Integer.MAX_VALUE
                            && openedDoorDP[i][j][1] != Integer.MAX_VALUE
                            && openedDoorDP[i][j][2] != Integer.MAX_VALUE) {
                        for (int k = 0; k < 3; k++) {
                            totalOpenedDoorCount += openedDoorDP[i][j][k];
                        }

                        if (jail[i][j] == '#') {
                            totalOpenedDoorCount -= 2;
                        }
                        answer = Math.min(answer, totalOpenedDoorCount);
                    }
                }
            }
            bw.append(Integer.toString(answer)).append("\n");
        }

        bw.flush();
    }

    private static void findDoorCount() {

        PriorityQueue<int[]> pq = new PriorityQueue<>((c1, c2) -> c1[2] - c2[2]);
        for (int i = 0; i < 3; i++) {
            pq.add(new int[]{startPoint.get(i)[0], startPoint.get(i)[1], 0, i});
        }

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int r = cur[0];
            int c = cur[1];
            int openedDoorCount = cur[2];
            int person = cur[3];

            if (openedDoorDP[r][c][person] == Integer.MAX_VALUE) {

                openedDoorDP[r][c][person] = openedDoorCount;

                for (int i = 0; i < 4; i++) {
                    int nr = r + dr[i];
                    int nc = c + dc[i];

                    if (!isValid(nr, nc)) {
                        continue;
                    }
                    if (isWall(nr, nc)) {
                        continue;
                    }
                    if (openedDoorDP[nr][nc][person] != Integer.MAX_VALUE) {
                        continue;
                    }
                    if (isDoor(nr, nc)) {
                        pq.add(new int[]{nr, nc, openedDoorCount + 1, person});
                        continue;
                    }
                    pq.add(new int[]{nr, nc, openedDoorCount, person});
                }
            }

        }
    }

    private static boolean isValid(int r, int c) {
        return 0 <= r && r < h + 2 && 0 <= c && c < w + 2;
    }

    private static boolean isDoor(int r, int c) {
        return jail[r][c] == '#';
    }

    private static boolean isWall(int r, int c) {
        return jail[r][c] == '*';
    }
}