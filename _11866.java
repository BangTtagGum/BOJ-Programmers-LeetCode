package org.example2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 2023.07.28
 * 11866 요세푸스 문제 0
 */
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        ArrayList<Integer> arr = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            arr.add(i);
        }

        int idx = 0;
        sb.append("<");
        while (arr.size() != 0) {
            idx = (idx + k - 1) % arr.size();
            sb.append(arr.get(idx));
            arr.remove(idx);
            if (arr.size() == 0) {
                sb.append(">");
                break;
            }
            sb.append(", ");
        }
        System.out.println(sb);
    }
}

