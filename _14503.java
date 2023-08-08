package org.example2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 2023.08.08
 * 14503 로봇 청소기
 */

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());


        st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int d= Integer.parseInt(st.nextToken());

        Robot robo = new Robot(n,m,r, c, d);

        // 맵 입력 받기
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                Room.room[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        while (robo.running) {
            robo.doJob();
        }
        System.out.println(robo.cnt);
    }
}

class Robot {
    int n,m; // 로봇이 청소할 범위
    int r,c; // 로봇 청소기의 위치
    int d; // 로봇 청소기가 바라보는 방향
    int dr[] = {-1, 0, 1, 0};
    int dc[] = {0, 1, 0, -1};
    int cnt = 0; // 로봇이 청소한 칸의 수
    boolean running = true;


    public Robot(int n, int m, int r, int c, int d) {
        this.n = n;
        this.m = m;
        this.r = r;
        this.c = c;
        this.d = d;
    }

    public void doJob() {
        clean();  // 1. 현재 칸을 청소
        if (isSurroundClean()) { // 2. 주변 4칸 중 청소 안한이 없는 경우
            if (isValid(this.r + dr[(this.d + 2) % 4], this.c + dc[(this.d + 2) % 4]) && Room.room[this.r + dr[(this.d + 2) % 4]][this.c + dc[(this.d + 2) % 4]] != 1) {
                moveBack();
            } else {
                this.running = false;
            }
        } else { // 3. 현재 칸의 주변 4칸중 청소되지 않은 빈 칸이 있는 경우
            for (int i = 0; i < 4; i++) {
                this.turn();
                if (isValid(this.r + dr[this.d], this.c + dc[this.d])) {
                    if (Room.room[this.r + dr[this.d]][this.c + dc[this.d]] == 0) { // 앞에 있는 방이 청소를 안한경우
                        this.move();
                        break;
                    }
                }
            }
        }
    }

    public void clean() {
        if (Room.room[r][c] == 0) {
            Room.room[r][c] = 2; // 방을 청소
            this.cnt++;
        }
    }
    public void move() {
            this.r = this.r + dr[this.d];
            this.c = this.c + dc[this.d];
    }
    public void moveBack() {
            this.r = this.r + dr[(this.d + 2) % 4];
            this.c = this.c + dc[(this.d + 2) % 4];
    }
    public void turn() {// 반시계 방향으로 90 도 회전
        this.d = (this.d + 3) % 4;
    }
    public boolean isSurroundClean() {
        for (int i = 0; i < 4; i++) {
            int r = this.r + this.dr[i];
            int c = this.c + this.dc[i];
            if (isValid(r, c)) {
                if (Room.room[r][c] == 0) { // 청소 안한 방이 주변에 있을경우
                    return false;
                }
            }
        }
        return true;
    }
    public boolean isValid(int r, int c) {
        if (0 <= r && r < n && 0 <= c && c < m) {
            return true;
        } else {
            return false;
        }
    }
}
class Room{
    static int room[][] = new int[50][50];
}
