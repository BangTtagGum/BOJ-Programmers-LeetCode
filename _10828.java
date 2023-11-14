package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 2023.07.26
 * 10828 스택
 */
public class Main {

    static String ins[] = new String[2]; 
    static int stack[] = new int[10000];
    static int top = 0;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int idx = 0;
            while (st.hasMoreTokens()) {
                ins[idx++] = st.nextToken();
            }
            switch (ins[0]) {
                case "push":
                    stack[top++] = Integer.parseInt(ins[1]);
                    break;
                case "pop":
                    if (top == 0) {
                        sb.append(-1).append("\n");
                    } else {
                        sb.append(stack[--top]).append("\n");
                    }
                    break;
                case "size":
                    sb.append(top).append("\n");
                    break;
                case "empty":
                    if (top == 0) {
                        sb.append(1).append("\n");
                    } else {
                        sb.append(0).append("\n");
                    }
                    break;
                case "top":
                    if (top == 0) {
                        sb.append(-1).append("\n");
                    } else {
                        sb.append(stack[top-1]).append("\n");
                    }
                    break;
            }
        }
        System.out.println(sb);

    }
}

