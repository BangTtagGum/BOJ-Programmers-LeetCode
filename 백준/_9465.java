package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 2023.08.26
 * 9465 스티커
 */
public class _9465 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());

            StringTokenizer st;
            int sticker[][] = new int[2][n+1];
            for (int j = 0; j < 2; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 1; k <= n; k++) {
                    sticker[j][k] = Integer.parseInt(st.nextToken());
                }
            }

            int dp[][] = new int[2][n+1];
            dp[0][1] = sticker[0][1]; // sticker[r],[c] 를 뜯었을 경우 최대값
            dp[1][1] = sticker[1][1];
            for (int k = 2; k <= n; k++) {
                dp[0][k] = Math.max(dp[1][k-1],dp[1][k-2]) + sticker[0][k]; // 바로 대각선 전, 그리고 그 전
                dp[1][k] = Math.max(dp[0][k-1],dp[0][k-2]) + sticker[1][k];
            }
            System.out.println(Math.max(dp[0][n], dp[1][n]));
        }
    }
}
