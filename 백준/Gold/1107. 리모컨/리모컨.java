import java.io.*;
import java.util.*;

public class Main {

    static int atoi(String str) {
        return Integer.parseInt(str);
    }

    static int N;
    static boolean error[];

    public static void main(String[] args) throws IOException {
        input();
        pro();
    }

    static void pro() {
        int click = Math.abs(N - 100); //+, -를 누르는 횟수

        for (int i = 0; i < 1000000; i++) {
            String s = String.valueOf(i);
            int len = s.length();

            boolean isBreak = false;
            for (int j = 0; j < len; j++) {
                if (error[s.charAt(j) - '0']) {
                    isBreak = true;
                    break;
                }
            }

            //i에서 누른 버튼들이 모두 고장나지 않을 경우
            if (!isBreak) {
                int value = Math.abs(N - i) + len;
                click = Math.min(value, click);
            }
        }
        System.out.println(click);
    }


    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String sN = br.readLine();

        N = atoi(sN);

        int errorCnt = atoi(br.readLine());
        error = new boolean[10];

        //고장난 게 없을 경우 예외처리
        if (errorCnt == 0) {
            return;
        }

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < errorCnt; i++) {
            int idx = atoi(st.nextToken());
            error[idx] = true;
        }
    }
}