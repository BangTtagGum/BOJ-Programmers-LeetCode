import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            int[] delay = new int[n + 1];
            int[] needToBuildCount = new int[n + 1];

            st = new StringTokenizer(br.readLine());

            for (int i = 1; i <= n; i++) {
                delay[i] = Integer.parseInt(st.nextToken());
            }

            Map<Integer, List<Integer>> relationMap = new HashMap<>();

            for (int i = 0; i < k; i++) {
                st = new StringTokenizer(br.readLine());

                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                relationMap.putIfAbsent(a, new ArrayList<>());
                relationMap.get(a).add(b);
                needToBuildCount[b]++;

            }

            int w = Integer.parseInt(br.readLine());

            Queue<int[]> q = new PriorityQueue<>((a,b) -> a[1] - b[1]);
            Map<Integer, Integer> constructTimeMap = new HashMap<>();

            for (int i = 1; i <= n; i++) {
                if (needToBuildCount[i] == 0) {
                    q.add(new int[]{i, delay[i]});
                }
            }

            while (!q.isEmpty()) {
                int size = q.size();
                for (int i = 0; i < size; i++) {
                    int[] cur = q.poll();
                    int constructNum = cur[0];
                    int constructTime = cur[1];

                    if (constructNum == w) {
                        System.out.println(constructTime);
                        break ;
                    }

                    // 처음 건설하는 경우만 탐색
                    if (!constructTimeMap.containsKey(constructNum)) {
                        constructTimeMap.put(constructNum, constructTime);

                        if (relationMap.containsKey(constructNum)) {
                            for (Integer next : relationMap.get(constructNum)) {
                                needToBuildCount[next]--;
                                if (needToBuildCount[next] == 0) {
                                    q.add(new int[]{next, constructTime + delay[next]});
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}