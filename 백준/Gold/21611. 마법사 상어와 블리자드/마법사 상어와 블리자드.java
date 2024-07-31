import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static StringTokenizer st;

    static int[][] map;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static int[] arrangeR = {0, 1, 0, -1};
    static int[] arrangeC = {-1, 0, 1, 0};

    static int n;
    static int m;

    static int totalScore = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                if (i == (n + 1) / 2 && j == (n + 1) / 2) {
                    map[i][j] = -1;
                }
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken()) - 1;
            int s = Integer.parseInt(st.nextToken());

            blizzard(d, s);
        }
        System.out.println(totalScore);
    }

    static private void blizzard(int dir, int size) {
        int r = (n + 1) / 2;
        int c = (n + 1) / 2;
        for (int i = 1; i <= size; i++) {
            map[r + dr[dir] * i][c + dc[dir] * i] = 0;
        }
        // 맵에 있는 구슬들 큐로 반환
        Queue<Integer> q = getRestBeads();

        // 큐에 들어있는 값들 차례로 삽입
        putBeads(q);

        // 구슬 폭발
        Deque<Integer> dq = beadsExplosion();

        while (dq.poll() == -2) {
            putBeads(dq);
            dq = beadsExplosion();
        }
        putBeads(dq);

        // 새로운 구슬 생성
        q = getNewBeads();

        putBeads(q);

    }

    private static Queue<Integer> getNewBeads() {
        int dir = 0;
        int r = (n + 1) / 2;
        int c = (n + 1) / 2;

        int moveCount = 0;
        int moveSize = 1;

        Queue<Integer> q = new LinkedList<>();

        int preBeadNum = map[r + arrangeR[dir]][c + arrangeC[dir]];
        int duplicateCount = 0;
        boolean flag = true;
        // 0이 아닌 수들 큐에 삽입
        while (flag) {
            // 맨 윗줄 처리 후 반복문 종료
            if (moveSize == n) {
                flag = false;
                moveSize--;
            }
            for (int i = 0; i < moveSize; i++) {
                r += arrangeR[dir];
                c += arrangeC[dir];
                if (map[r][c] == 0) {
                    continue;
                }
                if (preBeadNum == map[r][c]) {
                    duplicateCount++;
                } else {
                    q.add(duplicateCount);
                    q.add(preBeadNum);
                    duplicateCount = 1;
                    preBeadNum = map[r][c];
                }
            }
            dir = (dir + 1) % 4;
            moveCount++;

            if (moveCount == 2) {
                moveCount = 0;
                moveSize++;
            }
        }
        q.add(duplicateCount);
        q.add(preBeadNum);
        return q;
    }

    private static void putBeads(Queue<Integer> q) {
        int dir = 0;
        int r = (n + 1) / 2;
        int c = (n + 1) / 2;

        int moveCount = 0;
        int moveSize = 1;

        boolean flag = true;
        // 0이 아닌 수들 큐에 삽입
        while (flag){
            // 맨 윗줄 처리 후 반복문 종료
            if (moveSize == n) {
                flag = false;
                moveSize--;
            }
            for (int i = 0; i < moveSize; i++) {
                r += arrangeR[dir];
                c += arrangeC[dir];
                if (!q.isEmpty()) {
                    map[r][c] = q.poll();
                } else {
                    map[r][c] = 0;
                }
            }
            dir = (dir + 1) % 4;
            moveCount++;

            if (moveCount == 2) {
                moveCount = 0;
                moveSize++;
            }
        }
    }


    private static Queue<Integer> getRestBeads() {
        int dir = 0;
        int r = (n + 1) / 2;
        int c = (n + 1) / 2;

        int moveCount = 0;
        int moveSize = 1;

        Queue<Integer> q = new LinkedList<>();

        boolean flag = true;
        // 0이 아닌 수들 큐에 삽입
        while (flag) {
            // 맨 윗줄 처리 후 반복문 종료
            if (moveSize == n) {
                flag = false;
                moveSize--;
            }
            for (int i = 0; i < moveSize; i++) {
                r += arrangeR[dir];
                c += arrangeC[dir];
                if (map[r][c] == 0) {
                    continue;
                }
                q.add(map[r][c]);
            }
            dir = (dir + 1) % 4;
            moveCount++;

            if (moveCount == 2) {
                moveCount = 0;
                moveSize++;
            }
        }
        return q;
    }

    private static Deque<Integer> beadsExplosion() {

        boolean isExploded = false;

        int dir = 0;
        int r = (n + 1) / 2;
        int c = (n + 1) / 2;

        int moveCount = 0;
        int moveSize = 1;
        int explosionCount = 0;

        Deque<Integer> stack = new ArrayDeque<>();

        boolean flag = true;
        // 0이 아닌 수들 큐에 삽입
        while (flag) {
            // 맨 윗줄 처리 후 반복문 종료
            if (moveSize == n) {
                flag = false;
                moveSize--;
            }
            for (int i = 0; i < moveSize; i++) {
                r += arrangeR[dir];
                c += arrangeC[dir];
                if (map[r][c] == 0) {
                    continue;
                }
                if (stack.isEmpty()) {
                    explosionCount = 1;
                    stack.push(map[r][c]);
                } else {
                    // 동일한 구슬이 다음에 오는 경우
                    if (stack.peek() == map[r][c]) {
                        explosionCount++;

                    // 다른 구슬이 오는 경우
                    } else {
                        // 4개 이상 쌓여있으면 폭발
                        if(explosionCount >= 4){
                            isExploded = true;
                            for (int j = 0; j < explosionCount; j++) {
                                totalScore += stack.pop();
                            }
                        }
                        explosionCount = 1;
                    }
                    stack.push(map[r][c]);
                }
            }
            dir = (dir + 1) % 4;
            moveCount++;

            if (moveCount == 2) {
                moveCount = 0;
                moveSize++;
            }
        }

        // 탐색이 끝나고 동일한게 4개 이상 쌓여있으면 폭발
        if(explosionCount >= 4){
            isExploded = true;
            for (int j = 0; j < explosionCount; j++) {
                totalScore += stack.pop();
            }
        }

        Deque<Integer> dq = new ArrayDeque<>();
        while(!stack.isEmpty()){
            dq.add(stack.pollLast());
        }
        // 폭발이 더이상 일어나지 않는경우 그룹을 만들기 위해 dq의 맨 앞에 -1 로 표기
        if (!isExploded) {
            dq.push(-1);
        } else {
            dq.push(-2);
        }

        return dq;
    }
}