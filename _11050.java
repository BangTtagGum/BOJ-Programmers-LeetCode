package org.example2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 2023.07.28
 * 11060 이항 계수 1
 */
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int ans = 1;
        for (int i = 1; i <= n; i++) {
            ans *= i;
        }
        for (int i = 1; i <= n-k; i++) {
            ans /= i;
        }
        for (int i = 1; i <= k; i++) {
            ans /= i;
        }
        System.out.println(ans);

    }
}

