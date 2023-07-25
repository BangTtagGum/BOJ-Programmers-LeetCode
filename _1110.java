package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 2023.07.25
 * 1110 더하기 싸이클
 */
public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int a,b,c;
        int num = n;
        int cnt = 0;

        do {
            a = num / 10;
            b = num % 10;
            c = a + b;
            cnt++;
            num = b * 10 + c%10;
        }while(num != n);

        System.out.println(cnt);
    }

}
