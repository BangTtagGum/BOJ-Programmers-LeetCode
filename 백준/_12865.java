package org.example2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *  2023.08.03
 *  12865 평범한 배낭
 */
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        ArrayList<Thing> things = new ArrayList<>();
        things.add(new Thing(0, 0));
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            things.add(new Thing(w, v));
        }
        int dy[][] = new int[n+1][k + 1];

        for (int i = 1; i <= n; i++) {   // i는 물건의 인덱스
            for (int j = 1; j <= k; j++) { // j는 가방의 허용 공간
                dy[i][j] = dy[i - 1][j];
                if (things.get(i).w <= j) {
                    // 중복을 허용하지 않기 위해 dy[i-1]에 i번째 물건의 가치를 더함
                    dy[i][j] = Math.max(dy[i-1][j - things.get(i).w] + things.get(i).v,dy[i-1][j]);
                }

            }
        }

        System.out.println(dy[n][k]);
    }
}

class Thing implements Comparable<Thing>{
    int w;
    int v;

    public Thing(int w, int v) {
        this.w = w;
        this.v = v;
    }


    @Override
    public int compareTo(Thing o) {
        return this.w - o.w;
    }
}






