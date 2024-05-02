import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int[] durability;
    static int brokenBeltCnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n, k;

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        durability = new int[2 * n];
        boolean[] robot = new boolean[2 * n];

        for (int i = 0; i < 2 * n; i++) {
            durability[i] = Integer.parseInt(st.nextToken());
        }

        int stackPoint = 0;
        int pickupPoint = n - 1;

        int step = 0;
        Queue<Integer> q = new LinkedList<>();
        while (k > brokenBeltCnt) {
            step++;

            // 1. 벨트 회전
            stackPoint = (stackPoint + 2 * n - 1) % (2 * n);
            pickupPoint = (pickupPoint + 2 * n - 1) % (2 * n);

            // 2. 로봇 이동
            int len = q.size();
            for (int i = 0; i < len; i++) {
                Integer idx = q.poll();
                // 내려야 하는 위치면 내리기
                if (idx == pickupPoint) {
                    robot[idx] = false;
                    continue;
                }

                int next = (idx + 1) % (2 * n);
                // 로봇이 다음칸에 없다면 이동
                if (!robot[next] && durability[next] != 0) {
                    robot[idx] = false;
                    reduceDurability(next);
                    // 다음칸이 픽업 칸이라면 바로 로봇 내리기
                    if (next == pickupPoint) {
                        continue;
                    }

                    robot[next] = true;
                    q.add(next);
                    continue;
                }

                // 로봇이 움직일 수 없는 경우 그대로 큐 삽입
                q.add(idx);
            }

            // 3. 로봇 올리기
            // 올리려는 벨트가 내구도가 0이 아니라면 로봇 올리기
            if (durability[stackPoint] != 0) {
                robot[stackPoint] = true;
                reduceDurability(stackPoint);

                q.add(stackPoint);
            }
        }

        System.out.println(step);

    }

    private static void reduceDurability(int index) {
        durability[index]--;
        if (durability[index] == 0) {
            brokenBeltCnt++;
        }
    }
}

