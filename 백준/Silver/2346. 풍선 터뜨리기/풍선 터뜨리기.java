import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        ArrayDeque<int[]> dq = new ArrayDeque<>();

        for (int i = 1; i <= n; i++) {
            int num = Integer.parseInt(st.nextToken());
            dq.offer(new int[]{i, num});
        }

        while (true) {

            int[] cur = dq.poll();
            int num = cur[0];
            int val = cur[1];
            int size = dq.size();

            sb.append(num).append(" ");

            if (dq.isEmpty()) {
                break;
            }

            if (val >= 0) {
                for (int i = 0; i < val - 1; i++) {
                    dq.offerLast(dq.poll());
                }
            } else {
                for (int i = 0; i < val * -1; i++) {
                    dq.offerFirst(dq.pollLast());
                }
            }
        }

        System.out.println(sb);
    }
}