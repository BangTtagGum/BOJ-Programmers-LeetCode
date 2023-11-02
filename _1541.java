package org.example2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 2023.07.28
 * 1541 잃어버린 괄호
 */
public class Main {

    static Object exp[] = new Object[100]; // 수식 저장 배열
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(),"+-",true);

        for (int i = 0; i < 100; i++) { 
            exp[i] = 0;
        }
        int idx = 0; // 수식 입력할 위치 인덱스
        int sum = 0;
        while (st.hasMoreTokens()) {
            String token = st.nextToken();
            if (token.equals("+")) {}
            else if (token.equals("-")) {
                exp[++idx] = "-";
                idx++;
            } else {
                int val = 0;
                for (int i = 0; i <= token.length()-1; i++) {
                    val += Integer.parseInt(String.valueOf(token.charAt(i)));
                    val *= 10;
                }
                val /= 10;
                exp[idx] = (int) exp[idx] + val;

            }
        }
        int ans = (int) exp[0];
        for (int i = 0; i <= idx; i++) {
            if (String.valueOf(exp[i]).equals("-")) {
                ans -= (int) exp[i + 1];
            }
        }
        System.out.println(ans);
    }
}

