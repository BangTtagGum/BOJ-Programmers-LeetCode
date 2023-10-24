package org.example2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringTokenizer;

/**
 * 2023.07.29
 * 11399 ATM
 */
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        ArrayList<Integer> arr = new ArrayList<>();
        while (st.hasMoreTokens()) {
            arr.add(Integer.valueOf(st.nextToken()));
        }  
        arr.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });
        int turnAroundTime = 0;
        int ans = 0;
        for (int i = 0; i < arr.size(); i++) {
            turnAroundTime = turnAroundTime + arr.get(i);
            ans += turnAroundTime;
        }
        System.out.println(ans);
    }
}


