import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {

    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());

        boolean[] dp = new boolean[n + 1]; // false: 하나만 잘못 비교 (r1,r2 상관x), true: 둘다 잘 비교or 둘다 잘못 비교

        dp[0] = true;
        int target = -1;
        for (int i = 1; i < n; i++) {
            target = Integer.parseInt(st.nextToken());
            if (dp[i - 1]) {
                if (target == 0) {
                    dp[i] = true;
                } else if (target == 1) {
                    dp[i] = false;
                } else {
                    System.out.println("NO");
                    return;
                }
            } else {
                if (target == 0 || target == 2) {
                    dp[i] = false;
                } else if (target == 1) {
                    dp[i] = true;
                } else {
                    System.out.println("NO");
                    return;
                }
            }
        }
        target = Integer.parseInt(st.nextToken());
        if (dp[n - 1] && target == 0 || !dp[n - 1] && target == 1) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }

}
