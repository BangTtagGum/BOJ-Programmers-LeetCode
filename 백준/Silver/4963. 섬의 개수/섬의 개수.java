import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static StringTokenizer st;
    static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        st = new StringTokenizer(br.readLine());
        int w = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());

        while (w != 0 || h != 0) {
            int[][] map = new int[h][w];
            for (int i = 0; i < h; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < w; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            int cnt = countIsland(map);
            bw.append(String.valueOf(cnt));
            bw.append("\n");

            st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

        }

        bw.flush();

        br.close();
        bw.close();
    }

    private static int countIsland(int[][] map) {
        int h = map.length;
        int w = map[0].length;

        boolean[][] isVisited = new boolean[h][w];

        int islandCount = 0;
        Queue<int[]> q = new LinkedList<>();
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if(!isVisited[i][j] && map[i][j] == 1){
                    islandCount++;
                    isVisited[i][j] = true;
                    q.add(new int[]{i,j});
                    while (!q.isEmpty()) {
                        int[] cur = q.poll();
                        for (int k = 0; k < 8; k++) {
                            int nh = cur[0] + dr[k];
                            int nw = cur[1] + dc[k];
                            if(isValid(h,w,nh,nw) && !isVisited[nh][nw] && map[nh][nw] == 1){
                                isVisited[nh][nw] = true;
                                q.add(new int[]{nh,nw});
                            }
                        }
                    }
                }
            }
        }
        return islandCount;
    }

    private static boolean isValid(int h, int w, int curh, int curw){
        return 0<= curh && curh < h && 0<= curw && curw < w;
    }
}