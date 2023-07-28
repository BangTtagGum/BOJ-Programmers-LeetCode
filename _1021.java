package org.example2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 2023.07.27
 * 1021 회전하는 큐
 */
public class Main {

    static int left;
    static int right;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        // 큐 생성
        ArrayList<Integer> queue = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            queue.add(i);
        }

        // 뽑아내려고 하는 수들
        int peek[] = new int[m];
        int idx = 0;
        st = new StringTokenizer(br.readLine());
        while (st.hasMoreTokens()) {
            peek[idx++] = Integer.parseInt(st.nextToken());
        }

        int ans = 0;

        for (int i = 0; i < m; i++) {

            int peekIdx = queue.indexOf(peek[i]);
            int half = queue.size() % 2 == 0 ? queue.size() / 2 - 1 : queue.size() / 2;

            if (peekIdx <= half) {
                for (int j = 0; j < peekIdx; j++) {
                    int val = queue.get(0);
                    queue.remove(0);
                    queue.add(val);
                    ans++;
                }
            } else {
                for (int j = 0; j < queue.size() - peekIdx; j++) {
                    int val = queue.get(queue.size() - 1);
                    queue.remove(queue.size() - 1);
                    queue.add(0, val);
                    ans++;
                }
            }
            queue.remove(0);
        }
        System.out.println(ans);
    }
}

