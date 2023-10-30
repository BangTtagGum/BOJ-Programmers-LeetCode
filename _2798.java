package org.example2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 2023.07.29
 * 2798 블랙잭 
 */
public class Main {

    static int n,m;
    static ArrayList<Integer> arr = new ArrayList<>();
    static int max = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());


        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr.add(Integer.valueOf(st.nextToken()));
        }

        int max = 0;
        for (int i = 0; i < n-2; i++) {
            for (int j = i+1; j < n-1; j++) {
                for (int k = j+1; k < n; k++) {
                    if (arr.get(i) + arr.get(j) + arr.get(k) > max && arr.get(i) + arr.get(j) + arr.get(k) <= m ) {
                        max = arr.get(i) + arr.get(j) + arr.get(k);
                    }
                }
            }
        }
        System.out.println(max);
    }




}


