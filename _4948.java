package org.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


/**
 * 2023.07.24
 * 4948 베르트랑 공준
 */

public class Main {

    static boolean[] isPrime = new boolean[123456 * 2+1];

    public static void main(String[] args) throws IOException {


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));



        for (int i = 2; i <= 123456*2; i++) {
            isPrime[i] = true;
        }

        for (int i = 2; i <= 123456*2; i++) {
            if(isPrime[i]) {
                for (int j = i * 2; j <= 123456 * 2; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        int n;
        n = Integer.parseInt(br.readLine());

        while (n != 0) {
            int cnt = 0;


            for (int i = n+1; i <= n * 2; i++) {
                if (isPrime[i]) {
                    cnt++;
                }
            }

            bw.write(String.valueOf(cnt));
            bw.write("\n");
            bw.flush();

            n = Integer.parseInt(br.readLine());
        }
    }

}


/* 시간초과
public class Main {

    public static void main(String[] args) throws IOException {


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = 1;
        n = Integer.parseInt(br.readLine());

        while (n != 0) {
            int cnt = 0;
            for (int i = n; i <= 2 * n; i++) {
                for (int j = 2; j <=i; j++) {
                    if (j == i) {
                        cnt++;
                    }
                    if (i % j == 0) {
                        break;
                    }
                }
            }
            bw.write(String.valueOf(cnt));
            bw.write("\n");
            bw.flush();

            n = Integer.parseInt(br.readLine());
        }

    }
}
*/