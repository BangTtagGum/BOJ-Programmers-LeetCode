import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

class Solution {

    static class Case {

        boolean[] isVisited;
        int node;
        int sheepCount;
        int wolfCount;

        public Case(boolean[] isVisited, int node, int sheepCount, int wolfCount) {
            this.isVisited = isVisited;
            this.node = node;
            this.sheepCount = sheepCount;
            this.wolfCount = wolfCount;
        }

    }

    public int solution(int[] info, int[][] edges) {

        int totalSheepCount = 0;
        for (int i = 0; i < info.length; i++) {
            if (info[i] == 0) {
                totalSheepCount++;
            }
        }

        Map<Integer,List<Integer>> childMap = new HashMap<>();
        Map<Integer,Integer>  parentMap = new HashMap<>();

        for(int[] edge: edges){

            childMap.putIfAbsent(edge[0], new ArrayList<>());
            childMap.get(edge[0]).add(edge[1]);

            parentMap.put(edge[1],edge[0]);

        }

        int[] maxSheepInfo = new int[info.length];

//        Queue<Case> q = new PriorityQueue<>((c1,c2) -> c2.sheepCount - c1.sheepCount);
        Queue<Case> q = new LinkedList<>();

        q.add(new Case(new boolean[info.length], 0, 0, 0));

        while (!q.isEmpty()) {
            Case cur = q.poll();

            // 첫 방문시만 늑대, 양 추가
            if (!cur.isVisited[cur.node]) {
                cur.isVisited[cur.node] = true;

                if (info[cur.node] == 0) {
                    cur.sheepCount++;
                } else {
                    cur.wolfCount++;
                }

                // 늑대가 양과 같아지면 해당 케이스 탐색 종료
                if (cur.sheepCount == cur.wolfCount) {
                    continue;
                }
            }

            // 양을 전부 모았으면 반환
            if (totalSheepCount == cur.sheepCount) {
                return cur.sheepCount;
            }

            // 현재 노드 방문 시 가질 수 있는 양의 최대값 갱신
            maxSheepInfo[cur.node] = cur.sheepCount;

            // 이어진 노드들 탐색
            if (childMap.get(cur.node) != null) {
                for (int next : childMap.get(cur.node)) {

                    // 중복방문을 예방하기 위해 양을 더 모으지 못했다면 다음 노드로 갈 수 없다.
                    if (!cur.isVisited[next] || maxSheepInfo[next] < cur.sheepCount) {
                        q.add(new Case(Arrays.copyOf(cur.isVisited, cur.isVisited.length), next,
                                cur.sheepCount, cur.wolfCount));
                    }
                }
            }

            // 부모노드 탐색
            if (parentMap.containsKey(cur.node)) {
                q.add(new Case(Arrays.copyOf(cur.isVisited, cur.isVisited.length), parentMap.get(cur.node),
                        cur.sheepCount, cur.wolfCount));
            }
        }

        int maxSheepCount = 0;
        for (int i = 0; i < info.length; i++) {
            if (maxSheepInfo[i] > maxSheepCount) {
                maxSheepCount = maxSheepInfo[i];
            }
        }

        return maxSheepCount;
    }
}