package org.example2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 2023.07.29
 * 2231 분해합
 */
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int num = 1;
        while (num < n) {
            int ele = num; // 분해합을 구하기 위해 수를 복사한 변수
            int sum = ele;   // 요소를 나눈 값을 더해 저장할 변수
            while (ele > 0) {
                sum += ele % 10;
                ele /= 10;
            }
            if (sum == n) {
                System.out.println(num);
                return ;
            }
            num++;
        }
        System.out.println(0);
    }
}


