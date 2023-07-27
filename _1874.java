package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 2023.07.27
 * 1874 스택 수열
 */
public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());

        int arr[] = new int[n];
        int stack[] = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        int idx = 0;
        int stackMax = 0;
        int top = 0;
        int num = 1;
        while (idx != n) {
            if (arr[idx] < stackMax) {
                System.out.println("NO");
                return ;
            }
            // 스택에 넣어야하는지
            if (arr[idx] > stackMax) {
                stackMax = num;
                stack[top++] = num++;
                sb.append("+").append("\n");
            }
            // 스택에서 빼야하는지
            if (arr[idx] == stackMax) {
                --top;
                if (top == 0) {
                    stackMax = 0;
                } else {
                    stackMax = stack[top-1];
                }
                idx++;
                sb.append("-").append("\n");
            }
        }
        System.out.println(sb);
    }
}

