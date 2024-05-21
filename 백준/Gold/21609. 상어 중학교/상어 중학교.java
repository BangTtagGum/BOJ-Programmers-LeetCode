import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static StringTokenizer st;
    static int n;
    static int m;
    static int[][] map;
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};
    static boolean isVisited[][];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][n];
        isVisited = new boolean[n][n];

        PriorityQueue<BlockGroup> pq = new PriorityQueue<>((bg1, bg2) -> {
            if (bg1.pq.size() == bg2.pq.size()) {
                if (bg2.rainbowCount == bg1.rainbowCount) {
                    if (bg1.standardRow == bg2.standardRow) {
                        return bg2.standardColumn - bg1.standardColumn;
                    }
                    return bg2.standardRow - bg1.standardRow;
                }
                return bg2.rainbowCount - bg1.rainbowCount;
            }
            return bg2.pq.size() - bg1.pq.size();
        });

        // 무지개 블럭들의 위치를 저장한 큐
        Queue<int[]> rainbowQueue = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 0) {
                    rainbowQueue.add(new int[]{i, j});
                }
            }
        }

        int totalScore = 0;

        while (true) {

            // 기준 초기화
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    isVisited[i][j] = false;
                    if (map[i][j] == 0) {
                        rainbowQueue.add(new int[]{i, j});
                    }
                }
            }

            //그룹 찾기
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (!isVisited[i][j] && map[i][j] > 0) {
                        BlockGroup blockGroup = new BlockGroup(i, j, map[i][j]);

                        // 사이즈가 2 이상이어야 그룹으로 인정
                        if (blockGroup.pq.size() >= 2) {
                            pq.add(blockGroup);
                            // 무지개는 다른 그룹에서도 탐색해야 하기 때문에 탐색 가능하게 되돌리기
                            int len = rainbowQueue.size();
                            for (int k = 0; k < len; k++) {
                                int[] rainbowBlock = rainbowQueue.poll();
                                isVisited[rainbowBlock[0]][rainbowBlock[1]] = false;
                                rainbowQueue.add(rainbowBlock);
                            }
                        }
                    }
                }
            }

            if (pq.isEmpty()) {
                break;
            }
            // 가장 큰 블록 삭제
            BlockGroup blockGroup = pq.peek();
            totalScore += blockGroup.getScore();

            pq.clear();
            rainbowQueue.clear();

            // 중력
            gravity();

            // 턴
            turn();

            // 중력
            gravity();
        }

        System.out.println(totalScore);

    }

    static void gravity() {

        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == -2) {
                    for (int k = i - 1; k >= 0; k--) {
                        if (map[k][j] != -2) {
                            if (map[k][j] >= 0) {
                                int tmp = map[i][j];
                                map[i][j] = map[k][j];
                                map[k][j] = tmp;
                            }
                            break;
                        }
                    }
                }
            }
        }
    }

    static void turn() {
        // 좌우 뒤집고 y = -x축으로 뒤집으면 반시계방향으로 90도 회전
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n / 2; j++) {
                int tmp = map[i][j];
                map[i][j] = map[i][n - 1 - j];
                map[i][n - 1 - j] = tmp;
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int tmp = map[i][j];
                map[i][j] = map[j][i];
                map[j][i] = tmp;
            }
        }
    }

    static boolean isValid(int r, int c) {
        return 0 <= r && r < n && 0 <= c && c < n && 0 <= map[r][c];
    }

    static class BlockGroup {

        PriorityQueue<int[]> pq = new PriorityQueue<>((b1, b2) -> {
            // 색이 같을 경우에는 행,열 작은 순으로
            if (b1[2] == b2[2]) {
                if (b1[0] == b2[0]) {
                    return b1[1] - b2[1];
                }
                return b1[0] - b2[0];
            }
            // 일반 블록을 앞으로 무지개를 맨 뒤로
            return b2[2] - b1[2];
        });

        int baseColor;
        int rainbowCount = 0;
        int standardRow;
        int standardColumn;

        public BlockGroup(int i, int j, int color) {
            baseColor = color;
            createBlockGroup(i, j, color);
            int[] standard = pq.peek();

            standardRow = standard[0];
            standardColumn = standard[1];

        }

        public void createBlockGroup(int i, int j, int color) {
            // 방문
            isVisited[i][j] = true;
            // 블럭 삽입
            pq.add(new int[]{i, j, color});

            if (color == 0) {
                rainbowCount++;
            }

            for (int k = 0; k < 4; k++) {
                int nr = i + dr[k];
                int nc = j + dc[k];
                if (isValid(nr, nc) && !isVisited[nr][nc] && (map[nr][nc] == baseColor
                        || map[nr][nc] == 0)) {
                    createBlockGroup(nr, nc, map[nr][nc]);
                }
            }

        }

        public int getScore() {
            int blockCount = pq.size();

            while (!pq.isEmpty()) {
                int[] block = pq.poll();
                // 빈칸으로 만들기
                map[block[0]][block[1]] = -2;
            }

            return blockCount * blockCount;
        }
    }

}
