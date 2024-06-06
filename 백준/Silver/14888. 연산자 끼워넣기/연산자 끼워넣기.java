import static java.lang.System.in;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {

    public static int MAX = Integer.MIN_VALUE;	// 최댓값
    public static int MIN = Integer.MAX_VALUE;	// 최솟값
    public static int[] operator = new int[4];	// 연산자 개수
    public static int[] number;					// 숫자
    public static int n;						// 숫자 개수

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(in));

        n = Integer.parseInt(br.readLine());
        number = new int[n];

        // 숫자 입력
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            number[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            operator[i] = Integer.parseInt(st.nextToken());
        }

        dfs(number[0], 1);

        System.out.println(MAX);
        System.out.println(MIN);

    }

    public static void dfs(int num, int idx) {
        if (idx == n) {
            MAX = Math.max(MAX, num);
            MIN = Math.min(MIN, num);
            return;
        }

        for (int i = 0; i < 4; i++) {
            if (operator[i] > 0) {

                operator[i]--;

                switch (i) {

                    case 0:	dfs(num + number[idx], idx + 1);	break;
                    case 1:	dfs(num - number[idx], idx + 1);	break;
                    case 2:	dfs(num * number[idx], idx + 1);	break;
                    case 3:	dfs(num / number[idx], idx + 1);	break;

                }
                operator[i]++;
            }
        }
    }

}