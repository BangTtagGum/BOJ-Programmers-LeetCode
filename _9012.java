package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 2023.07.28
 * 9012 괄호
 */
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            int top = 0;
            boolean flag = true; // 중간에 괄호 문자열이 깨질경우를 표현하기 위한 변수
            String brackets = br.readLine();
            for (int j = 0; j < brackets.length(); j++) {
                if (brackets.charAt(j) == '(') {
                    top++;
                } else if (brackets.charAt(j) == ')') {
                    if (top == 0) {
                        sb.append("NO").append("\n");
                        flag = false; // 중간에 빠져나와 이 경우는 무시하고 다음 케이스를 테스트함
                        break;
                    } else {
                        top--;
                    }
                }
            }
            if (flag) {
                if (top == 0) {
                    sb.append("YES").append("\n");
                } else {
                    sb.append("NO").append("\n");
                }
            }
        }
        System.out.println(sb);
    }
}

