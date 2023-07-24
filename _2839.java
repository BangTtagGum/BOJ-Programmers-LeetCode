package org.example;

import java.util.Scanner;

/**
 * 2023.07.24
 * 2839 설탕 배달
 */

public class Main {

    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

        int ans = 0;

        while(true){
            if (n % 5 == 0) {
                ans += n/5;
                break;
            }
            else{
                n -= 3;
                ans++;
            }
            if (n < 0) {
                ans = -1;
                break;
            }
        }
        System.out.println(ans);
    }
}