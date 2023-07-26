package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 2023.07.26
 * 1002 터렛
 */
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int r1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            int r2 = Integer.parseInt(st.nextToken());

            // 조규현과 백승환 사이의 거리
            double dis =  Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));

            // 조규현과 백승환의 좌표가 같을 경우
            if (x1 == x2 && y1 == y2) {
                if (r1 == r2) {     // 같은 거리에 있으면 월 위의 모든 점에 존재 가능
                    System.out.println(-1);
                } else { // 불가능하지만 다른 거리면 만날 수 없다.
                    System.out.println(0);
                }
            } else {    // 조규현과 백승현의 좌표가 다를 경우
                if ((r1 + dis < r2 || r2 + dis < r1) && r1 + r2 > dis) { // 작은 원이 큰 원 안에 있을 때
                    System.out.println(0);
                } else if ((r1 + dis == r2 || r2 + dis == r1) && r1 + r2 > dis) { // 작은 원이 큰 원에 내접할 때
                    System.out.println(1);
                } else if ((r1 + dis > r2 || r2 + dis > r1) && r1 + r2 > dis) { // 두 원이 두점에서 만날 때
                    System.out.println(2);
                } else if (r1 + r2 == dis) { // 두 원이 외접할 때
                    System.out.println(1);
                } else { // 두 원이 만나지 못할 때
                    System.out.println(0);
                }
            }
        }

        
        
    }

}
