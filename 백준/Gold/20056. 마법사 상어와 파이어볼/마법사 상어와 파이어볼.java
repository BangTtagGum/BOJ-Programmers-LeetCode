import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int[] dr;
    static int[] dc;

    static int n;
    static int m;
    static int k;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        dr = new int[]{n - 1, n - 1, 0, 1, 1, 1, 0, n - 1};
        dc = new int[]{0, 1, 1, 1, 0, n - 1, n - 1, n - 1};

        WizardShark wizardShark = new WizardShark();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int mass = Integer.parseInt(st.nextToken());
            int speed = Integer.parseInt(st.nextToken());
            int direction = Integer.parseInt(st.nextToken());
            FireBall fireBall = new FireBall(r, c, mass, speed, direction);
            wizardShark.fireBalls.add(fireBall);
        }

        // 마법사 상어의 주문
        for (int i = 0; i < k; i++) {
            wizardShark.spell();
        }


        System.out.println(wizardShark.getCurrentMass());

    }

    static class FireBall {

        int r, c;
        int mass;
        int speed;
        int direction;

        public FireBall(int r, int c, int mass, int speed, int direction) {
            this.r = r;
            this.c = c;
            this.mass = mass;
            this.speed = speed;
            this.direction = direction;
        }

        void move(Deque<FireBall>[][] map) {
            int nr = (r + dr[direction] * speed) % n;
            int nc = (c + dc[direction] * speed) % n;

            map[nr][nc].add(this);
            r = nr;
            c = nc;
        }

    }

    static class WizardShark {

        Queue<FireBall> fireBalls = new LinkedList<>();
        Deque<FireBall>[][] map;

        public void spell() {
            map = new ArrayDeque[n][n];
            for (int i = 0; i < n; i++) {
                map[i] = new ArrayDeque[n];
                for (int j = 0; j < n; j++) {
                    map[i][j] = new ArrayDeque<>();
                }
            }

            // 파이어볼 이동
            int len = fireBalls.size();
            for (int i = 0; i < len; i++) {
                fireBalls.poll().move(map);
            }

            // 파이어볼 합체
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (map[i][j].size() == 1) {
                        fireBalls.add(map[i][j].poll());
                    }

                    if (map[i][j].size() >= 2) {
                        int totalMass = 0;
                        int totalSpeed = 0;
                        int size = map[i][j].size();

                        boolean oddDir = false;
                        boolean evenDir = false;
                        while (!map[i][j].isEmpty()) {
                            FireBall fireBall = map[i][j].poll();
                            totalMass += fireBall.mass;
                            // 홀수방향 존재여부 체크
                            if (!oddDir && fireBall.direction % 2 == 1) {
                                oddDir = true;
                            }
                            // 짝수방향 존재여부 체크
                            if (!evenDir && fireBall.direction % 2 == 0) {
                                evenDir = true;
                            }
                            totalSpeed += fireBall.speed;
                        }
                        if (totalMass < 5) {
                            continue;
                        }

                        for (int l = 0; l < 4; l++) {
                            // 홀수방향만 있거나 짝수 방향만 있을 경우
                            if ((oddDir && !evenDir) || (!oddDir && evenDir)) {
                                fireBalls.add(new FireBall(i, j, totalMass / 5, totalSpeed / size,
                                        l * 2));
                            } else {
                                fireBalls.add(new FireBall(i, j, totalMass / 5, totalSpeed / size,
                                        l * 2 + 1));
                            }
                        }
                    }
                }
            }
        }

        int getCurrentMass() {
            int totalMass = 0;
            int len = fireBalls.size();
            for (int i = 0; i < len; i++) {
                totalMass += fireBalls.poll().mass;
            }
            return totalMass;
        }
    }
}

