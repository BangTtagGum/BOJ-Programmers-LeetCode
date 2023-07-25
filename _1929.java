package org.example;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 2023.07.25
 * 1929 소수 구하기
 */
public class Main {

    static boolean isPrime[] = new boolean[1000001];

    public static void main(String[] args) throws IOException {

        for (int i = 2; i < 1000001; i++) {
            isPrime[i] = true;
        }
        // 에라토스테네스의 채로 미리 모든 소수 구해놓기
        for (int i = 2; i < 1000001; i++) {
            if (isPrime[i]) {
                for (int j = i*2; j < 1000001; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());

        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        // 소수인 경우만 버퍼에 쌓기
        for (int i = m; i <= n; i++) {
            if (isPrime[i]) {
                sb.append(i).append("\n");
            }
        }

        // 한번에 출력
        System.out.println(sb);
    }
}
