import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static StringTokenizer st;

    static Map<Integer, Map<Integer, Integer>> roadMap;

    static class Case {


        int junction;
        int totalDist;

        public Case(int junction, int totalDist) {
            this.junction = junction;
            this.totalDist = totalDist;
        }
    }


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            // 교차로
            int n = Integer.parseInt(st.nextToken());
            // 도로
            int m = Integer.parseInt(st.nextToken());
            // 목적지 후보
            int t = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            // 출발지
            int s = Integer.parseInt(st.nextToken());
            // 예술가가 지나간 도로가 연결하는 교차로
            int g = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());

            // 도로의 정보
            roadMap = new HashMap<>();
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());

                roadMap.putIfAbsent(a, new HashMap<>());
                roadMap.putIfAbsent(b, new HashMap<>());
                roadMap.get(a).put(b, d);
                roadMap.get(b).put(a, d);
            }

            // 목적지 후보
            ArrayList<Integer> destinations = new ArrayList<>();
            for (int i = 0; i < t; i++) {
                int destination = Integer.parseInt(br.readLine());
                int shortestDist = getShortestDist(s, destination);

                int sTogDist = getShortestDist(s, g);
                int gTohDist = roadMap.get(g).get(h);
                int hTodDist = getShortestDist(h, destination);

                int sTohDist = getShortestDist(s, h);
                int gTodDist = getShortestDist(g, destination);

                // 시작점에서 g까지 최단거리 + g 에서 h 까지 + h에서 목적지까지
                // 시작점에서 h까지 최단거리 + h 에서 g 까지 + g에서 목적지까지
                // 위 두가지 경우가 시작점에서 목적지까지 거리와 같을 경우
                if (sTogDist + +gTohDist + hTodDist == shortestDist
                        || sTohDist + gTohDist + gTodDist == shortestDist) {
                    destinations.add(destination);
                }
            }
            sortAndPrint(destinations);
        }
    }

    private static int getShortestDist(int start, int destination) {
        // 각 교차로까지 최단거리 저장 맵
        Map<Integer, Integer> distMap = new HashMap<>();

        PriorityQueue<Case> pq = new PriorityQueue<>((a, b) -> a.totalDist - b.totalDist);
        pq.add(new Case(start, 0));
        while (!pq.isEmpty()) {
            Case cur = pq.poll();

            if (cur.junction == destination) {
                return cur.totalDist;
            }
            // 처음 방문하는 교차로거나 || 같은 거리 다른 길로 종착점에 도착할 경우
            if (!distMap.containsKey(cur.junction)) {
                distMap.put(cur.junction, cur.totalDist);

                if (roadMap.containsKey(cur.junction)) {
                    for (Entry<Integer, Integer> e : roadMap.get(cur.junction).entrySet()) {
                        int nextJunction = e.getKey();
                        int dist = e.getValue();
                        pq.add(new Case(nextJunction, cur.totalDist + dist));
                    }
                }
            }
        }
        return -1;
    }


    private static void sortAndPrint(ArrayList<Integer> destinations) {
        if (destinations.isEmpty()) {
            return;
        }

        destinations.sort(Comparator.naturalOrder());
        for (Integer destination : destinations) {
            System.out.print(destination + " ");
        }

        System.out.println();
    }

}

