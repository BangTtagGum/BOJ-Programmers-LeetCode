package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 2023.07.26
 * 10773 제로
 */
public class Main {

    static int top = 0;
    static int stack[] = new int[100000];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int k = Integer.parseInt(br.readLine());

        for (int i = 0; i < k; i++) {
            int num = Integer.parseInt(br.readLine());
            if (num == 0) {
                top--;
                continue;
            }
            stack[top++] = num;
        }

        int sum = 0;
        for (int i = 0; i < top; i++) {
            sum += stack[i];
        }
        System.out.println(sum);

    }

}

