package org.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 * 10250
 * ACM 호텔
 */
public class Main {

    // ACM 호텔의 높이[0], 너비[1], 몇번째 손님인지[2] 가 저장되어 있다.
    static int acm[] = new int[3];


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        // 테스트케이스 T번
        for (int i = 0; i < T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int idx = 0;
            while (st.hasMoreTokens()) {
                acm[idx++] = Integer.parseInt(st.nextToken());
            }
            StringBuilder sb = new StringBuilder();

            int h = acm[0],w=acm[1],n=acm[2];
            // 층수 구하기
            if (n % h == 0) {
                sb.append(h);
            } else {
                sb.append(n % h);
            }

            // 몇번 방인지 구하기

            if (n%h == 0) {
                if (n/h < 10) {
                    sb.append(0).append(n/h);
                } else {
                    sb.append(n/h);
                }
            } else {
                if (n/h+1 < 10) {
                    sb.append(0).append(n/h+1);
                } else {
                    sb.append(n/h+1);
                }
            }

            System.out.println(sb);
        }
    }
}