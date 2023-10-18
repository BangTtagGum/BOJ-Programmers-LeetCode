package org.example2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

/**
 * 2023.07.29
 * 1927 최소 힙
 */
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(br.readLine());
            if (num == 0) { // 삭제연산
                if (minHeap.size() == 0) {
                    sb.append("0\n");
                } else {
                    sb.append(minHeap.remove()).append("\n");
                }
            } else { // 삽입연산
                minHeap.add(num);
            }
        }
        System.out.println(sb);
    }
    
}

