package org.example2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 2023.07.28
 * 4949 괄호
 */
public class Main {
 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        while (true) {
            String line = br.readLine();
            // 온점(.) 하나만 입력으로 들어오면 입력종료
            if (line.equals(".")) {
                break;
            }

            int top = 0;
            char stack[] = new char[100];
            boolean flag = true;
            for (int i = 0; i < line.length(); i++) {
                if (line.charAt(i) == '(') {
                    stack[top++] = '(';
                } else if (line.charAt(i) == '[') {
                    stack[top++] = '[';
                } else if (line.charAt(i) == ')') {
                    if (top == 0 || stack[top - 1] != '(') {
                        sb.append("no").append("\n");
                        flag = false;
                        break;
                    } else {
                        top--;
                    }
                } else if (line.charAt(i) == ']') {
                    if (top == 0 || stack[top - 1] != '[') {
                        sb.append("no").append("\n");
                        flag = false;
                        break;
                    } else {
                        top--;
                    }
                }
            }
            if (flag) {
                if (top == 0) {
                    sb.append("yes").append("\n");
                } else {
                    sb.append("no").append("\n");
                }
            }
        }
        System.out.println(sb);
    }
}

