import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int n, m;
    static int startCityNum, endCityNum;
    static Map<Integer, Map<Integer, List<Integer>>> roadMap;
    static Map<Integer, Integer> costMap;
    static StringTokenizer st;

    static class Case {

        ArrayList<Integer> citiesPassedBy;
        int cityNum;
        int totalCost;

        public Case(int cityNum, int totalCost) {
            this.citiesPassedBy = new ArrayList<>();
            this.citiesPassedBy.add(cityNum);
            this.cityNum = cityNum;
            this.totalCost = totalCost;

        }

        public Case(ArrayList<Integer> arr, int cityNum, int totalCost) {
            this.citiesPassedBy = new ArrayList<>(arr);
            this.citiesPassedBy.add(cityNum);
            this.cityNum = cityNum;
            this.totalCost = totalCost;
        }
    }

    public static void main(String[] args) throws IOException {
        init();

        PriorityQueue<Case> pq = new PriorityQueue<>((c1, c2) -> c1.totalCost - c2.totalCost);

        pq.add(new Case(startCityNum, 0));
        while (!pq.isEmpty()) {
            Case cur = pq.poll();

            // 목표 도시 도착 시
            if (cur.cityNum == endCityNum) {
                System.out.println(cur.totalCost);
                System.out.println(cur.citiesPassedBy.size());
                for (Integer city : cur.citiesPassedBy) {
                    System.out.print(city + " ");
                }
                break;
            }

            if (!costMap.containsKey(cur.cityNum)) {
                costMap.put(cur.cityNum, cur.totalCost);

                // 도착한 도시에서 다른 도시로 가는 도로가 존재한다면 새로운 케이스 추가
                if (roadMap.containsKey(cur.cityNum)) {
                    for (Entry<Integer, List<Integer>> e : roadMap.get(cur.cityNum).entrySet()) {
                        for (Integer cost : e.getValue()) {
                            pq.add(new Case(cur.citiesPassedBy, e.getKey(),
                                    cur.totalCost + cost));
                        }
                    }
                }
            }
        }
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        roadMap = new HashMap<>();
        costMap = new HashMap<>();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            roadMap.putIfAbsent(start, new HashMap<>());
            roadMap.get(start).putIfAbsent(end, new ArrayList<>());
            roadMap.get(start).get(end).add(cost);

        }

        st = new StringTokenizer(br.readLine());

        startCityNum = Integer.parseInt(st.nextToken());
        endCityNum = Integer.parseInt(st.nextToken());
    }

}
