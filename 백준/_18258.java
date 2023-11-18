package org.example2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 2023.07.26
 * 18258 큐 2
 */
public class Main {

    static int head = 0;
    static int tail = 0;
    static int queue[] = new int[2000000];
    static String ins[] = new String[2];

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
            if (ins[0].equals("push")) {
                queue[tail++] = Integer.parseInt(ins[1]);
            } else if (ins[0].equals("pop")) {
                if (head == tail) {
                    sb.append(-1).append("\n");
                } else {
                    sb.append(queue[head++]).append("\n");
                }
            } else if (ins[0].equals("size")) {
                sb.append(tail - head).append("\n");
            } else if (ins[0].equals("empty")){
                if (head == tail) {
                    sb.append(1).append("\n");
                } else {
                    sb.append(0).append("\n");
                }
            } else if (ins[0].equals("front")) {
                if (head == tail) {
                    sb.append(-1).append("\n");
                } else {
                    sb.append(queue[head]).append("\n");
                }
            } else {
                if (head == tail) {
                    sb.append(-1).append("\n");
                } else {
                    sb.append(queue[tail-1]).append("\n");
                }
            }
        }
        System.out.println(sb);
    }
}

