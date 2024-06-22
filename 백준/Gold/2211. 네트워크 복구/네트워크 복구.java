import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

    static StringTokenizer st;

    static class Network {

        int node;
        int time;
        ArrayList<int[]> fixedNetwork;

        public Network(int node, int time, ArrayList<int[]> fixedNetwork) {
            this.node = node;
            this.time = time;
            this.fixedNetwork = new ArrayList<>(fixedNetwork);
        }

    }

    static boolean[] isFixed;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        isFixed = new boolean[n + 1];

        Map<Integer, Map<Integer, Integer>> networkMap = new HashMap<>();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            networkMap.putIfAbsent(a, new HashMap<>());
            networkMap.putIfAbsent(b, new HashMap<>());

            networkMap.get(a).put(b, c);
            networkMap.get(b).put(a, c);
        }

        PriorityQueue<Network> pq = new PriorityQueue<>((a, b) -> {
            if (a.time == b.time) {
                return a.fixedNetwork.size() - b.fixedNetwork.size();
            }
            return a.time - b.time;
        });

        Map<Integer, Set<Integer>> fixedNetworks = new HashMap<>();

        pq.add(new Network(1, 0, new ArrayList<>()));
        while (!pq.isEmpty()) {
            Network cur = pq.poll();

            // 아직 복구되지 않은 컴퓨터일 경우
            if (!isFixed[cur.node]) {
                isFixed[cur.node] = true;

                for (int[] network : cur.fixedNetwork) {
                    fixedNetworks.putIfAbsent(network[0], new HashSet<>());
                    fixedNetworks.get(network[0]).add(network[1]);
                }

                if (networkMap.containsKey(cur.node)) {
                    for (Entry<Integer, Integer> e : networkMap.get(cur.node).entrySet()) {
                        Network next = new Network(e.getKey(), cur.time + e.getValue(),
                                cur.fixedNetwork);

                        next.fixedNetwork.add(new int[]{cur.node, e.getKey()});

                        pq.add(next);
                    }
                }
            }
        }

        int fixedCount = 0;
        for (Integer key : fixedNetworks.keySet()) {
            fixedCount += fixedNetworks.get(key).size();
        }

        System.out.println(fixedCount);

        for (Integer key : fixedNetworks.keySet()) {
            for (Integer i : fixedNetworks.get(key)) {
                System.out.println(key + " " + i);
            }
        }
    }
}