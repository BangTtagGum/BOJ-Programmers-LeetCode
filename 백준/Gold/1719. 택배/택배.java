import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static StringTokenizer st;

    static class Case{
        int firstStorage;
        int storageNum;
        int weight;

        public Case(int firstStorage, int storageNum, int weight) {
            this.firstStorage = firstStorage;
            this.storageNum = storageNum;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        Map<Integer, Map<Integer, Integer>> roadMap = new HashMap<>();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            roadMap.putIfAbsent(a, new HashMap<>());
            roadMap.putIfAbsent(b, new HashMap<>());

            roadMap.get(a).put(b, w);
            roadMap.get(b).put(a, w);
        }

        int[][] answer = new int[n+1][n+1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                answer[i][j] = -1;
            }
        }

        for (int i = 1; i <= n; i++) {
            getShortestWay(i, roadMap, answer);
        }

        // 출력
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == j) {
                    System.out.print("- ");

                } else {
                    System.out.print(answer[i][j]+" ");
                }
            }
            System.out.println();
        }
    }

    private static void getShortestWay(int i, Map<Integer, Map<Integer, Integer>> roadMap,
            int[][] answer) {
        Map<Integer, Integer> distMap = new HashMap<>();

        PriorityQueue<Case> pq = new PriorityQueue<>((a, b) -> a.weight - b.weight);

        pq.add(new Case(-1, i, 0));
        while (!pq.isEmpty()) {
            Case cur = pq.poll();

            if (answer[i][cur.storageNum] == -1) {
                answer[i][cur.storageNum] = cur.firstStorage;
            }

            if (!distMap.containsKey(cur.storageNum)) {
                distMap.put(cur.storageNum, cur.weight);

                if (roadMap.containsKey(cur.storageNum)) {
                    for (Entry<Integer, Integer> e : roadMap.get(cur.storageNum).entrySet()) {
                        if (cur.firstStorage == -1) {
                            pq.add(new Case(e.getKey(), e.getKey(), e.getValue() + cur.weight));
                        } else {
                            pq.add(new Case(cur.firstStorage, e.getKey(),
                                    e.getValue() + cur.weight));
                        }
                    }
                }
            }
        }

    }
}