package org.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 * 2023.07.24
 * 2869 달팽이는 올라가고 싶다
 */

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int V = Integer.parseInt(st.nextToken());

        int ans = 0;

        int day = (V - A) % (A - B) == 0 ? (V - A) / (A - B) : (V - A) / (A - B) + 1 ;

        ans = ++day;

        bw.write(String.valueOf(ans));
        bw.flush();

    }

    boolean isPrime(int n) {
        // N이 2보다 작다면 실수가 아니다.
        if (N < 2) {
            return false;
        }
        for (int i = 2; i < n-1; i++) {
            if (n / i == 0) {
                return false;
            }
        }
        return true;
    }

}

