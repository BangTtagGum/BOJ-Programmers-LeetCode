package org.example2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringTokenizer;

/**
 * 2023.07.29
 * 1037 약수
 */
public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        ArrayList<Integer> div = new ArrayList<>();
        while (st.hasMoreTokens()) {
            div.add(Integer.parseInt(st.nextToken()));
        }
        div.sort(new Asc());

        System.out.println(div.get(0) * div.get(div.size() - 1));
    }
}

class Asc implements Comparator<Integer> {


    @Override
    public int compare(Integer o1, Integer o2) {
        return o1.compareTo(o2);
    }
}

