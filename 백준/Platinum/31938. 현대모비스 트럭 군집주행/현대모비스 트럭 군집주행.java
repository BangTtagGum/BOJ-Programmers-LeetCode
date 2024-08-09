import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {

    static StringTokenizer st;
    static Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
    static class Truck{
        int prev;
        int city;
        int cost;
        long totalCost;

        public Truck(int prev, int city, int cost, long totalCost) {
            this.prev = prev;
            this.city = city;
            this.cost = cost;
            this.totalCost = totalCost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            if (a == b) {
                continue;
            }

            map.putIfAbsent(a, new HashMap<>());
            map.putIfAbsent(b, new HashMap<>());

            // 이미 a -> b 로 가는 더 짧은 도로가 있을경우 패스
            if (map.get(a).containsKey(b)) {
                if (map.get(a).get(b) < cost) {
                    continue;
                }
            }
            map.get(a).put(b, cost);
            map.get(b).put(a, cost);
        }


        PriorityQueue<Truck> pq = new PriorityQueue<>((a, b) -> {
            if(a.totalCost < b.totalCost){
                return -1;
            }
            return 1;
        });

        pq.add(new Truck(1,1, 0,0));

        // 도시 사이 최단 거리 저장 맵
        Map<Integer, long[]> costMap = new HashMap<>();

        while (!pq.isEmpty()) {
            Truck truck = pq.poll();

            // 도시에 처음 방문하는 경우
            if (!costMap.containsKey(truck.city) || costMap.get(truck.city)[0] == truck.totalCost
                    && costMap.get(truck.city)[1] > truck.cost) {

                costMap.put(truck.city, new long[]{truck.totalCost, truck.cost});
//                map.get(truck.city).put(truck.prev, -1);
                for (Entry<Integer, Integer> e : map.get(truck.city).entrySet()) {
                    int nextCity = e.getKey();
                    int cost = e.getValue();
//                    if (cost == -1) {
//                        continue;
//                    }
                    pq.add(new Truck(truck.city,nextCity, cost,truck.totalCost + cost));
                }
            }
        }
        // 트럭이 사용하는 마지막 도로 * 0.1

        long result = 0;
        for (int i = 2; i <= n; i++) {
            result += (long) (costMap.get(i)[0] * 0.9);
            result += (long) (costMap.get(i)[1] * 0.1);
        }
        System.out.println(result);
    }
}