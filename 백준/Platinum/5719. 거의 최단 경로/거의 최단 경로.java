import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static StringTokenizer st;
    static StringBuilder sb;
    static int n, m;
    static int start, end;
    static Map<Integer, Map<Integer, Integer>> roadMap;
    static Map<Integer, Integer> distMap;

    static class Road {

        int end;
        int dist;
        ArrayList<Integer> visitedCitys = new ArrayList<>(550);

        public Road(int end, int dist) {
            this.end = end;
            this.dist = dist;
        }

        public Road(int end, int dist, ArrayList<Integer> visitedCitys) {
            this.end = end;
            this.dist = dist;
            this.visitedCitys = new ArrayList<>(visitedCitys);

            this.visitedCitys.add(end);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        while (true) {

            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            // 테스트 케이스 종료 조건
            if (n == 0 && m == 0) {
                break;
            }

            st = new StringTokenizer(br.readLine());
            start = Integer.parseInt(st.nextToken());
            end = Integer.parseInt(st.nextToken());

            // 도시 인접 도로 맵
            roadMap = new HashMap<>(10050);

            // 도로 정보 저장
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                int p = Integer.parseInt(st.nextToken());

                roadMap.putIfAbsent(u, new HashMap<>());
                roadMap.get(u).put(v, p);
            }

            banShortestWay(start, end);

            sb.append(almostShortestWay(start, end)).append("\n");
        }
        System.out.println(sb);
    }


    // 목적지까지의 최단거리 경로 금지
    static void banShortestWay(int s, int e) {
        
        
        distMap = new HashMap<>(10050);

        PriorityQueue<Road> pq = new PriorityQueue<>((r1, r2) -> r1.dist - r2.dist);

        pq.add(new Road(s, 0));

        int shortestDist = Integer.MAX_VALUE;
        
        while (!pq.isEmpty()) {
            Road road = pq.poll();
            int cityNum = road.end;
            int dist = road.dist;

            if (dist > shortestDist) {
                break;
            }

            // 최단거리 목적지 도착
            if (cityNum == e) {
                shortestDist = dist;

                int previousCityNum = start;
                // 도로 금지하기
                for (Integer visitedCityNum : road.visitedCitys) {
                    if (roadMap.containsKey(previousCityNum)) {
                        roadMap.get(previousCityNum).put(visitedCityNum, -1);
                    }
                    previousCityNum = visitedCityNum;
                }
                continue;
            }

            if (!distMap.containsKey(cityNum)) {
                distMap.put(cityNum, dist);
                if (roadMap.containsKey(cityNum)) {
                    for (Entry<Integer, Integer> entry : roadMap.get(cityNum).entrySet()) {
                        pq.add(new Road(entry.getKey(), dist + entry.getValue(),
                                road.visitedCitys));
                    }
                }
            } else {
                // 같은 거리 다른 방향으로 도시에 도착하는 경우도 도로를 금지
                if (distMap.get(cityNum) < dist) {
                    continue;
                }
                int size = road.visitedCitys.size();

                if (size < 2) {
                    roadMap.get(start).put(cityNum, -1);
                } else {
                    roadMap.get(road.visitedCitys.get(size - 2)).put(cityNum, -1);
                }
            }
        }
    }

    static int almostShortestWay(int s, int e) {
        distMap = new HashMap<>(10050);

        PriorityQueue<Road> pq = new PriorityQueue<>((r1, r2) -> r1.dist - r2.dist);

        pq.add(new Road(s, 0));

        while (!pq.isEmpty()) {
            Road road = pq.poll();
            int cityNum = road.end;
            int dist = road.dist;

            if (cityNum == e) {
                return dist;
            }
            if (!distMap.containsKey(cityNum)) {
                distMap.put(cityNum, dist);
                if (roadMap.containsKey(cityNum)) {
                    for (Entry<Integer, Integer> entry : roadMap.get(cityNum).entrySet()) {
                        // 금지된 도로면 점프
                        if (entry.getValue() == -1) {
                            continue;
                        }
                        pq.add(new Road(entry.getKey(), dist + entry.getValue()));
                    }
                }
            }
        }
        return -1;
    }

}