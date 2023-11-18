package org.example2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 2023.07.29
 * 2579 계단 오르기
 */
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        // 계단의 점수 합 선언
        int score[] = new int[n + 2];
        for (int i = 1; i <= n; i++) {
            score[i] = Integer.parseInt(br.readLine());
        }

        int dp[] = new int[n + 2];
        dp[1] = score[1];
        dp[2] = score[1] + score[2];

        for (int i = 3; i <= n; i++) {
            dp[i] = Math.max(dp[i - 3] + score[i - 1], dp[i - 2]) + score[i];
        }
        System.out.println(dp[n]);
    }

}




